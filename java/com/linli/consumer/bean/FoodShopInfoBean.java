package com.linli.consumer.bean;

import com.linli.consumer.common.HandleSuccess;

/**
 * Created by tomoyo on 2017/1/18.
 * {@link com.linli.consumer.net.FoodNet#foodShopInfo(long, HandleSuccess)}
 */

public class FoodShopInfoBean {

    /**
     * status : 1
     * msg : 查询成功！
     * page : null
     * data : {"id":414847215788752,"name":"泰日天","nickname":null,"domain":null,"wechat":null,"phone":"18303222636","notice":null,"info":"12","idCardNo":null,"companyMemberId":314842930477115,"bankCardNo":"123456789123","openingBank":"中国银行","accountHolder":"中","viewCount":null,"isStoreNotice":null,"integral":0,"approveStatus":1,"type":null,"categoryid":null,"createTime":1484721581000,"modifyTime":1484728603000,"status":0,"openStatus":0,"opentime":23940000,"closetime":-21300000,"longitude":115.862836,"latitude":39.726753,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FnUZQS4uDokepOu-NjFHrqaZUuKm","backImg":null,"address":"12","goodsDescribe":null,"logisticsDescribe":null,"serviceAttitude":null,"commentCount":null,"isallday":1,"typeCount":null,"isgrab":2,"categoryType":2,"keyword":null,"grab":1,"paytype":null,"distributiontype":null,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414847215788752flag_1484721584735.png","wifiid":null,"creditLevel":null}
     * url : null
     */

    private int status;
    private String msg;
    private Object page;
    /**
     * id : 414847215788752
     * name : 泰日天
     * nickname : null
     * domain : null
     * wechat : null
     * phone : 18303222636
     * notice : null
     * info : 12
     * idCardNo : null
     * companyMemberId : 314842930477115
     * bankCardNo : 123456789123
     * openingBank : 中国银行
     * accountHolder : 中
     * viewCount : null
     * isStoreNotice : null
     * integral : 0
     * approveStatus : 1
     * type : null
     * categoryid : null
     * createTime : 1484721581000
     * modifyTime : 1484728603000
     * status : 0
     * openStatus : 0
     * opentime : 23940000
     * closetime : -21300000
     * longitude : 115.862836
     * latitude : 39.726753
     * regionId : 1
     * logoImg : http://obqlfysk2.bkt.clouddn.com/FnUZQS4uDokepOu-NjFHrqaZUuKm
     * backImg : null
     * address : 12
     * goodsDescribe : null
     * logisticsDescribe : null
     * serviceAttitude : null
     * commentCount : null
     * isallday : 1
     * typeCount : null
     * isgrab : 2
     * categoryType : 2
     * keyword : null
     * grab : 1
     * paytype : null
     * distributiontype : null
     * qrcode : http://obqlfysk2.bkt.clouddn.com/414847215788752flag_1484721584735.png
     * wifiid : null
     * creditLevel : null
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
        private String name;
        private String nickname;
        private String domain;
        private String wechat;
        private String phone;
        private String notice;
        private String info;
        private String idCardNo;
        private long companyMemberId;
        private String bankCardNo;
        private String openingBank;
        private String accountHolder;
        private int viewCount;
        private int isStoreNotice;
        private int integral;
        private int approveStatus;
        private int type;
        private long categoryid;
        private long createTime;
        private long modifyTime;
        private int status;
        private int openStatus;
        private double opentime;
        private double closetime;
        private double longitude;
        private double latitude;
        private long regionId;
        private String logoImg;
        private String backImg;
        private String address;
        private double goodsDescribe;
        private int logisticsDescribe;
        private int serviceAttitude;
        private int commentCount;
        private int isallday;
        private Object typeCount;
        private int isgrab;
        private long categoryType;
        private String keyword;
        private int grab;
        private int paytype;
        private long distributiontype;
        private String qrcode;
        private String wifiid;
        private int creditLevel;
        private String mobilePhone;

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        /**
         * 广告轮播图片
         * */

        private String backImg1;

        private String backImg2;

        private String backImg3;

        private String backImg4;

        public String getBackImg1() {
            return backImg1;
        }

        public void setBackImg1(String backImg1) {
            this.backImg1 = backImg1;
        }

        public String getBackImg2() {
            return backImg2;
        }

        public void setBackImg2(String backImg2) {
            this.backImg2 = backImg2;
        }

        public String getBackImg3() {
            return backImg3;
        }

        public void setBackImg3(String backImg3) {
            this.backImg3 = backImg3;
        }

        public String getBackImg4() {
            return backImg4;
        }

        public void setBackImg4(String backImg4) {
            this.backImg4 = backImg4;
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

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
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

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
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

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getIsStoreNotice() {
            return isStoreNotice;
        }

        public void setIsStoreNotice(int isStoreNotice) {
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(long categoryid) {
            this.categoryid = categoryid;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
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

        public double getOpentime() {
            return opentime;
        }

        public void setOpentime(double opentime) {
            this.opentime = opentime;
        }

        public double getClosetime() {
            return closetime;
        }

        public void setClosetime(double closetime) {
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

        public long getRegionId() {
            return regionId;
        }

        public void setRegionId(long regionId) {
            this.regionId = regionId;
        }

        public String getLogoImg() {
            return logoImg;
        }

        public void setLogoImg(String logoImg) {
            this.logoImg = logoImg;
        }

        public String getBackImg() {
            return backImg;
        }

        public void setBackImg(String backImg) {
            this.backImg = backImg;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getGoodsDescribe() {
            return goodsDescribe;
        }

        public void setGoodsDescribe(double goodsDescribe) {
            this.goodsDescribe = goodsDescribe;
        }

        public int getLogisticsDescribe() {
            return logisticsDescribe;
        }

        public void setLogisticsDescribe(int logisticsDescribe) {
            this.logisticsDescribe = logisticsDescribe;
        }

        public int getServiceAttitude() {
            return serviceAttitude;
        }

        public void setServiceAttitude(int serviceAttitude) {
            this.serviceAttitude = serviceAttitude;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getIsallday() {
            return isallday;
        }

        public void setIsallday(int isallday) {
            this.isallday = isallday;
        }

        public Object getTypeCount() {
            return typeCount;
        }

        public void setTypeCount(Object typeCount) {
            this.typeCount = typeCount;
        }

        public int getIsgrab() {
            return isgrab;
        }

        public void setIsgrab(int isgrab) {
            this.isgrab = isgrab;
        }

        public long getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(long categoryType) {
            this.categoryType = categoryType;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getGrab() {
            return grab;
        }

        public void setGrab(int grab) {
            this.grab = grab;
        }

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }

        public long getDistributiontype() {
            return distributiontype;
        }

        public void setDistributiontype(long distributiontype) {
            this.distributiontype = distributiontype;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getWifiid() {
            return wifiid;
        }

        public void setWifiid(String wifiid) {
            this.wifiid = wifiid;
        }

        public int getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(int creditLevel) {
            this.creditLevel = creditLevel;
        }
    }
}
