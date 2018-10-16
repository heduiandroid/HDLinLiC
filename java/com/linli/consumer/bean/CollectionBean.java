package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/2/20.
 */

public class CollectionBean {
    /**
     * status : 1
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":10,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":0}
     * data : [{"mallCollectGoods":{"id":114875959321222,"memberId":114811598395000,"goodsSpecId":314870788456171,"status":0,"createTime":1487595932000},"mallGoods":{"id":314870788453119,"name":"苹果","storeId":414846522510124,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fpv1Jogk9j2F9TvSf2KGJjNn2itK","productImage1":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FnUZQS4uDokepOu-NjFHrqaZUuKm","productImage3":"http://obqlfysk2.bkt.clouddn.com/FmwZeAfa_GvRQMPqrJK-NP78IiGI","info":"这只是一个苹果","approveStatus":0,"brand":"地方官方的","status":0,"salesVolume":0,"storeCategoryId":214847201321701,"goodsSpecs":[{"id":314870788454420,"platformPrice":10,"inventory":1,"specaNameId":214847200746437,"specaValue":"红色","specbNameId":214847204042456,"specbValue":"200g","speccNameId":314867856422941,"speccValue":"50ml","anticipatedRevenue":0},{"id":314870788456171,"platformPrice":1010,"inventory":5,"specaNameId":214847200746437,"specaValue":"蓝色","specbNameId":214847204042456,"specbValue":"210g","speccNameId":314867856422941,"speccValue":"60ml","anticipatedRevenue":0},{"id":314870788458142,"platformPrice":3,"inventory":100,"specaNameId":214847200746437,"specaValue":"红色","specbNameId":214847204042456,"specbValue":"210g","speccNameId":314867856422941,"speccValue":"50ml","anticipatedRevenue":0}],"goodsType":0,"inventory":106}},{"mallCollectGoods":{"id":114875959369903,"memberId":114811598395000,"goodsSpecId":314870788456171,"status":0,"createTime":1487595936000},"mallGoods":{"id":314870788453119,"name":"苹果","storeId":414846522510124,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fpv1Jogk9j2F9TvSf2KGJjNn2itK","productImage1":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FnUZQS4uDokepOu-NjFHrqaZUuKm","productImage3":"http://obqlfysk2.bkt.clouddn.com/FmwZeAfa_GvRQMPqrJK-NP78IiGI","info":"这只是一个苹果","approveStatus":0,"brand":"地方官方的","status":0,"salesVolume":0,"storeCategoryId":214847201321701,"goodsSpecs":[{"id":314870788454420,"platformPrice":10,"inventory":1,"specaNameId":214847200746437,"specaValue":"红色","specbNameId":214847204042456,"specbValue":"200g","speccNameId":314867856422941,"speccValue":"50ml","anticipatedRevenue":0},{"id":314870788456171,"platformPrice":1010,"inventory":5,"specaNameId":214847200746437,"specaValue":"蓝色","specbNameId":214847204042456,"specbValue":"210g","speccNameId":314867856422941,"speccValue":"60ml","anticipatedRevenue":0},{"id":314870788458142,"platformPrice":3,"inventory":100,"specaNameId":214847200746437,"specaValue":"红色","specbNameId":214847204042456,"specbValue":"210g","speccNameId":314867856422941,"speccValue":"50ml","anticipatedRevenue":0}],"goodsType":0,"inventory":106}}]
     */

    private int status;
    /**
     * plainPageNum : 1
     * pageNum : 1
     * numPerPage : 10
     * orderField :
     * orderDirection :
     * totalPage : 1
     * prePage : 1
     * nextPage : 1
     * totalCount : 0
     */

    private PageBean page;
    /**
     * mallCollectGoods : {"id":114875959321222,"memberId":114811598395000,"goodsSpecId":314870788456171,"status":0,"createTime":1487595932000}
     * mallGoods : {"id":314870788453119,"name":"苹果","storeId":414846522510124,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fpv1Jogk9j2F9TvSf2KGJjNn2itK","productImage1":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FnUZQS4uDokepOu-NjFHrqaZUuKm","productImage3":"http://obqlfysk2.bkt.clouddn.com/FmwZeAfa_GvRQMPqrJK-NP78IiGI","info":"这只是一个苹果","approveStatus":0,"brand":"地方官方的","status":0,"salesVolume":0,"storeCategoryId":214847201321701,"goodsSpecs":[{"id":314870788454420,"platformPrice":10,"inventory":1,"specaNameId":214847200746437,"specaValue":"红色","specbNameId":214847204042456,"specbValue":"200g","speccNameId":314867856422941,"speccValue":"50ml","anticipatedRevenue":0},{"id":314870788456171,"platformPrice":1010,"inventory":5,"specaNameId":214847200746437,"specaValue":"蓝色","specbNameId":214847204042456,"specbValue":"210g","speccNameId":314867856422941,"speccValue":"60ml","anticipatedRevenue":0},{"id":314870788458142,"platformPrice":3,"inventory":100,"specaNameId":214847200746437,"specaValue":"红色","specbNameId":214847204042456,"specbValue":"210g","speccNameId":314867856422941,"speccValue":"50ml","anticipatedRevenue":0}],"goodsType":0,"inventory":106}
     */

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
         * id : 114875959321222
         * memberId : 114811598395000
         * goodsSpecId : 314870788456171
         * status : 0
         * createTime : 1487595932000
         */

