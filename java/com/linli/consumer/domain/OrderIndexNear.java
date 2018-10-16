package com.linli.consumer.domain;

/**
 * Created by wwy on 2016/5/20.
 * 附近推荐对应的实体类
 */
public class OrderIndexNear {
    private Long id;

    private int userid;

    private String longitude;

    private String latitude;

    private String shopname;

    private String address;

    private int category;

    private String linkman;

    private String linkphone;

    private String ordertel;

    private String orderphone;

    private int type;

    private int cuisine;

    private String shophours;

    private String consume;

    private String parking;

    private int bill;

    private String destine;

    private String license;

    private String sincerity;

    private String unsubscribe;

    private String timeoutFree;

    private String limitService;

    private String introduce;

    private String imagesrc;

    private int openstatus;

    private String postalcode;

    private String paykind;

    private boolean istakeout;

    private int shopopen;

    private int shopclose;

    private int temporary;

    private String temporaryTime;

    private String temporaryReason;

    private String qrcode;

    private String provice;

    private String city;

    private String country;

    private String assign;

    private String grab;

    private String shopcarouse;

    private String areacode;

    private String customservice;

    private int createtime;

    private int updatetime;

    private boolean deleted;
    private String distance;
    private Double beginGive;//起送价
    private Double thorn;//配送费

    public Double getBeginGive() {
        return beginGive;
    }

    public void setBeginGive(Double beginGive) {
        this.beginGive = beginGive;
    }

    public Double getThorn() {
        return thorn;
    }

    public void setThorn(Double thorn) {
        this.thorn = thorn;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return this.userid;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopname() {
        return this.shopname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return this.category;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkman() {
        return this.linkman;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getLinkphone() {
        return this.linkphone;
    }

    public void setOrdertel(String ordertel) {
        this.ordertel = ordertel;
    }

    public String getOrdertel() {
        return this.ordertel;
    }

    public void setOrderphone(String orderphone) {
        this.orderphone = orderphone;
    }

    public String getOrderphone() {
        return this.orderphone;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public void setCuisine(int cuisine) {
        this.cuisine = cuisine;
    }

    public int getCuisine() {
        return this.cuisine;
    }

    public void setShophours(String shophours) {
        this.shophours = shophours;
    }

    public String getShophours() {
        return this.shophours;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getConsume() {
        return this.consume;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getParking() {
        return this.parking;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getBill() {
        return this.bill;
    }

    public void setDestine(String destine) {
        this.destine = destine;
    }

    public String getDestine() {
        return this.destine;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicense() {
        return this.license;
    }

    public void setSincerity(String sincerity) {
        this.sincerity = sincerity;
    }

    public String getSincerity() {
        return this.sincerity;
    }

    public void setUnsubscribe(String unsubscribe) {
        this.unsubscribe = unsubscribe;
    }

    public String getUnsubscribe() {
        return this.unsubscribe;
    }

    public void setTimeoutFree(String timeoutFree) {
        this.timeoutFree = timeoutFree;
    }

    public String getTimeoutFree() {
        return this.timeoutFree;
    }

    public void setLimitService(String limitService) {
        this.limitService = limitService;
    }

    public String getLimitService() {
        return this.limitService;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public String getImagesrc() {
        return this.imagesrc;
    }

    public void setOpenstatus(int openstatus) {
        this.openstatus = openstatus;
    }

    public int getOpenstatus() {
        return this.openstatus;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPostalcode() {
        return this.postalcode;
    }

    public void setPaykind(String paykind) {
        this.paykind = paykind;
    }

    public String getPaykind() {
        return this.paykind;
    }

    public void setIstakeout(boolean istakeout) {
        this.istakeout = istakeout;
    }

    public boolean getIstakeout() {
        return this.istakeout;
    }

    public void setShopopen(int shopopen) {
        this.shopopen = shopopen;
    }

    public int getShopopen() {
        return this.shopopen;
    }

    public void setShopclose(int shopclose) {
        this.shopclose = shopclose;
    }

    public int getShopclose() {
        return this.shopclose;
    }

    public void setTemporary(int temporary) {
        this.temporary = temporary;
    }

    public int getTemporary() {
        return this.temporary;
    }

    public void setTemporaryTime(String temporaryTime) {
        this.temporaryTime = temporaryTime;
    }

    public String getTemporaryTime() {
        return this.temporaryTime;
    }

    public void setTemporaryReason(String temporaryReason) {
        this.temporaryReason = temporaryReason;
    }

    public String getTemporaryReason() {
        return this.temporaryReason;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getQrcode() {
        return this.qrcode;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getProvice() {
        return this.provice;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }

    public String getAssign() {
        return this.assign;
    }

    public void setGrab(String grab) {
        this.grab = grab;
    }

    public String getGrab() {
        return this.grab;
    }

    public void setShopcarouse(String shopcarouse) {
        this.shopcarouse = shopcarouse;
    }

    public String getShopcarouse() {
        return this.shopcarouse;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAreacode() {
        return this.areacode;
    }

    public void setCustomservice(String customservice) {
        this.customservice = customservice;
    }

    public String getCustomservice() {
        return this.customservice;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public int getCreatetime() {
        return this.createtime;
    }

    public void setUpdatetime(int updatetime) {
        this.updatetime = updatetime;
    }

    public int getUpdatetime() {
        return this.updatetime;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getDeleted() {
        return this.deleted;
    }
}
