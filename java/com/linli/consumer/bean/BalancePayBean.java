package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/9/22.
 */

public class BalancePayBean {

    /**
     * status : 1
     * msg : 更新余额成功!
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
