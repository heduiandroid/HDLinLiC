package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/1/19.
 */

public class ConcernedList {

    /**
     * status : 1
     * msg : 查询商家会员list成功
     * page : null
     * data : [{"id":414847256035112,"storeId":414847256035112,"memberId":314845394456941,"agencyid":null,"memberNumber":1484539445648,"name":"哈哈","phone":"18235910362","sex":2,"level":0,"points":0,"headpath":"http://obqlfysk2.bkt.clouddn.com/FuxDArZW8Vr6iy2bARsbnPUCdr_R","birthday":"","note":"","professional":"","address":"别急","qq":"","email":"","height":0,"weight":0,"bloodtype":null,"smoking":"","drinking":"","marriage":"","createTime":1484539446000,"modifyTime":null,"deleteTime":null,"status":0,"labelId":0,"region_id":null,"phoneCode":null,"hdFoodStore":{"id":414847256035112,"name":null,"nickname":null,"domain":null,"wechat":null,"phone":null,"notice":null,"paypassword":null,"info":null,"idCardNo":null,"companyMemberId":null,"bankCardNo":null,"openingBank":null,"accountHolder":null,"viewCount":null,"isStoreNotice":null,"integral":null,"approveStatus":null,"type":null,"categoryid":null,"createTime":null,"modifyTime":null,"status":null,"openStatus":null,"opentime":null,"closetime":null,"longitude":null,"latitude":null,"regionId":null,"logoImg":null,"backImg":null,"address":null,"goodsDescribe":null,"logisticsDescribe":null,"serviceAttitude":null,"commentCount":null,"isallday":null,"typeCount":null,"isgrab":null,"categoryType":null,"keyword":null,"grab":null,"paytype":null,"distributiontype":null,"qrcode":null,"wifiid":null,"creditLevel":null},"hdMallStore":{"id":414847256035112,"name":"中煤印务","nickname":null,"domain":null,"wechat":null,"phone":"18801071735","notice":null,"info":null,"idCardNo":null,"companyMemberId":314847253244742,"bankCardNo":"1234569","openingBank":"中国银行","accountHolder":"张厂长","paypassword":null,"viewCount":null,"isStoreNotice":null,"integral":0,"approveStatus":1,"type":null,"categoryid":1,"createTime":1484725605000,"modifyTime":null,"status":0,"openStatus":0,"opentime":1484725607000,"closetime":null,"longitude":116.151338,"latitude":39.716685,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FmeXUvNbQ7z___ljrZURfgPG3blx","backImg":null,"address":null,"creditLevel":null,"goodsDescribe":null,"logisticsDescribe":null,"serviceAttitude":null,"commentCount":null,"typeCount":200,"isgrab":2,"categoryType":1,"keyword":null,"grab":1,"paytype":null,"distributiontype":null,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414847256035112flag_1484725610894.png","wifiid":null}}]
     * url : null
     */

    private int status;
    private String msg;
    private Object page;
    private Object url;
    private List<DataBean> data;

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
         * id : 414847256035112
         * storeId : 414847256035112
         * memberId : 314845394456941
         * agencyid : null
         * memberNumber : 1484539445648
         * name : 哈哈
         * phone : 18235910362
         * sex : 2
         * level : 0
         * points : 0
         * headpath : http://obqlfysk2.bkt.clouddn.com/FuxDArZW8Vr6iy2bARsbnPUCdr_R
         * birthday :
         * note :
         * professional :
         * address : 别急
         * qq :
         * email :
         * height : 0
         * weight : 0
         * bloodtype : null
         * smoking :
         * drinking :
         * marriage :
         * createTime : 1484539446000
         * modifyTime : null
         * deleteTime : null
         * status : 0
         * labelId : 0
         * region_id : null
         * phoneCode : null
         * hdFoodStore : {"id":414847256035112,"name":null,"nickname":null,"domain":null,"wechat":null,"phone":null,"notice":null,"paypassword":null,"info":null,"idCardNo":null,"companyMemberId":null,"bankCardNo":null,"openingBank":null,"accountHolder":null,"viewCount":null,"isStoreNotice":null,"integral":null,"approveStatus":null,"type":null,"categoryid":null,"createTime":null,"modifyTime":null,"status":null,"openStatus":null,"opentime":null,"closetime":null,"longitude":null,"latitude":null,"regionId":null,"logoImg":null,"backImg":null,"address":null,"goodsDescribe":null,"logisticsDescribe":null,"serviceAttitude":null,"commentCount":null,"isallday":null,"typeCount":null,"isgrab":null,"categoryType":null,"keyword":null,"grab":null,"paytype":null,"distributiontype":null,"qrcode":null,"wifiid":null,"creditLevel":null}
         * hdMallStore : {"id":414847256035112,"name":"中煤印务","nickname":null,"domain":null,"wechat":null,"phone":"18801071735","notice":null,"info":null,"idCardNo":null,"companyMemberId":314847253244742,"bankCardNo":"1234569","openingBank":"中国银行","accountHolder":"张厂长","paypassword":null,"viewCount":null,"isStoreNotice":null,"integral":0,"approveStatus":1,"type":null,"categoryid":1,"createTime":1484725605000,"modifyTime":null,"status":0,"openStatus":0,"opentime":1484725607000,"closetime":null,"longitude":116.151338,"latitude":39.716685,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FmeXUvNbQ7z___ljrZURfgPG3blx","backImg":null,"address":null,"creditLevel":null,"goodsDescribe":null,"logisticsDescribe":null,"serviceAttitude":null,"commentCount":null,"typeCount":200,"isgrab":2,"categoryType":1,"keyword":null,"grab":1,"paytype":null,"distributiontype":null,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414847256035112flag_1484725610894.png","wifiid":null}
         */

