package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/2/21.
 */

public class MoneyInNew {

    /**
     * status : 1
     * msg : 创建充值记录成功，请完成充值!
     * data : {"orderId":715058929628042,"createTime":1505892962804}
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
         * orderId : 715058929628042
         * createTime : 1505892962804
         */

        private Long orderId;
        private long createTime;

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
