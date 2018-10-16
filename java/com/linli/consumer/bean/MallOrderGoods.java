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
/**
 * <p>
 * Title: 订单商品详情表
 * </p>
 * <p>
 * Description: 订单商品详情表
 * </p>
 * 
 * @author mine
 */
public class MallOrderGoods {


		private Long id;
	

		private Long orderId;
	

		private Long specId;
	

		private Integer goodsNum;
	

		private Date createTime;
	

		private Integer isEvaluation;
	
		private Long goodsId;
		
		private String goodsSpec;
		
		private MallGoods mallGoods;
		
		private MallGoodsSpec mallGoodsSpec;
		//退款状态
		private Integer refundStatus;
		
		private Integer commentStatus;
		
		private BigDecimal price;
		//地址
		private Long addressId=null;
		
		public void setId(Long value) {
			this.id = value;
		}
		
		public Long getId() {
			return this.id;
		}
			
		public void setOrderId(Long value) {
			this.orderId = value;
		}
		
		public Long getOrderId() {
			return this.orderId;
		}
			
		public void setSpecId(Long value) {
			this.specId = value;
		}
		
		public Long getSpecId() {
			return this.specId;
		}
			
		public void setGoodsNum(Integer value) {
			this.goodsNum = value;
		}
		
		public Integer getGoodsNum() {
			return this.goodsNum;
		}
			
		public void setCreateTime(Date value) {
			this.createTime = value;
		}
		
		public Date getCreateTime() {
			return this.createTime;
		}
			
		public void setIsEvaluation(Integer value) {
			this.isEvaluation = value;
		}
		
		public Integer getIsEvaluation() {
			return this.isEvaluation;
		}

		public Long getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(Long goodsId) {
			this.goodsId = goodsId;
		}
		
		public String getGoodsSpec() {
			
			return goodsSpec;
		}

		public void setGoodsSpec(String goodsSpec) {
			this.goodsSpec = goodsSpec;
		}

		public MallGoods getMallGoods() {
			return mallGoods;
		}

		public void setMallGoods(MallGoods mallGoods) {
			this.mallGoods = mallGoods;
		}

		public MallGoodsSpec getMallGoodsSpec() {
			return mallGoodsSpec;
		}

		public void setMallGoodsSpec(MallGoodsSpec mallGoodsSpec) {
			this.mallGoodsSpec = mallGoodsSpec;
		}

		public Integer getRefundStatus() {
			return refundStatus;
		}

		public void setRefundStatus(Integer refundStatus) {
			this.refundStatus = refundStatus;
		}

		public Integer getCommentStatus() {
			return commentStatus;
		}

		public void setCommentStatus(Integer commentStatus) {
			this.commentStatus = commentStatus;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public Long getAddressId() {
			return addressId;
		}

		public void setAddressId(Long addressId) {
			this.addressId = addressId;
		}
		
}
