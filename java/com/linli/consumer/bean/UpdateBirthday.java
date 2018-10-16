package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/2/24.
 */

public class UpdateBirthday {

    /**
     * status : 1
     * data : {"tag":0,"type":0,"sex":0,"birthday":"2017-02-18 00:00:00","memId":314879263004125}
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
         * tag : 0
         * type : 0
         * sex : 0
         * birthday : 2017-02-18 00:00:00
         * memId : 314879263004125
         */

        private int tag;
        private int type;
        private int sex;
        private String birthday;
        private long memId;

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public long getMemId() {
            return memId;
        }

        public void setMemId(long memId) {
            this.memId = memId;
        }
    }
}
