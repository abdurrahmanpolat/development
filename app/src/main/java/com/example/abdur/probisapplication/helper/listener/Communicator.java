package com.example.abdur.probisapplication.helper.listener;

import com.example.abdur.probisapplication.model.Product;

public interface Communicator {
   void respond(Product product, String categoryId, String imagePath);
}
