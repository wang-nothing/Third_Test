package com.example.admin.third_test;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.third_test.adapter.RecyclerAdapter;
import com.example.admin.third_test.bean.HomeBean;
import com.example.admin.third_test.broadcast.view.BroadcastActivity;
import com.example.admin.third_test.home.presenter.Presenter_Home;
import com.example.admin.third_test.home.view.Iview_Home;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Iview_Home, RecyclerAdapter.OnItemClickListener {
    private SmartRefreshLayout smart;
    private RecyclerView recyclerView;
    private int page = 0;
    private List<HomeBean.DataBean> data;
    private List<HomeBean.DataBean> list = new ArrayList<>();
    private Presenter_Home home;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        home = new Presenter_Home(this);
        home.getDatas(page);
    }

    private void initViews() {
        smart = findViewById(R.id.smart);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerAdapter = new RecyclerAdapter(MainActivity.this, list);
        recyclerView.setAdapter(recyclerAdapter);
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                home.getDatas(page);
            }
        });

        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                home.getDatas(page++);
            }
        });
        recyclerAdapter.setmItemClickListener(this);
    }

    @Override
    public void Home_viewOnsuccess(HomeBean homeBean) {
        data = homeBean.getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list.addAll(data);
                recyclerAdapter.notifyDataSetChanged();
                smart.finishLoadMore(2000);
                smart.finishRefresh(2000);
            }
        });
    }

    @Override
    public void Home_viewOnFail(int code) {

    }

    @Override
    public void onItemClick(int i) {
        Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
        intent.putExtra("id",data.get(i).getId());
        startActivity(intent);
    }
}