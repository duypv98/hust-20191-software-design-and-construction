-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: afc
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `oneway_ticket`
--

DROP TABLE IF EXISTS `oneway_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oneway_ticket` (
  `id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `ticket_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `embarkation_id` int(11) NOT NULL,
  `disembarkation_id` int(11) NOT NULL,
  `checked_in` tinyint(4) NOT NULL DEFAULT '0',
  `fare` double NOT NULL,
  `activated` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`ticket_code`),
  UNIQUE KEY `ticket_code` (`ticket_code`),
  KEY `fk_embarkation` (`embarkation_id`),
  KEY `fk_disembarkation` (`disembarkation_id`),
  CONSTRAINT `fk_disembarkation` FOREIGN KEY (`disembarkation_id`) REFERENCES `station` (`id`),
  CONSTRAINT `fk_embarkation` FOREIGN KEY (`embarkation_id`) REFERENCES `station` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oneway_ticket`
--

LOCK TABLES `oneway_ticket` WRITE;
/*!40000 ALTER TABLE `oneway_ticket` DISABLE KEYS */;
INSERT INTO `oneway_ticket` VALUES ('OW201910300000','14ffab8aebbc5204',1,4,0,3.5,1),('OW201910300001','b8094c1ccdff1df9',2,5,1,3.1,0),('OW201910300002','4435a2c99ae25d9d',2,5,0,3.1,0);
/*!40000 ALTER TABLE `oneway_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oneway_trip`
--

DROP TABLE IF EXISTS `oneway_trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oneway_trip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ticket_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `income_station_id` int(11) DEFAULT NULL,
  `income_time` timestamp NULL DEFAULT NULL,
  `outcome_station_id` int(11) DEFAULT NULL,
  `outcome_time` timestamp NULL DEFAULT NULL,
  `real_fare` double DEFAULT NULL,
  `ontrip` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_ticket_trip` (`ticket_id`),
  KEY `fk_income_station` (`income_station_id`),
  KEY `fk_outcome_station` (`outcome_station_id`),
  CONSTRAINT `fk_income_station` FOREIGN KEY (`income_station_id`) REFERENCES `station` (`id`),
  CONSTRAINT `fk_outcome_station` FOREIGN KEY (`outcome_station_id`) REFERENCES `station` (`id`),
  CONSTRAINT `fk_ticket_trip` FOREIGN KEY (`ticket_id`) REFERENCES `oneway_ticket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oneway_trip`
--

LOCK TABLES `oneway_trip` WRITE;
/*!40000 ALTER TABLE `oneway_trip` DISABLE KEYS */;
INSERT INTO `oneway_trip` VALUES (1,'OW201910300001',2,'2019-12-07 14:43:18',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `oneway_trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prepaid_card`
--

DROP TABLE IF EXISTS `prepaid_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prepaid_card` (
  `id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `card_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `balance` double DEFAULT NULL,
  `checked_in` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `card_code` (`card_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prepaid_card`
--

LOCK TABLES `prepaid_card` WRITE;
/*!40000 ALTER TABLE `prepaid_card` DISABLE KEYS */;
INSERT INTO `prepaid_card` VALUES ('PC201912070000','5134e0a3fcb80fe7',7.2,1),('PC201912070001','0ad1861fd02deef2',1,0),('PC201912070002','82b9fda721125157',4.2,0);
/*!40000 ALTER TABLE `prepaid_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prepaid_trip`
--

DROP TABLE IF EXISTS `prepaid_trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prepaid_trip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `income_station_id` int(11) DEFAULT NULL,
  `income_time` timestamp NULL DEFAULT NULL,
  `outcome_station_id` int(11) DEFAULT NULL,
  `outcome_time` timestamp NULL DEFAULT NULL,
  `real_fare` double DEFAULT NULL,
  `ontrip` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_outcome_station_p` (`outcome_station_id`),
  KEY `fk_income_station_p` (`income_station_id`),
  KEY `fk_prepaid_trip` (`card_id`),
  CONSTRAINT `fk_income_station_p` FOREIGN KEY (`income_station_id`) REFERENCES `station` (`id`),
  CONSTRAINT `fk_outcome_station_p` FOREIGN KEY (`outcome_station_id`) REFERENCES `station` (`id`),
  CONSTRAINT `fk_prepaid_trip` FOREIGN KEY (`card_id`) REFERENCES `prepaid_card` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prepaid_trip`
--

LOCK TABLES `prepaid_trip` WRITE;
/*!40000 ALTER TABLE `prepaid_trip` DISABLE KEYS */;
INSERT INTO `prepaid_trip` VALUES (1,'PC201912070000',1,'2019-12-07 15:23:49',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `prepaid_trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `station_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `distance` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'Saint-Lazare',0),(2,'Madeleine',5),(3,'Pyramides',8.5),(4,'Châtelet',11.3),(5,'Gare de Lyon',15.8),(6,'Bercy',18.9),(7,'Cour Saint-Émilion',22),(8,'Bibliothèque François Mitterrand',25.3),(9,'Olympiades',28.8);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_ticket`
--

DROP TABLE IF EXISTS `tf_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tf_ticket` (
  `id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `ticket_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `valid_time` timestamp NULL DEFAULT NULL,
  `checked_in` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ticket_code` (`ticket_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_ticket`
--

LOCK TABLES `tf_ticket` WRITE;
/*!40000 ALTER TABLE `tf_ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `tf_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_trip`
--

DROP TABLE IF EXISTS `tf_trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tf_trip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ticket_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `income_station_id` int(11) DEFAULT NULL,
  `income_time` timestamp NULL DEFAULT NULL,
  `outcome_station_id` int(11) DEFAULT NULL,
  `outcome_time` timestamp NULL DEFAULT NULL,
  `ontrip` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_income_station_tf` (`income_station_id`),
  KEY `fk_outcome_station_tf` (`outcome_station_id`),
  KEY `fk_ticket_trip_tf` (`ticket_id`),
  CONSTRAINT `fk_income_station_tf` FOREIGN KEY (`income_station_id`) REFERENCES `station` (`id`),
  CONSTRAINT `fk_outcome_station_tf` FOREIGN KEY (`outcome_station_id`) REFERENCES `station` (`id`),
  CONSTRAINT `fk_ticket_trip_tf` FOREIGN KEY (`ticket_id`) REFERENCES `tf_ticket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_trip`
--

LOCK TABLES `tf_trip` WRITE;
/*!40000 ALTER TABLE `tf_trip` DISABLE KEYS */;
/*!40000 ALTER TABLE `tf_trip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-08  9:52:18
