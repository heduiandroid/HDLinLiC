package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/8/30.
 */

public class ServiceShopsBean {

    /**
     * status : 1
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":20,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":1}
     * data : [{"id":715033692278092,"name":"王直直","phone":"18235910362","notice":"哈哈哈","info":"店铺信息","companyMemberId":715033690702238,"approveStatus":1,"categoryid":715016556252256,"createTime":1503369227000,"modifyTime":1504062256000,"status":0,"openStatus":0,"opentime":43200000,"closetime":-14400000,"longitude":116.152077,"latitude":39.7164261,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FlVrIoaVyuCnAPjhU1T6uDgPy252","isgrab":2,"grab":0,"qrcode":"http://obqlfysk2.bkt.clouddn.com/715033692278092flag_1503369227827.png","wifiid":"0AQ12S09","address":"北京北京房山区白杨西路号"}]
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
         * numPerPage : 20
         * orderField :
         * orderDirection :
         * totalPage : 1
         * prePage : 1
         * nextPage : 1
         * totalCount : 1
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
         * id : 715033692278092
         * name : 王直直
         * phone : 18235910362
         * notice : 哈哈哈
         * info : 店铺信息
         * companyMemberId : 715033690702238
         * approveStatus : 1
         * categoryid : 715016556252256
         * createTime : 1503369227000
         * modifyTime : 1504062256000
         * status : 0
         * openStatus : 0
         * opentime : 43200000
         * closetime : -14400000
         * longitude : 116.152077
         * latitude : 39.7164261
         * regionId : 508
         * logoImg : http://obqlfysk2.bkt.clouddn.com/FlVrIoaVyuCnAPjhU1T6uDgPy252
         * isgrab : 2
         * grab : 0
         * qrcode : http://obqlfysk2.bkt.clouddn.com/715033692278092flag_1503369227827.png
         * wifiid : 0AQ12S09
         * address : 北京北京房山区白杨西路号
         */

        private long id;
        private String name;
        private String phone;
        private String notice;
        private String info;
        private long companyMemberId;
        private int approveStatus;
        private long categoryid;
        private long createTime;
        private long modifyTime;
        private int status;
        private int openStatus;
        private long opentime;
        private long closetime;
        private double longitude;
        private double latitude;
        private int regionId;
        private String logoImg;
        private int isgrab;
        private int grab;
        private String qrcode;
        private String wifiid;
        private String address;
        private String distance;

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public long getCompanyMemberId() {
            return companyMemberId;
        }

        public void setCompanyMemberId(long companyMemberId) {
            this.companyMemberId = companyMemberId;
        }

        public int getApproveStatus() {
            return approveStatus;
        }

        public void setApproveStatus(int approveStatus) {
            this.approveStatus = approveStatus;
        }

        public long getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(long categoryid) {
            this.categoryid = categoryid;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOpenStatus() {
            return openStatus;
        }

        public void setOpenStatus(int openStatus) {
            this.openStatus = openStatus;
        }

        public long getOpentime() {
            return opentime;
        }

        public void setOpentime(long opentime) {
            this.opentime = opentime;
        }

        public long getClosetime() {
            return closetime;
        }

        public void setClosetime(long closetime) {
            this.closetime = closetime;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public String getLogoImg() {
            return logoImg;
        }

        public void setLogoImg(String logoImg) {
            this.logoImg = logoImg;
        }

        public int getIsgrab() {
            return isgrab;
        }

        public void setIsgrab(int isgrab) {
            this.isgrab = isgrab;
        }

        public int getGrab() {
            return grab;
        }

        public void setGrab(int grab) {
            this.grab = grab;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getWifiid() {
            return wifiid;
        }

        public void setWifiid(String wifiid) {
            this.wifiid = wifiid;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
