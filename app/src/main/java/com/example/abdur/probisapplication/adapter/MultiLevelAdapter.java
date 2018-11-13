package com.example.abdur.probisapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.abdur.probisapplication.model.RecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiLevelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<RecyclerViewItem> recyclerViewItemList = new ArrayList<>();

    public MultiLevelAdapter(List<?> recyclerViewItems) {
        if (!(recyclerViewItems.get(0) instanceof RecyclerViewItem)) {
            throw new IllegalArgumentException("Please Add Items Of Class extending RecyclerViewItem");
        }
        this.recyclerViewItemList = (List<RecyclerViewItem>) recyclerViewItems;
    }

    public void setRecyclerViewItemList(List<RecyclerViewItem> recyclerViewItemList) {
        this.recyclerViewItemList = recyclerViewItemList;
    }

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return recyclerViewItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return recyclerViewItemList.get(position).getLevel();
    }

    public List<RecyclerViewItem> getRecyclerViewItemList() {
        return recyclerViewItemList;
    }
}