package com.example.restaurant_manager_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.restaurant_manager_app.R;
import com.example.restaurant_manager_app.ViewPagerAdapter;
import com.example.restaurant_manager_app.ViewPagerAdapterAdmin;
import com.google.android.material.tabs.TabLayout;

public class Main_Admin extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pageradmin);

        ViewPagerAdapterAdmin viewPagerAdapterAdmin = new ViewPagerAdapterAdmin(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapterAdmin);

        tabLayout.setupWithViewPager(viewPager);
    }
}