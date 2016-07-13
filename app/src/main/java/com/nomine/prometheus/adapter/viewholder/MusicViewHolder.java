package com.nomine.prometheus.adapter.viewholder;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nomine.prometheus.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by E Nomine on 2016/3/13.
 */
public class MusicViewHolder extends BaseViewHolder {

    @Bind(R.id.music_img)
    public ImageView img;
    @Bind(R.id.music_album)
    public ImageView album;
    @Bind(R.id.music_title)
    public TextView title;
    @Bind(R.id.music_singer)
    public TextView singer;
    @Bind(R.id.music_play)
    public FloatingActionButton play;

    public MusicViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}