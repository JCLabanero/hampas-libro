package com.example.slambookapp.classes;

public class ContentAnswers {
    private int image;
    private String text1;
    private String text2;

    public ContentAnswers(Integer image, String answer, String name){
        this.image = image;
        this.text1 = answer;
        this.text2 = name;
    }

    public ContentAnswers(Integer image, String question){
        this.image = image;
        this.text1 = question;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
