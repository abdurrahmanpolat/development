package com.example.abdur.probisapplication.activity.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.abdur.probisapplication.R;
import com.example.abdur.probisapplication.adapter.ProductListAdapter;
import com.example.abdur.probisapplication.resClient.RetrofitApiClient;
import com.example.abdur.probisapplication.helper.listener.Communicator;
import com.example.abdur.probisapplication.helper.listener.RetrofitApiInterface;
import com.example.abdur.probisapplication.model.Category;
import com.example.abdur.probisapplication.model.Product;
import com.example.abdur.probisapplication.model.ProductResult;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryProductFragment extends Fragment {

    private static final String KEY_TITLE = "CONTENT";
    private static final String KEY_ID = "ID";

    private ArrayList<Product> productModelItems = new ArrayList<>();

    private String category_id;
    private GridView gridLayout;
    Communicator communicator;
    private MKLoader loadin_bar;

    public CategoryProductFragment() {
        // Required empty public constructor
    }

    public static CategoryProductFragment newInstance(Category category) {
        CategoryProductFragment fragment = new CategoryProductFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, category.getName());
        args.putString(KEY_ID, category.getId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadin_bar = (MKLoader) view.findViewById(R.id.loadin_bar);
        gridLayout = (GridView) view.findViewById(R.id.mainGrid);
        category_id = getArguments().getString(KEY_ID);

        communicator = (Communicator) getActivity();

        String title = getArguments().getString(KEY_TITLE);

        TextView producCategoryName = (TextView) view.findViewById(R.id.textGrid);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/LondiniaMedium.ttf");
        producCategoryName.setTypeface(font);
        producCategoryName.setText(title);

        loadin_bar.setVisibility(View.VISIBLE);
        getProduct(category_id);
    }


    private void getProduct(String map) {

        RetrofitApiInterface apiInterface = RetrofitApiClient.getApiClient().create(RetrofitApiInterface.class);
        Call<ProductResult> call = apiInterface.getProduct(category_id);
        call.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {
                if (response.body().getResult_message().getCode().equals("0000")) {
                    productModelItems = response.body().getResult();
                    ProductListAdapter productListAdapter = new ProductListAdapter(getContext(), productModelItems);
                    gridLayout.setAdapter(productListAdapter);

                    gridLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //productModelItems.get(position).getName();
                            communicator.respond(productModelItems.get(position), category_id, productModelItems.get(position).getMedia_thumb());
                            //Toast.makeText(getContext(), "Name: " + productModelItems.get(position).getName(), Toast.LENGTH_SHORT).show();
                        }
                    });
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
}