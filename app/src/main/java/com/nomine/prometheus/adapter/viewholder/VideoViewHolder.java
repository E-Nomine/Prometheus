package com.nomine.prometheus.adapter.viewholder;

import android.view.View;

import com.nomine.prometheus.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by E Nomine on 2016/3/13.
 */
public class VideoViewHolder extends BaseViewHolder {

    @Bind(R.id.video_img)
    public JCVideoPlayer img;

    public VideoViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}