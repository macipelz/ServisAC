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
<meta name=”viewport” content=”width=device-width; initial-scale=1.0; maximum-scale=1.0;”>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Laporan Teknisi</title>
</head>

<body>
<h2><strong><u><center>
  <h1>LAPORAN TEKNISI</h1>
</center></u></strong></h2>
<table width="100%" border="1" cellpadding="0" cellspacing="1">
  <tr>
    <td width="7%" height="30" align="center" bgcolor="#00CCFF"><h2><strong>No</strong></h2></td>
    <td align="center" bgcolor="#00CCFF"><h2>No Handphone</h2></td>
    <td align="center" bgcolor="#00CCFF"><h2>Nama</h2></td>
    <td align="center" bgcolor="#00CCFF"><h2>Alamat</h2></td>
  </tr>
  <?php $no=1; do { ?>
    <tr>
      <td align="center"><font size="+1"><?php echo $no; ?></font></td>
      <td align="center"><font size="+1"><?php echo $row_RecTeknisi['hp_teknisi']; ?></font></td>
      <td align="center"><font size="+1"><?php echo $row_RecTeknisi['nama_teknisi']; ?></font></td>
      <td align="center"><font size="+1"><?php echo $row_RecTeknisi['alamat_teknisi']; ?></font></td>
    </tr>
    <?php $no++; } while ($row_RecTeknisi = mysql_fetch_assoc($RecTeknisi)); ?>
</table>
</body>
</html>
<?php
mysql_free_result($RecTeknisi);
?>
