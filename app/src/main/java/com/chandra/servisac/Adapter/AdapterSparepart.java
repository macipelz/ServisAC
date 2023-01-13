package com.chandra.servisac.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chandra.servisac.R;
import com.chandra.servisac.Model.DataSparepart;

import java.util.List;

public class AdapterSparepart extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataSparepart> items;

    public AdapterSparepart(Activity activity, List<DataSparepart> items) {
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
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView no_sparepart = (TextView) convertView.findViewById(R.id.no_sparepart);
        TextView nama_sparepart = (TextView) convertView.findViewById(R.id.nama_sparepart);
        TextView harga = (TextView) convertView.findViewById(R.id.harga);
        TextView stok = (TextView) convertView.findViewById(R.id.stok);

        DataSparepart data = items.get(position);

        no_sparepart.setText(data.getNo_sparepart());
        nama_sparepart.setText(data.getNama_sparepart());
        harga.setText(data.getHarga());
        stok.setText(data.getStok());

        return convertView;
    }

}