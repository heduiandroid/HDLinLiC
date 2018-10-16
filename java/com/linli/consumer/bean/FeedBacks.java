package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2016/12/19.
 */

public class FeedBacks {

    /**
     * status : 1
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":10,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":0}
     * data : [{"hdPubVoice":{"id":314873137844187,"path":"http://obqlfysk2.bkt.clouddn.com/大包间","name":"大包间","memId":314847024078253,"type":2,"createTime":1487313784000},"hdMallStoreList":[{"id":414846522510124,"name":"晚安","phone":"18310013577","notice":"222","companyMemberId":314846417690942,"bankCardNo":"112222222222","openingBank":"中国银行","accountHolder":"庄园多","integral":0,"approveStatus":1,"categoryid":14828949725032,"createTime":1484652253000,"status":0,"openStatus":0,"opentime":1484652254000,"longitude":116.137424,"latitude":39.715714,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FgtCrcwF3n6GDRFFb0yRAf2EnTwi","address":"北京房山","creditLevel":1,"typeCount":200,"isgrab":2,"categoryType":1,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414846522510124flag_1484652257242.png"}],"hdFoodStoreList":[]},{"hdPubVoice":{"id":314873137467521,"path":"http://obqlfysk2.bkt.clouddn.com/要割小鸡鸡","name":"要割小鸡鸡","memId":314847024078253,"type":2,"createTime":1487313746000},"hdMallStoreList":[{"id":414846522510124,"name":"晚安","phone":"18310013577","notice":"222","companyMemberId":314846417690942,"bankCardNo":"112222222222","openingBank":"中国银行","accountHolder":"庄园多","integral":0,"approveStatus":1,"categoryid":14828949725032,"createTime":1484652253000,"status":0,"openStatus":0,"opentime":1484652254000,"longitude":116.137424,"latitude":39.715714,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FgtCrcwF3n6GDRFFb0yRAf2EnTwi","address":"北京房山","creditLevel":1,"typeCount":200,"isgrab":2,"categoryType":1,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414846522510124flag_1484652257242.png"},{"id":414846522510124,"name":"晚安","phone":"18310013577","notice":"222","companyMemberId":314846417690942,"bankCardNo":"112222222222","openingBank":"中国银行","accountHolder":"庄园多","integral":0,"approveStatus":1,"categoryid":14828949725032,"createTime":1484652253000,"status":0,"openStatus":0,"opentime":1484652254000,"longitude":116.137424,"latitude":39.715714,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FgtCrcwF3n6GDRFFb0yRAf2EnTwi","address":"北京房山","creditLevel":1,"typeCount":200,"isgrab":2,"categoryType":1,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414846522510124flag_1484652257242.png"},{"id":414846522510124,"name":"晚安","phone":"18310013577","notice":"222","companyMemberId":314846417690942,"bankCardNo":"112222222222","openingBank":"中国银行","accountHolder":"庄园多","integral":0,"approveStatus":1,"categoryid":14828949725032,"createTime":1484652253000,"status":0,"openStatus":0,"opentime":1484652254000,"longitude":116.137424,"latitude":39.715714,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FgtCrcwF3n6GDRFFb0yRAf2EnTwi","address":"北京房山","creditLevel":1,"typeCount":200,"isgrab":2,"categoryType":1,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414846522510124flag_1484652257242.png"}],"hdFoodStoreList":[]}]
     */

    private int status;
    private PageBean page;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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
         * hdPubVoice : {"id":314873137844187,"path":"http://obqlfysk2.bkt.clouddn.com/大包间","name":"大包间","memId":314847024078253,"type":2,"createTime":1487313784000}
         * hdMallStoreList : [{"id":414846522510124,"name":"晚安","phone":"18310013577","notice":"222","companyMemberId":314846417690942,"bankCardNo":"112222222222","openingBank":"中国银行","accountHolder":"庄园多","integral":0,"approveStatus":1,"categoryid":14828949725032,"createTime":1484652253000,"status":0,"openStatus":0,"opentime":1484652254000,"longitude":116.137424,"latitude":39.715714,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FgtCrcwF3n6GDRFFb0yRAf2EnTwi","address":"北京房山","creditLevel":1,"typeCount":200,"isgrab":2,"categoryType":1,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414846522510124flag_1484652257242.png"}]
         * hdFoodStoreList : []
         */

        private HdPubVoiceBean hdPubVoice;
        private List<HdMallStoreListBean> hdMallStoreList;
        private List<HdFoodStoreListBean> hdFoodStoreList;

        public HdPubVoiceBean getHdPubVoice() {
            return hdPubVoice;
        }

        public void setHdPubVoice(HdPubVoiceBean hdPubVoice) {
            this.hdPubVoice = hdPubVoice;
        }

        public List<HdMallStoreListBean> getHdMallStoreList() {
            return hdMallStoreList;
        }

