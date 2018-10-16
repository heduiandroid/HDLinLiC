package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/3/24.
 */

public class NotifyUrlBean {

    /**
     * status : 1
     * data : http://60.205.56.3:8080/gate/notifyUrlAliPay
     */

    private int status;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
