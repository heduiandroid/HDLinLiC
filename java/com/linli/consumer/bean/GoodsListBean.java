package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by tomoyo on 2017/1/13.
 */

public class GoodsListBean extends ResVo {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private Object pageNum;
        private Object numPerPage;
        private Object num;


        private Object stockPrice;
        private Object marketPrice;
        private Object platformPrice;
        private Object storeName;
        private Object identifying;
        private Object sort;
        private Object storeIdStr;
        private Object specList;

        private int number;		//自定义新增属性，用于本地存放数量数据
        private boolean isShow;      //自定义新增字段，是否显示标志 true为显示(有数量),false为不显示(0)

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        private MallGoodsBean mallGoods;
        private List<VoListBean> voList;

        public Object getPageNum() {
            return pageNum;
        }

        public void setPageNum(Object pageNum) {
            this.pageNum = pageNum;
        }

        public Object getNumPerPage() {
            return numPerPage;
        }

        public void setNumPerPage(Object numPerPage) {
            this.numPerPage = numPerPage;
        }

        public Object getNum() {
            return num;
        }

        public void setNum(Object num) {
            this.num = num;
        }

        public MallGoodsBean getMallGoods() {
            return mallGoods;
        }

        public void setMallGoods(MallGoodsBean mallGoods) {
            this.mallGoods = mallGoods;
        }

        public Object getStockPrice() {
            return stockPrice;
        }

        public void setStockPrice(Object stockPrice) {
            this.stockPrice = stockPrice;
        }

        public Object getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(Object marketPrice) {
            this.marketPrice = marketPrice;
        }

        public Object getPlatformPrice() {
            return platformPrice;
        }

        public void setPlatformPrice(Object platformPrice) {
            this.platformPrice = platformPrice;
        }

        public Object getStoreName() {
            return storeName;
        }

        public void setStoreName(Object storeName) {
            this.storeName = storeName;
        }

        public Object getIdentifying() {
            return identifying;
        }

