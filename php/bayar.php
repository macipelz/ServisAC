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
$query_RecBayar = "SELECT konsumen.hp_konsumen, konsumen.nama, pembayaran.no_pembayaran, servis.kerusakannya, admin.nama_teknisi, servis.merk_acnya, sparepart.nama_sparepart, sparepart.harga, pembayaran.biaya, pembayaran.total, pembayaran.tgl FROM pembayaran INNER JOIN servis ON (pembayaran.no_servis = servis.no_servis) INNER JOIN sparepart ON (servis.no_sparepart = sparepart.no_sparepart) INNER JOIN admin ON (servis.hp_teknisi = admin.hp_teknisi) INNER JOIN konsumen ON (servis.hp_konsumen = konsumen.hp_konsumen)";
$RecBayar = mysql_query($query_RecBayar, $koneksi) or die(mysql_error());
$row_RecBayar = mysql_fetch_assoc($RecBayar);
$totalRows_RecBayar = mysql_num_rows($RecBayar);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.js"></script>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/dataTables.bootstrap.css">
    <title>Laporan Transaksi Pembayaran</title>
</head>

<body>
<div class="container"><img src="https://servis-ac.000webhostapp.com/images/header.jpg" width="100%" height="100" /></div>
<h2><strong><u><center>
  <h2>LAPORAN TRANSAKSI PEMBAYARAN</h2>
</center></u></strong></h2>
<div class="container">
  <table class="table table-striped table-bordered data" width="100%" border="1" cellpadding="0" cellspacing="1" class="table table-striped table-bordered data">
    <thead><tr>
      <td align="center" bgcolor="#00CCFF"><strong>No</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Merk Ac</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Kerusakan</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Pemilik</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Teknisi</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Sparepart</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Harga</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Biaya</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Total</strong></td>
      <td align="center" bgcolor="#00CCFF"><strong>Tanggal</strong></td>
    </tr></thead>
    <?php $no=1; do { ?>
      <tr>
        <td align="center"><?php echo $no; ?></td>
        <td align="center"><?php echo $row_RecBayar['merk_acnya']; ?></td>
        <td align="left"><?php echo $row_RecBayar['kerusakannya']; ?></td>
        <td align="center"><?php echo $row_RecBayar['nama']; ?></td>
        <td align="center"><?php echo $row_RecBayar['nama_teknisi']; ?></td>
        <td align="center"><?php echo $row_RecBayar['nama_sparepart']; ?></td>
        <td align="right"><?php echo $row_RecBayar['harga']; ?></td>
        <td align="right"><?php echo $row_RecBayar['biaya']; ?></td>
        <td align="right"><?php echo $row_RecBayar['total']; ?></td>
        <td align="center"><?php echo $row_RecBayar['tgl']; ?></td>
      </tr>
      <?php $no++; } while ($row_RecBayar = mysql_fetch_assoc($RecBayar)); ?>
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
mysql_free_result($RecBayar);
?>
