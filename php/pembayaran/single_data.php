<?php
	$no_byr = $_GET['no_byr'];
	
	require_once 'setting_server.php';

	$query = "SELECT konsumen.hp_konsumen, pembayaran.no_pembayaran, servis.kerusakannya, admin.nama_teknisi, servis.merk_acnya, sparepart.nama_sparepart, sparepart.harga, pembayaran.biaya, pembayaran.total, pembayaran.tgl FROM pembayaran INNER JOIN servis ON (pembayaran.no_servis = servis.no_servis) INNER JOIN sparepart ON (servis.no_sparepart = sparepart.no_sparepart) INNER JOIN admin ON (servis.hp_teknisi = admin.hp_teknisi) INNER JOIN konsumen ON (servis.hp_konsumen = konsumen.hp_konsumen)  WHERE pembayaran.no_pembayaran = '".$no_byr."'";

	$query2 = "SELECT * FROM servis ORDER BY tanggal DESC";

	$sql = mysqli_query($con, $query);

	$ray = array();

	while ($row = mysqli_fetch_array($sql)){
		array_push($ray, array(
			"no_byr" => $row['no_pembayaran'],
			"hrg" => $row['harga'],
			"bya" => $row['biaya'],
			"tot" => $row['total'],
			"merk" => $row['merk_acnya'],
			"hp_kon" => $row['hp_konsumen'],
			"rusak" => $row['kerusakannya'],
			"nm_tek" => $row['nama_teknisi'],
			"nm_spt" => $row['nama_sparepart'],
			"tgl" => $row['tgl']
		));
	}

	echo json_encode($ray);

	mysqli_close($con);
?>