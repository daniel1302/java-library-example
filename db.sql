-- MySQL dump 10.13  Distrib 5.7.18-15, for Linux (x86_64)
--
-- Host: localhost    Database: lib
-- ------------------------------------------------------
-- Server version	5.7.18-15

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
/*!50717 SET @rocksdb_bulk_load_var_name='rocksdb_bulk_load' */;
/*!50717 SELECT COUNT(*) INTO @rocksdb_has_p_s_session_variables FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'performance_schema' AND TABLE_NAME = 'session_variables' */;
/*!50717 SET @rocksdb_get_is_supported = IF (@rocksdb_has_p_s_session_variables, 'SELECT COUNT(*) INTO @rocksdb_is_supported FROM performance_schema.session_variables WHERE VARIABLE_NAME=?', 'SELECT 0') */;
/*!50717 PREPARE s FROM @rocksdb_get_is_supported */;
/*!50717 EXECUTE s USING @rocksdb_bulk_load_var_name */;
/*!50717 DEALLOCATE PREPARE s */;
/*!50717 SET @rocksdb_enable_bulk_load = IF (@rocksdb_is_supported, 'SET SESSION rocksdb_bulk_load = 1', 'SET @rocksdb_dummy_bulk_load = 0') */;
/*!50717 PREPARE s FROM @rocksdb_enable_bulk_load */;
/*!50717 EXECUTE s */;
/*!50717 DEALLOCATE PREPARE s */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `isbn` varchar(60) NOT NULL,
  `authors` varchar(255) NOT NULL,
  `description` text,
  `publisher_id` int(11) NOT NULL,
  `status_id` int(11) DEFAULT NULL,
  `publication_year` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_publisher` (`publisher_id`),
  KEY `fk_book_status` (`status_id`),
  CONSTRAINT `fk_book_publisher` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`),
  CONSTRAINT `fk_book_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (5,'MATLAB i podstawy telekomunikacji','978-83-283-2701-6, 9788328327016','Jacek Izydorczyk i inni','<h3>MATLAB — niezastąpiony w obliczeniach i modelowaniu rozwiązań!</h3>  <p>Program <strong>MATLAB </strong>służy przede wszystkim do obliczeń macierzowych. Jest potężnym narzędziem, nie do zastąpienia w wielu dziedzinach techniki, elektroniki i projektowania najróżniejszych systemów. Jednym z niezwykle użytecznych zastosowań <strong>MATLAB</strong>-a jest możliwość wykorzystania tego środowiska do obliczeń związanych z dziedziną telekomunikacji: przetwarzaniem i przesyłaniem sygnałów, ich interpretacją i modulacją. Ta książka pomoże Ci zrozumieć, jak to wszystko działa, i pokaże, jak osiągnąć oczekiwane efekty pracy.</p>  <p>Jeśli chcesz nauczyć się sprawnie wykorzystywać algorytmy obliczeniowe do znajdowania rozwiązań konkretnych problemów związanych z zagadnieniami telekomunikacji, projektować filtry cyfrowe i syntezatory mowy, obliczać przepustowość kanałów transmisyjnych albo pisać funkcje implementujące układy dekoderów dla różnych typów modulacji, nie możesz obejść się bez tego podręcznika. Oprócz konkretnych, precyzyjnych informacji zawiera on mnóstwo praktycznych zadań, umożliwiających Ci sprawdzenie swojej wiedzy i dogłębne zrozumienie zasad działania środowiska <strong>MATLAB</strong>. Czytaj i ucz się pilnie!</p>  <ul> 	<li>Wprowadzenie</li> 	<li>Przetwarzanie i przesyłanie sygnałów</li> 	<li>Dyskretna transformacja Fouriera i splot kołowy</li> 	<li>Filtry cyfrowe FIR oraz IIR</li> 	<li>Sygnalizacja DTMF</li> 	<li>Przesuwanie widma sygnału</li> 	<li>Przetwarzanie&nbsp;i pasmowo-przepustowy przetwornik</li> 	<li>Elektroniczna eliminacja echa i liniowa predykcja sygnału</li> 	<li>Modulacja AM i SSB</li> 	<li>Modulacja i demodulacja FM</li> 	<li>Szumy w systemach FM i transmisja w paśmie podstawowym</li> 	<li>Modulacja QAM i MSK/GMSK</li> 	<li>Synchronizacja nadajnika i odbiornika</li> 	<li>Korekcja zniekształceń liniowych i ślepa korekcja kanału</li> 	<li>Kody blokowe i splotowe</li> 	<li>Modulacja OFDM i z widmem rozproszonym</li> 	<li>Techniki MIMO</li> </ul>',1,NULL,2017),(6,'test','1234','dsadasd','test',1,NULL,2014);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publisher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'PWN'),(2,'Helion');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `cretated` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_status_user` (`user_id`),
  CONSTRAINT `fk_status_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,1,2,'2017-06-23 21:47:15'),(2,1,2,'2017-06-23 22:14:46');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `pass` varchar(32) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `verified` int(11) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rank` varchar(25) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('daniel1302','Daniel1','daniel.1302@gmail.com','Daniel','Hornik',1,1,'admin'),('daniel1302_1','Daniel1','daniel.1302-93@o2.pl','ąś','Hornik',0,2,'admin'),('danielos1','Daniel1','daniel@gmail.com','daniel1302','Danielos',0,3,'user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50112 SET @disable_bulk_load = IF (@is_rocksdb_supported, 'SET SESSION rocksdb_bulk_load = @old_rocksdb_bulk_load', 'SET @dummy_rocksdb_bulk_load = 0') */;
/*!50112 PREPARE s FROM @disable_bulk_load */;
/*!50112 EXECUTE s */;
/*!50112 DEALLOCATE PREPARE s */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-23 22:16:31
