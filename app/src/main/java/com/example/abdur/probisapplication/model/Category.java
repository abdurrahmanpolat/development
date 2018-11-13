package com.example.abdur.probisapplication.model;

public class Category extends RecyclerViewItem {

    protected Category(int level, String id, String parentId, String slug, String name, String description, String lang, String order_no, String created_at, String updated_at) {
        super(level, id, parentId, slug, name, description, lang, order_no, created_at, updated_at);
    }
}