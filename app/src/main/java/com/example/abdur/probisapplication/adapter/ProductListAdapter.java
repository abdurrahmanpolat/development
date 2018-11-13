package com.example.abdur.probisapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.abdur.probisapplication.model.Product;
import com.example.abdur.probisapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ProductListAdapter extends BaseAdapter {

    private LayoutInflater layoutinflater;
    private List<Product> listStorage;
    private Context context;

    public ProductListAdapter(Context context, List<Product> customizedListView) {
        this.context = context;

        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        ViewHolder listViewHolder;
        if (convertView == null) {
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.grid_layout_item, parent, false);
            listViewHolder.textInListView = (TextView) convertView.findViewById(R.id.textView);
            listViewHolder.my_image_view = (SimpleDraweeView) convertView.findViewById(R.id.my_image_view);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }

        listViewHolder.textInListView.setText(listStorage.get(position).getName());


        Uri uri = Uri.parse("http://tekbirpazarlama.com/"+listStorage.get(position).getMedia_thumb());
        listViewHolder.my_image_view.setImageURI(uri);

        return convertView;
    }

    static class ViewHolder {
        TextView textInListView;
        SimpleDraweeView my_image_view;
    }

}