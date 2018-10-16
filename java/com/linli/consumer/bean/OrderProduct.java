package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/2/12.
 */

public class OrderProduct {

    /**
     * status : 1
     * msg : 查询成功!
     * page : {"plainPageNum":1,"pageNum":1,"numPerPage":10,"orderField":"","orderDirection":"","totalPage":1,"prePage":1,"nextPage":1,"totalCount":1}
     * data : [{"hdMallOrder":{"id":214868739290373,"orderSn":"20161010162210015","memberId":314847078298018,"orderStatus":0,"paymentId":1,"goodsAmount":11,"orderAmount":11,"storeId":414846522510124,"province":"北京","city":"北京","district":"海淀区","address":"北京 北京 海淀区   NewYork","phone":"15535373009","purchaser":"刘小黑","createTime":1486873927000,"status":0,"numtotal":2,"areaCode":"1","transId":173900661811843072,"byway":1},"status":0,"type":0,"mgVoList":[{"num":2,"mallGoods":{"id":314867167494972,"name":"U盘","storeId":414846522510124,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage1":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-","productImage3":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","approveStatus":0,"brand":"阿萨德","status":0,"salesVolume":0,"storeCategoryId":314848794346395,"goodsSpecs":[{"platformPrice":8.6,"inventory":66,"mallGoodsSpecNames":[],"specaNameId":214847200896048,"specaValue":"颜色1","specbNameId":214847201108239,"specbValue":"尺寸1","speccNameId":214847204042456,"speccValue":"大小1","anticipatedRevenue":0},{"platformPrice":78.3,"inventory":88,"mallGoodsSpecNames":[],"specaNameId":214847200896048,"specaValue":"颜色2","specbNameId":214847201108239,"specbValue":"尺寸2","speccNameId":214847204042456,"speccValue":"大小2","anticipatedRevenue":0}],"goodsType":0,"inventory":154},"platformPrice":8.6}],"num":0,"storeName":"晚安"}]
     */

