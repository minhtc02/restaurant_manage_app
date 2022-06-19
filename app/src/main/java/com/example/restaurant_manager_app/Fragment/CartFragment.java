package com.example.restaurant_manager_app.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Adapter.CartAdapter;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Database.AccountDAO;
import com.example.restaurant_manager_app.Database.CartDAO;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.Model.Account;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class CartFragment extends Fragment implements RunSql {
    ListView listView;
    CartAdapter adapter;
    ArrayList<Dish> list;
    View view;
    MainActivity mMainActivity;
    CartDAO cartDAO;
    AccountDAO accountDAO;
    Button btnOrder;
    TextView tvBill;
    String bill;
    Account account;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.activity_gio_hang, container, false);
        init();
        mapping();
        setUp();
        setClick();
        return view;
    }

    private void init() {
        list = new ArrayList<>();
        cartDAO = new CartDAO(getContext());
        accountDAO = new AccountDAO(getContext());
        mMainActivity = (MainActivity) getActivity();
    }

    private void mapping() {
        listView = view.findViewById(R.id.lvCart);
        btnOrder = view.findViewById(R.id.btnOrder);
        tvBill = view.findViewById(R.id.tvBill);
    }

    @SuppressLint("SetTextI18n")
    private void setUp() {
        list = cartDAO.getAll();
        adapter = new CartAdapter(getContext(), this, list);
        listView.setAdapter(adapter);
        if (cartDAO.checkCart() > 0) {
            tvBill.setText("Tổng tiền: 0");
        } else {
            bill = cartDAO.getBill();
            tvBill.setText("Tổng tiền: " + bill);
        }

    }

    private void addOrder() {
        if (cartDAO.checkCart() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Vui lòng chọn món ăn !!!");
            builder.setCancelable(true);
            builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
            builder.show();
        } else if (accountDAO.checkExist() > 0) {
            liNotify();
        } else {
            String dishes = cartDAO.getDishes();
            account = accountDAO.getAll();
            String sql = "INSERT INTO `orders` (`id`, `name`, `phoneNum`, `dishes`, `time`, `bill`, `status`) VALUES (NULL, '" +
                    account.getName() +
                    "', '" +
                    account.getPhoneNum() +
                    "', '" +
                    dishes +
                    "', '" +
                    getNow() +
                    "', '" +
                    bill +
                    "', 'Đang xử lý')";
            Log.i(TAG, sql);
            new ApiRunSql(sql, this).execute();
            fnNotify();
            cartDAO.resetC();
            setUp();
        }
    }

    private String getNow() {
        return sdf.format(Calendar.getInstance().getTime());
    }

    private void setClick() {
        btnOrder.setOnClickListener(v -> addOrder());
    }

    public void deleteFromCart(final String id) {
        cartDAO.delete(id);
        setUp();
    }

    private void fnNotify() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Đặt thành công");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void liNotify() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Vui lòng đăng nhập");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }


    @Override
    public void start() {
        //Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        //Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error() {
        //Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }
}