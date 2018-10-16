package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/2/15.
 */

public class SearchGood {

    /**
     * status : 1
     * msg : 查询成功!
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":10,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":1}
     * data : [{"id":214847271275128,"name":"跑鞋","storeId":414846522510124,"categoryId":14828949725032,"isShow":0,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage1":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage2":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage3":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","info":"\t滏阳河\t\t","approveStatus":0,"brand":"李宁运动裤","createTime":1484727127000,"modifyTime":1486810124000,"status":0,"salesVolume":0,"storeCategoryId":214847267832967,"goodsSpecs":[{"id":914866321519513,"platformPrice":600,"inventory":100,"specaNameId":914866320575301,"specaValue":"1","specbNameId":914866320575301,"specbValue":"1","speccNameId":914866320575301,"speccValue":"1","anticipatedRevenue":0}],"goodsType":0}]
     */

    private int status;
    private String msg;
    private PageBean page;
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
        /**
         * plainPageNum : 1
         * pageNum : 1
         * numPerPage : 10
         * orderField :
         * orderDirection :
         * totalPage : 1
         * prePage : 1
         * nextPage : 1
         * totalCount : 1
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
         * id : 214847271275128
         * name : 跑鞋
         * storeId : 414846522510124
         * categoryId : 14828949725032
         * isShow : 0
         * isPat : 0
         * primaryImage : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
         * productImage1 : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
         * productImage2 : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
         * productImage3 : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
         * info : 	滏阳河
         * approveStatus : 0
         * brand : 李宁运动裤
         * createTime : 1484727127000
         * modifyTime : 1486810124000
         * status : 0
         * salesVolume : 0
         * storeCategoryId : 214847267832967
         * goodsSpecs : [{"id":914866321519513,"platformPrice":600,"inventory":100,"specaNameId":914866320575301,"specaValue":"1","specbNameId":914866320575301,"specbValue":"1","speccNameId":914866320575301,"speccValue":"1","anticipatedRevenue":0}]
         * goodsType : 0
         */

        private long id;
        private String name;
        private long storeId;
        private long categoryId;
        private int isShow;
        private int isPat;
        private String primaryImage;
        private String productImage1;
        private String productImage2;
        private String productImage3;
        private String info;
        private int approveStatus;
        private String brand;
        private long createTime;
        private long modifyTime;
        private int status;
        private int salesVolume;
        private long storeCategoryId;
        private int goodsType;
        private List<GoodsSpecsBean> goodsSpecs;

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

        public long getStoreId() {
            return storeId;
        }

        public void setStoreId(long storeId) {
            this.storeId = storeId;
        }

        public long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(long categoryId) {
            this.categoryId = categoryId;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public int getIsPat() {
            return isPat;
        }

        public void setIsPat(int isPat) {
            this.isPat = isPat;
        }

        public String getPrimaryImage() {
            return primaryImage;
        }

        public void setPrimaryImage(String primaryImage) {
            this.primaryImage = primaryImage;
        }

        public String getProductImage1() {
            return productImage1;
        }

        public void setProductImage1(String productImage1) {
            this.productImage1 = productImage1;
        }

        public String getProductImage2() {
            return productImage2;
        }

        public void setProductImage2(String productImage2) {
            this.productImage2 = productImage2;
        }

        public String getProductImage3() {
            return productImage3;
        }

        public void setProductImage3(String productImage3) {
            this.productImage3 = productImage3;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getApproveStatus() {
            return approveStatus;
        }

        public void setApproveStatus(int approveStatus) {
            this.approveStatus = approveStatus;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
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

        public int getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(int salesVolume) {
            this.salesVolume = salesVolume;
        }

        public long getStoreCategoryId() {
            return storeCategoryId;
        }

        public void setStoreCategoryId(long storeCategoryId) {
            this.storeCategoryId = storeCategoryId;
        }

        public int getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(int goodsType) {
            this.goodsType = goodsType;
        }

        public List<GoodsSpecsBean> getGoodsSpecs() {
            return goodsSpecs;
        }

        public void setGoodsSpecs(List<GoodsSpecsBean> goodsSpecs) {
            this.goodsSpecs = goodsSpecs;
        }

        public static class GoodsSpecsBean {
            /**
             * id : 914866321519513
             * platformPrice : 600.0
             * inventory : 100
             * specaNameId : 914866320575301
             * specaValue : 1
             * specbNameId : 914866320575301
             * specbValue : 1
             * speccNameId : 914866320575301
             * speccValue : 1
             * anticipatedRevenue : 0.0
             */

            private long id;
            private double platformPrice;
            private int inventory;
            private long specaNameId;
            private String specaValue;
            private long specbNameId;
            private String specbValue;
            private long speccNameId;
            private String speccValue;
            private double anticipatedRevenue;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public double getPlatformPrice() {
                return platformPrice;
            }

            public void setPlatformPrice(double platformPrice) {
                this.platformPrice = platformPrice;
            }

            public int getInventory() {
                return inventory;
            }

            public void setInventory(int inventory) {
                this.inventory = inventory;
            }

            public long getSpecaNameId() {
                return specaNameId;
            }

            public void setSpecaNameId(long specaNameId) {
                this.specaNameId = specaNameId;
            }

            public String getSpecaValue() {
                return specaValue;
            }

            public void setSpecaValue(String specaValue) {
                this.specaValue = specaValue;
            }

            public long getSpecbNameId() {
                return specbNameId;
            }

            public void setSpecbNameId(long specbNameId) {
                this.specbNameId = specbNameId;
            }

            public String getSpecbValue() {
                return specbValue;
            }

            public void setSpecbValue(String specbValue) {
                this.specbValue = specbValue;
            }

            public long getSpeccNameId() {
                return speccNameId;
            }

            public void setSpeccNameId(long speccNameId) {
                this.speccNameId = speccNameId;
            }

            public String getSpeccValue() {
                return speccValue;
            }

            public void setSpeccValue(String speccValue) {
                this.speccValue = speccValue;
            }

            public double getAnticipatedRevenue() {
                return anticipatedRevenue;
            }

            public void setAnticipatedRevenue(double anticipatedRevenue) {
                this.anticipatedRevenue = anticipatedRevenue;
            }
        }
    }
}
