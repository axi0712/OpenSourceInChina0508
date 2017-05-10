package com.example.administrator.opensourceinchina0508.model.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.opensourceinchina0508.App;
import com.example.administrator.opensourceinchina0508.MainActivity;
import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class Fragment_Mine extends BaseFragment {
    @Override
    protected int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {
        if (App.base instanceof MainActivity) {
            ((MainActivity) App.base).getmMainRelaTitle().setVisibility(View.GONE);
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public void onHidden() {
        super.onHidden();
        updateTitleBar();
    }

    @Override
    public void onShow() {
        updateTitleBar();
    }
}
