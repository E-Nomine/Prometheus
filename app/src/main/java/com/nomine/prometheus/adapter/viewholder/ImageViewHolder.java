package com.nomine.prometheus.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.nomine.prometheus.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by E Nomine on 2016/3/13.
 */
public class ImageViewHolder extends BaseViewHolder {


    @Bind(R.id.image_img)
    public ImageView img;

    public ImageViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}