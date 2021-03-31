package com.example.iicapp.Models;

public class PeopleModel {


    PeopleModel(){


    }

    String name,imageurl,id,designation,email;

    public PeopleModel(String name, String imageurl, String id, String designation, String email) {
        this.name = name;
        this.imageurl = imageurl;
        this.id = id;
        this.designation = designation;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
