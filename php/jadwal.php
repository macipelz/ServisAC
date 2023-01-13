<?php require_once('Connections/koneksi.php'); ?>
<?php
if (!function_exists("GetSQLValueString")) {
function GetSQLValueString($theValue, $theType, $theDefinedValue = "", $theNotDefinedValue = "") 
{
  if (PHP_VERSION < 6) {
    $theValue = get_magic_quotes_gpc() ? stripslashes($theValue) : $theValue;
  }

  $theValue = function_exists("mysql_real_escape_string") ? mysql_real_escape_string($theValue) : mysql_escape_string($theValue);

  switch ($theType) {
    case "text":
      $theValue = ($theValue != "") ? "'" . $theValue . "'" : "NULL";
      break;    
    case "long":
    case "int":
      $theValue = ($theValue != "") ? intval($theValue) : "NULL";
      break;
    case "double":
      $theValue = ($theValue != "") ? doubleval($theValue) : "NULL";
      break;
    case "date":
      $theValue = ($theValue != "") ? "'" . $theValue . "'" : "NULL";
      break;
    case "defined":
      $theValue = ($theValue != "") ? $theDefinedValue : $theNotDefinedValue;
      break;
  }
  return $theValue;
}
}

mysql_select_db($database_koneksi, $koneksi);
$query_RecJadwal = "SELECT servis.kode_pesanan, pesan_servis.merk_ac, pesan_servis.kerusakan, servis.hp_teknisi, admin.nama_teknisi, servis.no_sparepart, sparepart.nama_sparepart, sparepart.harga, servis.hp_konsumen, konsumen.nama, servis.tanggal, servis.merk_acnya, servis.kerusakannya, servis.no_servis FROM servis INNER JOIN pesan_servis ON (servis.kode_pesanan = pesan_servis.kode_pesanan) INNER JOIN admin ON (servis.hp_teknisi = admin.hp_teknisi) INNER JOIN konsumen ON (servis.hp_konsumen = konsumen.hp_konsumen) INNER JOIN sparepart ON (servis.no_sparepart = sparepart.no_sparepart)";
$RecJadwal = mysql_query($query_RecJadwal, $koneksi) or die(mysql_error());
$row_RecJadwal = mysql_fetch_assoc($RecJadwal);
$totalRows_RecJadwal = mysql_num_rows($RecJadwal);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.js"></script>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/dataTables.bootstrap.css">
<title>Laporan Konsumen</title>
</head>

<body>
<div class="container"><img src="https://servis-ac.000webhostapp.com/images/header.jpg" width="100%" height="100" />
<h2><strong><u><center>
  <h2>LAPORAN JADWAL SERVIS BERIKUTNYA</h2>
</center></u></strong></h2></div>
<div class="container">
  <table class="table table-striped table-bordered data" width="100%" border="1" cellpadding="0" cellspacing="1">
    <thead><tr align="center" bgcolor="#00CCFF">
      <td><strong>Merk AC</strong></td>
      <td><strong>Nama</strong></td>
      <td><strong>Terakhir Servis</strong></td>
      <td><strong>Jadwal Servis Berikutnya</strong></td>
    </tr></thead>
    <?php do { ?>
      <tr>
        <td height="24" align="center"><?php echo $row_RecJadwal['merk_ac']; ?></td>
        <td align="center"><?php echo $row_RecJadwal['nama']; ?></td>
        <td align="center"><?php echo $row_RecJadwal['tanggal']; ?></td>
        <?php $b = $row_RecJadwal['tanggal']; ?>
        <?php $a = date('Y-m-d', strtotime('+3 month', strtotime($b))); ?>
        <td align="center"><?php echo $a; ?></td>
      </tr>
      <?php } while ($row_RecJadwal = mysql_fetch_assoc($RecJadwal)); ?>
  </table>
<p>&nbsp;</p>
<p>&nbsp;</p>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$('.data').DataTable();
	});
</script>
</html>
<?php
mysql_free_result($RecJadwal);
?>
