package com.nomine.prometheus.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nomine.prometheus.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by E Nomine on 2016/3/13.
 */
public class ArticleViewHolder extends BaseViewHolder {

    @Bind(R.id.article_title)
    public TextView title;
    @Bind(R.id.article_subtxt)
    public TextView subtxt;
    @Bind(R.id.article_img)
    public ImageView img;
    @Bind(R.id.article_click)
    public LinearLayout click;
    public ArticleViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
