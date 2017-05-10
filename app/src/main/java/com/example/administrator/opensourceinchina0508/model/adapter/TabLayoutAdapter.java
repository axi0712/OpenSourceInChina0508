package com.example.administrator.opensourceinchina0508.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class TabLayoutAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFraList;
    private List<String> mStrList;
    public TabLayoutAdapter(FragmentManager fm,List<Fragment> mFraList,List<String> mStrList) {
        super(fm);
        this.mFraList = mFraList;
        this.mStrList = mStrList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFraList.get(position);
    }

    @Override
    public int getCount() {
        return mFraList.isEmpty()?0:mFraList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

}
