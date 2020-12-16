-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.21 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table seminarski_rad_sc.administrator
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE IF NOT EXISTS `administrator` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) DEFAULT NULL,
  `prezime` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table seminarski_rad_sc.administrator: ~0 rows (approximately)
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` (`id`, `ime`, `prezime`, `username`, `password`) VALUES
	(1, 'Aleksandar', 'Djurdjevic', 'admin', '$2y$12$tW/tz4Uoet/aKJ.8Ot95U.Mp1aST9OUJwKbJiPU3OR0Twq/eWBtRi');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;

-- Dumping structure for table seminarski_rad_sc.korisnik
DROP TABLE IF EXISTS `korisnik`;
CREATE TABLE IF NOT EXISTS `korisnik` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(150) DEFAULT NULL,
  `prezime` varchar(150) DEFAULT NULL,
  `jmbg` varchar(13) DEFAULT NULL,
  `pol` varchar(1) DEFAULT NULL,
  `adresa` varchar(250) DEFAULT NULL,
  `datum_rodjenja` date DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `mesto_id` int unsigned DEFAULT NULL,
  `rola_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `fk_mesto` (`mesto_id`),
  KEY `fk_rola_idx` (`rola_id`),
  CONSTRAINT `fk_mesto` FOREIGN KEY (`mesto_id`) REFERENCES `mesto` (`id`),
  CONSTRAINT `fk_rola` FOREIGN KEY (`rola_id`) REFERENCES `rola` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- Dumping data for table seminarski_rad_sc.korisnik: ~10 rows (approximately)
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` (`id`, `ime`, `prezime`, `jmbg`, `pol`, `adresa`, `datum_rodjenja`, `username`, `password`, `mesto_id`, `rola_id`) VALUES
	(1, 'Pera', 'Peric2', NULL, 'M', 'Najlepsa bb', NULL, 'pera', '$2y$12$tW/tz4Uoet/aKJ.8Ot95U.Mp1aST9OUJwKbJiPU3OR0Twq/eWBtRi', 1, 2),
	(7, 'Milan', 'Djurdjevic', '1411958203840', 'M', 'Mihajla Pupina 4/20', '1958-11-14', 'nalim', 'nalim', 2, 2),
	(11, 'djura', 'djura', NULL, 'M', 'djakovacka', NULL, 'djura', 'djura', 3, 2),
	(28, 'Alex', 'English', NULL, 'M', '5th Avenue', NULL, 'alex', 'alex', 4, 2),
	(29, 'Stojko', 'Stojkovic', '1203992807654', 'M', 'Makenzijeva', '1992-03-12', 'st', 'st', 1, 2),
	(30, 'Milan', 'Mikic', '0101994867543', 'M', 'Darvinova 3', '1994-01-01', 'miki', 'miki', 1, 2),
	(32, 'Danijela', 'Djurdjevic', '1506967980987', 'Z', 'Kralja Aleksandra 23', '1967-06-15', 'dr', 'dr', 1, 2),
	(33, 'Jeja', 'Dj', '3011989123123', 'Z', 'Djakovacka 2a', '1989-11-30', 'jeja', 'jeja', 1, 2),
	(34, 'Aleksandar', 'Dj', '38163123456', 'M', 'Test 2', '1990-01-01', 'admin', '$2y$12$tW/tz4Uoet/aKJ.8Ot95U.Mp1aST9OUJwKbJiPU3OR0Twq/eWBtRi', 1, 1),
	(36, 'S', 'S', NULL, 'Z', 'e', NULL, 'sa', '$2a$10$1UfQ2ZNI.YvyZzRCJnqjsuJ7YxVNjDkh9I7Ez5.C8jm1VNi84fvua', 3, 1);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;

-- Dumping structure for table seminarski_rad_sc.mesto
DROP TABLE IF EXISTS `mesto`;
CREATE TABLE IF NOT EXISTS `mesto` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(250) DEFAULT NULL,
  `postanski_broj` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table seminarski_rad_sc.mesto: ~5 rows (approximately)
/*!40000 ALTER TABLE `mesto` DISABLE KEYS */;
INSERT INTO `mesto` (`id`, `naziv`, `postanski_broj`) VALUES
	(1, 'Beograd', '11000'),
	(2, 'Novi Sad', '21000'),
	(3, 'Cacak', '32000'),
	(4, 'Nis', '18000'),
	(5, 'Smederevo', '11300');
/*!40000 ALTER TABLE `mesto` ENABLE KEYS */;

-- Dumping structure for table seminarski_rad_sc.rola
DROP TABLE IF EXISTS `rola`;
CREATE TABLE IF NOT EXISTS `rola` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `opis` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table seminarski_rad_sc.rola: ~2 rows (approximately)
/*!40000 ALTER TABLE `rola` DISABLE KEYS */;
INSERT INTO `rola` (`id`, `naziv`, `opis`) VALUES
	(1, 'ADMIN', 'Administrator sistema'),
	(2, 'CLAN', 'Clan kluba');
/*!40000 ALTER TABLE `rola` ENABLE KEYS */;

-- Dumping structure for table seminarski_rad_sc.usluga
DROP TABLE IF EXISTS `usluga`;
CREATE TABLE IF NOT EXISTS `usluga` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(250) DEFAULT NULL,
  `opis` varchar(250) DEFAULT NULL,
  `vrsta_usluge_id` int unsigned DEFAULT NULL,
  `aktivna` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vrsta_usluge` (`vrsta_usluge_id`),
  CONSTRAINT `fk_vrsta_usluge` FOREIGN KEY (`vrsta_usluge_id`) REFERENCES `vrsta_usluge` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table seminarski_rad_sc.usluga: ~4 rows (approximately)
