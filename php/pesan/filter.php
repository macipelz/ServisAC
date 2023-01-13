<?php 
	$server		= "localhost"; //sesuaikan dengan nama server
	$user		= "id7743565_chandra"; //sesuaikan username
	$password	= "datachan"; //sesuaikan password
	$database	= "id7743565_servis_ac"; //sesuaikan target databese

	$con = mysqli_connect($server, $user, $password, $database);
	if (mysqli_connect_errno()) {
		echo "Gagal terhubung MySQL: " . mysqli_connect_error();
	}

	$hp_konsumen = $_POST['keyword'];

	$query = mysqli_query($con, "SELECT * FROM pesan_servis WHERE hp_konsumen LIKE '%".$hp_konsumen."%'");

	$num_rows = mysqli_num_rows($query);

	if ($num_rows > 0){
		$json = '{"value":1, "results": [';

		while ($row = mysqli_fetch_array($query)){
			$char ='"';

			$json .= '{
				"kode_pesanan": "'.str_replace($char,'`',strip_tags($row['kode_pesanan'])).'",
				"merk": "'.str_replace($char,'`',strip_tags($row['merk_ac'])).'",
				"kerusakan": "'.str_replace($char,'`',strip_tags($row['kerusakan'])).'",
				"status_servis": "'.str_replace($char,'`',strip_tags($row['status_servis'])).'",
				"hp_konsumen": "'.str_replace($char,'`',strip_tags($row['hp_konsumen'])).'",
				"pemilik": "'.str_replace($char,'`',strip_tags($row['pemilik'])).'",
				"alamat": "'.str_replace($char,'`',strip_tags($row['alamat'])).'",
				"tgl_servis": "'.str_replace($char,'`',strip_tags($row['tgl_servis'])).'"
			},';
		}

		$json = substr($json,0,strlen($json)-1);

		$json .= ']}';

	} else {
		$json = '{"value":0, "message": "Data tidak ditemukan."}';
	}

	echo $json;

	mysqli_close($con);
?>