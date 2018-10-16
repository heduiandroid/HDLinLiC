package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/1/13.
 */

public class AdvertisementList {

    /**
     * status : 1
     * msg : 查询成功!
     * data : [{"id":314882052020695,"storeId":314879013261727,"name":"bigbang","type":"1","content":"我是广告美容","position":"314879066905649","count":0,"status":"1","createtime":1488205202000,"picurl":"http://obqlfysk2.bkt.clouddn.com/Fpv1Jogk9j2F9TvSf2KGJjNn2itK","remark":"发的广泛地","hdMallAdvertisementRule":{"id":314882052020847,"sendcount":1,"showcount":1,"sendstarttime":1485921600000,"sendendtime":1492747200000,"advertisementId":314882052020695,"createTime":1488205202000},"hdMallAdvertisementCost":{"id":314882052020846,"advertisementId":314882052020695,"createTime":1488205202000},"hdMallAdvertisementMember":{"id":314882052021008,"associatornumber1":"0","associatornumber2":"999999999999999","usernum1":0,"usernum2":999999999999999,"advertisementId":314882052020695,"createTime":1488205202000},"hdMallStore":{"id":314879013261727,"name":"万丰建材城","phone":"18310013577","companyMemberId":314879009775754,"bankCardNo":"123456789123456","openingBank":"中国邮政","accountHolder":"中枪","integral":0,"approveStatus":1,"categoryid":4,"createTime":1487901326000,"status":0,"openStatus":0,"opentime":-3600000,"closetime":54000000,"longitude":116.137424,"latitude":39.715714,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FqRdWHfhkt_-gyfVVkWoz-bE0dIZ","typeCount":190,"isgrab":2},"hdMallStoreUser":{"id":314879009775754,"password":"dedc1b0e543cc8cbebaa1c74182a94ef","phone":"18310013577","realname":"","salt":"4024832a8a9bf09df120a0bcaff2a1b2","status":"enable","username":"18310013577","storeId":314879013261727,"regionId":508,"createTime":1487900977000,"modifyTime":1487901326000,"credentialsSalt":"4024832a8a9bf09df120a0bcaff2a1b24024832a8a9bf09df120a0bcaff2a1b2"}},{"id":314881799408313,"storeId":314881598657034,"name":"来呀一起来","type":"1","content":"我是没事","position":"314879066905649","count":0,"status":"4","createtime":1488179940000,"picurl":"http://obqlfysk2.bkt.clouddn.com/FhxaGF4f4BcxuMhhnJ6lULaakhqf","remark":"信息","hdMallAdvertisementRule":{"id":314881799408625,"sendcount":1,"showcount":1,"sendstarttime":1486699200000,"sendendtime":1491624000000,"advertisementId":314881799408313,"createTime":1488179940000},"hdMallAdvertisementCost":{"id":314881799408464,"advertisementId":314881799408313,"createTime":1488179940000},"hdMallAdvertisementMember":{"id":314881799408626,"associatornumber1":"0","associatornumber2":"999999999999999","usernum1":0,"usernum2":999999999999999,"advertisementId":314881799408313,"createTime":1488179940000},"hdMallStore":{"id":314881598657034,"name":"测试商家2","phone":"18514283131","info":"我是店铺简介12345697976797997979979797649976767697979673734646949797346194952737694977676949497","companyMemberId":314881576453528,"bankCardNo":"1587967949767","openingBank":"建设银行","accountHolder":"被迫无奈","integral":0,"approveStatus":1,"categoryid":5,"createTime":1488159865000,"status":0,"openStatus":0,"opentime":1488159865000,"longitude":116.137424,"latitude":39.715714,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/ForI_0RhNbLIyB7gxXjpGVnG6WIf","typeCount":200,"isgrab":1},"hdMallStoreUser":{"id":314881576453528,"email":"1540855051@qq.com","password":"e9dcd87c8a23d579d6fe0e9265cccf51","phone":"18514283131","realname":"测试店铺联系人","salt":"5091f090d1a68ab8bc074e9716162757","status":"enable","username":"18514283131","storeId":314881598657034,"mobilePhone":"18633894413","qq":"369317126","regionId":508,"createTime":1488157645000,"modifyTime":1488159866000,"credentialsSalt":"5091f090d1a68ab8bc074e97161627575091f090d1a68ab8bc074e9716162757"}}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;
    private Page page;  //分页

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class Page{
        private int pageNum;
        private int totalPage;
        private int prePage;
        private int nextPage;
        private int totalCount;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
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
    public static class  DataBean {
        /**
         * id : 314882052020695
         * storeId : 314879013261727
         * name : bigbang
         * type : 1
         * content : 我是广告美容
         * position : 314879066905649
         * count : 0
         * status : 1
         * createtime : 1488205202000
         * picurl : http://obqlfysk2.bkt.clouddn.com/Fpv1Jogk9j2F9TvSf2KGJjNn2itK
         * remark : 发的广泛地
         * hdMallAdvertisementRule : {"id":314882052020847,"sendcount":1,"showcount":1,"sendstarttime":1485921600000,"sendendtime":1492747200000,"advertisementId":314882052020695,"createTime":1488205202000}
         * hdMallAdvertisementCost : {"id":314882052020846,"advertisementId":314882052020695,"createTime":1488205202000}
         * hdMallAdvertisementMember : {"id":314882052021008,"associatornumber1":"0","associatornumber2":"999999999999999","usernum1":0,"usernum2":999999999999999,"advertisementId":314882052020695,"createTime":1488205202000}
         * hdMallStore : {"id":314879013261727,"name":"万丰建材城","phone":"18310013577","companyMemberId":314879009775754,"bankCardNo":"123456789123456","openingBank":"中国邮政","accountHolder":"中枪","integral":0,"approveStatus":1,"categoryid":4,"createTime":1487901326000,"status":0,"openStatus":0,"opentime":-3600000,"closetime":54000000,"longitude":116.137424,"latitude":39.715714,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FqRdWHfhkt_-gyfVVkWoz-bE0dIZ","typeCount":190,"isgrab":2}
         * hdMallStoreUser : {"id":314879009775754,"password":"dedc1b0e543cc8cbebaa1c74182a94ef","phone":"18310013577","realname":"","salt":"4024832a8a9bf09df120a0bcaff2a1b2","status":"enable","username":"18310013577","storeId":314879013261727,"regionId":508,"createTime":1487900977000,"modifyTime":1487901326000,"credentialsSalt":"4024832a8a9bf09df120a0bcaff2a1b24024832a8a9bf09df120a0bcaff2a1b2"}
         */

