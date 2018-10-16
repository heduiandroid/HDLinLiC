package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2016/12/28.
 * {@link com.linli.consumer.net.IShopApi#goodsSortOfShop(String)}
 */

public class ShopSortBean {
    /**
     * status : 1
     * msg : null
     * page : null
     * data : [{"id":214745401038562,"storeId":214745395500281,"name":"纯可可脂","sortNo":1,"createTime":1474540103000},{"id":214752248132239,"storeId":214745395500281,"name":"香油猪皮","sortNo":1,"createTime":null},{"id":214752547141504,"storeId":214745395500281,"name":"凤梨酥","sortNo":3,"createTime":1475254714000},{"id":214752559552149,"storeId":214745395500281,"name":"绿豆糕","sortNo":4,"createTime":1475255955000},{"id":314817099471939,"storeId":214745395500281,"name":"香蕉类个巴拉s","sortNo":2,"createTime":null},{"id":314817614227961,"storeId":214745395500281,"name":"香蕉牛奶","sortNo":10,"createTime":1481761422000},{"id":314817634934252,"storeId":214745395500281,"name":"胖胖","sortNo":666,"createTime":1481763493000},{"id":314817709562513,"storeId":214745395500281,"name":"胖胖向前冲","sortNo":9,"createTime":1481770956000}]
     * url : null
     */

    private int status;
    private Object msg;
    private Object page;
    private Object url;
    /**
     * id : 214745401038562
     * storeId : 214745395500281
     * name : 纯可可脂
     * sortNo : 1
     * createTime : 1474540103000
     */

    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
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
        private long id;
        private long storeId;
        private String name;
        private long sortNo;
        private long createTime;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getStoreId() {
            return storeId;
        }

        public void setStoreId(long storeId) {
            this.storeId = storeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getSortNo() {
            return sortNo;
        }

        public void setSortNo(long sortNo) {
            this.sortNo = sortNo;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
