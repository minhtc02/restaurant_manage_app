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

import com.example.restaurant_manager_app.Fragment.DishFragment;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;

import java.util.ArrayList;

public class ViewDetailAdapter extends ArrayAdapter<Dish> implements Filterable {
    private final Context context;
    private final ArrayList<Dish> mListDish;
    DishFragment fragment;

    public ViewDetailAdapter(Context context, DishFragment fragment, ArrayList<Dish> mListDish) {
        super(context, 0, mListDish);
        this.context = context;
        this.mListDish = mListDish;
        this.fragment  = fragment;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_dish, null);
        }
        if (mListDish.size() > 0) {
            Dish dish = mListDish.get(position);
            TextView tvName = convertView.findViewById(R.id.tvName);
            TextView tvVote = convertView.findViewById(R.id.tvVote);
            TextView tvPrice = convertView.findViewById(R.id.tvPrice);
            TextView tvDescribe = convertView.findViewById(R.id.tvDescribe);
            ImageView imgDish = convertView.findViewById(R.id.imgDish);
            Button btnAddToCart = convertView.findViewById(R.id.btnAddToCart);

            tvName.setText(dish.getName());
            tvVote.setText(dish.getVote());
            tvPrice.setText(dish.getPrice());
            tvDescribe.setText(dish.getDescribe());
            Glide.with(context).load(dish.getImage()).into(imgDish);

            btnAddToCart.setOnClickListener(v -> {
                fragment.addToCart(dish);
            });
        }

        return convertView;

    }
}
