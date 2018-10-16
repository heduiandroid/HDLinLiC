package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2016/12/19.
 *
 * 美食使用的bean
 *
 */
@Deprecated
public class FoodAndShopBean {

    /**
     * prePage : 1
     * plainPageNum : 1
     * orderDirection :
     * nextPage : 1
     * totalCount : 1
     * numPerPage : 10
     * orderField :
     * totalPage : 1
     * pageNum : 1
     */

    private PageBean page;
    /**
     * page : {"prePage":1,"plainPageNum":1,"orderDirection":"","nextPage":1,"totalCount":1,"numPerPage":10,"orderField":"","totalPage":1,"pageNum":1}
     * status : 1
     * data : [{"hdFoodStore":{"openingBank":"建设银行","idCardNo":"130682199101177071","grab":1,"createTime":1480819124000,"phone":"18633894413","closetime":1480912798000,"backImg":"http://pic.sogou.com/d?query=%BF%A7%B7%C8%CC%FC&mood=0&picformat=0&mode=1&di=0&did=1#did0","type":1,"bankCardNo":"6227000131050516911","info":"回忆过去，珍惜现在，携手未来","integral":666,"id":2,"approveStatus":1,"name":"小月伴弯1","isgrab":2,"serviceAttitude":5,"domain":"www.xiaoyuebanwan.com","isStoreNotice":0,"longitude":116.1374243,"notice":"情侣入店8折优惠","modifyTime":1480905541000,"opentime":1480905574000,"regionId":1,"companyMemberId":123,"nickname":"咖啡店","status":0,"categoryType":2,"typeCount":13,"commentCount":26,"categoryid":1,"address":"北京市房山区白杨西路5号","accountHolder":"党伟","goodsDescribe":5,"openStatus":0,"latitude":39.7157143,"wechat":"18633894413","logisticsDescribe":5,"logoImg":"http://pic.sogou.com/d?query=%BF%A7%B7%C8%CC%FC&mood=0&picformat=0&mode=1&di=0&did=1#did7","viewCount":520,"isallday":1},"cookbookList":[{"belongtype":null,"sort":null,"cuisine":null,"status":null,"accessories":null,"id":414809881987892,"imgpath":"http://obqlfysk2.bkt.clouddn.com/.12 - 副本 (2).jpg","makemethod":null,"price":null,"isoutshop":null,"name":null,"maining":null,"bussId":2}]}]
     * msg : 查询成功!
     * url : null
     */

    private int status;
    private String msg;
    private Object url;
    /**
     * hdFoodStore : {"openingBank":"建设银行","idCardNo":"130682199101177071","grab":1,"createTime":1480819124000,"phone":"18633894413","closetime":1480912798000,"backImg":"http://pic.sogou.com/d?query=%BF%A7%B7%C8%CC%FC&mood=0&picformat=0&mode=1&di=0&did=1#did0","type":1,"bankCardNo":"6227000131050516911","info":"回忆过去，珍惜现在，携手未来","integral":666,"id":2,"approveStatus":1,"name":"小月伴弯1","isgrab":2,"serviceAttitude":5,"domain":"www.xiaoyuebanwan.com","isStoreNotice":0,"longitude":116.1374243,"notice":"情侣入店8折优惠","modifyTime":1480905541000,"opentime":1480905574000,"regionId":1,"companyMemberId":123,"nickname":"咖啡店","status":0,"categoryType":2,"typeCount":13,"commentCount":26,"categoryid":1,"address":"北京市房山区白杨西路5号","accountHolder":"党伟","goodsDescribe":5,"openStatus":0,"latitude":39.7157143,"wechat":"18633894413","logisticsDescribe":5,"logoImg":"http://pic.sogou.com/d?query=%BF%A7%B7%C8%CC%FC&mood=0&picformat=0&mode=1&di=0&did=1#did7","viewCount":520,"isallday":1}
     * cookbookList : [{"belongtype":null,"sort":null,"cuisine":null,"status":null,"accessories":null,"id":414809881987892,"imgpath":"http://obqlfysk2.bkt.clouddn.com/.12 - 副本 (2).jpg","makemethod":null,"price":null,"isoutshop":null,"name":null,"maining":null,"bussId":2}]
     */

