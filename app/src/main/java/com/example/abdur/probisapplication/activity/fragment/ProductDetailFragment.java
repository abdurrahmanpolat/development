package com.example.abdur.probisapplication.activity.fragment;


import android.graphics.Typeface;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdur.probisapplication.R;
import com.example.abdur.probisapplication.adapter.ProductDetailsAtapter;
import com.example.abdur.probisapplication.adapter.ProductDetailsListAdapter;
import com.example.abdur.probisapplication.resClient.RetrofitApiClient;
import com.example.abdur.probisapplication.helper.listener.RetrofitApiInterface;
import com.example.abdur.probisapplication.model.Product;
import com.example.abdur.probisapplication.model.ProductDetailResult;
import com.example.abdur.probisapplication.model.ProductItemDetails;
import com.example.abdur.probisapplication.model.ProductResult;
import com.tuyenmonkey.mkloader.MKLoader;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment {

    private ProductDetailsAtapter productDetailsAtapter;
    private ViewPager mProductViewPager;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;

    private static final String KEY_CATEGORY_ID = "CATEGORY ID";
    private static final String KEY_PRODUCT_NAME_TITLE = "PRODUCT NAME";
    private static final String KEY_SELECT_PRODUCT_ID = "SELECT PRODUCT ID";
    private static final String KEY_SELECT_PRODUCT_IMAGE_URL = "SELECT PRODUCT IMAGE";

    private String getKeyCategoryId;
    private String getKeySelectProductId;
    private String getKeyProductNameTitle;
    private String getKeySelectProductImageUrl;


    private ProductItemDetails productModelItems;
    private ArrayList<Product> productModel = new ArrayList<>();

    private MKLoader loadin_bar;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(Product product, String category_Id, String imageURL) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(KEY_CATEGORY_ID, category_Id);
        args.putString(KEY_PRODUCT_NAME_TITLE, product.getName());
        args.putString(KEY_SELECT_PRODUCT_ID, product.getId());
        args.putString(KEY_SELECT_PRODUCT_IMAGE_URL, imageURL);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getKeyCategoryId = getArguments().getString(KEY_CATEGORY_ID);
            getKeySelectProductId = getArguments().getString(KEY_SELECT_PRODUCT_ID);
            getKeyProductNameTitle = getArguments().getString(KEY_PRODUCT_NAME_TITLE);
            getKeySelectProductImageUrl = getArguments().getString(KEY_SELECT_PRODUCT_IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadin_bar = (MKLoader) view.findViewById(R.id.loadin_bar);
        TextView textView = (TextView) view.findViewById(R.id.textProductName);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/LondiniaMedium.ttf");
        textView.setTypeface(font);
        textView.setText(getKeyProductNameTitle);

        mProductViewPager = (ViewPager) view.findViewById(R.id.detailViewPager);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewHorizontal);
        recyclerView.setLayoutManager(layoutManager);

        setView();
        loadin_bar.setVisibility(View.VISIBLE);

        getProductsHorizontal();
    }

    private void getProductsHorizontal() {

        RetrofitApiInterface apiInterface = RetrofitApiClient.getApiClient().create(RetrofitApiInterface.class);
        Call<ProductResult> call = apiInterface.getProduct(getKeyCategoryId);
        call.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {
                if (response.body().getResult_message().getCode().equals("0000")) {

                    for (Product item : response.body().getResult()) {
                        if (!(item.getId().equals(getKeySelectProductId))) {
                            productModel.add(item);
                        }
                    }

                    ProductDetailsListAdapter adapter = new ProductDetailsListAdapter(getActivity(), getContext(), productModel, getKeyCategoryId);
                    recyclerView.setAdapter(adapter);
                }
                loadin_bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {
                Log.d("sdffn", "dsfbn");
                loadin_bar.setVisibility(View.GONE);

            }
        });

    }


    private void setView() {

        RetrofitApiInterface apiInterface = RetrofitApiClient.getApiClient().create(RetrofitApiInterface.class);
        Call<ProductDetailResult> call = apiInterface.getProductDetails(getKeySelectProductId);
        call.enqueue(new Callback<ProductDetailResult>() {
            @Override
            public void onResponse(Call<ProductDetailResult> call, Response<ProductDetailResult> response) {
                if (response.body().getResult_message().getCode().equals("0000")) {
                    productModelItems = response.body().getResult();
                    productDetailsAtapter = new ProductDetailsAtapter(getContext(), productModelItems, getKeySelectProductImageUrl);
                    mProductViewPager.setAdapter(productDetailsAtapter);
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResult> call, Throwable t) {

            }
        });

    }
}