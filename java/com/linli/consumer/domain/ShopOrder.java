package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/7/13.
 */
public class ShopOrder {

    private int Id;
    private String totalOrderNo;
    private String OrderNo;
    private int Price;
    private String BuyerEmail;
    private String TradeNo;
    private int Status;
    private String paykind;
    private int PayType;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTotalOrderNo() {
        return totalOrderNo;
    }

    public void setTotalOrderNo(String totalOrderNo) {
        this.totalOrderNo = totalOrderNo;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getBuyerEmail() {
        return BuyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        BuyerEmail = buyerEmail;
    }

    public String getTradeNo() {
        return TradeNo;
    }

    public void setTradeNo(String tradeNo) {
        TradeNo = tradeNo;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getPaykind() {
        return paykind;
    }

    public void setPaykind(String paykind) {
        this.paykind = paykind;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }
}
