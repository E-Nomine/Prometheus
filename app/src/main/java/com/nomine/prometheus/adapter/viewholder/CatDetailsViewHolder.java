package com.nomine.prometheus.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nomine.prometheus.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by E Nomine on 2016/4/4.
 */
public class CatDetailsViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.source_img)
    public ImageView logo;
    @Bind(R.id.source_title)
    public TextView title;
    @Bind(R.id.source_num)
    public TextView number;
    @Bind(R.id.source_des)
    public TextView des;
    @Bind(R.id.source_subscribe)
    public ImageButton source_subscribe;

    public CatDetailsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}