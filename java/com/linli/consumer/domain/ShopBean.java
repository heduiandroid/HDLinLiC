package com.linli.consumer.domain;

import java.util.List;

/**
 * Created by tomoyo on 2016/11/4.
 */

public class ShopBean {
    /**
     * code : 0
     * desc : null
     * data : {"pageNo":1,"pageSize":8,"pageCount":1,"total":7,"rows":[{"id":20,"userid":36,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"零售店1号","address":"白杨西路5号","category":2,"linkman":"zhang","linkphone":"15833334127","ordertel":null,"orderphone":null,"type":null,"cuisine":null,"shophours":"23","consume":null,"parking":null,"bill":null,"destine":null,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":null,"imagesrc":"http://obqlfysk2.bkt.clouddn.com/20shopImg.jpg","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":null,"temporaryReason":null,"qrcode":"http://obqlfysk2.bkt.clouddn.com/1014flag_1474629725460.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":"68:5d:43:d1:01:e2","bankusername":"zhang","bankcard":"1111111111111111","bankname":"中国邮政","createtime":1474270113000,"updatetime":1474633678000,"deleted":false},{"id":21,"userid":37,"longitude":"116.1516257835364","latitude":"39.71676621218819","shopname":"订餐","address":"北京市房山区良乡经济开发区白杨路5号","category":1,"linkman":"张海帅","linkphone":"158111320","ordertel":"15811132039","orderphone":"15811132039","type":0,"cuisine":0,"shophours":"3333","consume":"100","parking":1,"bill":1,"destine":0,"license":null,"sincerity":true,"unsubscribe":true,"timeoutFree":true,"limitService":null,"introduce":"s","imagesrc":"http://obqlfysk2.bkt.clouddn.com/FmlcHN0EkW4HL6yh8chnmoE4x9Yz","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":"1","temporaryReason":"{reason=, plan=1, 2, 3, 4, 5, 6, 7}","qrcode":"http://obqlfysk2.bkt.clouddn.com/1015flag_1474631434428.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":"","bankusername":"小燕子","bankcard":"123456789","bankname":"中国农业银行","createtime":1474270230000,"updatetime":1476947777000,"deleted":false},{"id":22,"userid":39,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"直直直丶丶","address":"北京房山区白杨西路5号","category":1,"linkman":"王培","linkphone":"1880107175","ordertel":"110","orderphone":"110","type":0,"cuisine":2,"shophours":"24小时","consume":"个","parking":null,"bill":null,"destine":1,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":"少时诵诗书","imagesrc":"http://obqlfysk2.bkt.clouddn.com/FlFxkB5gND2sKekbi0xP9-Ev6vk1","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":"0","temporaryReason":"{reason=, plan=1, 2, 3, 4, 5}","qrcode":"http://obqlfysk2.bkt.clouddn.com/1015flag_1474631530133.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":"252","boxid":null,"wifiid":"","bankusername":"杜二二大","bankcard":"823573272","bankname":"额巨大萨尔","createtime":1474271778000,"updatetime":1474633711000,"deleted":false},{"id":27,"userid":54,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"树鹏珠宝行","address":"北京市房山区良乡经济开发二期白杨西路5号","category":2,"linkman":"邢树鹏","linkphone":"15718843379","ordertel":"40009874561","orderphone":"11111111111","type":null,"cuisine":null,"shophours":null,"consume":null,"parking":null,"bill":null,"destine":null,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":null,"imagesrc":"http://obqlfysk2.bkt.clouddn.com/27shopImg.jpg?v=0.7635661299021901","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":null,"temporaryReason":null,"qrcode":"http://obqlfysk2.bkt.clouddn.com/1016flag_1474632032407.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":null,"bankusername":"邢树鹏","bankcard":"6234567891111111","bankname":"中国人民银行","createtime":1474335762000,"updatetime":1474633753000,"deleted":false},{"id":28,"userid":55,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"罗大人烤肉店","address":"详细地址","category":2,"linkman":"罗帅","linkphone":"联系人手机号","ordertel":"18042690712","orderphone":"18042690712","type":null,"cuisine":null,"shophours":null,"consume":null,"parking":null,"bill":null,"destine":null,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":null,"imagesrc":"http://obqlfysk2.bkt.clouddn.com/图片地址","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":null,"temporaryReason":null,"qrcode":"店铺二维码","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":false,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":null,"bankusername":"罗帅","bankcard":"123456","bankname":"中国银行","createtime":1474353754000,"updatetime":1474633763000,"deleted":false},{"id":29,"userid":60,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"薛靖","address":"白杨西路5号","category":1,"linkman":"薛靖","linkphone":"15011583865","ordertel":"12374567","orderphone":"15011583865","type":null,"cuisine":null,"shophours":null,"consume":null,"parking":null,"bill":null,"destine":null,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":null,"imagesrc":"http://obqlfysk2.bkt.clouddn.com/29shopImg.png?v=0.48738660363852626?v=0.3869442434163056","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":"0","temporaryReason":"{reason=, plan=1, 2, 3, 4, 5, 6, 7}","qrcode":"http://obqlfysk2.bkt.clouddn.com/1016flag_1474632163443.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":null,"bankusername":"薛靖","bankcard":"123","bankname":"和兑银行","createtime":1474417386000,"updatetime":1474633781000,"deleted":false},{"id":35,"userid":69,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"美食美购","address":"北京市房山区白杨西路","category":1,"linkman":"用中枪","linkphone":"18310013577","ordertel":"123456","orderphone":"18310013577","type":null,"cuisine":null,"shophours":"8:00-12:00","consume":null,"parking":null,"bill":null,"destine":null,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":"暂无让他","imagesrc":"http://obqlfysk2.bkt.clouddn.com/Fuu12y83BjMc05u4WD06YlkSPYzT","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":null,"temporaryReason":null,"qrcode":"http://obqlfysk2.bkt.clouddn.com/1016flag_1474633492838.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":null,"bankusername":"杨忠强","bankcard":"123456","bankname":"邯郸银行","createtime":1474430117000,"updatetime":1474633813000,"deleted":false}],"startNo":1,"endNo":7,"prePage":1,"nextPage":1}
     */

