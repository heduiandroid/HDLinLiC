package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2018/4/17.
 */

public class TakeCarNeedListBean {

    /**
     * status : 1
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":10,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":2}
     * data : [{"id":715239481307791,"startingPoint":"北京市房山区良乡东路","endPoint":"良乡医院-公交车站","startingLog":116.159096,"startingLng":39.735739,"endLog":116.148496,"endLng":39.742519,"status":1,"travelState":1,"menberId":714931894383297,"driverId":714931894386632,"createTime":1523948130000,"deleted":2},{"id":715239481938162,"startingPoint":"北京市房山区良乡东路","endPoint":"良乡医院-公交车站","startingLog":116.159096,"startingLng":39.735739,"endLog":116.148496,"endLng":39.742519,"status":1,"travelState":1,"menberId":714931894383297,"driverId":714931894385242,"createTime":1523948193000,"deleted":2}]
     */

    private int status;
    private PageBean page;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * plainPageNum : 1
         * pageNum : 1
         * numPerPage : 10
         * orderField :
         * orderDirection :
         * totalPage : 1
         * prePage : 1
         * nextPage : 1
         * totalCount : 2
         */

        private int plainPageNum;
        private int pageNum;
        private int numPerPage;
        private String orderField;
        private String orderDirection;
        private int totalPage;
        private int prePage;
        private int nextPage;
        private int totalCount;

        public int getPlainPageNum() {
            return plainPageNum;
        }

        public void setPlainPageNum(int plainPageNum) {
            this.plainPageNum = plainPageNum;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getNumPerPage() {
            return numPerPage;
        }

        public void setNumPerPage(int numPerPage) {
            this.numPerPage = numPerPage;
        }

        public String getOrderField() {
            return orderField;
        }

        public void setOrderField(String orderField) {
            this.orderField = orderField;
        }

        public String getOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(String orderDirection) {
            this.orderDirection = orderDirection;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class DataBean {
        /**
         * id : 715239481307791
         * startingPoint : 北京市房山区良乡东路
         * endPoint : 良乡医院-公交车站
         * startingLog : 116.159096
         * startingLng : 39.735739
         * endLog : 116.148496
         * endLng : 39.742519
         * status : 1
         * travelState : 1
         * menberId : 714931894383297
         * driverId : 714931894386632
         * createTime : 1523948130000
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
        private String voicePath;

        public String getVoicePath() {
            return voicePath;
        }

        public void setVoicePath(String voicePath) {
            this.voicePath = voicePath;
        }

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
