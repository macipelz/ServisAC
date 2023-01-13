<!DOCTYPE html>
<html>
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
              <td><strong>Merk Ac</strong></td>
              <td><strong>Kerusakan</strong></td>
              <td><strong>Pemilik</strong></td>
              <td><strong>Teknisi</strong></td>
              <td><strong>Sparepart</strong></td>
              <td><strong>Tanggal</strong></td>
              <td><strong>Harga</strong></td>
              <td><strong>Biaya</strong></td>
              <td><strong>Total</strong></td>
	      </tr>
			<?php 
			$no = 1;
 
			if(isset($_GET['tanggal1'], $_GET['tanggal2'])){
				$tgl1 = $_GET['tanggal1'];
				$tgl2 = $_GET['tanggal2'];
				$sql = mysqli_query($koneksi,"SELECT konsumen.hp_konsumen, konsumen.nama, pembayaran.no_pembayaran, servis.kerusakannya, admin.nama_teknisi, servis.merk_acnya, sparepart.nama_sparepart, sparepart.harga, pembayaran.biaya, pembayaran.total, pembayaran.tgl FROM pembayaran INNER JOIN servis ON (pembayaran.no_servis = servis.no_servis) INNER JOIN sparepart ON (servis.no_sparepart = sparepart.no_sparepart) INNER JOIN admin ON (servis.hp_teknisi = admin.hp_teknisi) INNER JOIN konsumen ON (servis.hp_konsumen = konsumen.hp_konsumen) where tgl between'$tgl1' and '$tgl2'");
			}else{
				$sql = mysqli_query($koneksi,"SELECT konsumen.hp_konsumen, konsumen.nama, pembayaran.no_pembayaran, servis.kerusakannya, admin.nama_teknisi, servis.merk_acnya, sparepart.nama_sparepart, sparepart.harga, pembayaran.biaya, pembayaran.total, pembayaran.tgl FROM pembayaran INNER JOIN servis ON (pembayaran.no_servis = servis.no_servis) INNER JOIN sparepart ON (servis.no_sparepart = sparepart.no_sparepart) INNER JOIN admin ON (servis.hp_teknisi = admin.hp_teknisi) INNER JOIN konsumen ON (servis.hp_konsumen = konsumen.hp_konsumen)");
			}
			$jml = 0;
			while($data = mysqli_fetch_array($sql)){
			$jml +=$data['total'];
			?>
			<tr>
				<td height="25"  align="center"><?php echo $no++; ?></td>
                <td align="center"><?php echo $data['merk_acnya']; ?></td>
                <td align="left"><?php echo $data['kerusakannya']; ?></td>
                <td align="center"><?php echo $data['nama']; ?></td>
                <td align="center"><?php echo $data['nama_teknisi']; ?></td>
                <td align="center"><?php echo $data['nama_sparepart']; ?></td>
                <td align="center"><?php echo $data['tgl']; ?></td>
                <td align="right"><?php echo "Rp. " . number_format($data['harga'],0,',','.'); ?></td>
                <td align="right"><?php echo "Rp. " . number_format($data['biaya'],0,',','.'); ?></td>
                <td align="right"><?php echo "Rp. " . number_format($data['total'],0,',','.'); ?></td>
			</tr>
			<?php 
			}
			?>
              <td colspan="9"  align="right"><strong>Subtotal : </strong></td>
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