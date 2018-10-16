package com.linli.consumer.mock;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wds1993225 on 2016/8/17.
 */
public class NewsBean implements Serializable{



    private String reason;


    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        private String stat;

        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String title;
            private String date;
            private String author_name;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;
            private String url;
            private String uniquekey;
            private String type;
            private String realtype;

            private boolean isBorder = false;

            public boolean isBorder() {
                return isBorder;
            }

            public void setBorder(boolean border) {
                isBorder = border;
            }


            //新添加字段
            //TODO

            private String name;        //菜名
            private int number;         //数量
            private int price;          //价格
            private int position;       //位置
            private boolean isShow;      //是否显示标志 true为显示(有数量),false为不显示(0)
            private String sort;        //分类 由大适配器确定

            private boolean isAdd;      //是否添加到购物车

            private boolean isGet;      //代金券是否领取

            public boolean isGet() {
                return isGet;
            }

            public void setGet(boolean get) {
                isGet = get;
            }

            public boolean isAdd() {
                return isAdd;
            }

            public void setAdd(boolean add) {
                isAdd = add;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public boolean isShow() {
                return isShow;
            }

            public void setShow(boolean isShow) {
                this.isShow = isShow;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getRealtype() {
                return realtype;
            }

            public void setRealtype(String realtype) {
                this.realtype = realtype;
            }
        }
    }
}
