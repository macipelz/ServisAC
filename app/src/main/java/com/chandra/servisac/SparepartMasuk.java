package com.chandra.servisac;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.chandra.servisac.Model.DataSptMasuk;
import com.chandra.servisac.Adapter.AdapterSptMasuk;
import com.chandra.servisac.App.AppController;
import com.chandra.servisac.Model.DataSptMasuk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparepartMasuk extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    List<DataSptMasuk> itemList = new ArrayList<DataSptMasuk>();
    AdapterSptMasuk adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_no_masuk, txt_no_sparepart, txt_nama_sparepart, txt_jumlah, txt_tgl;
    String no_masuk, no_sparepart, nama_sparepart, jumlah, tgl, a;

    private static final String TAG = SparepartMasuk.class.getSimpleName();

    private static String url_select     = Server.URLsptmsk + "select.php";
    private static String url_insert     = Server.URLsptmsk + "insert.php";
    private static String url_edit       = Server.URLsptmsk + "edit.php";
    private static String url_update     = Server.URLsptmsk + "update.php";
    private static String url_delete     = Server.URLsptmsk + "delete.php";

    public static final String TAG_1   = "no_masuk";
    public static final String TAG_2   = "no_sparepart";
    public static final String TAG_3   = "nama_sparepart";
    public static final String TAG_4   = "jumlah";
    public static final String TAG_5   = "tgl";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sptmasuk);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // menghubungkan variablel pada layout dan pada java
        fab     = (FloatingActionButton) findViewById(R.id.fab_add);
        swipe   = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) findViewById(R.id.list);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new AdapterSptMasuk(SparepartMasuk.this, itemList);
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
                       }
                   }
        );

        // fungsi floating action button memanggil form biodata
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogForm("", "", "", "","","SIMPAN");
            }
        });

        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String no_masukx = itemList.get(position).getNo_masuk();

                final CharSequence[] dialogitem = {"Ubah", "Hapus"};
                dialog = new AlertDialog.Builder(SparepartMasuk.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                edit(no_masukx);
                                break;
                            case 1:
                                delete(no_masukx);
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
        txt_no_masuk.setText(null);
        txt_no_sparepart.setText(null);
        txt_nama_sparepart.setText(null);
        txt_jumlah.setText(null);
        txt_tgl.setText(null);
    }

    // untuk menampilkan dialog from biodata
    private void DialogForm(String no_masukx, String no_sparepartx, String nama_sparepartx, String jumlahx, String tglx, String button) {
        dialog = new AlertDialog.Builder(SparepartMasuk.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_input_sptmasuk, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Form Input");

        txt_no_masuk  = (EditText) dialogView.findViewById(R.id.txt_no_masuk);
        txt_no_sparepart  = (EditText) dialogView.findViewById(R.id.txt_no_sparepart);
        txt_nama_sparepart  = (EditText) dialogView.findViewById(R.id.txt_nama_sparepart);
        txt_jumlah  = (EditText) dialogView.findViewById(R.id.txt_jumlah);
        txt_tgl  = (EditText) dialogView.findViewById(R.id.txt_tgl);

        if (!no_masukx.isEmpty()){
            txt_no_masuk.setText(no_masukx);
            txt_no_sparepart.setText(no_sparepartx);
            txt_nama_sparepart.setText(nama_sparepartx);
            txt_jumlah.setText(jumlahx);
            txt_tgl.setText(tglx);
        } else {
            kosong();
        }

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                no_masuk  = txt_no_masuk.getText().toString();
                no_sparepart  = txt_no_sparepart.getText().toString();
                nama_sparepart  = txt_nama_sparepart.getText().toString();
                jumlah  = txt_jumlah.getText().toString();
                tgl  = txt_tgl.getText().toString();

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

                        DataSptMasuk item = new DataSptMasuk();

                        item.setNo_masuk(obj.getString(TAG_1));
                        item.setNo_sparepart(obj.getString(TAG_2));
                        item.setNama_sparepart(obj.getString(TAG_3));
                        item.setJumlah(obj.getString(TAG_4));
                        item.setTgl(obj.getString(TAG_5));

                        // menambah item ke array
                        itemList.add(item);
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
        if (no_masuk.isEmpty()){
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

                        Toast.makeText(SparepartMasuk.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(SparepartMasuk.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(SparepartMasuk.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                if (no_masuk.isEmpty()){
                    params.put("no_sparepart", no_sparepart);
                    params.put("nama_sparepart", nama_sparepart);
                    params.put("jumlah", jumlah);
                    params.put("tgl", tgl);
                } else {
                    params.put("no_masuk", no_masuk);
                    params.put("no_sparepart", no_sparepart);
                    params.put("nama_sparepart", nama_sparepart);
                    params.put("jumlah", jumlah);
                    params.put("tgl", tgl);
                }

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // fungsi untuk get edit data
    private void edit(final String no_masukx){
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
                        String no_masukx  = jObj.getString(TAG_1);
                        String no_sparepartx  = jObj.getString(TAG_2);
                        String nama_sparepartx  = jObj.getString(TAG_3);
                        String jumlahx  = jObj.getString(TAG_4);
                        String tglx  = jObj.getString(TAG_5);

                        DialogForm(no_masukx, no_sparepartx, nama_sparepartx, jumlahx, tglx, "UPDATE");

                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(SparepartMasuk.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(SparepartMasuk.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("no_masuk", no_masukx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // fungsi untuk menghapus
    private void delete(final String no_masukx){
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

                        Toast.makeText(SparepartMasuk.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(SparepartMasuk.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(SparepartMasuk.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("no_masuk", no_masukx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}