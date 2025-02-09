package com.easyai.client;


import com.easyai.common.security.annotation.EnableCustomConfig;
import com.easyai.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 用户模块
 *
 * @author xiaoyang
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
@EnableCaching
public class RuoYiClientApplication
{
    public static void main(String[] args)
    {
//        String proxy="127.0.0.1";
//        int port=7890;
//        System.setProperty("proxyType","4");
//        System.setProperty("proxyPort",Integer.toString(port));
//        System.setProperty("proxyHost",proxy);
//        System.setProperty("proxySet","true");
        SpringApplication.run(RuoYiClientApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  用户模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
