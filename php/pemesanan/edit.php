<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$kode = $_POST['kode'];

	$server		= "localhost"; //sesuaikan dengan nama server
	$user		= "id7743565_chandra"; //sesuaikan username
	$pass   	= "datachan"; //sesuaikan password
	$database	= "id7743565_servis_ac"; //sesuaikan target databese
	
	$conn = mysqli_connect($server, $user, $pass, $database);
	
	$sql = "UPDATE pesan_servis SET status_servis='Sedang Diservis' WHERE kode_pesanan='$kode'";
	
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