package com.linli.consumer.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tomoyo on 2017/2/22.
 */

public class FindFoodBean {

    /**
     * status : 1
     * msg : 查询成功!
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":10,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":0}
     * data : [{"hdFoodStore":{"id":21,"name":"和兑咖啡店111","nickname":"咖啡厅","domain":"www.kafei.com","wechat":"18231177946","phone":"15371234569","notice":"什么鬼555","info":"12312123","idCardNo":"130682199101177045","companyMemberId":121231551512551,"bankCardNo":"6227000102311520","openingBank":"中国银行","accountHolder":"德玛西亚","viewCount":9,"isStoreNotice":0,"integral":666,"approveStatus":1,"type":1,"categoryid":1,"createTime":1483320937000,"modifyTime":1487313367000,"status":0,"openStatus":0,"opentime":2100000,"closetime":45000000,"longitude":119.1374243,"latitude":42.7157143,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/kafeidian.jpg","backImg":"http://file.uniitown.com/uniitown/uploads/2016/09/30/214752209352454_thum.jpg","address":"111","goodsDescribe":5,"logisticsDescribe":5,"serviceAttitude":5,"commentCount":2,"isallday":1,"typeCount":14,"isgrab":2,"categoryType":2,"grab":1,"paytype":2,"distributiontype":2,"creditLevel":1},"hdFoodStoreoperate":{"id":414846367405713,"bussId":21,"opetype":"2","opemethod":"0","opeavgcost":12,"opereserve":"0","opeseatcount":12,"opeseatcost":12,"opeallseat":12,"operoompersoncount":12,"operoomcost":12,"opeallroomcount":12,"opesendrange":"12","opesendstartmoney":12,"opepackagecost":12,"opesendcost":12,"opelimittime":"0"},"supportCount":0,"collectCount":0},{"hdFoodStore":{"id":314845564722732,"name":"君主阁之归来","phone":"15911013803","companyMemberId":314845562418077,"bankCardNo":"622700131050563696","openingBank":"建设银行","accountHolder":"叶笑","integral":0,"approveStatus":1,"createTime":1484556472000,"status":0,"openStatus":0,"opentime":1484556472000,"longitude":115.862836,"latitude":39.726753,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FiTcHadgiJLqsc_IhNwo9vP3zOaD","isgrab":2,"categoryType":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/314845564722732flag_1484556472493.png","creditLevel":1},"hdFoodStoreoperate":{"id":314845564732043,"bussId":314845564722732,"opeavgcost":0,"opereserve":"","opeseatcount":0,"opeseatcost":0,"opeallseat":0,"operoompersoncount":0,"operoomcost":0,"opeallroomcount":0,"opesendrange":"null","opesendstartmoney":0,"opepackagecost":0,"opesendcost":0,"opelimittime":"20分钟"},"supportCount":0,"collectCount":1},{"hdFoodStore":{"id":314867099258073,"name":"长安coffee","nickname":"美食","phone":"15911013808","notice":"十里桃花醉","info":"等一个人coffee","companyMemberId":314867068472491,"bankCardNo":"6227000131050516919","openingBank":"建设银行","accountHolder":"潇潇","integral":0,"approveStatus":1,"createTime":1486709916000,"modifyTime":1487225592000,"status":0,"openStatus":0,"opentime":25080000,"longitude":114.523301,"latitude":38.058575,"regionId":1078,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FvirmE7YjaNDp_MiP0T1ow9E7S9e","address":"石家庄长安区青园街2号","isallday":0,"isgrab":2,"categoryType":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/314867099163388flag_1486709916443.png","creditLevel":1},"hdFoodStoreoperate":{"id":314867099261974,"bussId":314867099258073,"opemethod":"0","opeavgcost":978,"opereserve":"","opeseatcount":0,"opeseatcost":0,"opeallseat":0,"operoompersoncount":0,"operoomcost":0,"opeallroomcount":0,"opesendrange":"4998","opesendstartmoney":0,"opepackagecost":20,"opesendcost":60,"opelimittime":"20分钟"},"supportCount":30,"collectCount":2},{"hdFoodStore":{"id":314877472069562,"name":"和兑九星级饭店","phone":"18390876375","companyMemberId":314872302202444,"bankCardNo":"62288864973949734","openingBank":"建设银行","accountHolder":"和兑","integral":0,"approveStatus":1,"createTime":1487747206000,"status":0,"openStatus":0,"opentime":1487747206000,"longitude":116.137424,"latitude":39.715714,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FlNO9xCLAoTu8PjKAOYIuqMXU4hY","isgrab":2,"categoryType":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/314877472069562flag_1487747208576.png","creditLevel":1},"hdFoodStoreoperate":{"id":314877472115393,"bussId":314877472069562,"opetype":"1"},"supportCount":0,"collectCount":0},{"hdFoodStore":{"id":414845543688192,"name":"龙","phone":"15833334127","companyMemberId":314845539022362,"bankCardNo":"123456789","openingBank":"么么哒","accountHolder":"龙哥","integral":0,"approveStatus":1,"createTime":1484554368000,"status":0,"openStatus":0,"opentime":1484554368000,"longitude":115.862836,"latitude":39.726753,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FjeADhxD2kuekD65FEAAEC-YnzB0","isgrab":2,"categoryType":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414845543688192flag_1484554369268.png","creditLevel":1},"supportCount":0,"collectCount":0},{"hdFoodStore":{"id":414847205219604,"name":"三拳打死","phone":"13835833615","companyMemberId":314847078507689,"bankCardNo":"123456789","openingBank":"王培","accountHolder":"王培","integral":0,"approveStatus":1,"createTime":1484720524000,"status":0,"openStatus":0,"opentime":1484720527000,"longitude":116.137424,"latitude":39.715714,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/Fo5slp_SduIDPsyQlVmP6WirzmgT","isgrab":2,"categoryType":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414847205219604flag_1484720529386.png","creditLevel":1},"hdFoodStoreoperate":{"id":414847205418645,"bussId":414847205219604,"opetype":"3"},"supportCount":0,"collectCount":1},{"hdFoodStore":{"id":814846353207249,"name":"瓶子","phone":"15201489130","companyMemberId":314846348032272,"bankCardNo":"123456789","openingBank":"123456","accountHolder":"张晓梅","integral":0,"approveStatus":1,"createTime":1484635320000,"status":0,"openStatus":0,"opentime":1484635320000,"longitude":115.862836,"latitude":39.726753,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FmeXUvNbQ7z___ljrZURfgPG3blx","isgrab":2,"categoryType":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/814846353207249flag_1484635320876.png","creditLevel":1},"hdFoodStoreoperate":{"id":814846353214070,"bussId":814846353207249,"opetype":"2"},"supportCount":0,"collectCount":0},{"hdFoodStore":{"id":814867151159876,"name":"张海帅大哥的店","phone":"18004224320","companyMemberId":814867146932753,"bankCardNo":"6227000783011446209","openingBank":"中国建设银行","accountHolder":"汤远见","integral":0,"approveStatus":1,"createTime":1486715115000,"status":0,"openStatus":0,"opentime":1486715115000,"longitude":115.862836,"latitude":39.726753,"regionId":1,"isgrab":2,"categoryType":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/814867151159876flag_1486715116150.png","creditLevel":1},"hdFoodStoreoperate":{"id":814867151165797,"bussId":814867151159876,"opetype":"3"},"supportCount":0,"collectCount":1}]
     */

