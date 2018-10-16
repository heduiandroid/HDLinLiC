package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2016/12/27.
 *
 * this bean is a response of this request,see
 * {@link com.linli.consumer.net.IShopApi#goodsBigTitle }
 */

public class ShopTitleBean {

    /**
     * status : 1
     * msg : 查询成功！
     * page : null
     * data : [{"id":214741996844551,"name":"小说","parentId":214741996370919,"sort":1,"isShow":0,"categoryImgUrl":"","level":0,"status":0,"createTime":1474128000000,"modifyTime":1474513680000,"children":null,"url":null},{"id":214743632544205,"name":"文学","parentId":214741996370919,"sort":1,"isShow":0,"categoryImgUrl":"","level":0,"status":0,"createTime":1474300800000,"modifyTime":1474513694000,"children":null,"url":null}]
     * url : null
     */

    private int status;
    private String msg;
    private Object page;
    private Object url;
    /**
     * id : 214741996844551
     * name : 小说
     * parentId : 214741996370919
     * sort : 1
     * isShow : 0
     * categoryImgUrl :
     * level : 0
     * status : 0
     * createTime : 1474128000000
     * modifyTime : 1474513680000
     * children : null
     * url : null
     */

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

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private long id;
        private String name;
        private long parentId;
        private int sort;
        private int isShow;
        private String categoryImgUrl;
        private int level;
        private int status;
        private long createTime;
        private long modifyTime;
        private Object children;
        private Object url;

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

        public long getParentId() {
            return parentId;
        }

        public void setParentId(long parentId) {
            this.parentId = parentId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public String getCategoryImgUrl() {
            return categoryImgUrl;
        }

        public void setCategoryImgUrl(String categoryImgUrl) {
            this.categoryImgUrl = categoryImgUrl;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public Object getChildren() {
            return children;
        }

        public void setChildren(Object children) {
            this.children = children;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }
    }
}
