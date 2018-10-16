package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/2/14.
 */

public class SearchFood {

    /**
     * status : 1
     * msg : 查询成功!
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":10,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":1}
     * data : [{"id":314848055865913,"bussId":314848053314163,"name":"水煮花生","maining":"花生","accessories":"大料","imgpath":"http://obqlfysk2.bkt.clouddn.com/FvGwMndnFRfpFaZW9avX14ixOv3R","price":1,"status":"0","isoutshop":"1","sort":1,"belongtype":314848054539909}]
     */

    private int status;
    private String msg;
    private PageBean page;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
         * id : 314848055865913
         * bussId : 314848053314163
         * name : 水煮花生
         * maining : 花生
         * accessories : 大料
         * imgpath : http://obqlfysk2.bkt.clouddn.com/FvGwMndnFRfpFaZW9avX14ixOv3R
         * price : 1.0
         * status : 0
         * isoutshop : 1
         * sort : 1
         * belongtype : 314848054539909
         */

        private long id;
        private long bussId;
        private String name;
        private String maining;
        private String accessories;
        private String imgpath;
        private double price;
        private String status;
        private String isoutshop;
        private int sort;
        private long belongtype;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getBussId() {
            return bussId;
        }

        public void setBussId(long bussId) {
            this.bussId = bussId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMaining() {
            return maining;
        }

        public void setMaining(String maining) {
            this.maining = maining;
        }

        public String getAccessories() {
            return accessories;
        }

        public void setAccessories(String accessories) {
            this.accessories = accessories;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsoutshop() {
            return isoutshop;
        }

        public void setIsoutshop(String isoutshop) {
            this.isoutshop = isoutshop;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public long getBelongtype() {
            return belongtype;
        }

        public void setBelongtype(long belongtype) {
            this.belongtype = belongtype;
        }
    }
}
