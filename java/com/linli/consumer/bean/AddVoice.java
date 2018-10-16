package com.linli.consumer.bean;

/**
 * Created by hasee on 2016/12/19.
 */

public class AddVoice {

    /**
     * status : 1
     * msg : null
     * page : null
     * data : {"id":null,"path":"http://obqlfysk2.bkt.clouddn.com/aaaaa","name":"aaaaa","memId":314818679941362,"num":null,"regionId":null,"categoryId":null,"type":1,"createTime":null}
     * url : null
     */

    private int status;
    private Object msg;
    private Object page;
    private DataBean data;
    private Object url;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public static class DataBean {
        /**
         * id : null
         * path : http://obqlfysk2.bkt.clouddn.com/aaaaa
         * name : aaaaa
         * memId : 314818679941362
         * num : null
         * regionId : null
         * categoryId : null
         * type : 1
         * createTime : null
         */

        private Long id;
        private String path;
        private String name;
        private long memId;
        private Object num;
        private Object regionId;
        private Object categoryId;
        private int type;
        private Object createTime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getMemId() {
            return memId;
        }

        public void setMemId(long memId) {
            this.memId = memId;
        }

        public Object getNum() {
            return num;
        }

        public void setNum(Object num) {
            this.num = num;
        }

        public Object getRegionId() {
            return regionId;
        }

        public void setRegionId(Object regionId) {
            this.regionId = regionId;
        }

        public Object getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }
    }
}
