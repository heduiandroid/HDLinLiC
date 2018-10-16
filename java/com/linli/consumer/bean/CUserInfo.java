package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/2/20.
 */

public class CUserInfo {

    /**
     * status : 1
     * data : {"id":314847024078253,"nickname":"丽丽","password":"73af6672d80b7af00817224301b15752","loginNum":0,"avatar":"http://obqlfysk2.bkt.clouddn.com/Fn49gQPAw5fxSDElQhLjIwexX4tv","status":0,"salt":"30f23d02185cd01bd9ea91406ff3bffe","phone":15931286776,"sex":0,"regionId":502,"credentialsSalt":"30f23d02185cd01bd9ea91406ff3bffe30f23d02185cd01bd9ea91406ff3bffe"}
     */

    private int status;
    private DataBean data;

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

    public static class DataBean {
        /**
         * id : 314847024078253
         * nickname : 丽丽
         * password : 73af6672d80b7af00817224301b15752
         * loginNum : 0
         * avatar : http://obqlfysk2.bkt.clouddn.com/Fn49gQPAw5fxSDElQhLjIwexX4tv
         * status : 0
         * salt : 30f23d02185cd01bd9ea91406ff3bffe
         * phone : 15931286776
         * sex : 0
         * regionId : 502
         * credentialsSalt : 30f23d02185cd01bd9ea91406ff3bffe30f23d02185cd01bd9ea91406ff3bffe
         */

        private long id;
        private String nickname;
        private String password;
        private int loginNum;
        private String avatar;
        private int status;
        private String salt;
        private Long phone;
        private int sex;
        private int regionId;
        private String credentialsSalt;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getLoginNum() {
            return loginNum;
        }

        public void setLoginNum(int loginNum) {
            this.loginNum = loginNum;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public Long getPhone() {
            return phone;
        }

        public void setPhone(Long phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public String getCredentialsSalt() {
            return credentialsSalt;
        }

        public void setCredentialsSalt(String credentialsSalt) {
            this.credentialsSalt = credentialsSalt;
        }
    }
}
