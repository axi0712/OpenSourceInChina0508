package com.example.administrator.opensourceinchina0508.controller.fragment.tweet;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;
import com.example.administrator.opensourceinchina0508.model.bean.Tweet_HotBean;
import com.example.administrator.opensourceinchina0508.model.http.http.MyCallBack;
import com.example.administrator.opensourceinchina0508.model.http.http.Parsing;
import com.example.administrator.opensourceinchina0508.model.http.http.ParsingImple;
import com.example.administrator.opensourceinchina0508.model.util.MyContentLinearLayoutManager;
import com.example.administrator.opensourceinchina0508.model.util.UtilsData;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class Fragment_Tweet_new extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private List<Tweet_HotBean.TweetBean> mList = new ArrayList<>();
    private Parsing par = new ParsingImple();
    private int pageIndex = 1;
    @Override
    protected int layoutId() {
        return R.layout.fragment_tweet_new;
    }

    @Override
    protected void initView(View view) {
        mView = (PullToRefreshRecyclerView) view.findViewById(R.id.fragment_tweet_new_pull);
    }

    @Override
    protected void initData() {
        LinearLayoutManager linea = new LinearLayoutManager(getContext());
        getRe();
        mView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),DividerItemDecoration.VERTICAL));
        mView.setLayoutManager(new MyContentLinearLayoutManager(mView.getContext()));
        mView.setPullRefreshEnabled(true);//下拉刷新
        mView.setLoadingMoreEnabled(true);//上拉加载
        mView.displayLastRefreshTime(true);//显示上次刷新的时间
        //设置刷新回调
        mView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                mView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex = 0;
                        mList.clear();
//                        for (int i = 1; i <= pageIndex; i++) {
                        getRe();
//                        }
                        mView.setRefreshComplete();

//                        mEditor.putInt("Index", pageIndex);
//                        mEditor.commit();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                mView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex++;
                        getRe();
                        mView.setLoadMoreComplete();
//                        mEditor.putInt("Index", pageIndex);
//                        Log.i("加载", pageIndex + "");
//                        mEditor.commit();
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
    private void getRe() {
        Map<String,String> map = new HashMap<>();
        map.put("uid","0");
        map.put("pageIndex","0");
        map.put("pageSize","20");
        par.gets(UtilsData.URL + "action/api/tweet_list", map, new MyCallBack() {
            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina",Tweet_HotBean.class);
                xs.alias("tweet",Tweet_HotBean.TweetBean.class);
                xs.alias("user",Tweet_HotBean.TweetBean.UserBean.class);
                Tweet_HotBean bean = (Tweet_HotBean) xs.fromXML(strSuccess);
                Log.d("Fragment_Tweet_Hot", "bean.getTweets():" + bean.getTweets());
                mList.addAll(bean.getTweets());
                mView.setAdapter(new MyAdapter(getContext(),mList));
            }

            @Override
            public void onError(String strError) {

            }
        });

    }
    class MyAdapter extends BaseAdapter<Tweet_HotBean.TweetBean> {

        public MyAdapter(Context context, List<Tweet_HotBean.TweetBean> datas) {
            super(context, R.layout.fragment_tweet_hot_item, datas);
        }

        @Override
        public void convert(ViewHolder holder, Tweet_HotBean.TweetBean tweetBean) {
            final ImageView imageView = holder.getView(R.id.fragment_tweet_hot_item_image_touxiang);

            Glide.with(getContext()).load(tweetBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    ciDrawable.setCircular(true);
                    imageView.setImageDrawable(ciDrawable);
                }
            });

            final ImageView img = holder.getView(R.id.fragment_tweet_hot_item_image_big);
            Glide.with(getContext()).load(tweetBean.getImgSmall()).diskCacheStrategy(DiskCacheStrategy.ALL).into(img);
            holder.setText(R.id.fragment_tweet_hot_item_title,tweetBean.getAuthor());
            holder.setText(R.id.fragment_tweet_hot_item_body,tweetBean.getBody());
            holder.setText(R.id.fragment_tweet_hot_item_time,tweetBean.getPubDate());

            holder.setText(R.id.fragment_tweet_hot_item_zan,tweetBean.getLikeCount());

        }
    }
}
