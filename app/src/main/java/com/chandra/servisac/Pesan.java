package com.chandra.servisac;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chandra.servisac.Adapter.AdapterPesan;
import com.chandra.servisac.Model.DataPesan;
import com.chandra.servisac.App.AppController;

import static com.chandra.servisac.Server.URLcari;

public class Pesan extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    ListView list;
    TextView txt_id, txt_error;
    SwipeRefreshLayout swipe;
    List<DataPesan> itemList = new ArrayList<DataPesan>();
    AdapterPesan adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_kode_pesanan, txt_merk_ac, txt_kerusakan, txt_status_servis, txt_hp_konsumen, txt_pemilik, txt_alamat, txt_tgl_servis;
    Button btn_edit, btn_batal;
    String kode_pesanan, merk_ac, kerusakan, status_servis, hp_konsumen, pemilik, alamat, tgl_servis, username;
    Intent intent;
    SharedPreferences sharedpreferences;

    private static final String TAG = Pesan.class.getSimpleName();

    private static String url_select 	 = Server.URLpesan + "select.php";
    private static String url_insert 	 = Server.URLpesan + "insert.php";
    private static String url_edit 	     = Server.URLpesan + "edit.php";
    private static String url_update 	 = Server.URLpesan + "update.php";
    private static String url_delete 	 = Server.URLpesan + "delete.php";

    public static final String TAG_KODE     = "kode_pesanan";
    public static final String TAG_NAMA     = "pemilik";
    public static final String TAG_ALAMAT   = "alamat";
    public static final String TAG_MERK     = "merk_ac";
    public static final String TAG_KERUSAKAN= "kerusakan";
    public static final String TAG_STATUS   = "status_servis";
    public static final String TAG_HP       = "hp_konsumen";
    public static final String TAG_TGL      = "tgl_servis";
    public static final String TAG_VALUE    = "value";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_RESULTS  = "results";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_error = (TextView) findViewById(R.id.txt_error);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);

        txt_id.setText(username);

        // menghubungkan variablel pada layout dan pada java
        fab     = (FloatingActionButton) findViewById(R.id.fab_add);
        swipe   = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) findViewById(R.id.list);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new AdapterPesan(Pesan.this, itemList);
        list.setAdapter(adapter);

        // menamilkan widget refresh
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                           //cariData(username);
                       }
                   }
        );

        // fungsi floating action button memanggil form kontak
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogForm("", "", "", "","","","","","SIMPAN");
            }
        });

        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long kode_pesanan) {
                // TODO Auto-generated method stub
                final String kodex = itemList.get(position).getKode_pesanan();

                final CharSequence[] dialogitem = {"Batalkan Pesanan"};
                dialog = new AlertDialog.Builder(Pesan.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                delete(kodex);
                                break;
                            case 1:
                                delete(kodex);
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });

    }

    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }

    // untuk mengosongi edittext pada form
    private void kosong(){
        txt_kode_pesanan.setText(null);
        txt_merk_ac.setText(null);
        txt_kerusakan.setText(null);
        txt_status_servis.setText(null);
        //txt_hp_konsumen.setText(null);
        //txt_pemilik.setText(null);
        //txt_alamat.setText(null);
        txt_tgl_servis.setText(null);
    }

    // untuk menampilkan dialog from kontak
    private void DialogForm(String kodex, String merkx, String kerusakanx, String statusx, String hpx, String namax, String alamatx, String tglx, String button) {
        dialog = new AlertDialog.Builder(Pesan.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_editpesan, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Form Pemesanan Servis");

        txt_kode_pesanan = (EditText) dialogView.findViewById(R.id.txt_kode_pesanan);
        txt_merk_ac = (EditText) dialogView.findViewById(R.id.txt_merk_ac);
        txt_kerusakan = (EditText) dialogView.findViewById(R.id.txt_kerusakan);
        txt_status_servis = (EditText) dialogView.findViewById(R.id.txt_status_servis);
        txt_hp_konsumen = (EditText) dialogView.findViewById(R.id.txt_hp_konsumen);
        txt_pemilik = (EditText) dialogView.findViewById(R.id.txt_pemilik);
        txt_alamat = (EditText) dialogView.findViewById(R.id.txt_alamat);
        txt_tgl_servis = (EditText) dialogView.findViewById(R.id.txt_tgl_servis);

        if (!kodex.isEmpty()){
            txt_kode_pesanan.setText(kodex);
            txt_merk_ac.setText(merkx);
            txt_kerusakan.setText(kerusakanx);
            txt_status_servis.setText(statusx);
            txt_hp_konsumen.setText(hpx);
            txt_pemilik.setText(namax);
            txt_alamat.setText(alamatx);
            txt_tgl_servis.setText(tglx);
        } else {
            kosong();
        }

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                kode_pesanan = txt_kode_pesanan.getText().toString();
                merk_ac = txt_merk_ac.getText().toString();
                kerusakan = txt_kerusakan.getText().toString();
                status_servis = txt_status_servis.getText().toString();
                hp_konsumen = txt_hp_konsumen.getText().toString();
                pemilik = txt_pemilik.getText().toString();
                alamat = txt_alamat.getText().toString();
                tgl_servis = txt_tgl_servis.getText().toString();

                simpan_update();
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                kosong();
            }
        });

        dialog.show();
    }

    // untuk menampilkan semua data pada listview
    private void callVolley(){
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                            JSONObject obj = response.getJSONObject(i);

                            if (obj.getString(TAG_HP).equals(username)) {
                                DataPesan item = new DataPesan();

                                item.setKode_pesanan(obj.getString(TAG_KODE));
                                item.setMerk_ac(obj.getString(TAG_MERK));
                                item.setKerusakan(obj.getString(TAG_KERUSAKAN));
                                item.setStatus_servis(obj.getString(TAG_STATUS));
                                item.setHp_konsumen(obj.getString(TAG_HP));
                                item.setPemilik(obj.getString(TAG_NAMA));
                                item.setAlamat(obj.getString(TAG_ALAMAT));
                                item.setTgl_servis(obj.getString(TAG_TGL));

                                // menambah item ke array
                                itemList.add(item);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    // fungsi untuk menyimpan atau update
    private void simpan_update() {
        String url;
        // jika id kosong maka simpan, jika id ada nilainya maka update
        if (kode_pesanan.isEmpty()){
            url = url_insert;
        } else {
            url = url_update;
        }

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/update", jObj.toString());

                        callVolley();
                        kosong();

                        Toast.makeText(Pesan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(Pesan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(Pesan.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                if (kode_pesanan.isEmpty()){
                    params.put("merk_ac", merk_ac);
                    params.put("kerusakan", kerusakan);
                    params.put("status_servis", status_servis);
                    params.put("hp_konsumen", hp_konsumen);
                    params.put("pemilik", pemilik);
                    params.put("alamat", alamat);
                    params.put("tgl_servis", tgl_servis);
                } else {
                    params.put("kode_pesanan", kode_pesanan);
                    params.put("merk_ac", merk_ac);
                    params.put("kerusakan", kerusakan);
                    params.put("status_servis", status_servis);
                    params.put("hp_konsumen", hp_konsumen);
                    params.put("pemilik", pemilik);
                    params.put("alamat", alamat);
                    params.put("tgl_servis", tgl_servis);
                }

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // fungsi untuk get edit data
    private void edit(final String kodex){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_edit, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String kodex      = jObj.getString(TAG_KODE);
                        String merkx      = jObj.getString(TAG_MERK);
                        String kerusakanx = jObj.getString(TAG_KERUSAKAN);
                        String statusx    = jObj.getString(TAG_STATUS);
                        String hpx        = jObj.getString(TAG_HP);
                        String namax      = jObj.getString(TAG_NAMA);
                        String alamatx    = jObj.getString(TAG_ALAMAT);
                        String tglx       = jObj.getString(TAG_TGL);

                        DialogForm(kodex, merkx, kerusakanx, statusx, hpx, namax, alamatx, tglx,"EDIT");

                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(Pesan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(Pesan.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("kode_pesanan", kodex);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // fungsi untuk menghapus
    private void delete(final String kodex){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_delete, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("delete", jObj.toString());

                        callVolley();

                        Toast.makeText(Pesan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(Pesan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(Pesan.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("kode_pesanan", kodex);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void cariData(final String keyword) {
        StringRequest strReq = new StringRequest(Request.Method.POST, URLcari, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Response: ", response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);

                    int value = jObj.getInt(TAG_VALUE);

                    if (value == 1) {
                        itemList.clear();
                        adapter.notifyDataSetChanged();

                        String getObject = jObj.getString(TAG_RESULTS);
                        JSONArray jsonArray = new JSONArray(getObject);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            DataPesan item = new DataPesan();

                            item.setKode_pesanan(obj.getString(TAG_KODE));
                            item.setMerk_ac(obj.getString(TAG_MERK));
                            item.setKerusakan(obj.getString(TAG_KERUSAKAN));
                            item.setStatus_servis(obj.getString(TAG_STATUS));
                            item.setHp_konsumen(obj.getString(TAG_HP));
                            item.setPemilik(obj.getString(TAG_NAMA));
                            item.setAlamat(obj.getString(TAG_ALAMAT));
                            item.setTgl_servis(obj.getString(TAG_TGL));

                            // menambah item ke array
                            itemList.add(item);
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("keyword", keyword);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}