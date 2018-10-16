package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/2/13.
 * 1,同步数据接口
 * 2,通过标签查询商品接口
 */

public class GoodsDetailListBean extends ResVo {

    private List<GoodsDetailBean> data;

    public List<GoodsDetailBean> getData() {
        return data;
    }

    public void setData(List<GoodsDetailBean> data) {
        this.data = data;
    }
}
