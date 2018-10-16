package com.linli.consumer.bean;

/**
 * Created by hasee on 2016/12/23.
 */

public class AddrNew {

    /**
     * status : 1
     * msg : null
     * page : null
     * data : {"id":314824787710841,"name":"啊啊啊","phone":"15833332222","provinceId":1,"cityId":1,"areaId":1,"address":"啊啊啊","postcode":null,"memberId":314818679941362,"isDefault":null,"type":1}
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
         * id : 314824787710841
         * name : 啊啊啊
         * phone : 15833332222
         * provinceId : 1
         * cityId : 1
         * areaId : 1
         * address : 啊啊啊
         * postcode : null
         * memberId : 314818679941362
         * isDefault : null
         * type : 1
         */

        private long id;
        private String name;
        private String phone;
        private int provinceId;
        private int cityId;
        private int areaId;
        private String address;
        private Object postcode;
        private long memberId;
        private Object isDefault;
        private int type;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getPostcode() {
            return postcode;
        }

        public void setPostcode(Object postcode) {
            this.postcode = postcode;
        }

        public long getMemberId() {
            return memberId;
        }

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }

        public Object getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(Object isDefault) {
            this.isDefault = isDefault;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
