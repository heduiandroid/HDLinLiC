package com.linli.consumer.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 订单信息实体
 * Created by Administrator on 2016/3/4.
 */
public class Order implements Serializable {
    private Long id;//订单ID
    private String proName;//商品名称
    private Integer proNum;//商品数量
    private String proPic;//商品图片
    private Long shopId;//所属商家ID
    private String shopName;//所属商家 商家名称
    private String shopPic;//所属商家 图片
    private String orderNum;//订单编号
    private Double goodsAmount;//商品总价
    private Double price;//订单价格
    private Double payPrice;//支付价格
    private Long couponId;
    private Double favourableFee;//优惠金额
    private Integer type;//订单类别类别 1订餐  2外卖  3商城  4服务
    private Double freight;//运费
    private Double packageCost;//订餐用的打包费
    private Integer payKind;//支付方式
    private Integer payType;//支付类型
    private Integer status;//支付状态
    private String createTime;//订单创建时间
    private String realName;//用户真实姓名
    private String receiverPhone;//收货人电话
    private String receiverAddress;//收货人地址
    private Integer proCount;//订单包含商品个数
    private String remark;//备注信息
    private Integer isassign;//是否为指派模式 1-是 0-否
    private Long uservoiceid;//用户语音ID
    private String areacode;//地区码
    private Long transid;//用于结算的单号
    private Boolean isByUserSelfIntoShop;//-----用户要堂吃true/外卖false-----  -------用户到店自取true/送货上门false---------
    private Integer orderType;
    private String appointTime;
    private Integer appointPerson;
    private Integer appointRoom;
    private ArrayList<Product> proList;//订单包含的商品列表

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Double getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Double goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Integer getAppointRoom() {
        return appointRoom;
    }

    public void setAppointRoom(Integer appointRoom) {
        this.appointRoom = appointRoom;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public Integer getAppointPerson() {
        return appointPerson;
    }

    public void setAppointPerson(Integer appointPerson) {
        this.appointPerson = appointPerson;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Boolean getByUserSelfIntoShop() {
        return isByUserSelfIntoShop;
    }

    public void setByUserSelfIntoShop(Boolean byUserSelfIntoShop) {
        isByUserSelfIntoShop = byUserSelfIntoShop;
    }

    public Double getFavourableFee() {
        return favourableFee;
    }

    public void setFavourableFee(Double favourableFee) {
        this.favourableFee = favourableFee;
    }

    public Double getPackageCost() {
        return packageCost;
    }

    public void setPackageCost(Double packageCost) {
        this.packageCost = packageCost;
    }

    public Long getTransid() {
        return transid;
    }

    public void setTransid(Long transid) {
        this.transid = transid;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public Integer getProNum() {
        return proNum;
    }

    public void setProNum(Integer proNum) {
        this.proNum = proNum;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public Long getUservoiceid() {
        return uservoiceid;
    }

    public void setUservoiceid(Long uservoiceid) {
        this.uservoiceid = uservoiceid;
    }

    public Integer getIsassign() {
        return isassign;
    }

    public void setIsassign(Integer isassign) {
        this.isassign = isassign;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProCount() {
        return proCount;
    }

    public void setProCount(Integer proCount) {
        this.proCount = proCount;
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public ArrayList<Product> getProList() {
        return proList;
    }

    public void setProList(ArrayList<Product> proList) {
        this.proList = proList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Integer getPayKind() {
        return payKind;
    }

    public void setPayKind(Integer payKind) {
        this.payKind = payKind;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
}
