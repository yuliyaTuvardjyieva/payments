-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.11-log - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных payments
DROP DATABASE IF EXISTS `payments`;
CREATE DATABASE IF NOT EXISTS `payments` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `payments`;


-- Дамп структуры для таблица payments.cards
DROP TABLE IF EXISTS `cards`;
CREATE TABLE IF NOT EXISTS `cards` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `counts_id` int(10) unsigned NOT NULL,
  `active_status` bit(1) DEFAULT NULL,
  `persons_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cards_counts` (`counts_id`),
  KEY `FK_cards_users` (`persons_id`),
  CONSTRAINT `FK_cards_counts` FOREIGN KEY (`counts_id`) REFERENCES `counts` (`id`),
  CONSTRAINT `FK_cards_users` FOREIGN KEY (`persons_id`) REFERENCES `persons` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.cards: ~9 rows (приблизительно)
DELETE FROM `cards`;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` (`id`, `counts_id`, `active_status`, `persons_id`) VALUES
	(1, 1, b'1', 2),
	(2, 2, b'1', 3),
	(3, 3, b'1', 4),
	(4, 4, b'1', 8),
	(5, 5, b'1', 9),
	(6, 6, b'1', 2),
	(7, 7, b'0', 2),
	(8, 8, b'1', 3),
	(9, 9, b'0', 3),
	(10, 10, b'0', 9);
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;


-- Дамп структуры для таблица payments.counts
DROP TABLE IF EXISTS `counts`;
CREATE TABLE IF NOT EXISTS `counts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.counts: ~10 rows (приблизительно)
DELETE FROM `counts`;
/*!40000 ALTER TABLE `counts` DISABLE KEYS */;
INSERT INTO `counts` (`id`, `value`) VALUES
	(1, 100),
	(2, 123),
	(3, 323),
	(4, 222),
	(5, 223),
	(6, 132),
	(7, 127),
	(8, 23),
	(9, 44),
	(10, 654);
/*!40000 ALTER TABLE `counts` ENABLE KEYS */;


-- Дамп структуры для таблица payments.persons
DROP TABLE IF EXISTS `persons`;
CREATE TABLE IF NOT EXISTS `persons` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `login` varchar(20) DEFAULT NULL COMMENT 'person login',
  `password` varchar(20) DEFAULT NULL COMMENT 'person password',
  `last_name` varchar(50) DEFAULT NULL COMMENT 'person last name',
  `access_level` enum('ADMIN','CLIENT') DEFAULT NULL COMMENT 'access level of curent person',
  `first_name` varchar(50) DEFAULT NULL COMMENT 'person first name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.persons: ~6 rows (приблизительно)
DELETE FROM `persons`;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` (`id`, `login`, `password`, `last_name`, `access_level`, `first_name`) VALUES
	(1, 'root', 'root', 'Petrenko', 'ADMIN', 'Dima'),
	(2, 'user', 'user', 'Ahmatova', 'CLIENT', 'Anna'),
	(3, 'user2', 'user2', 'Rudko', 'CLIENT', 'Dmitriy'),
	(4, 'user3', 'user3', 'Audi', 'CLIENT', 'Muhamad'),
	(8, 'user4', 'user4', 'Berezovskiy', 'CLIENT', 'Roman'),
	(9, 'user5', 'user5', 'Abramovich', 'CLIENT', 'Roman');
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
