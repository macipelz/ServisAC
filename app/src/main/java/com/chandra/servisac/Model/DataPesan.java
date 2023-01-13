package com.chandra.servisac.Model;

public class DataPesan {
    private String kode_pesanan, merk_ac, kerusakan, status_servis, hp_konsumen, pemilik, alamat, tgl_servis;

    public DataPesan(){}

    public DataPesan(String kode_pesanan, String merk_ac, String kerusakan, String status_servis, String hp_konsumen, String pemilik, String alamat, String tgl_servis) {
        this.kode_pesanan = kode_pesanan;
        this.merk_ac = merk_ac;
        this.kerusakan = kerusakan;
        this.status_servis = status_servis;
        this.hp_konsumen = hp_konsumen;
        this.pemilik = pemilik;
        this.alamat = alamat;
        this.tgl_servis = tgl_servis;
    }

    public String getKode_pesanan() {
        return kode_pesanan;
    }

    public void setKode_pesanan(String kode_pesanan) {
        this.kode_pesanan = kode_pesanan;
    }

    public String getMerk_ac() {
        return merk_ac;
    }

    public void setMerk_ac(String merk_ac) {
        this.merk_ac = merk_ac;
    }

    public String getKerusakan() {
        return kerusakan;
    }

    public void setKerusakan(String kerusakan) {
        this.kerusakan = kerusakan;
    }

    public String getStatus_servis() {
        return status_servis;
    }

    public void setStatus_servis(String status_servis) {
        this.status_servis = status_servis;
    }

    public String getHp_konsumen() {
        return hp_konsumen;
    }

    public void setHp_konsumen(String hp_konsumen) {
        this.hp_konsumen = hp_konsumen;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTgl_servis() {
        return tgl_servis;
    }

    public void setTgl_servis(String tgl_servis) {
        this.tgl_servis = tgl_servis;
    }
}
