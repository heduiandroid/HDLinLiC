package com.linli.consumer.bean;

/**
 * Created by tomoyo on 2016/12/13.
 * 购物车店铺名字的实体类，用于适配列表
 */

public class CartShopBean {

    private String name;    //店铺名称
    private long shopId;    //店铺id

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
