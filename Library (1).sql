CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `bookID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Az ellopott futár','Rejtő Jenő'),(2,'Csontbrigád','Rejtő Jenő'),(3,'A fekete kapitány','Rejtő Jenő'),(4,'Egri csillagok','Gárdonyi Géza'),(5,'Szent Péter Esernyője','Mikszáth Kálmán'),(6,'Rómeó és Júlia','William Shakespeare'),(7,'A kis herceg','Antoine de Saint-Exupéry'),(8,'Kiszámoló','Kotaszek Hedvig'),(9,'A Négyszögletű Kerek Erdő','Lázár Ervin'),(10,'Édes Anna','Kosztolányi Dezső');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrows`
--

DROP TABLE IF EXISTS `borrows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrows` (
  `borrowID` int NOT NULL AUTO_INCREMENT,
  `User_userID` int NOT NULL,
  `Borrows_copyID` int NOT NULL,
  `dateOfBorrow` datetime DEFAULT NULL,
  `dateOfReturn` datetime DEFAULT NULL,
  `arrivalDate` date DEFAULT NULL,
  PRIMARY KEY (`borrowID`),
  KEY `fk_User_has_Borrows_Borrows1_idx` (`Borrows_copyID`),
  KEY `fk_User_has_Borrows_User1_idx` (`User_userID`),
  CONSTRAINT `fk_User_has_Borrows_Borrows1` FOREIGN KEY (`Borrows_copyID`) REFERENCES `storage` (`copyID`),
  CONSTRAINT `fk_User_has_Borrows_User1` FOREIGN KEY (`User_userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrows`
--

LOCK TABLES `borrows` WRITE;
/*!40000 ALTER TABLE `borrows` DISABLE KEYS */;
INSERT INTO `borrows` VALUES (1,1,2,'2020-01-01 10:00:00','2020-01-20 10:00:00','1993-01-01'),(2,1,4,'2020-02-02 11:00:00','2020-02-10 12:00:00','1994-02-02'),(3,2,6,'2020-03-03 12:00:00','2020-03-20 13:00:00','1995-03-03'),(4,3,8,'2020-04-04 13:00:00','2020-04-18 17:00:00','1996-04-04'),(5,4,10,'2020-04-12 15:00:00','2020-04-29 13:00:00','1997-05-05'),(6,5,12,'2020-04-24 16:00:00','2020-05-20 18:00:00','1998-06-06'),(7,6,14,'2020-05-02 17:00:00',NULL,'1999-07-07'),(8,7,16,'2020-05-04 18:00:00',NULL,'2000-08-08'),(9,8,18,'2020-05-06 15:00:00',NULL,'2001-09-09'),(10,9,20,'2020-05-15 17:00:00',NULL,'2002-10-10'),(11,10,22,'2020-05-20 16:00:00',NULL,'2003-11-11');
/*!40000 ALTER TABLE `borrows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storage` (
  `copyID` int NOT NULL AUTO_INCREMENT,
  `bookID` int DEFAULT NULL,
  PRIMARY KEY (`copyID`),
  KEY `bookID_idx` (`bookID`),
  CONSTRAINT `bookID` FOREIGN KEY (`bookID`) REFERENCES `books` (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage`
--

LOCK TABLES `storage` WRITE;
/*!40000 ALTER TABLE `storage` DISABLE KEYS */;
INSERT INTO `storage` VALUES (1,1),(2,1),(3,2),(4,2),(5,2),(6,3),(7,3),(8,3),(9,4),(10,4),(11,4),(12,5),(13,5),(14,5),(15,6),(16,6),(17,6),(18,7),(19,7),(20,7),(21,8),(22,9),(23,10),(24,10),(25,10),(26,10);
/*!40000 ALTER TABLE `storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Kiss Attila','abc@gmail.com','Budapest'),(2,'Balogh Petra','abd@gmail.com','Békéscsaba'),(3,'Nagy anasztázia','abe@gmail.com','Sopron'),(4,'Má Zoltán','abf@gmail.com','Székesfehérvár'),(5,'Végh Rebeka','abg@gmail.com','Balatonalmádi'),(6,'Kis István','abh@gmail.com','Pákozd'),(7,'Kandó Kálmán','abi@gmail.com','Miskolc'),(8,'Víg Géza','abj@gmail.com','Aprajafalva'),(9,'Cseh Amália','abk@gmail.com','Pécs'),(10,'Balog Eszter','abl@gmail.com','Nagykáta');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-02 16:21:52
