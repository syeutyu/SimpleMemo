package com.example.lee.myapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.lee.myapplication.Domain.MemoItem;
import com.example.lee.myapplication.databinding.ContentMainBinding;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Main extends AppCompatActivity {

    public static final String TAG = "TAG";
    private Boolean isFabOpen = false;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private Adapter adapter;
    private List<MemoItem> list;
    private DataHelper helper;
    public  Realm realm;
    private ContentMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.content_main);
        Realm.init(this);
        activityInit();
        mainBinding.recyclerView.setHasFixedSize(true);
        mainBinding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mainBinding.recyclerView.setAdapter(adapter);
        mainBinding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimation();
            }
        });

        mainBinding.memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Memo.class);
                setAnimation();
                startActivity(intent);
            }
        });

        mainBinding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                setAnimation();
                startActivity(intent);
            }
        });
    }


    private void activityInit() {
        realm = Realm.getDefaultInstance();
        helper = new DataHelper();
        helper.create(realm);
        list = new ArrayList<>();
        RealmResults<MemoItem> check = realm.where(MemoItem.class).findAll();
        list.addAll(check);
        adapter = new Adapter(list);

        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
    }


    private void setAnimation() {
        if (isFabOpen) {
            mainBinding.plus.startAnimation(rotate_backward);
            mainBinding.memo.startAnimation(fab_close);
            mainBinding.setting.startAnimation(fab_close);
            isFabOpen = false;
            mainBinding.memo.setClickable(isFabOpen);
            mainBinding.setting.setClickable(isFabOpen);
        } else {
            mainBinding.plus.startAnimation(rotate_forward);
            mainBinding.memo.startAnimation(fab_open);
            mainBinding.setting.startAnimation(fab_open);
            isFabOpen = true;
            mainBinding.memo.setClickable(isFabOpen);
            mainBinding.setting.setClickable(isFabOpen);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
