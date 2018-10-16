package com.linli.consumer.domain;

import java.io.Serializable;

/**
 * 商品类别信息实体
 * Created by Administrator on 2016/3/4.
 */
public class BusinessInfo implements Serializable {
    private Long id;
    private Long storeId;
    private String title;//标题
    private String contents;//内容
    private Integer type;//类型
    private String distance;//地点
    private String date;//发布日期
    private String imagePath;
    private String url;//广告链接
    private String remark;
    private boolean isShowed;//广告是否展示过
    private int category;//广告类型  商城/美食广告
    private Integer count;//点击总数
    private Integer showCount;

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isShowed() {
        return isShowed;
    }

    public void setShowed(boolean showed) {
        isShowed = showed;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
