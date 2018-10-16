package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/2/12.
 * 批量查询商品详情,上传参数
 */

public class IdsBean {

    private List<Long> goodIds; //商品id集合

    public List<Long> getGoodIds() {
        return goodIds;
    }

    public void setGoodIds(List<Long> goodIds) {
        this.goodIds = goodIds;
    }
}
