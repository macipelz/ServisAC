package com.chandra.servisac;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.toolbox.Volley;
import com.chandra.servisac.Adapter.AdapterEditProfil;

public class EditProfil extends AppCompatActivity {

    Button btn_ubah;
    ProgressDialog progressDialog;
    EditText txt_username, txt_password, txt_nama, txt_alamat;
    Intent intent;
    String id, username, alamat, password, getUsername;
    SharedPreferences sharedpreferences;
    int success;
    AdapterEditProfil adapter;

    private String url = Server.URL + "edit.php";

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_PASSWORD = "password";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        btn_ubah = (Button) findViewById(R.id.btn_ubah);
        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);
        txt_nama = (EditText) findViewById(R.id.txt_nama);
        txt_alamat = (EditText) findViewById(R.id.txt_alamat);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);
        getUsername = getIntent().getStringExtra(TAG_USERNAME);
        alamat = getIntent().getStringExtra(TAG_ALAMAT);
        password = getIntent().getStringExtra(TAG_PASSWORD);

        txt_nama.setText(id);
        txt_username.setText(username);
        txt_alamat.setText(alamat);
        txt_password.setText(password);

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            // TODO Auto-generated method stub
            @Override
            public void onClick(View v) {
                String idx = txt_nama.getText().toString();
                String passwordx = txt_password.getText().toString();
                String alamatx = txt_alamat.getText().toString();
                String usernamex = txt_username.getText().toString();

                if (idx.equals("")){
                    Toast.makeText(EditProfil.this, "Nama Tidak Boleh Di Kosong", Toast.LENGTH_SHORT).show();
                } else if (usernamex.equals("")){
                    Toast.makeText(EditProfil.this, "No Handphone Tidak Boleh Di Ubah", Toast.LENGTH_SHORT).show();
                } else if (passwordx.equals("")){
                    Toast.makeText(EditProfil.this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else if (alamatx.equals("")){
                    Toast.makeText(EditProfil.this, "Alamat Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    EditP();
                }
            }
        });
    }

    public void EditP() {
        final String nama = this.txt_nama.getText().toString().trim();
        final String alamat = this.txt_alamat.getText().toString().trim();
        final String password = this.txt_password.getText().toString().trim();
        final String username = getUsername;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mengedit data ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putBoolean(Login.session_status, false);
                                editor.putString(TAG_ID, null);
                                editor.putString(TAG_USERNAME, null);
                                editor.putString(TAG_ALAMAT, null);
                                editor.commit();

                                Intent intent = new Intent(EditProfil.this, Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                                Toast.makeText(EditProfil.this,"Berhasil Mengubah Data, Silakan Login Ulang!!",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(EditProfil.this,"Error "+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(EditProfil.this,"Error "+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("nama",nama);
                params.put("password",password);
                params.put("alamat",alamat);
                params.put("username",username);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}