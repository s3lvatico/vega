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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-11 11:11:42
