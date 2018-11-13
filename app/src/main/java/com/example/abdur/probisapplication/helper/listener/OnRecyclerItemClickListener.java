package com.example.abdur.probisapplication.helper.listener;

import android.view.View;

import com.example.abdur.probisapplication.model.RecyclerViewItem;

public interface OnRecyclerItemClickListener {
    void onItemClick(View view, RecyclerViewItem item, int position);
}
