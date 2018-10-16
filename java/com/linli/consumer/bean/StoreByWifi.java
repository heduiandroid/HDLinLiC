package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/1/14.
 */

public class StoreByWifi {

    /**
     * status : 1
     * data : {"hdMallStore":{"id":114888549601929,"name":"呵呵商场","nickname":"刘哈哈","phone":"15931286776","companyMemberId":314888533826256,"bankCardNo":"20552351254212","openingBank":"央行","accountHolder":"刘哈哈","integral":0,"approveStatus":1,"categoryid":14828949725032,"createTime":1488854960000,"status":0,"openStatus":0,"opentime":-21240000,"closetime":39600000,"longitude":116.137424,"latitude":39.715714,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FsHOQssjtRg3jwoaEO5xVhPDwXuD","address":"北京北京房山区良乡镇白杨西路5号","typeCount":200,"isgrab":2,"categoryType":1,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/114888549601929flag_1488854961355.png","wifiid":"00:1d:e6:25:53:e1","isactivity":1},"hdFoodStore":{"id":314892153142187,"name":"航行美食","phone":"15233468263","companyMemberId":314892144623293,"bankCardNo":"6210210091900715279","openingBank":"河北农村信用社","accountHolder":"赵玉航","integral":0,"approveStatus":1,"createTime":1489215314000,"status":0,"openStatus":0,"opentime":1489215314000,"longitude":116.339674,"latitude":39.728336,"regionId":515,"logoImg":"http://obqlfysk2.bkt.clouddn.com/Fl1gSQZRzE5zodzWCY6uJVQzoroc","address":"北京北京大兴区北京市大兴区林校北路","isgrab":2,"categoryType":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/314892153142187flag_1489215314227.png","wifiid":"00:1d:e6:25:53:e1","isactivity":0},"type":0,"pageNum":0,"numPerPage":0,"grab":0,"isgrab":0,"openStatus":0,"praiseNum":0,"storeUserNum":0}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * hdMallStore : {"id":114888549601929,"name":"呵呵商场","nickname":"刘哈哈","phone":"15931286776","companyMemberId":314888533826256,"bankCardNo":"20552351254212","openingBank":"央行","accountHolder":"刘哈哈","integral":0,"approveStatus":1,"categoryid":14828949725032,"createTime":1488854960000,"status":0,"openStatus":0,"opentime":-21240000,"closetime":39600000,"longitude":116.137424,"latitude":39.715714,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FsHOQssjtRg3jwoaEO5xVhPDwXuD","address":"北京北京房山区良乡镇白杨西路5号","typeCount":200,"isgrab":2,"categoryType":1,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/114888549601929flag_1488854961355.png","wifiid":"00:1d:e6:25:53:e1","isactivity":1}
         * hdFoodStore : {"id":314892153142187,"name":"航行美食","phone":"15233468263","companyMemberId":314892144623293,"bankCardNo":"6210210091900715279","openingBank":"河北农村信用社","accountHolder":"赵玉航","integral":0,"approveStatus":1,"createTime":1489215314000,"status":0,"openStatus":0,"opentime":1489215314000,"longitude":116.339674,"latitude":39.728336,"regionId":515,"logoImg":"http://obqlfysk2.bkt.clouddn.com/Fl1gSQZRzE5zodzWCY6uJVQzoroc","address":"北京北京大兴区北京市大兴区林校北路","isgrab":2,"categoryType":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/314892153142187flag_1489215314227.png","wifiid":"00:1d:e6:25:53:e1","isactivity":0}
         * type : 0
         * pageNum : 0
         * numPerPage : 0
         * grab : 0
         * isgrab : 0
         * openStatus : 0
         * praiseNum : 0
         * storeUserNum : 0
         */

        private HdMallStoreBean hdMallStore;
        private HdFoodStoreBean hdFoodStore;
        private int type;
        private int pageNum;
        private int numPerPage;
        private int grab;
        private int isgrab;
        private int openStatus;
        private int praiseNum;
        private int storeUserNum;

        public HdMallStoreBean getHdMallStore() {
            return hdMallStore;
        }

        public void setHdMallStore(HdMallStoreBean hdMallStore) {
            this.hdMallStore = hdMallStore;
        }

        public HdFoodStoreBean getHdFoodStore() {
            return hdFoodStore;
        }

