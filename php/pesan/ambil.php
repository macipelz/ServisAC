<?php
	include "koneksi.php";
	
	$hp_konsumen = $_POST['hp_konsumen'];
	
	class emp{}
	
	if (empty($hp_konsumen)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Error Mengambil Data"; 
		die(json_encode($response));
	} else {
		$query 	= mysql_query("SELECT * FROM konsumen WHERE hp_konsumen='".$hp_konsumen."'");
		$row 	= mysql_fetch_array($query);
		
		if (!empty($row)) {
			$response = new emp();
			$response->success = 1;
			$response->hp_konsumen = $row["hp_konsumen"];
			$response->nama = $row["nama"];
			$response->alamat = $row["alamat"];
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Mengambil Data";
			die(json_encode($response)); 
		}	
	}
?>