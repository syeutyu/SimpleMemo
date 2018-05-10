package com.example.lee.myapplication;

import com.example.lee.myapplication.Domain.MemoItem;

import java.util.Map;

import io.realm.Realm;

public class DataHelper {

    private Map<String, String> map;

    public void create(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for(int i=0;i<13;i++){
                    MemoItem data = realm.createObject(MemoItem.class);
                    data.setTitle("this is title");
                    data.setBody("this is body");

                }
            }
        });
    }

    public Map<String, String> get(Realm realm) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MemoItem data = realm.where(MemoItem.class).findFirst();
                map.put(data.getTitle(), data.getBody());
            }
        });
        return map;
    }

}
