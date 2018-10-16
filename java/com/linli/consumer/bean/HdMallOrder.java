package com.linli.consumer.bean;

import java.math.BigDecimal;
import java.util.Date;


public class HdMallOrder {
    private Long id;

    private String orderSn;

    private String memberId;

    private Integer orderStatus;

    private Long paymentId;

    private BigDecimal goodsAmount;

    private BigDecimal discount;

    private BigDecimal orderAmount;

    private String orderPost;

    private Long storeId;

    private String province;

    private String city;

    private String district;

    private String address;

    private BigDecimal logisticsAmount;

    private String phone;

    private BigDecimal point;

    private Integer accountStatus;
    private Long addressId;
    private String purchaser;

    private String purchaserPhone;

    private BigDecimal favorableAmount;

    private String idCard;

    private Date feightTime;

    private Date createTime;

    private Date payTime;

    private Date finishedTime;

    private Integer status;

    private Integer numtotal;

    private Long distributionid;

    private String expresscode;

    private Long payOrderId;

    private Long couponId;

    private Date overtime;

    private Date lastModifyTime;

    private Integer isFicti;

    private String transactionId;

    private Boolean isAssign;

    private Long voiceId;

    private String areaCode;

    private Long transId;

    private int paytype;    //线上线下 1:线上    2:线下

    private int byway;       //自取    1:配送    2:自取
    
    private HdMallStore hdMallStore;
    
    private MallOrderGoods mallOrderGoods;
    
    
    private MallPayment mallPayment;
    
    private MallGoods mallGoods;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public int getByway() {
        return byway;
    }

    public void setByway(int byway) {
        this.byway = byway;
    }

    public HdMallStore getHdMallStore() {
		return hdMallStore;
	}

	public void setHdMallStore(HdMallStore hdMallStore) {
		this.hdMallStore = hdMallStore;
	}

	public MallOrderGoods getMallOrderGoods() {
		return mallOrderGoods;
	}

	public void setMallOrderGoods(MallOrderGoods mallOrderGoods) {
		this.mallOrderGoods = mallOrderGoods;
	}

	public MallPayment getMallPayment() {
		return mallPayment;
	}

	public void setMallPayment(MallPayment mallPayment) {
		this.mallPayment = mallPayment;
	}

	public MallGoods getMallGoods() {
		return mallGoods;
	}

	public void setMallGoods(MallGoods mallGoods) {
		this.mallGoods = mallGoods;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderPost() {
        return orderPost;
    }

    public void setOrderPost(String orderPost) {
        this.orderPost = orderPost == null ? null : orderPost.trim();
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getLogisticsAmount() {
        return logisticsAmount;
    }

    public void setLogisticsAmount(BigDecimal logisticsAmount) {
        this.logisticsAmount = logisticsAmount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser == null ? null : purchaser.trim();
    }

    public String getPurchaserPhone() {
        return purchaserPhone;
    }

    public void setPurchaserPhone(String purchaserPhone) {
        this.purchaserPhone = purchaserPhone == null ? null : purchaserPhone.trim();
    }

    public BigDecimal getFavorableAmount() {
        return favorableAmount;
    }

    public void setFavorableAmount(BigDecimal favorableAmount) {
        this.favorableAmount = favorableAmount;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Date getFeightTime() {
        return feightTime;
    }

    public void setFeightTime(Date feightTime) {
        this.feightTime = feightTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumtotal() {
        return numtotal;
    }

    public void setNumtotal(Integer numtotal) {
        this.numtotal = numtotal;
    }

    public Long getDistributionid() {
        return distributionid;
    }

    public void setDistributionid(Long distributionid) {
        this.distributionid = distributionid;
    }

    public String getExpresscode() {
        return expresscode;
    }

    public void setExpresscode(String expresscode) {
        this.expresscode = expresscode == null ? null : expresscode.trim();
    }

    public Long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(Long payOrderId) {
        this.payOrderId = payOrderId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getIsFicti() {
        return isFicti;
    }

    public void setIsFicti(Integer isFicti) {
        this.isFicti = isFicti;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public Boolean getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(Boolean isAssign) {
        this.isAssign = isAssign;
    }

    public Long getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(Long voiceId) {
        this.voiceId = voiceId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }
}