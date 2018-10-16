package com.linli.consumer.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hasee on 2017/8/23.
 */

public class ServiceGoodBean {

    /**
     * status : 1
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":20,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":1}
     * data : [{"id":715033694611775,"name":"我叫王","img":"http://obqlfysk2.bkt.clouddn.com/FqrXJRSmz7bGrP6gR58vXYsk1ukk","img1":"","img2":"http://obqlfysk2.bkt.clouddn.com/FrxHjM4h2AFD0OVlLbf_0V5Kl3BX","img3":"http://obqlfysk2.bkt.clouddn.com/FrxHjM4h2AFD0OVlLbf_0V5Kl3BX","storeid":715033692278092,"price":0.01,"content":"我叫王直直","createtime":1503369461000}]
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

    public static class DataBean implements Serializable {
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
        private String storeName;
        private double price;
        private String content;
        private long createtime;

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
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
