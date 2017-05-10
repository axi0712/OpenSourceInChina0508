package com.example.administrator.opensourceinchina0508.model.fragment.tweet;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.opensourceinchina0508.App;
import com.example.administrator.opensourceinchina0508.MainActivity;
import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.adapter.TabLayoutAdapter;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class Fragment_Tweet extends BaseFragment {
    private TabLayout mTab;
    private ViewPager mPager;
    private List<Fragment> mFraList = new ArrayList<>();
    private List<String> mStrList = new ArrayList<>();

    @Override
    protected int layoutId() {
        return R.layout.fragment_tweet;
    }

    @Override
    protected void initView(View view) {
        mTab = (TabLayout) view.findViewById(R.id.fragment_tweet_tab);
        mPager = (ViewPager) view.findViewById(R.id.fragment_tweet_viewpager);

    }

    @Override
    protected void initData() {
        Fragment_Tweet_Hot hot = new Fragment_Tweet_Hot();
        Fragment_Tweet_new news = new Fragment_Tweet_new();
        mFraList.add(hot);
        mFraList.add(news);
        mStrList.add("最新动弹");
        mStrList.add("热门动弹");
        mTab.setTabMode(TabLayout.MODE_FIXED);
        TabLayoutAdapter mAdapter = new TabLayoutAdapter(getActivity().getSupportFragmentManager(),mFraList,mStrList);
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        mTab.setupWithViewPager(mPager);

    }

    @Override
    protected void updateTitleBar() {
        if (App.base instanceof MainActivity) {
            //显示
            ((MainActivity) App.base).getmMainTextTitle().setVisibility(View.VISIBLE);
            ((MainActivity) App.base).getmMainRelaTitle().setVisibility(View.VISIBLE);

        }
        if (App.base instanceof MainActivity) {
            ((MainActivity) App.base).getmMainTextTitle().setText("动弹");
        }

    }

    @Override
    public void setParams(Bundle bundle) {

    }
}
