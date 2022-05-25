package com.example.restaurant_manager_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.restaurant_manager_app.Fragment.AccountFragment;
import com.example.restaurant_manager_app.Fragment.DatMonFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
//            case 0:
            case 1:
                return new DatMonFragment();
            case 2:
                return new AccountFragment();
//            default:
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
