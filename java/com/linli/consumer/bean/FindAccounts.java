package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by wangpei on 2017/9/13.
 */

public class FindAccounts {

    /**
     * status : 1
     * msg : 查询成功!
     * data : [{"id":715052673167085,"type":3,"balance":0,"roleId":715051169846642,"status":0},{"id":715052674915296,"type":3,"balance":0,"roleId":715051169846642,"status":0},{"id":715052674923657,"type":3,"balance":0,"roleId":715051169846642,"status":0},{"id":715052674929268,"type":3,"balance":0,"roleId":715051169846642,"status":0},{"id":715052674932189,"type":3,"balance":0,"roleId":715051169846642,"status":0},{"id":715052674934960,"type":3,"balance":0,"roleId":715051169846642,"status":0},{"id":715052674937481,"type":3,"balance":0,"roleId":715051169846642,"status":0},{"id":715052674940162,"type":3,"balance":0,"roleId":715051169846642,"status":0},{"id":715052674943863,"type":3,"balance":0,"roleId":715051169846642,"status":0},{"id":715052674948194,"type":3,"balance":0,"roleId":715051169846642,"status":0}]
     */

    private int status;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private Long id;
        private int type;
        private Double balance;
        private Long roleId;
        private int status;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public Long getRoleId() {
            return roleId;
        }

        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
