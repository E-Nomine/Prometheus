package com.nomine.prometheus.model;

import java.util.List;

/**
 * Created by E Nomine on 2016/3/29.
 */
public class ArticleData {
    private boolean result;
    private String message;
    private List<ArticleModel> data;

    public List<ArticleModel> getData() {
        return data;
    }

    public void setData(List<ArticleModel> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
