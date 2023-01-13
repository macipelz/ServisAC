package com.chandra.servisac;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chandra.servisac.API.ApiService;
import com.chandra.servisac.Model.ModelDataPesan;

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

public class TerimaPesan extends AppCompatActivity {

    String NO;
    EditText txt_kode_pesanan, txt_merk_ac, txt_kerusakan, txt_status_servis, txt_hp_konsumen, txt_pemilik, txt_alamat, txt_tgl_servis;
    TextView TextView20;
    Button btn_simpan, btn_batal;
    String username, id, kodex, merkx, rusakx, hpx;
    SharedPreferences sharedpreferences;
    Intent intent;

    private String url = Server.URLpemesanan + "edit.php";

    public static final String TAG_USERNAME = "username";
    public static final String TAG_ID = "id";
    public static final String TAG_KODE = "kodex";
    public static final String TAG_MERK = "merkx";
    public static final String TAG_RUSAK = "rusakx";
    public static final String TAG_HP = "hpx";
    public static final int notifikasi = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terima_pesanan);

        NO = getIntent().getStringExtra(ModelDataPesan.kd_psnn);

        txt_kode_pesanan = (EditText) findViewById(R.id.txt_kode_pesanan);
        txt_merk_ac = (EditText) findViewById(R.id.txt_merk_ac);
        txt_kerusakan = (EditText) findViewById(R.id.txt_kerusakan);
        txt_status_servis = (EditText) findViewById(R.id.txt_status_servis);
        txt_hp_konsumen = (EditText) findViewById(R.id.txt_hp_konsumen);
        txt_pemilik = (EditText) findViewById(R.id.txt_pemilik);
        txt_alamat = (EditText) findViewById(R.id.txt_alamat);
        txt_tgl_servis = (EditText) findViewById(R.id.txt_tgl_servis);
        TextView20 = (TextView) findViewById(R.id.TextView20);
        btn_simpan = (Button) findViewById(R.id.btn_simpan);
        btn_batal = (Button) findViewById(R.id.btn_batal);

        sharedpreferences = getSharedPreferences(LoginTeknisi.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);
        id = getIntent().getStringExtra(TAG_ID);

        binData();

        TextView20.setText(username);

        btn_batal.setOnClickListener(new View.OnClickListener() {
            // TODO Auto-generated method stub
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            // TODO Auto-generated method stub
            @Override
            public void onClick(View v) {
                EditP();
            }
        });
    }

    public void binData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.URLpemesanan)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelDataPesan>> call = service.getSinglePesan(NO);
        call.enqueue(new Callback<List<ModelDataPesan>>() {
            @Override
            public void onResponse(Call<List<ModelDataPesan>> call, Response<List<ModelDataPesan>> response) {

                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        txt_kode_pesanan.setText(response.body().get(i).getKode_pesanan());
                        txt_merk_ac.setText(response.body().get(i).getMerk_ac());
                        txt_kerusakan.setText(response.body().get(i).getKerusakan());
                        txt_status_servis.setText(response.body().get(i).getStatus_servis());
                        txt_hp_konsumen.setText(response.body().get(i).getHp_konsumen());
                        txt_pemilik.setText(response.body().get(i).getPemilik());
                        txt_alamat.setText(response.body().get(i).getAlamat());
                        txt_tgl_servis.setText(response.body().get(i).getTgl_servis());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<ModelDataPesan>> call, Throwable t) {

            }
        });


    }

    public void EditP() {
        final String kode = NO;
        final String merk = this.txt_merk_ac.getText().toString().trim();
        final String rusak = this.txt_kerusakan.getText().toString().trim();
        final String status = this.txt_status_servis.getText().toString().trim();
        final String hp = this.txt_hp_konsumen.getText().toString().trim();
        final String nama = this.txt_pemilik.getText().toString().trim();
        final String alamat = this.txt_alamat.getText().toString().trim();
        final String tgl = this.txt_tgl_servis.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Intent intent = new Intent(TerimaPesan.this, TambahServis.class);
                                intent.putExtra(TAG_USERNAME, username);
                                kodex = NO;
                                merkx = txt_merk_ac.getText().toString().trim();
                                rusakx = txt_kerusakan.getText().toString().trim();
                                hpx = txt_hp_konsumen.getText().toString().trim();
                                intent.putExtra(TAG_KODE, kodex);
                                intent.putExtra(TAG_ID, id);
                                intent.putExtra(TAG_MERK, merkx);
                                intent.putExtra(TAG_RUSAK, rusakx);
                                intent.putExtra(TAG_HP, hpx);
                                tampilNotifikasi("Pesananan Anda diterima", " Oleh Teknisi "+id+" / "+username);
                                startActivity(intent);
                                Toast.makeText(TerimaPesan.this,"Pesanan Diterima!!",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(TerimaPesan.this,"Error "+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TerimaPesan.this,"Error "+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("kode",kode);
                params.put("merk",merk);
                params.put("rusak",rusak);
                params.put("status",status);
                params.put("hp",hp);
                params.put("nama",nama);
                params.put("alamat",alamat);
                params.put("tgl",tgl);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void tampilNotifikasi(String s, String s1) {
        // membuat komponen pending intent
        //PendingIntent pendingIntent = PendingIntent.getActivity(TambahPesan.this
        //        , notifikasi, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // membuat komponen notifikasi
        NotificationCompat.Builder builder = new NotificationCompat.Builder(TerimaPesan.this);
        Notification notification;
        notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                //.setContentIntent(pendingIntent)
                .setContentTitle(s)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(TerimaPesan.this.getResources()
                        , R.mipmap.ic_launcher))
                .setContentText(s1)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) TerimaPesan.this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifikasi, notification);
    }
}
