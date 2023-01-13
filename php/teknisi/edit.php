<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$username = $_POST['username'];
	$password = $_POST['password'];
	$nama = $_POST['nama'];
	$alamat = $_POST['alamat'];
	
	$server		= "localhost"; //sesuaikan dengan nama server
	$user		= "id7743565_chandra"; //sesuaikan username
	$pass   	= "datachan"; //sesuaikan password
	$database	= "id7743565_servis_ac"; //sesuaikan target databese
	
	$conn = mysqli_connect($server, $user, $pass, $database);
	
	$sql = "UPDATE admin SET nama_teknisi='$nama', alamat_teknisi='$alamat', password_teknisi='$password', level='Teknisi' WHERE hp_teknisi='$username'";
	
	if(mysqli_query($conn, $sql)){
		$result["success"] = "1";
		$result["message"] = "success";

		echo json_encode($result);
		mysqi_close($conn);
	} else {
		$result["success"] = "0";
		$result["message"] = "error";

		echo json_encode($result);
		mysqi_close($conn);
	}	
}
?>