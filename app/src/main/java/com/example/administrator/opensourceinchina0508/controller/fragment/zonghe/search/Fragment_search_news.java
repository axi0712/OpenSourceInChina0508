package com.example.administrator.opensourceinchina0508.controller.fragment.zonghe.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;
import com.example.administrator.opensourceinchina0508.model.bean.Search_SoftWare;
import com.example.administrator.opensourceinchina0508.model.http.http.MyCallBack;
import com.example.administrator.opensourceinchina0508.model.http.http.Parsing;
import com.example.administrator.opensourceinchina0508.model.http.http.ParsingImple;
import com.example.administrator.opensourceinchina0508.model.util.UtilsData;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 * 作者 Administrator
 * 日期 2017/5/11 0011
 * 内容${CONTENT}
 * /**
 * /**
 * <p>
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


public class Fragment_search_news extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private List<Search_SoftWare.ResultBean> mList = new ArrayList<>();
    private Parsing par = new ParsingImple();
    private SharedPreferences mShare;
    @Override
    protected int layoutId() {
        return R.layout.fragment_search_news;
    }

    @Override
    protected void initView(View view) {
       mView = (PullToRefreshRecyclerView) view.findViewById(R.id.fragment_search_news_pull);
        mShare = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    @Override
    protected void initData() {
        LinearLayoutManager li = new LinearLayoutManager(getContext());
        getRetro();
        mView.setLayoutManager(li);
    }

    private void getRetro() {
        Map<String,String> map = new HashMap<>();
        map.put("catalog","news");
        map.put("content",mShare.getString("name",""));
        map.put("pageIndex","1");
        map.put("pageSize","20");
        par.gets(UtilsData.URL + "action/api/search_list", map, new MyCallBack() {
            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina",Search_SoftWare.class);
                xs.alias("result",Search_SoftWare.ResultBean.class);
                Search_SoftWare soft = (Search_SoftWare) xs.fromXML(strSuccess);
                mList.addAll(soft.getResults());
                mView.setAdapter(new MyAdapter(getContext(),mList));
            }

            @Override
            public void onError(String strError) {

            }
        });
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
    class MyAdapter extends BaseAdapter<Search_SoftWare.ResultBean> {

        public MyAdapter(Context context,  List<Search_SoftWare.ResultBean> datas) {
            super(context, R.layout.fragment_search_software_item, datas);
        }

        @Override
        public void convert(ViewHolder holder, Search_SoftWare.ResultBean resultBean) {
            holder.setText(R.id.fragment_search_software_item_title,resultBean.getTitle());
            holder.setText(R.id.fragment_search_software_item_body,resultBean.getDescription());
        }
    }
}
