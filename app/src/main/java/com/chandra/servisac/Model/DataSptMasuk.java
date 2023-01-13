package com.chandra.servisac.Model;

public class DataSptMasuk {
    private String no_masuk, no_sparepart, nama_sparepart, jumlah, tgl;

    public DataSptMasuk(){
    }

    public DataSptMasuk(String no_masuk, String no_sparepart, String nama_sparepart, String jumlah, String tgl) {
        this.no_masuk = no_masuk;
        this.no_sparepart = no_sparepart;
        this.nama_sparepart = nama_sparepart;
        this.jumlah = jumlah;
        this.tgl = tgl;
    }

    public String getNo_masuk() {
        return no_masuk;
    }

    public void setNo_masuk(String no_masuk) {
        this.no_masuk = no_masuk;
    }

    public String getNo_sparepart() {
        return no_sparepart;
    }

    public void setNo_sparepart(String no_sparepart) {
        this.no_sparepart = no_sparepart;
    }

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
}
