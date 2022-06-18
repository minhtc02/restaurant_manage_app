package com.example.restaurant_manager_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.restaurant_manager_app.Fragment.ViewStaffFragment;
import com.example.restaurant_manager_app.Model.Account;
import com.example.restaurant_manager_app.R;

import java.util.ArrayList;

public class StaffAdapter extends ArrayAdapter<Account> implements Filterable {
    private final Context context;
    private final ArrayList<Account> mListStaff;
    ViewStaffFragment fragment;


    public StaffAdapter(Context context, ViewStaffFragment fragment, ArrayList<Account> mListStaff) {
        super(context, 0,mListStaff );
        this.context = context;
        this.mListStaff = mListStaff;
        this.fragment  = fragment;
    }


    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_staff, null);
        }
        if (mListStaff.size() > 0) {
            Account account = mListStaff.get(position);
            TextView tvId = convertView.findViewById(R.id.tvId);
            TextView tvName = convertView.findViewById(R.id.tvName);
            TextView tvPhoneNum = convertView.findViewById(R.id.tvPhoneNum);
            TextView tvEmail = convertView.findViewById(R.id.tvEmail);
            TextView tvUsername = convertView.findViewById(R.id.tvUsername);
            TextView tvPassword = convertView.findViewById(R.id.tvPassword);
            ImageView imgStaff = convertView.findViewById(R.id.imgStaff);
            Button btnDelete = convertView.findViewById(R.id.btnDelete);
            Button btnUpdate = convertView.findViewById(R.id.btnUpdate);

            tvId.setText("ID: "+account.getId());
            tvName.setText(account.getName());
            tvPhoneNum.setText(account.getPhoneNum());
            tvEmail.setText(account.getEmail());
            tvUsername.setText(account.getUsername());
            tvPassword.setText(account.getPassword());
            Glide.with(context).load(account.getImage()).into(imgStaff);

            btnDelete.setOnClickListener(v -> fragment.deleteR(account.getId()));
            btnUpdate.setOnClickListener(v -> fragment.updateR(account));
        }

        return convertView;

    }
}
