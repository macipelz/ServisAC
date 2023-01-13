<?php
	$server		= "localhost"; //sesuaikan dengan nama server
	$user		= "id7743565_chandra"; //sesuaikan username
	$password	= "datachan"; //sesuaikan password
	$database	= "id7743565_servis_ac"; //sesuaikan target databese
	
	$connect = mysql_connect($server, $user, $password) or die ("Koneksi gagal!");
	mysql_select_db($database) or die ("Database belum siap!");

	$no_pembayaran = $_POST['no_pembayaran'];
	$no_servis = $_POST['no_servis'];
	$biaya = $_POST['biaya'];
	$total = $_POST['total'];
	$tgl = date('Y-m-d');
	
	class emp{}
	
	if (empty($no_servis) || empty($biaya)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("INSERT INTO pembayaran (no_pembayaran, tgl, no_servis, biaya, total) VALUES(0,'".$tgl."','".$no_servis."','".$biaya."','".$total."')");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Berhasil Disimpan, anda di alihkan ke form pembayaran";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
?>