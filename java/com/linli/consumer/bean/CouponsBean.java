package com.linli.consumer.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hasee on 2017/3/17.
 */

public class CouponsBean {


    /**
     * status : 0
     * msg : 查询优惠券成功！
     * data : [{"id":314899948835632,"memberId":314888496711754,"storeId":114888549601929,"storeName":"呵呵商场","batchId":314899948835228,"couponSum":100,"couponNum":5,"kind":3,"probability":100,"type":1,"createTime":1489994883000,"status":1}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 314899948835632
         * memberId : 314888496711754
         * storeId : 114888549601929
         * storeName : 呵呵商场
         * batchId : 314899948835228
         * couponSum : 100.0
         * couponNum : 5
         * kind : 3
         * probability : 100.0
         * type : 1
         * createTime : 1489994883000
         * status : 1
         */

        private long id;
        private long memberId;
        private long storeId;
        private String storeName;
        private long batchId;
        private Double couponMaxSum;
        private Double couponSum;
        private int couponNum;
        private Integer kind;
        private double probability;
        private int type;
        private long createTime;
        private int status;

        public Double getCouponMaxSum() {
            return couponMaxSum;
        }

        public void setCouponMaxSum(Double couponMaxSum) {
            this.couponMaxSum = couponMaxSum;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getMemberId() {
            return memberId;
        }

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }

        public long getStoreId() {
            return storeId;
        }

        public void setStoreId(long storeId) {
            this.storeId = storeId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public long getBatchId() {
            return batchId;
        }

        public void setBatchId(long batchId) {
            this.batchId = batchId;
        }

        public Double getCouponSum() {
            return couponSum;
        }

        public void setCouponSum(Double couponSum) {
            this.couponSum = couponSum;
        }

        public int getCouponNum() {
            return couponNum;
        }

        public void setCouponNum(int couponNum) {
            this.couponNum = couponNum;
        }

        public Integer getKind() {
            return kind;
        }

        public void setKind(Integer kind) {
            this.kind = kind;
        }

        public double getProbability() {
            return probability;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
