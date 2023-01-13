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
$query_RecGaransi = "SELECT servis.kode_pesanan, pesan_servis.merk_ac, pesan_servis.kerusakan, servis.hp_teknisi, admin.nama_teknisi, servis.no_sparepart, sparepart.nama_sparepart, sparepart.harga, servis.hp_konsumen, konsumen.nama, servis.tanggal, servis.merk_acnya, servis.kerusakannya, servis.no_servis FROM servis INNER JOIN pesan_servis ON (servis.kode_pesanan = pesan_servis.kode_pesanan) INNER JOIN admin ON (servis.hp_teknisi = admin.hp_teknisi) INNER JOIN konsumen ON (servis.hp_konsumen = konsumen.hp_konsumen) INNER JOIN sparepart ON (servis.no_sparepart = sparepart.no_sparepart)";
$RecGaransi = mysql_query($query_RecGaransi, $koneksi) or die(mysql_error());
$row_RecGaransi = mysql_fetch_assoc($RecGaransi);
$totalRows_RecGaransi = mysql_num_rows($RecGaransi);
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
  <h2>LAPORAN GARANSI</h2>
</center></u></strong></h2></div>
<div class="container">
<table width="100%" border="1" cellpadding="0" cellspacing="1" class="table table-striped table-bordered data">
  <thead><tr align="center" bgcolor="#00CCFF">
    <td><strong>Merk AC</strong></td>
    <td><strong>Kerusakan</strong></td>
    <td><strong>Teknisi</strong></td>
    <td><strong>Pemilik</strong></td>
    <td><strong>Tanggal</strong></td>
    <td><strong>Batas Garansi</strong></td>
    <td><strong>Status Garansi</strong></td>
    </tr></thead>
  <?php do { ?>
    <tr align="center">
      <td><?php echo $row_RecGaransi['merk_ac']; ?></td>
      <td align="left"><?php echo $row_RecGaransi['kerusakan']; ?></td>
      <td><?php echo $row_RecGaransi['nama_teknisi']; ?></td>
      <td><?php echo $row_RecGaransi['nama']; ?></td>
      <td><?php echo $row_RecGaransi['tanggal']; ?></td>
      <?php $b = $row_RecGaransi['tanggal']; ?>
      <?php $a = date('Y-m-d', strtotime('+1 month', strtotime($b))); ?>
      <?php $d = date('Y-m-d'); ?>
      <?php $e = date('Y-m-d', strtotime('+0 month', strtotime($d))); ?>
      <?php if ($e>$a) {
		  $c="Lewat Garansi";
	  } else {
		  $c="Masih Garansi";
	  }?>
      <td><?php echo $a; ?></td>
      <td><?php echo $c; ?></td>
      </tr>
    <?php } while ($row_RecGaransi = mysql_fetch_assoc($RecGaransi)); ?>
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
mysql_free_result($RecGaransi);
?>
