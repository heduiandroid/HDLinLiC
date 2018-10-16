package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2016/12/28.
 *
 * {@link com.linli.consumer.net.IShopApi#shopRecommend}
 */

public class ShopListBean {

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
    private Object url;

    private List<ShopVo> data;

    public List<ShopVo> getData() {
        return data;
    }

    public void setData(List<ShopVo> list) {
        this.data = list;
    }

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
}