    private int code;
    private Object desc;
    /**
     * pageNo : 1
     * pageSize : 8
     * pageCount : 1
     * total : 7
     * rows : [{"id":20,"userid":36,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"零售店1号","address":"白杨西路5号","category":2,"linkman":"zhang","linkphone":"15833334127","ordertel":null,"orderphone":null,"type":null,"cuisine":null,"shophours":"23","consume":null,"parking":null,"bill":null,"destine":null,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":null,"imagesrc":"http://obqlfysk2.bkt.clouddn.com/20shopImg.jpg","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":null,"temporaryReason":null,"qrcode":"http://obqlfysk2.bkt.clouddn.com/1014flag_1474629725460.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":"68:5d:43:d1:01:e2","bankusername":"zhang","bankcard":"1111111111111111","bankname":"中国邮政","createtime":1474270113000,"updatetime":1474633678000,"deleted":false},{"id":21,"userid":37,"longitude":"116.1516257835364","latitude":"39.71676621218819","shopname":"订餐","address":"北京市房山区良乡经济开发区白杨路5号","category":1,"linkman":"张海帅","linkphone":"158111320","ordertel":"15811132039","orderphone":"15811132039","type":0,"cuisine":0,"shophours":"3333","consume":"100","parking":1,"bill":1,"destine":0,"license":null,"sincerity":true,"unsubscribe":true,"timeoutFree":true,"limitService":null,"introduce":"s","imagesrc":"http://obqlfysk2.bkt.clouddn.com/FmlcHN0EkW4HL6yh8chnmoE4x9Yz","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":"1","temporaryReason":"{reason=, plan=1, 2, 3, 4, 5, 6, 7}","qrcode":"http://obqlfysk2.bkt.clouddn.com/1015flag_1474631434428.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":"","bankusername":"小燕子","bankcard":"123456789","bankname":"中国农业银行","createtime":1474270230000,"updatetime":1476947777000,"deleted":false},{"id":22,"userid":39,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"直直直丶丶","address":"北京房山区白杨西路5号","category":1,"linkman":"王培","linkphone":"1880107175","ordertel":"110","orderphone":"110","type":0,"cuisine":2,"shophours":"24小时","consume":"个","parking":null,"bill":null,"destine":1,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":"少时诵诗书","imagesrc":"http://obqlfysk2.bkt.clouddn.com/FlFxkB5gND2sKekbi0xP9-Ev6vk1","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":"0","temporaryReason":"{reason=, plan=1, 2, 3, 4, 5}","qrcode":"http://obqlfysk2.bkt.clouddn.com/1015flag_1474631530133.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":"252","boxid":null,"wifiid":"","bankusername":"杜二二大","bankcard":"823573272","bankname":"额巨大萨尔","createtime":1474271778000,"updatetime":1474633711000,"deleted":false},{"id":27,"userid":54,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"树鹏珠宝行","address":"北京市房山区良乡经济开发二期白杨西路5号","category":2,"linkman":"邢树鹏","linkphone":"15718843379","ordertel":"40009874561","orderphone":"11111111111","type":null,"cuisine":null,"shophours":null,"consume":null,"parking":null,"bill":null,"destine":null,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":null,"imagesrc":"http://obqlfysk2.bkt.clouddn.com/27shopImg.jpg?v=0.7635661299021901","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":null,"temporaryReason":null,"qrcode":"http://obqlfysk2.bkt.clouddn.com/1016flag_1474632032407.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":null,"bankusername":"邢树鹏","bankcard":"6234567891111111","bankname":"中国人民银行","createtime":1474335762000,"updatetime":1474633753000,"deleted":false},{"id":28,"userid":55,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"罗大人烤肉店","address":"详细地址","category":2,"linkman":"罗帅","linkphone":"联系人手机号","ordertel":"18042690712","orderphone":"18042690712","type":null,"cuisine":null,"shophours":null,"consume":null,"parking":null,"bill":null,"destine":null,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":null,"imagesrc":"http://obqlfysk2.bkt.clouddn.com/图片地址","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":null,"temporaryReason":null,"qrcode":"店铺二维码","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":false,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":null,"bankusername":"罗帅","bankcard":"123456","bankname":"中国银行","createtime":1474353754000,"updatetime":1474633763000,"deleted":false},{"id":29,"userid":60,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"薛靖","address":"白杨西路5号","category":1,"linkman":"薛靖","linkphone":"15011583865","ordertel":"12374567","orderphone":"15011583865","type":null,"cuisine":null,"shophours":null,"consume":null,"parking":null,"bill":null,"destine":null,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":null,"imagesrc":"http://obqlfysk2.bkt.clouddn.com/29shopImg.png?v=0.48738660363852626?v=0.3869442434163056","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":"0","temporaryReason":"{reason=, plan=1, 2, 3, 4, 5, 6, 7}","qrcode":"http://obqlfysk2.bkt.clouddn.com/1016flag_1474632163443.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":null,"bankusername":"薛靖","bankcard":"123","bankname":"和兑银行","createtime":1474417386000,"updatetime":1474633781000,"deleted":false},{"id":35,"userid":69,"longitude":"116.13742432171215","latitude":"39.71571430646427","shopname":"美食美购","address":"北京市房山区白杨西路","category":1,"linkman":"用中枪","linkphone":"18310013577","ordertel":"123456","orderphone":"18310013577","type":null,"cuisine":null,"shophours":"8:00-12:00","consume":null,"parking":null,"bill":null,"destine":null,"license":null,"sincerity":null,"unsubscribe":null,"timeoutFree":null,"limitService":null,"introduce":"暂无让他","imagesrc":"http://obqlfysk2.bkt.clouddn.com/Fuu12y83BjMc05u4WD06YlkSPYzT","openstatus":3,"postalcode":"102400","paykind":"0,1","istakeout":null,"shopopen":1,"shopclose":0,"temporary":0,"temporaryTime":null,"temporaryReason":null,"qrcode":"http://obqlfysk2.bkt.clouddn.com/1016flag_1474633492838.png","provice":"北京","city":"北京","country":"房山区","assign":true,"grab":true,"shopcarouse":null,"areacode":"110111","customservice":null,"boxid":null,"wifiid":null,"bankusername":"杨忠强","bankcard":"123456","bankname":"邯郸银行","createtime":1474430117000,"updatetime":1474633813000,"deleted":false}]
     * startNo : 1
     * endNo : 7
     * prePage : 1
     * nextPage : 1
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int pageNo;
        private int pageSize;
        private int pageCount;
        private int total;
        private int startNo;
        private int endNo;
        private int prePage;
        private int nextPage;
        /**
         * id : 20
         * userid : 36
         * longitude : 116.13742432171215
         * latitude : 39.71571430646427
         * shopname : 零售店1号
         * address : 白杨西路5号
         * category : 2
         * linkman : zhang
         * linkphone : 15833334127
         * ordertel : null
         * orderphone : null
         * type : null
         * cuisine : null
         * shophours : 23
         * consume : null
         * parking : null
         * bill : null
         * destine : null
         * license : null
         * sincerity : null
         * unsubscribe : null
         * timeoutFree : null
         * limitService : null
         * introduce : null
         * imagesrc : http://obqlfysk2.bkt.clouddn.com/20shopImg.jpg
         * openstatus : 3
         * postalcode : 102400
         * paykind : 0,1
         * istakeout : null
         * shopopen : 1
         * shopclose : 0
         * temporary : 0
         * temporaryTime : null
         * temporaryReason : null
         * qrcode : http://obqlfysk2.bkt.clouddn.com/1014flag_1474629725460.png
         * provice : 北京
         * city : 北京
         * country : 房山区
         * assign : true
         * grab : true
         * shopcarouse : null
         * areacode : 110111
         * customservice : null
         * boxid : null
         * wifiid : 68:5d:43:d1:01:e2
         * bankusername : zhang
         * bankcard : 1111111111111111
         * bankname : 中国邮政
         * createtime : 1474270113000
         * updatetime : 1474633678000
         * deleted : false
         */

