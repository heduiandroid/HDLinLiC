package com.linli.consumer.bean;

/**
 * Created by tomoyo on 2017/3/7.
 */

public class VipUpBean extends ResVo{


    /**
     * id : 314888715602399
     * status : 1
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private long id;
        private int status;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
