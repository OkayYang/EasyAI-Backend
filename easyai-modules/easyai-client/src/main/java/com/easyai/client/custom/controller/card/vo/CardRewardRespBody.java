package com.easyai.client.custom.controller.card.vo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;

/**
 * CardRewardRespBody
 */

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-22T14:29:12.213587300+08:00[Asia/Shanghai]")

public class CardRewardRespBody   {
  @JsonProperty("reward")
  private Long reward;

  public CardRewardRespBody reward(Long reward) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardRewardRespBody cardRewardRespBody = (CardRewardRespBody) o;
    return Objects.equals(this.reward, cardRewardRespBody.reward);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reward);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardRewardRespBody {\n");

    sb.append("    reward: ").append(toIndentedString(reward)).append("\n");
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

