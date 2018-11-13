package com.example.abdur.probisapplication.model;

public class ProductDetailResult {

    private ProductItemDetails result;
    private ResultMessage result_message;

    public ProductItemDetails getResult() {
        return result;
    }

    public void setResult(ProductItemDetails result) {
        this.result = result;
    }

    public ResultMessage getResult_message() {
        return result_message;
    }

    public void setResult_message(ResultMessage result_message) {
        this.result_message = result_message;
    }

}
