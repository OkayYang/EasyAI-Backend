package com.easyai.client.custom.controller.card.vo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;

/**
 * UseCardKeyBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-21T16:17:39.832621600+08:00[Asia/Shanghai]")

public class UseCardKeyBody   {
  @JsonProperty("card_key")
  private String cardKey;

  public UseCardKeyBody cardKey(String cardKey) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UseCardKeyBody useCardKeyBody = (UseCardKeyBody) o;
    return Objects.equals(this.cardKey, useCardKeyBody.cardKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cardKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UseCardKeyBody {\n");

    sb.append("    cardKey: ").append(toIndentedString(cardKey)).append("\n");
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

