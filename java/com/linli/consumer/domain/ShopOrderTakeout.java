package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/7/13.
 */
public class ShopOrderTakeout {

    private Takeout takeout;
    private ShopOrder shopOrder;

    public Takeout getTakeout() {
        return takeout;
    }

    public void setTakeout(Takeout takeout) {
        this.takeout = takeout;
    }

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
    }
}
