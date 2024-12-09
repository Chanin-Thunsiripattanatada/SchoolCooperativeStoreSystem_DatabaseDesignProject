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
-- Table structure for table `accountmember`
--

DROP TABLE IF EXISTS `accountmember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accountmember` (
  `Runbookid` int NOT NULL AUTO_INCREMENT,
  `recordDate` datetime NOT NULL,
  `dividend` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `Member_ID` char(10) NOT NULL,
  `EmpID` char(10) DEFAULT NULL,
  `accountType` varchar(40) NOT NULL,
  PRIMARY KEY (`Runbookid`,`Member_ID`),
  KEY `AccountMember_fk_Member` (`Member_ID`),
  KEY `AccountMember_fk_Employee` (`EmpID`),
  CONSTRAINT `AccountMember_fk_Employee` FOREIGN KEY (`EmpID`) REFERENCES `employee` (`EmpID`),
  CONSTRAINT `AccountMember_fk_Member` FOREIGN KEY (`Member_ID`) REFERENCES `member` (`Member_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountmember`
--

LOCK TABLES `accountmember` WRITE;
/*!40000 ALTER TABLE `accountmember` DISABLE KEYS */;
INSERT INTO `accountmember` VALUES (122,'2024-03-06 08:46:14',-2.03,-2.03,'M003','E001','เฉลี่ยคืน'),(123,'2024-03-06 08:46:26',0.00,-2.03,'M003','E001','หุ้น'),(124,'2024-03-06 08:46:26',0.00,-2.03,'M003','E001','เฉลี่ยคืน'),(125,'2024-03-06 08:49:47',0.00,-2.03,'M003','E001','หุ้น'),(126,'2024-03-06 08:49:47',0.00,-2.03,'M003','E001','เฉลี่ยคืน'),(128,'2024-03-06 08:53:27',0.00,0.00,'M001','E001','หุ้น'),(129,'2024-03-06 08:53:58',0.00,0.00,'M001','E001','หุ้น'),(130,'2024-03-06 08:54:50',0.00,0.00,'M001','E001','หุ้น'),(132,'2024-03-06 08:58:52',0.00,0.00,'M001','E001','หุ้น'),(134,'2024-03-06 09:03:45',0.00,0.00,'M001','E001','หุ้น'),(135,'2024-03-06 09:03:45',15.00,15.00,'M001','E001','เฉลี่ยคืน'),(136,'2024-03-06 09:03:56',0.00,15.00,'M001','E001','หุ้น'),(137,'2024-03-06 09:03:56',0.00,15.00,'M001','E001','เฉลี่ยคืน'),(138,'2024-03-06 09:06:26',-15.00,0.00,'M001','E001','ถอน'),(145,'2024-03-06 09:16:26',NULL,NULL,'M002','E001','หุ้น'),(146,'2024-03-06 09:16:26',7.50,7.50,'M002','E001','เฉลี่ยคืน'),(147,'2024-03-06 12:11:40',NULL,NULL,'M005','E001','หุ้น'),(148,'2024-03-06 12:11:40',54.94,54.94,'M005','E001','เฉลี่ยคืน'),(149,'2024-03-06 12:11:50',0.00,54.94,'M005','E001','หุ้น'),(150,'2024-03-06 12:11:50',0.00,54.94,'M005','E001','เฉลี่ยคืน'),(151,'2024-12-09 15:21:42',15.99,15.99,'M001','E001','หุ้น'),(152,'2024-12-09 15:21:42',0.00,15.99,'M001','E001','เฉลี่ยคืน');
/*!40000 ALTER TABLE `accountmember` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-10  0:36:06
