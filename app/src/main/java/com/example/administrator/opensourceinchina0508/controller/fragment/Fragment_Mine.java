package com.example.administrator.opensourceinchina0508.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.administrator.opensourceinchina0508.App;
import com.example.administrator.opensourceinchina0508.MainActivity;
import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.controller.fragment.zonghe.Activity_login;
import com.example.administrator.opensourceinchina0508.model.base.BaseFragment;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class Fragment_Mine extends BaseFragment {
    private ImageView mLoginSetting;
    private ImageView mMineImageTouxiang;
    private TextView mMineTextName;
    private RelativeLayout mDongtanRela;
    private TextView mMineDongtan;
    private TextView mMineDongtanZi;
    private TextView mMineShoucang;
    private TextView mMineShoucangZi;
    private RelativeLayout mItemGuanzhuRela;
    private TextView mMineGuanzhu;
    private TextView mMineGuanzhuZi;
    private RelativeLayout mMineFensiZong;
    private TextView mMineFensi;
    private TextView mMineFensiZi;
    private RelativeLayout mMineMessage;
    private ImageView mMessage;
    private RelativeLayout mMineBlog;
    private ImageView mBlog;
    private RelativeLayout mMineAnswer;
    private ImageView mEvent;
    private RelativeLayout mMineAction;
    private ImageView mQuestion;
    private RelativeLayout mMineTeam;
    private ImageView mTeam;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;

    @Override
    protected int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        mShared = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        mLoginSetting = (ImageView) view.findViewById(R.id.login_setting);
        mMineImageTouxiang = (ImageView) view.findViewById(R.id.mine_image_touxiang);

        mMineTextName = (TextView) view.findViewById(R.id.mine_text_name);
        mDongtanRela = (RelativeLayout) view.findViewById(R.id.dongtan_rela);
        mMineDongtan = (TextView) view.findViewById(R.id.mine_dongtan);
        mMineDongtanZi = (TextView) view.findViewById(R.id.mine_dongtan_zi);
        mMineShoucang = (TextView) view.findViewById(R.id.mine_shoucang);
        mMineShoucangZi = (TextView) view.findViewById(R.id.mine_shoucang_zi);
        mItemGuanzhuRela = (RelativeLayout) view.findViewById(R.id.item_guanzhu_rela);
        mMineGuanzhu = (TextView) view.findViewById(R.id.mine_guanzhu);
        mMineGuanzhuZi = (TextView) view.findViewById(R.id.mine_guanzhu_zi);
        mMineFensiZong = (RelativeLayout) view.findViewById(R.id.mine_fensi_zong);
        mMineFensi = (TextView) view.findViewById(R.id.mine_fensi);
        mMineFensiZi = (TextView) view.findViewById(R.id.mine_fensi_zi);
        mMineMessage = (RelativeLayout) view.findViewById(R.id.mine_message);
        mMessage = (ImageView) view.findViewById(R.id.message);
        mMineBlog = (RelativeLayout) view.findViewById(R.id.mine_blog);
        mBlog = (ImageView) view.findViewById(R.id.blog);
        mMineAnswer = (RelativeLayout) view.findViewById(R.id.mine_answer);
        mEvent = (ImageView) view.findViewById(R.id.event);
        mMineAction = (RelativeLayout) view.findViewById(R.id.mine_action);
        mQuestion = (ImageView) view.findViewById(R.id.question);
        mMineTeam = (RelativeLayout) view.findViewById(R.id.mine_team);
        mTeam = (ImageView) view.findViewById(R.id.team);
    }

    @Override
    protected void initData() {
        final String uid = mShared.getString("uid", "");
        if (uid == null || uid.equals("")) {
            Toast.makeText(App.base, uid, Toast.LENGTH_SHORT).show();
            mMineImageTouxiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(App.base, Activity_login.class);
                    startActivity(in);

                }
            });
        } else {
            Toast.makeText(App.base, uid, Toast.LENGTH_SHORT).show();
            String image = mShared.getString("image", "");
            Glide.with(getContext()).load(image).asBitmap().centerCrop().into(new BitmapImageViewTarget(mMineImageTouxiang) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    ciDrawable.setCircular(true);
                    mMineImageTouxiang.setImageDrawable(ciDrawable);
                }
            });
            mMineTextName.setText(mShared.getString("names", ""));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
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
