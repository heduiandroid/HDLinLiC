package com.linli.consumer.bean;

/**
 * Created by hasee on 2016/12/7.
 */

public class PhoneCode {

    /**
     * status : 1
     * msg : null
     * page : null
     * data : null
     * url : null
     */

    private int status;
    private Object msg;
    private Object page;
    private Object data;
    private Object url;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }
}
