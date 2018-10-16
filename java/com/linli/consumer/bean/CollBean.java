package com.linli.consumer.bean;

/**
 * Created by tomoyo on 2017/2/10.
 */

public class CollBean {

    /**
     * status : 11
     */

    private int status;     //添加收藏 1收藏成功 ;2已经收藏
                            //查询收藏 11-已经收藏 12-没有收藏

    private String msg;

    private long data;      //点赞数

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
