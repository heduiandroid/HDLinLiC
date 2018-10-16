package com.linli.consumer.domain;

import java.io.Serializable;

/**
 * Created by wwy on 2016/5/24.
 */
public class OrderHealthWay implements Serializable {
    private long id;//id
    private String name;//标题
    private int imgpath;//图片路径
    private int liulanliang;//浏览量
    private String lead;//导语
    private String label;//标签
    private String content;//标签内容
    private String time;//日期

    public OrderHealthWay(String name, int imgpath, int liulanliang, String lead, String label, String content, String time) {
        this.name = name;
        this.imgpath = imgpath;
        this.liulanliang = liulanliang;
        this.lead = lead;
        this.label = label;
        this.content = content;
        this.time = time;
    }

    public OrderHealthWay(long id, String name, int imgpath, String lead, int liulanliang, String label, String content, String time) {
        this.id = id;
        this.name = name;
        this.imgpath = imgpath;
        this.lead = lead;
        this.liulanliang = liulanliang;
        this.label = label;
        this.content = content;
        this.time = time;
    }

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

    public int getImgpath() {
        return imgpath;
    }

    public void setImgpath(int imgpath) {
        this.imgpath = imgpath;
    }

    public int getLiulanliang() {
        return liulanliang;
    }

    public void setLiulanliang(int liulanliang) {
        this.liulanliang = liulanliang;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
