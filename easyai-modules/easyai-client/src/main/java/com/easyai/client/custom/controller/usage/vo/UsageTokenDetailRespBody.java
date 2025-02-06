package com.easyai.client.custom.controller.usage.vo;

import java.util.Date;

/**
 * UsageTokenDetailRespBody
 */
public class UsageTokenDetailRespBody {
    private String modelName;
    private long usageCount;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public long getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(long usageCount) {
        this.usageCount = usageCount;
    }

    @Override
    public String toString() {
        return "UsageTokenDetailRespBody{" +
                "modelName='" + modelName + '\'' +
                ", usageCount=" + usageCount +
                '}';
    }
}