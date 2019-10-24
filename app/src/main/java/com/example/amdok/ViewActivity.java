package com.example.amdok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.amdok.Util.RequestHandler;
import com.example.amdok.Util.ServerAPI;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sharedpreferences;
String nama,nik;
    TextView tv_dok,tv_kode,tv_thn,tv_jum ,tv;
    private Button buttonAdd;

    public static final String TAG_ID = "nik";
    public static final String TAG_USERNAME = "nama";
    public static final String GBR = "http://10.0.2.2/propem/gambar/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        nama = sharedpreferences.getString(TAG_USERNAME,null);
        nik = sharedpreferences.getString(TAG_ID,null);


        String nama_dok = getIntent().getExtras().getString("nama_dokumen");
        String kode = getIntent().getExtras().getString("kode_dokumen");
        String tahun = getIntent().getExtras().getString("tahun");
        String jumlah = getIntent().getExtras().getString("jumlah_dokumen");
        String gambar = getIntent().getExtras().getString("gambar");




        tv_dok = findViewById(R.id.judul);
        tv_kode = findViewById(R.id.kode);
        tv_thn = findViewById(R.id.tahun);
        tv_jum = findViewById(R.id.jumlah);



        buttonAdd = (Button) findViewById(R.id.buttonAdd);

        tv_dok.setText(nama_dok);
        tv_kode.setText(kode);
        tv_thn.setText(tahun);
        tv_jum.setText(jumlah);



        ImageView imageView=(ImageView) findViewById(R.id.image67);
        Glide.with(this).load(GBR+gambar)
                .fitCenter() // menyesuaikan ukuran imageview
                .transition(new DrawableTransitionOptions().crossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        buttonAdd.setOnClickListener(this);
    }
    private void addEmployee(){

        final String nama_dok = tv_dok.getText().toString().trim();



        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(ServerAPI.KEY_EMP_NAMA_DOK,nama_dok);
                params.put(ServerAPI.KEY_EMP_NAMA,nama);
                params.put(ServerAPI.KEY_EMP_NIK,nik);



                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(ServerAPI.URL_DATA3, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
