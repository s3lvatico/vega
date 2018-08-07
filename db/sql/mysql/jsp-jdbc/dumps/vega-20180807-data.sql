-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: vega
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `allergen`
--

LOCK TABLES `allergen` WRITE;
/*!40000 ALTER TABLE `allergen` DISABLE KEYS */;
INSERT INTO `allergen` VALUES ('111122fd-bbe7-4a64-b42c-d52f81ad146b','all1',0,'e03daf50-de31-471a-9a02-b70097fc114a'),('20adc510-6b14-457c-915a-807b2e150f42','Lonfo',0,'e03daf50-de31-471a-9a02-b70097fc11da'),('27465574-94fd-4dbc-8ba9-806f1e64fe1c','allergene-2_3',0,'842b5f72-d188-4bd6-af5d-8b7e69a919fe'),('4826ea33-d5c5-4c78-8db7-e12da9d3453f','allergene-2_1',0,'842b5f72-d188-4bd6-af5d-8b7e69a919fe'),('683f2544-36b5-4826-b2cd-94d7d321fc7b','all1-2',0,'e03daf50-de31-471a-9a02-b70097fc114a'),('c12cc584-891c-496d-8186-498fd1d60fcc','allergene-2_2',0,'842b5f72-d188-4bd6-af5d-8b7e69a919fe');
/*!40000 ALTER TABLE `allergen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('35d6f3e7-4638-4ba8-b27c-19089570fbeb','sdfghgr4gvd',0),('842b5f72-d188-4bd6-af5d-8b7e69a919fe','cat2',0),('e03daf50-de31-471a-9a02-b70097fc114a','catUno',0),('e03daf50-de31-471a-9a02-b70097fc11da','catoffio',0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `report_line`
--

LOCK TABLES `report_line` WRITE;
/*!40000 ALTER TABLE `report_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_line` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-07 14:29:49
