package com.linli.consumer.bean;

import java.util.List;

/**
 * Created by hasee on 2017/2/20.
 */

public class FeedBackGoods {

    /**
     * status : 1
     * data : [{"hdMallStoreVoiceCart":{"id":614924755832517,"storeId":614919847457105,"storeName":"测试商城","storeLogo":"http://obqlfysk2.bkt.clouddn.com/FrTIkmb1KOIWLMpebzBZRgfxdeJ2","memId":614923999587342,"pubvoiceId":614924223948817,"goodsId":614920441895351,"goodsSpecId":614924193482217,"name":"怡宝饮用纯净水，555mlx12瓶/件，量贩装","price":9999999,"num":1,"proimg":"http://obqlfysk2.bkt.clouddn.com/614907817855162.jpg","shopcartygory":1,"pattern":1,"createtime":1492475583000,"deleted":false},"specId":614924193482217},{"hdMallStoreVoiceCart":{"id":614924759624750,"storeId":614919847457105,"storeName":"测试商城","storeLogo":"http://obqlfysk2.bkt.clouddn.com/FrTIkmb1KOIWLMpebzBZRgfxdeJ2","memId":614923999587342,"pubvoiceId":614924223948817,"goodsId":614919876715520,"goodsSpecId":614919882529529,"name":"上衣","price":0.01,"num":1,"proimg":"http://obqlfysk2.bkt.clouddn.com/FskcE2VcQfA3hQLx_EDCwHjeVasH","shopcartygory":1,"pattern":1,"createtime":1492475962000,"deleted":false},"specId":614919882529529}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * hdMallStoreVoiceCart : {"id":614924755832517,"storeId":614919847457105,"storeName":"测试商城","storeLogo":"http://obqlfysk2.bkt.clouddn.com/FrTIkmb1KOIWLMpebzBZRgfxdeJ2","memId":614923999587342,"pubvoiceId":614924223948817,"goodsId":614920441895351,"goodsSpecId":614924193482217,"name":"怡宝饮用纯净水，555mlx12瓶/件，量贩装","price":9999999,"num":1,"proimg":"http://obqlfysk2.bkt.clouddn.com/614907817855162.jpg","shopcartygory":1,"pattern":1,"createtime":1492475583000,"deleted":false}
         * specId : 614924193482217
         */

        private HdMallStoreVoiceCartBean hdMallStoreVoiceCart;
        private long specId;
        private int isPackageCost;      //美食专用字段，判断是否有打包费 0 : 没有     1 : 需要打包费

        public int getIsPackageCost() {
            return isPackageCost;
        }

        public void setIsPackageCost(int isPackageCost) {
            this.isPackageCost = isPackageCost;
        }

        public HdMallStoreVoiceCartBean getHdMallStoreVoiceCart() {
            return hdMallStoreVoiceCart;
        }

        public void setHdMallStoreVoiceCart(HdMallStoreVoiceCartBean hdMallStoreVoiceCart) {
            this.hdMallStoreVoiceCart = hdMallStoreVoiceCart;
        }

        public long getSpecId() {
            return specId;
        }

        public void setSpecId(long specId) {
            this.specId = specId;
        }

        public static class HdMallStoreVoiceCartBean {
            /**
             * id : 614924755832517
             * storeId : 614919847457105
             * storeName : 测试商城
             * storeLogo : http://obqlfysk2.bkt.clouddn.com/FrTIkmb1KOIWLMpebzBZRgfxdeJ2
             * memId : 614923999587342
             * pubvoiceId : 614924223948817
             * goodsId : 614920441895351
             * goodsSpecId : 614924193482217
             * name : 怡宝饮用纯净水，555mlx12瓶/件，量贩装
             * price : 9999999.0
             * num : 1
             * proimg : http://obqlfysk2.bkt.clouddn.com/614907817855162.jpg
             * shopcartygory : 1
             * pattern : 1
             * createtime : 1492475583000
             * deleted : false
             */

            private long id;
            private long storeId;
            private String storeName;
            private String storeLogo;
            private long memId;
            private long pubvoiceId;
            private long goodsId;
            private long goodsSpecId;
            private String name;
            private double price;
            private int num;
            private String proimg;
            private int shopcartygory;
            private int pattern;
            private long createtime;
            private boolean deleted;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getStoreId() {
                return storeId;
            }

            public void setStoreId(long storeId) {
                this.storeId = storeId;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStoreLogo() {
                return storeLogo;
            }

            public void setStoreLogo(String storeLogo) {
                this.storeLogo = storeLogo;
            }

            public long getMemId() {
                return memId;
            }

            public void setMemId(long memId) {
                this.memId = memId;
            }

            public long getPubvoiceId() {
                return pubvoiceId;
            }

            public void setPubvoiceId(long pubvoiceId) {
                this.pubvoiceId = pubvoiceId;
            }

            public long getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(long goodsId) {
                this.goodsId = goodsId;
            }

            public long getGoodsSpecId() {
                return goodsSpecId;
            }

            public void setGoodsSpecId(long goodsSpecId) {
                this.goodsSpecId = goodsSpecId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getProimg() {
                return proimg;
            }

            public void setProimg(String proimg) {
                this.proimg = proimg;
            }

            public int getShopcartygory() {
                return shopcartygory;
            }

            public void setShopcartygory(int shopcartygory) {
                this.shopcartygory = shopcartygory;
            }

            public int getPattern() {
                return pattern;
            }

            public void setPattern(int pattern) {
                this.pattern = pattern;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }
        }
    }
}
