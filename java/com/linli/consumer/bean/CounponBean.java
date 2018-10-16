package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/3/18.
 */

public class CounponBean {

    /**
     * status : 0
     * msg : 优惠券获取成功！
     * data : {"id":314897335468061,"storeId":114888549601929,"storeName":"呵呵商场","couponSum":100,"couponNum":10,"kind":1,"probability":90,"type":3,"createTime":1489733546000,"status":0}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 314897335468061
         * storeId : 114888549601929
         * storeName : 呵呵商场
         * couponSum : 100.0
         * couponNum : 10
         * kind : 1
         * probability : 90.0
         * type : 3
         * createTime : 1489733546000
         * status : 0
         */

        private long id;
        private long storeId;
        private String storeName;
        private Double couponMaxSum;
        private Double couponSum;
        private int couponNum;
        private int kind;
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

        public int getKind() {
            return kind;
        }

        public void setKind(int kind) {
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
