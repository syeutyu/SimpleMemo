package com.example.lee.myapplication.Domain;

import java.util.Date;

import io.realm.RealmObject;

public class User extends RealmObject {
    private String user_id;
    private Date create_at;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
