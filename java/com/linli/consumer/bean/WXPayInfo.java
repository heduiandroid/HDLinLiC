package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/1/17.
 */

public class WXPayInfo {
    /**
     * status : success
     * msg : ????
     * obj : {"appid":"wxd05de5a657c44192","timestamp":"1484634716807","partnerid":"1370401102","noncestr":"5K8264ILTKCH16CQ2502SI8ZNMTM67VS","prepayid":"wx201701171431560826ac38ab0882098265","sign":"C7A070A909B68B17122A9AE99AD30DFB"}
     */

    private String status;
    private String msg;
    private ObjBean obj;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * appid : wxd05de5a657c44192
         * timestamp : 1484634716807
         * partnerid : 1370401102
         * noncestr : 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
         * prepayid : wx201701171431560826ac38ab0882098265
         * sign : C7A070A909B68B17122A9AE99AD30DFB
         */

        private String appid;
        private String timestamp;
        private String partnerid;
        private String noncestr;
        private String prepayid;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
