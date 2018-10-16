package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/8/22.
 */

public class ServicesBean {

    /**
     * status : 1
     * data : [{"hdServeStore":{"id":715033692278092,"name":"王直直对对对","phone":"18235910362","notice":"哈哈哈","info":"店铺信息","companyMemberId":715033690702238,"approveStatus":1,"createTime":1503369227000,"modifyTime":1503388654000,"status":0,"openStatus":0,"opentime":1503369227000,"longitude":116.152077,"latitude":39.7164261,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FlVrIoaVyuCnAPjhU1T6uDgPy252","isgrab":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/715033692278092flag_1503369227827.png","wifiid":"0AQ12S09","address":"北京北京房山区白杨西路号"},"goodsList":[{"id":715033694611775,"name":"我叫王","img":"http://obqlfysk2.bkt.clouddn.com/FqrXJRSmz7bGrP6gR58vXYsk1ukk","img1":"","img2":"http://obqlfysk2.bkt.clouddn.com/FrxHjM4h2AFD0OVlLbf_0V5Kl3BX","img3":"http://obqlfysk2.bkt.clouddn.com/FrxHjM4h2AFD0OVlLbf_0V5Kl3BX","storeid":715033692278092,"price":0.01,"content":"我叫王直直","createtime":1503369461000}],"type":0,"pageNum":0,"numPerPage":0,"grab":0,"isgrab":0,"openStatus":0,"isactivity":0,"praiseNum":0,"storeUserNum":0}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * hdServeStore : {"id":715033692278092,"name":"王直直对对对","phone":"18235910362","notice":"哈哈哈","info":"店铺信息","companyMemberId":715033690702238,"approveStatus":1,"createTime":1503369227000,"modifyTime":1503388654000,"status":0,"openStatus":0,"opentime":1503369227000,"longitude":116.152077,"latitude":39.7164261,"regionId":508,"logoImg":"http://obqlfysk2.bkt.clouddn.com/FlVrIoaVyuCnAPjhU1T6uDgPy252","isgrab":2,"grab":1,"qrcode":"http://obqlfysk2.bkt.clouddn.com/715033692278092flag_1503369227827.png","wifiid":"0AQ12S09","address":"北京北京房山区白杨西路号"}
         * goodsList : [{"id":715033694611775,"name":"我叫王","img":"http://obqlfysk2.bkt.clouddn.com/FqrXJRSmz7bGrP6gR58vXYsk1ukk","img1":"","img2":"http://obqlfysk2.bkt.clouddn.com/FrxHjM4h2AFD0OVlLbf_0V5Kl3BX","img3":"http://obqlfysk2.bkt.clouddn.com/FrxHjM4h2AFD0OVlLbf_0V5Kl3BX","storeid":715033692278092,"price":0.01,"content":"我叫王直直","createtime":1503369461000}]
         * type : 0
         * pageNum : 0
         * numPerPage : 0
         * grab : 0
         * isgrab : 0
         * openStatus : 0
         * isactivity : 0
         * praiseNum : 0
         * storeUserNum : 0
         */

        private HdServeStoreBean hdServeStore;
        private int type;
        private int pageNum;
        private int numPerPage;
        private int grab;
        private int isgrab;
        private int openStatus;
        private int isactivity;
        private int praiseNum;
        private int storeUserNum;

        private List<GoodsListBean> goodsList;
        public HdServeStoreBean getHdServeStore() {
            return hdServeStore;
        }

        public void setHdServeStore(HdServeStoreBean hdServeStore) {
            this.hdServeStore = hdServeStore;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getGrab() {
            return grab;
        }

        public void setGrab(int grab) {
            this.grab = grab;
        }

        public int getIsgrab() {
            return isgrab;
        }

        public void setIsgrab(int isgrab) {
            this.isgrab = isgrab;
        }

        public int getOpenStatus() {
            return openStatus;
        }

        public void setOpenStatus(int openStatus) {
            this.openStatus = openStatus;
        }

        public int getIsactivity() {
            return isactivity;
        }

        public void setIsactivity(int isactivity) {
            this.isactivity = isactivity;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getStoreUserNum() {
            return storeUserNum;
        }

        public void setStoreUserNum(int storeUserNum) {
            this.storeUserNum = storeUserNum;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class HdServeStoreBean {
            /**
             * id : 715033692278092
             * name : 王直直对对对
             * phone : 18235910362
             * notice : 哈哈哈
             * info : 店铺信息
             * companyMemberId : 715033690702238
             * approveStatus : 1
             * createTime : 1503369227000
             * modifyTime : 1503388654000
             * status : 0
             * openStatus : 0
             * opentime : 1503369227000
             * longitude : 116.152077
             * latitude : 39.7164261
             * regionId : 508
             * logoImg : http://obqlfysk2.bkt.clouddn.com/FlVrIoaVyuCnAPjhU1T6uDgPy252
             * isgrab : 2
             * grab : 1
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

        public static class GoodsListBean {
            /**
             * id : 715033694611775
             * name : 我叫王
             * img : http://obqlfysk2.bkt.clouddn.com/FqrXJRSmz7bGrP6gR58vXYsk1ukk
             * img1 :
             * img2 : http://obqlfysk2.bkt.clouddn.com/FrxHjM4h2AFD0OVlLbf_0V5Kl3BX
             * img3 : http://obqlfysk2.bkt.clouddn.com/FrxHjM4h2AFD0OVlLbf_0V5Kl3BX
             * storeid : 715033692278092
             * price : 0.01
             * content : 我叫王直直
             * createtime : 1503369461000
             */

            private long id;
            private String name;
            private String img;
            private String img1;
            private String img2;
            private String img3;
            private long storeid;
            private double price;
            private String content;
            private long createtime;

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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public long getStoreid() {
                return storeid;
            }

            public void setStoreid(long storeid) {
                this.storeid = storeid;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }
        }
    }
}
