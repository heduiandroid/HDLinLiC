package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/7/22.
 */
public class OneFreshFood_OneCate {
public OneFreshFood_OneCate(String name){
    this.name=name;
}

    private int id;

    private String name;

    private int shopid;

    private String createtime;

    private String updatetime;

    private boolean  deleted;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public int getShopid() {
        return this.shopid;
    }

    public void setCreatetime(String  createtime) {
        this.createtime = createtime;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdatetime() {
        return this.updatetime;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getDeleted() {
        return this.deleted;
    }
}
