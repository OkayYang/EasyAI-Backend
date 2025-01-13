package com.easyai.client.custom.controller.mouth.vo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;



import jakarta.validation.constraints.*;

/**
 * MouseRewardReqBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-22T15:29:04.791735600+08:00[Asia/Shanghai]")

public class MouthRewardReqBody {
  @JsonProperty("mouth_key")
  private String mouthKey;

  public MouthRewardReqBody mouthKey(String mouthKey) {
    this.mouthKey = mouthKey;
    return this;
  }

  /**
   * 口令
   * @return mouthKey
  */

  @NotNull


  public String getMouthKey() {
    return mouthKey;
  }

  public void setMouthKey(String mouthKey) {
    this.mouthKey = mouthKey;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MouthRewardReqBody mouthRewardReqBody = (MouthRewardReqBody) o;
    return Objects.equals(this.mouthKey, mouthRewardReqBody.mouthKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mouthKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MouseRewardReqBody {\n");

    sb.append("    mouthKey: ").append(toIndentedString(mouthKey)).append("\n");
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

