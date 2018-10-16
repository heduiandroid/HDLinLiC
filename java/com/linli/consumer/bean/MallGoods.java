/************************** 版权声明 ************************
 * 
 * 版权所有：
 * 
 ************************* 变更记录 *************************
 *
 * 创建者：mine  创建日期：
 * 
 * 修改者：   修改日期：
 * 
 * 修改记录：//
 * 
 ************************************************************
 */

package com.linli.consumer.bean;

import java.util.Date;
import java.math.BigDecimal;

//import com.zzkx.mall.utils.CurrentUserIsSalesmanUtil;
/**
 * <p>
 * Title: 商品表
 * </p>
 * <p>
 * Description: 商品表
 * </p>
 * 
 * @author mine
 */
public class MallGoods {


		private Long id;
	

		private String name;
	

		private String description;
	

		private Long storeId;
	

		private Long categoryId;
	

		private Integer isShow;
	

		private String primaryImage;
	

		private Integer inventory;
	

		private String param;
	

		private Integer commission;
	

		private String info;
	

		private String countryImg;
	

		private String shipmentsInfo;
	

		private Integer sort;
	

		private Integer approveStatus;
	

		private String yieldly;
	

		private Long brandId;
	

		private String keyword;
	

		private Integer isRecommend;
	

		private Integer stockpile;
	

		private BigDecimal nospecStockPrice;
	

		private BigDecimal nospecPlatformPrice;			//没有规格
	

		private BigDecimal nospecMarketPrice;
	

		private BigDecimal nospecManagerPrice;
	

		private String nospecSku;
	

		private Integer isSpec;		//0:无规格   1:有规格
	

		private Date createTime;
	

		private Date modifyTime;
	

		private Integer status;
		//销量
		private Long salesVolume;
	 
		private HdMallStore mallStore;
		
		private Long storeCategoryId;
		
		private Integer platformProfit;
		
		private Integer storeType;
		//评论数
		private Integer commentNum;
		//月销量
		private Integer monthSaleNum;
		
		private String packagingAfterSale;
		


		private Integer showSeven;
		
		private Integer isTrue;
	
		private Integer speedRefund;
		//糖币使用比例
		private Integer sweetMoneyScale;
		//邮费
		//private Integer logisticsAmount;
		
		//商品类型
		private Integer goodsType;
		
		//生成方式
		private Integer createType;
		
		//自动生成码值的个数
		private Integer createNumber;
		
		/**
		 * 过期时间
		 */
		private Date overdueTime;
		
		private String specsKayString ;

	    private BigDecimal maxprice;
        private BigDecimal minprice;        //要显示的价格
	private String brand;//品牌

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

    public HdMallStore getMallStore() {
			return mallStore;
		}

		public void setMallStore(HdMallStore mallStore) {
			this.mallStore = mallStore;
		}

		public void setId(Long value) {
			this.id = value;
		}
		
		public Long getId() {
			return this.id;
		}
			
		public void setName(String value) {
			this.name = value;
		}

	public String getName() {
		return name;
	}

	public void setDescription(String value) {
			this.description = value;
		}
		
		public String getDescription() {
			return this.description;
		}
			
		public void setStoreId(Long value) {
			this.storeId = value;
		}
		
		public Long getStoreId() {
			return this.storeId;
		}
			
		public void setCategoryId(Long value) {
			this.categoryId = value;
		}
		
		public Long getCategoryId() {
			return this.categoryId;
		}
			
		public void setIsShow(Integer value) {
			this.isShow = value;
		}
		
		public Integer getIsShow() {
			return this.isShow;
		}
			
		public void setPrimaryImage(String value) {
			this.primaryImage = value;
		}
		
		public String getPrimaryImage() {
			return this.primaryImage;
		}
			
		public void setInventory(Integer value) {
			this.inventory = value;
		}
		
		public Integer getInventory() {
			return this.inventory;
		}
			
		public void setParam(String value) {
			this.param = value;
		}
		
		public String getParam() {
			return this.param;
		}
			
		public void setCommission(Integer value) {
			this.commission = value;
		}
		
		public Integer getCommission() {
			return this.commission;
		}
			
		public void setInfo(String value) {
			this.info = value;
		}
		
		public String getInfo() {
			return this.info;
		}
			
		public void setCountryImg(String value) {
			this.countryImg = value;
		}
		
		public String getCountryImg() {
			return this.countryImg;
		}
			
		public void setShipmentsInfo(String value) {
			this.shipmentsInfo = value;
		}
		
		public String getShipmentsInfo() {
			return this.shipmentsInfo;
		}
			
		public void setSort(Integer value) {
			this.sort = value;
		}
		
		public Integer getSort() {
			return this.sort;
		}
			
		public void setApproveStatus(Integer value) {
			this.approveStatus = value;
		}
		
		public Integer getApproveStatus() {
			return this.approveStatus;
		}
			
		public void setYieldly(String value) {
			this.yieldly = value;
		}
		
		public String getYieldly() {
			return this.yieldly;
		}
			
		public void setBrandId(Long value) {
			this.brandId = value;
		}
		
		public Long getBrandId() {
			return this.brandId;
		}
			
