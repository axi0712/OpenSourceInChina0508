package com.example.administrator.opensourceinchina0508.model.http.http;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public interface Parsing {
    void get(String url, Map<String, String> map, MyCallBack callback);
    void gets(String url, Map<String, String> map, MyCallBack callback);
    void post(String url, Map<String, String> map, MyCallBack callback);
}
