package com.example.admin.third_test.home.model;

import android.util.Log;

import com.example.admin.third_test.bean.HomeBean;
import com.example.admin.third_test.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Model_Home {
    private static final String TAG = "Model_Home";
    private String url_home = "http://39.108.3.12:3000/v1/restaurants?offset=";
    private String url_home_01 = "&limit=5&lng=116.29845&lat=39.95933";

    public void getData(final Imodel_Home imodel_home, int page){
        OkHttpUtils.doGet(url_home + page + url_home_01, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                imodel_home.Home_modelOnFail(1);
                Log.i(TAG, "onFailure: 失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: "+json);
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(json, HomeBean.class);
                imodel_home.Home_modelOnsuccess(homeBean);
            }
        });
    }
}
