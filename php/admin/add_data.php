<?php
	include_once('connection.php');
	
	$hp_teknisi = $_GET['hp_teknisi'];
	$password = $_GET['password'];
	$nama = $_GET['nama'];
	$alamat = $_GET['alamat'];
	$level = $_GET['level'];
	
	$insert = "INSERT INTO admin(hp_teknisi,password,nama,alamat,level) VALUES ('$hp_teknisi','$password','$nama','$alamat','$level')";
	$exeinsert = mysqli_query($koneksi,$insert);
	
	$response = array();
	
	if($exeinsert)
	{
		$response['code']=1;
		$response['message']="Data Berhasil ditambahkan";
	}
	else
	{
		$response['code']=0;
		$response['message']="Data Gagal ditambahkan";
	}
		
	echo json_encode($response);
	
?>