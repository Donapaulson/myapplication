package com.example.hp.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.RecyclerViewHolder>{

    ArrayList<String> name;
    public RecyclerAdapter(ArrayList<String> name){
        this.name = name;
    }
    @NonNull
    @Override
    public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_items,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tv.setText(name.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.user_names);
        }
    }
}
