package com.linli.consumer.domain;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public class FragementFood {
    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    private int imageid;
    private String name;
    private String price;
    private ImageView image;
    public FragementFood(int imageid,String name,String price){
        this.imageid=imageid;
        this.name=name;
        this.price=price;
    }
    private List<Integer> ImageList;
    private Integer ImageInteger[];

    public Integer[] getImageInteger() {
        return ImageInteger;
    }

    public void setImageInteger(Integer[] imageInteger) {
        ImageInteger = imageInteger;
    }

    public List<Integer> getImageList() {
        return ImageList;
    }

    public void setImageList(List<Integer> imageList) {
        ImageList = imageList;
    }

    public Integer getImage() {
        return imageid;
    }

    public void setImage(Integer image) {
        this.imageid = image;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
