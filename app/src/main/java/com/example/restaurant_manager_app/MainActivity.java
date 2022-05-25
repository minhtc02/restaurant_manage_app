package com.example.restaurant_manager_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
private BottomNavigationView navigationView;
private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottom);
        viewPager = findViewById(R.id.view_pager);
        setUpViewPager();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.datBan:
                        Toast.makeText(MainActivity.this, "Đặt Bàn", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.datMon:
                        Toast.makeText(MainActivity.this, "Đặt Món", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.Account:
                        Toast.makeText(MainActivity.this, "Tôi", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }
    private void setUpViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
    }
}