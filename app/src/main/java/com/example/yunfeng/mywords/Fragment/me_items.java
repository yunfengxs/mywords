package com.example.yunfeng.mywords.Fragment;

/**
 * Created by yunfeng on 2018/4/11.
 */

public class me_items {
    public String names;
    public String namess;
    public int imageid;
    public int imageids;
    public me_items(String name1, String name2, int imageId1, int imageId2) {
        this.names = name1;
        this.namess = name2;
        this.imageid = imageId1;
        this.imageids = imageId2;
    }
    public String getName1(){
        return names;
    }
    public  int getImageid1(){
        return imageid;
    }
    public String getName2(){
        return namess;
    }
    public  int getImageid2(){
        return imageids;
    }
}
