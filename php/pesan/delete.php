<?php
	include "koneksi.php";
	
	$kode_pesanan 	= $_POST['kode_pesanan'];
	
	class emp{}
	
	if (empty($kode_pesanan)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Data Tidak Ditemukan"; 
		die(json_encode($response));
	} else {
		$query 	= mysql_query("SELECT * FROM pesan_servis WHERE kode_pesanan='".$kode_pesanan."' AND status_servis='Sedang Dipesan'" );
		$row 	= mysql_fetch_array($query);
		
		if (!empty($row)) {
			$query2 = mysql_query("DELETE FROM pesan_servis WHERE kode_pesanan='".$row["kode_pesanan"]."'");
			
			if ($query2) {
				$response = new emp();
				$response->success = 1;
				$response->message = "Pesanan berhasil dibatalkan";
				die(json_encode($response));
			} else{ 
				$response = new emp();
				$response->success = 0;
				$response->message = "Tidak bisa dibatalkan, Pesanan sudah diterima/dikerjakan Teknisi";
				die(json_encode($response)); 
			}	
			
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Tidak bisa dibatalkan, Pesanan sudah diterima/dikerjakan Teknisi";
			die(json_encode($response)); 
		}	
	}
		
?>