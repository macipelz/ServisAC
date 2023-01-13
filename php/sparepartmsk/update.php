<?php
	include "koneksi.php";
	
	$no_masuk = $_POST['no_masuk'];
	$no_sparepart = $_POST['no_sparepart'];
	$nama_sparepart = $_POST['nama_sparepart'];
	$jumlah = $_POST['jumlah'];
	$tgl = date('Y-m-d');
	
	class emp{}
	
	if (empty($no_masuk) || empty($no_sparepart) || empty($nama_sparepart)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("UPDATE sparepart_masuk SET no_sparepart='".$no_sparepart."', nama_sparepart='".$nama_sparepart."', jumlah='".$jumlah."', tgl='".$tgl."' WHERE no_masuk='".$no_masuk."'");
		
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