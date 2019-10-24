package com.example.amdok;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.amdok.Adapter.AdapterTrans;
import com.example.amdok.Model.ModelTrans;
import com.example.amdok.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransaksiFragment extends Fragment {

    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<ModelTrans> mItems;
    private RecyclerView recyclerView;
    ProgressDialog pd;
    SwipeRefreshLayout swLayout;


    SharedPreferences sharedpreferences;
    String nama,nik;

    TextView textView1;


    public static final String TAG_ID = "nik";
    public static final String TAG_USERNAME = "nama";

    public TransaksiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_transaksi, container, false);


        sharedpreferences = this.getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        nama = sharedpreferences.getString(TAG_USERNAME,null);
        nik = sharedpreferences.getString(TAG_ID,null);

        swLayout = (SwipeRefreshLayout) RootView.findViewById(R.id.swlayout1);
        recyclerView =  (RecyclerView) RootView.findViewById(R.id.recyclerview1);
        pd = new ProgressDialog(getActivity());
        mItems = new ArrayList<>();

        loadJson();


        swLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);

        // Mengeset listener yang akan dijalankan saat layar di refresh/swipe
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Handler untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                        // Berhenti berputar/refreshing
                        swLayout.setRefreshing(false);

                        // fungsi-fungsi lain yang dijalankan saat refresh berhenti
                        getActivity().finish();
                        startActivity(getActivity().getIntent());

                    }
                }, 5000);
            }
        });
        return RootView;
    }
    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();
        request = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA4 + nik , null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        JSONObject jsonObject = null;
                        Log.d("volley","response : "+ response.toString());
                        for(int i = 0; i <response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelTrans mt = new ModelTrans();
                                mt.setNama_dokumen(data.getString("nama_dokumen"));
                                mt.setTgl_pinjam(data.getString("tgl_pinjam"));
                                mt.setInfo(data.getString("info"));
                                mItems.add(mt);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        setuprecylerview(mItems);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley","error : "+ error.getMessage());

                    }
                }
        );

        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }


    private void setuprecylerview(List<ModelTrans> mItems) {
        AdapterTrans myAdapter = new AdapterTrans(getActivity(),mItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(myAdapter);
    }

}
