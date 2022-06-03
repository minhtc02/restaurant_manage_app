package com.example.restaurant_manager_app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.restaurant_manager_app.R;
import com.example.restaurant_manager_app.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
private BottomNavigationView navigationView;
private ViewPager viewPager;
ImageView imgBell, imgCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottom);
        viewPager = findViewById(R.id.view_pager);
        imgBell = findViewById(R.id.imgBell);
        imgCart = findViewById(R.id.imgCart);
        setUpViewPager();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.datBan:
//                        Toast.makeText(MainActivity.this, "Đặt Bàn", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.datMon:
//                        Toast.makeText(MainActivity.this, "Đặt Món", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.Account:
//                        Toast.makeText(MainActivity.this, "Tôi", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
        imgBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
            }
        });
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(4);
            }
        });
    }
    private void setUpViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
    }
}