        private Long id;
        private Long storeId;
        private Long memberId;
        private Long agencyid;
        private String memberNumber;
        private String name;
        private String phone;
        private Integer sex;
        private Integer level;
        private Integer points;
        private String headpath;
        private String birthday;
        private String note;
        private String professional;
        private String address;
        private String qq;
        private String email;
        private Integer height;
        private Integer weight;
        private String bloodtype;
        private String smoking;
        private String drinking;
        private String marriage;
        private Long createTime;
        private Long modifyTime;
        private Long deleteTime;
        private int status;
        private Long labelId;
        private Long region_id;
        private HdFoodStoreBean hdFoodStore;
        private HdMallStoreBean hdMallStore;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getStoreId() {
            return storeId;
        }

        public void setStoreId(Long storeId) {
            this.storeId = storeId;
        }

        public Long getMemberId() {
            return memberId;
        }

        public void setMemberId(Long memberId) {
            this.memberId = memberId;
        }

        public Long getAgencyid() {
            return agencyid;
        }

        public void setAgencyid(Long agencyid) {
            this.agencyid = agencyid;
        }

        public String getMemberNumber() {
            return memberNumber;
        }

        public void setMemberNumber(String memberNumber) {
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

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Integer getPoints() {
            return points;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }

        public String getHeadpath() {
            return headpath;
        }

        public void setHeadpath(String headpath) {
            this.headpath = headpath;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getProfessional() {
            return professional;
        }

        public void setProfessional(String professional) {
            this.professional = professional;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getBloodtype() {
            return bloodtype;
        }

        public void setBloodtype(String bloodtype) {
            this.bloodtype = bloodtype;
        }

        public String getSmoking() {
            return smoking;
        }

        public void setSmoking(String smoking) {
            this.smoking = smoking;
        }

        public String getDrinking() {
            return drinking;
        }

        public void setDrinking(String drinking) {
            this.drinking = drinking;
        }

        public String getMarriage() {
            return marriage;
        }

        public void setMarriage(String marriage) {
            this.marriage = marriage;
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

        public Long getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(Long deleteTime) {
            this.deleteTime = deleteTime;
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

        public Long getRegion_id() {
            return region_id;
        }

        public void setRegion_id(Long region_id) {
            this.region_id = region_id;
        }

        public HdFoodStoreBean getHdFoodStore() {
            return hdFoodStore;
        }

        public void setHdFoodStore(HdFoodStoreBean hdFoodStore) {
            this.hdFoodStore = hdFoodStore;
        }

        public HdMallStoreBean getHdMallStore() {
            return hdMallStore;
        }

        public void setHdMallStore(HdMallStoreBean hdMallStore) {
            this.hdMallStore = hdMallStore;
        }

        public static class HdFoodStoreBean {
            /**
             * id : 414847256035112
             * name : 中煤印务
             * nickname : null
             * domain : null
             * wechat : null
             * phone : 18801071735
             * notice : null
             * info : null
             * idCardNo : null
             * companyMemberId : 314847253244742
             * bankCardNo : 1234569
             * openingBank : 中国银行
             * accountHolder : 张厂长
             * paypassword : null
             * viewCount : null
             * isStoreNotice : null
             * integral : 0
             * approveStatus : 1
             * type : null
             * categoryid : 1
             * createTime : 1484725605000
             * modifyTime : null
             * status : 0
             * openStatus : 0
             * opentime : 1484725607000
             * closetime : null
             * longitude : 116.151338
             * latitude : 39.716685
             * regionId : 1
             * logoImg : http://obqlfysk2.bkt.clouddn.com/FmeXUvNbQ7z___ljrZURfgPG3blx
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
             * qrcode : http://obqlfysk2.bkt.clouddn.com/414847256035112flag_1484725610894.png
             * wifiid : null
             */

            private Long id;
            private String name;
            private String nickname;
            private String phone;
            private long companyMemberId;
            private int status;
            private int openStatus;
            private double longitude;
            private double latitude;
            private String logoImg;
            private String backImg;
            private String address;
            private Integer creditLevel;
            private int categoryType;
            private String keyword;
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

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }
        }

        public static class HdMallStoreBean {
            /**
             * id : 414847256035112
             * name : 中煤印务
             * nickname : null
             * domain : null
             * wechat : null
             * phone : 18801071735
             * notice : null
             * info : null
             * idCardNo : null
             * companyMemberId : 314847253244742
             * bankCardNo : 1234569
             * openingBank : 中国银行
             * accountHolder : 张厂长
             * paypassword : null
             * viewCount : null
             * isStoreNotice : null
             * integral : 0
             * approveStatus : 1
             * type : null
             * categoryid : 1
             * createTime : 1484725605000
             * modifyTime : null
             * status : 0
             * openStatus : 0
             * opentime : 1484725607000
             * closetime : null
             * longitude : 116.151338
             * latitude : 39.716685
             * regionId : 1
             * logoImg : http://obqlfysk2.bkt.clouddn.com/FmeXUvNbQ7z___ljrZURfgPG3blx
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
             * qrcode : http://obqlfysk2.bkt.clouddn.com/414847256035112flag_1484725610894.png
             * wifiid : null
             */

            private Long id;
            private String name;
            private String nickname;
            private String phone;
            private long companyMemberId;
            private int status;
            private int openStatus;
            private double longitude;
            private double latitude;
            private String logoImg;
            private String backImg;
            private String address;
            private Integer creditLevel;
            private int categoryType;
            private String keyword;
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

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }
        }
    }
}
