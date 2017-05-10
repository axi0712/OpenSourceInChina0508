package com.example.administrator.opensourceinchina0508.model.http.http;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public interface IHTTP {
    void GET(String url, Map<String, String> map, MyCallBack callback);
    void GETS(String url, Map<String, String> map, MyCallBack callback);
    void POST(String url, Map<String, String> map, MyCallBack callback);

}
