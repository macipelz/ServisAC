-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 08, 2019 at 03:42 PM
-- Server version: 10.3.13-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id7743565_servis_ac`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `hp_teknisi` varchar(50) NOT NULL,
  `password_teknisi` varchar(50) NOT NULL,
  `nama_teknisi` varchar(50) NOT NULL,
  `alamat_teknisi` varchar(50) NOT NULL,
  `level` varchar(10) NOT NULL DEFAULT 'Teknisi'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`hp_teknisi`, `password_teknisi`, `nama_teknisi`, `alamat_teknisi`, `level`) VALUES
('082357410843', '222', 'Rizal', 'Jl Kelayan B', 'Teknisi'),
('087815201597', '1234', 'MacipeLz', 'Kelayan B', 'Teknisi'),
('08980569589', '123', 'Chandra MacipeLz', 'Jl Kelayan B Komplek 10 No 76', 'Teknisi'),
('1', '1', 'Admin', 'Jl Kelayan B', 'Teknisi'),
('2', '2', 'Aliandry', 'Kelayan', 'Teknisi');

-- --------------------------------------------------------

--
-- Table structure for table `konsumen`
--

CREATE TABLE `konsumen` (
  `hp_konsumen` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `konsumen`
--

INSERT INTO `konsumen` (`hp_konsumen`, `password`, `nama`, `alamat`) VALUES
('$hp_konsumen', '$password', '$nama', '$alamat'),
('082298780722', 'yusni2007', 'Khairul Yusni', 'Teluk Tiram'),
('085250891507', '123', 'A', 'A'),
('089669125018', 'qwertyuiop', 'A', 'A'),
('08980569589', '1234', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76'),
('08983191713', 'arulhosa', 'Hairullah', 'Jl. Kelayan B Komplek 10 '),
('1', '1', 'Admin', '-'),
('11', '11', 'Ikam', 'Gak Tau'),
('1234', '123', 'Chandra MacipeLz', 'Jl. Kelayan B Komplek 10'),
('12345', 'a', 'A', 'A'),
('8765', '123', 'Safrudin', 'Jl. Pekauman'),
('9090', '90', 'Chan', 'Jl. Handil Bakti'),
('coba', '1', 'Coba', 'Coba');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `no_pembayaran` int(11) NOT NULL,
  `tgl` date NOT NULL,
  `no_servis` int(11) NOT NULL,
  `biaya` varchar(50) NOT NULL,
  `total` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`no_pembayaran`, `tgl`, `no_servis`, `biaya`, `total`) VALUES
(1, '2018-11-03', 1, '60000', '60000'),
(2, '2018-12-02', 2, '10000', '70000'),
(3, '2018-12-04', 3, '60000', '60000'),
(4, '2018-12-04', 4, '50000', '300000'),
(5, '2019-01-28', 8, '60000', '70000'),
(6, '2019-01-28', 7, '60000', '70000'),
(7, '2019-01-28', 6, '10000', '70000'),
(8, '2019-01-28', 7, '80000', '140000'),
(9, '2019-01-28', 5, '20000', '80000'),
(10, '2019-01-28', 1, '50000', '110000'),
(19, '2019-01-29', 8, '5000', '65000'),
(20, '2019-01-29', 9, '10000', '70000'),
(21, '2019-01-29', 10, '10000', '70000'),
(22, '2019-01-31', 11, '10000', '70000'),
(23, '2019-01-31', 12, '10000', '70000'),
(24, '2019-01-31', 13, '10000', '260000'),
(25, '2019-02-02', 15, '60000', '60000'),
(26, '2019-02-04', 16, '1000', '151000'),
(27, '2019-02-20', 20, '10000', '260000'),
(28, '2019-02-20', 20, '10000', '260000'),
(29, '2019-02-20', 20, '10000', '260000'),
(30, '2019-02-20', 21, '60000', '60000'),
(31, '2019-02-20', 22, '60000', '60000');

-- --------------------------------------------------------

--
-- Table structure for table `pesan_servis`
--

CREATE TABLE `pesan_servis` (
  `kode_pesanan` int(11) NOT NULL,
  `merk_ac` varchar(50) NOT NULL,
  `kerusakan` varchar(200) NOT NULL,
  `status_servis` varchar(50) NOT NULL,
  `hp_konsumen` varchar(50) NOT NULL,
  `pemilik` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `tgl_servis` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pesan_servis`
