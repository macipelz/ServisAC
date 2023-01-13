<?php
	include "koneksi.php";
	
	$kode_pesanan = $_POST['kode_pesanan'];
	$merk_ac 	= $_POST['merk_ac'];
	$kerusakan = $_POST['kerusakan'];
	$status_servis = $_POST['status_servis'];
	$hp_konsumen = $_POST['hp_konsumen'];
	$pemilik 	= $_POST['pemilik'];
	$alamat = $_POST['alamat'];
	$tgl_servis = date('Y-m-d');
	
	class emp{}
	
	if (empty($kode_pesanan) || empty($merk_ac) || empty($kerusakan) || empty($hp_konsumen) || empty($pemilik) || empty($alamat)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("UPDATE pesan_servis SET merk_ac='".$merk_ac."', kerusakan='".$kerusakan."', hp_konsumen='".$hp_konsumen."', pemilik='".$pemilik."', alamat='".$alamat."' WHERE kode_pesanan='".$kode_pesanan."'");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di update";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error update Data";
			die(json_encode($response)); 
		}	
	}
?>