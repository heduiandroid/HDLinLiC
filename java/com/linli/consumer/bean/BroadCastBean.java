package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/4/14.
 */

public class BroadCastBean {

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

    private PageBean page;
    /**
     * data : [{"createtime":1492159236000,"id":314921592369752,"title":"指令上线通知！","type":1,"content":"指令于今日上线，马上下载体验专业的区域化电商服务吧！"}]
     * page : {"plainPageNum":1,"prePage":1,"totalPage":1,"nextPage":1,"orderDirection":"","numPerPage":10,"orderField":"","totalCount":1,"pageNum":1}
     * status : 1
     */

    private int status;
    /**
     * createtime : 1492159236000
     * id : 314921592369752
     * title : 指令上线通知！
     * type : 1
     * content : 指令于今日上线，马上下载体验专业的区域化电商服务吧！
     */

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

    public static class DataBean {
        private long createtime;
        private long id;
        private String title;
        private int type;
        private String content;

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
