package com.linli.consumer.bean;

import java.math.BigDecimal;

/**
 * Created by hasee on 2017/3/16.
 */

public class FoodExtraInfo {

    /**
     * status : 1
     * msg : 查询成功!
     * data : {"id":114888581068275,"bussId":114888581024444,"opetype":"3","opemethod":"2","opeavgcost":30,"opereserve":"0","opeseatcount":544566,"opeseatcost":0,"opeallseat":0,"operoompersoncount":0,"operoomcost":0,"opeallroomcount":336985,"opesendrange":"5","opesendstartmoney":1,"opepackagecost":1,"opesendcost":1,"opelimittime":"30"}
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
         * id : 114888581068275
         * bussId : 114888581024444
         * opetype : 3
         * opemethod : 2
         * opeavgcost : 30.0
         * opereserve : 0
         * opeseatcount : 544566
         * opeseatcost : 0.0
         * opeallseat : 0
         * operoompersoncount : 0
         * operoomcost : 0.0
         * opeallroomcount : 336985
         * opesendrange : 5
         * opesendstartmoney : 1
         * opepackagecost : 1
         * opesendcost : 1
         * opelimittime : 30
         */

        private long id;
        private long bussId;
        private String opetype;
        private String opemethod;
        private double opeavgcost;
        private String opereserve;
        private int opeseatcount;
        private double opeseatcost;
        private int opeallseat;
        private int operoompersoncount;
        private double operoomcost;
        private int opeallroomcount;
        private String opesendrange;
        private int opesendstartmoney;
        private BigDecimal opepackagecost;
        private BigDecimal opesendcost;
        private String opelimittime;

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

        public double getOpeavgcost() {
            return opeavgcost;
        }

        public void setOpeavgcost(double opeavgcost) {
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

        public double getOperoomcost() {
            return operoomcost;
        }

        public void setOperoomcost(double operoomcost) {
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

        public int getOpesendstartmoney() {
            return opesendstartmoney;
        }

        public void setOpesendstartmoney(int opesendstartmoney) {
            this.opesendstartmoney = opesendstartmoney;
        }

        public BigDecimal getOpepackagecost() {
            return opepackagecost;
        }

        public void setOpepackagecost(BigDecimal opepackagecost) {
            this.opepackagecost = opepackagecost;
        }

        public BigDecimal getOpesendcost() {
            return opesendcost;
        }

        public void setOpesendcost(BigDecimal opesendcost) {
            this.opesendcost = opesendcost;
        }

        public String getOpelimittime() {
            return opelimittime;
        }

        public void setOpelimittime(String opelimittime) {
            this.opelimittime = opelimittime;
        }
    }
}
