package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/3/8.
 */

public class ShopListCategoryBean extends ResVo{

    /**
     * status : 1
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":3,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":2}
     * data : [{"id":114888505960210,"name":"啊","nickname":"王直直","phone":"18801071735","info":"123456789","companyMemberId":314888497265168,"bankCardNo":"123456789","openingBank":"邮政","accountHolder":"王直","integral":0,"approveStatus":1,"categoryid":14828949725032,"createTime":1488850596000,"status":0,"openStatus":0,"opentime":1488850596000,"longitude":116.137424,"latitude":39.715714,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/Fi_HtDhj-cdEAYDTE85gvF-LTX7N","address":"北京北京房山区白杨西路5号","typeCount":2003,"isgrab":2,"categoryType":1,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/114888505960210flag_1488850596136.png"},{"id":114888549601929,"name":"呵呵商场","nickname":"刘哈哈","phone":"15931286776","companyMemberId":314888533826256,"bankCardNo":"20552351254212","openingBank":"央行","accountHolder":"刘哈哈","integral":0,"approveStatus":1,"categoryid":14828949725032,"createTime":1488854960000,"status":0,"openStatus":0,"opentime":-14400000,"closetime":0,"longitude":116.137424,"latitude":39.715714,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FsHOQssjtRg3jwoaEO5xVhPDwXuD","address":"北京北京房山区良乡镇白杨西路5号","typeCount":200,"isgrab":2,"categoryType":1,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/114888549601929flag_1488854961355.png"}]
     */

    /**
     * id : 114888505960210
     * name : 啊
     * nickname : 王直直
     * phone : 18801071735
     * info : 123456789
     * companyMemberId : 314888497265168
     * bankCardNo : 123456789
     * openingBank : 邮政
     * accountHolder : 王直
     * integral : 0
     * approveStatus : 1
     * categoryid : 14828949725032
     * createTime : 1488850596000
     * status : 0
     * openStatus : 0
     * opentime : 1488850596000
     * longitude : 116.137424
     * latitude : 39.715714
     * regionId : 508
     * logoImg : http://obqlfysk2.bkt.clouddn.com/Fi_HtDhj-cdEAYDTE85gvF-LTX7N
     * address : 北京北京房山区白杨西路5号
     * typeCount : 2003
     * isgrab : 2
     * categoryType : 1
     * grab : 1
     * qrcode : http://obqlfysk2.bkt.clouddn.com/114888505960210flag_1488850596136.png
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean {
        private long id;
        private String name;
        private String nickname;
        private String phone;
        private String info;
        private long companyMemberId;
        private String bankCardNo;
        private String openingBank;
        private String accountHolder;
        private int integral;
        private int approveStatus;
        private long categoryid;
        private long createTime;
        private int status;
        private int openStatus;
        private long opentime;
        private double longitude;
        private double latitude;
        private int regionId;
        private String logoImg;
        private String address;
        private int typeCount;
        private int isgrab;
        private int categoryType;
        private int grab;
        private String qrcode;
        private int creditLevel;

        private String distance;    //自定义字段 距离

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(int creditLevel) {
            this.creditLevel = creditLevel;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getOpeningBank() {
            return openingBank;
        }

        public void setOpeningBank(String openingBank) {
            this.openingBank = openingBank;
        }

        public String getAccountHolder() {
            return accountHolder;
        }

        public void setAccountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getTypeCount() {
            return typeCount;
        }

        public void setTypeCount(int typeCount) {
            this.typeCount = typeCount;
        }

        public int getIsgrab() {
            return isgrab;
        }

        public void setIsgrab(int isgrab) {
            this.isgrab = isgrab;
        }

        public int getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(int categoryType) {
            this.categoryType = categoryType;
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
    }
}
