package com.nomine.prometheus.ui.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nomine.prometheus.R;
import com.nomine.prometheus.model.ArticleModel;
import com.nomine.prometheus.utils.HtmlUtil;
import com.nomine.prometheus.utils.String2TimeUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import us.feras.mdv.MarkdownView;

public class ArticleDetailsActivity extends AppCompatActivity {
    @Bind(R.id.head_logo)
    ImageView logo;
    @Bind(R.id.head_src)
    TextView src;
    @Bind(R.id.article_title)
    TextView title;
    @Bind(R.id.markdownView)
    MarkdownView markdownView;
    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ArticleModel articleModel= (ArticleModel) getIntent().getSerializableExtra("article");
        setTitle(articleModel.getTitle());
        Glide.with(this).load(articleModel.getLogo()).into(logo);

        String time = String2TimeUtil.dateString2GoodExperienceFormat(articleModel.getDate());
        src.setText(articleModel.getSrc() + "  ·  " + time);
        title.setText(articleModel.getTitle());
        //markdownView.loadMarkdown(HtmlUtil.getHtml(articleModel.getDes()));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8") ;
        webView.loadDataWithBaseURL("", HtmlUtil.getHtml(articleModel.getDes()), "text/html", "utf-8","");
    }
}
