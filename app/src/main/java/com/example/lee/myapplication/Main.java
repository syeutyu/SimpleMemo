package com.example.lee.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.*;

import io.realm.Realm;

public class Main extends AppCompatActivity {

    public static final String TAG = "TAG";
    private Boolean isFabOpen = false;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private FloatingActionButton mainFab, memoFab, settingFab;
    private Intent intent;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private GridLayoutManager manager;
    private static List<RecyclerItem> list;
    private Realm realm;
    private DataHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Realm.init(this);
        activityInit();
        for(Entry<String,String> data : helper.get(realm).entrySet()){
            System.out.println(data.getKey()+"  :  "+  data.getValue());
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimation();
            }
        });

        memoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Memo.class);
                setAnimation();
                startActivity(intent);
            }
        });

        settingFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Setting.class);
                setAnimation();
                startActivity(intent);
            }
        });
    }


    private void activityInit() {
        realm = Realm.getDefaultInstance();
        list = new ArrayList<>();
        helper = new DataHelper();
        helper.create(realm);
        insertValue();
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        recyclerView = findViewById(R.id.recyclerView);
        mainFab = findViewById(R.id.plus);
        memoFab = findViewById(R.id.memo);
        settingFab = findViewById(R.id.setting);
        adapter = new Adapter(list);
        manager = new GridLayoutManager(getApplicationContext(),2);
    }


    private void setAnimation() {
        if (isFabOpen) {
            mainFab.startAnimation(rotate_backward);
            memoFab.startAnimation(fab_close);
            settingFab.startAnimation(fab_close);
            isFabOpen = false;
            memoFab.setClickable(isFabOpen);
            settingFab.setClickable(isFabOpen);
        } else {
            mainFab.startAnimation(rotate_forward);
            memoFab.startAnimation(fab_open);
            settingFab.startAnimation(fab_open);
            isFabOpen = true;
            memoFab.setClickable(isFabOpen);
            settingFab.setClickable(isFabOpen);
        }

    }

    private void insertValue() {
        for (int i = 0; i < 6; i++) {
            list.add(new RecyclerItem("title : " + String.valueOf(i), " body : " + String.valueOf(i)));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
