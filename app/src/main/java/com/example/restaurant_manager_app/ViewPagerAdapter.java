package com.example.restaurant_manager_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.restaurant_manager_app.Fragment.AccountFragment;
import com.example.restaurant_manager_app.Fragment.AddDishFragment;
import com.example.restaurant_manager_app.Fragment.CartFragment;
import com.example.restaurant_manager_app.Fragment.DeleteDishFragment;
import com.example.restaurant_manager_app.Fragment.TableFragment;
import com.example.restaurant_manager_app.Fragment.DishFragment;
import com.example.restaurant_manager_app.Fragment.NotificationFragment;
import com.example.restaurant_manager_app.Fragment.UpdateDishFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
               // return new DatBanFragment();
                return new TableFragment();
            case 1:
                return new DishFragment();
            case 2:
                return new AccountFragment();
            case 3:
                return new NotificationFragment();
            case 4:
                return new CartFragment();
            default:
                return new TableFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