    private int status;
    private String msg;
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

    private PageBean page;
    /**
     * hdFoodStore : {"id":21,"name":"和兑咖啡店111","nickname":"咖啡厅","domain":"www.kafei.com","wechat":"18231177946","phone":"15371234569","notice":"什么鬼555","info":"12312123","idCardNo":"130682199101177045","companyMemberId":121231551512551,"bankCardNo":"6227000102311520","openingBank":"中国银行","accountHolder":"德玛西亚","viewCount":9,"isStoreNotice":0,"integral":666,"approveStatus":1,"type":1,"categoryid":1,"createTime":1483320937000,"modifyTime":1487313367000,"status":0,"openStatus":0,"opentime":2100000,"closetime":45000000,"longitude":119.1374243,"latitude":42.7157143,"regionId":1,"logoImg":"http://obqlfysk2.bkt.clouddn.com/kafeidian.jpg","backImg":"http://file.uniitown.com/uniitown/uploads/2016/09/30/214752209352454_thum.jpg","address":"111","goodsDescribe":5,"logisticsDescribe":5,"serviceAttitude":5,"commentCount":2,"isallday":1,"typeCount":14,"isgrab":2,"categoryType":2,"grab":1,"paytype":2,"distributiontype":2,"creditLevel":1}
     * hdFoodStoreoperate : {"id":414846367405713,"bussId":21,"opetype":"2","opemethod":"0","opeavgcost":12,"opereserve":"0","opeseatcount":12,"opeseatcost":12,"opeallseat":12,"operoompersoncount":12,"operoomcost":12,"opeallroomcount":12,"opesendrange":"12","opesendstartmoney":12,"opepackagecost":12,"opesendcost":12,"opelimittime":"0"}
     * supportCount : 0
     * collectCount : 0
     */

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
         * id : 21
         * name : 和兑咖啡店111
         * nickname : 咖啡厅
         * domain : www.kafei.com
         * wechat : 18231177946
         * phone : 15371234569
         * notice : 什么鬼555
         * info : 12312123
         * idCardNo : 130682199101177045
         * companyMemberId : 121231551512551
         * bankCardNo : 6227000102311520
         * openingBank : 中国银行
         * accountHolder : 德玛西亚
         * viewCount : 9
         * isStoreNotice : 0
         * integral : 666
         * approveStatus : 1
         * type : 1
         * categoryid : 1
         * createTime : 1483320937000
         * modifyTime : 1487313367000
         * status : 0
         * openStatus : 0
         * opentime : 2100000
         * closetime : 45000000
         * longitude : 119.1374243
         * latitude : 42.7157143
         * regionId : 1
         * logoImg : http://obqlfysk2.bkt.clouddn.com/kafeidian.jpg
         * backImg : http://file.uniitown.com/uniitown/uploads/2016/09/30/214752209352454_thum.jpg
         * address : 111
         * goodsDescribe : 5
         * logisticsDescribe : 5
         * serviceAttitude : 5
         * commentCount : 2
         * isallday : 1
         * typeCount : 14
         * isgrab : 2
         * categoryType : 2
         * grab : 1
         * paytype : 2
         * distributiontype : 2
         * creditLevel : 1
         */

