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
-- Table structure for table `sharebookmember`
--

DROP TABLE IF EXISTS `sharebookmember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sharebookmember` (
  `Runbookid` int NOT NULL AUTO_INCREMENT,
  `recordDate` datetime NOT NULL,
  `SHARE` int DEFAULT NULL,
  `totalshare` int DEFAULT NULL,
  `Member_ID` char(10) NOT NULL,
  `EmpID` char(10) DEFAULT NULL,
  PRIMARY KEY (`Runbookid`,`Member_ID`),
  KEY `ShareBookMember_fk_Member` (`Member_ID`),
  KEY `ShareBookMember_fk_Employee` (`EmpID`),
  CONSTRAINT `ShareBookMember_fk_Employee` FOREIGN KEY (`EmpID`) REFERENCES `employee` (`EmpID`),
  CONSTRAINT `ShareBookMember_fk_Member` FOREIGN KEY (`Member_ID`) REFERENCES `member` (`Member_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sharebookmember`
--

LOCK TABLES `sharebookmember` WRITE;
/*!40000 ALTER TABLE `sharebookmember` DISABLE KEYS */;
INSERT INTO `sharebookmember` VALUES (74,'2024-03-06 08:40:10',10,10,'M003','E001'),(75,'2024-03-06 08:40:50',1,11,'M003','E001'),(77,'2024-03-06 08:46:05',1,12,'M003','E001'),(78,'2024-03-06 08:51:20',100,100,'M001','E001'),(79,'2024-03-06 08:51:47',10,110,'M001','E001'),(80,'2024-03-06 08:52:33',10,120,'M001','E001'),(81,'2024-03-06 08:53:27',10,130,'M001','E001'),(82,'2024-03-06 08:53:58',10,140,'M001','E001'),(83,'2024-03-06 09:10:27',100,100,'M002','E001'),(84,'2024-03-06 12:02:53',5,5,'M005','E001'),(85,'2024-03-06 12:03:12',10,15,'M005','E001');
/*!40000 ALTER TABLE `sharebookmember` ENABLE KEYS */;
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
