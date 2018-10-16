package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/2/20.
 */

public class BUserInfo {

    /**
     * status : 1
     * data : {"id":414866097970864,"name":"三生三世十里桃园","wechat":"369317126","phone":"18390876375","notice":"石门欢迎你","info":"十里桃花十里陈酿","companyMemberId":914866095609478,"bankCardNo":"622700016025164892","openingBank":"建设银行","accountHolder":"白凤九","integral":0,"approveStatus":1,"categoryid":1,"createTime":1486609797000,"status":1,"openStatus":1,"opentime":3600000,"closetime":57480000,"longitude":114.47514,"latitude":38.016465,"regionId":1080,"logoImg":"http://obqlfysk2.bkt.clouddn.com/Fu8gCyS1LUFNu2ALdLUYmdriHhgJ","creditLevel":1,"typeCount":1000,"isgrab":2,"categoryType":1,"grab":1,"paytype":0,"distributiontype":0,"qrcode":"http://obqlfysk2.bkt.clouddn.com/414866097970864flag_1486609797864.png"}
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
         * id : 414866097970864
         * name : 三生三世十里桃园
         * wechat : 369317126
         * phone : 18390876375
         * notice : 石门欢迎你
         * info : 十里桃花十里陈酿
         * companyMemberId : 914866095609478
         * bankCardNo : 622700016025164892
         * openingBank : 建设银行
         * accountHolder : 白凤九
         * integral : 0
         * approveStatus : 1
         * categoryid : 1
         * createTime : 1486609797000
         * status : 1
         * openStatus : 1
         * opentime : 3600000
         * closetime : 57480000
         * longitude : 114.47514
         * latitude : 38.016465
         * regionId : 1080
         * logoImg : http://obqlfysk2.bkt.clouddn.com/Fu8gCyS1LUFNu2ALdLUYmdriHhgJ
         * creditLevel : 1
         * typeCount : 1000
         * isgrab : 2
         * categoryType : 1
         * grab : 1
         * paytype : 0
         * distributiontype : 0
         * qrcode : http://obqlfysk2.bkt.clouddn.com/414866097970864flag_1486609797864.png
         */

        private long id;
        private String name;
        private String phone;
        private String info;
        private long companyMemberId;
        private long createTime;
        private int status;
        private int regionId;
        private String logoImg;
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
    }
}
