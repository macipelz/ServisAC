<?php
	
	$server		= "localhost"; //sesuaikan dengan nama server
	$user		= "id7743565_chandra"; //sesuaikan username
	$password	= "datachan"; //sesuaikan password
	$database	= "id7743565_servis_ac"; //sesuaikan target databese
	
	$connect = mysql_connect($server, $user, $password) or die ("Koneksi gagal!");
	mysql_select_db($database) or die ("Database belum siap!");
?>