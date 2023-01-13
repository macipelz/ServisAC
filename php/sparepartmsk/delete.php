<?php
	include "koneksi.php";
	
	$no_masuk 	= $_POST['no_masuk'];
	
	class emp{}
	
	if (empty($no_masuk)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Error hapus Data"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("DELETE FROM sparepart_masuk WHERE no_masuk='".$no_masuk."'");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di hapus";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error hapus Data";
			die(json_encode($response)); 
		}	
	}
?>