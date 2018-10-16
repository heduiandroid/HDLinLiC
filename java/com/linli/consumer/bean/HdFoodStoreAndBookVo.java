package com.linli.consumer.bean;

import java.io.Serializable;
import java.util.List;

public class HdFoodStoreAndBookVo implements Serializable {

	private List<HdFoodCookbook> cookbookList;

	public List<HdFoodCookbook> getCookbookList() {
		return cookbookList;
	}

	public void setCookbookList(List<HdFoodCookbook> cookbookList) {
		this.cookbookList = cookbookList;
	}
	
}
