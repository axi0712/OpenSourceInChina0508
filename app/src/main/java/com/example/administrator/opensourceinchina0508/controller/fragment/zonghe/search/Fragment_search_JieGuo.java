package com.example.administrator.opensourceinchina0508.controller.fragment.zonghe.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.adapter.TabLayoutAdapter;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

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


public class Fragment_search_JieGuo extends BaseFragment {
    private TabLayout mTab;
    private ViewPager mPager;
    private List<Fragment> mFraList = new ArrayList<>();
    private List<String> mStrList = new ArrayList<>();


    @Override
    protected int layoutId() {
        return R.layout.fragment_search_jieguo;
    }

    @Override
    protected void initView(View view) {
        mTab = (TabLayout) view.findViewById(R.id.fragment_search_jieguo_tab);
        mPager = (ViewPager) view.findViewById(R.id.fragment_search_jieguo_view);

    }

    @Override
    protected void initData() {
        Fragment_search_Post answer = new Fragment_search_Post();
        Fragment_Search_SoftWare softWare = new Fragment_Search_SoftWare();
        Fragment_search_blog blog = new Fragment_search_blog();
        Fragment_search_news news = new Fragment_search_news();
        mFraList.add(answer);
        mFraList.add(softWare);
        mFraList.add(blog);
        mFraList.add(news);
        mStrList.add("软件");
        mStrList.add("问答");
        mStrList.add("博客");
        mStrList.add("资讯");
        mTab.setTabMode(TabLayout.MODE_FIXED);
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
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
}
