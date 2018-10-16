package com.linli.consumer.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class UserNeedAndFeedback {
    private Long id;//需求反馈ID
    private String userHead;
    private Integer type;
    private String time;//语音时间
    private Long createTime;
    private String status;//需求与反馈的状态（类似订单状态）
    private String codeScannerData;//扫码的需求信息
    private String dataPath;//语音或照片的路径
    private String mText;//文字需求文字
    private List<Shop> backProducts;//商家反馈的商品信息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodeScannerData() {
        return codeScannerData;
    }

    public void setCodeScannerData(String codeScannerData) {
        this.codeScannerData = codeScannerData;
    }


    public List<Shop> getBackProducts() {
        return backProducts;
    }

    public void setBackProducts(List<Shop> backProducts) {
        this.backProducts = backProducts;
    }
}