        public void setHdFoodStore(HdFoodStoreBean hdFoodStore) {
            this.hdFoodStore = hdFoodStore;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getGrab() {
            return grab;
        }

        public void setGrab(int grab) {
            this.grab = grab;
        }

        public int getIsgrab() {
            return isgrab;
        }

        public void setIsgrab(int isgrab) {
            this.isgrab = isgrab;
        }

        public int getOpenStatus() {
            return openStatus;
        }

        public void setOpenStatus(int openStatus) {
            this.openStatus = openStatus;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getStoreUserNum() {
            return storeUserNum;
        }

        public void setStoreUserNum(int storeUserNum) {
            this.storeUserNum = storeUserNum;
        }

        public static class HdMallStoreBean {
            /**
             * id : 114888549601929
             * name : 呵呵商场
             * nickname : 刘哈哈
             * phone : 15931286776
             * companyMemberId : 314888533826256
             * bankCardNo : 20552351254212
             * openingBank : 央行
             * accountHolder : 刘哈哈
             * integral : 0
             * approveStatus : 1
             * categoryid : 14828949725032
             * createTime : 1488854960000
             * status : 0
             * openStatus : 0
             * opentime : -21240000
             * closetime : 39600000
             * longitude : 116.137424
             * latitude : 39.715714
             * regionId : 508
             * logoImg : http://obqlfysk2.bkt.clouddn.com/FsHOQssjtRg3jwoaEO5xVhPDwXuD
             * address : 北京北京房山区良乡镇白杨西路5号
             * typeCount : 200
             * isgrab : 2
             * categoryType : 1
             * grab : 1
             * qrcode : http://obqlfysk2.bkt.clouddn.com/114888549601929flag_1488854961355.png
             * wifiid : 00:1d:e6:25:53:e1
             * isactivity : 1
             */

            private long id;
            private String name;
            private String nickname;
            private String phone;
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
            private Long opentime;
            private Long closetime;
            private double longitude;
            private double latitude;
            private long regionId;
            private String logoImg;
            private String address;
            private int typeCount;
            private int isgrab;
            private int categoryType;
            private int grab;
            private String qrcode;
            private String wifiid;
            private Integer isactivity;
            private String notice;
            private Integer creditLevel;
            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public Integer getCreditLevel() {
                return creditLevel;
            }

            public void setCreditLevel(Integer creditLevel) {
                this.creditLevel = creditLevel;
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getWifiid() {
                return wifiid;
            }

            public void setWifiid(String wifiid) {
                this.wifiid = wifiid;
            }

            public Integer getIsactivity() {
                return isactivity;
            }

            public void setIsactivity(Integer isactivity) {
                this.isactivity = isactivity;
            }
        }

        public static class HdFoodStoreBean {
            /**
             * id : 314892153142187
             * name : 航行美食
             * phone : 15233468263
             * companyMemberId : 314892144623293
             * bankCardNo : 6210210091900715279
             * openingBank : 河北农村信用社
             * accountHolder : 赵玉航
             * integral : 0
             * approveStatus : 1
             * createTime : 1489215314000
             * status : 0
             * openStatus : 0
             * opentime : 1489215314000
             * longitude : 116.339674
             * latitude : 39.728336
             * regionId : 515
             * logoImg : http://obqlfysk2.bkt.clouddn.com/Fl1gSQZRzE5zodzWCY6uJVQzoroc
             * address : 北京北京大兴区北京市大兴区林校北路
             * isgrab : 2
             * categoryType : 2
             * grab : 1
             * qrcode : http://obqlfysk2.bkt.clouddn.com/314892153142187flag_1489215314227.png
             * wifiid : 00:1d:e6:25:53:e1
             * isactivity : 0
             */

            private long id;
            private String name;
            private String phone;
            private long companyMemberId;
            private String bankCardNo;
            private String openingBank;
            private String accountHolder;
            private int integral;
            private int approveStatus;
            private long createTime;
            private int status;
            private int openStatus;
            private long opentime;
            private double longitude;
            private double latitude;
            private long regionId;
            private String logoImg;
            private String address;
            private int isgrab;
            private int categoryType;
            private int grab;
            private String qrcode;
            private String wifiid;
            private Integer isactivity;
            private String notice;
            private Integer creditLevel;

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public Integer getCreditLevel() {
                return creditLevel;
            }

            public void setCreditLevel(Integer creditLevel) {
                this.creditLevel = creditLevel;
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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getWifiid() {
                return wifiid;
            }

            public void setWifiid(String wifiid) {
                this.wifiid = wifiid;
            }

            public Integer getIsactivity() {
                return isactivity;
            }

            public void setIsactivity(Integer isactivity) {
                this.isactivity = isactivity;
            }
        }
    }
}
