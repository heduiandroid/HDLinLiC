package com.linli.consumer.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tomoyo on 2016/12/2.
 */

@Entity
public class GoodsBean implements Serializable {

    @Id
    private Long id;        //数据库主键，使用greenDao，类型为Long

    //private BigDecimal price;


    private long goodId;    //商品id
    private String goodsName;   //商品名称

    /**商城商品专属属性*/
    private long goodsSpec ;  //商品规格id
    private String goodsSpecName;   //规格名称
    private int inventory;      //规格库存

    /**美食专属属性*/
    private int ispackagecost ; //美食是否需要打包费

    private int number;     //商品的数量
    private double price;    //价格

    private String imagePath;//图片路径

    private boolean isChoice;   //是否被选中

    //@ToMany 一对多
    private long shopId;    //商家id
    private String shopName;//商家名称

    private int type;       //商家类型




    @Generated(hash = 249195362)
    public GoodsBean(Long id, long goodId, String goodsName, long goodsSpec,
            String goodsSpecName, int inventory, int ispackagecost, int number,
            double price, String imagePath, boolean isChoice, long shopId,
            String shopName, int type) {
        this.id = id;
        this.goodId = goodId;
        this.goodsName = goodsName;
        this.goodsSpec = goodsSpec;
        this.goodsSpecName = goodsSpecName;
        this.inventory = inventory;
        this.ispackagecost = ispackagecost;
        this.number = number;
        this.price = price;
        this.imagePath = imagePath;
        this.isChoice = isChoice;
        this.shopId = shopId;
        this.shopName = shopName;
        this.type = type;
    }
    @Generated(hash = 1806305570)
    public GoodsBean() {
    }





    public String getShopName() {
        return this.shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public long getShopId() {
        return this.shopId;
    }
    public void setShopId(long shopId) {
        this.shopId = shopId;
    }
    public boolean getIsChoice() {
        return this.isChoice;
    }
    public void setIsChoice(boolean isChoice) {
        this.isChoice = isChoice;
    }
    public String getImagePath() {
        return this.imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getGoodsName() {
        return this.goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public long getGoodId() {
        return this.goodId;
    }
    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getGoodsSpec() {
        return this.goodsSpec;
    }
    public void setGoodsSpec(long goodsSpec) {
        this.goodsSpec = goodsSpec;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getGoodsSpecName() {
        return this.goodsSpecName;
    }
    public void setGoodsSpecName(String goodsSpecName) {
        this.goodsSpecName = goodsSpecName;
    }
    public int getInventory() {
        return this.inventory;
    }
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    public int getIspackagecost() {
        return this.ispackagecost;
    }
    public void setIspackagecost(int ispackagecost) {
        this.ispackagecost = ispackagecost;
    }




}
