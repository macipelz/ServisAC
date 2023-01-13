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
$query_RecSparepart = "SELECT nama_sparepart, harga, stok FROM sparepart";
$RecSparepart = mysql_query($query_RecSparepart, $koneksi) or die(mysql_error());
$row_RecSparepart = mysql_fetch_assoc($RecSparepart);
$totalRows_RecSparepart = mysql_num_rows($RecSparepart);
$query_RecFreon = "SELECT * FROM servis WHERE no_sparepart='2'";
$RecFreon= mysql_query($query_RecFreon, $koneksi) or die(mysql_error());
$row_RecFreon = mysql_fetch_assoc($RecFreon);
$totalRows_RecFreon = mysql_num_rows($RecFreon);
$query_RecFreon = "SELECT * FROM servis WHERE no_sparepart='3'";
$RecIsi= mysql_query($query_RecIsi, $koneksi) or die(mysql_error());
$row_RecIsi = mysql_fetch_assoc($RecIsi);
$totalRows_RecIsi = mysql_num_rows($RecIsi);
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
  <h2>LAPORAN SPAREPART</h2>
</center></u></strong></h2>
<div class="container">
  <table class="table table-striped table-bordered data" width="100%" border="1" cellpadding="0" cellspacing="1">
    <thead><tr align="center">
      <td bgcolor="#00CCFF">No</td>
      <td bgcolor="#00CCFF">Nama Sparepart</td>
      <td bgcolor="#00CCFF">Harga</td>
      <td bgcolor="#00CCFF">Stok</td>
    </tr></thead>
    <?php $no=1; do { ?>
      <tr>
        <td align="center"><?php echo $no; ?></td>
        <td><?php echo $row_RecSparepart['nama_sparepart']; ?></td>
        <td align="right"><?php echo $row_RecSparepart['harga']; ?></td>
        <?php $stok = $row_RecSparepart['stok']; ?>
		<?php if ($row_RecSparepart['nama_sparepart']="Cuci Saja") {
		  $stk = 0;
	    } else if ($row_RecSparepart['nama_sparepart']="Isi Freon") {
		  $stk = $totalRows_RecFreon;
	    } else {
		  $stk = $totalRows_RecIsi; 
		}?>
        <td align="center"><?php echo $stok-$stk ?></td>
      </tr>
      <?php $no++; } while ($row_RecSparepart = mysql_fetch_assoc($RecSparepart)); ?>
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
mysql_free_result($RecSparepart);
?>
