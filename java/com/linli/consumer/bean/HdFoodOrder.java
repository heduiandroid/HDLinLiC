package com.linli.consumer.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tomoyo on 2016/12/9.
 * 这个bean用来存放数据库数据
 */

public class HdFoodOrder implements Serializable{


    /**     公共部分    */
    private String memberId;    //用户id

    private int paymentId;   //1线上  2线下

    private double orderAmount;     //总价

    private long storeId;    //商铺id

    private long areaCode;   //区域码      from store

    private int orderType;      //订单类型      1:预订  2:外卖 3:堂吃

    private int numtotal;   //商品数量



    /**     外卖      */
    private long addressId;     //地址id              addressId

    private double logisticsAmount; //送餐费

    private double packageCost;     //打包费

    private int isByself;       //自取    1:配送    2:自取

    private int paytype;    //线上线下  1:线上    2:线下



    /**     堂吃      */
    private int appointPerson;  //人数

    private int appointRoom;    //是否包间         // 0:不包间  1:包间

    private Date appointTime;    //时间

    private String purchaser;   //联系人
    private String purchaserPhone;      //联系人电话



    private String name;        //联系人姓名         addressee
    private String phone;       //联系人电话         phone
    private String addressStr;  //地址Str           address

    private String shopPhone;   //店铺电话

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getPurchaserPhone() {
        return purchaserPhone;
    }

    public void setPurchaserPhone(String purchaserPhone) {
        this.purchaserPhone = purchaserPhone;
    }

    public int getIsByself() {
        return isByself;
    }

    public void setIsByself(int byway) {
        this.isByself = byway;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(long areaCode) {
        this.areaCode = areaCode;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getNumtotal() {
        return numtotal;
    }

    public void setNumtotal(int numtotal) {
        this.numtotal = numtotal;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public double getLogisticsAmount() {
        return logisticsAmount;
    }

    public void setLogisticsAmount(double logisticsAmount) {
        this.logisticsAmount = logisticsAmount;
    }

    public double getPackageCost() {
        return packageCost;
    }

    public void setPackageCost(double packageCost) {
        this.packageCost = packageCost;
    }

    public int getAppointPerson() {
        return appointPerson;
    }

    public void setAppointPerson(int appointPerson) {
        this.appointPerson = appointPerson;
    }

    public int getAppointRoom() {
        return appointRoom;
    }

    public void setAppointRoom(int appointRoom) {
        this.appointRoom = appointRoom;
    }

    public Date getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    public static class HdFoodOrderGoodsList implements Serializable{
        private long goodsId;
        private int goodsNum;
        private BigDecimal goodsPrice;

        public long getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(long goodsId) {
            this.goodsId = goodsId;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public BigDecimal getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(BigDecimal goodsPrice) {
            this.goodsPrice = goodsPrice;
        }
    }
}
