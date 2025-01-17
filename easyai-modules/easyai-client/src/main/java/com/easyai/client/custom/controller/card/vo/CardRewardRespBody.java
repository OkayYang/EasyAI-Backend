package com.easyai.client.custom.controller.card.vo;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CardRewardRespBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-01-15T23:13:38.162355600+08:00[Asia/Shanghai]")
public class CardRewardRespBody {
    @JsonProperty("reward")
    private Long reward;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("create_time")
    private Date createTime;

    public CardRewardRespBody reward(Long reward) {
        this.reward = reward;
        return this;
    }

    /**
     * 算力奖励
     *
     * @return reward
     */
    @NotNull
    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }

    public CardRewardRespBody createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * Get createTime
     *
     * @return createTime
     */

    @NotNull


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardRewardRespBody cardRewardRespBody = (CardRewardRespBody) o;
        return Objects.equals(this.reward, cardRewardRespBody.reward) &&
                Objects.equals(this.createTime, cardRewardRespBody.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reward, createTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CardRewardRespBody {\n");

        sb.append("    reward: ").append(toIndentedString(reward)).append("\n");
        sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

