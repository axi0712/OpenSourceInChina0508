package com.example.administrator.opensourceinchina0508.model.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(layoutId(), container, false);
         initView(v);
         initData();
          updateTitleBar();
        return v;
    }


    /**
     * 加载布局
     *
     * @return
     */
    protected abstract int layoutId();

    /**
     * 初始化View控件
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据（对象）
     */
    protected abstract void initData();

    //更改标题
    protected abstract void updateTitleBar();

    public abstract void setParams(Bundle bundle);

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onHidden();
        } else {
            onShow();
        }
    }

    public void onHidden() {
    }

    public void onShow() {
        updateTitleBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        layoutId();
    }

}
