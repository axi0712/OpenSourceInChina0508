package com.example.administrator.opensourceinchina0508.controller.fragment.zonghe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;
import com.example.administrator.opensourceinchina0508.model.bean.ZongHe_RecommendBean;
import com.example.administrator.opensourceinchina0508.model.http.http.MyCallBack;
import com.example.administrator.opensourceinchina0508.model.http.http.Parsing;
import com.example.administrator.opensourceinchina0508.model.http.http.ParsingImple;
import com.example.administrator.opensourceinchina0508.model.util.Dates;
import com.example.administrator.opensourceinchina0508.model.util.UtilsData;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12 0012.
 * 作者 Administrator
 * 日期 2017/5/12 0012
 * 内容${CONTENT}
 * /**
 * /**
 * <p>
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                     88 " . " 88                    #
 * #                     (|  -_-  |)                    #
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


public class Fragment_ZongHe_Recommend extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private Parsing par = new ParsingImple();
    private List<ZongHe_RecommendBean.BlogBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    @Override
    protected int layoutId() {
        return R.layout.fragment_zonghe_recommend;
    }

    @Override
    protected void initView(View view) {
           mView = (PullToRefreshRecyclerView) view.findViewById(R.id.fragment_zonghe_recommend_pull);
        LinearLayoutManager linea = new LinearLayoutManager(getContext());
        getRetro();
        mView.setLayoutManager(linea);
    }

    private void getRetro() {
        Map<String,String> map = new HashMap<>();
        map.put("type","recommend");
        map.put("pageIndex","1");
        map.put("pageSize","20");
        par.gets(UtilsData.URL + "action/api/blog_list", map, new MyCallBack() {
            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", ZongHe_RecommendBean.class);
                xs.alias("blog",ZongHe_RecommendBean.BlogBean.class);
                ZongHe_RecommendBean homeListBean = (ZongHe_RecommendBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getBlogs());
                mAdapter =new MyAdapter(getActivity().getApplication(),mList);
                mView.setAdapter(mAdapter);
                Log.i("多少",mAdapter.getItemCount()+"");
                Log.i("KANKAN",homeListBean.getBlogs().toString());
            }

            @Override
            public void onError(String strError) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
    class MyAdapter extends BaseAdapter<ZongHe_RecommendBean.BlogBean>{

        public MyAdapter(Context context, List<ZongHe_RecommendBean.BlogBean> datas) {
            super(context, R.layout.fragment_zonghe_recommend_item, datas);
        }

        @Override
        public void convert(ViewHolder holder, final ZongHe_RecommendBean.BlogBean newsBean) {
            holder.setText(R.id.fragment_zonghe_recommend_item_text_title, newsBean.getTitle());
            holder.setText(R.id.fragment_zonghe_recommend_item_text_message, newsBean.getBody());
            holder.setText(R.id.fragment_zonghe_recommend_item_text_author, newsBean.getAuthorname());
            holder.setText(R.id.fragment_zonghe_recommend_item_count_pinlun, newsBean.getCommentCount());
            String time = Dates.getDate(newsBean.getPubDate());
            holder.setText(R.id.fragment_zonghe_recommend_item_text_time, time);
//            holder.setOnclickListener(R.id.fragment_zonghe_recommend_item_zong, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent in = new Intent(getContext(), Activity_ZongHe_KaiYuan_Detail.class);
//                    in.putExtra("commentCount", newsBean.getCommentCount());
//                    in.putExtra("id", newsBean.getId());
//                    startActivity(in);
//                }
//            });
        }
    }
}
