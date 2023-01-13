package com.chandra.servisac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEditProfil {
    @SerializedName("hp_konsumen")
    @Expose
    private String hp_konsumen;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    public DataEditProfil(){}

    public DataEditProfil(String hp_konsumen, String password, String nama, String alamat) {
        this.hp_konsumen = hp_konsumen;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getHp_konsumen() {
        return hp_konsumen;
    }

    public void setHp_konsumen(String hp_konsumen) {
        this.hp_konsumen = hp_konsumen;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
