package com.chandra.servisac.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chandra.servisac.Model.ModelDataPesan;
import com.chandra.servisac.R;

import java.util.ArrayList;

public class ListArrayPesan extends ArrayAdapter<ModelDataPesan> {

    private ArrayList<ModelDataPesan> list;
    private LayoutInflater inflater;
    private int res;

    public ListArrayPesan(Context context, int resource, ArrayList<ModelDataPesan> list) {
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

            holder.Kd = (TextView) convertView.findViewById(R.id.listKdPesan);
            holder.Rusak = (TextView) convertView.findViewById(R.id.listRusakPesan);
            holder.Nama = (TextView) convertView.findViewById(R.id.listNamaKon);
            holder.Almt = (TextView) convertView.findViewById(R.id.listAlamatKon);
            holder.Hp = (TextView) convertView.findViewById(R.id.listNoKon);

            convertView.setTag(holder);

        } else {

            holder = (MyHolder) convertView.getTag();
        }

        holder.Kd.setText(list.get(position).getKode_pesanan());
        holder.Nama.setText( "Nama      : "+list.get(position).getPemilik());
        holder.Rusak.setText("Kerusakan : "+list.get(position).getKerusakan());
        holder.Almt.setText( "Alamat    : "+list.get(position).getAlamat());
        holder.Hp.setText(list.get(position).getHp_konsumen());

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
    public void remove(ModelDataPesan object) {
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
        TextView Kd;
        TextView Nama;
        TextView Rusak;
        TextView Almt;
        TextView Hp;
    }
}
