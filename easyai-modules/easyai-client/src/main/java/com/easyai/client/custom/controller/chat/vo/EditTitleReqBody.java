package com.easyai.client.custom.controller.chat.vo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

/**
 * EditTitleReqBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-1-15T11:41:55.271058+08:00[Asia/Shanghai]")
public class EditTitleReqBody   {
    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("title")
    private String title;

    public EditTitleReqBody sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     * Get sessionId
     * @return sessionId
     */
    @NotNull
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public EditTitleReqBody title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get title
     * @return title
     */
    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EditTitleReqBody editTitleReqBody = (EditTitleReqBody) o;
        return Objects.equals(this.sessionId, editTitleReqBody.sessionId) &&
                Objects.equals(this.title, editTitleReqBody.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, title);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EditTitleReqBody {\n");

        sb.append("    sessionId: ").append(toIndentedString(sessionId)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
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


