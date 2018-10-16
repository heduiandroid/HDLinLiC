package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/3/18.
 */

public class EggBean {

    /**
     * status : 0
     * msg : 发蛋成功！
     * data : {"id":314898163953531,"memberId":314888496711754,"storeId":114888549601929,"type":1,"status":0}
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
         * id : 314898163953531
         * memberId : 314888496711754
         * storeId : 114888549601929
         * type : 1
         * status : 0
         */

        private long id;
        private long memberId;
        private long storeId;
        private int type;
        private int status;

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
