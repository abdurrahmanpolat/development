package com.example.abdur.probisapplication.model;

import java.util.ArrayList;

public class ProductResult {

    private ArrayList<Product> result = new ArrayList<>();
    private ResultMessage result_message;

    public ArrayList<Product> getResult() {
        return result;
    }

    public void setResult(ArrayList<Product> result) {
        this.result = result;
    }

    public ResultMessage getResult_message() {
        return result_message;
    }

    public void setResult_message(ResultMessage result_message) {
        this.result_message = result_message;
    }

}
