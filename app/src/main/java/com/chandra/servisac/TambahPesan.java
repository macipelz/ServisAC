package com.chandra.servisac;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.chandra.servisac.App.AppController;

public class TambahPesan extends AppCompatActivity {

    ProgressDialog pDialog;
    EditText txt_kode_pesanan, txt_merk_ac, txt_kerusakan, txt_status_servis, txt_hp_konsumen, txt_pemilik, txt_alamat, txt_tgl_servis;
    Button btn_simpan, btn_batal;
    String kode_pesanan, merk_ac, kerusakan, status_servis, hp_konsumen, pemilik, alamat, tgl_servis;
    String id, username, almt;
    SharedPreferences sharedpreferences;
    Intent intent;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URLpesan + "insert.php";

    private static final String TAG = Register.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_ALAMAT = "alamat";
    public static final int notifikasi = 1;

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pesan);

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
        txt_status_servis = (EditText) findViewById(R.id.txt_status_servis);
        txt_hp_konsumen = (EditText) findViewById(R.id.txt_hp_konsumen);
        txt_pemilik = (EditText) findViewById(R.id.txt_pemilik);
        txt_alamat = (EditText) findViewById(R.id.txt_alamat);
        txt_tgl_servis = (EditText) findViewById(R.id.txt_tgl_servis);
        btn_simpan = (Button) findViewById(R.id.btn_simpan);
        btn_batal = (Button) findViewById(R.id.btn_batal);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        almt = getIntent().getStringExtra(TAG_ALAMAT);
        username = getIntent().getStringExtra(TAG_USERNAME);

        txt_pemilik.setText(id);
        txt_hp_konsumen.setText(username);
        txt_alamat.setText(almt);

        btn_batal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(TambahPesan.this, Pesan.class);
                intent.putExtra(TAG_USERNAME, username);
                startActivity(intent);
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String kode_pesanan = txt_kode_pesanan.getText().toString();
                String merk_ac = txt_merk_ac.getText().toString();
                String kerusakan = txt_kerusakan.getText().toString();
                String status_servis = txt_status_servis.getText().toString();
                String hp_konsumen = txt_hp_konsumen.getText().toString();
                String pemilik = txt_pemilik.getText().toString();
                String alamat = txt_alamat.getText().toString();
                String tgl_servis = txt_tgl_servis.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    Intent intent = new Intent(getApplicationContext(), TambahPesan.class);
                    tampilNotifikasi("Pesanan Servis Baru", merk_ac+" "+kerusakan+" dari "+hp_konsumen);
                    checkInsert(kode_pesanan, merk_ac, kerusakan, status_servis, hp_konsumen, pemilik, alamat, tgl_servis);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkInsert(final String kode_pesanan, final String merk_ac, final String kerusakan, final String status_servis, final String hp_konsumen, final String pemilik, final String alamat, final String tgl_servis) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Sedang melakukan pemesanan ...");
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

                        Log.e("Berhasil Memesan!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        txt_kode_pesanan.setText(null);
                        txt_merk_ac.setText(null);
                        txt_kerusakan.setText(null);
                        txt_status_servis.setText(null);
                        //txt_hp_konsumen.setText(null);
                        //txt_pemilik.setText(null);
                        //txt_alamat.setText(null);
                        txt_tgl_servis.setText(null);

                        Intent intent = new Intent(TambahPesan.this, Pesan.class);
                        intent.putExtra(TAG_USERNAME, username);
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
                params.put("kode_pesanan", kode_pesanan);
                params.put("merk_ac", merk_ac);
                params.put("kerusakan", kerusakan);
                params.put("status_servis", status_servis);
                params.put("hp_konsumen", hp_konsumen);
                params.put("pemilik", pemilik);
                params.put("alamat", alamat);
                params.put("tgl_servis", tgl_servis);

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

    private void tampilNotifikasi(String s, String s1) {
        // membuat komponen pending intent
        //PendingIntent pendingIntent = PendingIntent.getActivity(TambahPesan.this
        //        , notifikasi, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // membuat komponen notifikasi
        NotificationCompat.Builder builder = new NotificationCompat.Builder(TambahPesan.this);
        Notification notification;
        notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                //.setContentIntent(pendingIntent)
                .setContentTitle(s)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(TambahPesan.this.getResources()
                        , R.mipmap.ic_launcher))
                .setContentText(s1)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) TambahPesan.this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifikasi, notification);
    }
}
