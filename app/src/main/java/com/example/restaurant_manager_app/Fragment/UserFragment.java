package com.example.restaurant_manager_app.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.restaurant_manager_app.Activity.Login_Activity;
import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Activity.Register_Activity;
import com.example.restaurant_manager_app.Database.AccountDAO;
import com.example.restaurant_manager_app.Model.Account;
import com.example.restaurant_manager_app.R;


public class UserFragment extends Fragment {
    Account account;
    AccountDAO dao;
    Button btnLogIn, btnRegister, btnLogOut;
    TextView tvName, tvPhoneNum, tvEmail;
    ImageView imgImage, btnDish, btnTable, btnCart, btnOrder, btnHelp, btnSetting;
    View view;
    MainActivity mainActivity;
    SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user, container, false);

        init();
        mapping();
        setUp();
        setClick();
        mySwipeRefreshLayout.setOnRefreshListener(
                this::myUpdateOperation
        );
        return view;
    }

    public void myUpdateOperation() {
        mySwipeRefreshLayout.setRefreshing(false);
        setUp();
    }

    private void init() {
        dao = new AccountDAO(getContext());
        mainActivity = (MainActivity) getActivity();
    }

    private void mapping() {
        btnLogIn = view.findViewById(R.id.btnLogIn);
        btnRegister = view.findViewById(R.id.btnRegister);
        btnLogOut = view.findViewById(R.id.btnLogOut);
        btnOrder = view.findViewById(R.id.btnOrder);
        btnDish = view.findViewById(R.id.btnDish);
        btnTable = view.findViewById(R.id.btnTable);
        btnCart = view.findViewById(R.id.btnCart);
        btnHelp = view.findViewById(R.id.btnHelp);
        btnSetting = view.findViewById(R.id.btnSetting);
        imgImage = view.findViewById(R.id.imgImage);
        tvName = view.findViewById(R.id.tvName);
        tvPhoneNum = view.findViewById(R.id.tvPhoneNum);
        tvEmail = view.findViewById(R.id.tvEmail);
        mySwipeRefreshLayout = view.findViewById(R.id.swiperefresh);
    }

    private void setClick() {
        btnLogIn.setOnClickListener(v -> {

            dao.resetA();

            startActivity(new Intent(getContext(), Login_Activity.class));
        });

        btnRegister.setOnClickListener(v -> {

            dao.resetA();

            startActivity(new Intent(getContext(), Register_Activity.class));
        });

        btnLogOut.setOnClickListener(v -> {

            dao.resetA();
            startActivity(new Intent(getContext(), Login_Activity.class));
        });

        btnOrder.setOnClickListener(v -> mainActivity.replaceFragmentOrder());

        btnDish.setOnClickListener(v -> mainActivity.replaceFragmentDish());

        btnTable.setOnClickListener(v -> mainActivity.replaceFragmentTable());

        btnCart.setOnClickListener(v -> mainActivity.replaceFragmentCart());
        btnSetting.setOnClickListener(v -> mainActivity.replaceFragmentSetting());

        btnHelp.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Vui l??ng li??n h??? : 012345678" + "\n" +
                    "????? ???????c h??? tr??? s???m nh???t c?? th??? ");
            builder.setCancelable(true);
            builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
            builder.show();
        });

    }


    private void setUp() {
        account = new Account();
        account = dao.getAll();
        if (account == null) {
            tvName.setText("T??n kh??ch h??ng");
            tvPhoneNum.setText("S??T: XXX XXX XXXX");
            tvEmail.setText("user@gmail.com");
            imgImage.setImageResource(R.drawable.img_ganuong);
            btnSetting.setVisibility(View.INVISIBLE);
        } else {
            switch (account.getPermission()) {
                case "admin":
                    tvName.setText(account.getName());
                    tvPhoneNum.setText("S??T: " + account.getPhoneNum());
                    tvEmail.setText(account.getEmail());
                    Glide.with(requireContext()).load(account.getImage()).into(imgImage);
                    btnSetting.setVisibility(View.VISIBLE);
                    break;
                case "staff":
                case "customer":
                    tvName.setText(account.getName());
                    tvPhoneNum.setText("S??T: " + account.getPhoneNum());
                    tvEmail.setText(account.getEmail());
                    Glide.with(requireContext()).load(account.getImage()).into(imgImage);
                    btnSetting.setVisibility(View.INVISIBLE);
                    break;
                default:
                    tvName.setText("T??n kh??ch h??ng");
                    tvPhoneNum.setText("S??T: XXX XXX XXXX");
                    tvEmail.setText("user@gmail.com");
                    imgImage.setImageResource(R.drawable.img_ganuong);
                    btnSetting.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }


}
