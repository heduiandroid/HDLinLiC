package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/7/8.
 */
public class ShopMenusign {

    private Integer Id;
    private String Name;
    private Integer shopId;
    private Integer Reorder;
    private Integer display;
    private String createTime;
    private String updateTime;
    private Integer Deleted;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getReorder() {
        return Reorder;
    }

    public void setReorder(Integer reorder) {
        Reorder = reorder;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return Deleted;
    }

    public void setDeleted(Integer deleted) {
        Deleted = deleted;
    }

}
