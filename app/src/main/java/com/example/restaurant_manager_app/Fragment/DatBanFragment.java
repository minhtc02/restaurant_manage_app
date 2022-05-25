package com.example.restaurant_manager_app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.restaurant_manager_app.R;

public class DatBanFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

               View view =  inflater.inflate(R.layout.bookban_layout, container, false);
                return view;
    }
}