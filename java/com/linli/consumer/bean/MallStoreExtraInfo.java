package com.linli.consumer.bean;

import java.math.BigDecimal;

/**
 * Created by hasee on 2017/3/16.
 */

public class MallStoreExtraInfo {

    /**
     * status : 1
     * msg : 查询店铺附加信息成功！
     * data : {"id":314895467841667,"storeId":414895426917182,"scope":5,"begingive":0,"sendtype":2,"sendtime":30,"packing":0,"sendcost":1.3,"reduction":0,"reductioncost":0,"sale":0,"createTime":1489546784000,"modifyTime":1489566494000,"orderlevel":5}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 314895467841667
         * storeId : 414895426917182
         * scope : 5
         * begingive : 0.0
         * sendtype : 2
         * sendtime : 30
         * packing : 0.0
         * sendcost : 1.3
         * reduction : 0.0
         * reductioncost : 0.0
         * sale : 0.0
         * createTime : 1489546784000
         * modifyTime : 1489566494000
         * orderlevel : 5
         */

        private long id;
        private long storeId;
        private int scope;
        private double begingive;
        private int sendtype;
        private int sendtime;
        private double packing;
        private BigDecimal sendcost;
        private double reduction;
        private double reductioncost;
        private double sale;
        private long createTime;
        private long modifyTime;
        private int orderlevel;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getStoreId() {
            return storeId;
        }

        public void setStoreId(long storeId) {
            this.storeId = storeId;
        }

        public int getScope() {
            return scope;
        }

        public void setScope(int scope) {
            this.scope = scope;
        }

        public double getBegingive() {
            return begingive;
        }

        public void setBegingive(double begingive) {
            this.begingive = begingive;
        }

        public int getSendtype() {
            return sendtype;
        }

        public void setSendtype(int sendtype) {
            this.sendtype = sendtype;
        }

        public int getSendtime() {
            return sendtime;
        }

        public void setSendtime(int sendtime) {
            this.sendtime = sendtime;
        }

        public double getPacking() {
            return packing;
        }

        public void setPacking(double packing) {
            this.packing = packing;
        }

        public BigDecimal getSendcost() {
            return sendcost;
        }

        public void setSendcost(BigDecimal sendcost) {
            this.sendcost = sendcost;
        }

        public double getReduction() {
            return reduction;
        }

        public void setReduction(double reduction) {
            this.reduction = reduction;
        }

        public double getReductioncost() {
            return reductioncost;
        }

        public void setReductioncost(double reductioncost) {
            this.reductioncost = reductioncost;
        }

        public double getSale() {
            return sale;
        }

        public void setSale(double sale) {
            this.sale = sale;
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

        public int getOrderlevel() {
            return orderlevel;
        }

        public void setOrderlevel(int orderlevel) {
            this.orderlevel = orderlevel;
        }
    }
}
