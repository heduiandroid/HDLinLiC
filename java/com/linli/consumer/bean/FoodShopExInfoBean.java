package com.linli.consumer.bean;

/**
 * Created by tomoyo on 2016/12/30.
 */

public class FoodShopExInfoBean {

    /**
     * page : null
     * status : 1
     * data : {"operoompersoncount":null,"issend":null,"opemenu":null,"opeallseat":null,"opeavgcost":null,"ispackage":null,"opesendcost":null,"opemethod":null,"operoomcost":null,"opeallroomcount":null,"opeallcost":null,"opesendstartmoney":null,"opeatmosphere":null,"id":1,"opediscountcost":null,"opetype":"1","opeseatcount":null,"opeisdiscount":null,"opereserve":null,"bussId":1,"opeseatcost":null,"opelimittime":null,"opepackagecost":null,"opesendrange":null}
     * msg : 查询成功!
     * url : null
     */

    private Object page;
    private int status;
    /**
     * operoompersoncount : null
     * issend : null
     * opemenu : null
     * opeallseat : null
     * opeavgcost : null
     * ispackage : null
     * opesendcost : null
     * opemethod : null
     * operoomcost : null
     * opeallroomcount : null
     * opeallcost : null
     * opesendstartmoney : null
     * opeatmosphere : null
     * id : 1
     * opediscountcost : null
     * opetype : 1
     * opeseatcount : null
     * opeisdiscount : null
     * opereserve : null
     * bussId : 1
     * opeseatcost : null
     * opelimittime : null
     * opepackagecost : null
     * opesendrange : null
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
        private Object operoompersoncount;
        private Object issend;
        private Object opemenu;
        private Object opeallseat;
        private Object opeavgcost;
        private Object ispackage;
        private Object opesendcost;
        private Object opemethod;
        private Object operoomcost;
        private Object opeallroomcount;
        private Object opeallcost;
        private Object opesendstartmoney;
        private Object opeatmosphere;
        private int id;
        private Object opediscountcost;
        private String opetype;
        private Object opeseatcount;
        private Object opeisdiscount;
        private Object opereserve;
        private int bussId;
        private Object opeseatcost;
        private Object opelimittime;
        private Object opepackagecost;
        private Object opesendrange;

        public Object getOperoompersoncount() {
            return operoompersoncount;
        }

        public void setOperoompersoncount(Object operoompersoncount) {
            this.operoompersoncount = operoompersoncount;
        }

        public Object getIssend() {
            return issend;
        }

        public void setIssend(Object issend) {
            this.issend = issend;
        }

        public Object getOpemenu() {
            return opemenu;
        }

        public void setOpemenu(Object opemenu) {
            this.opemenu = opemenu;
        }

        public Object getOpeallseat() {
            return opeallseat;
        }

        public void setOpeallseat(Object opeallseat) {
            this.opeallseat = opeallseat;
        }

        public Object getOpeavgcost() {
            return opeavgcost;
        }

        public void setOpeavgcost(Object opeavgcost) {
            this.opeavgcost = opeavgcost;
        }

        public Object getIspackage() {
            return ispackage;
        }

        public void setIspackage(Object ispackage) {
            this.ispackage = ispackage;
        }

        public Object getOpesendcost() {
            return opesendcost;
        }

        public void setOpesendcost(Object opesendcost) {
            this.opesendcost = opesendcost;
        }

        public Object getOpemethod() {
            return opemethod;
        }

        public void setOpemethod(Object opemethod) {
            this.opemethod = opemethod;
        }

        public Object getOperoomcost() {
            return operoomcost;
        }

        public void setOperoomcost(Object operoomcost) {
            this.operoomcost = operoomcost;
        }

        public Object getOpeallroomcount() {
            return opeallroomcount;
        }

        public void setOpeallroomcount(Object opeallroomcount) {
            this.opeallroomcount = opeallroomcount;
        }

        public Object getOpeallcost() {
            return opeallcost;
        }

        public void setOpeallcost(Object opeallcost) {
            this.opeallcost = opeallcost;
        }

        public Object getOpesendstartmoney() {
            return opesendstartmoney;
        }

        public void setOpesendstartmoney(Object opesendstartmoney) {
            this.opesendstartmoney = opesendstartmoney;
        }

        public Object getOpeatmosphere() {
            return opeatmosphere;
        }

        public void setOpeatmosphere(Object opeatmosphere) {
            this.opeatmosphere = opeatmosphere;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getOpediscountcost() {
            return opediscountcost;
        }

        public void setOpediscountcost(Object opediscountcost) {
            this.opediscountcost = opediscountcost;
        }

        public String getOpetype() {
            return opetype;
        }

        public void setOpetype(String opetype) {
            this.opetype = opetype;
        }

        public Object getOpeseatcount() {
            return opeseatcount;
        }

        public void setOpeseatcount(Object opeseatcount) {
            this.opeseatcount = opeseatcount;
        }

        public Object getOpeisdiscount() {
            return opeisdiscount;
        }

        public void setOpeisdiscount(Object opeisdiscount) {
            this.opeisdiscount = opeisdiscount;
        }

        public Object getOpereserve() {
            return opereserve;
        }

        public void setOpereserve(Object opereserve) {
            this.opereserve = opereserve;
        }

        public int getBussId() {
            return bussId;
        }

        public void setBussId(int bussId) {
            this.bussId = bussId;
        }

        public Object getOpeseatcost() {
            return opeseatcost;
        }

        public void setOpeseatcost(Object opeseatcost) {
            this.opeseatcost = opeseatcost;
        }

        public Object getOpelimittime() {
            return opelimittime;
        }

        public void setOpelimittime(Object opelimittime) {
            this.opelimittime = opelimittime;
        }

        public Object getOpepackagecost() {
            return opepackagecost;
        }

        public void setOpepackagecost(Object opepackagecost) {
            this.opepackagecost = opepackagecost;
        }

        public Object getOpesendrange() {
            return opesendrange;
        }

        public void setOpesendrange(Object opesendrange) {
            this.opesendrange = opesendrange;
        }
    }
}
