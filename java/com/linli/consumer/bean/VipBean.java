package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/2/13.
 */

public class VipBean extends ResVo {

    /**
     * id : 314868116792781
     * storeId : 1
     * memberId : 314846245310909
     * memberNumber : 1486811679092
     * name : wgwwgwg
     * phone : 18202997411
     * points : 0
     * address : 未填写
     * createTime : 1486811679000
     * status : 1
     * labelId : 0
     * hdFoodStore : {"id":314868116792781,"name":"小月伴弯1","nickname":"咖啡店","domain":"www.xiaoyuebanwan.com","wechat":"18633894411","phone":"18633894411","notice":"情侣入店8折优惠","info":"回忆过去，珍惜现在，携手未来","idCardNo":"130682199101177071","companyMemberId":214642704784402,"bankCardNo":"6227000131050516911","openingBank":"建设银行","accountHolder":"党伟","viewCount":520,"isStoreNotice":0,"integral":666,"approveStatus":1,"type":1,"categoryid":1,"createTime":1480819124000,"modifyTime":1480905541000,"status":0,"openStatus":0,"opentime":1480905574000,"closetime":1480912798000,"longitude":116.1374243,"latitude":39.7157143,"regionId":1,"logoImg":"http://c.hiphotos.baidu.com/image/pic/item/f7246b600c3387448982f948540fd9f9d72aa0bb.jpg","backImg":"http://file.uniitown.com/uniitown/uploads/2016/09/30/214752209352454_thum.jpg","address":"北京市房山区白杨西路5号","goodsDescribe":5,"logisticsDescribe":5,"serviceAttitude":5,"commentCount":26,"isallday":1,"typeCount":13,"isgrab":2,"categoryType":2,"keyword":"123456","grab":1,"paytype":2,"distributiontype":2}
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private long id;
        private long storeId;
        private long memberId;
        private long memberNumber;
        private String name;
        private String phone;
        private long points;
        private String address;
        private long createTime;
        private int status;
        private Long labelId;
        /**
         * id : 314868116792781
         * name : 小月伴弯1
         * nickname : 咖啡店
         * domain : www.xiaoyuebanwan.com
         * wechat : 18633894411
         * phone : 18633894411
         * notice : 情侣入店8折优惠
         * info : 回忆过去，珍惜现在，携手未来
         * idCardNo : 130682199101177071
         * companyMemberId : 214642704784402
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
         * logoImg : http://c.hiphotos.baidu.com/image/pic/item/f7246b600c3387448982f948540fd9f9d72aa0bb.jpg
         * backImg : http://file.uniitown.com/uniitown/uploads/2016/09/30/214752209352454_thum.jpg
         * address : 北京市房山区白杨西路5号
         * goodsDescribe : 5
         * logisticsDescribe : 5
         * serviceAttitude : 5
         * commentCount : 26
         * isallday : 1
         * typeCount : 13
         * isgrab : 2
         * categoryType : 2
         * keyword : 123456
         * grab : 1
         * paytype : 2
         * distributiontype : 2
         */

        private HdFoodStoreBean hdFoodStore;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getStoreId() {
            return storeId;
        }

        public void setStoreId(long storeId) {
            this.storeId = storeId;
        }

        public long getMemberId() {
            return memberId;
        }

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }

        public long getMemberNumber() {
            return memberNumber;
        }

        public void setMemberNumber(long memberNumber) {
            this.memberNumber = memberNumber;
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

        public long getPoints() {
            return points;
        }

        public void setPoints(long points) {
            this.points = points;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public Long getLabelId() {
            return labelId;
        }

        public void setLabelId(Long labelId) {
            this.labelId = labelId;
        }

        public HdFoodStoreBean getHdFoodStore() {
            return hdFoodStore;
        }

        public void setHdFoodStore(HdFoodStoreBean hdFoodStore) {
            this.hdFoodStore = hdFoodStore;
        }

        public static class HdFoodStoreBean {
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
            private int categoryid;
            private long createTime;
            private long modifyTime;
            private int status;
            private int openStatus;
            private long opentime;
            private long closetime;
            private double longitude;
            private double latitude;
            private int regionId;
            private String logoImg;
            private String backImg;
            private String address;
            private int goodsDescribe;
            private int logisticsDescribe;
            private int serviceAttitude;
            private int commentCount;
            private int isallday;
            private int typeCount;
            private int isgrab;
            private int categoryType;
            private String keyword;
            private int grab;
            private int paytype;
            private int distributiontype;

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

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
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

            public int getIsallday() {
                return isallday;
            }

            public void setIsallday(int isallday) {
                this.isallday = isallday;
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
        }
    }
}
