package com.example.socialmediaappfirebaseversion;

public class Users {

    private String id, username, imagelink, status;

    public Users(){}
    public Users(String id, String username, String imagelink, String status){
        this.id = id;
        this.username = username;
        this.imagelink = imagelink;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }
}
