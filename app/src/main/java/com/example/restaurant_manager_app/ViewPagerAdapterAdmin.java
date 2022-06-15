package com.example.restaurant_manager_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.restaurant_manager_app.Fragment.AddDishFragment;
import com.example.restaurant_manager_app.Fragment.AddTableFragment;
import com.example.restaurant_manager_app.Fragment.DeleteDishFragment;
import com.example.restaurant_manager_app.Fragment.DeleteTableFragment;
import com.example.restaurant_manager_app.Fragment.UpdateDishFragment;
import com.example.restaurant_manager_app.Fragment.UpdateTableFragment;

public class ViewPagerAdapterAdmin extends FragmentStatePagerAdapter {
    public ViewPagerAdapterAdmin(@NonNull FragmentManager fm, int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AddDishFragment();               //return new TableFragment();
            case 1:
                return new AddTableFragment();
            case 2:
                return new DeleteDishFragment();
            case 3:
                return new DeleteTableFragment();
            case 4:
                return new UpdateDishFragment();
            case 5:
                return new UpdateTableFragment();
            default:
                return new AddDishFragment();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Thêm Món";
                break;
            case 1:
                title = "Thêm Bàn";
                break;
            case 2:
                title = "Xóa Món";
                break;
            case 3:
                title = "Xóa Bàn";
                break;
            case 4:
                title = "Sửa Món";
                break;
                case 5:
                title = "Sửa Bàn";
                break;

        }
        return title;

    }
}
