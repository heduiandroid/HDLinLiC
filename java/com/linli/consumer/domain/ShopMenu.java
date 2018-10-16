package com.linli.consumer.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/8.
 */
public class ShopMenu implements Serializable {

    private int id;

    private String name;

    private int cuisine;

    private String maining;

    private String accessories;

    private String imgpath;

    private Double price;

    private String insiderprice;

    private Boolean status;

    private int istakeout;

    private int limitnum;

    private int reorder;

    private String menuid;

    private int menusignid;

    private int shopid;

    private String regional;

    private String ambient;

    private String age;

    private String dinnertime;

    private String physique;

    private int salenum;

    private String cookway;

    private String createtime;

//    public Integer getShopCartNum() {
//        return shopCartNum;
//    }
//
//    public void setShopCartNum(Integer shopCartNum) {
//        this.shopCartNum = shopCartNum;
//    }

    private String updatetime;

    private int deleted;
    private Integer tagId;//标签ID

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    private Integer shopCartNum;//在购物车中的商品数量

    public Integer getShopCartNum() {
        return shopCartNum;
    }

    public void setShopCartNum(Integer shopCartNum) {
        this.shopCartNum = shopCartNum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCuisine(int cuisine) {
        this.cuisine = cuisine;
    }

    public int getCuisine() {
        return this.cuisine;
    }

    public void setMaining(String maining) {
        this.maining = maining;
    }

    public String getMaining() {
        return this.maining;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getAccessories() {
        return this.accessories;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getImgpath() {
        return this.imgpath;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setInsiderprice(String insiderprice) {
        this.insiderprice = insiderprice;
    }

    public String getInsiderprice() {
        return this.insiderprice;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setIstakeout(int istakeout) {
        this.istakeout = istakeout;
    }

    public int getIstakeout() {
        return this.istakeout;
    }

    public void setLimitnum(int limitnum) {
        this.limitnum = limitnum;
    }

    public int getLimitnum() {
        return this.limitnum;
    }

    public void setReorder(int reorder) {
        this.reorder = reorder;
    }

    public int getReorder() {
        return this.reorder;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getMenuid() {
        return this.menuid;
    }

    public void setMenusignid(int menusignid) {
        this.menusignid = menusignid;
    }

    public int getMenusignid() {
        return this.menusignid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public int getShopid() {
        return this.shopid;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getRegional() {
        return this.regional;
    }

    public void setAmbient(String ambient) {
        this.ambient = ambient;
    }

    public String getAmbient() {
        return this.ambient;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return this.age;
    }

    public void setDinnertime(String dinnertime) {
        this.dinnertime = dinnertime;
    }

    public String getDinnertime() {
        return this.dinnertime;
    }

    public void setPhysique(String physique) {
        this.physique = physique;
    }

    public String getPhysique() {
        return this.physique;
    }

    public void setSalenum(int salenum) {
        this.salenum = salenum;
    }

    public int getSalenum() {
        return this.salenum;
    }

    public void setCookway(String cookway) {
        this.cookway = cookway;
    }

    public String getCookway() {
        return this.cookway;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdatetime() {
        return this.updatetime;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getDeleted() {
        return this.deleted;
    }


}
