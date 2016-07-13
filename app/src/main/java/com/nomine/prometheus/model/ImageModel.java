package com.nomine.prometheus.model;

import java.io.Serializable;

/**
 * Created by E Nomine on 2016/4/1.
 */
public class ImageModel extends BaseModel implements Serializable{
    public String src;
    public String logo;
    public String image;

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
}
