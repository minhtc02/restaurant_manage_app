package com.example.restaurant_manager_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.restaurant_manager_app.Fragment.AccountFragment;
import com.example.restaurant_manager_app.Fragment.CartFragment;
import com.example.restaurant_manager_app.Fragment.DatBanFragment;
import com.example.restaurant_manager_app.Fragment.DatMonFragment;
import com.example.restaurant_manager_app.Fragment.NotificationFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DatBanFragment();
            case 1:
                return new DatMonFragment();
            case 2:
                return new AccountFragment();
            case 3:
                return new NotificationFragment();
            case 4:
                return new CartFragment();
            default:
                return new DatBanFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
