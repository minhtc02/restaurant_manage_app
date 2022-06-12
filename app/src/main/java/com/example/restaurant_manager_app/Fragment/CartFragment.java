package com.example.restaurant_manager_app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Adapter.CartAdapter;
import com.example.restaurant_manager_app.Adapter.DishAdapter;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Database.CartDAO;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;

import java.util.ArrayList;


public class CartFragment extends Fragment implements RunSql {
    ListView listView;
    CartAdapter adapter;
    ArrayList<Dish> list;
    Dish dish;
    View view;
    MainActivity mMainActivity;
    CartDAO dao;
    Button btnOrder;
    TextView tvBill;
    String bill;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.activity_gio_hang, container, false);
        init();
        mapping();
        setUp();
        setClick();
        return view;
    }
    private void init() {
        list = new ArrayList<>();
        dao = new CartDAO(getContext());
        mMainActivity = (MainActivity) getActivity();
    }
    private void mapping() {
        listView   = view.findViewById(R.id.lvCart);
        btnOrder = view.findViewById(R.id.btnOrder);
        tvBill = view.findViewById(R.id.tvBill);
    }
    private void setUp() {
        list = (ArrayList<Dish>) dao.getAll();
        adapter = new CartAdapter(getContext(), this, list);
        listView.setAdapter(adapter);
        if (dao.checkCart()>0){
            Toast.makeText(getContext(),"Giỏ hàng trống ",Toast.LENGTH_SHORT).show();
            tvBill.setText("Tổng tiền: 0");
        }
        else {
            bill = dao.getBill();
            tvBill.setText("Tổng tiền: "+bill);
        }

    }
    private void addOrder() {
        String dishes = dao.getDishes();
        String sql = "INSERT INTO `orders` (`id`, `name`, `phoneNum`, `dishes`, `time`, `bill`) VALUES (NULL, 'minh', " +
                "'012345678', '" +
                dishes+
                "', '7:00', '" +
                bill+
                "')";
        Log.i(TAG,sql);
        new ApiRunSql(sql,this).execute();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Đã tạo đơn, vui lòng kiểm tra trong phần thông báo");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert  = builder.create();
        builder.show();
        dao.resetC();
        setUp();
        //mMainActivity.startNoti(dishes);
    }
    private void setClick() {
        btnOrder.setOnClickListener(v -> {
            addOrder();
        });
    }
    public void deleteFromCart(final String id){
        //Toast.makeText(getActivity(),"đang xóa"+id,Toast.LENGTH_SHORT).show();
        dao.delete(id);
        setUp();
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