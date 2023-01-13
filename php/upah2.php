<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.js"></script>
	<script type="text/javascript" src="assets/DataTables/media/js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="assets/DataTables/media/css/dataTables.bootstrap.css">
    <title>LAPORAN PERHITUNGAN UPAH TEKNISI</title>
</head>

<body>
<div class="container"><img src="https://servis-ac.000webhostapp.com/images/header.jpg" width="100%" height="100" /></div>
<h2><strong><u><center>
  <h2>LAPORAN PERHITUNGAN UPAH TEKNISI</h2>
</center></u></strong></h2>
<div class="container"> 
<?php 
$host = "localhost";
$user = "id7743565_chandra";
$password = "datachan";
$database = "id7743565_servis_ac";
 
$koneksi = mysqli_connect($host,$user,$password,$database);
 
if($koneksi->connect_error){
	die("Koneksi gagal");
}
?>
		<center><form method="get">
            <label>NAMA TEKNISI</label>
			<input type="text" name="name">
			<label>PILIH TANGGAL</label>
			<input type="date" name="tanggal1">
            <label> s/d </label>
            <input type="date" name="tanggal2">
			<input type="submit" value="FILTER">
		</form></center>
 </br>
 		<table width="100%" border="1" cellpadding="0" cellspacing="1">
			<tr align="center" valign="middle" bgcolor="#00CCFF">
			  <td height="41" align="center"><strong>No</strong></td>
              <td><strong>Nama Teknisi</strong></td>
              <td><strong>Tanggal Servis</strong></td>
              <td><strong>Biaya Servis</strong></td>
              <td><strong>Total Pembayaran</strong></td>
              <td><strong>Upah Servis <br />(Biaya*90%</strong>)</td>
              <td><strong>Upah Sparepart<br />(Harga Spt-Harga Beli)</strong></td>
              <td><strong>Total Upah</strong></td>
             </tr>
			<?php 
			$no = 1;
 
			if(isset($_GET['name'], $_GET['tanggal1'], $_GET['tanggal2'])){
				$tgl1 = $_GET['tanggal1'];
				$tgl2 = $_GET['tanggal2'];
				$nm = $_GET['name'];
				$sql = mysqli_query($koneksi,"SELECT admin.nama_teknisi,     pembayaran.tgl     , pembayaran.biaya     , pembayaran.total FROM     pembayaran     INNER JOIN servis          ON (pembayaran.no_servis = servis.no_servis)     INNER JOIN admin          ON (servis.hp_teknisi = admin.hp_teknisi) where nama_teknisi='$nm' and tgl between'$tgl1' and '$tgl2'");
			}else{
				$sql = mysqli_query($koneksi,"SELECT admin.nama_teknisi,     pembayaran.tgl     , pembayaran.biaya     , pembayaran.total FROM     pembayaran     INNER JOIN servis          ON (pembayaran.no_servis = servis.no_servis)     INNER JOIN admin          ON (servis.hp_teknisi = admin.hp_teknisi)");
			}
			$jml = 0;
			while($data = mysqli_fetch_array($sql)){
			$a = (integer) $data['biaya'];
			$b = (integer) $data['total'];
            $harga = $b-$a;
            $c = $a*90/100;
            $d = $harga/5; 
            $e = $c+$d;
			$jml +=$e;
			?>
			<tr>
				<td height="25"  align="center"><?php echo $no++; ?></td>
                <td align="center"><?php echo $data['nama_teknisi']; ?></td>
                <td align="center"><?php echo $data['tgl']; ?></td>
                <td align="right"><?php echo "Rp. " . number_format($data['biaya'],0,',','.'); ?></td>
                <td align="right"><?php echo "Rp. " . number_format($data['total'],0,',','.'); ?></td>
	            <td align="right"><?php echo "Rp. " . number_format($c,0,',','.'); ?></td>
                <td align="right"><?php echo "Rp. " . number_format($d,0,',','.'); ?></td>
                <td align="right"><?php echo "Rp. " . number_format($e,0,',','.'); ?></td>
			</tr>
			<?php 
			}
			?>
              <td colspan="7"  align="right"><strong>Subtotal : </strong></td>
			  <td align="right"><?php echo "Rp. " . number_format($jml,0,',','.'); ?></td>
		</table>
 
	</center>
    <p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
<p>&nbsp;</p>
</div>
</body>
</html>