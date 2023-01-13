package com.chandra.servisac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chandra.servisac.API.ApiService;
import com.chandra.servisac.Model.ModelDataServis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailServisTeknisi extends AppCompatActivity {

    String username, id, NO;
    TextView lRusak, lNoServ, lNoTek, lNamaTek, lMerkAC, lSparepart, lHrg, lTgl;
    Button lbtn_bayar;
    String hargax, kodex, NOX;
    SharedPreferences sharedpreferences;

    private String url = Server.URLservisan + "edit.php";

    public static final String TAG_USERNAME = "username";
    public static final String TAG_ID = "id";
    public static final String TAG_KODE = "kodex";
    public static final String TAG_HARGA = "hargax";

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

        lbtn_bayar.setVisibility(View.VISIBLE);
        binData();

        sharedpreferences = getSharedPreferences(LoginTeknisi.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);
        id = getIntent().getStringExtra(TAG_ID);

        lbtn_bayar.setOnClickListener(new View.OnClickListener() {
            // TODO Auto-generated method stub
            @Override
            public void onClick(View v) {
                EditP();
            }
        });
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
                        NOX = response.body().get(i).getKode_pesanan();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<ModelDataServis>> call, Throwable t) {

            }
        });


    }

    public void EditP() {
        final String kode = NOX;
        final String harga = this.lHrg.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Intent intent = new Intent(DetailServisTeknisi.this, TambahBayar.class);
                                kodex = NO;
                                hargax = lHrg.getText().toString().trim();
                                intent.putExtra(TAG_KODE, kodex);
                                intent.putExtra(TAG_HARGA, hargax);
                                intent.putExtra(TAG_USERNAME, username);
                                intent.putExtra(TAG_ID, id);
                                startActivity(intent);
                                Toast.makeText(DetailServisTeknisi.this,"Silakan Input Pembayaran!!",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(DetailServisTeknisi.this,"Error "+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailServisTeknisi.this,"Error "+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("kode",kode);
                params.put("harga",harga);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

