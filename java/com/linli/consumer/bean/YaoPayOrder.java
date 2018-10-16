package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/2/15.
 */

public class YaoPayOrder {

    /**
     * status : 1
     * data : {"hdFoodOrderlist":[{"hdFoodOrder":{"id":314847253411195,"orderSn":"201612221016238531","memberId":314847078298018,"orderStatus":0,"paymentId":1,"orderAmount":66,"storeId":414847215788752,"province":"贵州","city":"黔西南","district":"安龙县","address":"北京 北京 海淀区   NewYork","logisticsAmount":0,"accountStatus":0,"purchaser":"Steve Jobs","purchaserPhone":"999888999888","createTime":1484725341000,"status":0,"numtotal":1,"transId":164888802874101760,"orderType":2,"packageCost":0},"cookbookVoList":[{"goods":{"id":314847253412136,"orderId":314847253411195,"goodsNum":1,"createTime":1484725341000,"goodsId":314847220056584}}],"storeName":"泰日天"},{"hdFoodOrder":{"id":314847253202149,"orderSn":"201612221016238531","memberId":314847078298018,"orderStatus":0,"paymentId":1,"orderAmount":0.01,"storeId":414847215788752,"province":"贵州","city":"黔西南","district":"安龙县","address":"北京 北京 海淀区   NewYork","logisticsAmount":0,"accountStatus":0,"purchaser":"Steve Jobs","purchaserPhone":"999888999888","createTime":1484725320000,"status":0,"numtotal":1,"transId":164888715120873472,"orderType":2,"packageCost":0},"cookbookVoList":[{"goods":{"id":314847253202930,"orderId":314847253202149,"goodsNum":1,"createTime":1484725320000,"goodsId":314847220971056}}],"storeName":"泰日天"},{"hdFoodOrder":{"id":314847252935856,"orderSn":"201612221016238531","memberId":314847078298018,"orderStatus":0,"paymentId":1,"orderAmount":0.01,"storeId":414847215788752,"province":"贵州","city":"黔西南","district":"安龙县","address":"北京 北京 海淀区   NewYork","logisticsAmount":0,"accountStatus":0,"purchaser":"Steve Jobs","purchaserPhone":"999888999888","createTime":1484725293000,"status":0,"numtotal":1,"transId":164888603443335168,"orderType":2,"packageCost":0},"cookbookVoList":[{"goods":{"id":314847252936637,"orderId":314847252935856,"goodsNum":1,"createTime":1484725293000,"goodsId":314847220971056}}],"storeName":"泰日天"}],"type":0,"num":0,"mallOrderList":[{"hdMallOrder":{"id":314869568511888,"orderSn":"314869568511888","memberId":314847078298018,"orderStatus":0,"paymentId":1,"orderAmount":11,"storeId":414846522510124,"address":"北京 北京 海淀区   NewYork","phone":"15535373009","createTime":1486874145000,"status":0,"numtotal":2,"payType":1,"areaCode":"1","transId":174248462718603264,"byway":1},"type":0,"mgVoList":[{"num":2,"mallGoods":{"id":314867167494972,"name":"U盘","storeId":414846522510124,"isShow":1,"isPat":1,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage1":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-","productImage3":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","approveStatus":0,"brand":"阿萨德","status":0,"salesVolume":0,"storeCategoryId":314848794346395,"goodsSpecs":[{"id":314867167495903,"platformPrice":8.6,"inventory":66,"specaNameId":214847200896048,"specaValue":"颜色1","specbNameId":214847201108239,"specbValue":"尺寸1","speccNameId":214847204042456,"speccValue":"大小1","anticipatedRevenue":0},{"id":314867167496844,"platformPrice":78.3,"inventory":88,"specaNameId":214847200896048,"specaValue":"颜色2","specbNameId":214847201108239,"specbValue":"尺寸2","speccNameId":214847204042456,"speccValue":"大小2","anticipatedRevenue":0}],"goodsType":0,"inventory":154},"platformPrice":8.6}],"num":0,"storeName":"晚安"}]}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * hdFoodOrderlist : [{"hdFoodOrder":{"id":314847253411195,"orderSn":"201612221016238531","memberId":314847078298018,"orderStatus":0,"paymentId":1,"orderAmount":66,"storeId":414847215788752,"province":"贵州","city":"黔西南","district":"安龙县","address":"北京 北京 海淀区   NewYork","logisticsAmount":0,"accountStatus":0,"purchaser":"Steve Jobs","purchaserPhone":"999888999888","createTime":1484725341000,"status":0,"numtotal":1,"transId":164888802874101760,"orderType":2,"packageCost":0},"cookbookVoList":[{"goods":{"id":314847253412136,"orderId":314847253411195,"goodsNum":1,"createTime":1484725341000,"goodsId":314847220056584}}],"storeName":"泰日天"},{"hdFoodOrder":{"id":314847253202149,"orderSn":"201612221016238531","memberId":314847078298018,"orderStatus":0,"paymentId":1,"orderAmount":0.01,"storeId":414847215788752,"province":"贵州","city":"黔西南","district":"安龙县","address":"北京 北京 海淀区   NewYork","logisticsAmount":0,"accountStatus":0,"purchaser":"Steve Jobs","purchaserPhone":"999888999888","createTime":1484725320000,"status":0,"numtotal":1,"transId":164888715120873472,"orderType":2,"packageCost":0},"cookbookVoList":[{"goods":{"id":314847253202930,"orderId":314847253202149,"goodsNum":1,"createTime":1484725320000,"goodsId":314847220971056}}],"storeName":"泰日天"},{"hdFoodOrder":{"id":314847252935856,"orderSn":"201612221016238531","memberId":314847078298018,"orderStatus":0,"paymentId":1,"orderAmount":0.01,"storeId":414847215788752,"province":"贵州","city":"黔西南","district":"安龙县","address":"北京 北京 海淀区   NewYork","logisticsAmount":0,"accountStatus":0,"purchaser":"Steve Jobs","purchaserPhone":"999888999888","createTime":1484725293000,"status":0,"numtotal":1,"transId":164888603443335168,"orderType":2,"packageCost":0},"cookbookVoList":[{"goods":{"id":314847252936637,"orderId":314847252935856,"goodsNum":1,"createTime":1484725293000,"goodsId":314847220971056}}],"storeName":"泰日天"}]
         * type : 0
         * num : 0
         * mallOrderList : [{"hdMallOrder":{"id":314869568511888,"orderSn":"314869568511888","memberId":314847078298018,"orderStatus":0,"paymentId":1,"orderAmount":11,"storeId":414846522510124,"address":"北京 北京 海淀区   NewYork","phone":"15535373009","createTime":1486874145000,"status":0,"numtotal":2,"payType":1,"areaCode":"1","transId":174248462718603264,"byway":1},"type":0,"mgVoList":[{"num":2,"mallGoods":{"id":314867167494972,"name":"U盘","storeId":414846522510124,"isShow":1,"isPat":1,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage1":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-","productImage3":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","approveStatus":0,"brand":"阿萨德","status":0,"salesVolume":0,"storeCategoryId":314848794346395,"goodsSpecs":[{"id":314867167495903,"platformPrice":8.6,"inventory":66,"specaNameId":214847200896048,"specaValue":"颜色1","specbNameId":214847201108239,"specbValue":"尺寸1","speccNameId":214847204042456,"speccValue":"大小1","anticipatedRevenue":0},{"id":314867167496844,"platformPrice":78.3,"inventory":88,"specaNameId":214847200896048,"specaValue":"颜色2","specbNameId":214847201108239,"specbValue":"尺寸2","speccNameId":214847204042456,"speccValue":"大小2","anticipatedRevenue":0}],"goodsType":0,"inventory":154},"platformPrice":8.6}],"num":0,"storeName":"晚安"}]
         */

