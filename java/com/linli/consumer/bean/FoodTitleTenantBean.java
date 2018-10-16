package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/1/3.
 * {@link com.linli.consumer.net.IFoodsApiTenant}
 */

public class FoodTitleTenantBean {

    /**
     * page : null
     * status : 1
     * data : [{"id":414803236235892,"sort":1,"groupname":"abc","isdisplay":"0","bussId":1}]
     * msg : 查询成功!
     * url : null
     */

    private Object page;
    private int status;
    private String msg;
    private Object url;
    /**
     * id : 414803236235892
     * sort : 1
     * groupname : abc
     * isdisplay : 0
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
        private long id;
        private int sort;
        private String groupname;
        private String isdisplay;
        private long bussId;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }

        public String getIsdisplay() {
            return isdisplay;
        }

        public void setIsdisplay(String isdisplay) {
            this.isdisplay = isdisplay;
        }

        public long getBussId() {
            return bussId;
        }

        public void setBussId(long bussId) {
            this.bussId = bussId;
        }
    }
}
