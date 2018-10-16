package com.linli.consumer.bean;

import java.math.BigDecimal;

/**
 * Created by tomoyo on 2017/1/11.
 */

public class FoodOrderPayBean {

    /**
     * status : 1
     * msg : 下单成功!
     * page : null
     * data : {"id":414841035021521,"orderSn":"3306b826-27bf-4656-ac45-28571dce81b9","memberId":114811598395000,"orderStatus":0,"paymentId":1,"goodsAmount":null,"discount":null,"orderAmount":120,"orderPost":null,"storeId":2,"province":null,"city":null,"district":null,"address":null,"logisticsAmount":3,"phone":null,"point":null,"accountStatus":0,"purchaser":null,"purchaserPhone":null,"favorableAmount":null,"idCard":null,"feightTime":null,"createTime":1484103502155,"payTime":null,"finishedTime":null,"status":0,"numtotal":3,"distributionid":null,"expresscode":null,"payOrderId":null,"couponId":null,"overtime":null,"lastModifyTime":null,"isFicti":null,"transactionId":null,"isAssign":null,"voiceId":null,"areaCode":null,"transId":null,"orderType":0,"appointTime":null,"appointPerson":null,"appointRoom":null,"packageCost":5,"isByself":null}
     * url : null
     */

    private int status;
    private String msg;
    private Object page;
    /**
     * id : 414841035021521
     * orderSn : 3306b826-27bf-4656-ac45-28571dce81b9
     * memberId : 114811598395000
     * orderStatus : 0
     * paymentId : 1
     * goodsAmount : null
     * discount : null
     * orderAmount : 120
     * orderPost : null
     * storeId : 2
     * province : null
     * city : null
     * district : null
     * address : null
     * logisticsAmount : 3
     * phone : null
     * point : null
     * accountStatus : 0
     * purchaser : null
     * purchaserPhone : null
     * favorableAmount : null
     * idCard : null
     * feightTime : null
     * createTime : 1484103502155
     * payTime : null
     * finishedTime : null
     * status : 0
     * numtotal : 3
     * distributionid : null
     * expresscode : null
     * payOrderId : null
     * couponId : null
     * overtime : null
     * lastModifyTime : null
     * isFicti : null
     * transactionId : null
     * isAssign : null
     * voiceId : null
     * areaCode : null
     * transId : null
     * orderType : 0
     * appointTime : null
     * appointPerson : null
     * appointRoom : null
     * packageCost : 5
     * isByself : null
     */

    private DataBean data;
    private Object url;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public static class DataBean {
        private long id;
        private String orderSn;
        private long memberId;
        private int orderStatus;
        private int paymentId;
        private Object goodsAmount;
        private Object discount;
        private double orderAmount;
        private Object orderPost;
        private long storeId;
        private Object province;
        private Object city;
        private Object district;
        private Object address;
        private double logisticsAmount;
        private Object phone;
        private Object point;
        private int accountStatus;
        private Object purchaser;
        private Object purchaserPhone;
        private Object favorableAmount;
        private Object idCard;
        private long feightTime;
        private long createTime;
        private long payTime;
        private long finishedTime;
        private int status;
        private int numtotal;
        private Object distributionid;
        private Object expresscode;
        private Object payOrderId;
        private Object couponId;
        private Object overtime;
        private Object lastModifyTime;
        private Object isFicti;
        private Object transactionId;
        private Object isAssign;
        private Object voiceId;
        private Object areaCode;
        private long transId;
        private int orderType;
        private Object appointTime;
        private Object appointPerson;
        private Object appointRoom;
        private double packageCost;
        private Object isByself;
        private BigDecimal price;

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

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

        public long getMemberId() {
            return memberId;
        }

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(int paymentId) {
            this.paymentId = paymentId;
        }

        public Object getGoodsAmount() {
            return goodsAmount;
        }

        public void setGoodsAmount(Object goodsAmount) {
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

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getDistrict() {
            return district;
        }

        public void setDistrict(Object district) {
            this.district = district;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public double getLogisticsAmount() {
            return logisticsAmount;
        }

        public void setLogisticsAmount(double logisticsAmount) {
            this.logisticsAmount = logisticsAmount;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
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

        public Object getPurchaser() {
            return purchaser;
        }

        public void setPurchaser(Object purchaser) {
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

        public long getFeightTime() {
            return feightTime;
        }

        public void setFeightTime(long feightTime) {
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

        public Object getPayOrderId() {
            return payOrderId;
        }

        public void setPayOrderId(Object payOrderId) {
            this.payOrderId = payOrderId;
        }

        public Object getCouponId() {
            return couponId;
        }

        public void setCouponId(Object couponId) {
            this.couponId = couponId;
        }

        public Object getOvertime() {
            return overtime;
        }

        public void setOvertime(Object overtime) {
            this.overtime = overtime;
        }

        public Object getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(Object lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public Object getIsFicti() {
            return isFicti;
        }

        public void setIsFicti(Object isFicti) {
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

        public long getTransId() {
            return transId;
        }

        public void setTransId(long transId) {
            this.transId = transId;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
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

        public double getPackageCost() {
            return packageCost;
        }

        public void setPackageCost(double packageCost) {
            this.packageCost = packageCost;
        }

        public Object getIsByself() {
            return isByself;
        }

        public void setIsByself(Object isByself) {
            this.isByself = isByself;
        }
    }
}
