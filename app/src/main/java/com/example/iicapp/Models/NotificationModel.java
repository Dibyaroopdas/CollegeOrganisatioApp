package com.example.iicapp.Models;

public class NotificationModel {

    NotificationModel(){

    }

    String notifImageUrl,title;
    int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int i) {
        this.pos = i;
    }

    public NotificationModel(int pos) {
        this.pos = pos;
    }

    public NotificationModel(String imageUrl, String title) {
        this.notifImageUrl = imageUrl;
        this.title = title;
    }

    public String getNotifImageUrl() {
        return notifImageUrl;
    }

    public void setNotifImageUrl(String imageUrl) {
        this.notifImageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }
}
