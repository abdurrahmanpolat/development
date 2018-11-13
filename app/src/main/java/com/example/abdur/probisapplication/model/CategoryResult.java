package com.example.abdur.probisapplication.model;

import java.util.List;

public class CategoryResult {

    private List<Category> result;
    private ResultMessage result_message;

    public List<Category> getResult() {
        return result;
    }

    public void setResult(List<Category> result) {
        this.result = result;
    }

    public ResultMessage getResult_message() {
        return result_message;
    }

    public void setResult_message(ResultMessage result_message) {
        this.result_message = result_message;
    }
}
