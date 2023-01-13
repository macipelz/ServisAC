<?php
	include "koneksi.php";
	
	$no_masuk 	= $_POST['no_masuk'];
	
	class emp{}
	
	if (empty($no_masuk)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Error Mengambil Data"; 
		die(json_encode($response));
	} else {
		$query 	= mysql_query("SELECT * FROM sparepart_masuk WHERE no_masuk='".$no_masuk."'");
		$row 	= mysql_fetch_array($query);
		
		if (!empty($row)) {
			$response = new emp();
			$response->success = 1;
			$response->no_masuk = $row["no_masuk"];
			$response->no_sparepart = $row["no_sparepart"];
			$response->nama_sparepart = $row["nama_sparepart"];
			$response->jumlah = $row["jumlah"];
			$response->tgl = $row["tgl"];
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Mengambil Data";
			die(json_encode($response)); 
		}	
	}
?>