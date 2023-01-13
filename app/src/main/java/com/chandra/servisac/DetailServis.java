package com.chandra.servisac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chandra.servisac.API.ApiService;
import com.chandra.servisac.Model.ModelDataServis;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailServis extends AppCompatActivity {

    String NO;
    TextView lRusak, lNoServ, lNoTek, lNamaTek, lMerkAC, lSparepart, lHrg, lTgl;
    Button lbtn_bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_riwayatdetail);

        NO = getIntent().getStringExtra(ModelDataServis.no_srvs);

        lRusak = (TextView) findViewById(R.id.listRusakServis2);
        lNoServ = (TextView) findViewById(R.id.listNoServis2);
        lNoTek = (TextView) findViewById(R.id.listNoTeknisi);
        lNamaTek = (TextView) findViewById(R.id.listNamaTeknisi2);
        lMerkAC = (TextView) findViewById(R.id.listMerkAC);
        lSparepart = (TextView) findViewById(R.id.listSparepart);
        lHrg = (TextView) findViewById(R.id.listHargaSpare);
        lTgl = (TextView) findViewById(R.id.listTglServis2);
        lbtn_bayar = (Button) findViewById(R.id.btn_bayar);

        lbtn_bayar.setVisibility(View.GONE);
        binData();
    }

    public void binData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.URLservis)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelDataServis>> call = service.getSingleData(NO);
        call.enqueue(new Callback<List<ModelDataServis>>() {
            @Override
            public void onResponse(Call<List<ModelDataServis>> call, Response<List<ModelDataServis>> response) {

                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        lNoServ.setText(response.body().get(i).getNo_servis());
                        lRusak.setText("Kerusakan    : "+response.body().get(i).getKerusakan());
                        lMerkAC.setText("Merk AC      : "+response.body().get(i).getMerk_ac());
                        lNamaTek.setText(response.body().get(i).getNama_teknisi());
                        lSparepart.setText("Sparepart    : "+response.body().get(i).getNama_sparepart());
                        lNoTek.setText("No Teknisi   : "+response.body().get(i).getHp_teknisi());
                        lHrg.setText(response.body().get(i).getHarga());
                        lTgl.setText(response.body().get(i).getTanggal());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<ModelDataServis>> call, Throwable t) {

            }
        });


    }

}
