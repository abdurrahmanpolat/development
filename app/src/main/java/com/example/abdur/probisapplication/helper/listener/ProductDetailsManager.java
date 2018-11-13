package com.example.abdur.probisapplication.helper.listener;

import com.example.abdur.probisapplication.model.Product;

public interface ProductDetailsManager {
    void showDetailsFragment(Product product, String category_Id, String imageURL);
}
