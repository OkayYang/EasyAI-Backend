package com.easyai.client.custom.controller.unauth.vo;

public class ModelRespBody {
    private String modelName;
    private Integer orderNum;
    private int price;
    private String version;

    public String getModelName() { return modelName; }
    public void setModelName(String value) { this.modelName = value; }

    public Integer getOrderNum() { return orderNum; }
    public void setOrderNum(Integer value) { this.orderNum = value; }

    public int getPrice() { return price; }
    public void setPrice(int value) { this.price = value; }

    public String getVersion() { return version; }
    public void setVersion(String value) { this.version = value; }

    @Override
    public String toString() {
        return "EasyAiModel{" +
                "modelName='" + modelName + '\'' +
                ", orderNum=" + orderNum +
                ", price=" + price +
                ", version='" + version + '\'' +
                '}';
    }
}
