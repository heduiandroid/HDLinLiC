package com.linli.consumer.domain;

/**
 * Created by wwy on 2016/5/23.
 */
public class ChaKanAllShop {
    private Long id;
    private int imagesrc;//图片路径
    private String shopname;//	店家名称
    private int salenum;//月销量
    private int begingive;//	起送价
    private int thorn;//	配送费
    private int distance;//	店铺与我距离	（将自己的定位经度纬度给后台，后台计算后给出此distance）
    private String shopAddress;//商店位置
    private int statNum;//星级

    public ChaKanAllShop(int imagesrc, String shopname, int salenum, int begingive, int thorn, int distance, String shopAddress, int statNum) {

        this.imagesrc = imagesrc;
        this.shopname = shopname;
        this.salenum = salenum;
        this.begingive = begingive;
        this.thorn = thorn;
        this.distance = distance;
        this.shopAddress = shopAddress;
        this.statNum = statNum;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(int imagesrc) {
        this.imagesrc = imagesrc;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public int getSalenum() {
        return salenum;
    }

    public void setSalenum(int salenum) {
        this.salenum = salenum;
    }

    public int getBegingive() {
        return begingive;
    }

    public void setBegingive(int begingive) {
        this.begingive = begingive;
    }

    public int getThorn() {
        return thorn;
    }

    public void setThorn(int thorn) {
        this.thorn = thorn;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public int getStatNum() {
        return statNum;
    }

    public void setStatNum(int statNum) {
        this.statNum = statNum;
    }
}