    private List<DataBean> data;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
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
        private int prePage;
        private int plainPageNum;
        private String orderDirection;
        private int nextPage;
        private int totalCount;
        private int numPerPage;
        private String orderField;
        private int totalPage;
        private int pageNum;

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getPlainPageNum() {
            return plainPageNum;
        }

        public void setPlainPageNum(int plainPageNum) {
            this.plainPageNum = plainPageNum;
        }

        public String getOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(String orderDirection) {
            this.orderDirection = orderDirection;
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

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }
    }

    public static class DataBean {
        /**
         * openingBank : 建设银行
         * idCardNo : 130682199101177071
         * grab : 1
         * createTime : 1480819124000
         * phone : 18633894413
         * closetime : 1480912798000
         * backImg : http://pic.sogou.com/d?query=%BF%A7%B7%C8%CC%FC&mood=0&picformat=0&mode=1&di=0&did=1#did0
         * type : 1
         * bankCardNo : 6227000131050516911
         * info : 回忆过去，珍惜现在，携手未来
         * integral : 666
         * id : 2
         * approveStatus : 1
         * name : 小月伴弯1
         * isgrab : 2
         * serviceAttitude : 5
         * domain : www.xiaoyuebanwan.com
         * isStoreNotice : 0
         * longitude : 116.1374243
         * notice : 情侣入店8折优惠
         * modifyTime : 1480905541000
         * opentime : 1480905574000
         * regionId : 1
         * companyMemberId : 123
         * nickname : 咖啡店
         * status : 0
         * categoryType : 2
         * typeCount : 13
         * commentCount : 26
         * categoryid : 1
         * address : 北京市房山区白杨西路5号
         * accountHolder : 党伟
         * goodsDescribe : 5
         * openStatus : 0
         * latitude : 39.7157143
         * wechat : 18633894413
         * logisticsDescribe : 5
         * logoImg : http://pic.sogou.com/d?query=%BF%A7%B7%C8%CC%FC&mood=0&picformat=0&mode=1&di=0&did=1#did7
         * viewCount : 520
         * isallday : 1
         */

        private HdFoodStoreBean hdFoodStore;
        /**
         * belongtype : null
         * sort : null
         * cuisine : null
         * status : null
         * accessories : null
         * id : 414809881987892
         * imgpath : http://obqlfysk2.bkt.clouddn.com/.12 - 副本 (2).jpg
         * makemethod : null
         * price : null
         * isoutshop : null
         * name : null
         * maining : null
         * bussId : 2
         */

        private List<CookbookListBean> cookbookList;

        public HdFoodStoreBean getHdFoodStore() {
            return hdFoodStore;
        }

        public void setHdFoodStore(HdFoodStoreBean hdFoodStore) {
            this.hdFoodStore = hdFoodStore;
        }

        public List<CookbookListBean> getCookbookList() {
            return cookbookList;
        }

        public void setCookbookList(List<CookbookListBean> cookbookList) {
            this.cookbookList = cookbookList;
        }

        public class HdFoodStoreBean {
            private String openingBank;
            private String idCardNo;
            private int grab;
            private long createTime;
            private String phone;
            private long closetime;
            private String backImg;
            private int type;
            private String bankCardNo;
            private String info;
            private int integral;
            private int id;
            private int approveStatus;
            private String name;
            private int isgrab;
            private int serviceAttitude;
            private String domain;
            private int isStoreNotice;
            private double longitude;
            private String notice;
            private long modifyTime;
            private long opentime;
            private int regionId;
            private int companyMemberId;
            private String nickname;
            private int status;
            private int categoryType;
            private int typeCount;
            private int commentCount;
            private int categoryid;
            private String address;
            private String accountHolder;
            private int goodsDescribe;
            private int openStatus;
            private double latitude;
            private String wechat;
            private int logisticsDescribe;
            private String logoImg;
            private int viewCount;
            private int isallday;

            public String getOpeningBank() {
                return openingBank;
            }

            public void setOpeningBank(String openingBank) {
                this.openingBank = openingBank;
            }

            public String getIdCardNo() {
                return idCardNo;
            }

            public void setIdCardNo(String idCardNo) {
                this.idCardNo = idCardNo;
            }

            public int getGrab() {
                return grab;
            }

            public void setGrab(int grab) {
                this.grab = grab;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public long getClosetime() {
                return closetime;
            }

            public void setClosetime(long closetime) {
                this.closetime = closetime;
            }

            public String getBackImg() {
                return backImg;
            }

            public void setBackImg(String backImg) {
                this.backImg = backImg;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getBankCardNo() {
                return bankCardNo;
            }

            public void setBankCardNo(String bankCardNo) {
                this.bankCardNo = bankCardNo;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getApproveStatus() {
                return approveStatus;
            }

            public void setApproveStatus(int approveStatus) {
                this.approveStatus = approveStatus;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIsgrab() {
                return isgrab;
            }

            public void setIsgrab(int isgrab) {
                this.isgrab = isgrab;
            }

            public int getServiceAttitude() {
                return serviceAttitude;
            }

            public void setServiceAttitude(int serviceAttitude) {
                this.serviceAttitude = serviceAttitude;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public int getIsStoreNotice() {
                return isStoreNotice;
            }

            public void setIsStoreNotice(int isStoreNotice) {
                this.isStoreNotice = isStoreNotice;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public long getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public long getOpentime() {
                return opentime;
            }

            public void setOpentime(long opentime) {
                this.opentime = opentime;
            }

            public int getRegionId() {
                return regionId;
            }

            public void setRegionId(int regionId) {
                this.regionId = regionId;
            }

            public int getCompanyMemberId() {
                return companyMemberId;
            }

            public void setCompanyMemberId(int companyMemberId) {
                this.companyMemberId = companyMemberId;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCategoryType() {
                return categoryType;
            }

            public void setCategoryType(int categoryType) {
                this.categoryType = categoryType;
            }

            public int getTypeCount() {
                return typeCount;
            }

            public void setTypeCount(int typeCount) {
                this.typeCount = typeCount;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
                this.categoryid = categoryid;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAccountHolder() {
                return accountHolder;
            }

            public void setAccountHolder(String accountHolder) {
                this.accountHolder = accountHolder;
            }

            public int getGoodsDescribe() {
                return goodsDescribe;
            }

            public void setGoodsDescribe(int goodsDescribe) {
                this.goodsDescribe = goodsDescribe;
            }

            public int getOpenStatus() {
                return openStatus;
            }

            public void setOpenStatus(int openStatus) {
                this.openStatus = openStatus;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public String getWechat() {
                return wechat;
            }

            public void setWechat(String wechat) {
                this.wechat = wechat;
            }

            public int getLogisticsDescribe() {
                return logisticsDescribe;
            }

            public void setLogisticsDescribe(int logisticsDescribe) {
                this.logisticsDescribe = logisticsDescribe;
            }

            public String getLogoImg() {
                return logoImg;
            }

            public void setLogoImg(String logoImg) {
                this.logoImg = logoImg;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }

            public int getIsallday() {
                return isallday;
            }

            public void setIsallday(int isallday) {
                this.isallday = isallday;
            }
        }

        public class CookbookListBean {
            private Object belongtype;
            private Object sort;
            private Object cuisine;
            private Object status;
            private Object accessories;
            private long id;
            private String imgpath;
            private Object makemethod;
            private Object price;
            private Object isoutshop;
            private Object name;
            private Object maining;
            private int bussId;

            public Object getBelongtype() {
                return belongtype;
            }

            public void setBelongtype(Object belongtype) {
                this.belongtype = belongtype;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public Object getCuisine() {
                return cuisine;
            }

            public void setCuisine(Object cuisine) {
                this.cuisine = cuisine;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getAccessories() {
                return accessories;
            }

            public void setAccessories(Object accessories) {
                this.accessories = accessories;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getImgpath() {
                return imgpath;
            }

            public void setImgpath(String imgpath) {
                this.imgpath = imgpath;
            }

            public Object getMakemethod() {
                return makemethod;
            }

            public void setMakemethod(Object makemethod) {
                this.makemethod = makemethod;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public Object getIsoutshop() {
                return isoutshop;
            }

            public void setIsoutshop(Object isoutshop) {
                this.isoutshop = isoutshop;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getMaining() {
                return maining;
            }

            public void setMaining(Object maining) {
                this.maining = maining;
            }

            public int getBussId() {
                return bussId;
            }

            public void setBussId(int bussId) {
                this.bussId = bussId;
            }
        }
    }
}