        private HdFoodStoreBean hdFoodStore;
        /**
         * id : 414846367405713
         * bussId : 21
         * opetype : 2
         * opemethod : 0
         * opeavgcost : 12
         * opereserve : 0
         * opeseatcount : 12
         * opeseatcost : 12
         * opeallseat : 12
         * operoompersoncount : 12
         * operoomcost : 12
         * opeallroomcount : 12
         * opesendrange : 12
         * opesendstartmoney : 12
         * opepackagecost : 12
         * opesendcost : 12
         * opelimittime : 0
         */

        private HdFoodStoreoperateBean hdFoodStoreoperate;
        private int supportCount;
        private int collectCount;

        public HdFoodStoreBean getHdFoodStore() {
            return hdFoodStore;
        }

        public void setHdFoodStore(HdFoodStoreBean hdFoodStore) {
            this.hdFoodStore = hdFoodStore;
        }

        public HdFoodStoreoperateBean getHdFoodStoreoperate() {
            return hdFoodStoreoperate;
        }

        public void setHdFoodStoreoperate(HdFoodStoreoperateBean hdFoodStoreoperate) {
            this.hdFoodStoreoperate = hdFoodStoreoperate;
        }

        public int getSupportCount() {
            return supportCount;
        }

        public void setSupportCount(int supportCount) {
            this.supportCount = supportCount;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
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
            private int goodsDescribe;
            private int logisticsDescribe;
            private int serviceAttitude;
            private int commentCount;
            private int isallday;
            private int typeCount;
            private int isgrab;
            private int categoryType;
            private int grab;
            private int paytype;
            private int distributiontype;
            private int creditLevel;
            private String distance;        //距离

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
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

            public int getCreditLevel() {
                return creditLevel;
            }

            public void setCreditLevel(int creditLevel) {
                this.creditLevel = creditLevel;
            }
        }

