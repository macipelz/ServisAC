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
$query_RecKonsumen = "SELECT hp_konsumen, nama, alamat FROM konsumen ORDER BY nama ASC";
$RecKonsumen = mysql_query($query_RecKonsumen, $koneksi) or die(mysql_error());
$row_RecKonsumen = mysql_fetch_assoc($RecKonsumen);
$totalRows_RecKonsumen = mysql_num_rows($RecKonsumen);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Laporan Konsumen</title>
</head>

<body>
<h2><strong><u><center>LAPORAN KONSUMEN</center></u></strong></h2>
<table width="100%" border="1" cellpadding="0" cellspacing="0">
  <tr valign="middle" bgcolor="#0099FF">
    <td height="30" align="center"><strong>No Handphone</strong></td>
    <td height="30" align="center"><strong>Nama</strong></td>
    <td height="30" align="center"><strong>Alamat</strong></td>
  </tr>
  <?php do { ?>
    <tr>
      <td><?php echo $row_RecKonsumen['hp_konsumen']; ?></td>
      <td><?php echo $row_RecKonsumen['nama']; ?></td>
      <td><?php echo $row_RecKonsumen['alamat']; ?></td>
    </tr>
    <?php } while ($row_RecKonsumen = mysql_fetch_assoc($RecKonsumen)); ?>
</table>
</body>
</html>
<?php
mysql_free_result($RecKonsumen);
?>
