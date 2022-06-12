package com.example.restaurant_manager_app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Interface.OnClickItemDish;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ViewDetailFragment extends Fragment {
    TextView tvName, tvDescribe, tvVote, tvPrice;
    ImageView imgDish;
    Button btnAdd;
    Dish dish;
    private MainActivity mMainActivity;
    OnClickItemDish onClickItemDish ;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_detail, container, false);

        mMainActivity = (MainActivity) getActivity();
        imgDish = view.findViewById(R.id.imgDish);
        tvName = view.findViewById(R.id.tvName);
        tvDescribe = view.findViewById(R.id.tvDescribe);
        tvVote = view.findViewById(R.id.tvVote);
        tvPrice = view.findViewById(R.id.tvPrice);
        btnAdd = view.findViewById(R.id.btnAddToCart);

        return view;
    }
    public void setUp(Dish dish){
        tvName.setText(dish.getName());
        tvVote.setText(dish.getVote());
        tvPrice.setText(dish.getPrice());
        tvDescribe.setText(dish.getDescribe());
        Glide.with(requireContext()).load(dish.getImage()).into(imgDish);
    }

    public ViewDetailFragment() {
    }
    //    @Override
//    public void onClickItem(Dish dish) {
//        tvName.setText(dish.getName());
//        tvVote.setText(dish.getVote());
//        tvPrice.setText(dish.getPrice());
//        tvDescribe.setText(dish.getDescribe());
//        Glide.with(requireContext()).load(dish.getImage()).into(imgDish);
//    }
//    public OnClickItemDish getOnClickItemDish() {
//        return onClickItemDish;
//    }
}