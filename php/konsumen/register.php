<?php
	/* include 'koneksi.php';
	
	class usr{}
	
	$hp_konsumen = $_POST["hp_konsumen"];
	$nama = $_POST["nama"];
	$alamat = $_POST["alamat"]
	$password = $_POST["password"];
	$c_password = $_POST["c_password"];
	
	if ((empty($hp_konsumen))) { 
		$result = new usr();
		$result->success = 0;
		$result->message = "Kolom No HP tidak boleh kosong"; 
		die(json_encode($result));
	} else if ((empty($nama))) { 
		$result = new usr();
		$result->success = 0;
		$result->message = "Kolom Nama tidak boleh kosong"; 
		die(json_encode($result));
	} else if ((empty($alamat))) { 
		$result = new usr();
		$result->success = 0;
		$result->message = "Kolom Alamat tidak boleh kosong"; 
		die(json_encode($result));
	} else if ((empty($password))) { 
		$result = new usr();
		$result->success = 0;
		$result->message = "Kolom password tidak boleh kosong"; 
		die(json_encode($result));
	} else if ((empty($c_password)) || $password != $c_password) { 
		$result = new usr();
		$result->success = 0;
		$result->message = "Konfirmasi password tidak sama"; 
		die(json_encode($result));
	} else {
		if (!empty($hp_konsumen) && !empty($nama) && !empty($alamat) && $password == $c_password){
			$query = mysql_query("INSERT INTO konsumen (hp_konsumen, password, nama, alamat) VALUES('".$hp_konsumen."','".$password."','".$nama."','".$alamat."')");
			
			if ($query){
				$result = new usr();
				$result->success = 1;
				$result->message = "Berhasil mendaftar, silahkan login.";
				die(json_encode($result));
				
			} else { 
				$result = new usr();
				$result->success = 0;
				$result->message = "No Handphone sudah pernah terdaftar";
				die(json_encode($result));
			}
		}	
	}
	
	mysql_close(); */

	include_once "koneksi.php";

	class usr{}
	
	$hp_konsumen = $_POST["hp_konsumen"];
	$nama = $_POST["nama"];
	$alamat = $_POST["alamat"];
	$password = $_POST["password"];
	$c_password = $_POST["c_password"];
	
	if ((empty($hp_konsumen))) { 
		$result = new usr();
		$result->success = 0;
		$result->message = "Kolom No Handphone tidak boleh kosong"; 
		die(json_encode($result));
	} else if ((empty($nama))) { 
		$result = new usr();
		$result->success = 0;
		$result->message = "Kolom Nama tidak boleh kosong"; 
		die(json_encode($result));
	} else if ((empty($alamat))) { 
		$result = new usr();
		$result->success = 0;
		$result->message = "Kolom Alamat tidak boleh kosong"; 
		die(json_encode($result));
	} else if ((empty($password))) { 
		$result = new usr();
		$result->success = 0;
		$result->message = "Kolom password tidak boleh kosong"; 
		die(json_encode($result));
	} else if ((empty($c_password)) || $password != $c_password) { 
		$result = new usr();
		$result->success = 0;
		$result->message = "Konfirmasi password tidak sama"; 
		die(json_encode($result));
	} else {
		if (!empty($hp_konsumen) && $password == $c_password){
			$query = mysqli_query($con, "INSERT INTO konsumen (hp_konsumen, password, nama, alamat) VALUES('".$hp_konsumen."','".$password."','".$nama."','".$alamat."')");
			
			if ($query){
				$result = new usr();
				$result->success = 1;
				$result->message = "Register berhasil, silahkan login.";
				die(json_encode($result));
				
			} else { 
				$result = new usr();
				$result->success = 0;
				$result->message = "No Handphone sudah ada";
				die(json_encode($result));
			}
		}	
	}

	mysqli_close($con);

?>	