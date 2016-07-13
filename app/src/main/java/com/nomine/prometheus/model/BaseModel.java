package com.nomine.prometheus.model;

import java.io.Serializable;

/**
 * Created by E Nomine on 2016/3/18.
 */
public class BaseModel implements Serializable{
    int  type;
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
