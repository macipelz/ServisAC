package com.chandra.servisac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_logout, btn_data, b_pesan, b_profil, b_bayar, b_serv;
    Button btn_admin;
    TextView txt_id, txt_username;
    String id, username, alamat, password;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_username = (TextView) findViewById(R.id.txt_username);
        btn_logout = (ImageButton) findViewById(R.id.btn_logout);
        btn_data = (ImageButton) findViewById(R.id.btn_data);
        b_pesan = (ImageButton) findViewById(R.id.b_pesan);
        b_profil = (ImageButton) findViewById(R.id.b_profil);
        b_bayar = (ImageButton) findViewById(R.id.b_bayar);
        b_serv = (ImageButton) findViewById(R.id.btn_serv);
        btn_admin = (Button) findViewById(R.id.btn_admin);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);
        alamat = getIntent().getStringExtra(TAG_ALAMAT);
        password = getIntent().getStringExtra(TAG_PASSWORD);

        txt_id.setText("Selamat Datang, "+id);
        txt_username.setText(username);

        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                editor.putString(TAG_ALAMAT, null);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, Login.class);
                finish();
                startActivity(intent);
            }
        });

        b_serv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,RiwayatServis.class);
                intent.putExtra(TAG_USERNAME, username);
                startActivity(intent);
            }
        });

        btn_data.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView username = (TextView) findViewById(R.id.txt_username);
                Intent intent = new Intent(MainActivity.this,Pesan.class);
                intent.putExtra(MainActivity.TAG_USERNAME, username.getText().toString());
                startActivity(intent);
            }
        });

        b_bayar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,RiwayatBayar.class);
                intent.putExtra(TAG_USERNAME, username);
                startActivity(intent);
            }
        });

        btn_admin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,MainActivityTeknisi.class);
                intent.putExtra(TAG_ID, id);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_ALAMAT, alamat);
                intent.putExtra(TAG_PASSWORD, password);
                startActivity(intent);
            }
        });

        b_profil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,EditProfil.class);
                intent.putExtra(TAG_ID, id);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_ALAMAT, alamat);
                intent.putExtra(TAG_PASSWORD, password);
                startActivity(intent);
            }
        });

        b_pesan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,TambahPesan.class);
                intent.putExtra(TAG_ID, id);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_ALAMAT, alamat);
                startActivity(intent);
            }
        });

    }
}
