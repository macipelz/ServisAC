<?php
	include "koneksi.php";
	
	$no_sparepart 	= $_POST['no_sparepart'];
	
	class emp{}
	
	if (empty($no_sparepart)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Error Mengambil Data"; 
		die(json_encode($response));
	} else {
		$query 	= mysql_query("SELECT * FROM sparepart WHERE no_sparepart='".$no_sparepart."'");
		$row 	= mysql_fetch_array($query);
		
		if (!empty($row)) {
			$response = new emp();
			$response->success = 1;
			$response->no_sparepart = $row["no_sparepart"];
			$response->nama_sparepart = $row["nama_sparepart"];
			$response->harga = $row["harga"];
			$response->stok  = $row["stok"];
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Mengambil Data";
			die(json_encode($response)); 
		}	
	}
?>