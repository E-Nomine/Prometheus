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
 * Created by E Nomine on 2016/3/18.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder{
    @Bind(R.id.head_logo)
    public ImageView logo;
    @Bind(R.id.head_src)
    public TextView src;
    @Bind(R.id.head_time)
    public TextView time;

    @Bind(R.id.bottom_share)
    public ImageButton share;
    @Bind(R.id.bottom_fav)
    public ImageButton fav;
    @Bind(R.id.bottom_like)
    public ImageButton like;
    @Bind(R.id.bottom_commit)
    public ImageButton commit;

    public BaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
