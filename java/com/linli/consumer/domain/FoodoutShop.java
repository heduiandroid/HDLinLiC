package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/5/22.
 */
public class FoodoutShop {

    private Integer Id;
    private Integer imagesrc;//图片路径
    private String shopname;//商家名称
    private String salenum;//销量
    private String viplevel;//店铺等级
    private String distance;//距离

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {

        this.distance = distance;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setImagesrc(Integer imagesrc) {
        this.imagesrc = imagesrc;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public void setSalenum(String salenum) {
        this.salenum = salenum;
    }

    public void setViplevel(String viplevel) {
        this.viplevel = viplevel;
    }

    public void setBegingive(String begingive) {
        this.begingive = begingive;
    }

    public void setThorn(String thorn) {
        this.thorn = thorn;
    }

    public Integer getId() {

        return Id;
    }

    public Integer getImagesrc() {
        return imagesrc;
    }

    public String getShopname() {
        return shopname;
    }

    public String getSalenum() {
        return salenum;
    }

    public String getViplevel() {
        return viplevel;
    }

    public String getBegingive() {
        return begingive;
    }

    public String getThorn() {
        return thorn;
    }

    private String begingive;//起送价
    private String thorn;//配送费
}
