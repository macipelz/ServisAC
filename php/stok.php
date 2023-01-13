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
$query_RecKeluar = "SELECT sparepart.nama_sparepart, servis.tanggal, sparepart.harga FROM servis INNER JOIN sparepart ON (servis.no_sparepart = sparepart.no_sparepart) where servis.no_sparepart<>'1'";
$RecKeluar = mysql_query($query_RecKeluar, $koneksi) or die(mysql_error());
$row_RecKeluar = mysql_fetch_assoc($RecKeluar);
$totalRows_RecKeluar = mysql_num_rows($RecKeluar);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.js"></script>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/dataTables.bootstrap.css">
<title>Laporan Stok</title>
</head>

<body>
<div class="container"><img src="https://servis-ac.000webhostapp.com/images/header.jpg" width="100%" height="100" />
<h2><strong><u><center>
  <h2>LAPORAN SPAREPART KELUAR</h2>
</center></u></strong></h2></div>
<div class="container">
<table class="table table-striped table-bordered data" width="100%" border="1" cellpadding="0" cellspacing="1">
  <thead><tr>
    <td align="center" bgcolor="#00CCFF">No</td>
    <td align="center" bgcolor="#00CCFF">Nama Sparepart</td>
    <td align="center" bgcolor="#00CCFF">Tanggal Keluar</td>
    <td align="center" bgcolor="#00CCFF">Harga</td>
  </tr></thead>
  <?php $no=1; do { ?>
    <tr>
	  <td align="center"><?php echo $no; ?></td>
      <td align="center"><?php echo $row_RecKeluar['nama_sparepart']; ?></td>
      <td align="center"><?php echo $row_RecKeluar['tanggal']; ?></td>
      <td align="right"><?php echo (integer) $row_RecKeluar['harga']; ?></td>
    </tr>
    <?php $no++; } while ($row_RecKeluar = mysql_fetch_assoc($RecKeluar)); ?>
</table>
<p></p>
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
mysql_free_result($RecKeluar);
?>
