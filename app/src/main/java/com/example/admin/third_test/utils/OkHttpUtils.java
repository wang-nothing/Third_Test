package com.example.admin.third_test.utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtils {
    private static OkHttpClient client = null;

    public OkHttpUtils(){

    }

    public static OkHttpClient getInstance(){
        if (client == null){
            synchronized (OkHttpUtils.class){
                if (client == null){
                    client = new OkHttpClient();
                }
            }
        }
        return client;
    }

    public static void doGetex(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void doGet(String url, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }


    public static void doPost(String url, Map<String, String> map, Callback callback){
        FormBody.Builder formBody = new FormBody.Builder();
        for (String key: map.keySet()) {
            formBody.add(key, map.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }


    public static void doPostJson(String url, String json, Callback callback){
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset = utf-8"),json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }
}
