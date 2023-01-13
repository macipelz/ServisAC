package com.chandra.servisac;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chandra.servisac.App.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahServis extends AppCompatActivity {

    ProgressDialog pDialog;
    EditText txt_kode_pesanan, txt_merk_ac, txt_kerusakan, txt_no_servis, txt_hp_teknisi, txt_sparepart, txt_hp_konsumen, txt_pemilik, txt_alamat, txt_tgl_servis;
    Button btn_simpan, btn_batal, btn_spt;
    //String kode_pesanan, merk_ac, kerusakan, status_servis, hp_konsumen, pemilik, alamat, tgl_servis;
    String username, id, kodex, merkx, rusakx, hpx, a;
    SharedPreferences sharedpreferences;
    Intent intent;

    private Spinner spNamen2;
    private String[] Sparepart = {
            "Cuci Saja",
            "Tambah Freon",
            "Isi Freon Kosong"
    };

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URLpemesanan + "insert.php";

    private static final String TAG = RegisterTeknisi.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public static final String TAG_USERNAME = "username";
    public static final String TAG_ID = "id";
    public static final String TAG_KODE = "kodex";
    public static final String TAG_MERK = "merkx";
    public static final String TAG_RUSAK = "rusakx";
    public static final String TAG_HP = "hpx";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_servis);

        spNamen2 = (Spinner) findViewById(R.id.sp_name_2);

        // inisialiasi Array Adapter dengan memasukkan string array di atas
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Sparepart);

        // mengeset Array Adapter tersebut ke Spinner
        spNamen2.setAdapter(adapter);

        // mengeset listener untuk mengetahui saat item dipilih
        spNamen2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
                String pilihan = adapter.getItem(i);
                if (pilihan == "Cuci Saja") {
                    a = "1";
                } else if (pilihan == "Tambah Freon") {
                    a = "2";
                } else if (pilihan == "Isi Freon Kosong") {
                    a = "3";
                }
                txt_sparepart.setText(a);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        txt_kode_pesanan = (EditText) findViewById(R.id.txt_kode_pesanan);
        txt_merk_ac = (EditText) findViewById(R.id.txt_merk_ac);
        txt_kerusakan = (EditText) findViewById(R.id.txt_kerusakan);
        txt_no_servis = (EditText) findViewById(R.id.txt_no_servis);
        txt_hp_konsumen = (EditText) findViewById(R.id.txt_hp_konsumen);
        txt_tgl_servis = (EditText) findViewById(R.id.txt_tgl_servis);
        txt_hp_teknisi = (EditText) findViewById(R.id.txt_hp_teknisi);
        txt_sparepart = (EditText) findViewById(R.id.txt_sparepart);
        btn_simpan = (Button) findViewById(R.id.btn_simpan);
        btn_batal = (Button) findViewById(R.id.btn_batal);
        btn_spt = (Button) findViewById(R.id.btn_spt);

        sharedpreferences = getSharedPreferences(LoginTeknisi.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);
        id = getIntent().getStringExtra(TAG_ID);
        kodex = getIntent().getStringExtra(TAG_KODE);
        merkx = getIntent().getStringExtra(TAG_MERK);
        rusakx = getIntent().getStringExtra(TAG_RUSAK);
        hpx = getIntent().getStringExtra(TAG_HP);

        txt_hp_teknisi.setText(username);
        txt_kode_pesanan.setText(kodex);
        txt_merk_ac.setText(merkx);
        txt_kerusakan.setText(rusakx);
        txt_hp_konsumen.setText(hpx);

        btn_spt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(TambahServis.this,Sparepart.class);
                startActivity(intent);
            }
        });

        btn_batal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "AC Tidak Bisa Diperbaiki Lagi",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TambahServis.this, RiwayatServisTeknisi.class);
                intent.putExtra(TAG_USERNAME, username);
                startActivity(intent);
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String kode_pesanan = txt_kode_pesanan.getText().toString();
                String merk_acnya = txt_merk_ac.getText().toString();
                String kerusakannya = txt_kerusakan.getText().toString();
                String no_servis = txt_no_servis.getText().toString();
                String hp_konsumen = txt_hp_konsumen.getText().toString();
                String hp_teknisi = txt_hp_teknisi.getText().toString();
                String no_sparepart = txt_sparepart.getText().toString();
                String tanggal = txt_tgl_servis.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkInsert(no_servis, kode_pesanan, merk_acnya, hp_teknisi, kerusakannya, no_sparepart, hp_konsumen, tanggal);

                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkInsert(final String no_servis, final String kode_pesanan, final String merk_acnya, final String hp_teknisi, final String kerusakannya, final String no_sparepart, final String hp_konsumen, final String tanggal) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Sedang menyimpan ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Insert Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Berhasil Menyimpan!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        txt_kode_pesanan.setText(null);
                        txt_merk_ac.setText(null);
                        txt_kerusakan.setText(null);
                        txt_no_servis.setText(null);
                        //txt_hp_konsumen.setText(null);
                        //txt_pemilik.setText(null);
                        //txt_alamat.setText(null);
                        txt_tgl_servis.setText(null);

                        Intent intent = new Intent(TambahServis.this, RiwayatServisTeknisi.class);
                        intent.putExtra(TAG_USERNAME, username);
                        intent.putExtra(TAG_ID, id);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("no_servis", no_servis);
                params.put("kode_pesanan", kode_pesanan);
                params.put("merk_acnya", merk_acnya);
                params.put("hp_teknisi", hp_teknisi);
                params.put("kerusakannya", kerusakannya);
                params.put("no_sparepart", no_sparepart);
                params.put("hp_konsumen", hp_konsumen);
                params.put("tanggal", tanggal);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
