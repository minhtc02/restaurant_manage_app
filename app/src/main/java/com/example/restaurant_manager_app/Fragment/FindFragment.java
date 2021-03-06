package com.example.restaurant_manager_app.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Adapter.FindAdapter;
import com.example.restaurant_manager_app.Api.ApiFindData;
import com.example.restaurant_manager_app.Database.CartDAO;
import com.example.restaurant_manager_app.Interface.FindData;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FindFragment extends Fragment implements FindData {
    ListView listView;
    FindAdapter adapter;
    ArrayList<Dish> list;
    View view;
    MainActivity mMainActivity;
    CartDAO dao;
    ImageView imgFind;
    EditText edName;
    String tableName = "findDataDish.php";

    public FindFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_find, container, false);

        init();
        mapping();
        updateView();
        setClick();
        return view;
    }


    private void init() {
        dao = new CartDAO(getContext());
        list = new ArrayList<>();
        mMainActivity = (MainActivity) getActivity();
    }

    private void mapping() {
        listView = view.findViewById(R.id.lvFind);
        imgFind = view.findViewById(R.id.imgFind);
        edName = view.findViewById(R.id.edName);

    }


    public void setClick() {
        imgFind.setOnClickListener(v -> findDish());
    }

    public void findDish() {
        String name = "'%" + edName.getText().toString() + "%'";
        new ApiFindData(tableName, name, this).execute();
        updateView();
    }

    public void addToCart(final Dish dish) {
        dao.insert(dish);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("???? th??m v??o gi??? h??ng");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void updateView() {
        adapter = new FindAdapter(getContext(), this, list);
        listView.setAdapter(adapter);
    }


    @Override
    public void start() {
        //Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish(String data) {
        try {
            list.clear();
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                Dish dish = new Dish(o);
                list.add(dish);
                updateView();
            }
        } catch (JSONException ignored) {
        }
    }

    @Override
    public void error() {
        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }


}