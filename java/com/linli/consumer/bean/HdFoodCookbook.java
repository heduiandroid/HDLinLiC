package com.linli.consumer.bean;

import java.io.Serializable;

/**
 * Created by tomoyo on 2016/12/23.
 *
 * 批量查菜品详情 传的bean
 */

public class HdFoodCookbook implements Serializable {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private Long id;

    private Long bussId;

    private String name;

    private String cuisine;

    private String maining;

    private String accessories;

    private String imgpath;

    private String makemethod;

    private Float price;

    private String status;

    private String isoutshop;

    private Integer sort;

    private Long belongtype;




}
