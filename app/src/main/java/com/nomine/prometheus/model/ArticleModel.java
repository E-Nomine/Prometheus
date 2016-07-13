package com.nomine.prometheus.model;

import java.io.Serializable;

/**
 * Created by E Nomine on 2016/3/28.
 */
public class ArticleModel extends BaseModel implements Serializable{
    private String src;
    private String logo;
    private String title;
    private String des;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
