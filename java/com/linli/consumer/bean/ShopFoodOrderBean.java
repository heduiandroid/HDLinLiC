package com.linli.consumer.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomoyo on 2016/12/23.
 */

public class ShopFoodOrderBean implements Serializable{

    public static class People implements Serializable{
        private int Id;
        private String peopleName;
        private String phone;
        private String address;
    }

    public List<Goods> list;

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }


    /**
     * 商品bean
     * */
    public static class Goods implements Serializable{

        /**
         * id : 214760099187012
         * orderId : 214760099186560
         * goodsNum : 1
         * createTime : 1476009918000
         * isEvaluation : 0
         * goodsId : 214752560537521
         * specId : 214752561451518
         * goodsSpec : 净含量:320g两盒
         * refundStatus : 0
         * commentStatus : 0
         * price : 39.9
         */

        private long id;
        private long orderId;
        private int goodsNum;
        private long createTime;
        private int isEvaluation;
        private long goodsId;
        private long specId;
        private String goodsSpec;
        private int refundStatus;
        private int commentStatus;
        private double price;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getIsEvaluation() {
            return isEvaluation;
        }

        public void setIsEvaluation(int isEvaluation) {
            this.isEvaluation = isEvaluation;
        }

        public long getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(long goodsId) {
            this.goodsId = goodsId;
        }

        public long getSpecId() {
            return specId;
        }

        public void setSpecId(long specId) {
            this.specId = specId;
        }

        public String getGoodsSpec() {
            return goodsSpec;
        }

        public void setGoodsSpec(String goodsSpec) {
            this.goodsSpec = goodsSpec;
        }

        public int getRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(int refundStatus) {
            this.refundStatus = refundStatus;
        }

        public int getCommentStatus() {
            return commentStatus;
        }

        public void setCommentStatus(int commentStatus) {
            this.commentStatus = commentStatus;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }


    /**
     * 下订单要的类
     * */
    public static class HdFoodOrder implements Serializable{

        /**
         * id : 214760099186560
         * orderSn : 20161009538210002
         * memberId : 8184
         * orderStatus : 3
         * paymentId : null
         * goodsAmount : 79.8
         * discount : null
         * orderAmount : 79.8
         * orderPost : null
         * storeId : 214745395500281
         * province : 北京
         * city : 北京
         * district : 朝阳区
         * address : 芍药居2号院8号楼1门602
         * logisticsAmount : 0
         * phone : 15652933345
         * point : null
         * accountStatus : 0
         * purchaser : 王丞亭
         * purchaserPhone : null
         * favorableAmount : null
         * idCard : null
         * feightTime : null
         * createTime : 1476009918000
         * payTime : 1476009929000
         * finishedTime : 1481614971000
         * status : 0
         * numtotal : 2
         * distributionid : null
         * expresscode : null
         * payOrderId : 214760099227813
         * couponId : null
         * overtime : 1476011718000
         * lastModifyTime : 1476009929000
         * isFicti : 2
         * transactionId : null
         * isAssign : null
         * voiceId : null
         * areaCode : null
         * transId : null
         * orderType : null
         * appointTime : null
         * appointPerson : null
         * appointRoom : null
         */

        private long id;
        private String orderSn;
        private int memberId;
        private int orderStatus;
        private Object paymentId;
        private double goodsAmount;
        private Object discount;
        private double orderAmount;
        private Object orderPost;
        private long storeId;
        private String province;
        private String city;
        private String district;
        private String address;
        private int logisticsAmount;
        private String phone;
        private Object point;
        private int accountStatus;
        private String purchaser;
        private Object purchaserPhone;
        private Object favorableAmount;
        private Object idCard;
        private Object feightTime;
        private long createTime;
        private long payTime;
        private long finishedTime;
        private int status;
        private int numtotal;
        private Object distributionid;
        private Object expresscode;
        private long payOrderId;
        private Object couponId;
        private long overtime;
        private long lastModifyTime;
        private int isFicti;
        private Object transactionId;
        private Object isAssign;
        private Object voiceId;
        private Object areaCode;
        private Object transId;
        private Object orderType;
        private Object appointTime;
        private Object appointPerson;
        private Object appointRoom;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public Object getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(Object paymentId) {
            this.paymentId = paymentId;
        }

        public double getGoodsAmount() {
            return goodsAmount;
        }

        public void setGoodsAmount(double goodsAmount) {
            this.goodsAmount = goodsAmount;
        }

        public Object getDiscount() {
            return discount;
        }

        public void setDiscount(Object discount) {
            this.discount = discount;
        }

        public double getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(double orderAmount) {
            this.orderAmount = orderAmount;
        }

        public Object getOrderPost() {
            return orderPost;
        }

        public void setOrderPost(Object orderPost) {
            this.orderPost = orderPost;
        }

        public long getStoreId() {
            return storeId;
        }

        public void setStoreId(long storeId) {
            this.storeId = storeId;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getLogisticsAmount() {
            return logisticsAmount;
        }

        public void setLogisticsAmount(int logisticsAmount) {
            this.logisticsAmount = logisticsAmount;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getPoint() {
            return point;
        }

        public void setPoint(Object point) {
            this.point = point;
        }

        public int getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(int accountStatus) {
            this.accountStatus = accountStatus;
        }

        public String getPurchaser() {
            return purchaser;
        }

        public void setPurchaser(String purchaser) {
            this.purchaser = purchaser;
        }

        public Object getPurchaserPhone() {
            return purchaserPhone;
        }

        public void setPurchaserPhone(Object purchaserPhone) {
            this.purchaserPhone = purchaserPhone;
        }

        public Object getFavorableAmount() {
            return favorableAmount;
        }

        public void setFavorableAmount(Object favorableAmount) {
            this.favorableAmount = favorableAmount;
        }

        public Object getIdCard() {
            return idCard;
        }

        public void setIdCard(Object idCard) {
            this.idCard = idCard;
        }

        public Object getFeightTime() {
            return feightTime;
        }

        public void setFeightTime(Object feightTime) {
            this.feightTime = feightTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getPayTime() {
            return payTime;
        }

        public void setPayTime(long payTime) {
            this.payTime = payTime;
        }

        public long getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(long finishedTime) {
            this.finishedTime = finishedTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getNumtotal() {
            return numtotal;
        }

        public void setNumtotal(int numtotal) {
            this.numtotal = numtotal;
        }

        public Object getDistributionid() {
            return distributionid;
        }

        public void setDistributionid(Object distributionid) {
            this.distributionid = distributionid;
        }

        public Object getExpresscode() {
            return expresscode;
        }

        public void setExpresscode(Object expresscode) {
            this.expresscode = expresscode;
        }

        public long getPayOrderId() {
            return payOrderId;
        }

        public void setPayOrderId(long payOrderId) {
            this.payOrderId = payOrderId;
        }

        public Object getCouponId() {
            return couponId;
        }

        public void setCouponId(Object couponId) {
            this.couponId = couponId;
        }

        public long getOvertime() {
            return overtime;
        }

        public void setOvertime(long overtime) {
            this.overtime = overtime;
        }

        public long getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(long lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public int getIsFicti() {
            return isFicti;
        }

        public void setIsFicti(int isFicti) {
            this.isFicti = isFicti;
        }

        public Object getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(Object transactionId) {
            this.transactionId = transactionId;
        }

        public Object getIsAssign() {
            return isAssign;
        }

        public void setIsAssign(Object isAssign) {
            this.isAssign = isAssign;
        }

        public Object getVoiceId() {
            return voiceId;
        }

        public void setVoiceId(Object voiceId) {
            this.voiceId = voiceId;
        }

        public Object getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(Object areaCode) {
            this.areaCode = areaCode;
        }

        public Object getTransId() {
            return transId;
        }

        public void setTransId(Object transId) {
            this.transId = transId;
        }

        public Object getOrderType() {
            return orderType;
        }

        public void setOrderType(Object orderType) {
            this.orderType = orderType;
        }

        public Object getAppointTime() {
            return appointTime;
        }

        public void setAppointTime(Object appointTime) {
            this.appointTime = appointTime;
        }

        public Object getAppointPerson() {
            return appointPerson;
        }

        public void setAppointPerson(Object appointPerson) {
            this.appointPerson = appointPerson;
        }

        public Object getAppointRoom() {
            return appointRoom;
        }

        public void setAppointRoom(Object appointRoom) {
            this.appointRoom = appointRoom;
        }
    }

}
