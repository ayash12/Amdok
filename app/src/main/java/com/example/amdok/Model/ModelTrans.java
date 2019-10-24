package com.example.amdok.Model;

public class ModelTrans {
    String id,nama_dokumen,nip_nidn,nama,tgl_pinjam,jumlah_pinjam,info;

    public ModelTrans(String id, String nama_dokumen, String nip_nidn, String nama, String tgl_pinjam, String jumlah_pinjam, String info) {
        this.id = id;
        this.nama_dokumen = nama_dokumen;
        this.nip_nidn = nip_nidn;
        this.nama = nama;
        this.tgl_pinjam = tgl_pinjam;
        this.jumlah_pinjam = jumlah_pinjam;
        this.info = info;
    }

    public ModelTrans() {

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

    public String getNip_nidn() {
        return nip_nidn;
    }

    public void setNip_nidn(String nip_nidn) {
        this.nip_nidn = nip_nidn;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl_pinjam() {
        return tgl_pinjam;
    }

    public void setTgl_pinjam(String tgl_pinjam) {
        this.tgl_pinjam = tgl_pinjam;
    }

    public String getJumlah_pinjam() {
        return jumlah_pinjam;
    }

    public void setJumlah_pinjam(String jumlah_pinjam) {
        this.jumlah_pinjam = jumlah_pinjam;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
