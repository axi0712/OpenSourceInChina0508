package com.example.administrator.opensourceinchina0508.controller.fragment.zonghe.search;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/11 0011.
 * 作者 Administrator
 * 日期 2017/5/11 0011
 * 内容 搜索主页面
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


public class Activity_Search extends BaseActivity {
    private EditText mSearchEdit;
    private TextView mSearchTextQuxiao;
    private FrameLayout mSearchFrame;
    private SharedPreferences mShare;
    private SharedPreferences.Editor mEditor;
    private MyManager mDB;
    private android.support.v4.app.FragmentManager man;

    private void assignViews() {
        mShare = getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShare.edit();
        mSearchEdit = (EditText) findViewById(R.id.search_edit);
        mSearchTextQuxiao = (TextView) findViewById(R.id.search_text_quxiao);
        mSearchFrame = (FrameLayout) findViewById(R.id.search_frame);
        mDB = new MyManager(Activity_Search.this);
        mSearchEdit.setOnKeyListener(new View.OnKeyListener() {
                                         @Override
                                         public boolean onKey(View v, int keyCode, KeyEvent event) {
                                             //输入完后按键盘上的搜索键【回车键改为了搜索键】


                                             if (keyCode == KeyEvent.KEYCODE_ENTER) {
                                                 // 先隐藏键盘
                                                 ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                                                         .hideSoftInputFromWindow(Activity_Search.this.getCurrentFocus()
                                                                 .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                                                 //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                                             }
                                             //跳转到搜索结果界面
                                             if (!mSearchEdit.getText().toString().isEmpty()) {
                                                 search();
                                             } else {
                                                 Toast.makeText(Activity_Search.this, "不能为空", Toast.LENGTH_SHORT).show();
                                             }
                                             return false;
                                         }

                                     }
        );
    }
    private void initPanDuan(){
        if(mSearchEdit.getText().toString().isEmpty()){

            man = getSupportFragmentManager();
            FragmentTransaction tra = man.beginTransaction();
            tra.replace(R.id.search_frame, new Fragment_Search_ListView());
            tra.commit();
        }
    }
    private void search() {
        initPanDuan();
        mEditor.putString("name", mSearchEdit.getText().toString());
        mEditor.commit();
        String name = mShare.getString("name", "");
        Boolean boo = mDB.insert(name);
        if (boo) {
            Log.i("添加++++++", name);
        } else {
        }
        man = getSupportFragmentManager();
        FragmentTransaction tra = man.beginTransaction();
        tra.replace(R.id.search_frame, new Fragment_search_JieGuo());
        tra.commit();


    }

    @Override
    protected void initID() {
        assignViews();
        man = getSupportFragmentManager();
        FragmentTransaction tra = man.beginTransaction();
        tra.replace(R.id.search_frame, new Fragment_Search_ListView());
        tra.commit();
        initPanDuan();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void onClick() {

    }

    @Override
    protected void initData() {
       initPanDuan();
    }
}
