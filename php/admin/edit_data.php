<?php
	include_once('connection.php');
	
	$hp_teknisi = $_GET['hp_teknisi'];
	$password = $_GET['password'];
	$nama = $_GET['nama'];
	$alamat = $_GET['alamat'];
	$level = $_GET['level'];
	
	$getdata = mysqli_query($koneksi,"SELECT * FROM admin WHERE hp_teknisi = '$hp_teknisi'");
	$rows = mysqli_num_rows($getdata);
	
	$update = "UPDATE admin SET hp_teknisi='$hp_teknisi',password='$password',nama='$nama',alamat='$alamat',level='$level' WHERE hp_teknisi = '$hp_teknisi'";
	$exeupdate = mysqli_query($koneksi,$update);
	
	$response = array();
	
	if($rows > 0)
	{
		if($exeupdate)
		{
			$response['code']=1;
			$response['message']="Data Berhasil diubah";
		}
		else
		{
			$response['code']=0;
			$response['message']="Data Gagal diubah";
		}
	}
	else
	{
		$response['code']=0;
		$response['message']="Gagal diubah, Data Tidak ditemukan";
	}
	
	echo json_encode($response);
	
?>