package com.linli.consumer.bean;

import java.io.Serializable;

/**
 * Created by tomoyo on 2016/12/28.
 */

public abstract class ResVo implements Serializable{



    private int status;     //返回状态 1: success   2,3,4,5,6:failure
    private String msg;
    private Object page;
    private String url;

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

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
