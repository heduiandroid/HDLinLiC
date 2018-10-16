package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/1/16.
 */

public class MallShopInfo {

    /**
     * status : 1
     * msg : 查询成功！
     * page : null
     * data : {"id":214845319687421,"name":"张勇测试店铺","nickname":null,"domain":null,"wechat":null,"phone":"18701346478","notice":null,"info":null,"idCardNo":null,"companyMemberId":214845311848104,"bankCardNo":"","openingBank":"","accountHolder":"张勇","paypassword":null,"viewCount":null,"isStoreNotice":null,"integral":0,"approveStatus":1,"type":null,"categoryid":1,"createTime":1484531968000,"modifyTime":null,"status":0,"openStatus":0,"opentime":1484531968000,"closetime":null,"longitude":116.137424,"latitude":39.715714,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/214845315647039_logo.jpg","backImg":null,"address":null,"creditLevel":null,"goodsDescribe":null,"logisticsDescribe":null,"serviceAttitude":null,"commentCount":null,"typeCount":200,"isgrab":2,"categoryType":1,"keyword":null,"grab":1,"paytype":null,"distributiontype":null,"qrcode":"http://obqlfysk2.bkt.clouddn.com/214845319687421flag_1484531969297.png","wifiid":null}
     * url : null
     */

    private int status;
    private String msg;
    private Object page;
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
        /**
         * id : 214845319687421
         * name : 张勇测试店铺
         * nickname : null
         * domain : null
         * wechat : null
         * phone : 18701346478
         * notice : null
         * info : null
         * idCardNo : null
         * companyMemberId : 214845311848104
         * bankCardNo :
         * openingBank :
         * accountHolder : 张勇
         * paypassword : null
         * viewCount : null
         * isStoreNotice : null
         * integral : 0
         * approveStatus : 1
         * type : null
         * categoryid : 1
         * createTime : 1484531968000
         * modifyTime : null
         * status : 0
         * openStatus : 0
         * opentime : 1484531968000
         * closetime : null
         * longitude : 116.137424
         * latitude : 39.715714
         * regionId : 1
         * logoImg : http://obqlfysk2.bkt.clouddn.com/214845315647039_logo.jpg
         * backImg : null
         * address : null
         * creditLevel : null
         * goodsDescribe : null
         * logisticsDescribe : null
         * serviceAttitude : null
         * commentCount : null
         * typeCount : 200
         * isgrab : 2
         * categoryType : 1
         * keyword : null
         * grab : 1
         * paytype : null
         * distributiontype : null
         * qrcode : http://obqlfysk2.bkt.clouddn.com/214845319687421flag_1484531969297.png
         * wifiid : null
         */

        private long id;
        private String name;
        private Object nickname;
        private Object domain;
        private Object wechat;
        private String phone;
        private String notice;
        private Object info;
        private Object idCardNo;
        private long companyMemberId;
        private String bankCardNo;
        private String openingBank;
        private String accountHolder;
        private Object paypassword;
        private Object viewCount;
        private Object isStoreNotice;
        private int integral;
        private int approveStatus;
        private Integer type;
        private Long categoryid;
        private long createTime;
        private Object modifyTime;
        private int status;
        private int openStatus;
        private long opentime;
        private Object closetime;
        private double longitude;
        private double latitude;
        private Long regionId;
        private String logoImg;
        private Object backImg;
        private Object address;
        private Integer creditLevel;
        private Object goodsDescribe;
        private Object logisticsDescribe;
        private Object serviceAttitude;
        private Object commentCount;
        private int typeCount;
        private int isgrab;
        private int categoryType;
        private Object keyword;
        private int grab;
        private Object paytype;
        private Object distributiontype;
        private String qrcode;
        private Object wifiid;
        private String mobilePhone;

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getDomain() {
            return domain;
        }

        public void setDomain(Object domain) {
            this.domain = domain;
        }

        public Object getWechat() {
            return wechat;
        }

        public void setWechat(Object wechat) {
            this.wechat = wechat;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }

        public Object getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(Object idCardNo) {
            this.idCardNo = idCardNo;
        }

        public long getCompanyMemberId() {
            return companyMemberId;
        }

        public void setCompanyMemberId(long companyMemberId) {
            this.companyMemberId = companyMemberId;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getOpeningBank() {
            return openingBank;
        }

        public void setOpeningBank(String openingBank) {
            this.openingBank = openingBank;
        }

        public String getAccountHolder() {
            return accountHolder;
        }

        public void setAccountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
        }

        public Object getPaypassword() {
            return paypassword;
        }

        public void setPaypassword(Object paypassword) {
            this.paypassword = paypassword;
        }

        public Object getViewCount() {
            return viewCount;
        }

        public void setViewCount(Object viewCount) {
            this.viewCount = viewCount;
        }

        public Object getIsStoreNotice() {
            return isStoreNotice;
        }

        public void setIsStoreNotice(Object isStoreNotice) {
            this.isStoreNotice = isStoreNotice;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getApproveStatus() {
            return approveStatus;
        }

        public void setApproveStatus(int approveStatus) {
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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOpenStatus() {
            return openStatus;
        }

        public void setOpenStatus(int openStatus) {
            this.openStatus = openStatus;
        }

        public long getOpentime() {
            return opentime;
        }

        public void setOpentime(long opentime) {
            this.opentime = opentime;
        }

        public Object getClosetime() {
            return closetime;
        }

        public void setClosetime(Object closetime) {
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
            this.logoImg = logoImg;
        }

        public Object getBackImg() {
            return backImg;
        }

        public void setBackImg(Object backImg) {
            this.backImg = backImg;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Integer getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(Integer creditLevel) {
            this.creditLevel = creditLevel;
        }

        public Object getGoodsDescribe() {
            return goodsDescribe;
        }

        public void setGoodsDescribe(Object goodsDescribe) {
            this.goodsDescribe = goodsDescribe;
        }

        public Object getLogisticsDescribe() {
            return logisticsDescribe;
        }

        public void setLogisticsDescribe(Object logisticsDescribe) {
            this.logisticsDescribe = logisticsDescribe;
        }

        public Object getServiceAttitude() {
            return serviceAttitude;
        }

        public void setServiceAttitude(Object serviceAttitude) {
            this.serviceAttitude = serviceAttitude;
        }

        public Object getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Object commentCount) {
            this.commentCount = commentCount;
        }

        public int getTypeCount() {
            return typeCount;
        }

        public void setTypeCount(int typeCount) {
            this.typeCount = typeCount;
        }

        public int getIsgrab() {
            return isgrab;
        }

        public void setIsgrab(int isgrab) {
            this.isgrab = isgrab;
        }

        public int getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(int categoryType) {
            this.categoryType = categoryType;
        }

        public Object getKeyword() {
            return keyword;
        }

        public void setKeyword(Object keyword) {
            this.keyword = keyword;
        }

        public int getGrab() {
            return grab;
        }

        public void setGrab(int grab) {
            this.grab = grab;
        }

        public Object getPaytype() {
            return paytype;
        }

        public void setPaytype(Object paytype) {
            this.paytype = paytype;
        }

        public Object getDistributiontype() {
            return distributiontype;
        }

        public void setDistributiontype(Object distributiontype) {
            this.distributiontype = distributiontype;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public Object getWifiid() {
            return wifiid;
        }

        public void setWifiid(Object wifiid) {
            this.wifiid = wifiid;
        }
    }
}
