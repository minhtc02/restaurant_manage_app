package com.example.restaurant_manager_app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;


public class UpdateDishFragment extends Fragment implements RunSql {
    View view;
    public static final String TAG = MainActivity.class.getSimpleName();
    MainActivity mMainActivity;
    EditText edID,edName, edDescribe, edVote, edPrice, edImage;
    Button btnUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_update_dish, container, false);


        init();
        mapping();
        setUp();
        setClick();
        return view;
    }

    private void init() {
    }

    private void mapping() {
        edID = view.findViewById(R.id.edID);
        edName = view.findViewById(R.id.edName);
        edDescribe = view.findViewById(R.id.edDescribe);
        edVote = view.findViewById(R.id.edVote);
        edPrice = view.findViewById(R.id.edPrice);
        edImage = view.findViewById(R.id.edImage);
        btnUpdate = view.findViewById(R.id.btnUpdate);
    }

    private void setClick() {
        btnUpdate.setOnClickListener(v -> {
            updateDish();
        });
    }

    private void setUp() {
    }

    private void updateDish() {
        String id = edID.getText().toString();
        String name = edName.getText().toString();
        String describe = edDescribe.getText().toString();
        String vote = edVote.getText().toString();
        String price = edPrice.getText().toString();
        String image = edImage.getText().toString();
        String sql = "UPDATE `dish` SET `name` = '" +
                name+
                "', `describes` = '" +
                describe+
                "', `vote` = '" +
                vote+
                "', `price` = '" +
                price+
                "', `image` = '" +
                image+
                "' WHERE `dish`.`id` = " +
                id+"";
        Log.i(TAG,sql);
        new ApiRunSql(sql, this).execute();
    }


    @Override
    public void start() {
        Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error() {
        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }


}