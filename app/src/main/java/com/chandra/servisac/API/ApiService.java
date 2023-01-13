package com.chandra.servisac.API;

import com.chandra.servisac.Model.DataPesan;
import com.chandra.servisac.Model.ModelDataPembayaran;
import com.chandra.servisac.Model.ModelDataPesan;
import com.chandra.servisac.Model.ModelDataServis;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

//    @FormUrlEncoded
//    @POST("tambah_data.php")
//    Call<ResponseBody> tambahData(@Field("nama") String nama, @Field("jenis") String jenis, @Field("keterangan") String keterangan);
//
    @FormUrlEncoded
    @POST("edit.php")
    Call<ResponseBody> EditP(@Field("hp_konsumen") String hp_konsumen, @Field("nama") String nama, @Field("alamat") String alamat, @Field("password") String password);
//
//    @FormUrlEncoded
//    @POST("hapus_data.php")
//    Call<ResponseBody> hapusData(@Field("id_barang") String id_barang);

    @GET("lihat_data.php")
    Call<List<ModelDataPesan>> getSemuaPesan();

    @GET("single_data.php")
    Call<List<ModelDataPesan>> getSinglePesan(@Query("kd_psn") String id);

    @GET("lihat_data.php")
    Call<List<ModelDataServis>> getSemuaData();

    @GET("single_data.php")
    Call<List<ModelDataServis>> getSingleData(@Query("no_srv") String id);

    @GET("lihat_data.php")
    Call<List<ModelDataPembayaran>> getSemuaBayar();

    @GET("single_data.php")
    Call<List<ModelDataPembayaran>> getSingleBayar(@Query("no_byr") String id);

}
