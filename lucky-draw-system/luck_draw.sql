/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 8.0.33 : Database - lucky_draw
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lucky_draw` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `lucky_draw`;

/*Table structure for table `award` */

DROP TABLE IF EXISTS `award`;

CREATE TABLE `award` (
  `award_id` int NOT NULL AUTO_INCREMENT COMMENT '奖项id',
  `award_describe` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '奖项描述 0-预留 1-一等奖 2-二等奖 3-三等奖',
  `count` int DEFAULT NULL COMMENT '奖项数量',
  `config_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`award_id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `lottery_config` */

DROP TABLE IF EXISTS `lottery_config`;

CREATE TABLE `lottery_config` (
  `config_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键id',
  `lottery_number` int DEFAULT NULL,
  `lottery_date` varchar(64) DEFAULT NULL COMMENT '添加日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `winner` */

DROP TABLE IF EXISTS `winner`;

CREATE TABLE `winner` (
  `win_id` int NOT NULL AUTO_INCREMENT COMMENT '获奖人ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '获奖人名字',
  `award_id` int DEFAULT NULL COMMENT '奖项ID',
  `config_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '抽奖信息',
  PRIMARY KEY (`win_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5138 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
