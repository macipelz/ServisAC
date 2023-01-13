
package com.chandra.servisac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDataPembayaran {

    @SerializedName("no_byr")
    @Expose
    private String no_pembayaran;

    @SerializedName("hrg")
    @Expose
    private String harga;

    @SerializedName("bya")
    @Expose
    private String biaya;

    @SerializedName("merk")
    @Expose
    private String merk_acnya;

    @SerializedName("tot")
    @Expose
    private String total;

    @SerializedName("rusak")
    @Expose
    private String kerusakannya;

    @SerializedName("nm_tek")
    @Expose
    private String nama_teknisi;

    @SerializedName("hp_kon")
    @Expose
    private String hp_konsumen;

    @SerializedName("nm_spt")
    @Expose
    private String nama_sparepart;

    @SerializedName("tgl")
    @Expose
    private String tgl;

    public static final String hp_konsum = "HP_KON";
    public static final String no_byar = "NO";

    public ModelDataPembayaran(String no_pembayaran, String harga, String biaya, String merk_acnya, String total, String kerusakannya, String nama_teknisi, String hp_konsumen, String nama_sparepart, String tgl) {
        this.no_pembayaran = no_pembayaran;
        this.harga = harga;
        this.biaya = biaya;
        this.merk_acnya = merk_acnya;
        this.total = total;
        this.kerusakannya = kerusakannya;
        this.nama_teknisi = nama_teknisi;
        this.hp_konsumen = hp_konsumen;
        this.nama_sparepart = nama_sparepart;
        this.tgl = tgl;
    }

    public String getNo_pembayaran() {
        return no_pembayaran;
    }

    public void setNo_pembayaran(String no_pembayaran) {
        this.no_pembayaran = no_pembayaran;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }

    public String getMerk_acnya() {
        return merk_acnya;
    }

    public void setMerk_acnya(String merk_acnya) {
        this.merk_acnya = merk_acnya;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getKerusakannya() {
        return kerusakannya;
    }

    public void setKerusakannya(String kerusakannya) {
        this.kerusakannya = kerusakannya;
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

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public static String getHp_konsum() {
        return hp_konsum;
    }

    public static String getNo_byar() {
        return no_byar;
    }
}