package com.example.restaurant_manager_app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.restaurant_manager_app.Database.CartDAO;
import com.example.restaurant_manager_app.R;
import com.example.restaurant_manager_app.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    ImageView imgBell, imgCart;
    TextView edFind;
    CartDAO dao;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigationView = findViewById(R.id.bottom);
        viewPager = findViewById(R.id.view_pager);
        imgBell = findViewById(R.id.imgBell);
        imgCart = findViewById(R.id.imgCart);
        edFind = findViewById(R.id.edFind);
        String name = "Gà nướng muối ớt";
        dao = new CartDAO(this);
        if (dao.checkCart() < 0) {
            startServices(name);
        }
        setUpViewPager();
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.datBan:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.datMon:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.Account:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });
        edFind.setOnClickListener(v -> viewPager.setCurrentItem(6));
        imgBell.setOnClickListener(view -> viewPager.setCurrentItem(3));
        imgCart.setOnClickListener(view -> viewPager.setCurrentItem(4));
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
    }

    public void startServices(String ten) {
        String name = ten + " Thơm ngon mời bạn ăn nha";
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("name", name);
        startService(intent);
    }

    public void replaceFragmentOrder() {
        viewPager.setCurrentItem(3);
    }

    public void replaceFragmentDish() {
        viewPager.setCurrentItem(1);
    }

    public void replaceFragmentTable() {
        viewPager.setCurrentItem(0);
    }

    public void replaceFragmentCart() {
        viewPager.setCurrentItem(4);
    }

    public void replaceFragmentSetting() {
        viewPager.setCurrentItem(7);
    }

    public void replaceFragmentAddStaff() {
        viewPager.setCurrentItem(8);
    }

    public void replaceFragmentViewStaff() {
        viewPager.setCurrentItem(9);
    }

    public void replaceFragmentAddDish() {
        viewPager.setCurrentItem(10);
    }

    public void replaceFragmentAddTable() {
        viewPager.setCurrentItem(11);
    }


}