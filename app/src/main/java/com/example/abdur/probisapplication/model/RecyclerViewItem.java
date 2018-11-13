package com.example.abdur.probisapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewItem {

    private List<RecyclerViewItem> children;

    private int level;

    private int position;

    private boolean expanded = false;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public List<RecyclerViewItem> getChildren() {
        if (children == null)
            return children = new ArrayList<>();
        else
            return children;
    }

    public void addChildren(List<RecyclerViewItem> children) {
        this.children = children;
    }

    public boolean hasChildren() {
        if (children != null && children.size() > 0) {
            return true;
        } else {
            return false;
        }

    }
    protected RecyclerViewItem(int level, String id, String parentId, String slug, String name, String description, String lang, String order_no, String created_at, String updated_at) {
        this.level = level;
        this.id = id;
        this.parent_id = parent_id;
        this.slug = slug;
        this.name = name;
        this.description = description;
        this.lang = lang;
        this.order_no = order_no;
        this.created_at = created_at;
        this.updated_at = updated_at;

    }
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("parent_id")
    @Expose
    private String parent_id;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("name")
    @Expose
    private String name;



    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("lang")
    @Expose
    private String lang;

    @SerializedName("order_no")
    @Expose
    private String order_no;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;


    public String getId() {
        return id;
    }

    public String getParentId() {
        return parent_id;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLang() {
        return lang;
    }

    public String getOrderNo() {
        return order_no;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }
}