--

INSERT INTO `pesan_servis` (`kode_pesanan`, `merk_ac`, `kerusakan`, `status_servis`, `hp_konsumen`, `pemilik`, `alamat`, `tgl_servis`) VALUES
(1, 'LG', 'Freon Bocor', 'Sedang Diservis', '11', 'aa', 'aa', '2018-11-03'),
(30, 'Midea', 'Freon habis, tidak dingin', 'Selesai', '08980569589', 'Muhammad Chandra Aliandry', 'Jl. Kelayan B Komplek 10 ', '2018-11-23'),
(31, 'Chang Hong', 'Kotor', 'Sedang Diservis', '1', 'Darma', 'Jl. Handil Bakti', '2018-11-23'),
(52, 'LG', 'Kotor', 'Selesai', '08980569589', 'Chandra Aliandry', 'Jl. Kelayan B Komplek 10 No 76', '2018-12-06'),
(55, 'Daikin', 'Kotor', 'Sedang Diservis', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-01-07'),
(56, 'Tes', 'Kotor', 'Sedang Diservis', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-01-08'),
(57, 'Tes 2', 'Kotor', 'Sedang Diservis', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-01-08'),
(58, 'Tes 3', 'Ahaja', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-01-08'),
(59, 'Udin', '124', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-01-09'),
(60, 'National', 'Kotor', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-01-27'),
(61, 'Tes', 'Tes', 'Selesai', '1', 'Admin', 'Jl Kelayan B', '2019-01-28'),
(62, 'LG', 'Kotot', 'Selesai', '12345', 'A', 'A', '2019-01-29'),
(63, 'Darma', 'Darma', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-01-29'),
(67, 'Sanyo', 'Kotor', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-01-31'),
(69, 'Aqua Japan', 'Kotor', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-01-31'),
(70, 'Toshiba', 'Freon Habis', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-01-31'),
(72, 'LG', 'Kotor', 'Sedang Diservis', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-02-02'),
(73, 'Toshiba', 'Kotor', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-02-02'),
(75, 'New', 'New', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-02-04'),
(76, 'Kg k', 'Ugff', 'Sedang Diservis', '1', 'Admin', '-', '2019-02-18'),
(77, 'Ghg', 'Hjj', 'Selesai', '1', 'Admin', '-', '2019-02-18'),
(78, 'LG', 'Kotor', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-02-20'),
(79, 'LG', 'Kotor', 'Selesai', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-02-20'),
(80, 'Gjbjkkjjk', 'Hjjjjj', 'Selesai', '1', 'Admin', 'Jl Kelayan B', '2019-02-20'),
(81, 'LG', 'Kotor', 'Sedang Dipesan', '08980569589', 'M. Chandra Aliandry', 'Jl Kelayan B Komplek 10 No 76', '2019-02-21');

-- --------------------------------------------------------

--
-- Table structure for table `servis`
--

CREATE TABLE `servis` (
  `no_servis` int(11) NOT NULL,
  `kode_pesanan` int(11) NOT NULL,
  `merk_acnya` varchar(50) NOT NULL,
  `hp_teknisi` varchar(50) NOT NULL,
  `kerusakannya` varchar(200) NOT NULL,
  `no_sparepart` int(11) NOT NULL,
  `hp_konsumen` varchar(50) NOT NULL,
  `tanggal` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `servis`
--

INSERT INTO `servis` (`no_servis`, `kode_pesanan`, `merk_acnya`, `hp_teknisi`, `kerusakannya`, `no_sparepart`, `hp_konsumen`, `tanggal`) VALUES
(1, 52, 'LG', '08980569589', 'kotor', 1, '11', '2018-11-03'),
(2, 31, 'Chang Hong', '082357410843', 'AC kotor dan tidak dingin', 1, '085250891507', '2018-11-30'),
(3, 52, 'LG', '082357410843', 'Kotor', 1, '08980569589', '2018-11-30'),
(4, 30, 'Midea', '082357410843', 'Freon habis, tidak dingin', 3, '08980569589', '2018-11-30'),
(5, 58, 'Tes 3', '08980569589', 'Ahaja', 1, '08980569589', '2019-01-08'),
(6, 59, 'Udin', '08980569589', '124', 1, '08980569589', '2019-01-10'),
(7, 60, 'National', '1', 'Kotor', 1, '08980569589', '2019-01-28'),
(8, 61, 'Tes', '1', 'Tes', 1, '1', '2019-01-28'),
(9, 62, 'LG', '1', 'Freon', 2, '12345', '2019-01-29'),
(10, 63, 'Darma', '1', 'Darma', 1, '08980569589', '2019-01-29'),
(11, 67, 'Sanyo', '087815201597', 'Kotor', 1, '08980569589', '2019-01-31'),
(12, 69, 'Aqua Japan', '087815201597', 'Kotor', 1, '08980569589', '2019-01-31'),
(13, 70, 'Toshiba', '087815201597', 'Freon Habis', 3, '08980569589', '2019-01-31'),
(14, 72, 'LG', '1', 'Kotor', 1, '08980569589', '2019-02-02'),
(15, 73, 'Toshiba', '087815201597', 'Kotor', 1, '08980569589', '2019-02-02'),
(16, 75, 'New', '1', 'New', 2, '08980569589', '2019-02-04'),
(17, 77, 'Ghg', '1', 'Hjj', 2, '1', '2019-02-18'),
(19, 31, 'Chang Hong', '082357410843', 'Kotor', 3, '089669125018', '2019-01-29'),
(20, 78, 'LG', '1', 'Kotor', 3, '08980569589', '2019-02-20'),
(21, 79, 'LG', '1', 'Kotor', 1, '08980569589', '2019-02-20'),
(22, 80, 'Gjbjkkjjk', '1', 'Hjjjjj', 1, '1', '2019-02-20');

--
-- Triggers `servis`
--
DELIMITER $$
CREATE TRIGGER `TG_HAPUS` AFTER DELETE ON `servis` FOR EACH ROW BEGIN
 UPDATE sparepart SET stok=stok+1
 WHERE no_sparepart=OLD.no_sparepart;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `TG_PAKAI` AFTER INSERT ON `servis` FOR EACH ROW BEGIN
 UPDATE sparepart SET stok=stok-1
 WHERE no_sparepart=NEW.no_sparepart;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `sparepart`
--

CREATE TABLE `sparepart` (
  `no_sparepart` int(11) NOT NULL,
  `nama_sparepart` varchar(50) NOT NULL,
  `harga` varchar(50) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sparepart`
--

INSERT INTO `sparepart` (`no_sparepart`, `nama_sparepart`, `harga`, `stok`) VALUES
(1, 'Cuci Saja', '0', 9998),
(2, 'Tambah Freon', '150000', 18),
(3, 'Isi Freon Kosong', '250000', 19);

-- --------------------------------------------------------

--
-- Table structure for table `sparepart_masuk`
--

CREATE TABLE `sparepart_masuk` (
  `no_masuk` int(11) NOT NULL,
  `no_sparepart` int(11) NOT NULL,
  `nama_sparepart` varchar(50) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `tgl` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sparepart_masuk`
--

INSERT INTO `sparepart_masuk` (`no_masuk`, `no_sparepart`, `nama_sparepart`, `jumlah`, `tgl`) VALUES
(1, 1, 'Cuci Saja', 9999, '2018-11-03'),
(2, 3, 'Isi Freon Kosong', 10, '2019-02-20'),
(4, 2, 'Tambah Freon', 8, '2019-02-20');

--
-- Triggers `sparepart_masuk`
--
DELIMITER $$
CREATE TRIGGER `TG_DELETE` AFTER DELETE ON `sparepart_masuk` FOR EACH ROW BEGIN
 UPDATE sparepart SET stok=stok-OLD.jumlah
 WHERE no_sparepart=OLD.no_sparepart;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `TG_EDIT` AFTER UPDATE ON `sparepart_masuk` FOR EACH ROW BEGIN
 UPDATE sparepart SET stok=stok-OLD.jumlah+NEW.jumlah
 WHERE no_sparepart=NEW.no_sparepart;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `TG_TAMBAH` AFTER INSERT ON `sparepart_masuk` FOR EACH ROW BEGIN
 UPDATE sparepart SET stok=stok+NEW.jumlah
 WHERE no_sparepart=NEW.no_sparepart;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`hp_teknisi`);

--
-- Indexes for table `konsumen`
--
ALTER TABLE `konsumen`
  ADD PRIMARY KEY (`hp_konsumen`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`no_pembayaran`),
  ADD KEY `FK_pembayaran_servis` (`no_servis`);

--
-- Indexes for table `pesan_servis`
--
ALTER TABLE `pesan_servis`
  ADD PRIMARY KEY (`kode_pesanan`),
  ADD KEY `FK__konsumen` (`hp_konsumen`);

--
-- Indexes for table `servis`
--
ALTER TABLE `servis`
  ADD PRIMARY KEY (`no_servis`),
  ADD KEY `FK_servis_admin` (`hp_teknisi`),
  ADD KEY `FK_servis_konsumen` (`hp_konsumen`),
  ADD KEY `FK_servis_pesan_servis` (`kode_pesanan`),
  ADD KEY `FK_servis_sparepart` (`no_sparepart`);

--
-- Indexes for table `sparepart`
--
ALTER TABLE `sparepart`
  ADD PRIMARY KEY (`no_sparepart`);

--
-- Indexes for table `sparepart_masuk`
--
ALTER TABLE `sparepart_masuk`
  ADD PRIMARY KEY (`no_masuk`),
  ADD KEY `FK_sparepart_masuk_sparepart` (`no_sparepart`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `no_pembayaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `pesan_servis`
--
ALTER TABLE `pesan_servis`
  MODIFY `kode_pesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT for table `servis`
--
ALTER TABLE `servis`
  MODIFY `no_servis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `sparepart`
--
ALTER TABLE `sparepart`
  MODIFY `no_sparepart` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sparepart_masuk`
--
ALTER TABLE `sparepart_masuk`
  MODIFY `no_masuk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `FK_pembayaran_servis` FOREIGN KEY (`no_servis`) REFERENCES `servis` (`no_servis`);

--
-- Constraints for table `pesan_servis`
--
ALTER TABLE `pesan_servis`
  ADD CONSTRAINT `FK__konsumen` FOREIGN KEY (`hp_konsumen`) REFERENCES `konsumen` (`hp_konsumen`);

--
-- Constraints for table `servis`
--
ALTER TABLE `servis`
  ADD CONSTRAINT `FK_servis_admin` FOREIGN KEY (`hp_teknisi`) REFERENCES `admin` (`hp_teknisi`),
  ADD CONSTRAINT `FK_servis_konsumen` FOREIGN KEY (`hp_konsumen`) REFERENCES `konsumen` (`hp_konsumen`),
  ADD CONSTRAINT `FK_servis_pesan_servis` FOREIGN KEY (`kode_pesanan`) REFERENCES `pesan_servis` (`kode_pesanan`),
  ADD CONSTRAINT `FK_servis_sparepart` FOREIGN KEY (`no_sparepart`) REFERENCES `sparepart` (`no_sparepart`);

--
-- Constraints for table `sparepart_masuk`
--
ALTER TABLE `sparepart_masuk`
  ADD CONSTRAINT `FK_sparepart_masuk_sparepart` FOREIGN KEY (`no_sparepart`) REFERENCES `sparepart` (`no_sparepart`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
