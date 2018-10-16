package com.linli.consumer.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public class ShopMenuAll implements Serializable {
    private  ShopMenusign shopMenusign;

    public ShopMenusign getShopMenusign() {
        return shopMenusign;
    }

    public void setShopMenusign(ShopMenusign shopMenusign) {
        this.shopMenusign = shopMenusign;
    }

    public List<ShopMenu> getShopMenuList() {
        return shopMenuList;
    }

    public void setShopMenuList(List<ShopMenu> shopMenuList) {
        this.shopMenuList = shopMenuList;
    }

    private List<ShopMenu> shopMenuList;



}
