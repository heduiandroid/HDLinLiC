package com.linli.consumer.domain;

/**
 * Created by 王征 on 2016/7/9.
 */
//菜品对象
public class Dish {
    public Dish(int id, String name){
        this.id=id;
        this.name=name;
    }
    private int id;

    private String name;

    private String cuisine;

    private String maining;

    private String accessories;

    private String imgpath;

    private String videopath;

    private int difficulty;

    private String maketime;

    private String dishes;

    private String flavor;

    private String cookway;

    private String regional;

    private String ambient;

    private String age;

    private String dinnertime;

    private String physique;

    private int createtime;

    private String updatetime;

    private boolean deleted;

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
    public void setCuisine(String cuisine){
        this.cuisine = cuisine;
    }
    public String getCuisine(){
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
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    public int getDifficulty(){
        return this.difficulty;
    }
    public void setMaketime(String maketime){
        this.maketime = maketime;
    }
    public String getMaketime(){
        return this.maketime;
    }
    public void setDishes(String dishes){
        this.dishes = dishes;
    }
    public String getDishes(){
        return this.dishes;
    }
    public void setFlavor(String flavor){
        this.flavor = flavor;
    }
    public String getFlavor(){
        return this.flavor;
    }
    public void setCookway(String cookway){
        this.cookway = cookway;
    }
    public String getCookway(){
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
    public void setCreatetime(int createtime){
        this.createtime = createtime;
    }
    public int getCreatetime(){
        return this.createtime;
    }
    public void setUpdatetime(String updatetime){
        this.updatetime = updatetime;
    }
    public String getUpdatetime(){
        return this.updatetime;
    }
    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }
    public boolean getDeleted(){
        return this.deleted;
    }
}
