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
$query_RecPesan = "SELECT merk_ac, kerusakan, status_servis, hp_konsumen, pemilik, tgl_servis FROM pesan_servis";
$RecPesan = mysql_query($query_RecPesan, $koneksi) or die(mysql_error());
$row_RecPesan = mysql_fetch_assoc($RecPesan);
$totalRows_RecPesan = mysql_num_rows($RecPesan);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.js"></script>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/dataTables.bootstrap.css">
    <title>Laporan Pemesanan</title>
</head>

<body>
<div class="container"><img src="https://servis-ac.000webhostapp.com/images/header.jpg" width="100%" height="100" /></div>
<h2><strong><u><center>
  <h2>LAPORAN PESANAN SERVIS</h2>
</center></u></strong></h2>
<div class="container">
  <table class="table table-striped table-bordered data" width="100%" border="1" cellpadding="0" cellspacing="1">
    <thead><tr>
      <td align="center" bgcolor="#0099FF"><strong>No</strong></td>
      <td align="center" bgcolor="#0099FF"><strong>Merk Ac</strong></td>
      <td align="center" bgcolor="#0099FF"><strong>Kerusakan</strong></td>
      <td align="center" bgcolor="#0099FF"><strong>Status Servis</strong></td>
      <td align="center" bgcolor="#0099FF"><strong>No HP Pemilik</strong></td>
      <td align="center" bgcolor="#0099FF"><strong>Pemilik</strong></td>
      <td align="center" bgcolor="#0099FF"><strong>Tanggal</strong></td>
    </tr></thead>
    <?php $no=1; do { ?>
      <tr>
        <td align="center"><?php echo $no; ?></td>
        <td align="center"><?php echo $row_RecPesan['merk_ac']; ?></td>
        <td align="center"><?php echo $row_RecPesan['kerusakan']; ?></td>
        <td align="center"><?php echo $row_RecPesan['status_servis']; ?></td>
        <td align="center"><?php echo $row_RecPesan['hp_konsumen']; ?></td>
        <td align="center"><?php echo $row_RecPesan['pemilik']; ?></td>
        <td align="center"><?php echo $row_RecPesan['tgl_servis']; ?></td>
      </tr>
      <?php $no++; } while ($row_RecPesan = mysql_fetch_assoc($RecPesan)); ?>
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
mysql_free_result($RecPesan);
?>
