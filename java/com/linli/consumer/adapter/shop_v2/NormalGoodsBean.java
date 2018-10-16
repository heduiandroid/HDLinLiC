package com.linli.consumer.adapter.shop_v2;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hasee on 2018/7/25.
 */

public class NormalGoodsBean {

    /**
     * status : 1
     * data : [{"id":715325022849285,"name":"三个规格","storeId":715051165247906,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/FgOHIu_JZ0_d1n9NZV1ytmQd8-Vr","productImage1":"","productImage2":"","productImage3":"","info":"11","brand":"111","isRecommend":1,"nospecStockPrice":0,"isSpec":0,"createTime":1532502284000,"modifyTime":1532502284000,"status":0,"salesVolume":0,"storeCategoryId":715051165682502,"goodsSpecs":[],"salescategoryId":0,"unit":"单位"},{"id":715325022364671,"name":"一个规格","storeId":715051165247906,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/FgOHIu_JZ0_d1n9NZV1ytmQd8-Vr","productImage1":"","productImage2":"","productImage3":"","info":"1","brand":"111","isRecommend":1,"nospecStockPrice":0,"isSpec":0,"createTime":1532502236000,"modifyTime":1532502236000,"status":0,"salesVolume":0,"storeCategoryId":715051165682502,"goodsSpecs":[],"salescategoryId":0,"unit":"单位"},{"id":715325022158469,"name":"测试商品 没有规格","storeId":715051165247906,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/FgOHIu_JZ0_d1n9NZV1ytmQd8-Vr","productImage1":"","productImage2":"","productImage3":"","info":"1","brand":"11","isRecommend":1,"nospecStockPrice":0,"isSpec":0,"createTime":1532502215000,"modifyTime":1532502215000,"status":0,"salesVolume":0,"storeCategoryId":715051165636311,"goodsSpecs":[],"salescategoryId":0,"unit":"单位"},{"id":715296357517363,"name":"怡宝饮用纯净水1点5L","storeId":715053613723114,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/314928521775280.jpg","productImage1":"http://obqlfysk2.bkt.clouddn.com/314928521796502.jpg","productImage2":"http://obqlfysk2.bkt.clouddn.com/314928521832223.jpg","productImage3":"http://obqlfysk2.bkt.clouddn.com/314928521852974.jpg","info":"<p><img src=\"http://obqlfysk2.bkt.clouddn.com/201704221492852206227015325.jpg\" title=\"201704221492852206227015325.jpg\" alt=\"201704221492852206227015325.jpg\"/><\/p>","brand":"怡宝","isRecommend":1,"nospecStockPrice":0,"isSpec":1,"createTime":1529635751000,"modifyTime":1531724400000,"status":0,"salesVolume":0,"storeCategoryId":715102846994874,"goodsSpecs":[],"unit":"单位"}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 715325022849285
         * name : 三个规格
         * storeId : 715051165247906
         * isShow : 1
         * isPat : 0
         * primaryImage : http://obqlfysk2.bkt.clouddn.com/FgOHIu_JZ0_d1n9NZV1ytmQd8-Vr
         * productImage1 :
         * productImage2 :
         * productImage3 :
         * info : 11
         * brand : 111
         * isRecommend : 1
         * nospecStockPrice : 0
         * isSpec : 0
         * createTime : 1532502284000
         * modifyTime : 1532502284000
         * status : 0
         * salesVolume : 0
         * storeCategoryId : 715051165682502
         * goodsSpecs : []
         * salescategoryId : 0
         * unit : 单位
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
        private String brand;
        private Integer isRecommend;
        private Integer nospecStockPrice;
        private Integer isSpec;
        private Integer status;
        private Integer salesVolume;
        private Long storeCategoryId;
        private Long salescategoryId;
        private BigDecimal maxprice;
        private BigDecimal minprice;

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

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Integer getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(Integer isRecommend) {
            this.isRecommend = isRecommend;
        }

        public Integer getNospecStockPrice() {
            return nospecStockPrice;
        }

        public void setNospecStockPrice(Integer nospecStockPrice) {
            this.nospecStockPrice = nospecStockPrice;
        }

        public Integer getIsSpec() {
            return isSpec;
        }

        public void setIsSpec(Integer isSpec) {
            this.isSpec = isSpec;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(Integer salesVolume) {
            this.salesVolume = salesVolume;
        }

        public Long getStoreCategoryId() {
            return storeCategoryId;
        }

        public void setStoreCategoryId(Long storeCategoryId) {
            this.storeCategoryId = storeCategoryId;
        }

        public Long getSalescategoryId() {
            return salescategoryId;
        }

        public void setSalescategoryId(Long salescategoryId) {
            this.salescategoryId = salescategoryId;
        }
    }
}
