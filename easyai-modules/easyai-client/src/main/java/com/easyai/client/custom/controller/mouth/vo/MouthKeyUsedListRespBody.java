package com.easyai.client.custom.controller.mouth.vo;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * MouthKeyListRespBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-22T21:24:57.221606800+08:00[Asia/Shanghai]")

public class MouthKeyUsedListRespBody {
  @JsonProperty("category")
  private String category;

  @JsonProperty("reward")
  private Long reward;

  @JsonProperty("create_time")
  private Date createTime;

  public MouthKeyUsedListRespBody category(String category) {
    this.category = category;
    return this;
  }

  /**
   * 口令
   * @return category
  */

  @NotNull


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public MouthKeyUsedListRespBody reward(Long reward) {
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

  public MouthKeyUsedListRespBody createTime(Date createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * 使用时间
   * @return createTime
  */

  @NotNull

  @Valid

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
    MouthKeyUsedListRespBody mouthKeyUsedListRespBody = (MouthKeyUsedListRespBody) o;
    return Objects.equals(this.category, mouthKeyUsedListRespBody.category) &&
        Objects.equals(this.reward, mouthKeyUsedListRespBody.reward) &&
        Objects.equals(this.createTime, mouthKeyUsedListRespBody.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, reward, createTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MouthKeyListRespBody {\n");

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

