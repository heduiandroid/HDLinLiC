package com.linli.consumer.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangpei on 2017/8/7.
 */

public class FindserviceShopid {

    /**
     * data : [{"img3":"asdasd","img":"s","createtime":1501841390000,"price":12,"name":"aa","id":515018413901151,"storeid":123123,"img2":"asd","content":"asdasdasd","img1":"ad"}]
     * page : {"plainPageNum":1,"prePage":1,"totalPage":1,"nextPage":1,"orderDirection":"","numPerPage":10,"orderField":"","totalCount":1,"pageNum":1}
     * status : 1
     */

    private PageBean page;
    private int status;
    private List<DataBean> data;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

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

    public static class PageBean {
        /**
         * plainPageNum : 1
         * prePage : 1
         * totalPage : 1
         * nextPage : 1
         * orderDirection :
         * numPerPage : 10
         * orderField :
         * totalCount : 1
         * pageNum : 1
         */

        private int plainPageNum;
        private int prePage;
        private int totalPage;
        private int nextPage;
        private String orderDirection;
        private int numPerPage;
        private String orderField;
        private int totalCount;
        private int pageNum;

        public int getPlainPageNum() {
            return plainPageNum;
        }

        public void setPlainPageNum(int plainPageNum) {
            this.plainPageNum = plainPageNum;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public String getOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(String orderDirection) {
            this.orderDirection = orderDirection;
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

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }
    }

    public static class DataBean implements Serializable {
        /**
         * img3 : asdasd
         * img : s
         * createtime : 1501841390000
         * price : 12
         * name : aa
         * id : 515018413901151
         * storeid : 123123
         * img2 : asd
         * content : asdasdasd
         * img1 : ad
         */

        private String img3;
        private String img;
        private Long createtime;
        private Float price;
        private String name;
        private Long id;
        private Long storeid;
        private String img2;
        private String content;
        private String img1;

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Long getStoreid() {
            return storeid;
        }

        public void setStoreid(Long storeid) {
            this.storeid = storeid;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }
    }
}
