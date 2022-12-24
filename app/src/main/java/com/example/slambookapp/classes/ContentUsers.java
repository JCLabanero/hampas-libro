package com.example.slambookapp.classes;

public class ContentUsers {
    private int image;
    private String username;
    private int id;
    private String email;

    public ContentUsers(int image, String username, int id, String email) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
