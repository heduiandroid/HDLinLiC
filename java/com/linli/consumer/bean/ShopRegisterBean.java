package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/9/5.
 */

public class ShopRegisterBean {

    /**
     * status : 1
     * data : {"id":714930900248296,"regionId":508,"storename":"王","store_username":"王培","storepic":"http://obqlfysk2.bkt.clouddn.com/Fg2N0ilvZQEe_xAJXevsOON-zuR6","categoryId":14828949465519,"phonenum":18801071735,"adress":"北京北京房山区白杨西路5号","linkphone":18801071735,"cardno":"1234656265656565656","bankname":"邮政","accountname":"王培","storeUserid":714930900247224,"staus":1,"createTime":1493090024000,"longitude":116.137424,"latitude":39.715714,"category_type":1,"license":"http://obqlfysk2.bkt.clouddn.com/Fl_4_stmHBBFOtwob5kPcDqHETmV","idcard_front":"http://obqlfysk2.bkt.clouddn.com/Fjoj4z-VKjTMbqc798yYEG-4e9w5","idcard_back":"http://obqlfysk2.bkt.clouddn.com/Fu1k9LASq4VDnrPZXe26WIZkder1","idcard_hand":"http://obqlfysk2.bkt.clouddn.com/Fjoj4z-VKjTMbqc798yYEG-4e9w5","otherAptitude":"http://obqlfysk2.bkt.clouddn.com/Fjoj4z-VKjTMbqc798yYEG-4e9w5"}
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
         * id : 714930900248296
         * regionId : 508
         * storename : 王
         * store_username : 王培
         * storepic : http://obqlfysk2.bkt.clouddn.com/Fg2N0ilvZQEe_xAJXevsOON-zuR6
         * categoryId : 14828949465519
         * phonenum : 18801071735
         * adress : 北京北京房山区白杨西路5号
         * linkphone : 18801071735
         * cardno : 1234656265656565656
         * bankname : 邮政
         * accountname : 王培
         * storeUserid : 714930900247224
         * staus : 1
         * createTime : 1493090024000
         * longitude : 116.137424
         * latitude : 39.715714
         * category_type : 1
         * license : http://obqlfysk2.bkt.clouddn.com/Fl_4_stmHBBFOtwob5kPcDqHETmV
         * idcard_front : http://obqlfysk2.bkt.clouddn.com/Fjoj4z-VKjTMbqc798yYEG-4e9w5
         * idcard_back : http://obqlfysk2.bkt.clouddn.com/Fu1k9LASq4VDnrPZXe26WIZkder1
         * idcard_hand : http://obqlfysk2.bkt.clouddn.com/Fjoj4z-VKjTMbqc798yYEG-4e9w5
         * otherAptitude : http://obqlfysk2.bkt.clouddn.com/Fjoj4z-VKjTMbqc798yYEG-4e9w5
         */

        private long id;
        private int regionId;
        private String storename;
        private String store_username;
        private String storepic;
        private long categoryId;
        private long phonenum;
        private String adress;
        private long linkphone;
        private String cardno;
        private String bankname;
        private String accountname;
        private long storeUserid;
        private int staus;
        private long createTime;
        private double longitude;
        private double latitude;
        private int category_type;
        private String license;
        private String idcard_front;
        private String idcard_back;
        private String idcard_hand;
        private String otherAptitude;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public String getStore_username() {
            return store_username;
        }

        public void setStore_username(String store_username) {
            this.store_username = store_username;
        }

        public String getStorepic() {
            return storepic;
        }

        public void setStorepic(String storepic) {
            this.storepic = storepic;
        }

        public long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(long categoryId) {
            this.categoryId = categoryId;
        }

        public long getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(long phonenum) {
            this.phonenum = phonenum;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public long getLinkphone() {
            return linkphone;
        }

        public void setLinkphone(long linkphone) {
            this.linkphone = linkphone;
        }

        public String getCardno() {
            return cardno;
        }

        public void setCardno(String cardno) {
            this.cardno = cardno;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getAccountname() {
            return accountname;
        }

        public void setAccountname(String accountname) {
            this.accountname = accountname;
        }

        public long getStoreUserid() {
            return storeUserid;
        }

        public void setStoreUserid(long storeUserid) {
            this.storeUserid = storeUserid;
        }

        public int getStaus() {
            return staus;
        }

        public void setStaus(int staus) {
            this.staus = staus;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public int getCategory_type() {
            return category_type;
        }

        public void setCategory_type(int category_type) {
            this.category_type = category_type;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getIdcard_front() {
            return idcard_front;
        }

        public void setIdcard_front(String idcard_front) {
            this.idcard_front = idcard_front;
        }

        public String getIdcard_back() {
            return idcard_back;
        }

        public void setIdcard_back(String idcard_back) {
            this.idcard_back = idcard_back;
        }

        public String getIdcard_hand() {
            return idcard_hand;
        }

        public void setIdcard_hand(String idcard_hand) {
            this.idcard_hand = idcard_hand;
        }

        public String getOtherAptitude() {
            return otherAptitude;
        }

        public void setOtherAptitude(String otherAptitude) {
            this.otherAptitude = otherAptitude;
        }
    }
}
