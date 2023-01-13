<?php
	require_once 'setting_server.php';

	$query = "SELECT servis.kode_pesanan, pesan_servis.merk_ac, pesan_servis.kerusakan, servis.hp_teknisi, admin.nama_teknisi, servis.no_sparepart, sparepart.nama_sparepart, sparepart.harga, servis.hp_konsumen, konsumen.nama, servis.tanggal, servis.merk_acnya, servis.kerusakannya, servis.no_servis FROM servis INNER JOIN pesan_servis ON (servis.kode_pesanan = pesan_servis.kode_pesanan) INNER JOIN admin ON (servis.hp_teknisi = admin.hp_teknisi) INNER JOIN konsumen ON (servis.hp_konsumen = konsumen.hp_konsumen) INNER JOIN sparepart ON (servis.no_sparepart = sparepart.no_sparepart) ORDER BY tanggal DESC";

	$query2 = "SELECT * FROM servis ORDER BY tanggal DESC";

	$sql = mysqli_query($con, $query);

	$ray = array();

	while ($row = mysqli_fetch_array($sql)){
		array_push($ray, array(
			"no_srv" => $row['no_servis'],
			"kd_psn" => $row['kode_pesanan'],
			"merk" => $row['merk_ac'],
			"merkx" => $row['merk_acnya'],
			"rusak" => $row['kerusakan'],
			"rusakx" => $row['kerusakannya'],
			"hp_tek" => $row['hp_teknisi'],
			"nm_tek" => $row['nama_teknisi'],
			"hp_kon" => $row['hp_konsumen'],
			"nm" => $row['nama'],
			"no_spt" => $row['no_sparepart'],
			"nm_spt" => $row['nama_sparepart'],
			"hrg" => $row['harga'],
			"tgl" => $row['tanggal']
		));
	}

	echo json_encode($ray);

	mysqli_close($con);
?>