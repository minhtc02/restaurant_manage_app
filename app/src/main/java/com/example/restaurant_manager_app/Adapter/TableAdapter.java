package com.example.restaurant_manager_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.restaurant_manager_app.Database.AccountDAO;
import com.example.restaurant_manager_app.Fragment.TableFragment;
import com.example.restaurant_manager_app.Model.Account;
import com.example.restaurant_manager_app.Model.Table;
import com.example.restaurant_manager_app.R;

import java.util.ArrayList;

public class TableAdapter extends ArrayAdapter<Table> implements Filterable {
    private final Context context;
    private final ArrayList<Table> mListTable;
    AccountDAO dao;
    Account account;
    TableFragment fragment;

    public TableAdapter(Context context, TableFragment fragment, ArrayList<Table> mListTable) {
        super(context, 0, mListTable);
        this.context = context;
        this.mListTable = mListTable;
        this.fragment = fragment;
    }


    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_table, null);
        }
        if (mListTable.size() > 0) {
            Table table = mListTable.get(position);
            TextView tvName = convertView.findViewById(R.id.tvName);
            TextView tvFloor = convertView.findViewById(R.id.tvFloor);
            TextView tvStatus = convertView.findViewById(R.id.tvStatus);
            Button btnOn = convertView.findViewById(R.id.btnOn);
            Button btnOff = convertView.findViewById(R.id.btnOff);
            Button btnDelete = convertView.findViewById(R.id.btnDelete);
            Button btnUpdate = convertView.findViewById(R.id.btnUpdate);

            tvName.setText(table.getName());
            tvFloor.setText(table.getFloor());
            tvStatus.setText(table.getStatus());

            btnOn.setOnClickListener(v -> fragment.setOff(table.getId()));
            btnOff.setOnClickListener(v -> fragment.setOn(table.getId()));
            btnDelete.setOnClickListener(v -> fragment.deleteR(table.getId()));
            btnUpdate.setOnClickListener(v -> fragment.updateR(table));


            dao = new AccountDAO(getContext());
            account = dao.getAll();
            if (account == null) {
                btnOn.setVisibility(View.INVISIBLE);
                btnOff.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
                btnUpdate.setVisibility(View.INVISIBLE);
            } else {
                if (account.getPermission().equals("admin")) {
                    if (table.getStatus().equals("Trống")) {
                        btnOn.setVisibility(View.VISIBLE);
                        btnOff.setVisibility(View.INVISIBLE);
                    }
                    if (table.getStatus().equals("Đã đặt")) {
                        btnOn.setVisibility(View.INVISIBLE);
                        btnOff.setVisibility(View.VISIBLE);
                    }


                    btnDelete.setVisibility(View.VISIBLE);
                    btnUpdate.setVisibility(View.VISIBLE);
                } else if (account.getPermission().equals("staff")) {
                    if (table.getStatus().equals("Trống")) {
                        btnOn.setVisibility(View.VISIBLE);
                        btnOff.setVisibility(View.INVISIBLE);
                    }
                    if (table.getStatus().equals("Đã đặt")) {
                        btnOn.setVisibility(View.INVISIBLE);
                        btnOff.setVisibility(View.VISIBLE);
                    }

                    btnDelete.setVisibility(View.INVISIBLE);
                    btnUpdate.setVisibility(View.INVISIBLE);
                } else {
                    btnOn.setVisibility(View.INVISIBLE);
                    btnOff.setVisibility(View.INVISIBLE);
                    btnDelete.setVisibility(View.INVISIBLE);
                    btnUpdate.setVisibility(View.INVISIBLE);
                }

            }

        }

        return convertView;

    }
}