        public static class HdFoodStoreoperateBean {
            private long id;
            private long bussId;
            private String opetype;
            private String opemethod;
            private BigDecimal opeavgcost;
            private String opereserve;
            private int opeseatcount;
            private BigDecimal opeseatcost;
            private int opeallseat;
            private int operoompersoncount;
            private BigDecimal operoomcost;
            private int opeallroomcount;
            private String opesendrange;
            private BigDecimal opesendstartmoney;
            private BigDecimal opepackagecost;
            private BigDecimal opesendcost;
            private String opelimittime;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getBussId() {
                return bussId;
            }

            public void setBussId(long bussId) {
                this.bussId = bussId;
            }

            public String getOpetype() {
                return opetype;
            }

            public void setOpetype(String opetype) {
                this.opetype = opetype;
            }

            public String getOpemethod() {
                return opemethod;
            }

            public void setOpemethod(String opemethod) {
                this.opemethod = opemethod;
            }

            public BigDecimal getOpeavgcost() {
                return opeavgcost;
            }

            public void setOpeavgcost(BigDecimal opeavgcost) {
                this.opeavgcost = opeavgcost;
            }

            public String getOpereserve() {
                return opereserve;
            }

            public void setOpereserve(String opereserve) {
                this.opereserve = opereserve;
            }

            public int getOpeseatcount() {
                return opeseatcount;
            }

            public void setOpeseatcount(int opeseatcount) {
                this.opeseatcount = opeseatcount;
            }

            public BigDecimal getOpeseatcost() {
                return opeseatcost;
            }

            public void setOpeseatcost(BigDecimal opeseatcost) {
                this.opeseatcost = opeseatcost;
            }

            public int getOpeallseat() {
                return opeallseat;
            }

            public void setOpeallseat(int opeallseat) {
                this.opeallseat = opeallseat;
            }

            public int getOperoompersoncount() {
                return operoompersoncount;
            }

            public void setOperoompersoncount(int operoompersoncount) {
                this.operoompersoncount = operoompersoncount;
            }

            public BigDecimal getOperoomcost() {
                return operoomcost;
            }

            public void setOperoomcost(BigDecimal operoomcost) {
                this.operoomcost = operoomcost;
            }

            public int getOpeallroomcount() {
                return opeallroomcount;
            }

            public void setOpeallroomcount(int opeallroomcount) {
                this.opeallroomcount = opeallroomcount;
            }

            public String getOpesendrange() {
                return opesendrange;
            }

            public void setOpesendrange(String opesendrange) {
                this.opesendrange = opesendrange;
            }

            public BigDecimal getOpesendstartmoney() {
                return opesendstartmoney;
            }

            public void setOpesendstartmoney(BigDecimal opesendstartmoney) {
                this.opesendstartmoney = opesendstartmoney;
            }

            public BigDecimal getOpepackagecost() {
                return opepackagecost;
            }

            public void setOpepackagecost(BigDecimal opepackagecost) {
                this.opepackagecost = opepackagecost;
            }

            public BigDecimal getOpesendcost() {
                return opesendcost;
            }

            public void setOpesendcost(BigDecimal opesendcost) {
                this.opesendcost = opesendcost;
            }

            public String getOpelimittime() {
                return opelimittime;
            }

            public void setOpelimittime(String opelimittime) {
                this.opelimittime = opelimittime;
            }
        }
    }
}
