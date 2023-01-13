<?php
	include "koneksi.php";
	
	$no_sparepart = $_POST['no_sparepart'];
	$nama_sparepart = $_POST['nama_sparepart'];
	$harga = $_POST['harga'];
	$stok = $_POST['stok'];
	
	class emp{}
	
	if (empty($no_sparepart) || empty($nama_sparepart)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("UPDATE sparepart SET nama_sparepart='".$nama_sparepart."', harga='".$harga."', stok='".$stok."' WHERE no_sparepart='".$no_sparepart."'");
		
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