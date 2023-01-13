<?php
	require_once 'setting_server.php';

	$query = "SELECT * FROM pesan_servis WHERE status_servis = 'Sedang Dipesan' ORDER BY tgl_servis DESC";

	$sql = mysqli_query($con, $query);

	$ray = array();

	while ($row = mysqli_fetch_array($sql)){
		array_push($ray, array(
			"kd_psn" => $row['kode_pesanan'],
			"merk" => $row['merk_ac'],
			"rusak" => $row['kerusakan'],
			"stt" => $row['status_servis'],
			"hp_kon" => $row['hp_konsumen'],
			"nama" => $row['pemilik'],
			"almt" => $row['alamat'],
			"tgl" => $row['tgl_servis']
		));
	}

	echo json_encode($ray);

	mysqli_close($con);
?>