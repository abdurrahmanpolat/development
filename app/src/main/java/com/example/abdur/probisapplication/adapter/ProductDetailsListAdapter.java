package com.example.abdur.probisapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abdur.probisapplication.helper.listener.Communicator;
import com.example.abdur.probisapplication.model.Product;
import com.example.abdur.probisapplication.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductDetailsListAdapter extends RecyclerView.Adapter<ProductDetailsListAdapter.ViewHolder> {

    private static final String TAG = "ProductDetailsListAdapt";
    private ArrayList<Product> products = new ArrayList<>();
    private Context context;
    private Communicator communicator;
    private String categoryId;

    public ProductDetailsListAdapter(Activity activity, Context context, ArrayList<Product> products, String categoryId) {
        this.products = products;
        this.context = context;
        this.categoryId = categoryId;
        communicator = (Communicator) activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.products_details_list_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onCreateViewHolder: called.");
        String uri = "http://tekbirpazarlama.com/" + products.get(i).getMedia_thumb();

        Glide.with(context)
                .asBitmap()
                .load(uri)
                .into(viewHolder.productImage);

        viewHolder.productName.setText(products.get(i).getName());

        viewHolder.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on a image" + products.get(i).getName());
                communicator.respond(products.get(i), categoryId, products.get(i).getMedia_thumb());
                //Toast.makeText(context, products.get(i).getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView productImage;
        TextView productName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = (CircleImageView) itemView.findViewById(R.id.image_horizontal);
            productName = (TextView) itemView.findViewById(R.id.product_horizontal_name);

        }
    }

}
