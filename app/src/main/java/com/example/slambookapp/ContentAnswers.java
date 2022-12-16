package com.example.slambookapp;

public class ContentAnswers extends Content{
    private String answer;
    private String name;

    public ContentAnswers(Integer image, String answer, String name){
        super(image);
        this.answer = answer;
        this.name = name;
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
