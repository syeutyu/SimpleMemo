package com.example.lee.myapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lee.myapplication.databinding.MemoBinding;

import io.realm.Realm;


public class Memo extends AppCompatActivity {
    private MemoBinding memoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memoBinding = DataBindingUtil.setContentView(this,R.layout.memo);

        memoBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Main.realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Data data = new Data();
                            data.setTitle(memoBinding.inputTitle.getText().toString());
                            data.setBody(memoBinding.inputBody.getText().toString());
                        }
                    });
            }
        });

    }
}