package com.chandra.servisac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.chandra.servisac.API.ApiService;
import com.chandra.servisac.Model.ModelDataPembayaran;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailBayar extends AppCompatActivity {

    String NO;
    TextView lRusakx, lNoByr, lTotal, lBiaya, lHarga, lSpt, lTggl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_bayardetail);

        NO = getIntent().getStringExtra(ModelDataPembayaran.no_byar);

        lRusakx = (TextView) findViewById(R.id.listRusakBayar2);
        lNoByr = (TextView) findViewById(R.id.listNoBayar2);
        lTotal = (TextView) findViewById(R.id.listTotal2);
        lBiaya = (TextView) findViewById(R.id.listBiaya2);
        lHarga = (TextView) findViewById(R.id.listHarga2);
        lSpt = (TextView) findViewById(R.id.listSpare2);
        lTggl = (TextView) findViewById(R.id.listTglBayar2);

        binData();
    }

    public void binData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.URLbayar)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelDataPembayaran>> call = service.getSingleBayar(NO);
        call.enqueue(new Callback<List<ModelDataPembayaran>>() {
            @Override
            public void onResponse(Call<List<ModelDataPembayaran>> call, Response<List<ModelDataPembayaran>> response) {

                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        lNoByr.setText(response.body().get(i).getNo_pembayaran());
                        lRusakx.setText   ("Kerusakan       : "+response.body().get(i).getKerusakannya());
                        lHarga.setText    ("Harga Sparepart : "+response.body().get(i).getHarga());
                        lTotal.setText(response.body().get(i).getTotal());
                        lBiaya.setText    ("Biaya Perbaikan : "+response.body().get(i).getBiaya());
                        lSpt.setText      ("Nama Sparepart  : "+response.body().get(i).getNama_sparepart());
                        lTggl.setText(response.body().get(i).getTgl());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<ModelDataPembayaran>> call, Throwable t) {

            }
        });


    }

}
