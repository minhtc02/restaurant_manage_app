package com.example.restaurant_manager_app.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.restaurant_manager_app.Database.CartDAO;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;

public class Detail_Activity extends AppCompatActivity {
    TextView tvName, tvDescribe, tvVote, tvPrice;
    ImageView imgDish;
    Button btnAdd, btnBack;
    CartDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_detail);

        Dish dish = (Dish) getIntent().getSerializableExtra("deltais");
        dao = new CartDAO(this);

        imgDish = findViewById(R.id.imgDish);
        tvName = findViewById(R.id.tvName);
        tvDescribe = findViewById(R.id.tvDescribe);
        tvVote = findViewById(R.id.tvVote);
        tvPrice = findViewById(R.id.tvPrice);
        btnAdd = findViewById(R.id.btnAddToCart);
        btnBack = findViewById(R.id.btn_back);


        tvName.setText(dish.getName());
        tvVote.setText(dish.getVote());
        tvPrice.setText(dish.getPrice());
        tvDescribe.setText(dish.getDescribe());
        Glide.with(this).load(dish.getImage()).into(imgDish);

        btnBack.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));

        btnAdd.setOnClickListener(v -> {
            dao.insert(dish);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Đã thêm vào giỏ hàng");
            builder.setCancelable(true);
            builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
            builder.show();
        });
    }
}