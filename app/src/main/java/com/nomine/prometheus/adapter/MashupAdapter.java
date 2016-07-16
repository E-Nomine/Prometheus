package com.nomine.prometheus.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nomine.prometheus.adapter.viewholder.BaseViewHolder;
import com.nomine.prometheus.adapter.viewholder.ImageViewHolder;
import com.nomine.prometheus.adapter.viewholder.MusicViewHolder;
import com.nomine.prometheus.adapter.viewholder.VideoViewHolder;
import com.nomine.prometheus.model.ArticleModel;
import com.nomine.prometheus.model.BaseModel;
import com.nomine.prometheus.model.ImageModel;
import com.nomine.prometheus.model.MusicModel;
import com.nomine.prometheus.R;

import java.util.List;

import com.nomine.prometheus.adapter.viewholder.ArticleViewHolder;
import com.nomine.prometheus.rxbus.MusicEvent;
import com.nomine.prometheus.rxbus.RxBus;
import com.nomine.prometheus.ui.details.ArticleDetailsActivity;
import com.nomine.prometheus.ui.details.ImageDetailsActivity;
import com.nomine.prometheus.utils.HtmlUtil;
import com.nomine.prometheus.utils.ShareUtil;
import com.nomine.prometheus.utils.String2TimeUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by E Nomine on 2016/3/12.
 * 通用适配器，可适配 文章，图片，视频，音乐 4种视图
 */

public class MashupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<BaseModel> data;
    public static final int ITEM_TYPE_ARTICLE = 0;
    public static final int ITEM_TYPE_IMAGE = 1;
    public static final int ITEM_TYPE_MUSIC = 2;
    public static final int ITEM_TYPE_VIDEO = 3;

    public MashupAdapter(Context context, List<BaseModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        int type = data.get(position).getType();
        switch (type) {
            case 0:
                return ITEM_TYPE_ARTICLE;
            case 1:
                return ITEM_TYPE_IMAGE;
            case 2:
                return ITEM_TYPE_MUSIC;
            case 3:
                return ITEM_TYPE_VIDEO;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_ARTICLE:
                return new ArticleViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
                );
            case ITEM_TYPE_IMAGE:
                return new ImageViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
                );
            case ITEM_TYPE_MUSIC:
                return new MusicViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_music, parent, false)
                );
            case ITEM_TYPE_VIDEO:
                return new VideoViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_video, parent, false)
                );
        }
        return new ArticleViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
        final BaseModel baseModel  = data.get(position);
        baseViewHolder.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "commit!", Toast.LENGTH_SHORT).show();
            }
        });
        baseViewHolder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(baseViewHolder.fav.isSelected()){
                    baseViewHolder.fav.setSelected(false);
                    Toast.makeText(context, "取消收藏！", Toast.LENGTH_SHORT).show();
                } else {
                    baseViewHolder.fav.setSelected(true);
                    Toast.makeText(context, "收藏！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        baseViewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(baseViewHolder.like.isSelected()){
                    baseViewHolder.like.setSelected(false);
                } else {
                    baseViewHolder.like.setSelected(true);
                }
            }
        });
        baseViewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "share!", Toast.LENGTH_SHORT).show();
                ShareUtil.shareBaseModel(context, baseModel);
            }
        });
        String time = String2TimeUtil.dateString2GoodExperienceFormat(data.get(position).getDate());
        baseViewHolder.time.setText(time);

        int viewType = getItemViewType(position);
        switch (viewType) {
            case ITEM_TYPE_ARTICLE:
                final ArticleModel articleModel = (ArticleModel) data.get(position);
                final ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
                articleViewHolder.src.setText(articleModel.getSrc());
                articleViewHolder.title.setText(articleModel.getTitle());
                articleViewHolder.subtxt.setText(HtmlUtil.getText(articleModel.getDes(), 100));
                //Glide.with(context).load(HtmlUtil.getImgStr(articleModel.getDes())).into(articleViewHolder.img);
                Glide.with(context).load(articleModel.getLogo()).into(articleViewHolder.logo);
                //String url = HtmlUtil.getImgStr(articleModel.getDes());
                Glide.with(context).load(articleModel.getImage()).into(articleViewHolder.img);
                /*Glide.with(context)
                        .load(HtmlUtil.getImgStr(articleModel.getDes()))
                        .thumbnail(0.1f)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .crossFade()
                        .into(articleViewHolder.img);*/

                articleViewHolder.click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ArticleDetailsActivity.class);
                        intent.putExtra("article",articleModel);
                        context.startActivity(intent);
                    }
                });
                break;
            case ITEM_TYPE_IMAGE:
                final ImageViewHolder imageViewHolder = (ImageViewHolder)holder;
                final ImageModel imageModel = (ImageModel) data.get(position);
                imageViewHolder.src.setText(imageModel.getSrc());
                Glide.with(context).load(imageModel.getLogo()).into(imageViewHolder.logo);
                Glide.with(context)
                        .load(imageModel.getImage())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .crossFade()
                        .into(imageViewHolder.img);
                imageViewHolder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ImageDetailsActivity.class);
                        intent.putExtra("image",imageModel);
                        context.startActivity(intent);
                    }
                });
                break;
            case ITEM_TYPE_MUSIC:
                final MusicModel musicModel = (MusicModel) data.get(position);
                final MusicViewHolder musicViewHolder = (MusicViewHolder) holder;
                musicViewHolder.logo.setImageResource(R.drawable.luoo);
                musicViewHolder.src.setText("落网");
                Glide.with(context).load(musicModel.getVol_imgsrc()).into(musicViewHolder.img);
                Glide.with(context).load(musicModel.getAlbum_imgsrc()).into(musicViewHolder.album);
                musicViewHolder.title.setText(musicModel.getSong_name());
                musicViewHolder.singer.setText(musicModel.getSong_artist());
                musicViewHolder.play.setSelected(false);
                musicViewHolder.play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(musicViewHolder.play.isSelected()){
                            RxBus.getDefault().post(new MusicEvent("PAUSE",musicModel.getSong_url(), musicViewHolder.play));
                            musicViewHolder.play.setSelected(false);
                        } else {
                            RxBus.getDefault().post(new MusicEvent("PLAY",musicModel.getSong_url(), musicViewHolder.play));
                            musicViewHolder.play.setSelected(true);
                        }
                    }
                });
                break;
            case ITEM_TYPE_VIDEO:
                final VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
                videoViewHolder.src.setText("优酷");
                videoViewHolder.logo.setImageResource(R.drawable.logo_4);
                videoViewHolder.img.setUp("http://2449.vod.myqcloud.com/2449_43b6f696980311e59ed467f22794e792.f20.mp4", "节操精选");
                //Glide.with(context).load("http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg")
                //        .into(videoViewHolder.img.ivThumb);
                ImageLoader.getInstance().displayImage("http://p.qpic.cn/videoyun/0/2449_38e65894d9e211e5b0e0a3699ca1d490_1/640",
                        videoViewHolder.img.ivThumb);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    
}
