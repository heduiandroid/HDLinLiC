package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/7/11.
 */
public class ShopInfoShop {

    private OrderIndexNear mOrderIndexNear;
    private ShopInfo shopinfo;

    public OrderIndexNear getOrderIndexNear() {
        return mOrderIndexNear;
    }

    public void setOrderIndexNear(OrderIndexNear orderIndexNear) {
        mOrderIndexNear = orderIndexNear;
    }

    public ShopInfo getShopinfo() {
        return shopinfo;
    }

    public void setShopinfo(ShopInfo shopinfo) {
        this.shopinfo = shopinfo;
    }
}
