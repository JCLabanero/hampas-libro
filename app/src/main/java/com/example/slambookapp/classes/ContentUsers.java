package com.example.slambookapp.classes;

public class ContentUsers {
    private int image;
    private String username;
    private String id;
    private String email;

    public ContentUsers(int image, String username, String id, String email) {
        this.image = image;
        this.username = username;
        this.id = id;
        this.email = email;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }
}
