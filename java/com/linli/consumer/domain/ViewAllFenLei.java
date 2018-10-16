package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/5/26.
 */
public class ViewAllFenLei {
    private String Type;//筛选下拉框中的种类
    private String number;//筛选下拉框中的数量
    //private List<T> mTList;
    public ViewAllFenLei(String type, String number) {
        Type = type;
        this.number = number;
    }

    public String getType() {

        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
