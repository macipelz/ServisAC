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
$query_RecUpah = "SELECT admin.nama_teknisi,     pembayaran.tgl     , pembayaran.biaya     , pembayaran.total FROM     pembayaran     INNER JOIN servis          ON (pembayaran.no_servis = servis.no_servis)     INNER JOIN admin          ON (servis.hp_teknisi = admin.hp_teknisi);";
$RecUpah = mysql_query($query_RecUpah, $koneksi) or die(mysql_error());
$row_RecUpah = mysql_fetch_assoc($RecUpah);
$totalRows_RecUpah = mysql_num_rows($RecUpah);
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
  <h2>LAPORAN PERHITUNGAN UPAH TEKNISI</h2>
</center></u></strong></h2></div>
<div class="container">
<table class="table table-striped table-bordered data" width="100%" border="1" cellpadding="0" cellspacing="1">
  <thead><tr>
    <td align="center" bgcolor="#00CCFF"><strong>Nama Teknisi</strong></td>
    <td align="center" bgcolor="#00CCFF"><strong>Tanggal Servis</strong></td>
    <td align="center" bgcolor="#00CCFF"><strong>Biaya Servis</strong></td>
    <td align="center" bgcolor="#00CCFF"><strong>Total Pembayaran</strong></td>
    <td align="center" bgcolor="#00CCFF"><strong>Upah Servis <br />
      (Biaya*90%)</strong></td>
    <td align="center" bgcolor="#00CCFF"><strong>Upah Sparepart<br />
      (Harga Spt-Harga Beli)
    </strong></td>
    <td align="center" bgcolor="#00CCFF"><strong>Total Upah</strong></td>
  </tr></thead>
  <?php do { ?>
    <tr>
      <td><?php echo $row_RecUpah['nama_teknisi']; ?></td>
      <td align="center"><?php echo $row_RecUpah['tgl']; ?></td>
      <td align="right"><?php echo $row_RecUpah['biaya']; ?></td>
      <td align="right"><?php echo $row_RecUpah['total']; ?></td>
      <?php $a = (integer) $row_RecUpah['biaya']; ?>
      <?php $b = (integer) $row_RecUpah['total']; ?>
      <?php $harga = $b-$a; ?>
	  <?php $c = $a*90/100; ?>
      <?php $d = $harga/5; ?>
      <td align="right"><?php echo $c; ?></td>
      <td align="right"><?php echo $d; ?></td>
      <td align="right"><?php echo $c+$d; ?></td>
    </tr>
    <?php } while ($row_RecUpah = mysql_fetch_assoc($RecUpah)); ?>
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
mysql_free_result($RecUpah);
?>