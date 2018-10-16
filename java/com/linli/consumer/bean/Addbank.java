package com.linli.consumer.bean;

/**
 * Created by wangpei on 2017/9/14.
 */

public class Addbank {

    /**
     * status : 1
     * msg : 创建银行账户成功!
     * data : 1
     */

    private int status;
    private String msg;
    private int data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
