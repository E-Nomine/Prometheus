package com.nomine.prometheus.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.nomine.prometheus.R;
import com.nomine.prometheus.model.ArticleModel;
import com.nomine.prometheus.model.BaseModel;

/**
 * Created by E Nomine on 2016/3/22.
 */
public class ShareUtil {

    public static void shareImage(Context context, Uri uri, String title) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "图片分享");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/*");
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(shareIntent, title));
    }


    public static void shareBaseModel(Context context, BaseModel baseModel) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, baseModel.getDate());
        intent.setType("text/plain");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, ""));
    }

}
