-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: vega
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allergen`
--

DROP TABLE IF EXISTS `allergen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `allergen` (
  `id` char(36) COLLATE utf8_bin NOT NULL,
  `e_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `id_category` char(36) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_75` (`id_category`),
  CONSTRAINT `FK_75` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergen`
--

LOCK TABLES `allergen` WRITE;
/*!40000 ALTER TABLE `allergen` DISABLE KEYS */;
INSERT INTO `allergen` VALUES ('111122fd-bbe7-4a64-b42c-d52f81ad146b','all1',0,'e03daf50-de31-471a-9a02-b70097fc114a'),('20adc510-6b14-457c-915a-807b2e150f42','Lonfo',0,'e03daf50-de31-471a-9a02-b70097fc11da'),('27465574-94fd-4dbc-8ba9-806f1e64fe1c','allergene-2_3',0,'842b5f72-d188-4bd6-af5d-8b7e69a919fe'),('4826ea33-d5c5-4c78-8db7-e12da9d3453f','allergene-2_1',0,'842b5f72-d188-4bd6-af5d-8b7e69a919fe'),('683f2544-36b5-4826-b2cd-94d7d321fc7b','all1-2',0,'e03daf50-de31-471a-9a02-b70097fc114a'),('c12cc584-891c-496d-8186-498fd1d60fcc','allergene-2_2',0,'842b5f72-d188-4bd6-af5d-8b7e69a919fe');
/*!40000 ALTER TABLE `allergen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id` char(36) COLLATE utf8_bin NOT NULL,
  `e_name` varchar(100) COLLATE utf8_bin NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('842b5f72-d188-4bd6-af5d-8b7e69a919fe','cat2',0),('e03daf50-de31-471a-9a02-b70097fc114a','catUno',0),('e03daf50-de31-471a-9a02-b70097fc11da','catoffio',0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `report` (
  `id` char(64) COLLATE utf8_bin NOT NULL,
  `subject_name` varchar(100) COLLATE utf8_bin NOT NULL,
  `date_creation` datetime NOT NULL,
  `owner` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES ('67c157302abf216aecb4a691f1ae4f8e36e52f7658c9e583d7204e36639057b3','Simone','2018-08-08 20:01:07','gemini'),('b3cee2d1108425cf170b1ee84e2b1714e42c4991b51dd963bf4276a1a49c0cb4','Simone','2018-08-07 22:21:12','gemini');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_line`
--

DROP TABLE IF EXISTS `report_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `report_line` (
  `id_report` char(64) COLLATE utf8_bin NOT NULL,
  `id_allergen` char(36) COLLATE utf8_bin NOT NULL,
  `toxicity` decimal(4,2) NOT NULL,
  PRIMARY KEY (`id_report`,`id_allergen`),
  KEY `FK_report_line__allergen` (`id_allergen`),
  CONSTRAINT `FK_report_line__allergen` FOREIGN KEY (`id_allergen`) REFERENCES `allergen` (`id`),
  CONSTRAINT `FK_report_line__report` FOREIGN KEY (`id_report`) REFERENCES `report` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_line`
--

LOCK TABLES `report_line` WRITE;
/*!40000 ALTER TABLE `report_line` DISABLE KEYS */;
INSERT INTO `report_line` VALUES ('67c157302abf216aecb4a691f1ae4f8e36e52f7658c9e583d7204e36639057b3','111122fd-bbe7-4a64-b42c-d52f81ad146b',82.00),('67c157302abf216aecb4a691f1ae4f8e36e52f7658c9e583d7204e36639057b3','27465574-94fd-4dbc-8ba9-806f1e64fe1c',16.00),('67c157302abf216aecb4a691f1ae4f8e36e52f7658c9e583d7204e36639057b3','4826ea33-d5c5-4c78-8db7-e12da9d3453f',1.00),('67c157302abf216aecb4a691f1ae4f8e36e52f7658c9e583d7204e36639057b3','683f2544-36b5-4826-b2cd-94d7d321fc7b',33.00),('67c157302abf216aecb4a691f1ae4f8e36e52f7658c9e583d7204e36639057b3','c12cc584-891c-496d-8186-498fd1d60fcc',93.00),('b3cee2d1108425cf170b1ee84e2b1714e42c4991b51dd963bf4276a1a49c0cb4','111122fd-bbe7-4a64-b42c-d52f81ad146b',42.00),('b3cee2d1108425cf170b1ee84e2b1714e42c4991b51dd963bf4276a1a49c0cb4','27465574-94fd-4dbc-8ba9-806f1e64fe1c',61.00),('b3cee2d1108425cf170b1ee84e2b1714e42c4991b51dd963bf4276a1a49c0cb4','4826ea33-d5c5-4c78-8db7-e12da9d3453f',93.00),('b3cee2d1108425cf170b1ee84e2b1714e42c4991b51dd963bf4276a1a49c0cb4','683f2544-36b5-4826-b2cd-94d7d321fc7b',21.00),('b3cee2d1108425cf170b1ee84e2b1714e42c4991b51dd963bf4276a1a49c0cb4','c12cc584-891c-496d-8186-498fd1d60fcc',80.00);
/*!40000 ALTER TABLE `report_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vega_role`
--

DROP TABLE IF EXISTS `vega_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vega_role` (
  `role_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `user_name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`role_name`,`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vega_role`
--

LOCK TABLES `vega_role` WRITE;
/*!40000 ALTER TABLE `vega_role` DISABLE KEYS */;
INSERT INTO `vega_role` VALUES ('v-admin','gemini'),('v-user','gemini'),('v-user','tramonti');
/*!40000 ALTER TABLE `vega_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vega_user`
--

DROP TABLE IF EXISTS `vega_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vega_user` (
  `user_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `user_password` varchar(132) COLLATE utf8_bin NOT NULL,
  `full_name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vega_user`
--

LOCK TABLES `vega_user` WRITE;
/*!40000 ALTER TABLE `vega_user` DISABLE KEYS */;
INSERT INTO `vega_user` VALUES ('gemini','5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8','Simone Monotti'),('tramonti','5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8','RT');
/*!40000 ALTER TABLE `vega_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'vega'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-11 11:13:40
