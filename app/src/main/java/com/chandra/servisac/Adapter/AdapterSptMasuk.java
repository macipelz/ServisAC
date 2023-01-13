package com.chandra.servisac.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chandra.servisac.R;
import com.chandra.servisac.Model.DataSptMasuk;

import java.util.List;

public class AdapterSptMasuk extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataSptMasuk> items;

    public AdapterSptMasuk(Activity activity, List<DataSptMasuk> items) {
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
            convertView = inflater.inflate(R.layout.list_row_sptmasuk, null);

        TextView no_masuk = (TextView) convertView.findViewById(R.id.no_masuk);
        TextView no_sparepart = (TextView) convertView.findViewById(R.id.no_sparepart);
        TextView nama_sparepart = (TextView) convertView.findViewById(R.id.nama_sparepart);
        TextView jumlah = (TextView) convertView.findViewById(R.id.jumlah);
        TextView tgl = (TextView) convertView.findViewById(R.id.tgl);

        DataSptMasuk data = items.get(position);

        no_masuk.setText(data.getNo_masuk());
        no_sparepart.setText(data.getNo_sparepart());
        nama_sparepart.setText(data.getNama_sparepart());
        jumlah.setText(data.getJumlah());
        tgl.setText(data.getTgl());

        return convertView;
    }

}