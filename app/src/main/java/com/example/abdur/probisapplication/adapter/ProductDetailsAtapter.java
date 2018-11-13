package com.example.abdur.probisapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.example.abdur.probisapplication.model.ProductItemDetails;
import com.example.abdur.probisapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class ProductDetailsAtapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ProductItemDetails productItemDetails;
    private String ImageURL;


    public ProductDetailsAtapter(Context context, ProductItemDetails productItemDetails, String imageURL) {
        this.context = context;
        this.productItemDetails = productItemDetails;
        this.ImageURL = imageURL;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.product_details_item_layout, container, false);

        SimpleDraweeView productImage = (SimpleDraweeView) view.findViewById(R.id.product_image_view);
        WebView productContent = (WebView)view.findViewById(R.id.product_content);

        Uri uri = Uri.parse("http://tekbirpazarlama.com/" + ImageURL);
        productImage.setImageURI(uri);

        WebSettings settings = productContent.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        productContent.loadDataWithBaseURL(null, productItemDetails.getContent(), "text/html", "utf-8", null);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}