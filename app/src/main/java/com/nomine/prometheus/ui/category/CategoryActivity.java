package com.nomine.prometheus.ui.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nomine.prometheus.R;
import com.nomine.prometheus.model.ArticleModel;
import com.nomine.prometheus.model.BaseModel;
import com.nomine.prometheus.model.MusicData;
import com.nomine.prometheus.net.PrometheusAPIs;
import com.nomine.prometheus.net.RetrofitManager;
import com.nomine.prometheus.utils.HtmlUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import us.feras.mdv.MarkdownView;

public class CategoryActivity extends AppCompatActivity {

    MarkdownView markdownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        markdownView = (MarkdownView) findViewById(R.id.markdownView);
    }
}
