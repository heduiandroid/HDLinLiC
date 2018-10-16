package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/2/14.
 */

public class GoodBean {

    /**
     * status : 0
     * msg : 查询成功!
     * data : [{"id":614924175963809,"name":"花生","storeId":614921377337256,"isShow":1,"isPat":1,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/FutLa_TP5a0pytcJdkSunhJCZNsT","productImage1":"","productImage2":"","productImage3":"","maxprice":0.01,"minprice":0.01,"brand":"花生","status":0,"salesVolume":0,"storeCategoryId":614921378085891,"goodsSpecs":[{"id":614924175963810,"goodsId":614924175963809,"platformPrice":0.01,"inventory":1000,"specaNameId":614924175321448,"specaValue":"黄色","createTime":1492417596000,"salesVolume":0,"ratePraise":0,"goodCommonts":0,"middleCommonts":0,"badCommonts":0,"anticipatedRevenue":0,"status":0}],"inventory":1000},{"id":614921378484142,"name":"瓜子","storeId":614921377337256,"isShow":1,"isPat":1,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fu3y7w9LKNG9FSutDd5_I4fZk60M","productImage1":"","productImage2":"","productImage3":"","maxprice":8,"minprice":8,"brand":"恰恰","status":0,"salesVolume":0,"storeCategoryId":614921378085891,"goodsSpecs":[{"id":614921378484153,"goodsId":614921378484142,"platformPrice":8,"inventory":998,"createTime":1492137848000,"anticipatedRevenue":0,"status":0}],"inventory":998}]
     */

    private int status;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 614924175963809
         * name : 花生
         * storeId : 614921377337256
         * isShow : 1
         * isPat : 1
         * primaryImage : http://obqlfysk2.bkt.clouddn.com/FutLa_TP5a0pytcJdkSunhJCZNsT
         * productImage1 :
         * productImage2 :
         * productImage3 :
         * maxprice : 0.01
         * minprice : 0.01
         * brand : 花生
         * status : 0
         * salesVolume : 0
         * storeCategoryId : 614921378085891
         * goodsSpecs : [{"id":614924175963810,"goodsId":614924175963809,"platformPrice":0.01,"inventory":1000,"specaNameId":614924175321448,"specaValue":"黄色","createTime":1492417596000,"salesVolume":0,"ratePraise":0,"goodCommonts":0,"middleCommonts":0,"badCommonts":0,"anticipatedRevenue":0,"status":0}]
         * inventory : 1000
         */

        private long id;
        private String name;
        private long storeId;
        private int isShow;
        private int isPat;
        private String primaryImage;
        private String productImage1;
        private String productImage2;
        private String productImage3;
        private double maxprice;
        private double minprice;
        private String brand;
        private int status;
        private int salesVolume;
        private long storeCategoryId;
        private int inventory;
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

        public double getMaxprice() {
            return maxprice;
        }

        public void setMaxprice(double maxprice) {
            this.maxprice = maxprice;
        }

        public double getMinprice() {
            return minprice;
        }

        public void setMinprice(double minprice) {
            this.minprice = minprice;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
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

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public List<GoodsSpecsBean> getGoodsSpecs() {
            return goodsSpecs;
        }

        public void setGoodsSpecs(List<GoodsSpecsBean> goodsSpecs) {
            this.goodsSpecs = goodsSpecs;
        }

        public static class GoodsSpecsBean {
            /**
             * id : 614924175963810
             * goodsId : 614924175963809
             * platformPrice : 0.01
             * inventory : 1000
             * specaNameId : 614924175321448
             * specaValue : 黄色
             * createTime : 1492417596000
             * salesVolume : 0
             * ratePraise : 0
             * goodCommonts : 0
             * middleCommonts : 0
             * badCommonts : 0
             * anticipatedRevenue : 0.0
             * status : 0
             */

            private long id;
            private long goodsId;
            private double platformPrice;
            private int inventory;
            private long specaNameId;
            private String specaValue;
            private long createTime;
            private int salesVolume;
            private int ratePraise;
            private int goodCommonts;
            private int middleCommonts;
            private int badCommonts;
            private double anticipatedRevenue;
            private int status;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(long goodsId) {
                this.goodsId = goodsId;
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

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getSalesVolume() {
                return salesVolume;
            }

            public void setSalesVolume(int salesVolume) {
                this.salesVolume = salesVolume;
            }

            public int getRatePraise() {
                return ratePraise;
            }

            public void setRatePraise(int ratePraise) {
                this.ratePraise = ratePraise;
            }

            public int getGoodCommonts() {
                return goodCommonts;
            }

            public void setGoodCommonts(int goodCommonts) {
                this.goodCommonts = goodCommonts;
            }

            public int getMiddleCommonts() {
                return middleCommonts;
            }

            public void setMiddleCommonts(int middleCommonts) {
                this.middleCommonts = middleCommonts;
            }

            public int getBadCommonts() {
                return badCommonts;
            }

            public void setBadCommonts(int badCommonts) {
                this.badCommonts = badCommonts;
            }

            public double getAnticipatedRevenue() {
                return anticipatedRevenue;
            }

            public void setAnticipatedRevenue(double anticipatedRevenue) {
                this.anticipatedRevenue = anticipatedRevenue;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
