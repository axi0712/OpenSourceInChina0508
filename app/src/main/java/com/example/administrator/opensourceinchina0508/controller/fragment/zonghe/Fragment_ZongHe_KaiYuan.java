package com.example.administrator.opensourceinchina0508.controller.fragment.zonghe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.administrator.opensourceinchina0508.App;
import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.controller.activity.Activity_ZongHe_KaiYuan_Detail;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;
import com.example.administrator.opensourceinchina0508.model.bean.ZongHe_KaiYuanBean;
import com.example.administrator.opensourceinchina0508.model.http.http.MyCallBack;
import com.example.administrator.opensourceinchina0508.model.http.http.Parsing;
import com.example.administrator.opensourceinchina0508.model.http.http.ParsingImple;
import com.example.administrator.opensourceinchina0508.model.util.Dates;
import com.example.administrator.opensourceinchina0508.model.util.UtilsData;
import com.jude.rollviewpager.RollPagerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class Fragment_ZongHe_KaiYuan extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private Parsing parsing = new ParsingImple();
    private List<ZongHe_KaiYuanBean.NewsBean> mList = new ArrayList<>();
    private ViewPager mPager;
    private int pageIndex = 1;
    private List<View> list = new ArrayList<>();
    private RollPagerView mRoll;
    private final int CODE_START = 0;
    private final int CODE_STOP = 1;
    private int currentItem = 10000000;
    private RadioButton mOne, mTwo, mThree;
    private Handler mHand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE_START:
                    mPager.setCurrentItem(currentItem++);
                    mHand.sendEmptyMessageDelayed(CODE_START, 1000);

                    break;
                case CODE_STOP:
                    mHand.removeMessages(CODE_START);
                    break;
            }
        }
    };
    private View v;

    @Override
    protected int layoutId() {
        return R.layout.fragment_zonghe_kaiyuan;
    }

    @Override
    protected void initView(View view) {
        v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_viewpage, null);
        mPager = (ViewPager) v.findViewById(R.id.fragment_viewpager);
        mView = (PullToRefreshRecyclerView) view.findViewById(R.id.fragment_zonghe_kaiyuan_pull);
        mOne = (RadioButton) v.findViewById(R.id.fragment_viewpager_circle_one);
        mTwo = (RadioButton) v.findViewById(R.id.fragment_viewpager_circle_two);
        mThree = (RadioButton) v.findViewById(R.id.fragment_viewpager_circle_three);
    }

    @Override
    protected void initData() {
        LinearLayoutManager linear = new LinearLayoutManager(App.base);


        mView.addHeaderView(v);
//        mView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mView.setLayoutManager(linear);
//        mView.setLayoutManager(new MyContentLinearLayoutManager(mView.getContext()));
//        mView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

//        mView.setPullRefreshEnabled(true);//下拉刷新
//        mView.setLoadingMoreEnabled(true);//上拉加载
//        mView.displayLastRefreshTime(true);//显示上次刷新的时间
//        //设置刷新回调
//        mView.setPullToRefreshListener(new PullToRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        pageIndex = 0;
//                        mList.clear();
////                        for (int i = 1; i <= pageIndex; i++) {
//                        getRetrofit();
////                        }
//                        mView.setRefreshComplete();
//
////                        mEditor.putInt("Index", pageIndex);
////                        mEditor.commit();
//                    }
//                }, 2000);
//            }
//
//            @Override
//            public void onLoadMore() {
//                mView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        pageIndex++;
//                        getRetrofit();
//                        mView.setLoadMoreComplete();
////                        mEditor.putInt("Index", pageIndex);
////                        Log.i("加载", pageIndex + "");
////                        mEditor.commit();
//                    }
//                }, 2000);
//            }
//        });
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position % list.size() == 0) {
                    mOne.setChecked(true);
                } else if (position % list.size() == 1) {
                    mTwo.setChecked(true);
                } else {
                    mThree.setChecked(true);
                }
            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        lunbo();
    }

    private void lunbo() {
        View v = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.news_opensources_item1, null);
        View v1 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.news_opensources_item2, null);
        View v2 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.news_opensources_item3, null);
        list.add(v);
        list.add(v1);
        list.add(v2);

        mPager.setAdapter(new MyPagerAdapter());
        mPager.setCurrentItem(currentItem++);
        mHand.sendEmptyMessageDelayed(CODE_START, 1000);

    }

    @Override
    public void onResume() {
        super.onResume();
        getRetrofit();
    }

    private void getRetrofit() {
        Map<String, String> map = new HashMap<>();
        map.put("catalog", "1");
        map.put("pageIndex", "1");
        map.put("pageSize", "20");
        parsing.gets(UtilsData.URL + "action/api/news_list", map, new MyCallBack() {
            @Override
            public void onSuccess(String strSuccess) {
                Log.d("MainActivitysss", strSuccess);
                XStream xs = new XStream();
                xs.alias("oschina", ZongHe_KaiYuanBean.class);
                xs.alias("news", ZongHe_KaiYuanBean.NewsBean.class);
                ZongHe_KaiYuanBean news = (ZongHe_KaiYuanBean) xs.fromXML(strSuccess);
                Log.d("MainActivityasas", news.getNewslist().toString());
                mList.addAll(news.getNewslist());
                mView.setAdapter(new MyAdapter(getContext(), mList));
                mView.addItemDecoration(new DividerItemDecoration(App.base,
                        DividerItemDecoration.VERTICAL));
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

    class MyAdapter extends BaseAdapter<ZongHe_KaiYuanBean.NewsBean> {


        public MyAdapter(Context context, List<ZongHe_KaiYuanBean.NewsBean> datas) {
            super(context, R.layout.fragment_zonghe_kaiyuan_item, datas);
        }

        @Override
        public void convert(ViewHolder holder, final ZongHe_KaiYuanBean.NewsBean newstypeBean) {
            holder.setText(R.id.fragment_zonghe_kaiyuan_item_text_title, newstypeBean.getTitle());
            holder.setText(R.id.fragment_zonghe_kaiyuan_item_text_message, newstypeBean.getBody());
            holder.setText(R.id.fragment_zonghe_kaiyuan_item_text_author, newstypeBean.getAuthor());
            holder.setText(R.id.fragment_zonghe_kaiyuan_item_count_pinlun, newstypeBean.getCommentCount());
            String time = Dates.getDate(newstypeBean.getPubDate());
            holder.setText(R.id.fragment_zonghe_kaiyuan_item_text_time, time);
            holder.setOnclickListener(R.id.fragment_zonghe_kaiyuan_item_zong, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getContext(), Activity_ZongHe_KaiYuan_Detail.class);
                    in.putExtra("commentCount", newstypeBean.getCommentCount());
                    in.putExtra("id", newstypeBean.getId());
                    startActivity(in);
                }
            });
        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (container != null) {
                container.removeView(list.get(position % list.size()));
            }
            container.addView(list.get(position % list.size()));
            return list.get(position % list.size());
        }
    }
}
