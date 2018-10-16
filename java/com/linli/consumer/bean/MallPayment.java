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

/**
 * <p>
 * Title: 支付方式表
 * </p>
 * <p>
 * Description: 支付方式表
 * </p>
 * 
 * @author mine
 */
public class MallPayment {


		private Long id;
	

		private String code;
	
		private String name;
	

		private String description;
	

		private String config;
	
		/**
		 * 开启状态
		 * 0=开启
		 * 1=关闭
		 */
		private Integer openStatus;
	
		private String payImg;
	
		private Integer isDefault;	
		
		private BigDecimal account;
		
		public void setId(Long value) {
			this.id = value;
		}
		
		public Long getId() {
			return this.id;
		}
			
		public void setCode(String value) {
			this.code = value;
		}
		
		public String getCode() {
			return this.code;
		}
			
		public void setName(String value) {
			this.name = value;
		}
		
		public String getName() {
			return this.name;
		}
			
		public void setDescription(String value) {
			this.description = value;
		}
		
		public String getDescription() {
			return this.description;
		}
			
		public void setConfig(String value) {
			this.config = value;
		}
		
		public String getConfig() {
			return this.config;
		}
			
		public void setOpenStatus(Integer value) {
			this.openStatus = value;
		}
		
		public Integer getOpenStatus() {
			return this.openStatus;
		}

		public String getPayImg() {
			return payImg;
		}

		public void setPayImg(String payImg) {
			this.payImg = payImg;
		}

		public Integer getIsDefault() {
			return isDefault;
		}

		public void setIsDefault(Integer isDefault) {
			this.isDefault = isDefault;
		}

		public BigDecimal getAccount() {
			return account;
		}

		public void setAccount(BigDecimal account) {
			this.account = account;
		}
		
}
