package com.example.restaurant_manager_app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.R;

public class SettingFragment extends Fragment {
    Button btnAddStaff, btnAddDish, btnAddTable, btnViewStaff;
    View view;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);

        init();
        mapping();
        setUp();
        setClick();
        return view;
    }

    private void init() {
        mainActivity = (MainActivity) getActivity();
    }

    private void mapping() {
        btnAddStaff = view.findViewById(R.id.btnAddStaff);
        btnViewStaff = view.findViewById(R.id.btnViewStaff);
        btnAddDish = view.findViewById(R.id.btnAddDish);
        btnAddTable = view.findViewById(R.id.btnAddTable);
    }

    private void setClick() {
        btnAddStaff.setOnClickListener(v -> mainActivity.replaceFragmentAddStaff());

        btnViewStaff.setOnClickListener(v -> mainActivity.replaceFragmentViewStaff());

        btnAddDish.setOnClickListener(v -> mainActivity.replaceFragmentAddDish());

        btnAddTable.setOnClickListener(v -> mainActivity.replaceFragmentAddTable());


    }


    private void setUp() {
    }


}