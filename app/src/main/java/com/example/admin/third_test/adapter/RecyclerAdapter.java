package com.example.admin.third_test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.admin.third_test.R;
import com.example.admin.third_test.bean.HomeBean;
import com.example.admin.third_test.viewholder.RecyclerHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> implements View.OnClickListener {
    private Context context;
    private List<HomeBean.DataBean> list;

    public RecyclerAdapter(Context context, List<HomeBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, viewGroup, false);
        view.setOnClickListener(this);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder recyclerHolder, int i) {
        recyclerHolder.home_item_title.setText(list.get(i).getName());
        recyclerHolder.home_item_month_sales_tip.setText(list.get(i).getMonth_sales_tip());
        recyclerHolder.home_item_distance.setText(list.get(i).getDelivery_time_tip()+"/"+list.get(i).getDistance());
        recyclerHolder.home_item_min_price_tip.setText(list.get(i).getMin_price_tip()+"|"+list.get(i).getShipping_fee_tip()+"|"+list.get(i).getAverage_price_tip());
        recyclerHolder.home_item_discounts2_imfo1.setText(list.get(i).getDiscounts2().get(0).getInfo());
        recyclerHolder.home_item_discounts2_imfo2.setText(list.get(i).getDiscounts2().get(1).getInfo());
        Glide.with(context).load(list.get(i).getPic_url()).into(recyclerHolder.home_item_image);
        Glide.with(context).load(list.get(i).getDiscounts2().get(0).getIcon_url()).into(recyclerHolder.home_item_discounts2_icon1);
        Glide.with(context).load(list.get(i).getDiscounts2().get(1).getIcon_url()).into(recyclerHolder.home_item_discounts2_icon2);
        recyclerHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private OnItemClickListener mItemClickListener;

    public void setmItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View view) {
        if (mItemClickListener != null){
            mItemClickListener.onItemClick((Integer) view.getTag());
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int i);
    }
}
