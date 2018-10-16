package com.linli.consumer.domain;

/**
 * Created by wwy on 2016/5/22.
 */
public class FoodDetailsStep {
    private String stepNum;//操作条数
    private String stepDetails;//操作明细
    private int stepImg;//操作图片

    public FoodDetailsStep() {
    }

    public FoodDetailsStep(String stepNum, String stepDetails, int stepImg) {
        this.stepNum = stepNum;
        this.stepDetails = stepDetails;
        this.stepImg = stepImg;
    }

    public String getStepNum() {

        return stepNum;
    }

    public void setStepNum(String stepNum) {
        this.stepNum = stepNum;
    }

    public String getStepDetails() {
        return stepDetails;
    }

    public void setStepDetails(String stepDetails) {
        this.stepDetails = stepDetails;
    }

    public int getStepImg() {
        return stepImg;
    }

    public void setStepImg(int stepImg) {
        this.stepImg = stepImg;
    }
}
