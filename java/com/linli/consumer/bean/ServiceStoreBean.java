package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/8/28.
 */

public class ServiceStoreBean {

    /**
     * status : 1
     * data : {"id":715033692278092,"name":"王直直啊","phone":"18235910362","notice":"哈哈哈","info":"店铺信息","companyMemberId":715033690702238,"approveStatus":1,"createTime":1503369227000,"modifyTime":1503904776000,"status":0,"openStatus":0,"opentime":43200000,"closetime":-14400000,"longitude":116.152077,"latitude":39.7164261,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FlVrIoaVyuCnAPjhU1T6uDgPy252","isgrab":2,"grab":0,"qrcode":"http://obqlfysk2.bkt.clouddn.com/715033692278092flag_1503369227827.png","wifiid":"0AQ12S09","address":"北京北京房山区白杨西路号"}
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
         * id : 715033692278092
         * name : 王直直啊
         * phone : 18235910362
         * notice : 哈哈哈
         * info : 店铺信息
         * companyMemberId : 715033690702238
         * approveStatus : 1
         * createTime : 1503369227000
         * modifyTime : 1503904776000
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
        private long createTime;
        private long modifyTime;
        private int status;
        private int openStatus;
        private long opentime;
        private long closetime;
        private double longitude;
        private double latitude;
        private long regionId;
        private String logoImg;
        private int isgrab;
        private int grab;
        private String qrcode;
        private String wifiid;
        private String address;
        private String backImg1;
        private String backImg2;
        private String backImg3;
        private String backImg4;
        private String mobilePhone;

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getBackImg1() {
            return backImg1;
        }

        public void setBackImg1(String backImg1) {
            this.backImg1 = backImg1;
        }

        public String getBackImg2() {
            return backImg2;
        }

        public void setBackImg2(String backImg2) {
            this.backImg2 = backImg2;
        }

        public String getBackImg3() {
            return backImg3;
        }

        public void setBackImg3(String backImg3) {
            this.backImg3 = backImg3;
        }

        public String getBackImg4() {
            return backImg4;
        }

        public void setBackImg4(String backImg4) {
            this.backImg4 = backImg4;
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

        public long getRegionId() {
            return regionId;
        }

        public void setRegionId(long regionId) {
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