    private int status;
    private String msg;
    private PageBean page;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
         * totalCount : 1
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
         * hdMallOrder : {"id":214868739290373,"orderSn":"20161010162210015","memberId":314847078298018,"orderStatus":0,"paymentId":1,"goodsAmount":11,"orderAmount":11,"storeId":414846522510124,"province":"北京","city":"北京","district":"海淀区","address":"北京 北京 海淀区   NewYork","phone":"15535373009","purchaser":"刘小黑","createTime":1486873927000,"status":0,"numtotal":2,"areaCode":"1","transId":173900661811843072,"byway":1}
         * status : 0
         * type : 0
         * mgVoList : [{"num":2,"mallGoods":{"id":314867167494972,"name":"U盘","storeId":414846522510124,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage1":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-","productImage3":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","approveStatus":0,"brand":"阿萨德","status":0,"salesVolume":0,"storeCategoryId":314848794346395,"goodsSpecs":[{"platformPrice":8.6,"inventory":66,"mallGoodsSpecNames":[],"specaNameId":214847200896048,"specaValue":"颜色1","specbNameId":214847201108239,"specbValue":"尺寸1","speccNameId":214847204042456,"speccValue":"大小1","anticipatedRevenue":0},{"platformPrice":78.3,"inventory":88,"mallGoodsSpecNames":[],"specaNameId":214847200896048,"specaValue":"颜色2","specbNameId":214847201108239,"specbValue":"尺寸2","speccNameId":214847204042456,"speccValue":"大小2","anticipatedRevenue":0}],"goodsType":0,"inventory":154},"platformPrice":8.6}]
         * num : 0
         * storeName : 晚安
         */

        private HdMallOrderBean hdMallOrder;
        private int status;
        private int type;
        private int num;
        private String storeName;
        private List<MgVoListBean> mgVoList;

        public HdMallOrderBean getHdMallOrder() {
            return hdMallOrder;
        }

        public void setHdMallOrder(HdMallOrderBean hdMallOrder) {
            this.hdMallOrder = hdMallOrder;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public List<MgVoListBean> getMgVoList() {
            return mgVoList;
        }

        public void setMgVoList(List<MgVoListBean> mgVoList) {
            this.mgVoList = mgVoList;
        }

        public static class HdMallOrderBean {
            /**
             * id : 214868739290373
             * orderSn : 20161010162210015
             * memberId : 314847078298018
             * orderStatus : 0
             * paymentId : 1
             * goodsAmount : 11.0
             * orderAmount : 11.0
             * storeId : 414846522510124
             * province : 北京
             * city : 北京
             * district : 海淀区
             * address : 北京 北京 海淀区   NewYork
             * phone : 15535373009
             * purchaser : 刘小黑
             * createTime : 1486873927000
             * status : 0
             * numtotal : 2
             * areaCode : 1
             * transId : 173900661811843072
             * byway : 1
             */

            private long id;
            private String orderSn;
            private long memberId;
            private int orderStatus;
            private Long paymentId;
            private double goodsAmount;
            private double orderAmount;
            private long storeId;
            private String address;
            private String phone;
            private String purchaser;
            private long createTime;
            private int status;
            private int numtotal;
            private String areaCode;
            private long transId;
            private Integer byway;
            private int paytype;
            private Double logisticsAmount;
            private Double favorableAmount;
            private Long couponId;

            public Long getCouponId() {
                return couponId;
            }

            public void setCouponId(Long couponId) {
                this.couponId = couponId;
            }

            public Double getFavorableAmount() {
                return favorableAmount;
            }

            public void setFavorableAmount(Double favorableAmount) {
                this.favorableAmount = favorableAmount;
            }

            public Double getLogisticsAmount() {
                return logisticsAmount;
            }

            public void setLogisticsAmount(Double logisticsAmount) {
                this.logisticsAmount = logisticsAmount;
            }

            public int getPaytype() {
                return paytype;
            }

            public void setPaytype(int paytype) {
                this.paytype = paytype;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getOrderSn() {
                return orderSn;
            }

            public void setOrderSn(String orderSn) {
                this.orderSn = orderSn;
            }

            public long getMemberId() {
                return memberId;
            }

            public void setMemberId(long memberId) {
                this.memberId = memberId;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public Long getPaymentId() {
                return paymentId;
            }

            public void setPaymentId(Long paymentId) {
                this.paymentId = paymentId;
            }

            public double getGoodsAmount() {
                return goodsAmount;
            }

            public void setGoodsAmount(double goodsAmount) {
                this.goodsAmount = goodsAmount;
            }

            public double getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(double orderAmount) {
                this.orderAmount = orderAmount;
            }

            public long getStoreId() {
                return storeId;
            }

            public void setStoreId(long storeId) {
                this.storeId = storeId;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPurchaser() {
                return purchaser;
            }

            public void setPurchaser(String purchaser) {
                this.purchaser = purchaser;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getNumtotal() {
                return numtotal;
            }

            public void setNumtotal(int numtotal) {
                this.numtotal = numtotal;
            }

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public long getTransId() {
                return transId;
            }

            public void setTransId(long transId) {
                this.transId = transId;
            }

            public Integer getByway() {
                return byway;
            }

            public void setByway(Integer byway) {
                this.byway = byway;
            }
        }

        public static class MgVoListBean {
            /**
             * num : 2
             * mallGoods : {"id":314867167494972,"name":"U盘","storeId":414846522510124,"isShow":1,"isPat":0,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage1":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-","productImage3":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","approveStatus":0,"brand":"阿萨德","status":0,"salesVolume":0,"storeCategoryId":314848794346395,"goodsSpecs":[{"platformPrice":8.6,"inventory":66,"mallGoodsSpecNames":[],"specaNameId":214847200896048,"specaValue":"颜色1","specbNameId":214847201108239,"specbValue":"尺寸1","speccNameId":214847204042456,"speccValue":"大小1","anticipatedRevenue":0},{"platformPrice":78.3,"inventory":88,"mallGoodsSpecNames":[],"specaNameId":214847200896048,"specaValue":"颜色2","specbNameId":214847201108239,"specbValue":"尺寸2","speccNameId":214847204042456,"speccValue":"大小2","anticipatedRevenue":0}],"goodsType":0,"inventory":154}
             * platformPrice : 8.6
             */

            private int num;
            private MallGoodsBean mallGoods;
            private double platformPrice;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public MallGoodsBean getMallGoods() {
                return mallGoods;
            }

            public void setMallGoods(MallGoodsBean mallGoods) {
                this.mallGoods = mallGoods;
            }

            public double getPlatformPrice() {
                return platformPrice;
            }

            public void setPlatformPrice(double platformPrice) {
                this.platformPrice = platformPrice;
            }

            public static class MallGoodsBean {
                /**
                 * id : 314867167494972
                 * name : U盘
                 * storeId : 414846522510124
                 * isShow : 1
                 * isPat : 0
                 * primaryImage : http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw
                 * productImage1 : http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw
                 * productImage2 : http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-
                 * productImage3 : http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw
                 * approveStatus : 0
                 * brand : 阿萨德
                 * status : 0
                 * salesVolume : 0
                 * storeCategoryId : 314848794346395
                 * goodsSpecs : [{"platformPrice":8.6,"inventory":66,"mallGoodsSpecNames":[],"specaNameId":214847200896048,"specaValue":"颜色1","specbNameId":214847201108239,"specbValue":"尺寸1","speccNameId":214847204042456,"speccValue":"大小1","anticipatedRevenue":0},{"platformPrice":78.3,"inventory":88,"mallGoodsSpecNames":[],"specaNameId":214847200896048,"specaValue":"颜色2","specbNameId":214847201108239,"specbValue":"尺寸2","speccNameId":214847204042456,"speccValue":"大小2","anticipatedRevenue":0}]
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
                     * platformPrice : 8.6
                     * inventory : 66
                     * mallGoodsSpecNames : []
                     * specaNameId : 214847200896048
                     * specaValue : 颜色1
                     * specbNameId : 214847201108239
                     * specbValue : 尺寸1
                     * speccNameId : 214847204042456
                     * speccValue : 大小1
                     * anticipatedRevenue : 0.0
                     */

                    private double platformPrice;
                    private int inventory;
                    private long specaNameId;
                    private String specaValue;
                    private long specbNameId;
                    private String specbValue;
                    private long speccNameId;
                    private String speccValue;
                    private double anticipatedRevenue;
                    private List<?> mallGoodsSpecNames;

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

                    public double getAnticipatedRevenue() {
                        return anticipatedRevenue;
                    }

                    public void setAnticipatedRevenue(double anticipatedRevenue) {
                        this.anticipatedRevenue = anticipatedRevenue;
                    }

                    public List<?> getMallGoodsSpecNames() {
                        return mallGoodsSpecNames;
                    }

                    public void setMallGoodsSpecNames(List<?> mallGoodsSpecNames) {
                        this.mallGoodsSpecNames = mallGoodsSpecNames;
                    }
                }
            }
        }
    }
}
