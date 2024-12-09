-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: projectdatabase2
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `memberpurchasebook`
--

DROP TABLE IF EXISTS `memberpurchasebook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memberpurchasebook` (
  `Runbookid` int NOT NULL AUTO_INCREMENT,
  `recordDate` datetime NOT NULL,
  `OrderID` char(10) DEFAULT NULL,
  `Totalprice` decimal(10,2) DEFAULT NULL,
  `Member_ID` char(10) NOT NULL,
  `EmpID` char(10) DEFAULT NULL,
  PRIMARY KEY (`Runbookid`,`Member_ID`),
  KEY `MemberPurchaseBook_fk_Member` (`Member_ID`),
  KEY `MemberPurchaseBook_fk_Employee` (`EmpID`),
  CONSTRAINT `MemberPurchaseBook_fk_Employee` FOREIGN KEY (`EmpID`) REFERENCES `employee` (`EmpID`),
  CONSTRAINT `MemberPurchaseBook_fk_Member` FOREIGN KEY (`Member_ID`) REFERENCES `member` (`Member_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memberpurchasebook`
--

LOCK TABLES `memberpurchasebook` WRITE;
/*!40000 ALTER TABLE `memberpurchasebook` DISABLE KEYS */;
INSERT INTO `memberpurchasebook` VALUES (36,'2024-03-06 08:42:49','91',80.00,'M003','E001'),(37,'2024-03-06 09:03:15','92',125.00,'M001','E001'),(38,'2024-03-06 09:03:24','93',375.00,'M001','E001'),(39,'2024-03-06 09:10:55','94',250.00,'M002','E001'),(40,'2024-03-06 12:08:29','95',805.00,'M005','E001'),(41,'2024-03-06 12:08:54','96',125.00,'M005','E001'),(42,'2024-03-06 12:09:02','97',40.00,'M005','E001'),(43,'2024-03-06 12:09:07','98',30.00,'M005','E001'),(44,'2024-03-06 12:09:27','99',300.00,'M005','E001'),(45,'2024-03-06 12:09:33','100',140.25,'M005','E001');
/*!40000 ALTER TABLE `memberpurchasebook` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-10  0:36:07
