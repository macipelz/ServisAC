package com.chandra.servisac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEditProfilTek {
    @SerializedName("hp_teknisi")
    @Expose
    private String hp_teknisi;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    public DataEditProfilTek(){}

    public DataEditProfilTek(String hp_teknisi, String password, String nama, String alamat) {
        this.hp_teknisi = hp_teknisi;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getHp_teknisi() {
        return hp_teknisi;
    }

    public void setHp_teknisi(String hp_teknisi) {
        this.hp_teknisi = hp_teknisi;
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
