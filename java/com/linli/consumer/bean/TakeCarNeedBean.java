package com.linli.consumer.bean;

/**
 * Created by hasee on 2018/4/18.
 */

public class TakeCarNeedBean {


    /**
     * status : 1
     * data : {"id":715240499377771,"startingPoint":"北京市房山区良乡东路","endPoint":"发展大厦","startingLog":116.159096,"startingLng":39.735739,"endLog":116.467263,"endLng":39.957388,"status":1,"menberId":714931894383297,"createTime":1524049937000,"deleted":2}
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
         * id : 715240499377771
         * startingPoint : 北京市房山区良乡东路
         * endPoint : 发展大厦
         * startingLog : 116.159096
         * startingLng : 39.735739
         * endLog : 116.467263
         * endLng : 39.957388
         * status : 1
         * menberId : 714931894383297
         * createTime : 1524049937000
         * deleted : 2
         */

        private Long id;
        private String startingPoint;
        private String endPoint;
        private Double startingLog;
        private Double startingLng;
        private Double endLog;
        private Double endLng;
        private Integer status;
        private Integer travelState;
        private Long menberId;
        private Long driverId;
        private Long createTime;
        private Integer deleted;
        private Integer type;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getStartingPoint() {
            return startingPoint;
        }

        public void setStartingPoint(String startingPoint) {
            this.startingPoint = startingPoint;
        }

        public String getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(String endPoint) {
            this.endPoint = endPoint;
        }

        public Double getStartingLog() {
            return startingLog;
        }

        public void setStartingLog(Double startingLog) {
            this.startingLog = startingLog;
        }

        public Double getStartingLng() {
            return startingLng;
        }

        public void setStartingLng(Double startingLng) {
            this.startingLng = startingLng;
        }

        public Double getEndLog() {
            return endLog;
        }

        public void setEndLog(Double endLog) {
            this.endLog = endLog;
        }

        public Double getEndLng() {
            return endLng;
        }

        public void setEndLng(Double endLng) {
            this.endLng = endLng;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getTravelState() {
            return travelState;
        }

        public void setTravelState(Integer travelState) {
            this.travelState = travelState;
        }

        public Long getMenberId() {
            return menberId;
        }

        public void setMenberId(Long menberId) {
            this.menberId = menberId;
        }

        public Long getDriverId() {
            return driverId;
        }

        public void setDriverId(Long driverId) {
            this.driverId = driverId;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Integer getDeleted() {
            return deleted;
        }

        public void setDeleted(Integer deleted) {
            this.deleted = deleted;
        }
    }
}
