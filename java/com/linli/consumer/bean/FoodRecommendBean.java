package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/1/3.
 * {@link com.linli.consumer.net.FoodNet}
 */

public class FoodRecommendBean {



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
     * totalCount : 8
     */

    private PageBean page;
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
         * keyword : null
         * grab : 1
         * paytype : 2
         * distributiontype : 2
         */

        private HdFoodStoreBean hdFoodStore;
        /**
         * id : 1
         * bussId : 2
         * opemenu : null
         * opetype : null
         * opemethod : null
         * opeatmosphere : 优雅
         * opeavgcost : 66
         * opereserve : 7天
         * opeseatcount : 12
         * opeseatcost : 89.9
         * opeallseat : 9
         * operoompersoncount : 12
         * operoomcost : 60
         * opeallroomcount : 9
         * opesendrange : 3000米
         * opesendstartmoney : 66
         * opepackagecost : 5
         * opesendcost : 3.5
         * opelimittime : 20分钟
         * opeallcost : null
         * opeisdiscount : null
         * opediscountcost : null
         * issend : null
         * ispackage : null
         */

        private HdFoodStoreoperateBean hdFoodStoreoperate;
        /**
         * id : 57
         * bussId : 2
         * name : 宫保鸡丁
         * cuisine :
         * maining :
         * accessories :
         * imgpath : http://obqlfysk2.bkt.clouddn.com/.12 - 副本 (2).jpg
         * makemethod :
         * price : 15
         * status : 0
         * isoutshop : 1
         * sort : 1
         * belongtype : 21
         */

        private int supportCount;       //喜欢
        private int collectCount;       //收藏

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

        private List<CookbookListBean> cookbookList;

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

        public List<CookbookListBean> getCookbookList() {
            return cookbookList;
        }

        public void setCookbookList(List<CookbookListBean> cookbookList) {
            this.cookbookList = cookbookList;
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
            private Long categoryid;
            private long createTime;
            private long modifyTime;
            private int status;
            private int openStatus;
            private long opentime;
            private long closetime;
            private double longitude;
            private double latitude;
            private long regionId;
            private String logoImg;
            private String backImg;
            private String address;
            private int goodsDescribe;
            private int logisticsDescribe;
            private int serviceAttitude;        //几颗星
            private int commentCount;
            private int isallday;
            private int typeCount;
            private int isgrab;
            private int categoryType;
            private Object keyword;
            private int grab;
            private int paytype;
            private int distributiontype;

            private String distance;            //距离
            private Integer isactivity;             //活动开启与否

            public Integer getIsactivity() {
                return isactivity;
            }

            public void setIsactivity(Integer isactivity) {
                this.isactivity = isactivity;
            }

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

            public Object getKeyword() {
                return keyword;
            }

            public void setKeyword(Object keyword) {
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

        public static class HdFoodStoreoperateBean {
            private long id;
            private long bussId;
            private Object opemenu;
            private Object opetype;
            private String opemethod;       //0:堂吃   1:外卖    2:都有
            private String opeatmosphere;
            private int opeavgcost;
            private String opereserve;
            private int opeseatcount;
            private double opeseatcost;
            private int opeallseat;
            private int operoompersoncount;
            private int operoomcost;
            private int opeallroomcount;
            private String opesendrange;
            private int opesendstartmoney;
            private int opepackagecost;
            private double opesendcost;
            private String opelimittime;
            private Object opeallcost;
            private Object opeisdiscount;
            private Object opediscountcost;
            private Object issend;
            private Object ispackage;

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

            public Object getOpemenu() {
                return opemenu;
            }

            public void setOpemenu(Object opemenu) {
                this.opemenu = opemenu;
            }

            public Object getOpetype() {
                return opetype;
            }

            public void setOpetype(Object opetype) {
                this.opetype = opetype;
            }

            public String getOpemethod() {
                return opemethod;
            }

            public void setOpemethod(String opemethod) {
                this.opemethod = opemethod;
            }

            public String getOpeatmosphere() {
                return opeatmosphere;
            }

            public void setOpeatmosphere(String opeatmosphere) {
                this.opeatmosphere = opeatmosphere;
            }

            public int getOpeavgcost() {
                return opeavgcost;
            }

            public void setOpeavgcost(int opeavgcost) {
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

            public double getOpeseatcost() {
                return opeseatcost;
            }

            public void setOpeseatcost(double opeseatcost) {
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

            public int getOperoomcost() {
                return operoomcost;
            }

            public void setOperoomcost(int operoomcost) {
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

            public int getOpesendstartmoney() {
                return opesendstartmoney;
            }

            public void setOpesendstartmoney(int opesendstartmoney) {
                this.opesendstartmoney = opesendstartmoney;
            }

            public int getOpepackagecost() {
                return opepackagecost;
            }

            public void setOpepackagecost(int opepackagecost) {
                this.opepackagecost = opepackagecost;
            }

            public double getOpesendcost() {
                return opesendcost;
            }

            public void setOpesendcost(double opesendcost) {
                this.opesendcost = opesendcost;
            }

            public String getOpelimittime() {
                return opelimittime;
            }

            public void setOpelimittime(String opelimittime) {
                this.opelimittime = opelimittime;
            }

            public Object getOpeallcost() {
                return opeallcost;
            }

            public void setOpeallcost(Object opeallcost) {
                this.opeallcost = opeallcost;
            }

            public Object getOpeisdiscount() {
                return opeisdiscount;
            }

            public void setOpeisdiscount(Object opeisdiscount) {
                this.opeisdiscount = opeisdiscount;
            }

            public Object getOpediscountcost() {
                return opediscountcost;
            }

            public void setOpediscountcost(Object opediscountcost) {
                this.opediscountcost = opediscountcost;
            }

            public Object getIssend() {
                return issend;
            }

            public void setIssend(Object issend) {
                this.issend = issend;
            }

            public Object getIspackage() {
                return ispackage;
            }

            public void setIspackage(Object ispackage) {
                this.ispackage = ispackage;
            }
        }

        public static class CookbookListBean {
            private long id;
            private long bussId;
            private String name;
            private String cuisine;
            private String maining;
            private String accessories;
            private String imgpath;
            private String makemethod;
            private double price;
            private String status;
            private String isoutshop;
            private int sort;
            private long belongtype;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCuisine() {
                return cuisine;
            }

            public void setCuisine(String cuisine) {
                this.cuisine = cuisine;
            }

            public String getMaining() {
                return maining;
            }

            public void setMaining(String maining) {
                this.maining = maining;
            }

            public String getAccessories() {
                return accessories;
            }

            public void setAccessories(String accessories) {
                this.accessories = accessories;
            }

            public String getImgpath() {
                return imgpath;
            }

            public void setImgpath(String imgpath) {
                this.imgpath = imgpath;
            }

            public String getMakemethod() {
                return makemethod;
            }

            public void setMakemethod(String makemethod) {
                this.makemethod = makemethod;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIsoutshop() {
                return isoutshop;
            }

            public void setIsoutshop(String isoutshop) {
                this.isoutshop = isoutshop;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public long getBelongtype() {
                return belongtype;
            }

            public void setBelongtype(long belongtype) {
                this.belongtype = belongtype;
            }
        }
    }
}
