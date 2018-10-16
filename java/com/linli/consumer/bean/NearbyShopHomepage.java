package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2016/12/26.
 */

public class NearbyShopHomepage {

    /**
     * status : 1
     * msg : null
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":10,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":0}
     * data : [{"id":2,"name":"小月伴弯1","nickname":"咖啡店","domain":"www.xiaoyuebanwan.com","wechat":"18633894413","phone":"18633894413","notice":"情侣入店8折优惠","info":"回忆过去，珍惜现在，携手未来","idCardNo":"130682199101177071","companyMemberId":123,"bankCardNo":"6227000131050516911","openingBank":"建设银行","accountHolder":"党伟","viewCount":520,"isStoreNotice":0,"integral":666,"approveStatus":1,"type":1,"categoryid":1,"createTime":1480819124000,"modifyTime":1480905541000,"status":0,"openStatus":0,"opentime":1480905574000,"closetime":1480912798000,"longitude":116.1374243,"latitude":39.7157143,"regionId":1,"logoImg":"http://pic.sogou.com/d?query=%BF%A7%B7%C8%CC%FC&mood=0&picformat=0&mode=1&di=0&did=1#did7","backImg":"http://pic.sogou.com/d?query=%BF%A7%B7%C8%CC%FC&mood=0&picformat=0&mode=1&di=0&did=1#did0","address":"北京市房山区白杨西路5号","goodsDescribe":5,"logisticsDescribe":5,"serviceAttitude":5,"commentCount":26,"isallday":1,"typeCount":13,"isgrab":2,"categoryType":2,"grab":1,"paytype":null,"distributiontype":null}]
     * url : null
     */

    private int status;
    private Object msg;
    private PageBean page;
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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
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

    public static class PageBean {
        /**
         * plainPageNum : 1
         * pageNum : 1
         * numPerPage : 10
         * orderField :
         * orderDirection :
         * totalPage : 1
         * prePage : 1
         * nextPage : 1
         * totalCount : 0
         */

        private int plainPageNum;
        private int pageNum;
        private int numPerPage;
        private String orderField;
        private String orderDirection;
        private int totalPage;
        private int prePage;
        private int nextPage;
        private int totalCount;

        public int getPlainPageNum() {
            return plainPageNum;
        }

        public void setPlainPageNum(int plainPageNum) {
            this.plainPageNum = plainPageNum;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getNumPerPage() {
            return numPerPage;
        }

        public void setNumPerPage(int numPerPage) {
            this.numPerPage = numPerPage;
        }

        public String getOrderField() {
            return orderField;
        }

        public void setOrderField(String orderField) {
            this.orderField = orderField;
        }

        public String getOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(String orderDirection) {
            this.orderDirection = orderDirection;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class DataBean {
        /**
         * id : 2
         * name : 小月伴弯1
         * nickname : 咖啡店
         * domain : www.xiaoyuebanwan.com
         * wechat : 18633894413
         * phone : 18633894413
         * notice : 情侣入店8折优惠
         * info : 回忆过去，珍惜现在，携手未来
         * idCardNo : 130682199101177071
         * companyMemberId : 123
         * bankCardNo : 6227000131050516911
         * openingBank : 建设银行
         * accountHolder : 党伟
         * viewCount : 520
         * isStoreNotice : 0
         * integral : 666
         * approveStatus : 1
         * type : 1
         * categoryid : 1
         * createTime : 1480819124000
         * modifyTime : 1480905541000
         * status : 0
         * openStatus : 0
         * opentime : 1480905574000
         * closetime : 1480912798000
         * longitude : 116.1374243
         * latitude : 39.7157143
         * regionId : 1
         * logoImg : http://pic.sogou.com/d?query=%BF%A7%B7%C8%CC%FC&mood=0&picformat=0&mode=1&di=0&did=1#did7
         * backImg : http://pic.sogou.com/d?query=%BF%A7%B7%C8%CC%FC&mood=0&picformat=0&mode=1&di=0&did=1#did0
         * address : 北京市房山区白杨西路5号
         * goodsDescribe : 5
         * logisticsDescribe : 5
         * serviceAttitude : 5
         * commentCount : 26
         * isallday : 1
         * typeCount : 13
         * isgrab : 2
         * categoryType : 2
         * grab : 1
         * paytype : null
         * distributiontype : null
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
        private long createTime;
        private long modifyTime;
        private Integer status;
        private Integer openStatus;
        private long opentime;
        private long closetime;
        private Double longitude;
        private Double latitude;
        private Integer regionId;
        private String logoImg;
        private String backImg;
        private String address;
        private Integer goodsDescribe;
        private Integer logisticsDescribe;
        private Integer serviceAttitude;
        private Integer commentCount;
        private Integer isallday;
        private Integer typeCount;
        private Integer isgrab;
        private Integer categoryType;
        private Integer grab;
        private Integer paytype;
        private Integer distributiontype;
        private Integer creditLevel;
        private Integer isactivity; //是否开启活动

        public Integer getIsactivity() {
            return isactivity;
        }

        public void setIsactivity(Integer isactivity) {
            this.isactivity = isactivity;
        }

        public Integer getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(Integer creditLevel) {
            this.creditLevel = creditLevel;
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

        public long getOpentime() {
            return opentime;
        }

        public void setOpentime(long opentime) {
            this.opentime = opentime;
        }

        public long getClosetime() {
            return closetime;
        }

        public void setClosetime(long closetime) {
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

        public Integer getRegionId() {
            return regionId;
        }

        public void setRegionId(Integer regionId) {
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

        public Integer getIsallday() {
            return isallday;
        }

        public void setIsallday(Integer isallday) {
            this.isallday = isallday;
        }

        public Integer getTypeCount() {
            return typeCount;
        }

        public void setTypeCount(Integer typeCount) {
            this.typeCount = typeCount;
        }

        public Integer getIsgrab() {
            return isgrab;
        }

        public void setIsgrab(Integer isgrab) {
            this.isgrab = isgrab;
        }

        public Integer getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(Integer categoryType) {
            this.categoryType = categoryType;
        }

        public Integer getGrab() {
            return grab;
        }

        public void setGrab(Integer grab) {
            this.grab = grab;
        }

        public Integer getPaytype() {
            return paytype;
        }

        public void setPaytype(Integer paytype) {
            this.paytype = paytype;
        }

        public Integer getDistributiontype() {
            return distributiontype;
        }

        public void setDistributiontype(Integer distributiontype) {
            this.distributiontype = distributiontype;
        }
    }
}
