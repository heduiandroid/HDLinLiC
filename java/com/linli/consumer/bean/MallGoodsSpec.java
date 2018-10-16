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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * Title: 商品规格表
 * </p>
 * <p>
 * Description: 商品规格表
 * </p>
 * 
 * @author mine
 */
public class MallGoodsSpec {

	private Long id;

	private Long goodsId;

	private MallGoods mallGoods;

	//进货价
	private BigDecimal stockPrice;

	//市场价
	private BigDecimal marketPrice;

	//平台价
	private BigDecimal platformPrice;

	//平台给掌柜的价格
	private BigDecimal managerPrice;

	private Integer inventory;

	private String sku;


	private Long specaNameId;


	private Long specaValueId;

	private List<Long> specaValueIds;


	private Long specbNameId;


	private Long specbValueId;

	private List<Long> specbValueIds;


	private Long speccNameId;


	private Long speccValueId;

	private List<Long> speccValueIds;


	private String examplePic;

	private Date createTime;

	private Integer salesVolume;
	//附加图片
	private String attachDocument;

	private Integer collectFlag;


	private HdMallStore mallStore;

	//好评率
	private Integer ratePraise;

	//好评数
	private Integer goodCommonts;

	//中评数
	private Integer middleCommonts;

	//差评数
	private Integer badCommonts;

	//生成方式
	private Integer createType;

	//自动生成码值的个数
	private Integer createNumber;

	/**
	 * 过期时间
	 */
	private Date overdueTime;

	private double anticipatedRevenue;

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}

	public void setGoodsId(Long value) {
		this.goodsId = value;
	}

	public Long getGoodsId() {
		return this.goodsId;
	}

	public void setStockPrice(BigDecimal value) {
		this.stockPrice = value;
	}

	public BigDecimal getStockPrice() {
		return this.stockPrice;
	}

	public void setMarketPrice(BigDecimal value) {
		this.marketPrice = value;
	}

	public BigDecimal getMarketPrice() {
		return this.marketPrice;
	}

	public void setPlatformPrice(BigDecimal value) {
		this.platformPrice = value;
	}

	public BigDecimal getPlatformPrice() {
		return this.platformPrice;
	}

	public void setManagerPrice(BigDecimal value) {
		this.managerPrice = value;
	}

	public BigDecimal getManagerPrice() {
		return this.managerPrice;
	}

	public void setInventory(Integer value) {
		this.inventory = value;
	}

	public Integer getInventory() {
		return this.inventory;
	}

	public void setSku(String value) {
		this.sku = value;
	}

	public String getSku() {
		return this.sku;
	}

	public void setSpecaNameId(Long value) {
		this.specaNameId = value;
	}

	public Long getSpecaNameId() {
		return this.specaNameId;
	}

	public void setSpecaValueId(Long value) {
		this.specaValueId = value;
	}

	public Long getSpecaValueId() {
		return this.specaValueId;
	}

	public void setSpecbNameId(Long value) {
		this.specbNameId = value;
	}

	public Long getSpecbNameId() {
		return this.specbNameId;
	}

	public void setSpecbValueId(Long value) {
		this.specbValueId = value;
	}

	public Long getSpecbValueId() {
		return this.specbValueId;
	}

	public void setSpeccNameId(Long value) {
		this.speccNameId = value;
	}

	public Long getSpeccNameId() {
		return this.speccNameId;
	}

	public void setSpeccValueId(Long value) {
		this.speccValueId = value;
	}

	public Long getSpeccValueId() {
		return this.speccValueId;
	}


	public MallGoods getMallGoods() {
		return mallGoods;
	}

	public void setMallGoods(MallGoods mallGoods) {
		this.mallGoods = mallGoods;
	}

	public String getExamplePic() {
		return examplePic;
	}

	public void setExamplePic(String examplePic) {
		this.examplePic = examplePic;
	}

	public List<Long> getSpecaValueIds() {
		return specaValueIds;
	}

	public void setSpecaValueIds(List<Long> specaValueIds) {
		this.specaValueIds = specaValueIds;
	}

	public List<Long> getSpecbValueIds() {
		return specbValueIds;
	}

	public void setSpecbValueIds(List<Long> specbValueIds) {
		this.specbValueIds = specbValueIds;
	}

	public List<Long> getSpeccValueIds() {
		return speccValueIds;
	}

	public void setSpeccValueIds(List<Long> speccValueIds) {
		this.speccValueIds = speccValueIds;
	}

	public String getAttachDocument() {
		return attachDocument;
	}

	public void setAttachDocument(String attachDocument) {
		this.attachDocument = attachDocument;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}


	public Integer getCollectFlag() {
		return collectFlag;
	}

	public void setCollectFlag(Integer collectFlag) {
		this.collectFlag = collectFlag;
	}

	public HdMallStore getMallStore() {
		return mallStore;
	}

	public void setMallStore(HdMallStore mallStore) {
		this.mallStore = mallStore;
	}

	public Integer getRatePraise() {
		return ratePraise;
	}

	public void setRatePraise(Integer ratePraise) {
		this.ratePraise = ratePraise;
	}

	public Integer getGoodCommonts() {
		return goodCommonts;
	}

	public void setGoodCommonts(Integer goodCommonts) {
		this.goodCommonts = goodCommonts;
	}

	public Integer getMiddleCommonts() {
		return middleCommonts;
	}

	public void setMiddleCommonts(Integer middleCommonts) {
		this.middleCommonts = middleCommonts;
	}

	public Integer getBadCommonts() {
		return badCommonts;
	}

	public void setBadCommonts(Integer badCommonts) {
		this.badCommonts = badCommonts;
	}


	public Integer getCreateNumber() {
		return createNumber;
	}

	public void setCreateNumber(Integer createNumber) {
		this.createNumber = createNumber;
	}

	public Integer getCreateType() {
		return createType;
	}

	public void setCreateType(Integer createType) {
		this.createType = createType;
	}

	public double getAnticipatedRevenue() {
		return anticipatedRevenue;
	}

	public void setAnticipatedRevenue(double anticipatedRevenue) {
		this.anticipatedRevenue = anticipatedRevenue;
	}

	public Date getOverdueTime() {
		return overdueTime;
	}

	public void setOverdueTime(Date overdueTime) {
		this.overdueTime = overdueTime;
	}



		
}
