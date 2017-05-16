package com.example.administrator.opensourceinchina0508.controller.fragment.zonghe;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.opensourceinchina0508.R;
import com.example.administrator.opensourceinchina0508.model.base.BaseActivity;
import com.example.administrator.opensourceinchina0508.model.bean.Login_Bean;
import com.example.administrator.opensourceinchina0508.model.http.http.MyCallBack;
import com.example.administrator.opensourceinchina0508.model.http.http.Parsing;
import com.example.administrator.opensourceinchina0508.model.http.http.ParsingImple;
import com.example.administrator.opensourceinchina0508.model.util.UtilsData;
import com.thoughtworks.xstream.XStream;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 * 作者 Administrator
 * 日期 2017/5/11 0011
 * 内容${CONTENT}
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


public class Activity_login extends BaseActivity {
    private EditText mLoginName;
    private EditText mLoginMima;
    private Button mLoginLogin;
    private String name,pwd;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private Parsing par = new ParsingImple();
    private Map<String, String> map;

    private void assignViews() {
        mLoginName = (EditText) findViewById(R.id.login_name);
        mLoginMima = (EditText) findViewById(R.id.login_mima);
        mLoginLogin = (Button) findViewById(R.id.login_login);
    }

    @Override
    protected void initID() {
        mShared = getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mShared.edit();
        assignViews();
        mLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRetro();
            }
        });

    }

    private void getRetro() {
        isChecked();

        par.get(UtilsData.URL + "action/api/login_validate", map, new MyCallBack() {
            @Override
            public void onSuccess(String strSuccess) {
               XStream xs = new XStream();
                xs.alias("oschina", Login_Bean.class);
                xs.alias("result", Login_Bean.ResultBean.class);
                Login_Bean loginBean = (Login_Bean) xs.fromXML(strSuccess);
                Log.i("login", loginBean.getResult().getErrorCode() + loginBean.getResult().getErrorMessage());

                if (Integer.parseInt(loginBean.getResult().getErrorCode()) == 1) {
                    Toast.makeText(Activity_login.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Log.i("登陆成功", loginBean.getResult().getErrorMessage());
                    Log.i("image++++",loginBean.getUser().getPortrait());
                    Log.i("shji_______",loginBean.getUser().getUid());
                    mEditor.putString("image",loginBean.getUser().getPortrait());
                    mEditor.putString("names",loginBean.getUser().getName());
                    mEditor.putString("uid",loginBean.getUser().getUid());
                    mEditor.commit();
                    finish();
                } else {
                    Toast.makeText(Activity_login.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onError(String strError) {

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onClick() {

    }

    @Override
    protected void initData() {

    }
    public Boolean isChecked() {
        name = mLoginName.getText().toString();
        pwd = mLoginMima.getText().toString();
        if (name.isEmpty() || pwd.isEmpty()) {
            return false;
        }
        map = new HashMap<>();
        map.put("username",name);
        map.put("pwd",pwd);
        map.put("keep_login","1");
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
