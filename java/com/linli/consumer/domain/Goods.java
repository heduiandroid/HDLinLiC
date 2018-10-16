package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/7/21.
 * Summary:
 */
public class Goods {
    public Goods(int imageid, String name, String price){
        this.imageid=imageid;
        this.name=name;
        this.price=price;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private int imageid;
    private String name;
    private String price;
}