/*!40000 ALTER TABLE `usluga` DISABLE KEYS */;
INSERT INTO `usluga` (`id`, `naziv`, `opis`, `vrsta_usluge_id`, `aktivna`) VALUES
	(1, 'uopa,uopa', 'ouopaaaasagash', 1, 0),
	(3, 'Kosarkaski teren', 'Koriscenje kosarkaskog terena', 2, 1),
	(4, 'Nova usluga 5', 'Opis neke nove usluge', 1, 1),
	(5, 'Nasa nova usluga 12', 'Opis nase najnovije usluge', 2, 1),
	(6, 'lepa 1', 'najlepsa', 2, 1);
/*!40000 ALTER TABLE `usluga` ENABLE KEYS */;

-- Dumping structure for table seminarski_rad_sc.vrsta_usluge
DROP TABLE IF EXISTS `vrsta_usluge`;
CREATE TABLE IF NOT EXISTS `vrsta_usluge` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `opis` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table seminarski_rad_sc.vrsta_usluge: ~2 rows (approximately)
/*!40000 ALTER TABLE `vrsta_usluge` DISABLE KEYS */;
INSERT INTO `vrsta_usluge` (`id`, `naziv`, `opis`) VALUES
	(1, 'VrstaUsluge1', NULL),
	(2, 'VrstaUsluge2', NULL);
/*!40000 ALTER TABLE `vrsta_usluge` ENABLE KEYS */;

-- Dumping structure for table seminarski_rad_sc.zahtev
DROP TABLE IF EXISTS `zahtev`;
CREATE TABLE IF NOT EXISTS `zahtev` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(150) DEFAULT NULL,
  `opis` varchar(500) DEFAULT NULL,
  `status_zahteva` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `datum_kreiranja` date DEFAULT NULL,
  `datum_odgovora` date DEFAULT NULL,
  `clan_id` int unsigned NOT NULL,
  `usluga_id` int unsigned NOT NULL,
  `admin_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_clan_id` (`clan_id`),
  KEY `fk_usluga_id` (`usluga_id`),
  KEY `fk_admin_id` (`admin_id`),
  CONSTRAINT `FK_zahtev_korisnik` FOREIGN KEY (`admin_id`) REFERENCES `korisnik` (`id`),
  CONSTRAINT `FK_zahtev_korisnik_2` FOREIGN KEY (`clan_id`) REFERENCES `korisnik` (`id`),
  CONSTRAINT `FK_zahtev_usluga` FOREIGN KEY (`usluga_id`) REFERENCES `usluga` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Dumping data for table seminarski_rad_sc.zahtev: ~9 rows (approximately)
/*!40000 ALTER TABLE `zahtev` DISABLE KEYS */;
INSERT INTO `zahtev` (`id`, `naziv`, `opis`, `status_zahteva`, `datum_kreiranja`, `datum_odgovora`, `clan_id`, `usluga_id`, `admin_id`) VALUES
	(1, '1', '1', 'PRIHVACEN', NULL, '2020-09-28', 1, 1, 34),
	(2, '2', '2', 'PRIHVACEN', NULL, '2020-09-28', 30, 3, 34),
	(3, '3', '3', 'PRIHVACEN', NULL, '2020-09-28', 33, 1, 34),
	(4, '4', '4', 'PRIHVACEN', NULL, '2020-06-29', 11, 3, 34),
	(5, '5', '5', 'ODBIJEN', NULL, '2020-06-25', 33, 5, 34),
	(6, '6', '6', 'PRIHVACEN', NULL, '2020-07-04', 1, 4, 34),
	(7, '7', '7', 'PRIHVACEN', NULL, '2020-06-25', 1, 5, 34),
	(8, '8', '8', 'PRIHVACEN', NULL, '2020-06-25', 1, 5, 34),
	(9, '9', '9', 'PRIHVACEN', NULL, '2020-07-04', 1, 5, 34),
	(10, 'nov zahtev', 'trazim svasta', 'PRIHVACEN', NULL, '2020-09-28', 34, 4, 34),
	(11, '23', '244344', 'PRIHVACEN', '2020-09-28', '2020-09-28', 1, 5, 34),
	(12, 'ggg', 'gggggg', 'PRIHVACEN', '2020-09-28', '2020-09-29', 34, 5, 34),
	(13, '34', '42564', 'ODBIJEN', '2020-09-28', '2020-09-29', 34, 1, 34),
	(14, 'djyj', 'djjd', 'ODBIJEN', '2020-09-29', '2020-09-29', 34, 1, 34),
	(15, 'dhgdgh', 'gdkdgk', 'PODNET', '2020-09-29', NULL, 34, 1, NULL);
/*!40000 ALTER TABLE `zahtev` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
