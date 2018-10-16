package com.linli.consumer.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/28.
 */
public class CookBook implements Serializable{
    private Long id;
    private String name;
    private Long shopId;//菜谱所属商家ID
    private String signName;//舍签名字
    private Double price;//菜品价格
    private Boolean isTakeout;//是否外卖
    private String cuisine;//菜系
    private String maining;//主料
    private String accessories;//辅料
    private String imgpath;//图片路径
    private String videopath;//视频路径
    private Integer difficulty;//难易程度
    private String maketime;//制作时长
    private String dishes;//菜式
    private String flavor;//口味
    private String cookway;//烹制方法
    private String regional;//地域
    private Integer ambient;//环境
    private Integer age;//适合年龄
    private Integer dinnertime;//餐饮时间
    private Integer physique;//适合体质
    private Long createtime;
    private String makemethod;//制作方法
    private Long collectId;
    private int isPackageCost;      //美食专用字段，判断是否有打包费 0 : 没有     1 : 需要打包费

    public int getIsPackageCost() {
        return isPackageCost;
    }

    public void setIsPackageCost(int isPackageCost) {
        this.isPackageCost = isPackageCost;
    }

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getMakemethod() {
        return makemethod;
    }

    public void setMakemethod(String makemethod) {
        this.makemethod = makemethod;
    }

    public Boolean getIsTakeout() {
        return isTakeout;
    }

    public void setIsTakeout(Boolean isTakeout) {
        this.isTakeout = isTakeout;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMaining() {
        return maining;
    }

    public void setMaining(String maining) {
        this.maining = maining;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getMaketime() {
        return maketime;
    }

    public void setMaketime(String maketime) {
        this.maketime = maketime;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getCookway() {
        return cookway;
    }

    public void setCookway(String cookway) {
        this.cookway = cookway;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public Integer getAmbient() {
        return ambient;
    }

    public void setAmbient(Integer ambient) {
        this.ambient = ambient;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDinnertime() {
        return dinnertime;
    }

    public void setDinnertime(Integer dinnertime) {
        this.dinnertime = dinnertime;
    }

    public Integer getPhysique() {
        return physique;
    }

    public void setPhysique(Integer physique) {
        this.physique = physique;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
