package com.chandra.servisac;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chandra.servisac.App.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahBayar extends AppCompatActivity {

    ProgressDialog pDialog;
    EditText txt_no_serv, txt_harg, txt_biaya, txt_no_pembayaran, txt_tgl, txt_total;
    Button btn_smpn;
    //String kode_pesanan, merk_ac, kerusakan, status_servis, hp_konsumen, pemilik, alamat, tgl_servis;
    String username, id, kodex, hargax;
    SharedPreferences sharedpreferences;
    Intent intent;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URLbayar + "insert.php";

    private static final String TAG = RegisterTeknisi.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public static final String TAG_USERNAME = "username";
    public static final String TAG_ID = "id";
    public static final String TAG_KODE = "kodex";
    public static final String TAG_HARGA = "hargax";
    public static final int notifikasi = 1;

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_bayar);

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

        txt_no_serv = (EditText) findViewById(R.id.txt_no_serv);
        txt_harg = (EditText) findViewById(R.id.txt_harg);
        txt_biaya = (EditText) findViewById(R.id.txt_biaya);
        txt_no_pembayaran = (EditText) findViewById(R.id.txt_no_pembayaran);
        txt_tgl = (EditText) findViewById(R.id.txt_tgl);
        txt_total = (EditText) findViewById(R.id.txt_total);
        btn_smpn = (Button) findViewById(R.id.btn_smpn);

        sharedpreferences = getSharedPreferences(LoginTeknisi.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);
        id = getIntent().getStringExtra(TAG_ID);

        kodex = getIntent().getStringExtra(TAG_KODE);
        hargax = getIntent().getStringExtra(TAG_HARGA);

        txt_no_serv.setText(kodex);
        txt_harg.setText(hargax);
        txt_biaya.setText("0");

        btn_smpn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String no_servis = txt_no_serv.getText().toString();
                String biaya = txt_biaya.getText().toString();
                Integer vbiaya = Integer.parseInt(txt_biaya.getText().toString());
                Integer vharga = Integer.parseInt(txt_harg.getText().toString());
                String no_pembayaran = txt_no_pembayaran.getText().toString();
                String tgl = txt_tgl.getText().toString();
                Integer vtotal = vbiaya+vharga;
                txt_total.setText(Integer.toString(vtotal));
                String total = txt_total.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkInsert(no_pembayaran, tgl, no_servis, biaya, total);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkInsert(final String no_pembayaran, final String tgl, final String no_servis, final String biaya, final String total) {
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

                        txt_no_serv.setText(null);
                        txt_harg.setText(null);
                        txt_biaya.setText(null);

                        Intent intent = new Intent(TambahBayar.this, RiwayatBayarTeknisi.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(TAG_USERNAME, username);
                        intent.putExtra(TAG_ID, id);
                        startActivity(intent);
                        tampilNotifikasi("Pembayaran Servis", " Sudah dibayar kepada "+id+" / "+username);
                        finish();
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
                params.put("no_pembayaran", no_pembayaran);
                params.put("tgl", tgl);
                params.put("no_servis", no_servis);
                params.put("biaya", biaya);
                params.put("total", total);

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
        //PendingIntent pendingIntent = PendingIntent.getActivity(TambahBayar.this, notifikasi, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // membuat komponen notifikasi
        NotificationCompat.Builder builder = new NotificationCompat.Builder(TambahBayar.this);
        Notification notification;
        notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                //.setContentIntent(pendingIntent)
                .setContentTitle(s)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(TambahBayar.this.getResources()
                        , R.mipmap.ic_launcher))
                .setContentText(s1)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) TambahBayar.this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifikasi, notification);
    }
}
