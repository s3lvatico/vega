-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sandbox
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `vg_allergene`
--

DROP TABLE IF EXISTS vg_allergene;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE vg_allergene (
  id char(36) NOT NULL,
  id_categoria char(36) NOT NULL DEFAULT '00000000-0000-0000-0000-000000000000',
  nome varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UNIQUE_allergene_nome (nome),
  KEY fk_allergene__categoria_id_idx (id_categoria),
  CONSTRAINT fk_allergene__categoria_id FOREIGN KEY (id_categoria) REFERENCES vg_categoria (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vg_allergene`
--

LOCK TABLES vg_allergene WRITE;
/*!40000 ALTER TABLE vg_allergene DISABLE KEYS */;
/*!40000 ALTER TABLE vg_allergene ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vg_categoria`
--

DROP TABLE IF EXISTS vg_categoria;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE vg_categoria (
  id char(36) NOT NULL,
  nome varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY nome_UNIQUE (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vg_categoria`
--

LOCK TABLES vg_categoria WRITE;
/*!40000 ALTER TABLE vg_categoria DISABLE KEYS */;
INSERT INTO vg_categoria (id, nome) VALUES ('00000000-0000-0000-0000-000000000000','DEFAULT_CATEGORY');
/*!40000 ALTER TABLE vg_categoria ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vg_report`
--

DROP TABLE IF EXISTS vg_report;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE vg_report (
  id char(36) NOT NULL,
  data_creazione date NOT NULL,
  nome_soggetto varchar(45) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vg_report`
--

LOCK TABLES vg_report WRITE;
/*!40000 ALTER TABLE vg_report DISABLE KEYS */;
/*!40000 ALTER TABLE vg_report ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vg_report_data`
--

DROP TABLE IF EXISTS vg_report_data;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE vg_report_data (
  id_report char(36) NOT NULL,
  id_allergene char(36) NOT NULL,
  tox decimal(3,2) NOT NULL,
  PRIMARY KEY (id_report,id_allergene),
  KEY fk_report_data__id_allergene_idx (id_allergene),
  CONSTRAINT fk_report_data__id_allergene FOREIGN KEY (id_allergene) REFERENCES vg_allergene (id) ON UPDATE NO ACTION,
  CONSTRAINT fk_report_data__id_report FOREIGN KEY (id_report) REFERENCES vg_report (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vg_report_data`
--

LOCK TABLES vg_report_data WRITE;
/*!40000 ALTER TABLE vg_report_data DISABLE KEYS */;
/*!40000 ALTER TABLE vg_report_data ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-17 11:54:25
