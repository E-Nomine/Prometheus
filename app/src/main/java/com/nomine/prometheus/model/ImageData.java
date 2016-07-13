package com.nomine.prometheus.model;

import java.util.List;

/**
 * Created by E Nomine on 2016/4/1.
 */
public class ImageData {
    private boolean result;
    private String message;
    private List<ImageModel> data;

    public List<ImageModel> getData() {
        return data;
    }

    public void setData(List<ImageModel> data) {
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