        private long id;
        private long storeId;
        private String name;
        private String type;
        private String content;
        private String position;
        private int count;
        private String status;
        private long createtime;
        private String picurl;
        private String remark;
        private HdMallAdvertisementRuleBean hdMallAdvertisementRule;
        private HdMallAdvertisementCostBean hdMallAdvertisementCost;
        private HdMallAdvertisementMemberBean hdMallAdvertisementMember;
        private HdMallStoreBean hdMallStore;
        private HdMallStoreUserBean hdMallStoreUser;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public HdMallAdvertisementRuleBean getHdMallAdvertisementRule() {
            return hdMallAdvertisementRule;
        }

        public void setHdMallAdvertisementRule(HdMallAdvertisementRuleBean hdMallAdvertisementRule) {
            this.hdMallAdvertisementRule = hdMallAdvertisementRule;
        }

        public HdMallAdvertisementCostBean getHdMallAdvertisementCost() {
            return hdMallAdvertisementCost;
        }

        public void setHdMallAdvertisementCost(HdMallAdvertisementCostBean hdMallAdvertisementCost) {
            this.hdMallAdvertisementCost = hdMallAdvertisementCost;
        }

        public HdMallAdvertisementMemberBean getHdMallAdvertisementMember() {
            return hdMallAdvertisementMember;
        }

        public void setHdMallAdvertisementMember(HdMallAdvertisementMemberBean hdMallAdvertisementMember) {
            this.hdMallAdvertisementMember = hdMallAdvertisementMember;
        }

        public HdMallStoreBean getHdMallStore() {
            return hdMallStore;
        }

        public void setHdMallStore(HdMallStoreBean hdMallStore) {
            this.hdMallStore = hdMallStore;
        }

        public HdMallStoreUserBean getHdMallStoreUser() {
            return hdMallStoreUser;
        }

        public void setHdMallStoreUser(HdMallStoreUserBean hdMallStoreUser) {
            this.hdMallStoreUser = hdMallStoreUser;
        }

        public static class HdMallAdvertisementRuleBean {
            /**
             * id : 314882052020847
             * sendcount : 1
             * showcount : 1
             * sendstarttime : 1485921600000
             * sendendtime : 1492747200000
             * advertisementId : 314882052020695
             * createTime : 1488205202000
             */

            private long id;
            private int sendcount;
            private int showcount;
            private long sendstarttime;
            private long sendendtime;
            private long advertisementId;
            private long createTime;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getSendcount() {
                return sendcount;
            }

            public void setSendcount(int sendcount) {
                this.sendcount = sendcount;
            }

