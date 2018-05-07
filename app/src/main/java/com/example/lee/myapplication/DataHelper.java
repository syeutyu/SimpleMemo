package com.example.lee.myapplication;

import java.util.Map;

import io.realm.Realm;

public class DataHelper {

    private Map<String,String> map;
    public void create(Realm realm){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Data data = realm.createObject(Data.class);
                data.setTitle("this is title");
                data.setBody("this is body");
            }
        });
    }

    public Map<String,String> get(Realm realm){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Data data = realm.where(Data.class).findFirst();
                map.put(data.getTitle(),data.getBody());
            }
        });
        return map;
    }

}
