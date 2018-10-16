package com.linli.consumer.bean;

/**
 * Created by hasee on 2018/4/19.
 */

public class DriverInfoBean {

    /**
     * status : 1
     * data : {"id":715241259645621,"phone":"18801071735","img":"http://obqlfysk2.bkt.clouddn.com/FqFoRhqWuoQKwewd4faPn28_zDCf","surname":"刘","name":"诗诗","licensePlateNumber":"京B 3CP12","vehicleBrand":"日产蓝鸟","vehicleColor":"白色","createTime":1524125964000}
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
         * id : 715241259645621
         * phone : 18801071735
         * img : http://obqlfysk2.bkt.clouddn.com/FqFoRhqWuoQKwewd4faPn28_zDCf
         * surname : 刘
         * name : 诗诗
         * licensePlateNumber : 京B 3CP12
         * vehicleBrand : 日产蓝鸟
         * vehicleColor : 白色
         * createTime : 1524125964000
         */

        private Long id;
        private String phone;
        private String img;
        private String surname;
        private String name;
        private String licensePlateNumber;
        private String vehicleBrand;
        private String vehicleColor;
        private Long createTime;
        private Float average;//司机平均分

        public Float getAverage() {
            return average;
        }

        public void setAverage(Float average) {
            this.average = average;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLicensePlateNumber() {
            return licensePlateNumber;
        }

        public void setLicensePlateNumber(String licensePlateNumber) {
            this.licensePlateNumber = licensePlateNumber;
        }

        public String getVehicleBrand() {
            return vehicleBrand;
        }

        public void setVehicleBrand(String vehicleBrand) {
            this.vehicleBrand = vehicleBrand;
        }

        public String getVehicleColor() {
            return vehicleColor;
        }

        public void setVehicleColor(String vehicleColor) {
            this.vehicleColor = vehicleColor;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }
    }
}
