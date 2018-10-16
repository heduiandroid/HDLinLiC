package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2016/12/20.
 */

public class ProvinceCityCounty {

    /**
     * status : 1
     * msg : null
     * page : null
     * data : [{"id":2,"parentId":1,"name":"北京","type":1,"isEnable":1},{"id":8,"parentId":1,"name":"贵州","type":1,"isEnable":1},{"id":21,"parentId":1,"name":"青海","type":1,"isEnable":1}]
     * url : null
     */

    private int status;
    private Object msg;
    private Object page;
    private Object url;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
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
        /**
         * id : 2
         * parentId : 1
         * name : 北京
         * type : 1
         * isEnable : 1
         */

        private int id;
        private int parentId;
        private String name;
        private int type;
        private int isEnable;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIsEnable() {
            return isEnable;
        }

        public void setIsEnable(int isEnable) {
            this.isEnable = isEnable;
        }
    }
}
