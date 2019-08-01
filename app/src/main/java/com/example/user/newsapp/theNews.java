package com.example.user.newsapp;

/**
 * Created by User on 7/30/2019.
 */
public class theNews {



    private int ID;
    private String title;
    private String image;
    private String desc;
    private String details;
    public theNews(int id, String title,String desc,String image,String details)
    {
        this.ID = id;
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.details = details;


    }
    public int getID() {
        return ID;
    }
    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;

    }

}
