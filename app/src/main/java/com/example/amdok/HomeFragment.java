package com.example.amdok;


import android.app.ProgressDialog;
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
import com.example.amdok.Adapter.AdapterData;
import com.example.amdok.Model.ModelData;
import com.example.amdok.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.amdok.LoginActivity.my_shared_preferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<ModelData> mItems;
    private RecyclerView recyclerView;
    ProgressDialog pd;
    SwipeRefreshLayout swLayout;
    SharedPreferences sharedpreferences;
    String nik,nama,nama1,nik1;
    TextView textView1;


    public static final String TAG_ID = "nik";
    public static final String TAG_USERNAME = "nama";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Rootview = inflater.inflate(R.layout.fragment_home, container, false);
        swLayout = (SwipeRefreshLayout) Rootview.findViewById(R.id.swlayout);
        recyclerView = (RecyclerView) Rootview.findViewById(R.id.recyclerviewTemp);


        pd = new ProgressDialog(getActivity());
        mItems = new ArrayList<>();

        nik = getActivity().getIntent().getStringExtra(TAG_ID);
        nama = getActivity().getIntent().getStringExtra(TAG_USERNAME);

        loadJson();

        sharedpreferences = this.getActivity().getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        nik1 = sharedpreferences.getString(nik, null);
        nama1 = sharedpreferences.getString(nama, null);
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

        return Rootview;
    }
    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();
        request = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA, null,
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
                                ModelData md = new ModelData();

                                md.setNama_dokumen(data.getString("nama_dokumen"));
                                md.setTahun(data.getString("tahun"));
                                md.setJumlah_dokumen(data.getString("jumlah_dokumen"));
                                md.setId(data.getString("id"));
                                md.setKode_dokumen(data.getString("kode_dokumen"));
                                md.setGambar(data.getString("gambar"));
                                mItems.add(md);

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


    private void setuprecylerview(List<ModelData> mItems) {
        AdapterData myAdapter = new AdapterData(getActivity(),mItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(myAdapter);
    }
}
