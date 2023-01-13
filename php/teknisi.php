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
$query_RecTeknisi = "SELECT hp_teknisi, nama_teknisi, alamat_teknisi FROM `admin` ORDER BY nama_teknisi ASC";
$RecTeknisi = mysql_query($query_RecTeknisi, $koneksi) or die(mysql_error());
$row_RecTeknisi = mysql_fetch_assoc($RecTeknisi);
$totalRows_RecTeknisi = mysql_num_rows($RecTeknisi);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.js"></script>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/dataTables.bootstrap.css">
<title>Laporan Teknisi</title>
</head>

<body>
<div class="container"><img src="https://servis-ac.000webhostapp.com/images/header.jpg" width="100%" height="100" /></div>
<h2><strong><u><center>
  <h2>LAPORAN TEKNISI</h2>
</center></u></strong></h2>
<div class="container">
<table class="table table-striped table-bordered data" width="100%" border="1" cellpadding="0" cellspacing="1">
  <thead><tr>
    <td width="7%" align="center" bgcolor="#00CCFF"><strong>No</strong></td>
    <td align="center" bgcolor="#00CCFF"><strong>No Handphone</strong></td>
    <td align="center" bgcolor="#00CCFF"><strong>Nama</strong></td>
    <td align="center" bgcolor="#00CCFF"><strong>Alamat</strong></td>
  </tr></thead>
  <?php $no=1; do { ?>
    <tr>
      <td align="center"><?php echo $no; ?></td>
      <td align="center"><?php echo $row_RecTeknisi['hp_teknisi']; ?></td>
      <td align="center"><?php echo $row_RecTeknisi['nama_teknisi']; ?></td>
      <td align="center"><?php echo $row_RecTeknisi['alamat_teknisi']; ?></td>
    </tr>
    <?php $no++; } while ($row_RecTeknisi = mysql_fetch_assoc($RecTeknisi)); ?>
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
mysql_free_result($RecTeknisi);
?>
