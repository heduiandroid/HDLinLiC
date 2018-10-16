package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/1/10.
 */

public class CollectGood {

    /**
     * status : 1
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":10,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":0}
     * data : [{"mallCollectGoods":{"id":314869496663622,"memberId":314847024078253,"goodsSpecId":914868932385383,"status":0,"createTime":1486949666000},"mallGoods":{"id":914868930779961,"name":"跑鞋","storeId":414824562913762,"isShow":1,"isPat":1,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage1":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage2":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage3":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","approveStatus":0,"brand":"飞人凤凰","status":0,"salesVolume":0,"storeCategoryId":914842226945192,"goodsSpecs":[{"id":914868930829242,"platformPrice":8.6,"inventory":66,"specaNameId":123456789,"specaValue":"规格值1","specbNameId":123456789,"specbValue":"规格值2","speccNameId":123456789,"speccValue":"规格值3","anticipatedRevenue":0},{"id":914868932385383,"platformPrice":78.3,"inventory":88,"specaNameId":123456789,"specaValue":"规格值1-1","specbNameId":123456789,"specbValue":"规格值2-1","speccNameId":123456789,"speccValue":"规格值3-1","anticipatedRevenue":0}],"goodsType":0,"inventory":154}}]
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
         * numPerPage : 10
         * orderField :
         * orderDirection :
         * totalPage : 1
         * prePage : 1
         * nextPage : 1
         * totalCount : 0
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
         * mallCollectGoods : {"id":314869496663622,"memberId":314847024078253,"goodsSpecId":914868932385383,"status":0,"createTime":1486949666000}
         * mallGoods : {"id":914868930779961,"name":"跑鞋","storeId":414824562913762,"isShow":1,"isPat":1,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage1":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage2":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","productImage3":"http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS","approveStatus":0,"brand":"飞人凤凰","status":0,"salesVolume":0,"storeCategoryId":914842226945192,"goodsSpecs":[{"id":914868930829242,"platformPrice":8.6,"inventory":66,"specaNameId":123456789,"specaValue":"规格值1","specbNameId":123456789,"specbValue":"规格值2","speccNameId":123456789,"speccValue":"规格值3","anticipatedRevenue":0},{"id":914868932385383,"platformPrice":78.3,"inventory":88,"specaNameId":123456789,"specaValue":"规格值1-1","specbNameId":123456789,"specbValue":"规格值2-1","speccNameId":123456789,"speccValue":"规格值3-1","anticipatedRevenue":0}],"goodsType":0,"inventory":154}
         */

        private MallCollectGoodsBean mallCollectGoods;
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
            /**
             * id : 314869496663622
             * memberId : 314847024078253
             * goodsSpecId : 914868932385383
             * status : 0
             * createTime : 1486949666000
             */

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
            /**
             * id : 914868930779961
             * name : 跑鞋
             * storeId : 414824562913762
             * isShow : 1
             * isPat : 1
             * primaryImage : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
             * productImage1 : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
             * productImage2 : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
             * productImage3 : http://obqlfysk2.bkt.clouddn.com/FtzWoZXL5H9xcGFON9cy1KvcrSkS
             * approveStatus : 0
             * brand : 飞人凤凰
             * status : 0
             * salesVolume : 0
             * storeCategoryId : 914842226945192
             * goodsSpecs : [{"id":914868930829242,"platformPrice":8.6,"inventory":66,"specaNameId":123456789,"specaValue":"规格值1","specbNameId":123456789,"specbValue":"规格值2","speccNameId":123456789,"speccValue":"规格值3","anticipatedRevenue":0},{"id":914868932385383,"platformPrice":78.3,"inventory":88,"specaNameId":123456789,"specaValue":"规格值1-1","specbNameId":123456789,"specbValue":"规格值2-1","speccNameId":123456789,"speccValue":"规格值3-1","anticipatedRevenue":0}]
             * goodsType : 0
             * inventory : 154
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
            private int approveStatus;
            private String brand;
            private int status;
            private int salesVolume;
            private long storeCategoryId;
            private int goodsType;
            private int inventory;
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
                /**
                 * id : 914868930829242
                 * platformPrice : 8.6
                 * inventory : 66
                 * specaNameId : 123456789
                 * specaValue : 规格值1
                 * specbNameId : 123456789
                 * specbValue : 规格值2
                 * speccNameId : 123456789
                 * speccValue : 规格值3
                 * anticipatedRevenue : 0.0
                 */

                private long id;
                private double platformPrice;
                private int inventory;
                private Long specaNameId;
                private String specaValue;
                private Long specbNameId;
                private String specbValue;
                private Long speccNameId;
                private String speccValue;
                private double anticipatedRevenue;

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public double getPlatformPrice() {
                    return platformPrice;
                }

                public void setPlatformPrice(double platformPrice) {
                    this.platformPrice = platformPrice;
                }

                public int getInventory() {
                    return inventory;
                }

                public void setInventory(int inventory) {
                    this.inventory = inventory;
                }

                public Long getSpecaNameId() {
                    return specaNameId;
                }

                public void setSpecaNameId(Long specaNameId) {
                    this.specaNameId = specaNameId;
                }

                public String getSpecaValue() {
                    return specaValue;
                }

                public void setSpecaValue(String specaValue) {
                    this.specaValue = specaValue;
                }

                public Long getSpecbNameId() {
                    return specbNameId;
                }

                public void setSpecbNameId(Long specbNameId) {
                    this.specbNameId = specbNameId;
                }

                public String getSpecbValue() {
                    return specbValue;
                }

                public void setSpecbValue(String specbValue) {
                    this.specbValue = specbValue;
                }

                public Long getSpeccNameId() {
                    return speccNameId;
                }

                public void setSpeccNameId(Long speccNameId) {
                    this.speccNameId = speccNameId;
                }

                public String getSpeccValue() {
                    return speccValue;
                }

                public void setSpeccValue(String speccValue) {
                    this.speccValue = speccValue;
                }

                public double getAnticipatedRevenue() {
                    return anticipatedRevenue;
                }

                public void setAnticipatedRevenue(double anticipatedRevenue) {
                    this.anticipatedRevenue = anticipatedRevenue;
                }
            }
        }
    }
}
