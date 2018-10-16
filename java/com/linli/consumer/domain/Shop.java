package com.linli.consumer.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class Shop implements Serializable {
    private Long id;
    private Long collectionId;
    private Long shopUserId;
    private String shopName;//商店名称
    private String shopAddr;//地址
    private String shopPhone;//商家电话
    private String logoPath;//商家LOGO 地址路径
    private Integer level;
    private Double distance_d;
    private String distance;//商家距离
    private Long prodsNumber;//商家拥有商品数量
    private String introduce;
    private String longitude;//商家经度
    private String latitude;//商家纬度
    private Integer category;//商家类别 1零售商贸  2美食餐饮  3生活服务
    private Integer type;//主营类型 0-中式正餐1-中式快餐2-西式快餐3-早点小吃4-糕点饮品
    private Integer cuisine;//菜系
    private String shophours;//营业时间
    private String consume;//人均消费
    private Integer parking;//停车类型 0-收费泊车1-免费泊车2-无车位
    private Integer bill;//有无发票0-有发票 1-无发票
    private Integer destine;//可提前多少天预订 0-12天 1-3天 2-无限制
    private Boolean timeoutFree;//超时免单 true false
    private Integer paykind;//支付方式
    private Boolean istakeout;//是否外卖
    private Double shopTotal;//在本商铺购买商品订单总价
    private Double freight;//该商铺配送费用
    private String sendtype;//配送方式 0到店自取 1送货上门
    private String areacode;//商家所属地区码
    private String createTime;
    private Long regionId;//区域ID
    private Integer isActivity;
    private List<Product> recommends;//商家推荐的商品

    public Double getDistance_d() {
        return distance_d;
    }

    public void setDistance_d(Double distance_d) {
        this.distance_d = distance_d;
    }

    public Integer getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(Integer isActivity) {
        this.isActivity = isActivity;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getSendtype() {
        return sendtype;
    }

    public void setSendtype(String sendtype) {
        this.sendtype = sendtype;
    }

    public Double getShopTotal() {
        return shopTotal;
    }

    public void setShopTotal(Double shopTotal) {
        this.shopTotal = shopTotal;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getPaykind() {
        return paykind;
    }

    public void setPaykind(Integer paykind) {
        this.paykind = paykind;
    }

    public Long getShopUserId() {
        return shopUserId;
    }

    public void setShopUserId(Long shopUserId) {
        this.shopUserId = shopUserId;
    }

    public Boolean getIstakeout() {
        return istakeout;
    }

    public void setIstakeout(Boolean istakeout) {
        this.istakeout = istakeout;
    }

    public Integer getCuisine() {
        return cuisine;
    }

    public void setCuisine(Integer cuisine) {
        this.cuisine = cuisine;
    }

    public String getShophours() {
        return shophours;
    }

    public void setShophours(String shophours) {
        this.shophours = shophours;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public Integer getParking() {
        return parking;
    }

    public void setParking(Integer parking) {
        this.parking = parking;
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public Integer getDestine() {
        return destine;
    }

    public void setDestine(Integer destine) {
        this.destine = destine;
    }

    public Boolean getTimeoutFree() {
        return timeoutFree;
    }

    public void setTimeoutFree(Boolean timeoutFree) {
        this.timeoutFree = timeoutFree;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<Product> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<Product> recommends) {
        this.recommends = recommends;
    }

    public Long getProdsNumber() {
        return prodsNumber;
    }

    public void setProdsNumber(Long prodsNumber) {
        this.prodsNumber = prodsNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
