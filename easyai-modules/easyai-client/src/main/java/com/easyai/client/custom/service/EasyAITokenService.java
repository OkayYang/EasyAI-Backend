package com.easyai.client.custom.service;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.easyai.client.custom.constant.EasyAIConstants;
import com.easyai.client.custom.model.EasyAILoginUser;
import jakarta.servlet.http.HttpServletRequest;

import com.easyai.common.core.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.easyai.common.core.constant.CacheConstants;
import com.easyai.common.core.constant.SecurityConstants;
import com.easyai.common.core.utils.JwtUtils;
import com.easyai.common.core.utils.ServletUtils;
import com.easyai.common.core.utils.StringUtils;
import com.easyai.common.core.utils.ip.IpUtils;
import com.easyai.common.core.utils.uuid.IdUtils;
import com.easyai.common.redis.service.RedisService;
import com.easyai.common.security.utils.SecurityUtils;
/**
 * token验证处理
 *
 * @author ruoyi
 */
@Component
public class EasyAITokenService
{
    private static final Logger log = LoggerFactory.getLogger(EasyAITokenService.class);

    @Autowired
    private RedisService redisService;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private final static long expireTime = CacheConstants.EXPIRATION;

    private final static String ACCESS_TOKEN = CacheConstants.CLIENT_LOGIN_TOKEN_KEY;

    private final static Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;

    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(EasyAILoginUser loginUser)
    {
        String token = IdUtils.fastUUID();
        Long userId = loginUser.getEasyAIUser().getId();
        String userName = loginUser.getEasyAIUser().getUsername();
        loginUser.setToken(token);
        loginUser.setUserid(userId);
        loginUser.setUsername(userName);
        loginUser.setIpaddr(IpUtils.getIpAddr());
        refreshToken(loginUser);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);
        claimsMap.put(SecurityConstants.TOKEN_TYPE, EasyAIConstants.NAME);

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put("access_token", JwtUtils.createToken(claimsMap));
        rspMap.put("expires_in", expireTime);
        return rspMap;
    }


    /**
     * 获取Gpt用户身份信息
     *
     * @return 用户信息
     */
    public EasyAILoginUser getLoginUser()
    {
        return getLoginUser(ServletUtils.getRequest());
    }



    /**
     * 获取GPT用户身份信息
     *
     * @return 用户信息
     */
    public EasyAILoginUser getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = SecurityUtils.getToken(request);
        return getLoginUser(token);
    }



    /**
     * 获取Gpt用户身份信息
     *
     * @return 用户信息
     */
    public EasyAILoginUser getLoginUser(String token)
    {
        EasyAILoginUser user = null;
        try
        {
            if (StringUtils.isNotEmpty(token))
            {
                String userkey = JwtUtils.getUserKey(token);
                user = redisService.getCacheObject(getTokenKey(userkey));
                return user;
            }
        }
        catch (Exception e)
        {
            log.error("获取用户信息异常'{}'", e.getMessage());
        }
        return user;
    }







    public void setLoginUser(EasyAILoginUser loginUser)
    {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken()))
        {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户缓存信息
     */
    public void delLoginUser(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            String userkey = JwtUtils.getUserKey(token);
            redisService.deleteObject(getTokenKey(userkey));
        }
    }

    /**
     * 验证令牌有效期，相差不足120分钟，自动刷新缓存
     *
     * @param loginUser
     */

    public void verifyToken(EasyAILoginUser loginUser)
    {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(EasyAILoginUser loginUser)
    {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String token)
    {
        return ACCESS_TOKEN + token;
    }
}
