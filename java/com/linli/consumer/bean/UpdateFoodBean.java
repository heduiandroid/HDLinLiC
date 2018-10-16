package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/3/28.
 */

public class UpdateFoodBean extends ResVo {

    /**
     * status : 1
     * msg : 查询成功!
     * data : [{"storeName":"长安饭店","hdFoodCookbook":{"id":314903448823302,"bussId":314888731062212,"name":"红酒","maining":"酒","accessories":"酒","imgpath":"http://obqlfysk2.bkt.clouddn.com/FrVwUbu2k5Ke3bV3MdQT-0WT_KZo","price":2,"status":"0","belongtype":314888746491648,"ispat":0,"ispackagecost":1}},{},{"storeName":"长安饭店","hdFoodCookbook":{"id":314903448823803,"bussId":314888731062212,"name":"红酒","maining":"酒","accessories":"酒","imgpath":"http://obqlfysk2.bkt.clouddn.com/FrVwUbu2k5Ke3bV3MdQT-0WT_KZo","price":2,"status":"0","belongtype":314888746491648,"ispat":0,"ispackagecost":1}},{"storeName":"长安饭店","hdFoodCookbook":{"id":314903448836734,"bussId":314888731062212,"name":"美酒","maining":"酒","accessories":"酒","imgpath":"http://obqlfysk2.bkt.clouddn.com/FrVwUbu2k5Ke3bV3MdQT-0WT_KZo","price":9,"status":"0","isoutshop":"1","belongtype":314888746491648,"ispat":0,"ispackagecost":1}}]
     */

    /**
     * storeName : 长安饭店
     * hdFoodCookbook : {"id":314903448823302,"bussId":314888731062212,"name":"红酒","maining":"酒","accessories":"酒","imgpath":"http://obqlfysk2.bkt.clouddn.com/FrVwUbu2k5Ke3bV3MdQT-0WT_KZo","price":2,"status":"0","belongtype":314888746491648,"ispat":0,"ispackagecost":1}
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String storeName;
        /**
         * id : 314903448823302
         * bussId : 314888731062212
         * name : 红酒
         * maining : 酒
         * accessories : 酒
         * imgpath : http://obqlfysk2.bkt.clouddn.com/FrVwUbu2k5Ke3bV3MdQT-0WT_KZo
         * price : 2
         * status : 0
         * belongtype : 314888746491648
         * ispat : 0
         * ispackagecost : 1
         */

        private HdFoodCookbookBean hdFoodCookbook;

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public HdFoodCookbookBean getHdFoodCookbook() {
            return hdFoodCookbook;
        }

        public void setHdFoodCookbook(HdFoodCookbookBean hdFoodCookbook) {
            this.hdFoodCookbook = hdFoodCookbook;
        }

        public static class HdFoodCookbookBean {
            private long id;
            private long bussId;
            private String name;
            private String maining;
            private String accessories;
            private String imgpath;
            private double price;
            private String status;
            private long belongtype;
            private int ispat;
            private int ispackagecost;

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

            public long getBelongtype() {
                return belongtype;
            }

            public void setBelongtype(long belongtype) {
                this.belongtype = belongtype;
            }

            public int getIspat() {
                return ispat;
            }

            public void setIspat(int ispat) {
                this.ispat = ispat;
            }

            public int getIspackagecost() {
                return ispackagecost;
            }

            public void setIspackagecost(int ispackagecost) {
                this.ispackagecost = ispackagecost;
            }
        }
    }
}
