<?php
/* ===== www.dedykuncoro.com ===== */
	include 'koneksi.php';
	
	class usr{}
	
	$username = $_POST["username"];
	$password = $_POST["password"];
	$nama = $_POST["nama"];
	$alamat = $_POST["alamat"];
	$confirm_password = $_POST["confirm_password"];
	
	if ((empty($username))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom No Handphone tidak boleh kosong"; 
		die(json_encode($response));
	} else if ((empty($nama))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom Nama tidak boleh kosong"; 
		die(json_encode($response));
	} else if ((empty($alamat))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom alamat tidak boleh kosong"; 
		die(json_encode($response));
	} else if ((empty($password))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom password tidak boleh kosong"; 
		die(json_encode($response));
	} else if ((empty($confirm_password)) || $password != $confirm_password) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Konfirmasi password tidak sama"; 
		die(json_encode($response));
	} else {
		if (!empty($username) && !empty($nama) && !empty($alamat) && $password == $confirm_password){
			$query = mysql_query("INSERT INTO admin (hp_teknisi, password_teknisi, nama_teknisi, alamat_teknisi, level) VALUES('".$username."','".$password."','".$nama."','".$alamat."','Teknisi')");
			
			if ($query){
				$response = new usr();
				$response->success = 1;
				$response->message = "Register berhasil, silahkan login.";
				die(json_encode($response));
				
			} else { 
				$response = new usr();
				$response->success = 0;
				$response->message = "No Handphone sudah pernah terdaftar";
				die(json_encode($response));
			}
		}	
	}
	
	mysql_close();


	/* ========= KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI DI UNREMARK ======== */
	// include_once "koneksi.php";

	// class usr{}
	
	// $hp_konsumen = $_POST["hp_konsumen"];
	// $password = $_POST["password"];
	// $confirm_password = $_POST["confirm_password"];
	
	// if ((empty($hp_konsumen))) { 
	// 	$response = new usr();
	// 	$response->success = 0;
	// 	$response->message = "Kolom hp_konsumen tidak boleh kosong"; 
	// 	die(json_encode($response));
	// } else if ((empty($password))) { 
	// 	$response = new usr();
	// 	$response->success = 0;
	// 	$response->message = "Kolom password tidak boleh kosong"; 
	// 	die(json_encode($response));
	// } else if ((empty($confirm_password)) || $password != $confirm_password) { 
	// 	$response = new usr();
	// 	$response->success = 0;
	// 	$response->message = "Konfirmasi password tidak sama"; 
	// 	die(json_encode($response));
	// } else {
	// 	if (!empty($hp_konsumen) && $password == $confirm_password){
	// 		$query = mysqli_query($con, "INSERT INTO users (id, hp_konsumen, password) VALUES(0,'".$hp_konsumen."','".$password."')");
			
	// 		if ($query){
	// 			$response = new usr();
	// 			$response->success = 1;
	// 			$response->message = "Register berhasil, silahkan login.";
	// 			die(json_encode($response));
				
	// 		} else { 
	// 			$response = new usr();
	// 			$response->success = 0;
	// 			$response->message = "hp_konsumen sudah ada";
	// 			die(json_encode($response));
	// 		}
	// 	}	
	// }

	// mysqli_close($con);

?>	