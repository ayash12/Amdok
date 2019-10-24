package com.example.amdok.Model;

import android.app.Application;

public class ModelUser extends Application {
    private String nik;
    private String nama;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ModelUser(String nik, String nama) {
        this.nik = nik;
        this.nama = nama;
    }
}
