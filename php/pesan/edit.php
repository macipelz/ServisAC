<?php
	include "koneksi.php";
	
	$kode_pesanan = $_POST['kode_pesanan'];
	
	class emp{}
	
	if (empty($kode_pesanan)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Error Mengambil Data"; 
		die(json_encode($response));
	} else {
		$query 	= mysql_query("SELECT * FROM pesan_servis WHERE kode_pesanan='".$kode_pesanan."'");
		$row 	= mysql_fetch_array($query);
		
		if (!empty($row)) {
			$response = new emp();
			$response->success = 1;
			$response->kode_pesanan = $row["kode_pesanan"];
			$response->merk_ac = $row["merk_ac"];
			$response->kerusakan = $row["kerusakan"];
			$response->hp_konsumen = $row["hp_konsumen"];
			$response->pemilik = $row["pemilik"];
			$response->alamat = $row["alamat"];
			$response->tgl_servis = $row["tgl_servis"];
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Mengambil Data";
			die(json_encode($response)); 
		}	
	}
?>