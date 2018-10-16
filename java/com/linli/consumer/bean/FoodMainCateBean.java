package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/9/1.
 */

public class FoodMainCateBean {

    /**
     * status : 1
     * data : [{"id":715042287178033,"name":"酒店/饭馆","parentId":14828947671413,"sort":1,"isShow":0,"status":0,"createTime":1504228717000},{"id":715042287354785,"name":"快餐/小吃","parentId":14828947671413,"sort":2,"isShow":0,"status":0,"createTime":1504228735000},{"id":715042287641327,"name":"酒吧/茶馆/咖啡馆","parentId":14828947671413,"sort":3,"isShow":0,"status":0,"createTime":1504228764000}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 715042287178033
         * name : 酒店/饭馆
         * parentId : 14828947671413
         * sort : 1
         * isShow : 0
         * status : 0
         * createTime : 1504228717000
         */

        private long id;
        private String name;
        private long parentId;
        private int sort;
        private int isShow;
        private int status;
        private long createTime;

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

        public long getParentId() {
            return parentId;
        }

        public void setParentId(long parentId) {
            this.parentId = parentId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
