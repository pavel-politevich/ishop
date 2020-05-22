-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: ishop
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `item_reviews`
--

DROP TABLE IF EXISTS `item_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_reviews` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `USER_ID` int DEFAULT NULL,
  `ITEM_ID` int DEFAULT NULL,
  `RATE` int DEFAULT NULL,
  `TEXT` varchar(1024) DEFAULT NULL,
  `EVENT_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `fk_user_id_idx` (`USER_ID`),
  KEY `fk_reviews_item_id_idx` (`ITEM_ID`),
  CONSTRAINT `fk_reviews_item_id` FOREIGN KEY (`ITEM_ID`) REFERENCES `items` (`ID`),
  CONSTRAINT `fk_rewiews_user_id` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_reviews`
--

LOCK TABLES `item_reviews` WRITE;
/*!40000 ALTER TABLE `item_reviews` DISABLE KEYS */;
INSERT INTO `item_reviews` VALUES (7,8,41,4,'Обычные яйца, можно было бы и покрупнее сделать','2020-05-20 15:46:22'),(8,8,45,1,'Последний раз привезли черствый, пришлось скормить коту!','2020-05-20 15:47:23'),(9,8,46,5,'Самый вкусный хлеб, который можно купить в этом магазине','2020-05-20 15:48:11'),(10,8,29,3,'Обычный имбирь, но цена - просто космос!!!','2020-05-20 15:48:45'),(11,8,38,5,'Очень вкусно. Отбивные ушли очень быстро)))','2020-05-20 15:50:53'),(12,8,48,2,'Больше никогда такие не куплю и вам не советую','2020-05-20 15:51:47'),(13,10,29,2,'За такую цену ешьте его сами!','2020-05-20 15:53:52'),(15,75,32,4,'Картофель нормальный, но неужели нет белорусского...','2020-05-20 21:29:41');
/*!40000 ALTER TABLE `item_reviews` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-22 11:03:15
