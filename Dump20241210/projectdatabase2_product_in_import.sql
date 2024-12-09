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
-- Table structure for table `product_in_import`
--

DROP TABLE IF EXISTS `product_in_import`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_in_import` (
  `ImportID` char(10) NOT NULL,
  `PID` char(10) NOT NULL,
  `quantity` int DEFAULT NULL,
  `Totalcost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ImportID`,`PID`),
  KEY `Product_in_import_fk_Product` (`PID`),
  CONSTRAINT `Product_in_import_fk_Product` FOREIGN KEY (`PID`) REFERENCES `product` (`PID`),
  CONSTRAINT `Product_in_import_fk_Record_Import` FOREIGN KEY (`ImportID`) REFERENCES `record_import` (`ImportID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_in_import`
--

LOCK TABLES `product_in_import` WRITE;
/*!40000 ALTER TABLE `product_in_import` DISABLE KEYS */;
INSERT INTO `product_in_import` VALUES ('1111111','100002',10,200.00),('IM00001','100002',5,100.00),('IM00001','100003',10,170.00),('IM00001','100004',20,300.00),('IM00001','100005',15,105.00),('IM00001','100006',50,2000.00),('IM00001','100007',100,1000.00),('IM001','100001',10,1000.00);
/*!40000 ALTER TABLE `product_in_import` ENABLE KEYS */;
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
