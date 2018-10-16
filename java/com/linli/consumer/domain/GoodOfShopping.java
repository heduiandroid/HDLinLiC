package com.linli.consumer.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/20.
 */
public class GoodOfShopping implements Serializable{//一招鲜界面食品

    public GoodOfShopping(int imageid, String name, double price) {
        this.imageid = imageid;
        this.name = name;
        this.price = price;
    }
    public GoodOfShopping() {

    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }


    private int imageid;//测试使用，json中没有

    private int id;

    private String rmid;

    private int shopid;

    private String name;

    private double price;

    private int stock;

    private String bitvalue;

    private String imgsrc;

    private String imgsrc1;

    private String imgsrc2;

    private String imgsrc3;

    private int total;

    private int two;

    private int three;

    private int shoptotalid;

    private int shoptwoid;

    private int shopthreeid;

    private String shopname;

    private boolean isshelve;

    private String brand;

    private String type;

    private String specialprice;

    private String ispat;

    private String  createtime;

    private String updatetime;

    private boolean deleted;

    private String detail;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setRmid(String rmid){
        this.rmid = rmid;
    }
    public String getRmid(){
        return this.rmid;
    }
    public void setShopid(int shopid){
        this.shopid = shopid;
    }
    public int getShopid(){
        return this.shopid;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return this.price;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
    public int getStock(){
        return this.stock;
    }
    public void setBitvalue(String bitvalue){
        this.bitvalue = bitvalue;
    }
    public String getBitvalue(){
        return this.bitvalue;
    }
    public void setImgsrc(String imgsrc){
        this.imgsrc = imgsrc;
    }
    public String getImgsrc(){
        return this.imgsrc;
    }
    public void setImgsrc1(String imgsrc1){
        this.imgsrc1 = imgsrc1;
    }
    public String getImgsrc1(){
        return this.imgsrc1;
    }
    public void setImgsrc2(String imgsrc2){
        this.imgsrc2 = imgsrc2;
    }
    public String getImgsrc2(){
        return this.imgsrc2;
    }
    public void setImgsrc3(String imgsrc3){
        this.imgsrc3 = imgsrc3;
    }
    public String getImgsrc3(){
        return this.imgsrc3;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setTwo(int two){
        this.two = two;
    }
    public int getTwo(){
        return this.two;
    }
    public void setThree(int three){
        this.three = three;
    }
    public int getThree(){
        return this.three;
    }
    public void setShoptotalid(int shoptotalid){
        this.shoptotalid = shoptotalid;
    }
    public int getShoptotalid(){
        return this.shoptotalid;
    }
    public void setShoptwoid(int shoptwoid){
        this.shoptwoid = shoptwoid;
    }
    public int getShoptwoid(){
        return this.shoptwoid;
    }
    public void setShopthreeid(int shopthreeid){
        this.shopthreeid = shopthreeid;
    }
    public int getShopthreeid(){
        return this.shopthreeid;
    }
    public void setShopname(String shopname){
        this.shopname = shopname;
    }
    public String getShopname(){
        return this.shopname;
    }
    public void setIsshelve(boolean isshelve){
        this.isshelve = isshelve;
    }
    public boolean getIsshelve(){
        return this.isshelve;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public String getBrand(){
        return this.brand;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setSpecialprice(String specialprice){
        this.specialprice = specialprice;
    }
    public String getSpecialprice(){
        return this.specialprice;
    }
    public void setIspat(String ispat){
        this.ispat = ispat;
    }
    public String getIspat(){
        return this.ispat;
    }
    public void setCreatetime(String  createtime){
        this.createtime = createtime;
    }
    public String getCreatetime(){
        return this.createtime;
    }
    public void setUpdatetime(String updatetime){
        this.updatetime = updatetime;
    }
    public String getUpdatetime(){
        return this.updatetime;
    }
    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }
    public boolean getDeleted(){
        return this.deleted;
    }
    public void setDetail(String detail){
        this.detail = detail;
    }
    public String getDetail(){
        return this.detail;
    }

}
