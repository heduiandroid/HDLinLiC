package com.linli.consumer.bean;

import java.util.Date;

public class HdMallStore {
    private Long id;

    private String name;

    private String nickname;

    private String domain;

    private String wechat;

    private String phone;

    private String notice;

    private String info;

    private String idCardNo;

    private Long companyMemberId;

    private String bankCardNo;

    private String openingBank;

    private String accountHolder;

    private Integer viewCount;

    private Integer isStoreNotice;

    private Integer integral;

    private Integer approveStatus;

    private Integer type;

    private Long categoryid;

    private Date createTime;

    private Date modifyTime;

    private Integer status;

    private Integer openStatus;

    private Date opentime;

    private Date closetime;

    private double longitude;

    private double latitude;

    private Long regionId;

    private String logoImg;

    private String backImg;

    private String address;

    private Integer creditLevel;

    private Integer goodsDescribe;

    private Integer logisticsDescribe;

    private Integer serviceAttitude;

    private Integer commentCount;

    private Integer typeCount;

    private Integer categoryType;

    private Integer isgrab;
    
    private Integer grab;
    
    private Long paytype;//支付方式 0,1,2
    private Long distributiontype;//配送方式 0,1,2
    
    private String keyword;//商家关键词

    private String distance;        //距离
    private Integer isactivity;   //活动是否开启

    public Integer getIsactivity() {
        return isactivity;
    }

    public void setIsactivity(Integer isactivity) {
        this.isactivity = isactivity;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Long getPaytype() {
		return paytype;
	}

	public void setPaytype(Long paytype) {
		this.paytype = paytype;
	}

	public Long getDistributiontype() {
		return distributiontype;
	}

	public void setDistributiontype(Long distributiontype) {
		this.distributiontype = distributiontype;
	}

	public Integer getGrab() {
		return grab;
	}

	public void setGrab(Integer grab) {
		this.grab = grab;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    public Long getCompanyMemberId() {
        return companyMemberId;
    }

    public void setCompanyMemberId(Long companyMemberId) {
        this.companyMemberId = companyMemberId;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank == null ? null : openingBank.trim();
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder == null ? null : accountHolder.trim();
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getIsStoreNotice() {
        return isStoreNotice;
    }

    public void setIsStoreNotice(Integer isStoreNotice) {
        this.isStoreNotice = isStoreNotice;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(Integer openStatus) {
        this.openStatus = openStatus;
    }

    public Date getOpentime() {
        return opentime;
    }

    public void setOpentime(Date opentime) {
        this.opentime = opentime;
    }

    public Date getClosetime() {
        return closetime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg == null ? null : logoImg.trim();
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg == null ? null : backImg.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(Integer creditLevel) {
        this.creditLevel = creditLevel;
    }

    public Integer getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(Integer goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public Integer getLogisticsDescribe() {
        return logisticsDescribe;
    }

    public void setLogisticsDescribe(Integer logisticsDescribe) {
        this.logisticsDescribe = logisticsDescribe;
    }

    public Integer getServiceAttitude() {
        return serviceAttitude;
    }

    public void setServiceAttitude(Integer serviceAttitude) {
        this.serviceAttitude = serviceAttitude;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(Integer typeCount) {
        this.typeCount = typeCount;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getIsgrab() {
        return isgrab;
    }

    public void setIsgrab(Integer isgrab) {
        this.isgrab = isgrab;
    }

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}