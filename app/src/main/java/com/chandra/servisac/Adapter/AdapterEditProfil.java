package com.chandra.servisac.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.chandra.servisac.Model.DataEditProfil;
import com.chandra.servisac.R;

public class AdapterEditProfil extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataEditProfil> items;

    public AdapterEditProfil(Activity activity, List<DataEditProfil> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_edit_profil, null);

        TextView hp_konsumen = (TextView) convertView.findViewById(R.id.txt_username);
        TextView nama = (TextView) convertView.findViewById(R.id.txt_nama);
        TextView alamat = (TextView) convertView.findViewById(R.id.txt_alamat);
        TextView password = (TextView) convertView.findViewById(R.id.txt_password);

        DataEditProfil data = items.get(position);

        hp_konsumen.setText(data.getHp_konsumen());
        nama.setText(data.getNama());
        alamat.setText(data.getAlamat());
        password.setText(data.getPassword());

        return convertView;
    }

}
