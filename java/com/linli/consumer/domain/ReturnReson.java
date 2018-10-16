package com.linli.consumer.domain;

/**
 * Created by hasee on 2018/5/19.
 */

public class ReturnReson {
    private String reson;
    private String resonExtra;
    private Boolean isChoosed;

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getResonExtra() {
        return resonExtra;
    }

    public void setResonExtra(String resonExtra) {
        this.resonExtra = resonExtra;
    }

    public Boolean getChoosed() {
        return isChoosed;
    }

    public void setChoosed(Boolean choosed) {
        isChoosed = choosed;
    }
}
