package com.easyai.client.custom.controller.card.vo;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;

/**
 * CardKeyUsedListRespBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-22T22:18:16.494452400+08:00[Asia/Shanghai]")

public class CardKeyUsedListRespBody   {
  @JsonProperty("card_key")
  private String cardKey;

  @JsonProperty("category")
  private String category;

  @JsonProperty("reward")
  private Long reward;

  @JsonProperty("create_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  public CardKeyUsedListRespBody cardKey(String cardKey) {
    this.cardKey = cardKey;
    return this;
  }

  /**
   * 卡密
   * @return cardKey
  */

  @NotNull
  public String getCardKey() {
    return cardKey;
  }

  public void setCardKey(String cardKey) {
    this.cardKey = cardKey;
  }

  public CardKeyUsedListRespBody category(String category) {
    this.category = category;
    return this;
  }

  /**
   * 卡密类别
   * @return category
  */

  @NotNull


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public CardKeyUsedListRespBody reward(Long reward) {
    this.reward = reward;
    return this;
  }

  /**
   * 算力奖励
   * @return reward
  */

  @NotNull


  public Long getReward() {
    return reward;
  }

  public void setReward(Long reward) {
    this.reward = reward;
  }

  public CardKeyUsedListRespBody createTime(Date createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * 使用时间
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
    CardKeyUsedListRespBody cardKeyUsedListRespBody = (CardKeyUsedListRespBody) o;
    return Objects.equals(this.cardKey, cardKeyUsedListRespBody.cardKey) &&
        Objects.equals(this.category, cardKeyUsedListRespBody.category) &&
        Objects.equals(this.reward, cardKeyUsedListRespBody.reward) &&
        Objects.equals(this.createTime, cardKeyUsedListRespBody.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cardKey, category, reward, createTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardKeyUsedListRespBody {\n");

    sb.append("    cardKey: ").append(toIndentedString(cardKey)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
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

