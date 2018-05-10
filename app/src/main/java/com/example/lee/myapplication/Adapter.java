package com.example.lee.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lee.myapplication.Domain.MemoItem;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

   private List<MemoItem> list;

    public Adapter(List<MemoItem> list){
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
         TextView title;
         TextView body;
         CardView cardView;

        public ViewHolder(View v){
            super(v);
            title = itemView.findViewById(R.id.cardTitle);
            body = itemView.findViewById(R.id.cardBody);
            cardView = itemView.findViewById(R.id.card);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.body.setText(list.get(position).getBody());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"카드 내용",Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
