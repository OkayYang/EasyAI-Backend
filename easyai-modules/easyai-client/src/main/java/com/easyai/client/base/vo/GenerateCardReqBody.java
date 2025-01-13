package com.easyai.client.base.vo;
import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.*;

/**
 * BatchCardReqBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-18T23:48:11.890135300+08:00[Asia/Shanghai]")

public class GenerateCardReqBody {
    @JsonProperty("reward")
    @Min(0)
    private Long reward;

    @JsonProperty("generateNum")
    @Min(1)
    private Long generateNum;

    @JsonProperty("category")
    private String category;

    @JsonProperty("expireTime")
    private Date expireTime;

    public GenerateCardReqBody reward(Long reward) {
        this.reward = reward;
        return this;
    }

    /**
     * Get reward
     * @return reward
     */

    @NotNull
    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }

    public GenerateCardReqBody generateNum(Long generateNum) {
        this.generateNum = generateNum;
        return this;
    }

    /**
     * Get generateNum
     * @return generateNum
     */

    @NotNull


    public Long getGenerateNum() {
        return generateNum;
    }

    public void setGenerateNum(Long generateNum) {
        this.generateNum = generateNum;
    }

    public GenerateCardReqBody category(String category) {
        this.category = category;
        return this;
    }

    /**
     * 分组
     * @return category
     */

    @NotNull


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public GenerateCardReqBody expireTime(Date expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    /**
     * Get expireTime
     * @return expireTime
     */

    @NotNull
    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GenerateCardReqBody generateCardReqBody = (GenerateCardReqBody) o;
        return Objects.equals(this.reward, generateCardReqBody.reward) &&
                Objects.equals(this.generateNum, generateCardReqBody.generateNum) &&
                Objects.equals(this.category, generateCardReqBody.category) &&
                Objects.equals(this.expireTime, generateCardReqBody.expireTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reward, generateNum, category, expireTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BatchCardReqBody {\n");
        sb.append("    reward: ").append(toIndentedString(reward)).append("\n");
        sb.append("    generateNum: ").append(toIndentedString(generateNum)).append("\n");
        sb.append("    category: ").append(toIndentedString(category)).append("\n");
        sb.append("    expireTime: ").append(toIndentedString(expireTime)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

