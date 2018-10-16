package com.linli.consumer.bean;

import com.linli.consumer.net.IShopApi;

import java.util.List;

/**
 * Created by tomoyo on 2016/12/28.
 *
 * {@link IShopApi#goodsListOfShop}
 */

public class ShopBeanV2_2 {


    /**
     * status : 0
     * msg : 查询成功!
     * data : [{"id":314867167494972,"name":"U盘","storeId":414846522510124,"isShow":1,"isPat":"0","primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-","maxprice":78.3,"minprice":8.6,"brand":"阿萨德","status":0,"salesVolume":0,"storeCategoryId":314848794346395,"goodsSpecs":[],"inventory":154}]
     */

    private int status;
    private String msg;
    /**
     * id : 314867167494972
     * name : U盘
     * storeId : 414846522510124
     * isShow : 1
     * isPat : 0
     * primaryImage : http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw
     * productImage2 : http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-
     * maxprice : 78.3
     * minprice : 8.6
     * brand : 阿萨德
     * status : 0
     * salesVolume : 0
     * storeCategoryId : 314848794346395
     * goodsSpecs : []
     * inventory : 154
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private long id;
        private String name;
        private long storeId;
        //private String storeName;
        private StoreNameBean storeName;
        private int isShow;
        private String isPat;
        private String primaryImage;
        private String productImage2;
        private double maxprice;
        private double minprice;
        private String brand;
        private int status;
        private int salesVolume;
        private long storeCategoryId;
        private int inventory;
        private String specName;
        private List<GoodsSpecsBean> goodsSpecs;

        private int number;        //自定义新增属性，用于本地存放数量数据
        private boolean isChoice;      //自定义新增字段，是否显示标志 true为显示(有数量),false为不显示(0)

        public StoreNameBean getStoreName() {
            return storeName;
        }

        public void setStoreName(StoreNameBean storeName) {
            this.storeName = storeName;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isChoice() {
            return isChoice;
        }

        public void setChoice(boolean choice) {
            isChoice = choice;
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

        public String getIsPat() {
            return isPat;
        }

        public void setIsPat(String isPat) {
            this.isPat = isPat;
        }

        public String getPrimaryImage() {
            return primaryImage;
        }

        public void setPrimaryImage(String primaryImage) {
            this.primaryImage = primaryImage;
        }

        public String getProductImage2() {
            return productImage2;
        }

        public void setProductImage2(String productImage2) {
            this.productImage2 = productImage2;
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

        public static class GoodsSpecsBean {
            private long id;
            private double platformPrice;
            private int inventory;
            private long specaNameId;
            private String specaValue;
            private long specbNameId;
            private String specbValue;
            private long speccNameId;
            private String speccValue;
            private int anticipatedRevenue;
            private List<?> mallGoodsSpecNames;

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

            public int getAnticipatedRevenue() {
                return anticipatedRevenue;
            }

            public void setAnticipatedRevenue(int anticipatedRevenue) {
                this.anticipatedRevenue = anticipatedRevenue;
            }

            public List<?> getMallGoodsSpecNames() {
                return mallGoodsSpecNames;
            }

            public void setMallGoodsSpecNames(List<?> mallGoodsSpecNames) {
                this.mallGoodsSpecNames = mallGoodsSpecNames;
            }
        }

        public void setGoodsSpecs(List<GoodsSpecsBean> goodsSpecs) {
            this.goodsSpecs = goodsSpecs;
        }
        public static class StoreNameBean {
            private String storeName;
            private String storeId;

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }
        }
    }
}
