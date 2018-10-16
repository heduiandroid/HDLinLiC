package com.linli.consumer.bean;

/**
 * Created by hasee on 2016/12/16.
 */

public class Login {

    /**
     * responseCode : 200
     * responseMessage : 成功
     * responseData : {"personalCode":"159130681201810116776","personalPhone":"15931286776","personalAvatar":null,"personalRealName":null,"invitationCode":"ee008E3O","personalIdcard":"130681199005111011","openId":"b72e75427525b5a32e728cc2183fed21"}
     */

    private String responseCode;
    private String responseMessage;
    private ResponseDataBean responseData;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseDataBean getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseDataBean responseData) {
        this.responseData = responseData;
    }

    public static class ResponseDataBean {
        /**
         * personalCode : 159130681201810116776
         * personalPhone : 15931286776
         * personalAvatar : null
         * personalRealName : null
         * invitationCode : ee008E3O
         * personalIdcard : 130681199005111011
         * openId : b72e75427525b5a32e728cc2183fed21
         */

        private String personalCode;
        private String personalPhone;
        private String personalAvatar;
        private String personalRealName;
        private String invitationCode;
        private String personalIdcard;
        private String openId;

        public String getPersonalCode() {
            return personalCode;
        }

        public void setPersonalCode(String personalCode) {
            this.personalCode = personalCode;
        }

        public String getPersonalPhone() {
            return personalPhone;
        }

        public void setPersonalPhone(String personalPhone) {
            this.personalPhone = personalPhone;
        }

        public String getPersonalAvatar() {
            return personalAvatar;
        }

        public void setPersonalAvatar(String personalAvatar) {
            this.personalAvatar = personalAvatar;
        }

        public String getPersonalRealName() {
            return personalRealName;
        }

        public void setPersonalRealName(String personalRealName) {
            this.personalRealName = personalRealName;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }

        public String getPersonalIdcard() {
            return personalIdcard;
        }

        public void setPersonalIdcard(String personalIdcard) {
            this.personalIdcard = personalIdcard;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }
    }
}
