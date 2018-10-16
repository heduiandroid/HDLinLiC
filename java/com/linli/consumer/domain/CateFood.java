package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/5/22.
 */
public class CateFood {
    private Integer id;
    private String shopname;//商店名称
    private String introduce;//店家介绍
    private String consume;//人均消费

    public void setId(Integer id) {
        this.id = id;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setImg(Integer img) {
        Img = img;
    }

    public Integer getId() {

        return id;
    }

    public String getShopname() {
        return shopname;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getConsume() {
        return consume;
    }

    public String getDistance() {
        return distance;
    }

    public Integer getImg() {
        return Img;
    }

    private String distance;
    private Integer Img;

}
