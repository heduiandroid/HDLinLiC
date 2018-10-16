package com.linli.consumer.domain;

/**
 * 商品类别信息实体
 * Created by Administrator on 2016/3/4.
 */
public class Category {
    private Integer id;//类别ID
    private String cateName;//类别名

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
