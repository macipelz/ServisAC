package com.chandra.servisac.Model;

public class DataSparepart {
    private String no_sparepart, nama_sparepart, harga, stok;

    public DataSparepart(){
    }

    public DataSparepart(String no_sparepart, String nama_sparepart, String harga, String stok) {
        this.no_sparepart = no_sparepart;
        this.nama_sparepart = nama_sparepart;
        this.harga = harga;
        this.stok = stok;
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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }
}
