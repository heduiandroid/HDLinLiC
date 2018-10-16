package com.linli.consumer.bean;

/**
 * Created by hasee on 2018/7/12.
 */

public class ServiceOrderBean {

    /**
     * status : 1
     * data : {"id":715313891590821,"orderSn":"715313891590821","storeid":715051870324677,"memberid":714931894383297,"goodsid":715052014682334,"goodsamount":22222,"orderamount":22222,"orderstatus":0,"linkname":"刘诗诗","linkphone":"18801071735","address":"北京 北京 房山区 良乡镇白杨西路5号","appointment":"2018年07月12日 17时52分","createtime":1531389159082}
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
         * id : 715313891590821
         * orderSn : 715313891590821
         * storeid : 715051870324677
         * memberid : 714931894383297
         * goodsid : 715052014682334
         * goodsamount : 22222.0
         * orderamount : 22222.0
         * orderstatus : 0
         * linkname : 刘诗诗
         * linkphone : 18801071735
         * address : 北京 北京 房山区 良乡镇白杨西路5号
         * appointment : 2018年07月12日 17时52分
         * createtime : 1531389159082
         */

        private Long id;
        private String orderSn;
        private Long storeid;
        private Long memberid;
        private Long goodsid;
        private Double goodsamount;
        private Double orderamount;
        private int orderstatus;
        private String linkname;
        private String linkphone;
        private String address;
        private String appointment;
        private Long createtime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
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
    }
}
