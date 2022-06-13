package com.example.restaurant_manager_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.restaurant_manager_app.Fragment.OrderFragment;
import com.example.restaurant_manager_app.Interface.OnClickItemDish;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.Model.Order;
import com.example.restaurant_manager_app.R;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> implements Filterable {
    private final Context context;
    private final ArrayList<Order> mListOrder;
    OrderFragment fragment;

    public OrderAdapter(Context context, OrderFragment fragment, ArrayList<Order> mListOrder) {
        super(context, 0, mListOrder);
        this.context = context;
        this.mListOrder = mListOrder;
        this.fragment  = fragment;
    }


    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_order, null);
        }
        if (mListOrder.size() > 0) {
            Order order = mListOrder.get(position);
            TextView tvId = convertView.findViewById(R.id.tvId);
            TextView tvName = convertView.findViewById(R.id.tvName);
            TextView tvPhoneNum = convertView.findViewById(R.id.tvPhoneNum);
            TextView tvDishes = convertView.findViewById(R.id.tvDishes);
            TextView tvTime = convertView.findViewById(R.id.tvTime);
            TextView tvBill = convertView.findViewById(R.id.tvBill);
            TextView tvStatus = convertView.findViewById(R.id.tvStatus);
            TextView tvType = convertView.findViewById(R.id.tvType);
            ImageView imgDish = convertView.findViewById(R.id.imgDish);
            Button btnHelp = convertView.findViewById(R.id.btnHelp);

            tvId.setText(order.getId());
            tvName.setText(order.getName());
            tvPhoneNum.setText(order.getPhoneNum());
            tvDishes.setText(order.getDishes());
            tvTime.setText(order.getTime());
            tvBill.setText(order.getBill());
            tvStatus.setText(order.getStatus());
            if (order.getDishes().contains("bàn")){
                tvType.setText("Đồ ăn/ Bàn #");
                imgDish.setImageResource(R.drawable.table_img);
            }
            btnHelp.setOnClickListener(v -> {
                fragment.helper();
            });


        }

        return convertView;

    }
}

