package com.linli.consumer.bean;

/**
 * Created by hasee on 2016/12/22.
 */

public class AccountInfo {

    /**
     * status : 1
     * msg : null
     * page : null
     * data : {"remainAmount":0,"accountName":null,"bankName":null,"cardNumber":null}
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
         * remainAmount : 0.0
         * accountName : null
         * bankName : null
         * cardNumber : null
         */

        private double remainAmount;
        private String accountName;
        private String bankName;
        private String cardNumber;

        public double getRemainAmount() {
            return remainAmount;
        }

        public void setRemainAmount(double remainAmount) {
            this.remainAmount = remainAmount;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }
    }
}
