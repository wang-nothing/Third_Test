package com.example.admin.third_test.broadcast.view;


import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.admin.third_test.R;
import com.example.admin.third_test.adapter.RecyclerAdapter;
import com.example.admin.third_test.bean.HomeBean;
import com.example.admin.third_test.home.presenter.Presenter_Home;
import com.example.admin.third_test.home.view.Iview_Home;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class BroadcastActivity extends AppCompatActivity implements Iview_Home {
    private SmartRefreshLayout smart;
    private RecyclerView recyclerView;
    private int page = 0;
    private List<HomeBean.DataBean> data;
    private List<HomeBean.DataBean> list = new ArrayList<>();
    private Presenter_Home home;
    private RecyclerAdapter recyclerAdapter;
    private List<String> imgs;
    int time=3;
    private ImageView image;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ImageLoader.getInstance().displayImage(imgs.get(time%imgs.size()),image);
            time++;
            sendEmptyMessageDelayed(1,3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        initViews();
        home = new Presenter_Home(this);
        home.getDatas(page);
    }

    private void initViews() {
        smart = findViewById(R.id.smart);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerAdapter = new RecyclerAdapter(BroadcastActivity.this, list);
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

        image = (ImageView)findViewById(R.id.iv);
        imgs = new ArrayList<>();
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508910683617&di=73f055f2e18ad010b60f29bda13ca3a8&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Da8e6763e61600c33e474d68b72253b7a%2F8644ebf81a4c510f1a7c57306a59252dd42aa564.jpg");
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508910757462&di=9e24a3cd1f6d00b3c373690b8b3ceb4e&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F19%2F75%2F71%2F571227cc578f2_1024.jpg");
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508910757460&di=461ac91bd572b862fff5c13a50b90f95&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F5d6034a85edf8db1074e8e5d0223dd54564e74a3.jpg");

        handler.sendEmptyMessageDelayed(1,1000);
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


}
