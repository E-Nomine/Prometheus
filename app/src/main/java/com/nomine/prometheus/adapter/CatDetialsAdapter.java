package com.nomine.prometheus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nomine.prometheus.R;
import com.nomine.prometheus.adapter.viewholder.CatDetailsViewHolder;
import com.nomine.prometheus.model.SourceModel;

import java.util.List;

/**
 * Created by E Nomine on 2016/4/4.
 */
public class CatDetialsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<SourceModel> data;

    public CatDetialsAdapter(Context context, List<SourceModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatDetailsViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_source, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CatDetailsViewHolder catDetailsViewHolder = (CatDetailsViewHolder)holder;
        catDetailsViewHolder.title.setText(data.get(position).getSrc());
        catDetailsViewHolder.des.setText(data.get(position).getDes());
        catDetailsViewHolder.number.setText(data.get(position).getUsersCount() + "人已订阅");
        Glide.with(context).load(data.get(position).getLogo()).into(catDetailsViewHolder.logo);
        catDetailsViewHolder.source_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(catDetailsViewHolder.source_subscribe.isSelected()){
                    catDetailsViewHolder.source_subscribe.setSelected(false);
                }else {
                    catDetailsViewHolder.source_subscribe.setSelected(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
