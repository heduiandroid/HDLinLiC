package com.linli.consumer.domain;

/**
 * Created by 王征 on 2016/7/9.
 */
//食品类型，比如：肉类
public class FoodType {
    public FoodType(Integer id, String foodTypeName){
        this.id=id;
        this.foodTypeName=foodTypeName;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;//类别ID

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String cateName) {
        this.foodTypeName = cateName;
    }

    private String foodTypeName;//类别名
}
