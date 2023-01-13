package com.chandra.servisac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chandra.servisac.API.ApiService;
import com.chandra.servisac.Adapter.ListArrayBayar;
import com.chandra.servisac.Model.ModelDataPembayaran;
import com.chandra.servisac.Model.ModelDataServis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiwayatBayar extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ArrayList<ModelDataPembayaran> dataservis = new ArrayList<ModelDataPembayaran>();
    ListView listview;
    ListArrayBayar adapter;

    LinearLayout layout_loading;
    TextView text_load;
    ImageView icon_load;
    String username, filter;
    Intent intent;
    SharedPreferences sharedpreferences;

    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_bayar);

        layout_loading = (LinearLayout) findViewById(R.id.layout_loading);

        text_load = (TextView) findViewById(R.id.text_load);
        icon_load = (ImageView) findViewById(R.id.icon_load);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);

        listview = (ListView) findViewById(R.id.listServis);
        listview.setOnItemClickListener(RiwayatBayar.this);
        listview.setDividerHeight(0);
        setup();

    }

    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.URLbayar)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelDataPembayaran>> call = service.getSemuaBayar();
        call.enqueue(new Callback<List<ModelDataPembayaran>>() {
            @Override
            public void onResponse(Call<List<ModelDataPembayaran>> call, Response<List<ModelDataPembayaran>> response) {

                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {
                        if (response.body().get(i).getHp_konsumen().equals(username)) {
                            ModelDataPembayaran data = new ModelDataPembayaran(
                                    response.body().get(i).getNo_pembayaran(),
                                    response.body().get(i).getHarga(),
                                    response.body().get(i).getBiaya(),
                                    response.body().get(i).getMerk_acnya(),
                                    response.body().get(i).getTotal(),
                                    response.body().get(i).getKerusakannya(),
                                    response.body().get(i).getNama_teknisi(),
                                    response.body().get(i).getHp_konsumen(),
                                    response.body().get(i).getNama_sparepart(),
                                    response.body().get(i).getTgl());
                            dataservis.add(data);
                            Log.d("RESPON", "onResponse: " + response.body().get(i).getNo_pembayaran());
                        }
                    }
                    listview.setVisibility(View.VISIBLE);
                    adapter = new ListArrayBayar(RiwayatBayar.this, R.layout.row_bayar, dataservis);
                    listview.setAdapter(adapter);

                    if (adapter.getCount() < 1 ) {
                        layout_loading.setVisibility(View.VISIBLE);
                        String error = "Anda Belum pernah memperbaiki AC melalui layanan aplikasi ini";
                        text_load.setText(error);
                        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_data_kosong);
                        icon_load.setImageBitmap(icon);
                    } else {
                        layout_loading.setVisibility(View.GONE);
                    }
                } else {
                    String error = "Gagal mendapatkan data dari server !!!";
                    text_load.setText(error);
                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_network);
                    icon_load.setImageBitmap(icon);

                }

            }

            @Override
            public void onFailure(Call<List<ModelDataPembayaran>> call, Throwable t) {
                String error = "Gagal mendapatkan data dari server !!!\n" + t.getMessage();
                text_load.setText(error);
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_network);
                icon_load.setImageBitmap(icon);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        int posi = (int) adapter.getItemId(position);
        Intent intent = new Intent(RiwayatBayar.this,DetailBayar.class);
        String no = dataservis.get(posi).getNo_pembayaran();
        //TextView n = (TextView) findViewById(R.id.listNoServis);
        intent.putExtra(ModelDataPembayaran.no_byar, no);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            adapter.clear();
            setup();
        }
    }
}
