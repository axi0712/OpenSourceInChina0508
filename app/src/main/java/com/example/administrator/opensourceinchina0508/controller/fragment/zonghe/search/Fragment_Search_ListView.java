package com.example.administrator.opensourceinchina0508.controller.fragment.zonghe.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/11 0011.
 * 作者 Administrator
 * 日期 2017/5/11 0011
 * 内容${CONTENT}
 * /**
 * /**
 * created by yuyh, 16/04/09
 * Copyright (c) 2016, smuyyh@gmail.com All Rights Reserved.
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


public class Fragment_Search_ListView extends BaseFragment {
    private ListView mFragmentSearchListview;
    private ArrayList<String> mList = new ArrayList<>();
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private String name;
    private MyManager mDB;
    private MyAdapter mAdapter;
    private TextView mText;
    private android.support.v4.app.FragmentManager man;


    @Override
    protected int layoutId() {
        return R.layout.fragment_search_listview;
    }

    @Override
    protected void initView(View view) {
        man = getActivity().getSupportFragmentManager();
        mText = (TextView) view.findViewById(R.id.fragment_search_listview_text);
        mFragmentSearchListview = (ListView) view.findViewById(R.id.fragment_search_listview_view);
        mShared = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        mEditor = mShared.edit();
        mDB = new MyManager(getActivity().getApplicationContext());
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mShared.getString("name", "");
                Boolean boo = mDB.deleteRecord();
                if(boo){
                    mList.clear();
                    mList.addAll(mDB.getDBAll());
//                    mAdapter.setList(mList);
                    mAdapter.notifyDataSetChanged();
                    mText.setVisibility(View.GONE);
                    Log.i("成功删除",mList.toString());
                }else{
                    Log.i("失败",mList.toString());
                }
            }
        });
        mFragmentSearchListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = mList.get(position);
                mEditor.putString("name",s);
                mEditor.commit();
                FragmentTransaction tra = man.beginTransaction();
                tra.replace(R.id.search_frame, new Fragment_search_JieGuo());
                tra.commit();

            }
        });
    }

    @Override
    protected void initData() {
        mList = mDB.getDBAll();
        mAdapter = new MyAdapter();
        mFragmentSearchListview.setAdapter(mAdapter);
        Log.i("查询添加-------",mList.toString());
        if(mList.isEmpty()){
            mAdapter.notifyDataSetChanged();
            mText.setVisibility(View.GONE);
        }else{
            mFragmentSearchListview.setAdapter(mAdapter);
            mText.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
    class  MyAdapter extends android.widget.BaseAdapter{


        @Override
        public int getCount() {
            return mList.isEmpty()?0:mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.fragment_search_listview_item,null);
                holder.name = (TextView) convertView.findViewById(R.id.fragment_search_item_text);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            String str = mList.get(position);
            holder.name.setText(str.toString()+"");
            return convertView;
        }
        class ViewHolder{
            private TextView name;
        }
    }
}
