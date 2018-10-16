package com.linli.consumer.bean;

/**
 * Created by hasee on 2016/12/22.
 */

public class AliPayInfo {
    /**
     * status : 1
     * msg : null
     * page : null
     * data : {"PARTNER":"2088121688433844","RSA_PRIVATE":"MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAKrtYYICD7bNfCYE\nwF56iS5GIohF6oI05B4IUXvvuR3akJnMDGK5vWGKrUUNiO3uKlpmJ/tk4i9encTI\n0xKFeNcedYz6W7lZVntVIVoPc8HsaQKpWgBslLNbw5xh/kOZRfj6x3kXs4cOrd4l\n8FKfu4ybR0CWwFJSuGSytz5F3jxpAgMBAAECgYEAjaa8MCnVLAGO6VL2uVWDaj3l\nUFngxpdelST/ol5AY2VGgK6BdS/IqpVqktdsqwG9R71xVP1+FROsW/CyO58xTHm3\nXNw20qs985rkaqt2uxChel6pH8dKYHstkqJ6qBn8JiPKn9nl44lSLXHUzLlcFKAj\n1ZbMx7IAxAhgfNqN1AECQQDilOx7fE4+yhg2ediE0iu6/Qn0NUibXz1V1L7kN9//\nlo2MxNba82eWGunJ2KFvANi1tP6El4dimUytz3i1nKT5AkEAwR6gLVSeyq4HOKKT\n5+oRigOoKZtuK+L09cP2t76438O0vAZP3JFghWfShEdcqFXhgO0lLJTfASAorkTF\n2c/e8QJBAIqdKtMcp82X2xowjnorDV+2f4lrs4CB0kcZALMjT6DP3Ma6gOqvr7ZP\nvqiiI5iB/xGiqXY6MTPrrtJCM4l/TBkCQQCxRyzhD/oIsoKcgdnmnd9l439+Wllj\nXh9icHbOK/1IJD6ID/9FygphzHHaziuHt/afRJLIfwzL2rqdZzJ8hS7xAkEAvN3h\nMkWFg0/ruJq5XGAXWdwN9m4QdCMA883HKA22IOliXGz8rw3UT2u0CCw4cGYxLmqR\nBX9KIxqCaRBwwPa3Bg==","RSA_PUBLIC":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB","SELLER":"beijingheduikeji@163.com"}
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
         * PARTNER : 2088121688433844
         * RSA_PRIVATE : MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAKrtYYICD7bNfCYE
         wF56iS5GIohF6oI05B4IUXvvuR3akJnMDGK5vWGKrUUNiO3uKlpmJ/tk4i9encTI
         0xKFeNcedYz6W7lZVntVIVoPc8HsaQKpWgBslLNbw5xh/kOZRfj6x3kXs4cOrd4l
         8FKfu4ybR0CWwFJSuGSytz5F3jxpAgMBAAECgYEAjaa8MCnVLAGO6VL2uVWDaj3l
         UFngxpdelST/ol5AY2VGgK6BdS/IqpVqktdsqwG9R71xVP1+FROsW/CyO58xTHm3
         XNw20qs985rkaqt2uxChel6pH8dKYHstkqJ6qBn8JiPKn9nl44lSLXHUzLlcFKAj
         1ZbMx7IAxAhgfNqN1AECQQDilOx7fE4+yhg2ediE0iu6/Qn0NUibXz1V1L7kN9//
         lo2MxNba82eWGunJ2KFvANi1tP6El4dimUytz3i1nKT5AkEAwR6gLVSeyq4HOKKT
         5+oRigOoKZtuK+L09cP2t76438O0vAZP3JFghWfShEdcqFXhgO0lLJTfASAorkTF
         2c/e8QJBAIqdKtMcp82X2xowjnorDV+2f4lrs4CB0kcZALMjT6DP3Ma6gOqvr7ZP
         vqiiI5iB/xGiqXY6MTPrrtJCM4l/TBkCQQCxRyzhD/oIsoKcgdnmnd9l439+Wllj
         Xh9icHbOK/1IJD6ID/9FygphzHHaziuHt/afRJLIfwzL2rqdZzJ8hS7xAkEAvN3h
         MkWFg0/ruJq5XGAXWdwN9m4QdCMA883HKA22IOliXGz8rw3UT2u0CCw4cGYxLmqR
         BX9KIxqCaRBwwPa3Bg==
         * RSA_PUBLIC : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB
         * SELLER : beijingheduikeji@163.com
         */

        private String PARTNER;
        private String RSA_PRIVATE;
        private String RSA_PUBLIC;
        private String SELLER;

        public String getPARTNER() {
            return PARTNER;
        }

        public void setPARTNER(String PARTNER) {
            this.PARTNER = PARTNER;
        }

        public String getRSA_PRIVATE() {
            return RSA_PRIVATE;
        }

        public void setRSA_PRIVATE(String RSA_PRIVATE) {
            this.RSA_PRIVATE = RSA_PRIVATE;
        }

        public String getRSA_PUBLIC() {
            return RSA_PUBLIC;
        }

        public void setRSA_PUBLIC(String RSA_PUBLIC) {
            this.RSA_PUBLIC = RSA_PUBLIC;
        }

        public String getSELLER() {
            return SELLER;
        }

        public void setSELLER(String SELLER) {
            this.SELLER = SELLER;
        }
    }
}
