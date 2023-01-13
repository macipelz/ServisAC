<?php
	include "koneksi.php";
	
	$no_sparepart	= $_POST['no_sparepart'];
	$nama_sparepart = $_POST['nama_sparepart'];
	$jumlah = $_POST['jumlah'];
	$tgl = date('Y-m-d');
	
	class emp{}
	
	if (empty($no_sparepart) || empty($nama_sparepart)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("INSERT INTO sparepart_masuk (no_masuk,no_sparepart,nama_sparepart,jumlah,tgl) VALUES(0,'".$no_sparepart."','".$nama_sparepart."','".$jumlah."','".$tgl."')");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di simpan";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
?>