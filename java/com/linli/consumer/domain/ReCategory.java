package com.linli.consumer.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/6/6.
 */
public class ReCategory {
    private Integer id;
    private String name;
    private List<Category> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }
}
