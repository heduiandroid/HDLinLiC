package com.linli.consumer.bean;

import java.util.List;

public class ShopVo {
	
	private HdMallStore hdMallStore;       //商城店铺

	private List<MallGoodsVo> voList;               //店铺商品列表

	private HdMallStoreoperate hdMallStoreoperate;   //店铺附加信息



	private Long memId;                              //用户id
	
	private int type;                             //店铺类型1-商城2-订餐3-服务
	
	private int pageNum;
	
	private int numPerPage;
	
	private Long storeId;                      //店铺id
	
	private int grab;                          //0-拒令 1-收令
	
	private int isgrab;                       //抢单模式1-指派2-抢单
	
	private int openStatus;                   //是否开启 0：开店，1：闭店
	
	private String name;                     //店铺名称
	
	 private String info;
	 
	 private String nickname;
	 
	 private String logoImg;

	private int praiseNum;                 //点赞数
	private int storeUserNum;             //会员数

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	public int getStoreUserNum() {
		return storeUserNum;
	}

	public void setStoreUserNum(int storeUserNum) {
		this.storeUserNum = storeUserNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public List<MallGoodsVo> getVoList() {
		return voList;
	}

	public void setVoList(List<MallGoodsVo> voList) {
		this.voList = voList;
	}

	public int getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(int openStatus) {
		this.openStatus = openStatus;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public int getGrab() {
		return grab;
	}

	public void setGrab(int grab) {
		this.grab = grab;
	}

	public int getIsgrab() {
		return isgrab;
	}

	public void setIsgrab(int isgrab) {
		this.isgrab = isgrab;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public Long getMemId() {
		return memId;
	}

	public void setMemId(Long memId) {
		this.memId = memId;
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

	public HdMallStore getHdMallStore() {
		return hdMallStore;
	}

	public void setHdMallStore(HdMallStore hdMallStore) {
		this.hdMallStore = hdMallStore;
	}


	public HdMallStoreoperate getHdMallStoreoperate() {
		return hdMallStoreoperate;
	}

	public void setHdMallStoreoperate(HdMallStoreoperate hdMallStoreoperate) {
		this.hdMallStoreoperate = hdMallStoreoperate;
	}
}
