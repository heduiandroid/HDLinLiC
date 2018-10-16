package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2018/7/24.
 */

public class DirectGoodBean {

    /**
     * status : 1
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":20,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":3}
     * data : [{"id":715323530618975,"name":"111","storeId":999,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/FgavN9BMAKEzl8Lnr5Q5XyRV1sGv","productImage1":"","productImage2":"","productImage3":"","info":"111","brand":"1111","isRecommend":0,"nospecStockPrice":0,"isSpec":1,"createTime":1532353061000,"modifyTime":1532353061000,"status":0,"salesVolume":0,"storeCategoryId":715151449731402,"goodsSpecs":[],"unit":"单位"},{"id":715323512402981,"name":"111","storeId":999,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/FgOHIu_JZ0_d1n9NZV1ytmQd8-Vr","productImage1":"","productImage2":"","productImage3":"","info":"111","brand":"1","isRecommend":0,"nospecStockPrice":0,"isSpec":0,"createTime":1532351240000,"modifyTime":1532351240000,"status":0,"salesVolume":0,"storeCategoryId":715145357201970,"goodsSpecs":[],"unit":"单位"},{"id":715323505237290,"name":"111","storeId":999,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/FgOHIu_JZ0_d1n9NZV1ytmQd8-Vr","productImage1":"","productImage2":"","productImage3":"","info":"111","brand":"11","isRecommend":0,"nospecStockPrice":0,"isSpec":0,"createTime":1532350523000,"modifyTime":1532350523000,"status":0,"salesVolume":0,"storeCategoryId":715145357201970,"goodsSpecs":[],"unit":"单位"}]
     */

    private int status;
    private PageBean page;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * plainPageNum : 1
         * pageNum : 1
         * numPerPage : 20
         * orderField :
         * orderDirection :
         * totalPage : 1
         * prePage : 1
         * nextPage : 1
         * totalCount : 3
         */

        private int plainPageNum;
        private int pageNum;
        private int numPerPage;
        private String orderField;
        private String orderDirection;
        private int totalPage;
        private int prePage;
        private int nextPage;
        private int totalCount;

        public int getPlainPageNum() {
            return plainPageNum;
        }

        public void setPlainPageNum(int plainPageNum) {
            this.plainPageNum = plainPageNum;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getNumPerPage() {
            return numPerPage;
        }

        public void setNumPerPage(int numPerPage) {
            this.numPerPage = numPerPage;
        }

        public String getOrderField() {
            return orderField;
        }

        public void setOrderField(String orderField) {
            this.orderField = orderField;
        }

        public String getOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(String orderDirection) {
            this.orderDirection = orderDirection;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class DataBean {
        /**
         * id : 715323530618975
         * name : 111
         * storeId : 999
         * isShow : 1
         * isPat : 0
         * primaryImage : http://obqlfysk2.bkt.clouddn.com/FgavN9BMAKEzl8Lnr5Q5XyRV1sGv
         * productImage1 :
         * productImage2 :
         * productImage3 :
         * info : 111
         * brand : 1111
         * isRecommend : 0
         * nospecStockPrice : 0.0
         * isSpec : 1
         * createTime : 1532353061000
         * modifyTime : 1532353061000
         * status : 0
         * salesVolume : 0
         * storeCategoryId : 715151449731402
         * goodsSpecs : []
         * unit : 单位
         */

        private Long id;
        private String name;
        private Integer storeId;
        private Integer isShow;
        private Integer isPat;
        private String primaryImage;
        private String productImage1;
        private String productImage2;
        private String productImage3;
        private String info;
        private String brand;
        private Integer isRecommend;
        private Double nospecStockPrice;
        private Integer isSpec;
        private Long createTime;
        private Long modifyTime;
        private Integer status;
        private Integer salesVolume;
        private Long storeCategoryId;
        private String unit;
        private Double minprice;
        private Double maxprice;
        private Long salescategoryId;

        public Long getSalescategoryId() {
            return salescategoryId;
        }

        public void setSalescategoryId(Long salescategoryId) {
            this.salescategoryId = salescategoryId;
        }

        public Double getMinprice() {
            return minprice;
        }

        public void setMinprice(Double minprice) {
            this.minprice = minprice;
        }

        public Double getMaxprice() {
            return maxprice;
        }

        public void setMaxprice(Double maxprice) {
            this.maxprice = maxprice;
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

        public Integer getStoreId() {
            return storeId;
        }

        public void setStoreId(Integer storeId) {
            this.storeId = storeId;
        }

        public Integer getIsShow() {
            return isShow;
        }

        public void setIsShow(Integer isShow) {
            this.isShow = isShow;
        }

        public Integer getIsPat() {
            return isPat;
        }

        public void setIsPat(Integer isPat) {
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

        public Double getNospecStockPrice() {
            return nospecStockPrice;
        }

        public void setNospecStockPrice(Double nospecStockPrice) {
            this.nospecStockPrice = nospecStockPrice;
        }

        public Integer getIsSpec() {
            return isSpec;
        }

        public void setIsSpec(Integer isSpec) {
            this.isSpec = isSpec;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Long modifyTime) {
            this.modifyTime = modifyTime;
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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
