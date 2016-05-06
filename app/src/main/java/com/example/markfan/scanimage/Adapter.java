package com.example.markfan.scanimage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by MarkFan on 2016/5/6.
 */
public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private List<ImageItem> list;

    public Adapter(List<ImageItem> imageItems) {
        list = imageItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.grid_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.fillData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return (list == null) ? 0 : list.size();
    }
}
