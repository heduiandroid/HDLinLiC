package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2016/12/28.
 *
 * {@link com.linli.consumer.net.IShopApi#goodsListOfShop}
 */

public class ShopBeanV2 extends ResVo{

    private List<MallGoodsVo> data;

    public List<MallGoodsVo> getData() {
        return data;
    }

    public void setData(List<MallGoodsVo> data) {
        this.data = data;
    }
}
