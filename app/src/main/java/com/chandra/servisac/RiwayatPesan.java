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
import com.chandra.servisac.Adapter.ListArrayPesan;
import com.chandra.servisac.Model.ModelDataPesan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiwayatPesan extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ArrayList<ModelDataPesan> dataservis = new ArrayList<ModelDataPesan>();
    ListView listview;
    ListArrayPesan adapter;

    LinearLayout layout_loading;
    TextView text_load;
    ImageView icon_load;
    String username, idnya;
    Intent intent;
    SharedPreferences sharedpreferences;

    public static final String TAG_USERNAME = "username";
    public static final String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_pesan);

        layout_loading = (LinearLayout) findViewById(R.id.layout_loading);

        text_load = (TextView) findViewById(R.id.text_load);
        icon_load = (ImageView) findViewById(R.id.icon_load);

        sharedpreferences = getSharedPreferences(LoginTeknisi.my_shared_preferences, Context.MODE_PRIVATE);
        idnya = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);

        listview = (ListView) findViewById(R.id.listPesan);
        listview.setOnItemClickListener(RiwayatPesan.this);
        listview.setDividerHeight(0);
        setup();
    }

    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.URLpemesanan)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelDataPesan>> call = service.getSemuaPesan();
        call.enqueue(new Callback<List<ModelDataPesan>>() {
            @Override
            public void onResponse(Call<List<ModelDataPesan>> call, Response<List<ModelDataPesan>> response) {

                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {
                            ModelDataPesan data = new ModelDataPesan(
                                    response.body().get(i).getKode_pesanan(),
                                    response.body().get(i).getMerk_ac(),
                                    response.body().get(i).getKerusakan(),
                                    response.body().get(i).getStatus_servis(),
                                    response.body().get(i).getHp_konsumen(),
                                    response.body().get(i).getPemilik(),
                                    response.body().get(i).getAlamat(),
                                    response.body().get(i).getTgl_servis());
                            dataservis.add(data);
                            Log.d("RESPON", "onResponse: " + response.body().get(i).getKode_pesanan());
                    }
                    listview.setVisibility(View.VISIBLE);
                    adapter = new ListArrayPesan(RiwayatPesan.this, R.layout.row_pesan, dataservis);
                    listview.setAdapter(adapter);

                    if (adapter.getCount() < 1 ) {
                        layout_loading.setVisibility(View.VISIBLE);
                        String error = "Belum ada pemesanan servis di aplikasi ini";
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
            public void onFailure(Call<List<ModelDataPesan>> call, Throwable t) {
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
        Intent intent = new Intent(RiwayatPesan.this,TerimaPesan.class);
        String no = dataservis.get(posi).getKode_pesanan();
        //TextView n = (TextView) findViewById(R.id.listNoServis);
        intent.putExtra(ModelDataPesan.kd_psnn, no);
        intent.putExtra(TAG_USERNAME, username);
        intent.putExtra(TAG_ID, idnya);
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
