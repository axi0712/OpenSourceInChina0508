package com.example.administrator.opensourceinchina0508;

import android.content.Intent;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.opensourceinchina0508.controller.fragment.Fragment_Discover;
import com.example.administrator.opensourceinchina0508.controller.fragment.Fragment_Mine;
import com.example.administrator.opensourceinchina0508.controller.fragment.tweet.Fragment_Tweet;
import com.example.administrator.opensourceinchina0508.controller.fragment.zonghe.Fragment_ZongHe;
import com.example.administrator.opensourceinchina0508.controller.fragment.zonghe.search.Activity_Search;
import com.example.administrator.opensourceinchina0508.model.base.BaseActivity;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;
import com.example.administrator.opensourceinchina0508.model.base.FragmentBuilder;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    private long mExitTime;
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
        mMainImageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in  = new Intent(MainActivity.this, Activity_Search.class);
                startActivity(in);
            }
        });
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
                FragmentBuilder.getInstance().start(Fragment_ZongHe.class).build();
                break;
            case R.id.main_move:
                mMainTextTitle.setText("动弹");
                FragmentBuilder.getInstance().start(Fragment_Tweet.class).build();

                break;
            case R.id.main_discover:
                mMainTextTitle.setText("发现");
                FragmentBuilder.getInstance().start(Fragment_Discover.class).build();
                break;
            case R.id.main_mine:
                mMainRelaTitle.setVisibility(View.GONE);
                FragmentBuilder.getInstance().start(Fragment_Mine.class).build();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    private void manger() {
//        man = getSupportFragmentManager();
//        tra = man.beginTransaction();
//        tra.add(R.id.main_Frame,new Fragment_ZongHe());
//        tra.commit();
        FragmentBuilder.getInstance().start(Fragment_ZongHe.class).build();
    }
    @Override
    public void onBackPressed() {
        String name = man.getBackStackEntryAt(man.getBackStackEntryCount() - 1).getName();
        if (Fragment_ZongHe.class.getSimpleName().equals(name)
                || Fragment_Tweet.class.getSimpleName().equals(name)
                || Fragment_Discover.class.getSimpleName().equals(name)
                || Fragment_Mine.class.getSimpleName().equals(name)) {
            finish();
        } else {
            if (man.getBackStackEntryCount() > 1) {
                //弹栈
                man.popBackStackImmediate();
                String name1 = man.getBackStackEntryAt(man.getBackStackEntryCount() - 1).getName();
                BaseFragment frag = (BaseFragment) man.findFragmentByTag(name1);
                App.lastFragment = frag;
            }
        }

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
