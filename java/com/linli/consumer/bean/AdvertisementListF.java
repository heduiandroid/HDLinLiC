package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/3/15.
 */

public class AdvertisementListF {

    /**
     * status : 1
     * msg : 查询成功!
     * data : [{"id":314895583124884,"storeId":314888731062212,"name":"哈哈哈哈","type":"1","content":"今日首发互刷是你说你那","position":"314888498323136","count":0,"status":"5","createtime":1489558498000,"picurl":"http://obqlfysk2.bkt.clouddn.com/FgFH6LTrJ7esQq7-1mF6Hd7Ni9Jw","areaId":1078,"remark":"你是一个小丫小苹果","hdFoodAdvertisementRule":{"id":314895583125036,"sendcount":1,"showcount":1,"sendstarttime":1489550400000,"sendendtime":1490932800000,"advertisementId":314895583124884,"createTime":1489558312000},"hdFoodAdvertisementCost":{"id":314895583124965,"advertisementId":314895583124884,"money":154,"createTime":1489558312000},"hdFoodAdvertisementMember":{"id":314895583125137,"associatornumber1":"0","associatornumber2":"999999999999999","usernum1":0,"usernum2":999999999999999,"advertisementId":314895583124884,"createTime":1489558312000},"hdFoodStore":{"id":314888731062212,"name":"长安饭店","phone":"15911013808","notice":"就是666","info":"欢迎欢迎","companyMemberId":314888729219408,"bankCardNo":"632258896775699","openingBank":"建设银行","accountHolder":"你敢不敢","integral":0,"approveStatus":1,"createTime":1488873106000,"modifyTime":1489151321000,"status":0,"openStatus":0,"opentime":50400000,"closetime":54000000,"longitude":114.523824,"latitude":38.058016,"regionId":1078,"logoImg":"http://obqlfysk2.bkt.clouddn.com/logo314888731062212","address":"河北石家庄长安区青园街5号","isallday":0,"isgrab":2,"categoryType":2,"grab":1}},{"id":314889675024525,"storeId":314888731062212,"name":"商讯23","type":"1","content":"123456我是商讯","position":"314888498323136","count":0,"status":"5","createtime":1489152529000,"picurl":"http://obqlfysk2.bkt.clouddn.com/8.png","areaId":1078,"remark":"123456","hdFoodAdvertisementRule":{"id":314889675057877,"sendcount":1,"showcount":1,"sendstarttime":1488902400000,"sendendtime":1490943715000,"advertisementId":314889675024525,"createTime":1488967502000},"hdFoodAdvertisementCost":{"id":314889675057776,"advertisementId":314889675024525,"money":154,"createTime":1488967502000},"hdFoodAdvertisementMember":{"id":314889675058028,"associatornumber1":"0","associatornumber2":"999999999999999","usernum1":0,"usernum2":999999999999999,"advertisementId":314889675024525,"createTime":1488967502000},"hdFoodStore":{"id":314888731062212,"name":"长安饭店","phone":"15911013808","notice":"就是666","info":"欢迎欢迎","companyMemberId":314888729219408,"bankCardNo":"632258896775699","openingBank":"建设银行","accountHolder":"你敢不敢","integral":0,"approveStatus":1,"createTime":1488873106000,"modifyTime":1489151321000,"status":0,"openStatus":0,"opentime":50400000,"closetime":54000000,"longitude":114.523824,"latitude":38.058016,"regionId":1078,"logoImg":"http://obqlfysk2.bkt.clouddn.com/logo314888731062212","address":"河北石家庄长安区青园街5号","isallday":0,"isgrab":2,"categoryType":2,"grab":1}},{"id":114891522520502,"storeId":314888731062212,"name":"广告1","type":"1","content":"额外若无群二","position":"314888498323136","count":0,"status":"2","createtime":1489152515000,"picurl":"http://obqlfysk2.bkt.clouddn.com/3.png","areaId":1078,"remark":"请描述你的广告,比如精准投放广告的位置、方式、投放时间、人群特点、消费潜能、地域范围等。\r\n                            二","hdFoodAdvertisementRule":{"id":114891522534154,"sendcount":1,"showcount":1,"sendstarttime":1488384000000,"sendendtime":1490943715000,"sendstarthour":"额外若群","sendendhour":"认为群若群","advertisementId":114891522520502,"createTime":1489152252000},"hdFoodAdvertisementCost":{"id":114891522534153,"advertisementId":114891522520502,"money":154,"createTime":1489152252000},"hdFoodAdvertisementMember":{"id":114891522534325,"associatornumber1":"3246546533546","associatornumber2":"245234523452345","usernum1":0,"usernum2":999999999999999,"advertisementId":114891522520502,"createTime":1489152252000},"hdFoodStore":{"id":314888731062212,"name":"长安饭店","phone":"15911013808","notice":"就是666","info":"欢迎欢迎","companyMemberId":314888729219408,"bankCardNo":"632258896775699","openingBank":"建设银行","accountHolder":"你敢不敢","integral":0,"approveStatus":1,"createTime":1488873106000,"modifyTime":1489151321000,"status":0,"openStatus":0,"opentime":50400000,"closetime":54000000,"longitude":114.523824,"latitude":38.058016,"regionId":1078,"logoImg":"http://obqlfysk2.bkt.clouddn.com/logo314888731062212","address":"河北石家庄长安区青园街5号","isallday":0,"isgrab":2,"categoryType":2,"grab":1}}]
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
    public static class DataBean {
        /**
         * id : 314895583124884
         * storeId : 314888731062212
         * name : 哈哈哈哈
         * type : 1
         * content : 今日首发互刷是你说你那
         * position : 314888498323136
         * count : 0
         * status : 5
         * createtime : 1489558498000
         * picurl : http://obqlfysk2.bkt.clouddn.com/FgFH6LTrJ7esQq7-1mF6Hd7Ni9Jw
         * areaId : 1078
         * remark : 你是一个小丫小苹果
         * hdFoodAdvertisementRule : {"id":314895583125036,"sendcount":1,"showcount":1,"sendstarttime":1489550400000,"sendendtime":1490932800000,"advertisementId":314895583124884,"createTime":1489558312000}
         * hdFoodAdvertisementCost : {"id":314895583124965,"advertisementId":314895583124884,"money":154,"createTime":1489558312000}
         * hdFoodAdvertisementMember : {"id":314895583125137,"associatornumber1":"0","associatornumber2":"999999999999999","usernum1":0,"usernum2":999999999999999,"advertisementId":314895583124884,"createTime":1489558312000}
         * hdFoodStore : {"id":314888731062212,"name":"长安饭店","phone":"15911013808","notice":"就是666","info":"欢迎欢迎","companyMemberId":314888729219408,"bankCardNo":"632258896775699","openingBank":"建设银行","accountHolder":"你敢不敢","integral":0,"approveStatus":1,"createTime":1488873106000,"modifyTime":1489151321000,"status":0,"openStatus":0,"opentime":50400000,"closetime":54000000,"longitude":114.523824,"latitude":38.058016,"regionId":1078,"logoImg":"http://obqlfysk2.bkt.clouddn.com/logo314888731062212","address":"河北石家庄长安区青园街5号","isallday":0,"isgrab":2,"categoryType":2,"grab":1}
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
        private int areaId;
        private String remark;
        private HdFoodAdvertisementRuleBean hdFoodAdvertisementRule;
        private HdFoodAdvertisementCostBean hdFoodAdvertisementCost;
        private HdFoodAdvertisementMemberBean hdFoodAdvertisementMember;
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

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public HdFoodAdvertisementRuleBean getHdFoodAdvertisementRule() {
            return hdFoodAdvertisementRule;
        }

        public void setHdFoodAdvertisementRule(HdFoodAdvertisementRuleBean hdFoodAdvertisementRule) {
            this.hdFoodAdvertisementRule = hdFoodAdvertisementRule;
        }

        public HdFoodAdvertisementCostBean getHdFoodAdvertisementCost() {
            return hdFoodAdvertisementCost;
        }

        public void setHdFoodAdvertisementCost(HdFoodAdvertisementCostBean hdFoodAdvertisementCost) {
            this.hdFoodAdvertisementCost = hdFoodAdvertisementCost;
        }

        public HdFoodAdvertisementMemberBean getHdFoodAdvertisementMember() {
            return hdFoodAdvertisementMember;
        }

        public void setHdFoodAdvertisementMember(HdFoodAdvertisementMemberBean hdFoodAdvertisementMember) {
            this.hdFoodAdvertisementMember = hdFoodAdvertisementMember;
        }

        public HdFoodStoreBean getHdFoodStore() {
            return hdFoodStore;
        }

        public void setHdFoodStore(HdFoodStoreBean hdFoodStore) {
            this.hdFoodStore = hdFoodStore;
        }

        public static class HdFoodAdvertisementRuleBean {
            /**
             * id : 314895583125036
             * sendcount : 1
             * showcount : 1
             * sendstarttime : 1489550400000
             * sendendtime : 1490932800000
             * advertisementId : 314895583124884
             * createTime : 1489558312000
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

        public static class HdFoodAdvertisementCostBean {
            /**
             * id : 314895583124965
             * advertisementId : 314895583124884
             * money : 154.0
             * createTime : 1489558312000
             */

