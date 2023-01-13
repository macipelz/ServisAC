<?php
	include 'koneksi.php';
	
	class usr{}
	
	$username = $_POST["username"];
	$password = $_POST["password"];
	
	if ((empty($username))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom No Handphone tidak boleh kosong"; 
		die(json_encode($response));
	} else if ((empty($password))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom Password tidak boleh kosong"; 
		die(json_encode($response));
	}
	
	$query = mysql_query("SELECT * FROM admin WHERE hp_teknisi='$username' AND password_teknisi='$password'");
	
	$row = mysql_fetch_array($query);
	
	if (!empty($row)){
		$response = new usr();
		$response->success = 1;
		$response->message = "Selamat datang ".$row['nama_teknisi'];
		$response->id = $row['nama_teknisi'];
		$response->username = $row['hp_teknisi'];
		$response->alamat = $row['alamat_teknisi'];
		$response->almt = $row['alamat_teknisi'];
		$response->password = $row['password_teknisi'];
		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->success = 0;
		$response->message = "No handphone atau password salah";
		die(json_encode($response));
	}
	
	mysql_close();


	//=================== KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI RI UNREMARK ========
	// include_once "koneksi.php";

	// class usr{}
	
	// $hp_konsumen = $_POST["hp_konsumen"];
	// $password = $_POST["password"];
	
	// if ((empty($hp_konsumen)) || (empty($password))) { 
	// 	$response = new usr();
	// 	$response->success = 0;
	// 	$response->message = "Kolom tidak boleh kosong"; 
	// 	die(json_encode($response));
	// }
	
	// $query = mysqli_query($con, "SELECT * FROM users WHERE hp_konsumen='$hp_konsumen' AND password='$password'");
	
	// $row = mysqli_fetch_array($query);
	
	// if (!empty($row)){
	// 	$response = new usr();
	// 	$response->success = 1;
	// 	$response->message = "Selamat datang ".$row['hp_konsumen'];
	// 	$response->id = $row['id'];
	// 	$response->hp_konsumen = $row['hp_konsumen'];
	// 	die(json_encode($response));
		
	// } else { 
	// 	$response = new usr();
	// 	$response->success = 0;
	// 	$response->message = "hp_konsumen atau password salah";
	// 	die(json_encode($response));
	// }
	
	// mysqli_close($con);

?>