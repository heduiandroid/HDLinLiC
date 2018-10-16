package com.linli.consumer.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class AdverPics {
    private Long id;//
    private List<String> imgSrcs;//存储显示图片地址
    private List<String> imgPaths;//点击图片跳转的地址
    private List<Long> shopIds;//点击图片跳转的商家-首页

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getImgSrcs() {
        return imgSrcs;
    }

    public void setImgSrcs(List<String> imgSrcs) {
        this.imgSrcs = imgSrcs;
    }

    public List<String> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<String> imgPaths) {
        this.imgPaths = imgPaths;
    }

    public List<Long> getShopIds() {
        return shopIds;
    }

    public void setShopIds(List<Long> shopIds) {
        this.shopIds = shopIds;
    }
}
