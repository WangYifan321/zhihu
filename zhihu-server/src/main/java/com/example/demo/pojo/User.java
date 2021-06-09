package com.example.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int id;
    private String content;
    private int state;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", state=" + state +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User(int id, String content, int state) {
        this.id = id;
        this.content = content;
        this.state = state;
    }

    public User() {

    }
}
