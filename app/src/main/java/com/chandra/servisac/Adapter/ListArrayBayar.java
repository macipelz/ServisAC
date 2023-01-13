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

public class ListArrayBayar extends ArrayAdapter<ModelDataPembayaran> {

    private ArrayList<ModelDataPembayaran> list;
    private LayoutInflater inflater;
    private int res;

    public ListArrayBayar(Context context, int resource, ArrayList<ModelDataPembayaran> list) {
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

            holder.No = (TextView) convertView.findViewById(R.id.listNoBayar);
            holder.Rusak = (TextView) convertView.findViewById(R.id.listRusakBayar);
            holder.Total = (TextView) convertView.findViewById(R.id.listBiaya);
            holder.Tggl = (TextView) convertView.findViewById(R.id.listTglBayar);

            convertView.setTag(holder);

        } else {

            holder = (MyHolder) convertView.getTag();
        }

        holder.No.setText(list.get(position).getNo_pembayaran());
        holder.Total.setText("Biaya Servis : "+list.get(position).getTotal());
        holder.Rusak.setText("Kerusakan : "+list.get(position).getKerusakannya());
        holder.Tggl.setText("Tanggal Servis : "+list.get(position).getTgl());

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
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
    }
}
