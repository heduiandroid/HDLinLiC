package com.linli.consumer.domain;

/**
 * Created by wwy on 2016/5/21.
 */
public class FoodSearchResult {
    private long id;//id
    private String name;//菜名
    private String imgpath;//图片路径
    private String maining;//主料
    private String accessories;//辅料


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getMaining() {
        return maining;
    }

    public void setMaining(String maining) {
        this.maining = maining;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }
}
