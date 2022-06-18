package com.example.restaurant_manager_app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Adapter.DishAdapter;
import com.example.restaurant_manager_app.Adapter.StaffAdapter;
import com.example.restaurant_manager_app.Api.ApiGetData;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Database.CartDAO;
import com.example.restaurant_manager_app.Interface.GetData;
import com.example.restaurant_manager_app.Interface.OnClickItemDish;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.Model.Account;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.Model.Order;
import com.example.restaurant_manager_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ViewStaffFragment extends Fragment implements GetData, RunSql {
    ListView listView;
    StaffAdapter adapter;
    ArrayList<Account> list;
    View view;
    MainActivity mMainActivity;
    String tableName = "getDataStaff.php";
    SwipeRefreshLayout mySwipeRefreshLayout;
    Dialog dialog;
    EditText edId, edName, edPhoneNum, edEmail,edUsername, edPassword, edImage;
    Button btnSave, btnCancel;
    Account item;
    ImageView imgBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_view_staff, container, false);

        init();
        mapping();
        setClick();
        new ApiGetData(tableName, this).execute();
        updateView();
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        myUpdateOperation();
                    }
                }
        );
        return view;
    }


    private void init() {
        list = new ArrayList<>();
        mMainActivity = (MainActivity) getActivity();
    }
    public void myUpdateOperation(){
        new ApiGetData(tableName, this).execute();
        mySwipeRefreshLayout.setRefreshing(false);
        updateView();
    }

    private void mapping() {
        listView = view.findViewById(R.id.lvStaff);
        mySwipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        imgBack = view.findViewById(R.id.imgBack);
    }
    private void setClick() {

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }
    private void back(){
        mMainActivity.replaceFragmentSetting();
    }

    public void deleteR(String id) {
        String sql = "DELETE FROM `account` WHERE `account`.`id` = " +
                "" +
                id + "";
        new ApiRunSql(sql, this).execute();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Đã xóa");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                myUpdateOperation();
            }
        });
        builder.show();
    }
    public void updateR(Account account) {
        item = account;
        openDialog(getActivity());
    }

    protected void openDialog(final Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_staff);
        edId = dialog.findViewById(R.id.edID);
        edName = dialog.findViewById(R.id.edName);
        edUsername = dialog.findViewById(R.id.edUsername);
        edPassword = dialog.findViewById(R.id.edPassword);
        edPhoneNum = dialog.findViewById(R.id.edPhoneNum);
        edEmail = dialog.findViewById(R.id.edEmail);
        edImage = dialog.findViewById(R.id.edImage);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnSave = dialog.findViewById(R.id.btnSave);
        edId.setEnabled(false);
        edId.setText(item.getId());
        edName.setText(item.getName());
        edUsername.setText(item.getUsername());
        edPassword.setText(item.getPassword());
        edPhoneNum.setText(item.getPhoneNum());
        edEmail.setText(item.getEmail());
        edImage.setText(item.getImage());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(v -> {
            String id = edId.getText().toString();
            String name = edName.getText().toString();
            String username = edUsername.getText().toString();
            String password = edPassword.getText().toString();
            String phoneNum = edPhoneNum.getText().toString();
            String email = edEmail.getText().toString();
            String image = edImage.getText().toString();
            String sql = "UPDATE `account` SET `username` = '" +
                    username+
                    "', `password` = '" +
                    password+
                    "', `name` = '" +
                    name+
                    "', `phoneNum` = '" +
                    phoneNum+
                    "', `email` = '" +
                    email+
                    "', `image` = '" +
                    image+
                    "' WHERE `account`.`id` = " +
                    id+"";
            new ApiRunSql(sql, this).execute();
            dialog.dismiss();
        });
        dialog.show();
    }

    private void updateView() {
        adapter = new StaffAdapter(getContext(), this, list);
        listView.setAdapter(adapter);
    }


    @Override
    public void start() {
        //Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {

    }

    @Override
    public void finish(String data) {
        try {
            list.clear();
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                Account account = new Account(o);
                list.add(account);
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