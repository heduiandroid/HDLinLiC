package com.linli.consumer.bean;

/**
 * Created by hasee on 2018/4/25.
 */

public class DriverPosBean {

    /**
     * status : 1
     * data : {"id":715244471363142,"driverId":715241259645621,"log":116.151335,"lng":39.716919,"createTime":1524447136000}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 715244471363142
         * driverId : 715241259645621
         * log : 116.151335
         * lng : 39.716919
         * createTime : 1524447136000
         */

        private Long id;
        private Long driverId;
        private Double log;
        private Double lng;
        private Long createTime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getDriverId() {
            return driverId;
        }

        public void setDriverId(Long driverId) {
            this.driverId = driverId;
        }

        public Double getLog() {
            return log;
        }

        public void setLog(Double log) {
            this.log = log;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }
    }
}
