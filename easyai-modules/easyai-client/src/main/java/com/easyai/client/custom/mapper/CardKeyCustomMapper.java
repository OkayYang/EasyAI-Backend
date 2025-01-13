package com.easyai.client.custom.mapper;

import com.easyai.client.base.domain.CardKey;
import com.easyai.client.base.mapper.CardKeyMapper;
import org.apache.ibatis.annotations.Param;

public interface CardKeyCustomMapper extends CardKeyMapper {

    CardKey selectCardKeyByKey(String card_key);

    int updateCardKeyStatus(@Param("card_key") String card_key, @Param("status") String status);
}