        public void setIdentifying(Object identifying) {
            this.identifying = identifying;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public Object getStoreIdStr() {
            return storeIdStr;
        }

        public void setStoreIdStr(Object storeIdStr) {
            this.storeIdStr = storeIdStr;
        }

        public Object getSpecList() {
            return specList;
        }

        public void setSpecList(Object specList) {
            this.specList = specList;
        }

        public List<VoListBean> getVoList() {
            return voList;
        }

        public void setVoList(List<VoListBean> voList) {
            this.voList = voList;
        }

        public static class MallGoodsBean {
            private long id;
            private String name;
            private String description;
            private long storeId;
            private int categoryId;
            private int isShow;
            private Object primaryImage;
            private int inventory;
            private Object param;
            private Object commission;
            private Object info;
            private Object countryImg;
            private Object shipmentsInfo;
            private Object sort;
            private int approveStatus;
            private String yieldly;
            private Object brand;
            private String keyword;
            private int isRecommend;
            private Object stockpile;
            private int nospecStockPrice;
            private int nospecPlatformPrice;
            private int nospecMarketPrice;
            private Object nospecManagerPrice;
            private Object nospecSku;
            private int isSpec;
            private long createTime;
            private long modifyTime;
            private int status;
            private int salesVolume;
            private Object mallStore;
            private long storeCategoryId;
            private Object platformProfit;
            private int storeType;
            private Object commentNum;
            private Object monthSaleNum;
            private Object packagingAfterSale;
            private Object mallGoodsSpec;
            private Object mallCart;
            private Object showSeven;
            private Object isTrue;
            private Object speedRefund;
            private Object sweetMoneyScale;
            private int goodsType;
            private Object createType;
            private Object createNumber;
            private Object overdueTime;
            private Object specsKayString;
            private Object mallGoodsCategory;


            private List<?> goodsSpecs;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public long getStoreId() {
                return storeId;
            }

            public void setStoreId(long storeId) {
                this.storeId = storeId;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public int getIsShow() {
                return isShow;
            }

            public void setIsShow(int isShow) {
                this.isShow = isShow;
            }

            public Object getPrimaryImage() {
                return primaryImage;
            }

            public void setPrimaryImage(Object primaryImage) {
                this.primaryImage = primaryImage;
            }

            public int getInventory() {
                return inventory;
            }

            public void setInventory(int inventory) {
                this.inventory = inventory;
            }

            public Object getParam() {
                return param;
            }

            public void setParam(Object param) {
                this.param = param;
            }

            public Object getCommission() {
                return commission;
            }

            public void setCommission(Object commission) {
                this.commission = commission;
            }

            public Object getInfo() {
                return info;
            }

            public void setInfo(Object info) {
                this.info = info;
            }

            public Object getCountryImg() {
                return countryImg;
            }

            public void setCountryImg(Object countryImg) {
                this.countryImg = countryImg;
            }

            public Object getShipmentsInfo() {
                return shipmentsInfo;
            }

            public void setShipmentsInfo(Object shipmentsInfo) {
                this.shipmentsInfo = shipmentsInfo;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public int getApproveStatus() {
                return approveStatus;
            }

            public void setApproveStatus(int approveStatus) {
                this.approveStatus = approveStatus;
            }

            public String getYieldly() {
                return yieldly;
            }

            public void setYieldly(String yieldly) {
                this.yieldly = yieldly;
            }

            public Object getBrand() {
                return brand;
            }

            public void setBrand(Object brand) {
                this.brand = brand;
            }

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public int getIsRecommend() {
                return isRecommend;
            }

            public void setIsRecommend(int isRecommend) {
                this.isRecommend = isRecommend;
            }

            public Object getStockpile() {
                return stockpile;
            }

            public void setStockpile(Object stockpile) {
                this.stockpile = stockpile;
            }

            public int getNospecStockPrice() {
                return nospecStockPrice;
            }

            public void setNospecStockPrice(int nospecStockPrice) {
                this.nospecStockPrice = nospecStockPrice;
            }

            public int getNospecPlatformPrice() {
                return nospecPlatformPrice;
            }

            public void setNospecPlatformPrice(int nospecPlatformPrice) {
                this.nospecPlatformPrice = nospecPlatformPrice;
            }

            public int getNospecMarketPrice() {
                return nospecMarketPrice;
            }

            public void setNospecMarketPrice(int nospecMarketPrice) {
                this.nospecMarketPrice = nospecMarketPrice;
            }

            public Object getNospecManagerPrice() {
                return nospecManagerPrice;
            }

            public void setNospecManagerPrice(Object nospecManagerPrice) {
                this.nospecManagerPrice = nospecManagerPrice;
            }

            public Object getNospecSku() {
                return nospecSku;
            }

            public void setNospecSku(Object nospecSku) {
                this.nospecSku = nospecSku;
            }

            public int getIsSpec() {
                return isSpec;
            }

            public void setIsSpec(int isSpec) {
                this.isSpec = isSpec;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
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

            public Object getMallStore() {
                return mallStore;
            }

            public void setMallStore(Object mallStore) {
                this.mallStore = mallStore;
            }

            public long getStoreCategoryId() {
                return storeCategoryId;
            }

            public void setStoreCategoryId(long storeCategoryId) {
                this.storeCategoryId = storeCategoryId;
            }

            public Object getPlatformProfit() {
                return platformProfit;
            }

            public void setPlatformProfit(Object platformProfit) {
                this.platformProfit = platformProfit;
            }

            public int getStoreType() {
                return storeType;
            }

            public void setStoreType(int storeType) {
                this.storeType = storeType;
            }

            public Object getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(Object commentNum) {
                this.commentNum = commentNum;
            }

            public Object getMonthSaleNum() {
                return monthSaleNum;
            }

            public void setMonthSaleNum(Object monthSaleNum) {
                this.monthSaleNum = monthSaleNum;
            }

            public Object getPackagingAfterSale() {
                return packagingAfterSale;
            }

            public void setPackagingAfterSale(Object packagingAfterSale) {
                this.packagingAfterSale = packagingAfterSale;
            }

            public Object getMallGoodsSpec() {
                return mallGoodsSpec;
            }

            public void setMallGoodsSpec(Object mallGoodsSpec) {
                this.mallGoodsSpec = mallGoodsSpec;
            }

            public Object getMallCart() {
                return mallCart;
            }

            public void setMallCart(Object mallCart) {
                this.mallCart = mallCart;
            }

            public Object getShowSeven() {
                return showSeven;
            }

            public void setShowSeven(Object showSeven) {
                this.showSeven = showSeven;
            }

            public Object getIsTrue() {
                return isTrue;
            }

            public void setIsTrue(Object isTrue) {
                this.isTrue = isTrue;
            }

            public Object getSpeedRefund() {
                return speedRefund;
            }

            public void setSpeedRefund(Object speedRefund) {
                this.speedRefund = speedRefund;
            }

            public Object getSweetMoneyScale() {
                return sweetMoneyScale;
            }

            public void setSweetMoneyScale(Object sweetMoneyScale) {
                this.sweetMoneyScale = sweetMoneyScale;
            }

            public int getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(int goodsType) {
                this.goodsType = goodsType;
            }

            public Object getCreateType() {
                return createType;
            }

            public void setCreateType(Object createType) {
                this.createType = createType;
            }

            public Object getCreateNumber() {
                return createNumber;
            }

            public void setCreateNumber(Object createNumber) {
                this.createNumber = createNumber;
            }

            public Object getOverdueTime() {
                return overdueTime;
            }

            public void setOverdueTime(Object overdueTime) {
                this.overdueTime = overdueTime;
            }

            public Object getSpecsKayString() {
                return specsKayString;
            }

            public void setSpecsKayString(Object specsKayString) {
                this.specsKayString = specsKayString;
            }

            public Object getMallGoodsCategory() {
                return mallGoodsCategory;
            }

            public void setMallGoodsCategory(Object mallGoodsCategory) {
                this.mallGoodsCategory = mallGoodsCategory;
            }



            public List<?> getGoodsSpecs() {
                return goodsSpecs;
            }

            public void setGoodsSpecs(List<?> goodsSpecs) {
                this.goodsSpecs = goodsSpecs;
            }

        }

        public static class VoListBean {



            private Object specANameId;
            private String specAName;
            private String spec1values;
            private Object specAvalues;
            private Object specBNameId;
            private String specBName;
            private String spec2values;
            private Object specBvalues;
            private Object specCNameId;
            private String specCName;
            private String spec3values;
            private Object specCvalues;
            private Object pageNum;
            private Object numPerPage;
            private Object categoryId;
            private Object identification;
            private Object storeId;
            private Object freeShipping;
            private Object sugarDollar;
            private Object havingGoods;
            private Object minPrice;
            private Object maxPrice;
            private Object goodsName;
            private Object storeCateId;
            /**
             * id : 214742023599061
             * goodsSpecId : 214742021640073
             * imgUrl : http://file.uniitown.com/uniitown/uploads/2016/09/18/214742023543287_thum.png
             * thumbnaiImgUrll : null
             * sort : 1
             * status : 0
             * createTime : 1474202359000
             * modifyTime : null
             * type : 0
             */
            private MallGoodsSpecBean mallGoodsSpec;
            private List<ImgListBean> imgList;

            public MallGoodsSpecBean getMallGoodsSpec() {
                return mallGoodsSpec;
            }

            public void setMallGoodsSpec(MallGoodsSpecBean mallGoodsSpec) {
                this.mallGoodsSpec = mallGoodsSpec;
            }

            public Object getSpecANameId() {
                return specANameId;
            }

            public void setSpecANameId(Object specANameId) {
                this.specANameId = specANameId;
            }

            public String getSpecAName() {
                return specAName;
            }

            public void setSpecAName(String specAName) {
                this.specAName = specAName;
            }

            public String getSpec1values() {
                return spec1values;
            }

            public void setSpec1values(String spec1values) {
                this.spec1values = spec1values;
            }

            public Object getSpecAvalues() {
                return specAvalues;
            }

            public void setSpecAvalues(Object specAvalues) {
                this.specAvalues = specAvalues;
            }

            public Object getSpecBNameId() {
                return specBNameId;
            }

            public void setSpecBNameId(Object specBNameId) {
                this.specBNameId = specBNameId;
            }

            public String getSpecBName() {
                return specBName;
            }

            public void setSpecBName(String specBName) {
                this.specBName = specBName;
            }

            public String getSpec2values() {
                return spec2values;
            }

            public void setSpec2values(String spec2values) {
                this.spec2values = spec2values;
            }

            public Object getSpecBvalues() {
                return specBvalues;
            }

            public void setSpecBvalues(Object specBvalues) {
                this.specBvalues = specBvalues;
            }

            public Object getSpecCNameId() {
                return specCNameId;
            }

            public void setSpecCNameId(Object specCNameId) {
                this.specCNameId = specCNameId;
            }

            public String getSpecCName() {
                return specCName;
            }

            public void setSpecCName(String specCName) {
                this.specCName = specCName;
            }

            public String getSpec3values() {
                return spec3values;
            }

            public void setSpec3values(String spec3values) {
                this.spec3values = spec3values;
            }

            public Object getSpecCvalues() {
                return specCvalues;
            }

            public void setSpecCvalues(Object specCvalues) {
                this.specCvalues = specCvalues;
            }

            public Object getPageNum() {
                return pageNum;
            }

            public void setPageNum(Object pageNum) {
                this.pageNum = pageNum;
            }

            public Object getNumPerPage() {
                return numPerPage;
            }

            public void setNumPerPage(Object numPerPage) {
                this.numPerPage = numPerPage;
            }

            public Object getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Object categoryId) {
                this.categoryId = categoryId;
            }

            public Object getIdentification() {
                return identification;
            }

            public void setIdentification(Object identification) {
                this.identification = identification;
            }

            public Object getStoreId() {
                return storeId;
            }

            public void setStoreId(Object storeId) {
                this.storeId = storeId;
            }

            public Object getFreeShipping() {
                return freeShipping;
            }

            public void setFreeShipping(Object freeShipping) {
                this.freeShipping = freeShipping;
            }

            public Object getSugarDollar() {
                return sugarDollar;
            }

            public void setSugarDollar(Object sugarDollar) {
                this.sugarDollar = sugarDollar;
            }

            public Object getHavingGoods() {
                return havingGoods;
            }

            public void setHavingGoods(Object havingGoods) {
                this.havingGoods = havingGoods;
            }

            public Object getMinPrice() {
                return minPrice;
            }

            public void setMinPrice(Object minPrice) {
                this.minPrice = minPrice;
            }

            public Object getMaxPrice() {
                return maxPrice;
            }

            public void setMaxPrice(Object maxPrice) {
                this.maxPrice = maxPrice;
            }

            public Object getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(Object goodsName) {
                this.goodsName = goodsName;
            }

            public Object getStoreCateId() {
                return storeCateId;
            }

            public void setStoreCateId(Object storeCateId) {
                this.storeCateId = storeCateId;
            }

            public List<ImgListBean> getImgList() {
                return imgList;
            }

            public void setImgList(List<ImgListBean> imgList) {
                this.imgList = imgList;
            }

            public static class MallGoodsSpecBean {
                private long id;
                private long goodsId;
                /**
                 * id : null
                 * name : null 深空灰 公开版 16GB
                 * description : null
                 * storeId : null
                 * categoryId : null
                 * isShow : null
                 * primaryImage : null
                 * inventory : null
                 * param : null
                 * commission : null
                 * info : null
                 * countryImg : null
                 * shipmentsInfo : null
                 * sort : null
                 * approveStatus : null
                 * yieldly : null
                 * brand : null
                 * keyword : null
                 * isRecommend : null
                 * stockpile : null
                 * nospecStockPrice : null
                 * nospecPlatformPrice : null
                 * nospecMarketPrice : null
                 * nospecManagerPrice : null
                 * nospecSku : null
                 * isSpec : null
                 * createTime : null
                 * modifyTime : null
                 * status : null
                 * salesVolume : null
                 * mallStore : null
                 * storeCategoryId : null
                 * platformProfit : null
                 * storeType : null
                 * commentNum : null
                 * monthSaleNum : null
                 * packagingAfterSale : null
                 * mallGoodsSpec : {"id":null,"goodsId":null,"mallGoods":null,"stockPrice":null,"marketPrice":null,"platformPrice":null,"managerPrice":null,"inventory":null,"sku":null,"mallCart":null,"mallGoodsSpecNames":[],"specaNameId":null,"mallGoodsSpecAName":{"id":214742016226995,"ids":null,"name":"颜色","value":"深空灰","storeId":null,"createTime":null,"sortNo":null,"mallGoodsSpecValues":[]},"specaValueId":null,"specaValueIds":null,"mallGoodsSpecAValue":null,"specbNameId":null,"mallGoodsSpecBName":{"id":214742016331137,"ids":null,"name":"版本","value":"公开版","storeId":null,"createTime":null,"sortNo":null,"mallGoodsSpecValues":[]},"specbValueId":null,"specbValueIds":null,"mallGoodsSpecBValue":null,"speccNameId":null,"mallGoodsSpecCName":{"id":214742016434729,"ids":null,"name":"容量","value":"16GB","storeId":null,"createTime":null,"sortNo":null,"mallGoodsSpecValues":[]},"speccValueId":null,"speccValueIds":null,"mallGoodsSpecCValue":null,"examplePic":null,"createTime":null,"salesVolume":null,"attachDocument":null,"collectFlag":null,"goodsImages":[],"mallStore":null,"ratePraise":null,"goodCommonts":null,"middleCommonts":null,"badCommonts":null,"createType":null,"createNumber":null,"overdueTime":null,"anticipatedRevenue":0,"shareUrl":"http://192.168.1.253:8080/mall/portal/api/mall/goods/goodsdetails/null","salesmanUrl":"http://192.168.1.253:8080/mall/portal/api/mall/mallsalesman/joinsalesman","shortUrl":null}
                 * mallCart : null
                 * mallGoodsComments : []
                 * mallGoodsCommentTags : []
                 * goodsSpecs : []
                 * showSeven : null
                 * isTrue : null
                 * speedRefund : null
                 * sweetMoneyScale : null
                 * goodsType : null
                 * createType : null
                 * createNumber : null
                 * overdueTime : null
                 * specsKayString : null
                 * mallGoodsCategory : null
                 */

                private MallGoodsBean mallGoods;
                private Object stockPrice;
                private int marketPrice;
                private int platformPrice;
                private Object managerPrice;
                private int inventory;
                private String sku;
                private Object mallCart;
                private long specaNameId;
                /**
                 * id : 214742016226995
                 * ids : null
                 * name : 颜色
                 * value : 深空灰
                 * storeId : null
                 * createTime : null
                 * sortNo : null
                 * mallGoodsSpecValues : []
                 */

                private MallGoodsSpecANameBean mallGoodsSpecAName;
                private long specaValueId;
                private Object specaValueIds;
                private Object mallGoodsSpecAValue;
                private long specbNameId;
                /**
                 * id : 214742016331137
                 * ids : null
                 * name : 版本
                 * value : 公开版
                 * storeId : null
                 * createTime : null
                 * sortNo : null
                 * mallGoodsSpecValues : []
                 */

                private MallGoodsSpecBNameBean mallGoodsSpecBName;
                private long specbValueId;
                private Object specbValueIds;
                private Object mallGoodsSpecBValue;
                private long speccNameId;
                /**
                 * id : 214742016434729
                 * ids : null
                 * name : 容量
                 * value : 16GB
                 * storeId : null
                 * createTime : null
                 * sortNo : null
                 * mallGoodsSpecValues : []
                 */

                private MallGoodsSpecCNameBean mallGoodsSpecCName;
                private long speccValueId;
                private Object speccValueIds;
                private Object mallGoodsSpecCValue;
                private String examplePic;
                private long createTime;
                private int salesVolume;
                private Object attachDocument;
                private Object collectFlag;
                private Object mallStore;
                private int ratePraise;
                private int goodCommonts;
                private int middleCommonts;
                private int badCommonts;
                private Object createType;
                private Object createNumber;
                private Object overdueTime;
                private int anticipatedRevenue;
                private String shareUrl;
                private String salesmanUrl;
                private Object shortUrl;
                private List<?> mallGoodsSpecNames;
                private List<?> goodsImages;

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public long getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(long goodsId) {
                    this.goodsId = goodsId;
                }

                public MallGoodsBean getMallGoods() {
                    return mallGoods;
                }

                public void setMallGoods(MallGoodsBean mallGoods) {
                    this.mallGoods = mallGoods;
                }

                public Object getStockPrice() {
                    return stockPrice;
                }

                public void setStockPrice(Object stockPrice) {
                    this.stockPrice = stockPrice;
                }

                public int getMarketPrice() {
                    return marketPrice;
                }

                public void setMarketPrice(int marketPrice) {
                    this.marketPrice = marketPrice;
                }

                public int getPlatformPrice() {
                    return platformPrice;
                }

                public void setPlatformPrice(int platformPrice) {
                    this.platformPrice = platformPrice;
                }

                public Object getManagerPrice() {
                    return managerPrice;
                }

                public void setManagerPrice(Object managerPrice) {
                    this.managerPrice = managerPrice;
                }

                public int getInventory() {
                    return inventory;
                }

                public void setInventory(int inventory) {
                    this.inventory = inventory;
                }

                public String getSku() {
                    return sku;
                }

                public void setSku(String sku) {
                    this.sku = sku;
                }

                public Object getMallCart() {
                    return mallCart;
                }

                public void setMallCart(Object mallCart) {
                    this.mallCart = mallCart;
                }

                public long getSpecaNameId() {
                    return specaNameId;
                }

                public void setSpecaNameId(long specaNameId) {
                    this.specaNameId = specaNameId;
                }

                public MallGoodsSpecANameBean getMallGoodsSpecAName() {
                    return mallGoodsSpecAName;
                }

                public void setMallGoodsSpecAName(MallGoodsSpecANameBean mallGoodsSpecAName) {
                    this.mallGoodsSpecAName = mallGoodsSpecAName;
                }

                public long getSpecaValueId() {
                    return specaValueId;
                }

                public void setSpecaValueId(long specaValueId) {
                    this.specaValueId = specaValueId;
                }

                public Object getSpecaValueIds() {
                    return specaValueIds;
                }

                public void setSpecaValueIds(Object specaValueIds) {
                    this.specaValueIds = specaValueIds;
                }

                public Object getMallGoodsSpecAValue() {
                    return mallGoodsSpecAValue;
                }

                public void setMallGoodsSpecAValue(Object mallGoodsSpecAValue) {
                    this.mallGoodsSpecAValue = mallGoodsSpecAValue;
                }

                public long getSpecbNameId() {
                    return specbNameId;
                }

                public void setSpecbNameId(long specbNameId) {
                    this.specbNameId = specbNameId;
                }

                public MallGoodsSpecBNameBean getMallGoodsSpecBName() {
                    return mallGoodsSpecBName;
                }

                public void setMallGoodsSpecBName(MallGoodsSpecBNameBean mallGoodsSpecBName) {
                    this.mallGoodsSpecBName = mallGoodsSpecBName;
                }

                public long getSpecbValueId() {
                    return specbValueId;
                }

                public void setSpecbValueId(long specbValueId) {
                    this.specbValueId = specbValueId;
                }

                public Object getSpecbValueIds() {
                    return specbValueIds;
                }

                public void setSpecbValueIds(Object specbValueIds) {
                    this.specbValueIds = specbValueIds;
                }

                public Object getMallGoodsSpecBValue() {
                    return mallGoodsSpecBValue;
                }

                public void setMallGoodsSpecBValue(Object mallGoodsSpecBValue) {
                    this.mallGoodsSpecBValue = mallGoodsSpecBValue;
                }

                public long getSpeccNameId() {
                    return speccNameId;
                }

                public void setSpeccNameId(long speccNameId) {
                    this.speccNameId = speccNameId;
                }

                public MallGoodsSpecCNameBean getMallGoodsSpecCName() {
                    return mallGoodsSpecCName;
                }

                public void setMallGoodsSpecCName(MallGoodsSpecCNameBean mallGoodsSpecCName) {
                    this.mallGoodsSpecCName = mallGoodsSpecCName;
                }

                public long getSpeccValueId() {
                    return speccValueId;
                }

                public void setSpeccValueId(long speccValueId) {
                    this.speccValueId = speccValueId;
                }

                public Object getSpeccValueIds() {
                    return speccValueIds;
                }

                public void setSpeccValueIds(Object speccValueIds) {
                    this.speccValueIds = speccValueIds;
                }

                public Object getMallGoodsSpecCValue() {
                    return mallGoodsSpecCValue;
                }

                public void setMallGoodsSpecCValue(Object mallGoodsSpecCValue) {
                    this.mallGoodsSpecCValue = mallGoodsSpecCValue;
                }

                public String getExamplePic() {
                    return examplePic;
                }

                public void setExamplePic(String examplePic) {
                    this.examplePic = examplePic;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public int getSalesVolume() {
                    return salesVolume;
                }

                public void setSalesVolume(int salesVolume) {
                    this.salesVolume = salesVolume;
                }

                public Object getAttachDocument() {
                    return attachDocument;
                }

                public void setAttachDocument(Object attachDocument) {
                    this.attachDocument = attachDocument;
                }

                public Object getCollectFlag() {
                    return collectFlag;
                }

                public void setCollectFlag(Object collectFlag) {
                    this.collectFlag = collectFlag;
                }

                public Object getMallStore() {
                    return mallStore;
                }

                public void setMallStore(Object mallStore) {
                    this.mallStore = mallStore;
                }

                public int getRatePraise() {
                    return ratePraise;
                }

                public void setRatePraise(int ratePraise) {
                    this.ratePraise = ratePraise;
                }

                public int getGoodCommonts() {
                    return goodCommonts;
                }

                public void setGoodCommonts(int goodCommonts) {
                    this.goodCommonts = goodCommonts;
                }

                public int getMiddleCommonts() {
                    return middleCommonts;
                }

                public void setMiddleCommonts(int middleCommonts) {
                    this.middleCommonts = middleCommonts;
                }

                public int getBadCommonts() {
                    return badCommonts;
                }

                public void setBadCommonts(int badCommonts) {
                    this.badCommonts = badCommonts;
                }

                public Object getCreateType() {
                    return createType;
                }

                public void setCreateType(Object createType) {
                    this.createType = createType;
                }

                public Object getCreateNumber() {
                    return createNumber;
                }

                public void setCreateNumber(Object createNumber) {
                    this.createNumber = createNumber;
                }

                public Object getOverdueTime() {
                    return overdueTime;
                }

                public void setOverdueTime(Object overdueTime) {
                    this.overdueTime = overdueTime;
                }

                public int getAnticipatedRevenue() {
                    return anticipatedRevenue;
                }

                public void setAnticipatedRevenue(int anticipatedRevenue) {
                    this.anticipatedRevenue = anticipatedRevenue;
                }

                public String getShareUrl() {
                    return shareUrl;
                }

                public void setShareUrl(String shareUrl) {
                    this.shareUrl = shareUrl;
                }

                public String getSalesmanUrl() {
                    return salesmanUrl;
                }

                public void setSalesmanUrl(String salesmanUrl) {
                    this.salesmanUrl = salesmanUrl;
                }

                public Object getShortUrl() {
                    return shortUrl;
                }

                public void setShortUrl(Object shortUrl) {
                    this.shortUrl = shortUrl;
                }

                public List<?> getMallGoodsSpecNames() {
                    return mallGoodsSpecNames;
                }

                public void setMallGoodsSpecNames(List<?> mallGoodsSpecNames) {
                    this.mallGoodsSpecNames = mallGoodsSpecNames;
                }

                public List<?> getGoodsImages() {
                    return goodsImages;
                }

                public void setGoodsImages(List<?> goodsImages) {
                    this.goodsImages = goodsImages;
                }

                public static class MallGoodsSpecANameBean {
                    private long id;
                    private Object ids;
                    private String name;
                    private String value;
                    private Object storeId;
                    private Object createTime;
                    private Object sortNo;
                    private List<?> mallGoodsSpecValues;

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public Object getIds() {
                        return ids;
                    }

                    public void setIds(Object ids) {
                        this.ids = ids;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public Object getStoreId() {
                        return storeId;
                    }

                    public void setStoreId(Object storeId) {
                        this.storeId = storeId;
                    }

                    public Object getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(Object createTime) {
                        this.createTime = createTime;
                    }

                    public Object getSortNo() {
                        return sortNo;
                    }

                    public void setSortNo(Object sortNo) {
                        this.sortNo = sortNo;
                    }

                    public List<?> getMallGoodsSpecValues() {
                        return mallGoodsSpecValues;
                    }

                    public void setMallGoodsSpecValues(List<?> mallGoodsSpecValues) {
                        this.mallGoodsSpecValues = mallGoodsSpecValues;
                    }
                }

                public static class MallGoodsSpecBNameBean {
                    private long id;
                    private Object ids;
                    private String name;
                    private String value;
                    private Object storeId;
                    private Object createTime;
                    private Object sortNo;
                    private List<?> mallGoodsSpecValues;

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public Object getIds() {
                        return ids;
                    }

                    public void setIds(Object ids) {
                        this.ids = ids;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public Object getStoreId() {
                        return storeId;
                    }

                    public void setStoreId(Object storeId) {
                        this.storeId = storeId;
                    }

                    public Object getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(Object createTime) {
                        this.createTime = createTime;
                    }

                    public Object getSortNo() {
                        return sortNo;
                    }

                    public void setSortNo(Object sortNo) {
                        this.sortNo = sortNo;
                    }

                    public List<?> getMallGoodsSpecValues() {
                        return mallGoodsSpecValues;
                    }

                    public void setMallGoodsSpecValues(List<?> mallGoodsSpecValues) {
                        this.mallGoodsSpecValues = mallGoodsSpecValues;
                    }
                }

                public static class MallGoodsSpecCNameBean {
                    private long id;
                    private Object ids;
                    private String name;
                    private String value;
                    private Object storeId;
                    private Object createTime;
                    private Object sortNo;
                    private List<?> mallGoodsSpecValues;

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public Object getIds() {
                        return ids;
                    }

                    public void setIds(Object ids) {
                        this.ids = ids;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public Object getStoreId() {
                        return storeId;
                    }

                    public void setStoreId(Object storeId) {
                        this.storeId = storeId;
                    }

                    public Object getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(Object createTime) {
                        this.createTime = createTime;
                    }

                    public Object getSortNo() {
                        return sortNo;
                    }

                    public void setSortNo(Object sortNo) {
                        this.sortNo = sortNo;
                    }

                    public List<?> getMallGoodsSpecValues() {
                        return mallGoodsSpecValues;
                    }

                    public void setMallGoodsSpecValues(List<?> mallGoodsSpecValues) {
                        this.mallGoodsSpecValues = mallGoodsSpecValues;
                    }
                }
            }

            public static class ImgListBean {
                private long id;
                private long goodsSpecId;
                private String imgUrl;
                private Object thumbnaiImgUrll;
                private int sort;
                private int status;
                private long createTime;
                private Object modifyTime;
                private int type;

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public long getGoodsSpecId() {
                    return goodsSpecId;
                }

                public void setGoodsSpecId(long goodsSpecId) {
                    this.goodsSpecId = goodsSpecId;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public Object getThumbnaiImgUrll() {
                    return thumbnaiImgUrll;
                }

                public void setThumbnaiImgUrll(Object thumbnaiImgUrll) {
                    this.thumbnaiImgUrll = thumbnaiImgUrll;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
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

                public Object getModifyTime() {
                    return modifyTime;
                }

                public void setModifyTime(Object modifyTime) {
                    this.modifyTime = modifyTime;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }
        }
    }
}
