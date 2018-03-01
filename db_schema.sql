/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных pd
CREATE DATABASE IF NOT EXISTS `pd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pd`;


-- Дамп структуры для таблица pd.parcel
CREATE TABLE IF NOT EXISTS `parcel` (
  `parcel_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sender` varchar(70) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `path` varchar(255) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `status` enum('Transit','On Next Point','Delivered') NOT NULL DEFAULT 'Transit',
  PRIMARY KEY (`parcel_id`),
  UNIQUE KEY `UNIQUE KEY` (`uuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



-- Дамп структуры для таблица pd.point
CREATE TABLE IF NOT EXISTS `point` (
  `point_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `point_number` int(10) unsigned NOT NULL,
  `uri` varchar(30) NOT NULL,
  PRIMARY KEY (`point_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
