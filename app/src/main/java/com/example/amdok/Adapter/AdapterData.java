package com.example.amdok.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amdok.HomeFragment;
import com.example.amdok.Model.ModelData;
import com.example.amdok.R;
import com.example.amdok.ViewActivity;
import com.example.amdok.ViewFragment;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.amdok.LoginActivity.my_shared_preferences;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems;
    Context context;
    SharedPreferences sharedpreferences;
    String nik1,nama1;
    public static final String TAG_ID = "nik";
    public static final String TAG_USERNAME = "nama";
    public AdapterData(Context context, List<ModelData> items) {
        this.mItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder called");
        sharedpreferences = context.getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        nik1 = sharedpreferences.getString(TAG_ID, null);
        nama1 = sharedpreferences.getString(TAG_USERNAME, null);
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.list,viewGroup,false);
        final HolderData holderData = new HolderData(view);
        holderData.card.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i = new Intent(context, ViewActivity.class);


               i.putExtra("nama_dokumen",mItems.get(holderData.getAdapterPosition()).getNama_dokumen());
               i.putExtra("kode_dokumen",mItems.get(holderData.getAdapterPosition()).getKode_dokumen());
               i.putExtra("tahun",mItems.get(holderData.getAdapterPosition()).getTahun());
               i.putExtra("jumlah_dokumen",mItems.get(holderData.getAdapterPosition()).getJumlah_dokumen());
               i.putExtra("gambar",mItems.get(holderData.getAdapterPosition()).getGambar());


               context.startActivity(i);


            }
        });


        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int i) {

        holderData.tvnama.setText(mItems.get(i).getNama_dokumen());
        holderData.tvtahun.setText(mItems.get(i).getTahun());
        holderData.tvstock.setText(mItems.get(i).getJumlah_dokumen());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvnama, tvtahun, tvstock;
        CardView card;

        public HolderData(View view) {
            super(view);
            card = view.findViewById(R.id.card);

            tvnama = (TextView) view.findViewById(R.id.nama);
            tvtahun = (TextView) view.findViewById(R.id.tahun);
            tvstock = (TextView) view.findViewById(R.id.stock);
        }
    }
}

