package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/7/11.
 */
public class OrderOutShopCarMenu {
    public int getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(int menuNum) {
        this.menuNum = menuNum;
    }

    public ShopMenu getOneMenu() {
        return oneMenu;
    }

    public void setOneMenu(ShopMenu oneMenu) {
        this.oneMenu = oneMenu;
    }

    private ShopMenu oneMenu;//菜谱实体类
    private int menuNum;//份数
}
