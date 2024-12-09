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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `PID` char(10) NOT NULL,
  `PName` varchar(100) DEFAULT NULL,
  `Cost_per_unit` decimal(10,2) DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  `Product_detail` varchar(255) DEFAULT NULL,
  `Amount` int DEFAULT NULL,
  `Weight` decimal(10,2) DEFAULT NULL,
  `CategoryID` char(10) DEFAULT NULL,
  PRIMARY KEY (`PID`),
  KEY `Product_fk_Category` (`CategoryID`),
  CONSTRAINT `Product_fk_Category` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('100001','โอวันตินโกลด์ 3 in 1 แพ็ก 12 ซอง',100.00,125.00,'https://backend.tops.co.th/media/catalog/product/8/8/8850086163906_15-12-2022.jpg',1,10.00,'C002'),('100002','มันหวานญี่ปุ่นเผา',20.00,27.00,'https://www.prachachat.net/wp-content/uploads/2020/06/8-2.jpg',10,2.00,'C001'),('100003','บิ๊กเปาหมูสับไข่เค็ม',17.00,20.00,'http://about7eleven.com/wp-content/uploads/2021/08/7-113-5.jpg',3,5.00,'C001'),('100004','H FH ขนมปังโลฟลูกเกด',15.00,20.00,'https://shop.farmhouse.co.th/media/catalog/product/cache/09ad5326b5f9b030d858c3959bbb1f0b/2/0/2021_loaf_raisin.png',20,20.00,'C001'),('100005','เค้กกล้วยหอม',7.00,10.00,'https://pbs.twimg.com/media/DkPg-toVAAILco-.jpg',10,50.00,'C001'),('100006','นมพาสฯ เมจิจืด 830 มล.',40.00,46.75,'https://backend.tops.co.th/media/catalog/product/8/8/8850329183111_e24-03-2020.jpg',47,6.00,'C001'),('100007','โยเกิร์ตดัชชี่วุ้นมะพร้าว',10.00,15.00,'https://backend.tops.co.th/media/catalog/product/8/8/8851717020148_22-06-2020.jpg',60,20.00,'C001'),('100008','แม็กนั่ม อัลมอนด์',40.00,45.00,'https://assets.unileversolutions.com/v1/1451839.png',0,30.50,'C001'),('100009','เอเชียนดีไลท์ เผือก',22.00,25.00,'https://img.my-best.in.th/product_images/297b3310d8d5c88ee20e6180394f52e0.jpeg?ixlib=rails-4.2.0&q=70&lossless=0&w=800&h=800&fit=clip&s=d006c40fd097c0e17090fae42c106aae',0,10.50,'C002'),('100010','น้ำผึ้งดอกคำ 770 กรัม',150.00,185.00,'https://media.allonline.7eleven.co.th/pdmain/357901-01-potato-chip-lays-v3.jpg',0,770.00,'C002');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
