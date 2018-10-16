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
/**
 * <p>
 * Title: 商品图片表
 * </p>
 * <p>
 * Description: 商品图片表
 * </p>
 * 
 * @author mine
 */
public class MallGoodsImage {


		private Long id;
	

		private Long goodsSpecId;
	

		private String imgUrl;
	

		private String thumbnaiImgUrll;
	

		private Integer sort;
	

		private Integer status;
	

		private Date createTime;
	

		private Date modifyTime;
	

		private Integer type;
	
	
	
			
		public void setId(Long value) {
			this.id = value;
		}
		
		public Long getId() {
			return this.id;
		}
			
	
		public void setImgUrl(String value) {
			this.imgUrl = value;
		}
		
		public String getImgUrl() {
			return this.imgUrl;
		}
			
		public void setThumbnaiImgUrll(String value) {
			this.thumbnaiImgUrll = value;
		}
		
		public String getThumbnaiImgUrll() {
			return this.thumbnaiImgUrll;
		}
			
		public void setSort(Integer value) {
			this.sort = value;
		}
		
		public Integer getSort() {
			return this.sort;
		}
			
		public void setStatus(Integer value) {
			this.status = value;
		}
		
		public Integer getStatus() {
			return this.status;
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
			
		public void setType(Integer value) {
			this.type = value;
		}
		
		public Integer getType() {
			return this.type;
		}

		public Long getGoodsSpecId() {
			return goodsSpecId;
		}

		public void setGoodsSpecId(Long goodsSpecId) {
			this.goodsSpecId = goodsSpecId;
		}

		
}
