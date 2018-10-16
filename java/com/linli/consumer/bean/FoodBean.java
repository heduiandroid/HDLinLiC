package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/2/13.
 */

public class FoodBean {

    /**
     * status : 1
     * msg : 查询成功!
     * data : [{"id":314868065085464,"bussId":314868063088655,"name":"盖饭","maining":"米饭","accessories":"菜","imgpath":"http://obqlfysk2.bkt.clouddn.com/Fv8SmaeNl_IYAXoGIBbPWOZJab-F","price":16,"status":"0","isoutshop":"0","belongtype":314868064153672,"ispat":1},{"id":314868065825845,"bussId":314868063088655,"name":"土豆炖肉","maining":"肉","accessories":"土豆","imgpath":"http://obqlfysk2.bkt.clouddn.com/Fhz_agGwUhFrjdcUyB4_ZOaBB9Xi","price":20,"status":"0","isoutshop":"0","belongtype":314868064037451,"ispat":1}]
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
         * id : 314868065085464
         * bussId : 314868063088655
         * name : 盖饭
         * maining : 米饭
         * accessories : 菜
         * imgpath : http://obqlfysk2.bkt.clouddn.com/Fv8SmaeNl_IYAXoGIBbPWOZJab-F
         * price : 16.0
         * status : 0
         * isoutshop : 0
         * belongtype : 314868064153672
         * ispat : 1
         */

        private long id;
        private long bussId;
        private String name;
        private String maining;
        private String accessories;
        private String imgpath;
        private double price;
        private int status;
        private String isoutshop;
        private long belongtype;
        private int ispat;
        private int ispackagecost;      //美食专用字段，判断是否有打包费 0 : 没有     1 : 需要打包费

        public int getIsPackageCost() {
            return ispackagecost;
        }

        public void setIsPackageCost(int isPackageCost) {
            this.ispackagecost = isPackageCost;
        }

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIsoutshop() {
            return isoutshop;
        }

        public void setIsoutshop(String isoutshop) {
            this.isoutshop = isoutshop;
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
    }
}
