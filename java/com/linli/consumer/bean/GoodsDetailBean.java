package com.linli.consumer.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tomoyo on 2017/2/12.
 * 商品的实体类
 */

public class GoodsDetailBean implements Serializable{

    /**
     * id : 314868700445992
     * name : 测试商品
     * storeId : 414846522510124
     * isShow : 1
     * isPat : 0
     * isSpec: 0 : 无规格   1 : 有规格
     * primaryImage : http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw
     * productImage1 : http://obqlfysk2.bkt.clouddn.com/FnUZQS4uDokepOu-NjFHrqaZUuKm
     * productImage2 : http://obqlfysk2.bkt.clouddn.com/Fpv1Jogk9j2F9TvSf2KGJjNn2itK
     * productImage3 : http://obqlfysk2.bkt.clouddn.com/FmwZeAfa_GvRQMPqrJK-NP78IiGI
     * info : 这只是一个测试的商品，不要太当真
     * approveStatus : 0
     * brand : 李宁
     * status : 0
     * salesVolume : 0
     * storeCategoryId : 314848794346395
     * storeCategoryName : 每日狗狗
     * goodsSpecs : [{"id":314868700446623,"platformPrice":1,"inventory":1,"specaNameId":214847200896048,"specaValue":"36cm大小","specbNameId":214847201108239,"specbValue":"89ml","speccNameId":314867856422941,"speccValue":"88米","anticipatedRevenue":0},{"id":314868700447404,"platformPrice":2,"inventory":2,"specaNameId":214847200896048,"specaValue":"985cm大小","specbNameId":214847201108239,"specbValue":"110ml","speccNameId":314867856422941,"speccValue":"199米","anticipatedRevenue":0},{"id":314868700447865,"platformPrice":3,"inventory":3,"specaNameId":214847200896048,"specaValue":"958cm大小","specbNameId":214847201108239,"specbValue":"120ml","speccNameId":314867856422941,"speccValue":"211米","anticipatedRevenue":0}]
     * storeName : 晚安
     * goodsType : 0
     * specNameValues : ["大小","尺寸","净含量"]
     * inventory : 6
     */

    private long id;
    private String name;
    private long storeId;
    private int isShow;
    private int isPat;
    private String primaryImage;
    private String productImage1;
    private String productImage2;
    private String productImage3;
    private String info;
    private int approveStatus;
    private String brand;
    private int status;
    private Integer salesVolume;
    private long storeCategoryId;
    private String storeCategoryName;
    private String storeName;
    private int goodsType;
    private int inventory;

    private int isSpec; //  0 : 无规格   1 : 有规格

    private BigDecimal maxprice;
    private BigDecimal minprice;        //主页展示的时候显示最小值
    private Boolean isSpecialPush; //是否是特推商品
    private Integer isRecommend;
    private BigDecimal nospecStockPrice;//商品返红包比率
    private Long salescategoryId;

    public Long getSalescategoryId() {
        return salescategoryId;
    }

    public void setSalescategoryId(Long salescategoryId) {
        this.salescategoryId = salescategoryId;
    }

    public BigDecimal getNospecStockPrice() {
        return nospecStockPrice;
    }

    public void setNospecStockPrice(BigDecimal nospecStockPrice) {
        this.nospecStockPrice = nospecStockPrice;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Boolean getSpecialPush() {
        return isSpecialPush;
    }

    public void setSpecialPush(Boolean specialPush) {
        isSpecialPush = specialPush;
    }

    public int getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(int isSpec) {
        this.isSpec = isSpec;
    }

    public BigDecimal getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(BigDecimal maxprice) {
        this.maxprice = maxprice;
    }

    public BigDecimal getMinprice() {
        return minprice;
    }

    public void setMinprice(BigDecimal minprice) {
        this.minprice = minprice;
    }

    /**
     * id : 314868700446623
     * platformPrice : 1
     * inventory : 1
     * specaNameId : 214847200896048
     * specaValue : 36cm大小
     * specbNameId : 214847201108239
     * specbValue : 89ml
     * speccNameId : 314867856422941
     * speccValue : 88米
     * anticipatedRevenue : 0
     */

    private List<GoodsSpecsBean> goodsSpecs;
    private List<String> specNameValues;

    private int number;        //自定义新增属性，用于本地存放数量数据
    private boolean isChoice;      //自定义新增字段，是否显示标志 true为显示(有数量),false为不显示(0)

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

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

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getIsPat() {
        return isPat;
    }

    public void setIsPat(int isPat) {
        this.isPat = isPat;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public String getProductImage1() {
        return productImage1;
    }

    public void setProductImage1(String productImage1) {
        this.productImage1 = productImage1;
    }

    public String getProductImage2() {
        return productImage2;
    }

    public void setProductImage2(String productImage2) {
        this.productImage2 = productImage2;
    }

    public String getProductImage3() {
        return productImage3;
    }

    public void setProductImage3(String productImage3) {
        this.productImage3 = productImage3;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(int approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public long getStoreCategoryId() {
        return storeCategoryId;
    }

    public void setStoreCategoryId(long storeCategoryId) {
        this.storeCategoryId = storeCategoryId;
    }

    public String getStoreCategoryName() {
        return storeCategoryName;
    }

    public void setStoreCategoryName(String storeCategoryName) {
        this.storeCategoryName = storeCategoryName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public List<GoodsSpecsBean> getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(List<GoodsSpecsBean> goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }

    public List<String> getSpecNameValues() {
        return specNameValues;
    }

    public void setSpecNameValues(List<String> specNameValues) {
        this.specNameValues = specNameValues;
    }

    public static class GoodsSpecsBean implements Serializable{
        private long id;
        private BigDecimal platformPrice;
        private int inventory;
        private long specaNameId;
        private String specaValue;
        private long specbNameId;
        private String specbValue;
        private long speccNameId;
        private String speccValue;
        private int anticipatedRevenue;
        private int specNumber;     //规格的数量
        private BigDecimal stockPrice;//红包可抵金额

        public BigDecimal getStockPrice() {
            return stockPrice;
        }

        public void setStockPrice(BigDecimal stockPrice) {
            this.stockPrice = stockPrice;
        }

        public int getNumber() {
            return specNumber;
        }

        public void setNumber(int number) {
            this.specNumber = number;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public BigDecimal getPlatformPrice() {
            return platformPrice;
        }

        public void setPlatformPrice(BigDecimal platformPrice) {
            this.platformPrice = platformPrice;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public long getSpecaNameId() {
            return specaNameId;
        }

        public void setSpecaNameId(long specaNameId) {
            this.specaNameId = specaNameId;
        }

        public String getSpecaValue() {
            return specaValue;
        }

        public void setSpecaValue(String specaValue) {
            this.specaValue = specaValue;
        }

        public long getSpecbNameId() {
            return specbNameId;
        }

        public void setSpecbNameId(long specbNameId) {
            this.specbNameId = specbNameId;
        }

        public String getSpecbValue() {
            return specbValue;
        }

        public void setSpecbValue(String specbValue) {
            this.specbValue = specbValue;
        }

        public long getSpeccNameId() {
            return speccNameId;
        }

        public void setSpeccNameId(long speccNameId) {
            this.speccNameId = speccNameId;
        }

        public String getSpeccValue() {
            return speccValue;
        }

        public void setSpeccValue(String speccValue) {
            this.speccValue = speccValue;
        }

        public int getAnticipatedRevenue() {
            return anticipatedRevenue;
        }

        public void setAnticipatedRevenue(int anticipatedRevenue) {
            this.anticipatedRevenue = anticipatedRevenue;
        }
    }
}
