package com.example.amdok.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amdok.Model.ModelTrans;
import com.example.amdok.R;

import java.util.List;

public class AdapterTrans extends RecyclerView.Adapter<AdapterTrans.HolderData> {

    private List<ModelTrans> mItems;
    Context context;

    public AdapterTrans(Context context, List<ModelTrans> items) {
        this.mItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.transaksi,viewGroup,false);

        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int i) {
        holderData.tv_nama_dok.setText(mItems.get(i).getNama_dokumen());
        holderData.tv_tgl.setText(mItems.get(i).getTgl_pinjam());
        holderData.tv_status.setText(mItems.get(i).getInfo());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tv_nama_dok,tv_tgl,tv_status;

        public HolderData (View itemView){
            super(itemView);
            tv_nama_dok = (TextView) itemView.findViewById(R.id.namadok);
            tv_tgl = (TextView) itemView.findViewById(R.id.tgl_pesan);
            tv_status = (TextView) itemView.findViewById(R.id.status);
        }
    }
}