        private List<RowsBean> rows;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getStartNo() {
            return startNo;
        }

        public void setStartNo(int startNo) {
            this.startNo = startNo;
        }

        public int getEndNo() {
            return endNo;
        }

        public void setEndNo(int endNo) {
            this.endNo = endNo;
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

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            private int id;
            private int userid;
            private String longitude;
            private String latitude;
            private String shopname;
            private String address;
            private int category;
            private String linkman;
            private String linkphone;
            private Object ordertel;
            private Object orderphone;
            private Object type;
            private Object cuisine;
            private String shophours;
            private Object consume;
            private Object parking;
            private Object bill;
            private Object destine;
            private Object license;
            private Object sincerity;
            private Object unsubscribe;
            private Object timeoutFree;
            private Object limitService;
            private Object introduce;
            private String imagesrc;
            private int openstatus;
            private String postalcode;
            private String paykind;
            private Object istakeout;
            private int shopopen;
            private int shopclose;
            private int temporary;
            private Object temporaryTime;
            private Object temporaryReason;
            private String qrcode;
            private String provice;
            private String city;
            private String country;
            private boolean assign;
            private boolean grab;
            private Object shopcarouse;
            private String areacode;
            private Object customservice;
            private Object boxid;
            private String wifiid;
            private String bankusername;
            private String bankcard;
            private String bankname;
            private long createtime;
            private long updatetime;
            private boolean deleted;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public String getLinkman() {
                return linkman;
            }

            public void setLinkman(String linkman) {
                this.linkman = linkman;
            }

            public String getLinkphone() {
                return linkphone;
            }

            public void setLinkphone(String linkphone) {
                this.linkphone = linkphone;
            }

            public Object getOrdertel() {
                return ordertel;
            }

            public void setOrdertel(Object ordertel) {
                this.ordertel = ordertel;
            }

            public Object getOrderphone() {
                return orderphone;
            }

            public void setOrderphone(Object orderphone) {
                this.orderphone = orderphone;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getCuisine() {
                return cuisine;
            }

            public void setCuisine(Object cuisine) {
                this.cuisine = cuisine;
            }

            public String getShophours() {
                return shophours;
            }

            public void setShophours(String shophours) {
                this.shophours = shophours;
            }

            public Object getConsume() {
                return consume;
            }

            public void setConsume(Object consume) {
                this.consume = consume;
            }

            public Object getParking() {
                return parking;
            }

            public void setParking(Object parking) {
                this.parking = parking;
            }

            public Object getBill() {
                return bill;
            }

            public void setBill(Object bill) {
                this.bill = bill;
            }

            public Object getDestine() {
                return destine;
            }

            public void setDestine(Object destine) {
                this.destine = destine;
            }

            public Object getLicense() {
                return license;
            }

            public void setLicense(Object license) {
                this.license = license;
            }

            public Object getSincerity() {
                return sincerity;
            }

            public void setSincerity(Object sincerity) {
                this.sincerity = sincerity;
            }

            public Object getUnsubscribe() {
                return unsubscribe;
            }

            public void setUnsubscribe(Object unsubscribe) {
                this.unsubscribe = unsubscribe;
            }

            public Object getTimeoutFree() {
                return timeoutFree;
            }

            public void setTimeoutFree(Object timeoutFree) {
                this.timeoutFree = timeoutFree;
            }

            public Object getLimitService() {
                return limitService;
            }

            public void setLimitService(Object limitService) {
                this.limitService = limitService;
            }

            public Object getIntroduce() {
                return introduce;
            }

            public void setIntroduce(Object introduce) {
                this.introduce = introduce;
            }

            public String getImagesrc() {
                return imagesrc;
            }

            public void setImagesrc(String imagesrc) {
                this.imagesrc = imagesrc;
            }

            public int getOpenstatus() {
                return openstatus;
            }

            public void setOpenstatus(int openstatus) {
                this.openstatus = openstatus;
            }

            public String getPostalcode() {
                return postalcode;
            }

            public void setPostalcode(String postalcode) {
                this.postalcode = postalcode;
            }

            public String getPaykind() {
                return paykind;
            }

            public void setPaykind(String paykind) {
                this.paykind = paykind;
            }

            public Object getIstakeout() {
                return istakeout;
            }

            public void setIstakeout(Object istakeout) {
                this.istakeout = istakeout;
            }

            public int getShopopen() {
                return shopopen;
            }

            public void setShopopen(int shopopen) {
                this.shopopen = shopopen;
            }

            public int getShopclose() {
                return shopclose;
            }

            public void setShopclose(int shopclose) {
                this.shopclose = shopclose;
            }

            public int getTemporary() {
                return temporary;
            }

            public void setTemporary(int temporary) {
                this.temporary = temporary;
            }

            public Object getTemporaryTime() {
                return temporaryTime;
            }

            public void setTemporaryTime(Object temporaryTime) {
                this.temporaryTime = temporaryTime;
            }

            public Object getTemporaryReason() {
                return temporaryReason;
            }

            public void setTemporaryReason(Object temporaryReason) {
                this.temporaryReason = temporaryReason;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }

            public String getProvice() {
                return provice;
            }

            public void setProvice(String provice) {
                this.provice = provice;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public boolean isAssign() {
                return assign;
            }

            public void setAssign(boolean assign) {
                this.assign = assign;
            }

            public boolean isGrab() {
                return grab;
            }

            public void setGrab(boolean grab) {
                this.grab = grab;
            }

            public Object getShopcarouse() {
                return shopcarouse;
            }

            public void setShopcarouse(Object shopcarouse) {
                this.shopcarouse = shopcarouse;
            }

            public String getAreacode() {
                return areacode;
            }

            public void setAreacode(String areacode) {
                this.areacode = areacode;
            }

            public Object getCustomservice() {
                return customservice;
            }

            public void setCustomservice(Object customservice) {
                this.customservice = customservice;
            }

            public Object getBoxid() {
                return boxid;
            }

            public void setBoxid(Object boxid) {
                this.boxid = boxid;
            }

            public String getWifiid() {
                return wifiid;
            }

            public void setWifiid(String wifiid) {
                this.wifiid = wifiid;
            }

            public String getBankusername() {
                return bankusername;
            }

            public void setBankusername(String bankusername) {
                this.bankusername = bankusername;
            }

            public String getBankcard() {
                return bankcard;
            }

            public void setBankcard(String bankcard) {
                this.bankcard = bankcard;
            }

            public String getBankname() {
                return bankname;
            }

            public void setBankname(String bankname) {
                this.bankname = bankname;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public long getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(long updatetime) {
                this.updatetime = updatetime;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }
        }
    }
}
