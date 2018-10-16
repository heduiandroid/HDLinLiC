package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/2/11.
 */

public class test {

    /**
     * status : 1
     * msg : 查询商品成功
     * data : {"id":914848018958174,"name":"跑鞋","storeId":414824562913762,"isShow":1,"isPat":"1","primaryImage":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage1":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage2":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage3":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","inventory":66,"approveStatus":0,"brand":"飞人凤凰","status":0,"salesVolume":0,"storeCategoryId":914842227115023,"goodsSpecs":[{"platformPrice":8.6,"inventory":66,"mallGoodsSpecNames":[],"specaNameId":914844067623503,"specaValue":"规格值1","specbNameId":914844067623503,"specbValue":"规格值2","speccNameId":914844067623503,"speccValue":"规格值3","anticipatedRevenue":0},{"platformPrice":78.3,"inventory":88,"mallGoodsSpecNames":[],"specaNameId":914844067623503,"specaValue":"规格值1-1","specbNameId":914844067623503,"specbValue":"规格值2-1","speccNameId":914844067623503,"speccValue":"规格值3-1","anticipatedRevenue":0}],"goodsType":0}
     */

    private int status;
    private String msg;
    /**
     * id : 914848018958174
     * name : 跑鞋
     * storeId : 414824562913762
     * isShow : 1
     * isPat : 1
     * primaryImage : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
     * productImage1 : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
     * productImage2 : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
     * productImage3 : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
     * inventory : 66
     * approveStatus : 0
     * brand : 飞人凤凰
     * status : 0
     * salesVolume : 0
     * storeCategoryId : 914842227115023
     * goodsSpecs : [{"platformPrice":8.6,"inventory":66,"mallGoodsSpecNames":[],"specaNameId":914844067623503,"specaValue":"规格值1","specbNameId":914844067623503,"specbValue":"规格值2","speccNameId":914844067623503,"speccValue":"规格值3","anticipatedRevenue":0},{"platformPrice":78.3,"inventory":88,"mallGoodsSpecNames":[],"specaNameId":914844067623503,"specaValue":"规格值1-1","specbNameId":914844067623503,"specbValue":"规格值2-1","speccNameId":914844067623503,"speccValue":"规格值3-1","anticipatedRevenue":0}]
     * goodsType : 0
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private long id;
        private String name;
        private long storeId;
        private int isShow;
        private String isPat;
        private String primaryImage;
        private String productImage1;
        private String productImage2;
        private String productImage3;
        private int inventory;
        private int approveStatus;
        private String brand;
        private int status;
        private int salesVolume;
        private long storeCategoryId;
        private int goodsType;
        /**
         * platformPrice : 8.6
         * inventory : 66
         * mallGoodsSpecNames : []
         * specaNameId : 914844067623503
         * specaValue : 规格值1
         * specbNameId : 914844067623503
         * specbValue : 规格值2
         * speccNameId : 914844067623503
         * speccValue : 规格值3
         * anticipatedRevenue : 0
         */

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

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
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
    }
}
