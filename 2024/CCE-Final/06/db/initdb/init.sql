use cce;

DROP TABLE IF EXISTS `train_info`;
CREATE TABLE `train_info` (
  `train_number` int NOT NULL,
  `total_seats` int DEFAULT NULL,
  PRIMARY KEY (`train_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `train_schedule`;
CREATE TABLE `train_schedule` (
  `schedule_id` int NOT NULL,
  `train_number` int DEFAULT NULL,
  `total_seats` int DEFAULT NULL,
  `available_seats` int DEFAULT NULL,
  `departure_time` datetime DEFAULT NULL,
  `arrival_time` datetime DEFAULT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `train_number` (`train_number`),
  CONSTRAINT `train_schedule_ibfk_1` FOREIGN KEY (`train_number`) REFERENCES `train_info` (`train_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `member_info`;
CREATE TABLE `member_info` (
  `uuid` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `train_info` VALUES (101,40),(102,40),(103,40),(104,40),(105,40),(106,40),(107,40),(108,40);
INSERT INTO `train_schedule` VALUES (1,101,40,40,'2024-09-12 14:00:00','2024-09-12 16:35:00'),(2,102,40,40,'2024-09-12 14:35:00','2024-09-12 17:00:00'),(3,103,40,40,'2024-09-12 15:24:00','2024-09-12 17:59:00'),(4,104,40,40,'2024-09-12 16:00:00','2024-09-12 18:12:00'),(5,105,40,40,'2024-09-12 17:40:00','2024-09-12 20:27:00'),(6,106,40,40,'2024-09-12 18:27:00','2024-09-12 20:49:00'),(7,107,40,40,'2024-09-12 19:50:00','2024-09-12 22:00:00'),(8,108,40,40,'2024-09-12 20:43:00','2024-09-12 22:50:00');
INSERT INTO `member_info` VALUES ('fda4a4e9-9fe4-40f6-8595-84c53a905415','admin','admin','d4b066f51d50db36f175064f84e7ba85',1);