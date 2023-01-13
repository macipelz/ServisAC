<?php
	include "koneksi.php";
	
	$merk_ac 	= $_POST['merk_ac'];
	$kerusakan = $_POST['kerusakan'];
	$hp_konsumen = $_POST['hp_konsumen'];
	$pemilik 	= $_POST['pemilik'];
	$alamat = $_POST['alamat'];
	$tgl_servis = date('Y-m-d');
	
	class emp{}
	
	if (empty($merk_ac) || empty($kerusakan) || empty($hp_konsumen) || empty($pemilik) || empty($alamat)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("INSERT INTO pesan_servis (kode_pesanan, merk_ac, kerusakan, status_servis, hp_konsumen, pemilik, alamat, tgl_servis) VALUES(0,'".$merk_ac."','".$kerusakan."','Sedang Dipesan','".$hp_konsumen."','".$pemilik."','".$alamat."','".$tgl_servis."')");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Pemesanan berhasil di lakukan, tunggu konfirmasi teknisi";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
?>