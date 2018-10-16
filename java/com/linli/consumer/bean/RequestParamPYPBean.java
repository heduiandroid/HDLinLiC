package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/2/14.
 */

public class RequestParamPYPBean {

    /**
     * numPerPage : 10
     * pageNum : 1
     * storeId : 414824562913762
     */

    private int numPerPage;
    private int pageNum;
    private long storeId;
    private int isShow;

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }
}
