package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/2/23.
 */

public class VersionInfo {

    /**
     * status : 1
     * msg : 版本号
     * data : Bate u_17.1.3.2.0.0
     */

    private int status;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