            public int getShowcount() {
                return showcount;
            }

            public void setShowcount(int showcount) {
                this.showcount = showcount;
            }

            public long getSendstarttime() {
                return sendstarttime;
            }

            public void setSendstarttime(long sendstarttime) {
                this.sendstarttime = sendstarttime;
            }

            public long getSendendtime() {
                return sendendtime;
            }

            public void setSendendtime(long sendendtime) {
                this.sendendtime = sendendtime;
            }

            public long getAdvertisementId() {
                return advertisementId;
            }

            public void setAdvertisementId(long advertisementId) {
                this.advertisementId = advertisementId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }

        public static class HdMallAdvertisementCostBean {
            /**
             * id : 314882052020846
             * advertisementId : 314882052020695
             * createTime : 1488205202000
             */

            private long id;
            private long advertisementId;
            private long createTime;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getAdvertisementId() {
                return advertisementId;
            }

            public void setAdvertisementId(long advertisementId) {
                this.advertisementId = advertisementId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }

        public static class HdMallAdvertisementMemberBean {
            /**
             * id : 314882052021008
             * associatornumber1 : 0
             * associatornumber2 : 999999999999999
             * usernum1 : 0
             * usernum2 : 999999999999999
             * advertisementId : 314882052020695
             * createTime : 1488205202000
             */

            private long id;
            private String associatornumber1;
            private String associatornumber2;
            private int usernum1;
            private long usernum2;
            private long advertisementId;
            private long createTime;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getAssociatornumber1() {
                return associatornumber1;
            }

            public void setAssociatornumber1(String associatornumber1) {
                this.associatornumber1 = associatornumber1;
            }

            public String getAssociatornumber2() {
                return associatornumber2;
            }

            public void setAssociatornumber2(String associatornumber2) {
                this.associatornumber2 = associatornumber2;
            }

            public int getUsernum1() {
                return usernum1;
            }

            public void setUsernum1(int usernum1) {
                this.usernum1 = usernum1;
            }

            public long getUsernum2() {
                return usernum2;
            }

            public void setUsernum2(long usernum2) {
                this.usernum2 = usernum2;
            }

            public long getAdvertisementId() {
                return advertisementId;
            }

            public void setAdvertisementId(long advertisementId) {
                this.advertisementId = advertisementId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }

        public static class HdMallStoreBean {
            /**
             * id : 314879013261727
             * name : 万丰建材城
             * phone : 18310013577
             * companyMemberId : 314879009775754
             * bankCardNo : 123456789123456
             * openingBank : 中国邮政
             * accountHolder : 中枪
             * integral : 0
             * approveStatus : 1
             * categoryid : 4
             * createTime : 1487901326000
             * status : 0
             * openStatus : 0
             * opentime : -3600000
             * closetime : 54000000
             * longitude : 116.137424
             * latitude : 39.715714
             * regionId : 508
             * logoImg : http://obqlfysk2.bkt.clouddn.com/FqRdWHfhkt_-gyfVVkWoz-bE0dIZ
             * typeCount : 190
             * isgrab : 2
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
            private Long categoryid;
            private Long createTime;
            private int status;
            private int openStatus;
            private Long opentime;
            private Long closetime;
            private Double longitude;
            private Double latitude;
            private int regionId;
            private String logoImg;
            private int typeCount;
            private int isgrab;

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
        }

        public static class HdMallStoreUserBean {
            /**
             * id : 314879009775754
             * password : dedc1b0e543cc8cbebaa1c74182a94ef
             * phone : 18310013577
             * realname :
             * salt : 4024832a8a9bf09df120a0bcaff2a1b2
             * status : enable
             * username : 18310013577
             * storeId : 314879013261727
             * regionId : 508
             * createTime : 1487900977000
             * modifyTime : 1487901326000
             * credentialsSalt : 4024832a8a9bf09df120a0bcaff2a1b24024832a8a9bf09df120a0bcaff2a1b2
             */

            private long id;
            private String password;
            private String phone;
            private String realname;
            private String salt;
            private String status;
            private String username;
            private long storeId;
            private int regionId;
            private long createTime;
            private long modifyTime;
            private String credentialsSalt;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public long getStoreId() {
                return storeId;
            }

            public void setStoreId(long storeId) {
                this.storeId = storeId;
            }

            public int getRegionId() {
                return regionId;
            }

            public void setRegionId(int regionId) {
                this.regionId = regionId;
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

            public String getCredentialsSalt() {
                return credentialsSalt;
            }

            public void setCredentialsSalt(String credentialsSalt) {
                this.credentialsSalt = credentialsSalt;
            }
        }
    }
}
