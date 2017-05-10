package com.example.administrator.opensourceinchina0508;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.opensourceinchina0508.model.base.BaseActivity;
import com.example.administrator.opensourceinchina0508.model.fragment.tweet.Fragment_Tweet;
import com.example.administrator.opensourceinchina0508.model.fragment.zonghe.Fragment_ZongHe;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private RelativeLayout mActivityMain;
    private RelativeLayout mMainRelaTitle;
    private TextView mMainTextTitle;
    private ImageView mMainImageSearch;
    private FrameLayout mMainFrame;
    private RadioGroup mMainGroup;
    private RadioButton mMainNews;
    private RadioButton mMainMove;
    private RadioButton mMainIamgeBuild;
    private RadioButton mMainDiscover;
    private RadioButton mMainMine;
    private FragmentManager man;
    private FragmentTransaction tra;

    private void assignViews() {
        mActivityMain = (RelativeLayout) findViewById(R.id.activity_main);
        mMainRelaTitle = (RelativeLayout) findViewById(R.id.main_rela_title);
        mMainTextTitle = (TextView) findViewById(R.id.main_text_title);
        mMainImageSearch = (ImageView) findViewById(R.id.main_image_search);
        mMainFrame = (FrameLayout) findViewById(R.id.main_Frame);
        mMainGroup = (RadioGroup) findViewById(R.id.Main_group);

        mMainNews = (RadioButton) findViewById(R.id.main_news);
        mMainMove = (RadioButton) findViewById(R.id.main_move);
        mMainIamgeBuild = (RadioButton) findViewById(R.id.main_iamge_build);
        mMainDiscover = (RadioButton) findViewById(R.id.main_discover);
        mMainMine = (RadioButton) findViewById(R.id.main_mine);
    }

    public TextView getmMainTextTitle() {
        return mMainTextTitle;
    }

    public void setmMainTextTitle(TextView mMainTextTitle) {
        this.mMainTextTitle = mMainTextTitle;
    }

    public RelativeLayout getmMainRelaTitle() {

        return mMainRelaTitle;
    }

    public void setmMainRelaTitle(RelativeLayout mMainRelaTitle) {
        this.mMainRelaTitle = mMainRelaTitle;
    }

    @Override
    protected void initID() {
        assignViews();
        manger();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onClick() {

    }

    @Override
    protected void initData() {
        mMainGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_news:
                mMainTextTitle.setText("综合");
                manger();
                break;
            case R.id.main_move:
                mMainTextTitle.setText("动弹");
                tra = man.beginTransaction();
                tra.replace(R.id.main_Frame,new Fragment_Tweet());
                tra.commit();

                break;
            case R.id.main_discover:
                mMainTextTitle.setText("发现");
                break;
        }
    }

    private void manger() {
        man = getSupportFragmentManager();
        tra = man.beginTransaction();
        tra.replace(R.id.main_Frame,new Fragment_ZongHe());
        tra.commit();
    }
}
