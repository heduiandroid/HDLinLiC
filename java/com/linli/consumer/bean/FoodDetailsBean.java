package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2016/12/27.
 */

public class FoodDetailsBean {


    /**
     * page : null
     * status : 1
     * data : [{"belongtype":null,"sort":null,"cuisine":null,"status":null,"accessories":null,"id":214752548172876,"imgpath":"http://obqlfysk2.bkt.clouddn.com/timg.jpg","makemethod":null,"price":80,"isoutshop":null,"name":"546sdf","maining":null,"bussId":214745395500281}]
     * msg : 查询成功!
     * url : null
     */

    private Object page;
    private int status;
    private String msg;
    private Object url;
    /**
     * belongtype : null
     * sort : null
     * cuisine : null
     * status : null
     * accessories : null
     * id : 214752548172876
     * imgpath : http://obqlfysk2.bkt.clouddn.com/timg.jpg
     * makemethod : null
     * price : 80
     * isoutshop : null
     * name : 546sdf
     * maining : null
     * bussId : 214745395500281
     */

    private List<DataBean> data;

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
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

    public static class DataBean {
        private Object belongtype;
        private Object sort;
        private Object cuisine;
        private Object status;
        private Object accessories;
        private long id;
        private String imgpath;
        private Object makemethod;
        private int price;
        private Object isoutshop;
        private String name;
        private Object maining;
        private long bussId;

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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Object getIsoutshop() {
            return isoutshop;
        }

        public void setIsoutshop(Object isoutshop) {
            this.isoutshop = isoutshop;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getMaining() {
            return maining;
        }

        public void setMaining(Object maining) {
            this.maining = maining;
        }

        public long getBussId() {
            return bussId;
        }

        public void setBussId(long bussId) {
            this.bussId = bussId;
        }
    }
}
