package com.linli.consumer.bean;

/**
 * Created by tomoyo on 2016/12/30.
 */

public class ShopExtraInfoBean {

    /**
     * page : null
     * status : 1
     * data : {"createTime":null,"scope":3444,"status":null,"sendcost":3423,"begingive":23,"explains":null,"sendtype":1,"sale":1231,"id":314821366893151,"packing":12,"reductioncost":3422,"reduction":123,"sendtime":3232,"modifyTime":1482137091000,"storeId":214741998048404}
     * msg : 查询店铺附加信息成功！
     * url : null
     */

    private Object page;
    private int status;
    /**
     * createTime : null
     * scope : 3444
     * status : null
     * sendcost : 3423
     * begingive : 23
     * explains : null
     * sendtype : 1
     * sale : 1231
     * id : 314821366893151
     * packing : 12
     * reductioncost : 3422
     * reduction : 123
     * sendtime : 3232
     * modifyTime : 1482137091000
     * storeId : 214741998048404
     */

    private DataBean data;
    private String msg;
    private Object url;

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public static class DataBean {
        private double createTime;
        private double scope;
        private Object status;
        private double sendcost;
        private Double begingive;
        private Object explains;
        private long sendtype;
        private double sale;
        private long id;
        private double packing;
        private double reductioncost;
        private int reduction;
        private double sendtime;
        private double modifyTime;
        private long storeId;

        public double getCreateTime() {
            return createTime;
        }

        public void setCreateTime(double createTime) {
            this.createTime = createTime;
        }

        public double getScope() {
            return scope;
        }

        public void setScope(double scope) {
            this.scope = scope;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public double getSendcost() {
            return sendcost;
        }

        public void setSendcost(double sendcost) {
            this.sendcost = sendcost;
        }

        public Double getBegingive() {
            return begingive;
        }

        public void setBegingive(Double begingive) {
            this.begingive = begingive;
        }

        public Object getExplains() {
            return explains;
        }

        public void setExplains(Object explains) {
            this.explains = explains;
        }

        public long getSendtype() {
            return sendtype;
        }

        public void setSendtype(long sendtype) {
            this.sendtype = sendtype;
        }

        public double getSale() {
            return sale;
        }

        public void setSale(double sale) {
            this.sale = sale;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public double getPacking() {
            return packing;
        }

        public void setPacking(double packing) {
            this.packing = packing;
        }

        public double getReductioncost() {
            return reductioncost;
        }

        public void setReductioncost(double reductioncost) {
            this.reductioncost = reductioncost;
        }

        public int getReduction() {
            return reduction;
        }

        public void setReduction(int reduction) {
            this.reduction = reduction;
        }

        public double getSendtime() {
            return sendtime;
        }

        public void setSendtime(double sendtime) {
            this.sendtime = sendtime;
        }

        public double getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(double modifyTime) {
            this.modifyTime = modifyTime;
        }

        public long getStoreId() {
            return storeId;
        }

        public void setStoreId(long storeId) {
            this.storeId = storeId;
        }
    }
}
