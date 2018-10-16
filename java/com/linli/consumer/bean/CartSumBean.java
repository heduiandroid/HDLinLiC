package com.linli.consumer.bean;

/**
 * Created by tomoyo on 2016/12/13.
 * 购物车总价的实体类，用于适配列表
 */

public class CartSumBean {

    private String price;
    private long shopId;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
