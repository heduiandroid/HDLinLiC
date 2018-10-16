package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/1/13.
 */

public class AdverPosition {

    /**
     * status : 1
     * msg : 查询成功!
     * page : null
     * data : [{"id":1,"name":"拍一拍","length":0,"width":0,"areacode":1,"money":4,"createTime":1482891959000,"isEnable":false,"remark":"APP"},{"id":214828919596903,"name":"商讯","length":0,"width":0,"areacode":1,"money":4,"createTime":1482891959000,"isEnable":false,"remark":"APP"},{"id":214828919597735,"name":"首页轮播图","length":0,"width":0,"areacode":1,"money":5,"createTime":1482891959000,"isEnable":false,"remark":"APP"},{"id":214828919598166,"name":"启动屏广告","length":0,"width":0,"areacode":1,"money":0,"createTime":1482891959000,"isEnable":false,"remark":"APP"},{"id":214828919598547,"name":"首页雷达广告","length":0,"width":0,"areacode":1,"money":0,"createTime":1482891959000,"isEnable":false,"remark":"APP"},{"id":214828919598878,"name":"商家列表广告","length":0,"width":0,"areacode":1,"money":0,"createTime":1482891959000,"isEnable":false,"remark":"APP"}]
     * url : null
     */

    private int status;
    private String msg;
    private Object page;
    private Object url;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 拍一拍
         * length : 0.0
         * width : 0.0
         * areacode : 1
         * money : 4.0
         * createTime : 1482891959000
         * isEnable : false
         * remark : APP
         */

        private Long id;
        private String name;
        private double length;
        private double width;
        private int areacode;
        private double money;
        private long createTime;
        private boolean isEnable;
        private String remark;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public int getAreacode() {
            return areacode;
        }

        public void setAreacode(int areacode) {
            this.areacode = areacode;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public boolean isIsEnable() {
            return isEnable;
        }

        public void setIsEnable(boolean isEnable) {
            this.isEnable = isEnable;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
