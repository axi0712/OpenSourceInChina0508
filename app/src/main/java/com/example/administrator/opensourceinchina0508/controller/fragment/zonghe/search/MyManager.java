package com.example.administrator.opensourceinchina0508.controller.fragment.zonghe.search;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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


public class MyManager {

    private MySql mySql;
    private SQLiteDatabase mDB;
    private Context context;
    private final String DB_NAME = "lwx.db";
    private final int DB_VERSION = 1;

    public MyManager(Context context) {
        this.context = context;
        mySql = new MySql(context,DB_NAME,DB_VERSION);
        mDB = mySql.getWritableDatabase();
    }
    public Boolean insert(String name){
        Boolean boo ;
        ContentValues con = new ContentValues();
        con.put("name",name);
        long insert = mDB.insert("search",null,con);
        if(insert>0){
            boo = true;
        }else{
            boo = false;
        }
        return boo;
    }
    public ArrayList<String> getDBAll(){
        ArrayList<String> list =  new ArrayList<String>();
        //String sql = select * from lwx;
        Cursor cursor = mDB.query("search", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));

            list.add(name);
        }
        return list;
    }
    public Boolean deleteRecord() {
        Boolean boo;
        int count = mDB.delete("search", null,null);
        if (count > 0) {
            boo = true;
        } else {
            boo = false;
        }
        return boo;
    }

}