        private int type;
        private int num;
        private List<HdFoodOrderlistBean> hdFoodOrderlist;
        private List<MallOrderListBean> mallOrderList;

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

        public List<HdFoodOrderlistBean> getHdFoodOrderlist() {
            return hdFoodOrderlist;
        }

        public void setHdFoodOrderlist(List<HdFoodOrderlistBean> hdFoodOrderlist) {
            this.hdFoodOrderlist = hdFoodOrderlist;
        }

        public List<MallOrderListBean> getMallOrderList() {
            return mallOrderList;
        }

        public void setMallOrderList(List<MallOrderListBean> mallOrderList) {
            this.mallOrderList = mallOrderList;
        }

        public static class HdFoodOrderlistBean {
            /**
             * hdFoodOrder : {"id":314847253411195,"orderSn":"201612221016238531","memberId":314847078298018,"orderStatus":0,"paymentId":1,"orderAmount":66,"storeId":414847215788752,"province":"贵州","city":"黔西南","district":"安龙县","address":"北京 北京 海淀区   NewYork","logisticsAmount":0,"accountStatus":0,"purchaser":"Steve Jobs","purchaserPhone":"999888999888","createTime":1484725341000,"status":0,"numtotal":1,"transId":164888802874101760,"orderType":2,"packageCost":0}
             * cookbookVoList : [{"goods":{"id":314847253412136,"orderId":314847253411195,"goodsNum":1,"createTime":1484725341000,"goodsId":314847220056584}}]
             * storeName : 泰日天
             */

