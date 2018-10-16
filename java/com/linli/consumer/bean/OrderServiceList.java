package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/9/13.
 */

public class OrderServiceList {

    /**
     * status : 1
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":10,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":1}
     * data : [{"id":715314538311637,"storeid":715051870324677,"memberid":714931894383297,"goodsid":715052014682334,"goodsamount":0.01,"orderamount":0.01,"favorableamount":0.01,"orderstatus":0,"linkname":"刘诗诗","linkphone":"18801071735","address":"北京 北京 房山区 良乡镇白杨西路5号","appointment":"2018年07月13日 11时50分","createtime":1531453831000,"updatetime":1531462642000,"hdServeGoods":{"id":715052014682334,"name":"111111","img":"","img1":"","img2":"","img3":"http://obqlfysk2.bkt.clouddn.com/FoKUHE48x1g4FDoTTyxzSUuvixxD","storeid":715051870324677,"price":0.01,"content":"1111111111111111","createtime":1505201468000}}]
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
         * id : 715314538311637
         * storeid : 715051870324677
         * memberid : 714931894383297
         * goodsid : 715052014682334
         * goodsamount : 0.01
         * orderamount : 0.01
         * favorableamount : 0.01
         * orderstatus : 0
         * linkname : 刘诗诗
         * linkphone : 18801071735
         * address : 北京 北京 房山区 良乡镇白杨西路5号
         * appointment : 2018年07月13日 11时50分
         * createtime : 1531453831000
         * updatetime : 1531462642000
         * hdServeGoods : {"id":715052014682334,"name":"111111","img":"","img1":"","img2":"","img3":"http://obqlfysk2.bkt.clouddn.com/FoKUHE48x1g4FDoTTyxzSUuvixxD","storeid":715051870324677,"price":0.01,"content":"1111111111111111","createtime":1505201468000}
         */

        private Long id;
        private Long storeid;
        private Long memberid;
        private Long ordersn;
        private Long goodsid;
        private Double goodsamount;
        private Double orderamount;
        private Long couponId;
        private Double favorableamount;
        private int orderstatus;
        private String linkname;
        private String linkphone;
        private String address;
        private String appointment;
        private Long createtime;
        private Long updatetime;
        private HdServeGoodsBean hdServeGoods;

        public Long getCouponId() {
            return couponId;
        }

        public void setCouponId(Long couponId) {
            this.couponId = couponId;
        }

        public Long getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(Long ordersn) {
            this.ordersn = ordersn;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getStoreid() {
            return storeid;
        }

        public void setStoreid(Long storeid) {
            this.storeid = storeid;
        }

        public Long getMemberid() {
            return memberid;
        }

        public void setMemberid(Long memberid) {
            this.memberid = memberid;
        }

        public Long getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(Long goodsid) {
            this.goodsid = goodsid;
        }

        public Double getGoodsamount() {
            return goodsamount;
        }

        public void setGoodsamount(Double goodsamount) {
            this.goodsamount = goodsamount;
        }

        public Double getOrderamount() {
            return orderamount;
        }

        public void setOrderamount(Double orderamount) {
            this.orderamount = orderamount;
        }

        public Double getFavorableamount() {
            return favorableamount;
        }

        public void setFavorableamount(Double favorableamount) {
            this.favorableamount = favorableamount;
        }

        public int getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
            this.orderstatus = orderstatus;
        }

        public String getLinkname() {
            return linkname;
        }

        public void setLinkname(String linkname) {
            this.linkname = linkname;
        }

        public String getLinkphone() {
            return linkphone;
        }

        public void setLinkphone(String linkphone) {
            this.linkphone = linkphone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAppointment() {
            return appointment;
        }

        public void setAppointment(String appointment) {
            this.appointment = appointment;
        }

        public Long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Long createtime) {
            this.createtime = createtime;
        }

        public Long getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Long updatetime) {
            this.updatetime = updatetime;
        }

        public HdServeGoodsBean getHdServeGoods() {
            return hdServeGoods;
        }

        public void setHdServeGoods(HdServeGoodsBean hdServeGoods) {
            this.hdServeGoods = hdServeGoods;
        }

        public static class HdServeGoodsBean {
            /**
             * id : 715052014682334
             * name : 111111
             * img :
             * img1 :
             * img2 :
             * img3 : http://obqlfysk2.bkt.clouddn.com/FoKUHE48x1g4FDoTTyxzSUuvixxD
             * storeid : 715051870324677
             * price : 0.01
             * content : 1111111111111111
             * createtime : 1505201468000
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
