package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/1/3.
 * {@link com.linli.consumer.net.IFoodsApiTenant#foodListOfSHop}
 * {@link com.linli.consumer.net.IFoodsApi#foodDetail}
 */

public class FoodListBean {

    /**
     * page : null
     * status : 1
     * data : [{"belongtype":1,"sort":null,"cuisine":null,"status":null,"accessories":null,"id":1,"imgpath":null,"makemethod":null,"price":null,"isoutshop":null,"name":"ddddd","maining":null,"bussId":1}]
     * msg : 查询成功!
     * url : null
     */

    private Object page;
    private int status;
    private String msg;
    private Object url;
    /**
     * belongtype : 1
     * sort : null
     * cuisine : null
     * status : null
     * accessories : null
     * id : 1
     * imgpath : null
     * makemethod : null
     * price : null
     * isoutshop : null
     * name : ddddd
     * maining : null
     * bussId : 1
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
        private long belongtype;
        private Object sort;
        private Object cuisine;
        private Object status;
        private Object accessories;
        private long id;
        private String imgpath;
        private Object makemethod;
        private double price;
        private Object isoutshop;
        private String name;
        private Object maining;
        private long bussId;
        private String storeName;
        private int ispackagecost;      //0:不需要  1:需要  打包费



        private int number;     //自定义字段,数量
        private boolean isShow; //自定义新增字段，是否显示标志 true为显示(有数量),false为不显示(0)

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public long getBelongtype() {
            return belongtype;
        }

        public void setBelongtype(long belongtype) {
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
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

        public int getIspackagecost() {
            return ispackagecost;
        }

        public void setIspackagecost(int ispackagecost) {
            this.ispackagecost = ispackagecost;
        }
    }
}
