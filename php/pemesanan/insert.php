<?php
	$server		= "localhost"; //sesuaikan dengan nama server
	$user		= "id7743565_chandra"; //sesuaikan username
	$password	= "datachan"; //sesuaikan password
	$database	= "id7743565_servis_ac"; //sesuaikan target databese
	
	$connect = mysql_connect($server, $user, $password) or die ("Koneksi gagal!");
	mysql_select_db($database) or die ("Database belum siap!");

	$kode_pesanan = $_POST['kode_pesanan'];
	$merk_acnya = $_POST['merk_acnya'];
	$kerusakannya = $_POST['kerusakannya'];
	$hp_konsumen = $_POST['hp_konsumen'];
	$hp_teknisi	= $_POST['hp_teknisi'];
	$no_sparepart = $_POST['no_sparepart'];
	$tanggal = date('Y-m-d');
	
	class emp{}
	
	if (empty($merk_acnya) || empty($kerusakannya) || empty($hp_konsumen) || empty($hp_teknisi) || empty($no_sparepart)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("INSERT INTO servis (no_servis, kode_pesanan, merk_acnya, hp_teknisi, kerusakannya, no_sparepart, hp_konsumen, tanggal) VALUES(0,'".$kode_pesanan."','".$merk_acnya."','".$hp_teknisi."','".$kerusakannya."','".$no_sparepart."','".$hp_konsumen."','".$tanggal."')");
		
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