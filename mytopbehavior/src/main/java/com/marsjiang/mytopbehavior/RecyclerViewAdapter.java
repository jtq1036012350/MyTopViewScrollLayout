package com.marsjiang.mytopbehavior;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jiang on 2017-08-16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Integer> arr;

    public RecyclerViewAdapter(Context context, ArrayList<Integer> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        holder = new MyViewHolder(View.inflate(context, R.layout.item_layout, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).setData(arr.get(position));
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    //推荐布局
    class MyViewHolder extends RecyclerView.ViewHolder {
        private int data;
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item);
        }

        public void setData(int data) {
            this.data = data;
            textView.setText(data + "");
        }
    }
}
