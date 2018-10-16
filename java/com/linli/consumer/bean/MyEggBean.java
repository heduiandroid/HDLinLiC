package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/3/20.
 */

public class MyEggBean {

    /**
     * status : 0
     * msg : 查询蛋库成功！
     * data : [{"id":314898163953531,"memberId":314888496711754,"storeId":114888549601929,"couponId":135124332343342,"storeName":"合和商场","storePath":"sss","storeType":1,"type":1,"status":0}]
     */

    private int status;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 314898163953531
         * memberId : 314888496711754
         * storeId : 114888549601929
         * couponId : 135124332343342
         * storeName : 合和商场
         * storePath : sss
         * storeType : 1
         * type : 1
         * status : 0
         */

        private Long id;
        private Long memberId;
        private Long storeId;
        private Long couponId;
        private String storeName;
        private String storePath;
        private Integer storeType;
        private int type;
        private int status;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Long getMemberId() {
            return memberId;
        }

        public void setMemberId(Long memberId) {
            this.memberId = memberId;
        }

        public Long getStoreId() {
            return storeId;
        }

        public void setStoreId(Long storeId) {
            this.storeId = storeId;
        }

        public Long getCouponId() {
            return couponId;
        }

        public void setCouponId(Long couponId) {
            this.couponId = couponId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStorePath() {
            return storePath;
        }

        public void setStorePath(String storePath) {
            this.storePath = storePath;
        }

        public Integer getStoreType() {
            return storeType;
        }

        public void setStoreType(Integer storeType) {
            this.storeType = storeType;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
