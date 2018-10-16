package com.linli.consumer.domain;

import java.io.Serializable;

/**
 * 商品信息实体
 * Created by Administrator on 2016/3/4.
 */
public class Product implements Serializable {
    private Long id;//商品ID
    private Long shopId;//商品所属商家ID
    private Long shopCartID;//商品在购物车中的ID
    private Long specId;//规格id
    private String shopLogo;//商品所属商家Logo
    private Integer shopCategory;
    private Integer shopCartNum;//在购物车中的商品数量
    private String shopName;//商品所属商家名称
    private String name;//商品名称
    private Double price;//商品价格
    private Double oldprice;//原价
    private String bitvalue;//计量单位
    private String picPath;//图片路径
    private String picPath1;//图片路径1
    private String picPath2;//图片路径2
    private String picPath3;//图片路径3
    private Integer residueNum;//库存
    private Integer soldNumber;//已售数量
    private String productArea;//产地
    private String packageMethod;//包装方式
    private String detailsText;//商品文字详细介绍
    private String netContent;//净含量
    private Boolean haveSuger;//是否含糖
    private String kind;//种类
    private String specification;//规格
    private String productData;//生产日期
    private String expirationDate;//保质期
    private String burdening;//配料
    private String makeMethod;//加工工艺
    private String material;//加工原料
    private String standerdNum;//展品标准号
    private String storageMethod;//存储方式
    private String brand;//品牌
    private Integer countInOrder;//订单中此商品的数量
    private String createTime;
    private Long collectId;//可作为数据库Id&收藏id等的冗余字段
    private Integer isEvaled;//是否已评价
    private Integer praiseNum;//已点赞数量
    private int isPackageCost;      //美食专用字段，判断是否有打包费 0 : 没有     1 : 需要打包费
    private int type;//商品所属商家类型
    private int ispackage;//0否 1是 是否要打包费

    public int getIspackage() {
        return ispackage;
    }

    public void setIspackage(int ispackage) {
        this.ispackage = ispackage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getIsEvaled() {
        return isEvaled;
    }

    public void setIsEvaled(Integer isEvaled) {
        this.isEvaled = isEvaled;
    }

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Integer getCountInOrder() {
        return countInOrder;
    }

    public void setCountInOrder(Integer countInOrder) {
        this.countInOrder = countInOrder;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", oldprice=" + oldprice +
                ", picPath='" + picPath + '\'' +
                ", residueNum=" + residueNum +
                ", soldNumber=" + soldNumber +
                ", productArea='" + productArea + '\'' +
                ", packageMethod='" + packageMethod + '\'' +
                ", detailsText='" + detailsText + '\'' +
                ", netContent='" + netContent + '\'' +
                ", haveSuger=" + haveSuger +
                ", kind='" + kind + '\'' +
                ", specification='" + specification + '\'' +
                ", productData='" + productData + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", burdening='" + burdening + '\'' +
                ", makeMethod='" + makeMethod + '\'' +
                ", material='" + material + '\'' +
                ", standerdNum='" + standerdNum + '\'' +
                ", storageMethod='" + storageMethod + '\'' +
                ", brand='" + brand + '\'' +
                ", isPackageCost='" + isPackageCost + '\'' +
                '}';
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public Integer getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(Integer shopCategory) {
        this.shopCategory = shopCategory;
    }

    public String getBitvalue() {
        return bitvalue;
    }

    public void setBitvalue(String bitvalue) {
        this.bitvalue = bitvalue;
    }

    public String getPicPath1() {
        return picPath1;
    }

    public void setPicPath1(String picPath1) {
        this.picPath1 = picPath1;
    }

    public String getPicPath2() {
        return picPath2;
    }

    public void setPicPath2(String picPath2) {
        this.picPath2 = picPath2;
    }

    public String getPicPath3() {
        return picPath3;
    }

    public void setPicPath3(String picPath3) {
        this.picPath3 = picPath3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopCartID() {
        return shopCartID;
    }

    public void setShopCartID(Long shopCartID) {
        this.shopCartID = shopCartID;
    }

    public Integer getShopCartNum() {
        return shopCartNum;
    }

    public void setShopCartNum(Integer shopCartNum) {
        this.shopCartNum = shopCartNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOldprice() {
        return oldprice;
    }

    public void setOldprice(Double oldprice) {
        this.oldprice = oldprice;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Integer getResidueNum() {
        return residueNum;
    }

    public void setResidueNum(Integer residueNum) {
        this.residueNum = residueNum;
    }

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

    public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea;
    }

    public String getPackageMethod() {
        return packageMethod;
    }

    public void setPackageMethod(String packageMethod) {
        this.packageMethod = packageMethod;
    }

    public String getDetailsText() {
        return detailsText;
    }

    public void setDetailsText(String detailsText) {
        this.detailsText = detailsText;
    }

    public String getNetContent() {
        return netContent;
    }

    public void setNetContent(String netContent) {
        this.netContent = netContent;
    }

    public Boolean getHaveSuger() {
        return haveSuger;
    }

    public void setHaveSuger(Boolean haveSuger) {
        this.haveSuger = haveSuger;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getProductData() {
        return productData;
    }

    public void setProductData(String productData) {
        this.productData = productData;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBurdening() {
        return burdening;
    }

    public void setBurdening(String burdening) {
        this.burdening = burdening;
    }

    public String getMakeMethod() {
        return makeMethod;
    }

    public void setMakeMethod(String makeMethod) {
        this.makeMethod = makeMethod;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getStanderdNum() {
        return standerdNum;
    }

    public void setStanderdNum(String standerdNum) {
        this.standerdNum = standerdNum;
    }

    public String getStorageMethod() {
        return storageMethod;
    }

    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getIsPackageCost() {
        return isPackageCost;
    }

    public void setIsPackageCost(int isPackageCost) {
        this.isPackageCost = isPackageCost;
    }
}
