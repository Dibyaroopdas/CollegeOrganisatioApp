package com.example.iicapp.Models;

public class PostModel {

    PostModel(){

    }

    String imageurl,postid,title,description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PostModel(String description) {
        this.description = description;
    }

    public PostModel(String imageurl, String postid, String title) {
        this.imageurl = imageurl;
        this.postid = postid;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }
}
