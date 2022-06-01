package com.example.restaurant_manager_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.restaurant_manager_app.Model.Table;
import com.example.restaurant_manager_app.R;

import java.util.ArrayList;

public class TableAdapter extends ArrayAdapter<Table> implements Filterable {
    private final Context context;
    private final ArrayList<Table> mListTable;

    public TableAdapter(Context context, int resource, ArrayList<Table> mListTable) {
        super(context, resource, mListTable);
        this.context = context;
        this.mListTable = mListTable;
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

            tvName.setText(table.getName());
            tvFloor.setText(table.getFloor());
            tvStatus.setText(table.getStatus());
        }

        return convertView;

    }
}
