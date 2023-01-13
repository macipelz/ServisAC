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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chandra.servisac.API.ApiService;
import com.chandra.servisac.Adapter.ListArrayAdapter;
import com.chandra.servisac.Model.ModelDataServis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiwayatServis extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ArrayList<ModelDataServis> dataservis = new ArrayList<ModelDataServis>();
    ListView listview;
    ListArrayAdapter adapter;

    LinearLayout layout_loading;
    TextView text_load;
    ImageView icon_load;
    String username;
    Intent intent;
    SharedPreferences sharedpreferences;

    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_servis);

        layout_loading = (LinearLayout) findViewById(R.id.layout_loading);

        text_load = (TextView) findViewById(R.id.text_load);
        icon_load = (ImageView) findViewById(R.id.icon_load);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);

        listview = (ListView) findViewById(R.id.listServis);
        listview.setOnItemClickListener(RiwayatServis.this);
        listview.setDividerHeight(0);
        setup();
    }

    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.URLservis)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelDataServis>> call = service.getSemuaData();
        call.enqueue(new Callback<List<ModelDataServis>>() {
            @Override
            public void onResponse(Call<List<ModelDataServis>> call, Response<List<ModelDataServis>> response) {

                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {
                        if (response.body().get(i).getHp_konsumen().equals(username)) {
                            ModelDataServis data = new ModelDataServis(
                                    response.body().get(i).getNo_servis(),
                                    response.body().get(i).getKode_pesanan(),
                                    response.body().get(i).getMerk_ac(),
                                    response.body().get(i).getMerk_acnya(),
                                    response.body().get(i).getKerusakan(),
                                    response.body().get(i).getKerusakannya(),
                                    response.body().get(i).getHp_teknisi(),
                                    response.body().get(i).getNama_teknisi(),
                                    response.body().get(i).getHp_konsumen(),
                                    response.body().get(i).getNama(),
                                    response.body().get(i).getNo_sparepart(),
                                    response.body().get(i).getNama_sparepart(),
                                    response.body().get(i).getHarga(),
                                    response.body().get(i).getTanggal());
                            dataservis.add(data);
                            Log.d("RESPON", "onResponse: " + response.body().get(i).getNo_servis());
                        }
                    }
                    listview.setVisibility(View.VISIBLE);
                    adapter = new ListArrayAdapter(RiwayatServis.this, R.layout.row_riwayat, dataservis);
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
            public void onFailure(Call<List<ModelDataServis>> call, Throwable t) {
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
        Intent intent = new Intent(RiwayatServis.this,DetailServis.class);
        String no = dataservis.get(posi).getNo_servis();
        //TextView n = (TextView) findViewById(R.id.listNoServis);
        intent.putExtra(ModelDataServis.no_srvs, no);
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
