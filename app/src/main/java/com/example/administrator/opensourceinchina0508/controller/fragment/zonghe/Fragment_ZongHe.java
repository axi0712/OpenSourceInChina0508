package com.example.administrator.opensourceinchina0508.controller.fragment.zonghe;

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

public class Fragment_ZongHe extends BaseFragment {

    private TabLayout mTab;
    private ViewPager mPager;
    private List<Fragment> mFraList = new ArrayList<>();
    private List<String> mStrList = new ArrayList<>();

    @Override
    protected int layoutId() {
        return R.layout.fragment_zonghe;
    }

    @Override
    protected void initView(View view) {
        mTab = (TabLayout) view.findViewById(R.id.fragment_zonghe_tab);
        mPager = (ViewPager) view.findViewById(R.id.fragment_zonghe_viewpager);
    }

    @Override
    protected void initData() {

        Fragment_ZongHe_KaiYuan answer = new Fragment_ZongHe_KaiYuan();
        Fragment_ZongHe_Hot hot = new Fragment_ZongHe_Hot();
        Fragment_ZongHe_Blog blog = new Fragment_ZongHe_Blog();
        Fragment_ZongHe_Recommend recommend = new Fragment_ZongHe_Recommend();
        mFraList.add(answer);
        mFraList.add(hot);
        mFraList.add(blog);
        mFraList.add(recommend);
        mStrList.add("开源资讯");
        mStrList.add("热点");
        mStrList.add("推荐博客");
        mStrList.add("码云推荐");
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        TabLayoutAdapter mAdapter = new TabLayoutAdapter(getActivity().getSupportFragmentManager(), mFraList, mStrList);
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
    public void onShow() {
        super.onShow();
        updateTitleBar();
    }

    @Override
    public void onHidden() {
        super.onHidden();
        updateTitleBar();
    }

    @Override
    protected void updateTitleBar() {
     if (App.base instanceof MainActivity) {
            //显示\
            ((MainActivity) App.base).getmMainTextTitle().setVisibility(View.VISIBLE);
            ((MainActivity) App.base).getmMainRelaTitle().setVisibility(View.VISIBLE);

      }
        if (App.base instanceof MainActivity) {
            ((MainActivity) App.base).getmMainTextTitle().setText("综合");
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }

}
