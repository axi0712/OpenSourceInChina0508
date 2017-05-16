package com.example.administrator.opensourceinchina0508.controller.activity;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.base.BaseActivity;
import com.example.administrator.opensourceinchina0508.model.bean.ZongHe_KaiYuan_DetailBean;
import com.example.administrator.opensourceinchina0508.model.http.http.MyCallBack;
import com.example.administrator.opensourceinchina0508.model.http.http.Parsing;
import com.example.administrator.opensourceinchina0508.model.http.http.ParsingImple;
import com.thoughtworks.xstream.XStream;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class Activity_ZongHe_KaiYuan_Detail extends BaseActivity {
    private WebView mWeb;
    private Parsing pars = new ParsingImple();
    private String url;
    private TextView mCommentCoun;

    @Override
    protected void initID() {
           mWeb = (WebView) findViewById(R.id.fragment_zonghe_kaiyuan_detail_webView);
        mCommentCoun = (TextView) findViewById(R.id.fragment_zonghe_kaiyuan_detail_commentCount);
    }

    private void parsing(String id) {
        Map<String,String> map =  new HashMap<>();
        map.put("id",id);
        pars.gets("http://www.oschina.net/action/api/news_detail", map, new MyCallBack() {
            @Override
            public void onSuccess(String strSuccess) {
                Log.d("Activity_ZongHe_KaiYuan", strSuccess);
                XStream xs = new XStream();
                xs.alias("oschina", ZongHe_KaiYuan_DetailBean.class);
                xs.alias("relative",ZongHe_KaiYuan_DetailBean.NewsBean.RelativeBean.class);
                ZongHe_KaiYuan_DetailBean bean = (ZongHe_KaiYuan_DetailBean) xs.fromXML(strSuccess);

                Log.d("Activity_ZongHe_KaiYuan", "bean.getNews():" + bean.getNews());
                Log.d("Activity_ZongHe_KaiYuan", bean.getNews().getUrl());
                url = bean.getNews().getUrl();
                mWeb.loadUrl(bean.getNews().getUrl());

            }

            @Override
            public void onError(String strError) {

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_zonghe_kaiyuan_detail;
    }

    @Override
    protected void onClick() {

    }

    @Override
    protected void initData() {
        Intent in = getIntent();
        String commentCount = in.getStringExtra("commentCount");
        String id = in.getStringExtra("id");
        mCommentCoun.setText(commentCount);
        parsing(id);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
