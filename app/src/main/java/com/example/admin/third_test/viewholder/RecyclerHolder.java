package com.example.admin.third_test.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.third_test.R;

public class RecyclerHolder extends RecyclerView.ViewHolder {
    public TextView home_item_title;
    public TextView home_item_month_sales_tip;
    public TextView home_item_distance;
    public TextView home_item_min_price_tip;
    public TextView home_item_discounts2_imfo1;
    public TextView home_item_discounts2_imfo2;
    public ImageView home_item_discounts2_icon1;
    public ImageView home_item_discounts2_icon2;
    public ImageView home_item_image;
    public RecyclerHolder(@NonNull View itemView) {
        super(itemView);
        home_item_title = itemView.findViewById(R.id.home_item_title);
        home_item_month_sales_tip = itemView.findViewById(R.id.home_item_month_sales_tip);
        home_item_distance = itemView.findViewById(R.id.home_item_distance);
        home_item_min_price_tip = itemView.findViewById(R.id.home_item_min_price_tip);
        home_item_discounts2_imfo1 = itemView.findViewById(R.id.home_item_discounts2_imfo1);
        home_item_discounts2_imfo2 = itemView.findViewById(R.id.home_item_discounts2_imfo2);
        home_item_discounts2_icon1 = itemView.findViewById(R.id.home_item_discounts2_icon1);
        home_item_discounts2_icon2 = itemView.findViewById(R.id.home_item_discounts2_icon2);
        home_item_image = itemView.findViewById(R.id.home_item_image);
    }
}
