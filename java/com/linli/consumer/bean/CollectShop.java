package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/1/9.
 */

public class CollectShop {

    /**
     * status : 1
     * msg : null
     * page : null
     * data : [{"id":1,"name":"爱来不来","nickname":"阿萨德撒","domain":"0","wechat":"sds","phone":"32132123132","notice":"asdasd","info":"成人用品","idCardNo":"2316523156321561","companyMemberId":314798791180651,"bankCardNo":"320531531351","openingBank":"中国银行","accountHolder":"1","paypassword":"0","viewCount":0,"isStoreNotice":0,"integral":1,"approveStatus":1,"type":1,"categoryid":1,"createTime":1482388657000,"modifyTime":1482388660000,"status":1,"openStatus":0,"opentime":1482388665000,"closetime":null,"longitude":null,"latitude":null,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FuxDArZW8Vr6iy2bARsbnPUCdr_R","backImg":"http://obqlfysk2.bkt.clouddn.com/.12 - 副本 (2).jpg","address":"北京房山","creditLevel":1,"goodsDescribe":1,"logisticsDescribe":1,"serviceAttitude":1,"commentCount":1,"typeCount":1111,"isgrab":1,"categoryType":1,"keyword":"1","grab":1,"paytype":1,"distributiontype":1,"qrcode":null}]
     * url : null
     */

    private int status;
    private Object msg;
    private Object page;
    private Object url;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 爱来不来
         * nickname : 阿萨德撒
         * domain : 0
         * wechat : sds
         * phone : 32132123132
         * notice : asdasd
         * info : 成人用品
         * idCardNo : 2316523156321561
         * companyMemberId : 314798791180651
         * bankCardNo : 320531531351
         * openingBank : 中国银行
         * accountHolder : 1
         * paypassword : 0
         * viewCount : 0
         * isStoreNotice : 0
         * integral : 1
         * approveStatus : 1
         * type : 1
         * categoryid : 1
         * createTime : 1482388657000
         * modifyTime : 1482388660000
         * status : 1
         * openStatus : 0
         * opentime : 1482388665000
         * closetime : null
         * longitude : null
         * latitude : null
         * regionId : 1
         * logoImg : http://obqlfysk2.bkt.clouddn.com/FuxDArZW8Vr6iy2bARsbnPUCdr_R
         * backImg : http://obqlfysk2.bkt.clouddn.com/.12 - 副本 (2).jpg
         * address : 北京房山
         * creditLevel : 1
         * goodsDescribe : 1
         * logisticsDescribe : 1
         * serviceAttitude : 1
         * commentCount : 1
         * typeCount : 1111
         * isgrab : 1
         * categoryType : 1
         * keyword : 1
         * grab : 1
         * paytype : 1
         * distributiontype : 1
         * qrcode : null
         */

        private Long id;
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
        private String paypassword;
        private int viewCount;
        private int isStoreNotice;
        private int integral;
        private int approveStatus;
        private int type;
        private Long categoryid;
        private Long createTime;
        private Long modifyTime;
        private int status;
        private int openStatus;
        private Long opentime;
        private Long closetime;
        private Double longitude;
        private Double latitude;
        private int regionId;
        private String logoImg;
        private String backImg;
        private String address;
        private Integer creditLevel;
        private int goodsDescribe;
        private int logisticsDescribe;
        private int serviceAttitude;
        private int commentCount;
        private int typeCount;
        private int isgrab;
        private Integer categoryType;
        private String keyword;
        private int grab;
        private int paytype;
        private int distributiontype;
        private String qrcode;

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
            this.name = name;
        }

        public String getNickname() {
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

        public String getPaypassword() {
            return paypassword;
        }

        public void setPaypassword(String paypassword) {
            this.paypassword = paypassword;
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

        public Long getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(Long categoryid) {
            this.categoryid = categoryid;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Long modifyTime) {
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

        public Long getOpentime() {
            return opentime;
        }

        public void setOpentime(Long opentime) {
            this.opentime = opentime;
        }

        public Long getClosetime() {
            return closetime;
        }

        public void setClosetime(Long closetime) {
            this.closetime = closetime;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
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

        public Integer getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(Integer creditLevel) {
            this.creditLevel = creditLevel;
        }

        public int getGoodsDescribe() {
            return goodsDescribe;
        }

        public void setGoodsDescribe(int goodsDescribe) {
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

        public Integer getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(Integer categoryType) {
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

        public int getDistributiontype() {
            return distributiontype;
        }

        public void setDistributiontype(int distributiontype) {
            this.distributiontype = distributiontype;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }
    }
}