        private MallCollectGoodsBean mallCollectGoods;
        /**
         * id : 314870788453119
         * name : 苹果
         * storeId : 414846522510124
         * isShow : 1
         * isPat : 0
         * primaryImage : http://obqlfysk2.bkt.clouddn.com/Fpv1Jogk9j2F9TvSf2KGJjNn2itK
         * productImage1 : http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw
         * productImage2 : http://obqlfysk2.bkt.clouddn.com/FnUZQS4uDokepOu-NjFHrqaZUuKm
         * productImage3 : http://obqlfysk2.bkt.clouddn.com/FmwZeAfa_GvRQMPqrJK-NP78IiGI
         * info : 这只是一个苹果
         * approveStatus : 0
         * brand : 地方官方的
         * status : 0
         * salesVolume : 0
         * storeCategoryId : 214847201321701
         * goodsSpecs : [{"id":314870788454420,"platformPrice":10,"inventory":1,"specaNameId":214847200746437,"specaValue":"红色","specbNameId":214847204042456,"specbValue":"200g","speccNameId":314867856422941,"speccValue":"50ml","anticipatedRevenue":0},{"id":314870788456171,"platformPrice":1010,"inventory":5,"specaNameId":214847200746437,"specaValue":"蓝色","specbNameId":214847204042456,"specbValue":"210g","speccNameId":314867856422941,"speccValue":"60ml","anticipatedRevenue":0},{"id":314870788458142,"platformPrice":3,"inventory":100,"specaNameId":214847200746437,"specaValue":"红色","specbNameId":214847204042456,"specbValue":"210g","speccNameId":314867856422941,"speccValue":"50ml","anticipatedRevenue":0}]
         * goodsType : 0
         * inventory : 106
         */

        private MallGoodsBean mallGoods;

        public MallCollectGoodsBean getMallCollectGoods() {
            return mallCollectGoods;
        }

        public void setMallCollectGoods(MallCollectGoodsBean mallCollectGoods) {
            this.mallCollectGoods = mallCollectGoods;
        }

        public MallGoodsBean getMallGoods() {
            return mallGoods;
        }

        public void setMallGoods(MallGoodsBean mallGoods) {
            this.mallGoods = mallGoods;
        }

        public static class MallCollectGoodsBean {
            private long id;
            private long memberId;
            private long goodsSpecId;
            private int status;
            private long createTime;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getMemberId() {
                return memberId;
            }

            public void setMemberId(long memberId) {
                this.memberId = memberId;
            }

            public long getGoodsSpecId() {
                return goodsSpecId;
            }

            public void setGoodsSpecId(long goodsSpecId) {
                this.goodsSpecId = goodsSpecId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }

        public static class MallGoodsBean {
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
            private int salesVolume;
            private long storeCategoryId;
            private int goodsType;
            private int inventory;
            /**
             * id : 314870788454420
             * platformPrice : 10
             * inventory : 1
             * specaNameId : 214847200746437
             * specaValue : 红色
             * specbNameId : 214847204042456
             * specbValue : 200g
             * speccNameId : 314867856422941
             * speccValue : 50ml
             * anticipatedRevenue : 0
             */

            private List<GoodsSpecsBean> goodsSpecs;

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

            public int getSalesVolume() {
                return salesVolume;
            }

            public void setSalesVolume(int salesVolume) {
                this.salesVolume = salesVolume;
            }

            public long getStoreCategoryId() {
                return storeCategoryId;
            }

            public void setStoreCategoryId(long storeCategoryId) {
                this.storeCategoryId = storeCategoryId;
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

            public static class GoodsSpecsBean {
                private long id;
                private int platformPrice;
                private int inventory;
                private long specaNameId;
                private String specaValue;
                private long specbNameId;
                private String specbValue;
                private long speccNameId;
                private String speccValue;
                private int anticipatedRevenue;

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public int getPlatformPrice() {
                    return platformPrice;
                }

                public void setPlatformPrice(int platformPrice) {
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
    }
}
