package com.example.lee.myapplication;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lee.myapplication.Domain.MemoItem;
import com.example.lee.myapplication.databinding.ItemCardBinding;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<MemoItem> list;
    @SuppressLint("StaticFieldLeak")
    static ItemCardBinding cardBinding;

    Adapter(List<MemoItem> list) {
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View v) {
            super(v);
            cardBinding = DataBindingUtil.bind(v);
        }
    }

    public void addData(MemoItem item){
        list.add(item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cardBinding.cardTitle.setText(list.get(position).getTitle());
        cardBinding.cardBody.setText(list.get(position).getBody());
        cardBinding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "카드 내용", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
