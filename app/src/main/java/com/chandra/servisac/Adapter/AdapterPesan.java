package com.chandra.servisac.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.chandra.servisac.Model.DataPesan;
import com.chandra.servisac.R;

public class AdapterPesan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataPesan> items;

    public AdapterPesan(Activity activity, List<DataPesan> items) {
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
            convertView = inflater.inflate(R.layout.list_pesan, null);

        TextView hp_konsumen = (TextView) convertView.findViewById(R.id.hp_konsumen);
        TextView kode_pesanan = (TextView) convertView.findViewById(R.id.kode_pesanan);
        TextView merk_ac = (TextView) convertView.findViewById(R.id.merk_ac);
        TextView kerusakan = (TextView) convertView.findViewById(R.id.kerusakan);
        TextView status_servis = (TextView) convertView.findViewById(R.id.status_servis);
        TextView pemilik = (TextView) convertView.findViewById(R.id.pemilik);
        TextView alamat = (TextView) convertView.findViewById(R.id.alamat);
        TextView tgl_servis = (TextView) convertView.findViewById(R.id.tgl_servis);

        DataPesan data = items.get(position);

        kode_pesanan.setText(data.getKode_pesanan());
        merk_ac.setText(data.getMerk_ac());
        kerusakan.setText(data.getKerusakan());
        status_servis.setText(data.getStatus_servis());
        hp_konsumen.setText(data.getHp_konsumen());
        pemilik.setText(data.getPemilik());
        alamat.setText(data.getAlamat());
        tgl_servis.setText(data.getTgl_servis());

        return convertView;
    }

}