		public void setKeyword(String value) {
			this.keyword = value;
		}
		
		public String getKeyword() {
			return this.keyword;
		}
			
		public void setIsRecommend(Integer value) {
			this.isRecommend = value;
		}
		
		public Integer getIsRecommend() {
			return this.isRecommend;
		}
			
		public void setStockpile(Integer value) {
			this.stockpile = value;
		}
		
		public Integer getStockpile() {
			return this.stockpile;
		}
			
		public void setNospecStockPrice(BigDecimal value) {
			this.nospecStockPrice = value;
		}
		
		public BigDecimal getNospecStockPrice() {
			return this.nospecStockPrice;
		}
			
		public void setNospecPlatformPrice(BigDecimal value) {
			this.nospecPlatformPrice = value;
		}
		
		public BigDecimal getNospecPlatformPrice() {
			return this.nospecPlatformPrice;
		}
			
		public void setNospecMarketPrice(BigDecimal value) {
			this.nospecMarketPrice = value;
		}
		
		public BigDecimal getNospecMarketPrice() {
			return this.nospecMarketPrice;
		}
			
		public void setNospecManagerPrice(BigDecimal value) {
			this.nospecManagerPrice = value;
		}
		
		public BigDecimal getNospecManagerPrice() {
			return this.nospecManagerPrice;
		}
			
		public void setNospecSku(String value) {
			this.nospecSku = value;
		}
		
		public String getNospecSku() {
			return this.nospecSku;
		}
			
		public void setIsSpec(Integer value) {
			this.isSpec = value;
		}
		
		public Integer getIsSpec() {
			return this.isSpec;
		}
			
		public void setCreateTime(Date value) {
			this.createTime = value;
		}
		
		public Date getCreateTime() {
			return this.createTime;
		}
			
		public void setModifyTime(Date value) {
			this.modifyTime = value;
		}
		
		public Date getModifyTime() {
			return this.modifyTime;
		}
			
		public void setStatus(Integer value) {
			this.status = value;
		}
		
		public Integer getStatus() {
			return this.status;
		}

		public Long getSalesVolume() {
			return salesVolume;
		}

		public void setSalesVolume(Long salesVolume) {
			this.salesVolume = salesVolume;
		}

		public Integer getPlatformProfit() {
			return platformProfit;
		}

		public void setPlatformProfit(Integer platformProfit) {
			this.platformProfit = platformProfit;
		}

		public Integer getStoreType() {
			return storeType;
		}

		public void setStoreType(Integer storeType) {
			this.storeType = storeType;
		}

		public String getPackagingAfterSale() {
			return packagingAfterSale;
		}

		public void setPackagingAfterSale(String packagingAfterSale) {
			this.packagingAfterSale = packagingAfterSale;
		}

		public Long getStoreCategoryId() {
			return storeCategoryId;
		}

		public void setStoreCategoryId(Long storeCategoryId) {
			this.storeCategoryId = storeCategoryId;
		}


		public Integer getCommentNum() {
			return commentNum;
		}

		public Integer getMonthSaleNum() {
			return monthSaleNum;
		}

		public Integer getShowSeven() {
			return showSeven;
		}

		public void setShowSeven(Integer showSeven) {
			this.showSeven = showSeven;
		}

		public Integer getIsTrue() {
			return isTrue;
		}

		public void setIsTrue(Integer isTrue) {
			this.isTrue = isTrue;
		}

		public Integer getSpeedRefund() {
			return speedRefund;
		}

		public void setSpeedRefund(Integer speedRefund) {
			this.speedRefund = speedRefund;
		}


		public Integer getSweetMoneyScale() {
			return sweetMoneyScale;
		}

		public void setSweetMoneyScale(Integer sweetMoneyScale) {
			this.sweetMoneyScale = sweetMoneyScale;
		}
/*
		public Integer getLogisticsAmount() {
			return logisticsAmount;
		}

		public void setLogisticsAmount(Integer logisticsAmount) {
			this.logisticsAmount = logisticsAmount;
		}
		*/

		public Integer getGoodsType() {
			return goodsType;
		}

		public void setGoodsType(Integer goodsType) {
			this.goodsType = goodsType;
		}

		public Integer getCreateType() {
			return createType;
		}

		public void setCreateType(Integer createType) {
			this.createType = createType;
		}

		public Integer getCreateNumber() {
			return createNumber;
		}

		public void setCreateNumber(Integer createNumber) {
			this.createNumber = createNumber;
		}

		public void setCommentNum(Integer commentNum) {
			this.commentNum = commentNum;
		}

		public void setMonthSaleNum(Integer monthSaleNum) {
			this.monthSaleNum = monthSaleNum;
		}

		public Date getOverdueTime() {
			return overdueTime;
		}

		public void setOverdueTime(Date overdueTime) {
			this.overdueTime = overdueTime;
		}

		public String getSpecsKayString() {
			return specsKayString;
		}

		public void setSpecsKayString(String specsKayString) {
			this.specsKayString = specsKayString;
		}

		/**
		 * 是否是销售员
		 * @return
		 */
//		public Integer getIsSalesman(){
//			return CurrentUserIsSalesmanUtil.getIsSalesman();
//		}
		
		
		
		
}
