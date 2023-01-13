
package com.chandra.servisac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDataServis {

    @SerializedName("no_srv")
    @Expose
    private String no_servis;

    @SerializedName("kd_psn")
    @Expose
    private String kode_pesanan;

    @SerializedName("merk")
    @Expose
    private String merk_ac;

    @SerializedName("merkx")
    @Expose
    private String merk_acnya;

    @SerializedName("rusak")
    @Expose
    private String kerusakan;

    @SerializedName("rusakx")
    @Expose
    private String kerusakannya;

    @SerializedName("hp_tek")
    @Expose
    private String hp_teknisi;

    @SerializedName("nm_tek")
    @Expose
    private String nama_teknisi;

    @SerializedName("hp_kon")
    @Expose
    private String hp_konsumen;

    @SerializedName("nm")
    @Expose
    private String nama;

    @SerializedName("no_spt")
    @Expose
    private String no_sparepart;

    @SerializedName("nm_spt")
    @Expose
    private String nama_sparepart;

    @SerializedName("hrg")
    @Expose
    private String harga;

    @SerializedName("tgl")
    @Expose
    private String tanggal;

    public static final String hp_konsum = "HP_KON";
    public static final String no_srvs = "NO";

    public ModelDataServis(String no_servis, String kode_pesanan, String merk_ac, String merk_acnya, String kerusakan, String kerusakannya, String hp_teknisi, String nama_teknisi, String hp_konsumen, String nama, String no_sparepart, String nama_sparepart, String harga, String tanggal) {
        this.no_servis = no_servis;
        this.kode_pesanan = kode_pesanan;
        this.merk_ac = merk_ac;
        this.merk_acnya = merk_acnya;
        this.kerusakan = kerusakan;
        this.kerusakannya = kerusakannya;
        this.hp_teknisi = hp_teknisi;
        this.nama_teknisi = nama_teknisi;
        this.hp_konsumen = hp_konsumen;
        this.nama = nama;
        this.no_sparepart = no_sparepart;
        this.nama_sparepart = nama_sparepart;
        this.harga = harga;
        this.tanggal = tanggal;
    }

    public String getNo_servis() {
        return no_servis;
    }

    public void setNo_servis(String no_servis) {
        this.no_servis = no_servis;
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

    public String getMerk_acnya() {
        return merk_acnya;
    }

    public void setMerk_acnya(String merk_acnya) {
        this.merk_acnya = merk_acnya;
    }

    public String getKerusakan() {
        return kerusakan;
    }

    public void setKerusakan(String kerusakan) {
        this.kerusakan = kerusakan;
    }

    public String getKerusakannya() {
        return kerusakannya;
    }

    public void setKerusakannya(String kerusakannya) {
        this.kerusakannya = kerusakannya;
    }

    public String getHp_teknisi() {
        return hp_teknisi;
    }

    public void setHp_teknisi(String hp_teknisi) {
        this.hp_teknisi = hp_teknisi;
    }

    public String getNama_teknisi() {
        return nama_teknisi;
    }

    public void setNama_teknisi(String nama_teknisi) {
        this.nama_teknisi = nama_teknisi;
    }

    public String getHp_konsumen() {
        return hp_konsumen;
    }

    public void setHp_konsumen(String hp_konsumen) {
        this.hp_konsumen = hp_konsumen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}