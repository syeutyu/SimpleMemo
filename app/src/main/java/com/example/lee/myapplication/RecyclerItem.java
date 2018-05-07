package com.example.lee.myapplication;


public class RecyclerItem {
    private String title;
    private String body;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


    public RecyclerItem(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
