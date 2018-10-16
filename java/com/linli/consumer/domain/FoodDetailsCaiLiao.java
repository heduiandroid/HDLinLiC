package com.linli.consumer.domain;

/**
 * Created by wwy on 2016/5/22.// 菜品详情中的材料
 */
public class FoodDetailsCaiLiao {
    private String CaiLiao;//对应的材料
    private String CaiLiaoNum;//对应的材料数量

    public FoodDetailsCaiLiao() {
    }

    public FoodDetailsCaiLiao(String caiLiaoNum, String caiLiao) {
        CaiLiaoNum = caiLiaoNum;
        CaiLiao = caiLiao;
    }

    public String getCaiLiao() {
        return CaiLiao;
    }

    public void setCaiLiao(String caiLiao) {
        CaiLiao = caiLiao;
    }

    public String getCaiLiaoNum() {
        return CaiLiaoNum;
    }

    public void setCaiLiaoNum(String caiLiaoNum) {
        CaiLiaoNum = caiLiaoNum;
    }
}
