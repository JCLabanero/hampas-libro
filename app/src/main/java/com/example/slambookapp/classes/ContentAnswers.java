package com.example.slambookapp.classes;

public class ContentAnswers {
    private int image;
    private String answer;
    private String name;

    public ContentAnswers(Integer image, String answer, String name){
        this.image = image;
        this.answer = answer;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
