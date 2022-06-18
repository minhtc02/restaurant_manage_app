package com.example.restaurant_manager_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.restaurant_manager_app.Fragment.AddDishFragment;
import com.example.restaurant_manager_app.Fragment.AddStaffFragment;
import com.example.restaurant_manager_app.Fragment.AddTableFragment;
import com.example.restaurant_manager_app.Fragment.SettingFragment;
import com.example.restaurant_manager_app.Fragment.UserFragment;
import com.example.restaurant_manager_app.Fragment.CartFragment;
import com.example.restaurant_manager_app.Fragment.FindFragment;
import com.example.restaurant_manager_app.Fragment.OrderFragment;
import com.example.restaurant_manager_app.Fragment.TableFragment;
import com.example.restaurant_manager_app.Fragment.DishFragment;
import com.example.restaurant_manager_app.Fragment.ViewDetailFragment;
import com.example.restaurant_manager_app.Fragment.ViewStaffFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new DishFragment();
            case 2:
                return new UserFragment();
            case 3:
                return new OrderFragment();
            case 4:
                return new CartFragment();
            case 5:
                return new ViewDetailFragment();
            case 6:
                return new FindFragment();
            case 7:
                return new SettingFragment();
            case 8:
                return new AddStaffFragment();
            case 9:
                return new ViewStaffFragment();
            case 10:
                return new AddDishFragment();
            case 11:
                return new AddTableFragment();
            case 0:
            default:
                return new TableFragment();
        }
    }

    @Override
    public int getCount() {
        return 12;
    }
}
