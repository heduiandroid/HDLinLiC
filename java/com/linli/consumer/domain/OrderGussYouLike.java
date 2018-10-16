package com.linli.consumer.domain;

/**
 * Created by wwy on 2016/5/22.
 */
public class OrderGussYouLike {
    private Long Id;
    private String shopname;//所属商家 商家名
    private String name;//		菜名
    private String imgpath;//	图片路径
    private String price;//	价格
    private String salenum;//已售（销量）
    private String shopsummary;//商店简介
    private String pricegate;//门市价
    private String addressshop;//地址
    private String addplace;//地址简介
    private String distance;//距离
    private String lati;//纬度
    private String longti;//经度
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongti() {
        return longti;
    }

    public void setLongti(String longti) {
        this.longti = longti;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPricegate() {
        return pricegate;
    }

    public void setPricegate(String pricegate) {
        this.pricegate = pricegate;
    }

    public String getShopsummary() {
        return shopsummary;
    }

    public void setShopsummary(String shopsummary) {
        this.shopsummary = shopsummary;
    }

    public String getSalenum() {
        return salenum;
    }

    public void setSalenum(String salenum) {
        this.salenum = salenum;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }




    public String getAddressshop() {
        return addressshop;
    }

    public void setAddressshop(String addressshop) {
        this.addressshop = addressshop;
    }

    public String getAddplace() {
        return addplace;
    }

    public void setAddplace(String addplace) {
        this.addplace = addplace;
    }
}
