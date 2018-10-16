/**************************
 * 版权声明 ************************
 * <p>
 * 版权所有：
 * <p>
 * ************************ 变更记录 *************************
 * <p>
 * 创建者：mine  创建日期：
 * <p>
 * 修改者：   修改日期：
 * <p>
 * 修改记录：//
 * <p>
 * ***********************************************************
 */

package com.linli.consumer.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * Title: 商品表
 * </p>
 * <p>
 * Description: 商品表
 * </p>
 *
 *
 *
 *   {@link com.linli.consumer.net.IShopApi#goodsListOfShop}
 *
 * @author mine
 */
public class MallGoodsVo implements Serializable {

    private Integer pageNum;

    private Integer numPerPage;

    private Integer num;

    private MallGoods mallGoods;    //基础信息

    private BigDecimal stockPrice;      //进货价

    private BigDecimal marketPrice;       //市场价

    private BigDecimal platformPrice;      //平台价,有规格价格

    private String imgSrc;              //图片，有规格



    private String storeName;

    private Integer identifying;

    private Integer sort;

    private String storeIdStr;

    private int number;        //自定义新增属性，用于本地存放数量数据
    private boolean isShow;      //自定义新增字段，是否显示标志 true为显示(有数量),false为不显示(0)

    private List<MallGoodsSpec> specList;     //商品规格属性
    private List<MallGoodsSpecVo> voList;     //商品详情对应的值

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public List<MallGoodsSpecVo> getVoList() {
        return voList;
    }

    public void setVoList(List<MallGoodsSpecVo> voList) {
        this.voList = voList;
    }

    public List<MallGoodsSpec> getSpecList() {
        return specList;
    }

    public void setSpecList(List<MallGoodsSpec> specList) {
        this.specList = specList;
    }

    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(BigDecimal stockPrice) {
        this.stockPrice = stockPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getPlatformPrice() {
        return platformPrice;
    }

    public void setPlatformPrice(BigDecimal platformPrice) {
        this.platformPrice = platformPrice;
    }

    public MallGoods getMallGoods() {
        return this.mallGoods;
    }

    public void setMallGoods(MallGoods value) {
        this.mallGoods = value;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getIdentifying() {
        return identifying;
    }

    public void setIdentifying(Integer identifying) {
        this.identifying = identifying;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStoreIdStr() {
        return storeIdStr;
    }

    public void setStoreIdStr(String storeIdStr) {
        this.storeIdStr = storeIdStr;
    }


}
