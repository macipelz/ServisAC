package com.chandra.servisac.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chandra.servisac.Model.ModelDataServis;
import com.chandra.servisac.R;

import java.util.ArrayList;

public class ListArrayAdapter extends ArrayAdapter<ModelDataServis> {

    private ArrayList<ModelDataServis> list;
    private LayoutInflater inflater;
    private int res;

    public ListArrayAdapter(Context context, int resource, ArrayList<ModelDataServis> list) {
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

            holder.No = (TextView) convertView.findViewById(R.id.listNoServis);
            holder.Rusak = (TextView) convertView.findViewById(R.id.listRusakServis);
            holder.Nama = (TextView) convertView.findViewById(R.id.listNamaTeknisi);
            holder.Tggl = (TextView) convertView.findViewById(R.id.listTglServis);

            convertView.setTag(holder);

        } else {

            holder = (MyHolder) convertView.getTag();
        }

        holder.No.setText(list.get(position).getNo_servis());
        holder.Nama.setText("Nama Teknisi : "+list.get(position).getNama_teknisi());
        holder.Rusak.setText("Kerusakan : "+list.get(position).getKerusakan());
        holder.Tggl.setText("Tanggal Servis : "+list.get(position).getTanggal());

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
    public void remove(ModelDataServis object) {
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
        TextView Nama;
        TextView Rusak;
        TextView Tggl;
    }
}