            private HdFoodOrderBean hdFoodOrder;
            private String storeName;
            private List<CookbookVoListBean> cookbookVoList;

            public HdFoodOrderBean getHdFoodOrder() {
                return hdFoodOrder;
            }

            public void setHdFoodOrder(HdFoodOrderBean hdFoodOrder) {
                this.hdFoodOrder = hdFoodOrder;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public List<CookbookVoListBean> getCookbookVoList() {
                return cookbookVoList;
            }

            public void setCookbookVoList(List<CookbookVoListBean> cookbookVoList) {
                this.cookbookVoList = cookbookVoList;
            }

            public static class HdFoodOrderBean {
                /**
                 * id : 314847253411195
                 * orderSn : 201612221016238531
                 * memberId : 314847078298018
                 * orderStatus : 0
                 * paymentId : 1
                 * orderAmount : 66
                 * storeId : 414847215788752
                 * province : 贵州
                 * city : 黔西南
                 * district : 安龙县
                 * address : 北京 北京 海淀区   NewYork
                 * logisticsAmount : 0
                 * accountStatus : 0
                 * purchaser : Steve Jobs
                 * purchaserPhone : 999888999888
                 * createTime : 1484725341000
                 * status : 0
                 * numtotal : 1
                 * transId : 164888802874101760
                 * orderType : 2
                 * packageCost : 0
                 */

                private long id;
                private String orderSn;
                private long memberId;
                private int orderStatus;
                private int paymentId;
                private Double orderAmount;
                private long storeId;
                private String province;
                private String city;
                private String district;
                private String address;
                private int logisticsAmount;
                private int accountStatus;
                private String purchaser;
                private String purchaserPhone;
                private long createTime;
                private int status;
                private int numtotal;
                private long transId;
                private int orderType;
                private int packageCost;

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

                public int getPaymentId() {
                    return paymentId;
                }

                public void setPaymentId(int paymentId) {
                    this.paymentId = paymentId;
                }

                public Double getOrderAmount() {
                    return orderAmount;
                }

                public void setOrderAmount(Double orderAmount) {
                    this.orderAmount = orderAmount;
                }

                public long getStoreId() {
                    return storeId;
                }

                public void setStoreId(long storeId) {
                    this.storeId = storeId;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public int getLogisticsAmount() {
                    return logisticsAmount;
                }

                public void setLogisticsAmount(int logisticsAmount) {
                    this.logisticsAmount = logisticsAmount;
                }

                public int getAccountStatus() {
                    return accountStatus;
                }

                public void setAccountStatus(int accountStatus) {
                    this.accountStatus = accountStatus;
                }

                public String getPurchaser() {
                    return purchaser;
                }

                public void setPurchaser(String purchaser) {
                    this.purchaser = purchaser;
                }

                public String getPurchaserPhone() {
                    return purchaserPhone;
                }

                public void setPurchaserPhone(String purchaserPhone) {
                    this.purchaserPhone = purchaserPhone;
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

                public long getTransId() {
                    return transId;
                }

                public void setTransId(long transId) {
                    this.transId = transId;
                }

                public int getOrderType() {
                    return orderType;
                }

                public void setOrderType(int orderType) {
                    this.orderType = orderType;
                }

                public int getPackageCost() {
                    return packageCost;
                }

                public void setPackageCost(int packageCost) {
                    this.packageCost = packageCost;
                }
            }

            public static class CookbookVoListBean {
                /**
                 * goods : {"id":314847253412136,"orderId":314847253411195,"goodsNum":1,"createTime":1484725341000,"goodsId":314847220056584}
                 */

                private GoodsBean goods;
                private CookbookBean cookbook;
                public GoodsBean getGoods() {
                    return goods;
                }

                public void setGoods(GoodsBean goods) {
                    this.goods = goods;
                }
                public CookbookBean getCookbook() {
                    return cookbook;
                }

                public void setCookbook(CookbookBean cookbook) {
                    this.cookbook = cookbook;
                }
                public static class GoodsBean {
                    /**
                     * id : 314847253412136
                     * orderId : 314847253411195
                     * goodsNum : 1
                     * createTime : 1484725341000
                     * goodsId : 314847220056584
                     */

                    private long id;
                    private long orderId;
                    private int goodsNum;
                    private long createTime;
                    private long goodsId;

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public long getOrderId() {
                        return orderId;
                    }

                    public void setOrderId(long orderId) {
                        this.orderId = orderId;
                    }

                    public int getGoodsNum() {
                        return goodsNum;
                    }

                    public void setGoodsNum(int goodsNum) {
                        this.goodsNum = goodsNum;
                    }

                    public long getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(long createTime) {
                        this.createTime = createTime;
                    }

                    public long getGoodsId() {
                        return goodsId;
                    }

                    public void setGoodsId(long goodsId) {
                        this.goodsId = goodsId;
                    }
                }
                public static class CookbookBean {
                    /**
                     * id : 314848055865913
                     * bussId : 314848053314163
                     * name : 水煮花生
                     * maining : 花生
                     * accessories : 大料
                     * imgpath : http://obqlfysk2.bkt.clouddn.com/FvGwMndnFRfpFaZW9avX14ixOv3R
                     * price : 1.0
                     * status : 0
                     * isoutshop : 1
                     * sort : 1
                     * belongtype : 314848054539909
                     */

                    private long id;
                    private long bussId;
                    private String name;
                    private String maining;
                    private String accessories;
                    private String imgpath;
                    private Double price;
                    private String status;
                    private String isoutshop;
                    private int sort;
                    private long belongtype;

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public long getBussId() {
                        return bussId;
                    }

                    public void setBussId(long bussId) {
                        this.bussId = bussId;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
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

                    public Double getPrice() {
                        return price;
                    }

                    public void setPrice(Double price) {
                        this.price = price;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public String getIsoutshop() {
                        return isoutshop;
                    }

                    public void setIsoutshop(String isoutshop) {
                        this.isoutshop = isoutshop;
                    }

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public long getBelongtype() {
                        return belongtype;
                    }

                    public void setBelongtype(long belongtype) {
                        this.belongtype = belongtype;
                    }
                }
            }
        }

        public static class MallOrderListBean {
            /**
             * hdMallOrder : {"id":314869568511888,"orderSn":"314869568511888","memberId":314847078298018,"orderStatus":0,"paymentId":1,"orderAmount":11,"storeId":414846522510124,"address":"北京 北京 海淀区   NewYork","phone":"15535373009","createTime":1486874145000,"status":0,"numtotal":2,"payType":1,"areaCode":"1","transId":174248462718603264,"byway":1}
             * type : 0
             * mgVoList : [{"num":2,"mallGoods":{"id":314867167494972,"name":"U盘","storeId":414846522510124,"isShow":1,"isPat":1,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage1":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-","productImage3":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","approveStatus":0,"brand":"阿萨德","status":0,"salesVolume":0,"storeCategoryId":314848794346395,"goodsSpecs":[{"id":314867167495903,"platformPrice":8.6,"inventory":66,"specaNameId":214847200896048,"specaValue":"颜色1","specbNameId":214847201108239,"specbValue":"尺寸1","speccNameId":214847204042456,"speccValue":"大小1","anticipatedRevenue":0},{"id":314867167496844,"platformPrice":78.3,"inventory":88,"specaNameId":214847200896048,"specaValue":"颜色2","specbNameId":214847201108239,"specbValue":"尺寸2","speccNameId":214847204042456,"speccValue":"大小2","anticipatedRevenue":0}],"goodsType":0,"inventory":154},"platformPrice":8.6}]
             * num : 0
             * storeName : 晚安
             */

            private HdMallOrderBean hdMallOrder;
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
                 * id : 314869568511888
                 * orderSn : 314869568511888
                 * memberId : 314847078298018
                 * orderStatus : 0
                 * paymentId : 1
                 * orderAmount : 11.0
                 * storeId : 414846522510124
                 * address : 北京 北京 海淀区   NewYork
                 * phone : 15535373009
                 * createTime : 1486874145000
                 * status : 0
                 * numtotal : 2
                 * payType : 1
                 * areaCode : 1
                 * transId : 174248462718603264
                 * byway : 1
                 */

                private long id;
                private String orderSn;
                private long memberId;
                private int orderStatus;
                private int paymentId;
                private double orderAmount;
                private long storeId;
                private String purchaser;
                private String address;
                private String phone;
                private long createTime;
                private int status;
                private int numtotal;
                private int payType;
                private String areaCode;
                private long transId;
                private int byway;

                public String getPurchaser() {
                    return purchaser;
                }

                public void setPurchaser(String purchaser) {
                    this.purchaser = purchaser;
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

                public int getPaymentId() {
                    return paymentId;
                }

                public void setPaymentId(int paymentId) {
                    this.paymentId = paymentId;
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

                public int getPayType() {
                    return payType;
                }

                public void setPayType(int payType) {
                    this.payType = payType;
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

                public int getByway() {
                    return byway;
                }

                public void setByway(int byway) {
                    this.byway = byway;
                }
            }

            public static class MgVoListBean {
                /**
                 * num : 2
                 * mallGoods : {"id":314867167494972,"name":"U盘","storeId":414846522510124,"isShow":1,"isPat":1,"primaryImage":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage1":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","productImage2":"http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-","productImage3":"http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw","approveStatus":0,"brand":"阿萨德","status":0,"salesVolume":0,"storeCategoryId":314848794346395,"goodsSpecs":[{"id":314867167495903,"platformPrice":8.6,"inventory":66,"specaNameId":214847200896048,"specaValue":"颜色1","specbNameId":214847201108239,"specbValue":"尺寸1","speccNameId":214847204042456,"speccValue":"大小1","anticipatedRevenue":0},{"id":314867167496844,"platformPrice":78.3,"inventory":88,"specaNameId":214847200896048,"specaValue":"颜色2","specbNameId":214847201108239,"specbValue":"尺寸2","speccNameId":214847204042456,"speccValue":"大小2","anticipatedRevenue":0}],"goodsType":0,"inventory":154}
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
                     * isPat : 1
                     * primaryImage : http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw
                     * productImage1 : http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw
                     * productImage2 : http://obqlfysk2.bkt.clouddn.com/FpFwi1VKQybyDTB95BMi2a5HKyq-
                     * productImage3 : http://obqlfysk2.bkt.clouddn.com/Fjy4M679uV-o8qjs7ccFDHifiuFw
                     * approveStatus : 0
                     * brand : 阿萨德
                     * status : 0
                     * salesVolume : 0
                     * storeCategoryId : 314848794346395
                     * goodsSpecs : [{"id":314867167495903,"platformPrice":8.6,"inventory":66,"specaNameId":214847200896048,"specaValue":"颜色1","specbNameId":214847201108239,"specbValue":"尺寸1","speccNameId":214847204042456,"speccValue":"大小1","anticipatedRevenue":0},{"id":314867167496844,"platformPrice":78.3,"inventory":88,"specaNameId":214847200896048,"specaValue":"颜色2","specbNameId":214847201108239,"specbValue":"尺寸2","speccNameId":214847204042456,"speccValue":"大小2","anticipatedRevenue":0}]
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
                         * id : 314867167495903
                         * platformPrice : 8.6
                         * inventory : 66
                         * specaNameId : 214847200896048
                         * specaValue : 颜色1
                         * specbNameId : 214847201108239
                         * specbValue : 尺寸1
                         * speccNameId : 214847204042456
                         * speccValue : 大小1
                         * anticipatedRevenue : 0.0
                         */

                        private long id;
                        private double platformPrice;
                        private int inventory;
                        private long specaNameId;
                        private String specaValue;
                        private long specbNameId;
                        private String specbValue;
                        private long speccNameId;
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
                    }
                }
            }
        }
    }
}
