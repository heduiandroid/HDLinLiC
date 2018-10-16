package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/3/28.
 */

public class UpdateGoodsBean extends ResVo {

    /**
     * status : 1
     * data : [{"goodsId":314895422498620,"goodsName":"萨达","goodsSpecId":314895422498921,"inventory":45,"price":34,"imgPath":"http://obqlfysk2.bkt.clouddn.com/FuIKrifQgELqzIGG4PRGvzxz4E-b","storeId":114888510449949,"storeName":"美食每购"},{},{"goodsId":314888713537391,"goodsName":"阿萨德撒","goodsSpecId":314895607954713,"inventory":0,"price":23,"imgPath":"http://obqlfysk2.bkt.clouddn.com/Fi-xEXAj4lYQsrb5db_E8xU20mlU","storeId":114888510449949,"storeName":"美食每购"},{"goodsId":314895415663728,"goodsName":"似懂非懂是","goodsSpecId":314895608410944,"inventory":54,"price":32,"imgPath":"http://obqlfysk2.bkt.clouddn.com/FjMRhXZAwZvMco1CSng5RZuwgoxq","storeId":114888510449949,"storeName":"美食每购"}]
     */


    /**
     * goodsId : 314895422498620
     * goodsName : 萨达
     * goodsSpecId : 314895422498921
     * inventory : 45
     * price : 34
     * imgPath : http://obqlfysk2.bkt.clouddn.com/FuIKrifQgELqzIGG4PRGvzxz4E-b
     * storeId : 114888510449949
     * storeName : 美食每购
     */

    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private long goodsId;
        private String goodsName;
        private long goodsSpecId;
        private int inventory;
        private double price;
        private String imgPath;
        private long storeId;
        private String storeName;

        public long getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(long goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public long getGoodsSpecId() {
            return goodsSpecId;
        }

        public void setGoodsSpecId(long goodsSpecId) {
            this.goodsSpecId = goodsSpecId;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public long getStoreId() {
            return storeId;
        }

        public void setStoreId(long storeId) {
            this.storeId = storeId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }
    }
}
