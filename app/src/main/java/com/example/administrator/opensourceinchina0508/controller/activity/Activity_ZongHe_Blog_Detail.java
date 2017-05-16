package com.example.administrator.opensourceinchina0508.controller.activity;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.base.BaseActivity;
import com.example.administrator.opensourceinchina0508.model.bean.ZongHe_Blog_DetailBean;
import com.example.administrator.opensourceinchina0508.model.http.http.MyCallBack;
import com.example.administrator.opensourceinchina0508.model.http.http.Parsing;
import com.example.administrator.opensourceinchina0508.model.http.http.ParsingImple;
import com.example.administrator.opensourceinchina0508.model.util.UtilsData;
import com.thoughtworks.xstream.XStream;

import java.util.HashMap;
import java.util.Map;

/**
 * /**
 * 项目名称: 开源中国
 * 类描述:
 * 创建人: XI
 * 创建时间: 2017/5/14 0014 17:58
 * 修改人:
 * 修改内容:
 * 修改时间:
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */


public class Activity_ZongHe_Blog_Detail extends BaseActivity {
    private WebView mWeb;
    private Parsing pars = new ParsingImple();
    private String url;
    private TextView mCommentCoun;

    @Override
    protected void initID() {
        mWeb = (WebView) findViewById(R.id.fragment_zonghe_blog_detail_webView);
        mCommentCoun = (TextView) findViewById(R.id.fragment_zonghe_blog_detail_commentCount);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_zonghe_blog_detail;
    }

    @Override
    protected void onClick() {

    }

    @Override
    protected void initData() {
        Intent in = getIntent();
        String commentCount = in.getStringExtra("commentCounts");
        String id = in.getStringExtra("ids");
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

    private void parsing(String id) {
        Map<String,String> map =  new HashMap<>();
        map.put("id",id);
        pars.gets(UtilsData.URL+"action/api/blog_detail", map, new MyCallBack() {
            @Override
            public void onSuccess(String strSuccess) {
                Log.d("Activity_ZongHe_KaiYuan", strSuccess);
                XStream xs = new XStream();
                xs.alias("oschina", ZongHe_Blog_DetailBean.class);
                xs.alias("relative",ZongHe_Blog_DetailBean.BlogBean.class);
                ZongHe_Blog_DetailBean bean = (ZongHe_Blog_DetailBean) xs.fromXML(strSuccess);
                Log.d("Activity_ZongHe_Blog_De", bean.getBlog().getUrl());
                url = bean.getBlog().getUrl();
                mWeb.loadUrl(url);

            }

            @Override
            public void onError(String strError) {

            }
        });
    }
}
