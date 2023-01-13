<?php
	include_once('connection.php');
	
	$hp_teknisi = $_GET['hp_teknisi'];
	
	$getdata = mysqli_query($koneksi,"SELECT * FROM admin WHERE hp_teknisi = '$hp_teknisi'");
	$rows = mysqli_num_rows($getdata);
	
	$delete = "DELETE FROM admin WHERE hp_teknisi = '$hp_teknisi'";
	$exedelete = mysqli_query($koneksi,$delete);
	
	$respose = array();
	
	if($rows > 0)
	{
		if($exedelete)
		{
			$respose['code']=1;
			$respose['message']="Berhasil dihapus";
		}
		else
		{
			$respose['code']=0;
			$respose['message']="Gagal dihapus";
		}
	}
	else
	{
		$respose['code']=0;
		$respose['message']="Gagal dihapus, Data Tidak ditemukan";
	}
	
	
	echo json_encode($respose);
	
?>