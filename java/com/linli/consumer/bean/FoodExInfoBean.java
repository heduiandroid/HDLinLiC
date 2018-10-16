package com.linli.consumer.bean;

/**
 * Created by tomoyo on 2017/1/10.
 */

public class FoodExInfoBean {

    /**
     * status : 1
     * msg : 查询成功!
     * page : null
     * data : {"id":6,"bussId":7,"opemenu":"","opetype":"3","opemethod":"2","opeatmosphere":"1","opeavgcost":100,"opereserve":"7","opeseatcount":2,"opeseatcost":50,"opeallseat":8,"operoompersoncount":5,"operoomcost":200,"opeallroomcount":4,"opesendrange":"3000","opesendstartmoney":10,"opepackagecost":1,"opesendcost":5,"opelimittime":"45","opeallcost":500,"opeisdiscount":"","opediscountcost":"","issend":"1","ispackage":"1"}
     * url : null
     */

    private int status;
    private String msg;
    private Object page;
    /**
     * id : 6
     * bussId : 7
     * opemenu :
     * opetype : 3
     * opemethod : 2
     * opeatmosphere : 1
     * opeavgcost : 100
     * opereserve : 7
     * opeseatcount : 2
     * opeseatcost : 50
     * opeallseat : 8
     * operoompersoncount : 5
     * operoomcost : 200
     * opeallroomcount : 4
     * opesendrange : 3000
     * opesendstartmoney : 10
     * opepackagecost : 1
     * opesendcost : 5
     * opelimittime : 45
     * opeallcost : 500
     * opeisdiscount :
     * opediscountcost :
     * issend : 1
     * ispackage : 1
     */

    private DataBean data;
    private Object url;

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
        private long id;
        private long bussId;
        private String opemenu;
        private String opetype;
        private String opemethod;       //0:堂吃  1:外卖    2:both
        private String opeatmosphere;
        private long opeavgcost;
        private String opereserve;
        private int opeseatcount;
        private double opeseatcost;
        private int opeallseat;
        private int operoompersoncount;
        private Double operoomcost;
        private int opeallroomcount;
        private String opesendrange;
        private Double opesendstartmoney;
        private double opepackagecost;
        private double opesendcost;
        private String opelimittime;
        private double opeallcost;
        private String opeisdiscount;
        private String opediscountcost;
        private String issend;
        private String ispackage;

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

        public String getOpemenu() {
            return opemenu;
        }

        public void setOpemenu(String opemenu) {
            this.opemenu = opemenu;
        }

        public String getOpetype() {
            return opetype;
        }

        public void setOpetype(String opetype) {
            this.opetype = opetype;
        }

        public String getOpemethod() {
            return opemethod;
        }

        public void setOpemethod(String opemethod) {
            this.opemethod = opemethod;
        }

        public String getOpeatmosphere() {
            return opeatmosphere;
        }

        public void setOpeatmosphere(String opeatmosphere) {
            this.opeatmosphere = opeatmosphere;
        }

        public long getOpeavgcost() {
            return opeavgcost;
        }

        public void setOpeavgcost(long opeavgcost) {
            this.opeavgcost = opeavgcost;
        }

        public String getOpereserve() {
            return opereserve;
        }

        public void setOpereserve(String opereserve) {
            this.opereserve = opereserve;
        }

        public int getOpeseatcount() {
            return opeseatcount;
        }

        public void setOpeseatcount(int opeseatcount) {
            this.opeseatcount = opeseatcount;
        }

        public double getOpeseatcost() {
            return opeseatcost;
        }

        public void setOpeseatcost(double opeseatcost) {
            this.opeseatcost = opeseatcost;
        }

        public int getOpeallseat() {
            return opeallseat;
        }

        public void setOpeallseat(int opeallseat) {
            this.opeallseat = opeallseat;
        }

        public int getOperoompersoncount() {
            return operoompersoncount;
        }

        public void setOperoompersoncount(int operoompersoncount) {
            this.operoompersoncount = operoompersoncount;
        }

        public Double getOperoomcost() {
            return operoomcost;
        }

        public void setOperoomcost(Double operoomcost) {
            this.operoomcost = operoomcost;
        }

        public int getOpeallroomcount() {
            return opeallroomcount;
        }

        public void setOpeallroomcount(int opeallroomcount) {
            this.opeallroomcount = opeallroomcount;
        }

        public String getOpesendrange() {
            return opesendrange;
        }

        public void setOpesendrange(String opesendrange) {
            this.opesendrange = opesendrange;
        }

        public Double getOpesendstartmoney() {
            return opesendstartmoney;
        }

        public void setOpesendstartmoney(Double opesendstartmoney) {
            this.opesendstartmoney = opesendstartmoney;
        }

        public double getOpepackagecost() {
            return opepackagecost;
        }

        public void setOpepackagecost(double opepackagecost) {
            this.opepackagecost = opepackagecost;
        }

        public double getOpesendcost() {
            return opesendcost;
        }

        public void setOpesendcost(double opesendcost) {
            this.opesendcost = opesendcost;
        }

        public String getOpelimittime() {
            return opelimittime;
        }

        public void setOpelimittime(String opelimittime) {
            this.opelimittime = opelimittime;
        }

        public double getOpeallcost() {
            return opeallcost;
        }

        public void setOpeallcost(double opeallcost) {
            this.opeallcost = opeallcost;
        }

        public String getOpeisdiscount() {
            return opeisdiscount;
        }

        public void setOpeisdiscount(String opeisdiscount) {
            this.opeisdiscount = opeisdiscount;
        }

        public String getOpediscountcost() {
            return opediscountcost;
        }

        public void setOpediscountcost(String opediscountcost) {
            this.opediscountcost = opediscountcost;
        }

        public String getIssend() {
            return issend;
        }

        public void setIssend(String issend) {
            this.issend = issend;
        }

        public String getIspackage() {
            return ispackage;
        }

        public void setIspackage(String ispackage) {
            this.ispackage = ispackage;
        }
    }
}
