package com.linli.consumer.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomoyo on 2017/3/9.
 */

public class FoodRecipeBean extends ResVo implements Serializable{

    /**
     * id : 55
     * name : 湖南小炒肉
     * cuisine : 2
     * maining : 五花肉500克、尖椒100克
     * accessories : 食盐1茶匙、酱油3克、花生油10克、味精2克、咖喱1小块
     * imgpath : http://obqlfysk2.bkt.clouddn.com/37.湖南小炒肉.png
     * videopath :
     * difficulty : 1
     * maketime : 45分钟
     * dishes : 0
     * flavor : 7
     * cookway : 58
     * createtime : 1450095534000
     * updatetime : 1487754763000
     * deleted : false
     * makemethod : <p>
     1.五花肉切片，咖喱少许<br />
     2.切点辣椒<br />
     3.少放油，放进五花肉炒匀<br />
     4.淋上酱油<br />
     5.放点盐炒至出油<br />
     6.放进咖喱和辣椒，辣椒后放也行<br />
     7.倒进清水<br />
     8.煮十分钟<br />
     9.放点味精<br />
     10.收汁出锅<br />
     11.装盘上桌
     </p>
     <p>
     <br />
     </p>
     * tips : <span style="background-color:#ffffff;color:#666666;">辣椒在出锅时再放更适宜。</span>
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private long id;
        private String name;
        private String cuisine;
        private String maining;
        private String accessories;
        private String imgpath;
        private String videopath;
        private long difficulty;
        private String maketime;
        private String dishes;
        private String flavor;
        private String cookway;
        private long createtime;
        private long updatetime;
        private boolean deleted;
        private String makemethod;
        private String tips;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCuisine() {
            return cuisine;
        }

        public void setCuisine(String cuisine) {
            this.cuisine = cuisine;
        }

        public String getMaining() {
            return maining;
        }

        public void setMaining(String maining) {
            this.maining = maining;
        }

        public String getAccessories() {
            return accessories;
        }

        public void setAccessories(String accessories) {
            this.accessories = accessories;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public String getVideopath() {
            return videopath;
        }

        public void setVideopath(String videopath) {
            this.videopath = videopath;
        }

        public long getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(long difficulty) {
            this.difficulty = difficulty;
        }

        public String getMaketime() {
            return maketime;
        }

        public void setMaketime(String maketime) {
            this.maketime = maketime;
        }

        public String getDishes() {
            return dishes;
        }

        public void setDishes(String dishes) {
            this.dishes = dishes;
        }

        public String getFlavor() {
            return flavor;
        }

        public void setFlavor(String flavor) {
            this.flavor = flavor;
        }

        public String getCookway() {
            return cookway;
        }

        public void setCookway(String cookway) {
            this.cookway = cookway;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public long getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(long updatetime) {
            this.updatetime = updatetime;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public String getMakemethod() {
            return makemethod;
        }

        public void setMakemethod(String makemethod) {
            this.makemethod = makemethod;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }
    }
}
