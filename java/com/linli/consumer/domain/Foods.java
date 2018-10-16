package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/5/26.
 */
public class Foods {
    private Integer id;//类别ID
    private String cateName;//类别名
    public   Foods(Integer id,String cateName ){
        this.id=id;
        this.cateName=cateName;
    }

    public FragementFood getPragemnetFood() {
        return pragemnetFood;
    }

    public void setPragemnetFood(FragementFood pragemnetFood) {
        this.pragemnetFood = pragemnetFood;
    }

    private FragementFood pragemnetFood;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
