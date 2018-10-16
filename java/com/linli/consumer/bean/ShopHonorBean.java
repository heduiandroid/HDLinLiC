package com.linli.consumer.bean;

/**
 * Created by tomoyo on 2017/2/12.
 */

public class ShopHonorBean {

    /**
     * status : 1
     * page : null
     * data : {"createTime":1479712601000,"parking":1,"bill":1,"reason":"呵呵哒","status":1,"sincerity":1,"id":1,"content":"1,2,3","timeoutfree":1,"name":"啊饿你","limitservice":1,"license":1,"unsubscribe":1,"storeId":1,"modifyTime":1481601418000,"regionId":1}
     * msg : 查询成功!
     * url : null
     */

    private int status;
    private Object page;
    /**
     * createTime : 1479712601000
     * parking : 1
     * bill : 1
     * reason : 呵呵哒
     * status : 1
     * sincerity : 1
     * id : 1
     * content : 1,2,3
     * timeoutfree : 1
     * name : 啊饿你
     * limitservice : 1
     * license : 1
     * unsubscribe : 1
     * storeId : 1
     * modifyTime : 1481601418000
     * regionId : 1
     */

    private DataBean data;
    private String msg;
    private Object url;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public static class DataBean {
        private long createTime;
        private long parking;
        private long bill;
        private String reason;
        private long status;
        private long sincerity;
        private long id;
        private String content;
        private long timeoutfree;
        private String name;
        private long limitservice;
        private long license;
        private long unsubscribe;
        private long storeId;
        private long modifyTime;
        private long regionId;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getParking() {
            return parking;
        }

        public void setParking(long parking) {
            this.parking = parking;
        }

        public long getBill() {
            return bill;
        }

        public void setBill(long bill) {
            this.bill = bill;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public long getSincerity() {
            return sincerity;
        }

        public void setSincerity(long sincerity) {
            this.sincerity = sincerity;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getTimeoutfree() {
            return timeoutfree;
        }

        public void setTimeoutfree(long timeoutfree) {
            this.timeoutfree = timeoutfree;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getLimitservice() {
            return limitservice;
        }

        public void setLimitservice(long limitservice) {
            this.limitservice = limitservice;
        }

        public long getLicense() {
            return license;
        }

        public void setLicense(long license) {
            this.license = license;
        }

        public long getUnsubscribe() {
            return unsubscribe;
        }

        public void setUnsubscribe(long unsubscribe) {
            this.unsubscribe = unsubscribe;
        }

        public long getStoreId() {
            return storeId;
        }

        public void setStoreId(long storeId) {
            this.storeId = storeId;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public long getRegionId() {
            return regionId;
        }

        public void setRegionId(long regionId) {
            this.regionId = regionId;
        }
    }
}
