package com.linli.consumer.bean;

/**
 * Created by hasee on 2017/2/27.
 */

public class LeaguerAdd {

    /**
     * status : 1
     * msg : 成为商家会员成功
     * data : {"id":314913798817361,"storeId":314902375391807,"memberId":314888496711754,"memberNumber":316393755273528,"name":"hsh","phone":"15931286776","points":0,"address":"未填写","createTime":1491379881736,"status":0,"region_id":508}
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
         * id : 314913798817361
         * storeId : 314902375391807
         * memberId : 314888496711754
         * memberNumber : 316393755273528
         * name : hsh
         * phone : 15931286776
         * points : 0
         * address : 未填写
         * createTime : 1491379881736
         * status : 0
         * region_id : 508
         */

        private long id;
        private long storeId;
        private long memberId;
        private String memberNumber;
        private String name;
        private String phone;
        private int points;
        private String address;
        private long createTime;
        private int status;
        private int region_id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getStoreId() {
            return storeId;
        }

        public void setStoreId(long storeId) {
            this.storeId = storeId;
        }

        public long getMemberId() {
            return memberId;
        }

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }

        public String getMemberNumber() {
            return memberNumber;
        }

        public void setMemberNumber(String memberNumber) {
            this.memberNumber = memberNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }
    }
}
