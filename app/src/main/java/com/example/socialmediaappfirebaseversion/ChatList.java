package com.example.socialmediaappfirebaseversion;

public class ChatList {
    private String receiverId;

    public ChatList() {
    }

    public ChatList(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getId() {
        return receiverId;
    }

    public void setId(String receiverId) {
        this.receiverId = receiverId;
    }
}
