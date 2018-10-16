package com.linli.consumer.bean;

/**
 * Created by hasee on 2016/12/22.
 */

public class AddBankCard {

    /**
     * status : 1
     * msg : null
     * page : null
     * data : {"code":1000,"msg":"成功","result":{"userId":314818679941362,"userAccountId":76,"remainAmount":0,"accountStatus":0,"hasPayPassword":true,"bankInfo":{"bankName":"建设银行","cardNumber":"602145698896325","accountName":"刘德华"},"createTime":1481867979,"updateTime":1482396679}}
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
         * code : 1000
         * msg : 成功
         * result : {"userId":314818679941362,"userAccountId":76,"remainAmount":0,"accountStatus":0,"hasPayPassword":true,"bankInfo":{"bankName":"建设银行","cardNumber":"602145698896325","accountName":"刘德华"},"createTime":1481867979,"updateTime":1482396679}
         */

        private int code;
        private String msg;
        private ResultBean result;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * userId : 314818679941362
             * userAccountId : 76
             * remainAmount : 0.0
             * accountStatus : 0
             * hasPayPassword : true
             * bankInfo : {"bankName":"建设银行","cardNumber":"602145698896325","accountName":"刘德华"}
             * createTime : 1481867979
             * updateTime : 1482396679
             */

            private long userId;
            private int userAccountId;
            private double remainAmount;
            private int accountStatus;
            private boolean hasPayPassword;
            private BankInfoBean bankInfo;
            private int createTime;
            private int updateTime;

            public long getUserId() {
                return userId;
            }

            public void setUserId(long userId) {
                this.userId = userId;
            }

            public int getUserAccountId() {
                return userAccountId;
            }

            public void setUserAccountId(int userAccountId) {
                this.userAccountId = userAccountId;
            }

            public double getRemainAmount() {
                return remainAmount;
            }

            public void setRemainAmount(double remainAmount) {
                this.remainAmount = remainAmount;
            }

            public int getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(int accountStatus) {
                this.accountStatus = accountStatus;
            }

            public boolean isHasPayPassword() {
                return hasPayPassword;
            }

            public void setHasPayPassword(boolean hasPayPassword) {
                this.hasPayPassword = hasPayPassword;
            }

            public BankInfoBean getBankInfo() {
                return bankInfo;
            }

            public void setBankInfo(BankInfoBean bankInfo) {
                this.bankInfo = bankInfo;
            }

            public int getCreateTime() {
                return createTime;
            }

            public void setCreateTime(int createTime) {
                this.createTime = createTime;
            }

            public int getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(int updateTime) {
                this.updateTime = updateTime;
            }

            public static class BankInfoBean {
                /**
                 * bankName : 建设银行
                 * cardNumber : 602145698896325
                 * accountName : 刘德华
                 */

                private String bankName;
                private String cardNumber;
                private String accountName;

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

                public String getAccountName() {
                    return accountName;
                }

                public void setAccountName(String accountName) {
                    this.accountName = accountName;
                }
            }
        }
    }
}