            private long id;
            private long advertisementId;
            private double money;
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

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }

        public static class HdFoodAdvertisementMemberBean {
            /**
             * id : 314895583125137
             * associatornumber1 : 0
             * associatornumber2 : 999999999999999
             * usernum1 : 0
             * usernum2 : 999999999999999
             * advertisementId : 314895583124884
             * createTime : 1489558312000
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

        public static class HdFoodStoreBean {
            /**
             * id : 314888731062212
             * name : 长安饭店
             * phone : 15911013808
             * notice : 就是666
             * info : 欢迎欢迎
             * companyMemberId : 314888729219408
             * bankCardNo : 632258896775699
             * openingBank : 建设银行
             * accountHolder : 你敢不敢
             * integral : 0
             * approveStatus : 1
             * createTime : 1488873106000
             * modifyTime : 1489151321000
             * status : 0
             * openStatus : 0
             * opentime : 50400000
             * closetime : 54000000
             * longitude : 114.523824
             * latitude : 38.058016
             * regionId : 1078
             * logoImg : http://obqlfysk2.bkt.clouddn.com/logo314888731062212
             * address : 河北石家庄长安区青园街5号
             * isallday : 0
             * isgrab : 2
             * categoryType : 2
             * grab : 1
             */

            private long id;
            private String name;
            private String phone;
            private String notice;
            private String info;
            private long companyMemberId;
            private String bankCardNo;
            private String openingBank;
            private String accountHolder;
            private int integral;
            private int approveStatus;
            private long createTime;
            private long modifyTime;
            private int status;
            private int openStatus;
            private Long opentime;
            private Long closetime;
            private Double longitude;
            private Double latitude;
            private int regionId;
            private String logoImg;
            private String address;
            private int isallday;
            private int isgrab;
            private int categoryType;
            private int grab;

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

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getIsallday() {
                return isallday;
            }

            public void setIsallday(int isallday) {
                this.isallday = isallday;
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
        }
    }
}
