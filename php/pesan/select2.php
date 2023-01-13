<?php 
	include "koneksi.php";
	
	$hp_konsumen = $_POST["hp_konsumen"];
	
	$query = mysql_query("SELECT * FROM pesan_servis WHERE hp_konsumen='$hp_konsumen' ORDER BY tgl_servis DESC");
	
	$json = array();
	
	while($row = mysql_fetch_assoc($query)){
		$json[] = $row;
	}
	
	echo json_encode($json);
	
	mysql_close($connect);
	
?>