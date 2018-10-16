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
public class MallGoodsSpecVo {

		private MallGoodsSpec mallGoodsSpec; 
		
		private Long specANameId;
		
		private String specAName;		//规格属性 名字
		private String spec1values;		//规格属性 值
		

		private Long specBNameId;
		
		private String specBName;
		private String spec2values;

		private Long specCNameId;
		
		private String specCName;
		private String spec3values;

		
		private List<MallGoodsImage> imgList;    //规格对应图片
		
		
		
		private Integer pageNum;
		
		private Integer numPerPage;
		//商品分类id
		private Long categoryId;
		//排序标识
		private Integer identification;
		//店铺id
		private Long storeId;
		
		//筛选条件(包邮)
		private Integer  freeShipping;
		
		//筛选条件(糖币抵钱)
		private Integer sugarDollar;
		
	    //筛选条件(只看有货)
		private Integer havingGoods;
		
		//最低价
		private BigDecimal minPrice;
		
		//最高价
		private BigDecimal maxPrice;
		
		//商品名称
		private String goodsName;
		
		//店铺分类id
		private Long storeCateId;
		
			
		public List<MallGoodsImage> getImgList() {
			return imgList;
		}

		public void setImgList(List<MallGoodsImage> imgList) {
			this.imgList = imgList;
		}

		public void setMallGoodsSpec(MallGoodsSpec value) {
			this.mallGoodsSpec = value;
		}
		
		public MallGoodsSpec getMallGoodsSpec() {
			return this.mallGoodsSpec;
		}

		public Long getSpecANameId() {
			return specANameId;
		}

		public void setSpecANameId(Long specANameId) {
			this.specANameId = specANameId;
		}

		public String getSpecAName() {
			return specAName;
		}

		public void setSpecAName(String specAName) {
			this.specAName = specAName;
		}


		public Long getSpecBNameId() {
			return specBNameId;
		}

		public void setSpecBNameId(Long specBNameId) {
			this.specBNameId = specBNameId;
		}

		public String getSpecBName() {
			return specBName;
		}

		public void setSpecBName(String specBName) {
			this.specBName = specBName;
		}


		public Long getSpecCNameId() {
			return specCNameId;
		}

		public void setSpecCNameId(Long specCNameId) {
			this.specCNameId = specCNameId;
		}

		public String getSpecCName() {
			return specCName;
		}

		public void setSpecCName(String specCName) {
			this.specCName = specCName;
		}


		public Integer getPageNum() {
			return pageNum;
		}

		public void setPageNum(Integer pageNum) {
			this.pageNum = pageNum;
		}

		public Integer getNumPerPage() {
			return numPerPage;
		}

		public void setNumPerPage(Integer numPerPage) {
			this.numPerPage = numPerPage;
		}

		public Long getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}

		public Integer getIdentification() {
			return identification;
		}

		public void setIdentification(Integer identification) {
			this.identification = identification;
		}

		public Long getStoreId() {
			return storeId;
		}

		public void setStoreId(Long storeId) {
			this.storeId = storeId;
		}


		public BigDecimal getMinPrice() {
			return minPrice;
		}

		public void setMinPrice(BigDecimal minPrice) {
			this.minPrice = minPrice;
		}

		public BigDecimal getMaxPrice() {
			return maxPrice;
		}

		public void setMaxPrice(BigDecimal maxPrice) {
			this.maxPrice = maxPrice;
		}

		public Integer getFreeShipping() {
			return freeShipping;
		}

		public void setFreeShipping(Integer freeShipping) {
			this.freeShipping = freeShipping;
		}

		public Integer getSugarDollar() {
			return sugarDollar;
		}

		public void setSugarDollar(Integer sugarDollar) {
			this.sugarDollar = sugarDollar;
		}

		public Integer getHavingGoods() {
			return havingGoods;
		}

		public void setHavingGoods(Integer havingGoods) {
			this.havingGoods = havingGoods;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public Long getStoreCateId() {
			return storeCateId;
		}

		public void setStoreCateId(Long storeCateId) {
			this.storeCateId = storeCateId;
		}

		public String getSpec1values() {
			return spec1values;
		}

		public void setSpec1values(String spec1values) {
			this.spec1values = spec1values;
		}

		public String getSpec2values() {
			return spec2values;
		}

		public void setSpec2values(String spec2values) {
			this.spec2values = spec2values;
		}

		public String getSpec3values() {
			return spec3values;
		}

		public void setSpec3values(String spec3values) {
			this.spec3values = spec3values;
		}
		
		
}
