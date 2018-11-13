package com.example.abdur.probisapplication.helper.listener;

import com.example.abdur.probisapplication.model.CategoryResult;
import com.example.abdur.probisapplication.model.ProductDetailResult;
import com.example.abdur.probisapplication.model.ProductResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApiInterface {

    @GET("/api/modx/product_categories")
    Call<CategoryResult> getCategory();

    @GET("/api/product/cat/{id}")
    Call<ProductResult> getProduct(@Path("id") String id);

    @GET("/api/modx/products/{id}")
    Call<ProductDetailResult> getProductDetails(@Path("id") String id);

}
