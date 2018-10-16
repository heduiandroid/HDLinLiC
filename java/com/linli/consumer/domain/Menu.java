package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/7/10.
 * Summary:
 */
public class Menu {
    private int id;

    private String name;

    private int cuisine;

    private String maining;

    private String accessories;

    private String imgpath;

    private String videopath;

    private String difficulty;

    private String maketime;

    private int dishes;

    private int flavor;

    private int cookway;

    private String regional;

    private String ambient;

    private String age;

    private String dinnertime;

    private String physique;

    private String createtime;

    private String updatetime;

    private int deleted;

    private String makemethod;
    public String getMakemethod() {return makemethod;}
    public void setMakemethod(String makemethod) {this.makemethod = makemethod;}
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCuisine(int cuisine){
        this.cuisine = cuisine;
    }
    public int getCuisine(){
        return this.cuisine;
    }
    public void setMaining(String maining){
        this.maining = maining;
    }
    public String getMaining(){
        return this.maining;
    }
    public void setAccessories(String accessories){
        this.accessories = accessories;
    }
    public String getAccessories(){
        return this.accessories;
    }
    public void setImgpath(String imgpath){
        this.imgpath = imgpath;
    }
    public String getImgpath(){
        return this.imgpath;
    }
    public void setVideopath(String videopath){
        this.videopath = videopath;
    }
    public String getVideopath(){
        return this.videopath;
    }
    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }
    public String getDifficulty(){
        return this.difficulty;
    }
    public void setMaketime(String maketime){
        this.maketime = maketime;
    }
    public String getMaketime(){
        return this.maketime;
    }
    public void setDishes(int dishes){
        this.dishes = dishes;
    }
    public int getDishes(){
        return this.dishes;
    }
    public void setFlavor(int flavor){
        this.flavor = flavor;
    }
    public int getFlavor(){
        return this.flavor;
    }
    public void setCookway(int cookway){
        this.cookway = cookway;
    }
    public int getCookway(){
        return this.cookway;
    }
    public void setRegional(String regional){
        this.regional = regional;
    }
    public String getRegional(){
        return this.regional;
    }
    public void setAmbient(String ambient){
        this.ambient = ambient;
    }
    public String getAmbient(){
        return this.ambient;
    }
    public void setAge(String age){
        this.age = age;
    }
    public String getAge(){
        return this.age;
    }
    public void setDinnertime(String dinnertime){
        this.dinnertime = dinnertime;
    }
    public String getDinnertime(){
        return this.dinnertime;
    }
    public void setPhysique(String physique){
        this.physique = physique;
    }
    public String getPhysique(){
        return this.physique;
    }
    public void setCreatetime(String createtime){
        this.createtime = createtime;
    }
    public String getCreatetime(){
        return this.createtime;
    }
    public void setUpdatetime(String updatetime){
        this.updatetime = updatetime;
    }
    public String getUpdatetime(){
        return this.updatetime;
    }
    public void setDeleted(int deleted){
        this.deleted = deleted;
    }
    public int getDeleted(){
        return this.deleted;
    }

}
