package com.chandra.servisac.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chandra.servisac.Model.ModelDataPembayaran;
import com.chandra.servisac.R;

import java.util.ArrayList;

public class ListBayarDetail extends ArrayAdapter<ModelDataPembayaran> {

    private ArrayList<ModelDataPembayaran> list;
    private LayoutInflater inflater;
    private int res;

    public ListBayarDetail(Context context, int resource, ArrayList<ModelDataPembayaran> list) {
        super(context, resource, list);
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        MyHolder holder = null;


        if (convertView == null) {

            convertView = inflater.inflate(res, parent, false);

            holder = new MyHolder();

            holder.No = (TextView) convertView.findViewById(R.id.listNoBayar2);
            holder.Rusak = (TextView) convertView.findViewById(R.id.listRusakServis2);
            holder.Total = (TextView) convertView.findViewById(R.id.listTotal2);
            holder.Tggl = (TextView) convertView.findViewById(R.id.listTglServis2);
            holder.Biaya = (TextView) convertView.findViewById(R.id.listBiaya2);
            holder.Harga = (TextView) convertView.findViewById(R.id.listHarga2);
            holder.Spt = (TextView) convertView.findViewById(R.id.listSpare2);

            convertView.setTag(holder);

        } else {

            holder = (MyHolder) convertView.getTag();
        }

        holder.No.setText   ("No Pembayaran   : "+list.get(position).getNo_pembayaran());
        holder.Total.setText("Total Pembayaran : "+list.get(position).getTotal());
        holder.Rusak.setText("Kerusakannya    : "+list.get(position).getKerusakannya());
        holder.Tggl.setText ("Tanggal Servis  : "+list.get(position).getTgl());
        holder.Biaya.setText("Biaya Perbaikan : "+list.get(position).getBiaya());
        holder.Spt.setText  ("Nama Sparepart  : "+list.get(position).getNama_sparepart());
        holder.Harga.setText("Harga Sparepart : "+list.get(position).getHarga());

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void remove(ModelDataPembayaran object) {
        super.remove(object);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    static class MyHolder {
        TextView No;
        TextView Total;
        TextView Rusak;
        TextView Tggl;
        TextView Biaya;
        TextView Harga;
        TextView Spt;
    }
}
