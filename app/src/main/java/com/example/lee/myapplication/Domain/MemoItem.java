package com.example.lee.myapplication.Domain;

import java.util.Date;

import io.realm.RealmObject;

public class MemoItem extends RealmObject {
    private String title;
    private String body;
    private Date create_at;
    private long setTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public long getSetTime() {
        return setTime;
    }

    public void setSetTime(long setTime) {
        this.setTime = setTime;
    }
}
