package com.example.amdok.Model;

public class ModelData  {
   String nama,id,nama_dokumen,kode_dokumen,tahun,jumlah_dokumen,tgl_input, gambar;

    public ModelData(String nama, String id, String nama_dokumen, String kode_dokumen, String tahun, String jumlah_dokumen, String tgl_input, String gambar) {
        this.nama = nama;
        this.id = id;
        this.nama_dokumen = nama_dokumen;
        this.kode_dokumen = kode_dokumen;
        this.tahun = tahun;
        this.jumlah_dokumen = jumlah_dokumen;
        this.tgl_input = tgl_input;
        this.gambar = gambar;
    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_dokumen() {
        return nama_dokumen;
    }

    public void setNama_dokumen(String nama_dokumen) {
        this.nama_dokumen = nama_dokumen;
    }

    public String getKode_dokumen() {
        return kode_dokumen;
    }

    public void setKode_dokumen(String kode_dokumen) {
        this.kode_dokumen = kode_dokumen;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getJumlah_dokumen() {
        return jumlah_dokumen;
    }

    public void setJumlah_dokumen(String jumlah_dokumen) {
        this.jumlah_dokumen = jumlah_dokumen;
    }

    public String getTgl_input() {
        return tgl_input;
    }

    public void setTgl_input(String tgl_input) {
        this.tgl_input = tgl_input;
    }

    public String getGambar() {
        return gambar;
    }
    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public ModelData(){

   }


}
