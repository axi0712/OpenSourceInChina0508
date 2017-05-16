package com.example.administrator.opensourceinchina0508.model.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.opensourceinchina0508.App;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        App.base = this;
        initID();
        initData();

    }
    //找布局id
    protected abstract void initID();
    //找布局
    protected abstract int getLayout();
    //监听
    protected abstract void onClick();
    //加载数据
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.base = this;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
