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
$query_RecServis = "SELECT servis.kode_pesanan, pesan_servis.merk_ac, pesan_servis.kerusakan, servis.hp_teknisi, admin.nama_teknisi, servis.no_sparepart, sparepart.nama_sparepart, sparepart.harga, servis.hp_konsumen, konsumen.nama, servis.tanggal, servis.merk_acnya, servis.kerusakannya, servis.no_servis FROM servis INNER JOIN pesan_servis ON (servis.kode_pesanan = pesan_servis.kode_pesanan) INNER JOIN admin ON (servis.hp_teknisi = admin.hp_teknisi) INNER JOIN konsumen ON (servis.hp_konsumen = konsumen.hp_konsumen) INNER JOIN sparepart ON (servis.no_sparepart = sparepart.no_sparepart)";
$RecServis = mysql_query($query_RecServis, $koneksi) or die(mysql_error());
$row_RecServis = mysql_fetch_assoc($RecServis);
$totalRows_RecServis = mysql_num_rows($RecServis);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.js"></script>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/dataTables.bootstrap.css">
    <title>Laporan Servis</title>
</head>

<body>
<div class="container"><img src="https://servis-ac.000webhostapp.com/images/header.jpg" width="100%" height="100" /></div>
<h2><strong><u><center>
  <h2>LAPORAN SERVIS</h2>
</center></u></strong></h2>
<div class="container">
  <table class="table table-striped table-bordered data" width="100%" border="1" cellpadding="0" cellspacing="1">
    <thead><tr>
      <td align="center" bgcolor="#00CCFF"><strong>No</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Merk AC</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Kerusakan</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Teknisi</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Sparepart</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Pemilk</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Tanggal</strong></td>
    </tr></thead>
    <?php $no=1; do { ?>
      <tr>
        <td align="center"><?php echo $no; ?></td>
        <td align="center"><?php echo $row_RecServis['merk_ac']; ?></td>
        <td align="left"><?php echo $row_RecServis['kerusakan']; ?></td>
        <td align="center"><?php echo $row_RecServis['nama_teknisi']; ?></td>
        <td align="center"><?php echo $row_RecServis['nama_sparepart']; ?></td>
        <td align="center"><?php echo $row_RecServis['nama']; ?></td>
        <td align="center"><?php echo $row_RecServis['tanggal']; ?></td>
      </tr>
      <?php $no++; } while ($row_RecServis = mysql_fetch_assoc($RecServis)); ?>
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
mysql_free_result($RecServis);
?>
