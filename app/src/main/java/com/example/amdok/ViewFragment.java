package com.example.amdok;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amdok.Util.RequestHandler;
import com.example.amdok.Util.ServerAPI;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {
    String nik,nama;
    TextView tv_dok,tv_kode,tv_thn,tv_jum,tv;
    private Button buttonAdd;

    public ViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Rootview = inflater.inflate(R.layout.fragment_view, container, false);
        Intent i = getActivity().getIntent();

        String nama_dok = getActivity().getIntent().getExtras().getString("nama_dokumen");
        String kode = getActivity().getIntent().getExtras().getString("kode_dokumen");
        String tahun = getActivity().getIntent().getExtras().getString("tahun");
        String jumlah = getActivity().getIntent().getExtras().getString("jumlah_dokumen");



        tv_dok = (TextView) Rootview.findViewById(R.id.judul);
        tv_kode = (TextView)Rootview.findViewById(R.id.kode);
        tv_thn = (TextView)Rootview.findViewById(R.id.tahun);
        tv_jum = (TextView)Rootview.findViewById(R.id.jumlah);


        buttonAdd = (Button) Rootview.findViewById(R.id.buttonAdd);

        tv_dok.setText(nama_dok);
        tv_kode.setText(kode);
        tv_thn.setText(tahun);
        tv_jum.setText(jumlah);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addEmployee();
            }
        });
        return Rootview;
    }
    private void addEmployee(){

        final String nama_dok = tv_dok.getText().toString().trim();


        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(ServerAPI.KEY_EMP_NAMA_DOK,nama_dok);



                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(ServerAPI.URL_DATA3, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

}