        public void setHdMallStoreList(List<HdMallStoreListBean> hdMallStoreList) {
            this.hdMallStoreList = hdMallStoreList;
        }

        public List<HdFoodStoreListBean> getHdFoodStoreList() {
            return hdFoodStoreList;
        }

        public void setHdFoodStoreList(List<HdFoodStoreListBean> hdFoodStoreList) {
            this.hdFoodStoreList = hdFoodStoreList;
        }

        public static class HdPubVoiceBean {
            /**
             * id : 314873137844187
             * path : http://obqlfysk2.bkt.clouddn.com/大包间
             * name : 大包间
             * memId : 314847024078253
             * type : 2
             * createTime : 1487313784000
             */

            private long id;
            private String path;
            private String name;
            private long memId;
            private int type;
            private long createTime;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getMemId() {
                return memId;
            }

            public void setMemId(long memId) {
                this.memId = memId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }

        public static class HdMallStoreListBean {
            /**
             * id : 414846522510124
             * name : 晚安
             * phone : 18310013577
             * notice : 222
             * companyMemberId : 314846417690942
             * bankCardNo : 112222222222
             * openingBank : 中国银行
             * accountHolder : 庄园多
             * integral : 0
             * approveStatus : 1
             * categoryid : 14828949725032
             * createTime : 1484652253000
             * status : 0
             * openStatus : 0
             * opentime : 1484652254000
             * longitude : 116.137424
             * latitude : 39.715714
             * regionId : 1
             * logoImg : http://obqlfysk2.bkt.clouddn.com/FgtCrcwF3n6GDRFFb0yRAf2EnTwi
             * address : 北京房山
             * creditLevel : 1
             * typeCount : 200
             * isgrab : 2
             * categoryType : 1
             * grab : 1
             * qrcode : http://obqlfysk2.bkt.clouddn.com/414846522510124flag_1484652257242.png
             */

            private long id;
            private String name;
            private String phone;
            private String notice;
            private long companyMemberId;
            private String bankCardNo;
            private String openingBank;
            private String accountHolder;
            private int integral;
            private int approveStatus;
            private long categoryid;
            private long createTime;
            private int status;
            private int openStatus;
            private long opentime;
            private Double longitude;
            private Double latitude;
            private Long regionId;
            private String logoImg;
            private String address;
            private int creditLevel;
            private int typeCount;
            private int isgrab;
            private int categoryType;
            private int grab;
            private String qrcode;

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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCreditLevel() {
                return creditLevel;
            }

            public void setCreditLevel(int creditLevel) {
                this.creditLevel = creditLevel;
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

            public int getGrab() {
                return grab;
            }

            public void setGrab(int grab) {
                this.grab = grab;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }
        }
        public static class HdFoodStoreListBean {
            /**
             * id : 414846522510124
             * name : 晚安
             * phone : 18310013577
             * notice : 222
             * companyMemberId : 314846417690942
             * bankCardNo : 112222222222
             * openingBank : 中国银行
             * accountHolder : 庄园多
             * integral : 0
             * approveStatus : 1
             * categoryid : 14828949725032
             * createTime : 1484652253000
             * status : 0
             * openStatus : 0
             * opentime : 1484652254000
             * longitude : 116.137424
             * latitude : 39.715714
             * regionId : 1
             * logoImg : http://obqlfysk2.bkt.clouddn.com/FgtCrcwF3n6GDRFFb0yRAf2EnTwi
             * address : 北京房山
             * creditLevel : 1
             * typeCount : 200
             * isgrab : 2
             * categoryType : 1
             * grab : 1
             * qrcode : http://obqlfysk2.bkt.clouddn.com/414846522510124flag_1484652257242.png
             */

            private long id;
            private String name;
            private String phone;
            private String notice;
            private long companyMemberId;
            private String bankCardNo;
            private String openingBank;
            private String accountHolder;
            private int integral;
            private int approveStatus;
            private long categoryid;
            private long createTime;
            private int openStatus;
            private double longitude;
            private double latitude;
            private Long regionId;
            private String logoImg;
            private String address;
            private int creditLevel;
            private int typeCount;
            private int categoryType;
            private String qrcode;

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

            public int getOpenStatus() {
                return openStatus;
            }

            public void setOpenStatus(int openStatus) {
                this.openStatus = openStatus;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCreditLevel() {
                return creditLevel;
            }

            public void setCreditLevel(int creditLevel) {
                this.creditLevel = creditLevel;
            }

            public int getTypeCount() {
                return typeCount;
            }

            public void setTypeCount(int typeCount) {
                this.typeCount = typeCount;
            }

            public int getCategoryType() {
                return categoryType;
            }

            public void setCategoryType(int categoryType) {
                this.categoryType = categoryType;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }
        }
    }
}
