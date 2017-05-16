package com.example.administrator.opensourceinchina0508.controller.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.opensourceinchina0508.App;
import com.example.administrator.opensourceinchina0508.MainActivity;
import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;

/**
 * /**
 * 项目名称: 开源中国
 * 类描述:
 * 创建人: XI
 * 创建时间: 2017/5/14 0014 20:08
 * 修改人:
 * 修改内容:
 * 修改时间:
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


public class Fragment_Discover extends BaseFragment {
    @Override
    protected int layoutId() {
        return R.layout.fragment_discover;
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
            //显示
            ((MainActivity) App.base).getmMainTextTitle().setVisibility(View.VISIBLE);
            ((MainActivity) App.base).getmMainRelaTitle().setVisibility(View.VISIBLE);

        }
        if (App.base instanceof MainActivity) {
            ((MainActivity) App.base).getmMainTextTitle().setText("发现");
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }
}
