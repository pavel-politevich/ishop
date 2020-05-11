CREATE DATABASE  IF NOT EXISTS `ishop` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ishop`;
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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `SURNAME` varchar(45) DEFAULT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `STATE_ID` int DEFAULT NULL,
  `ROLE_ID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN_UNIQUE` (`LOGIN`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  KEY `fk_users_status_id_idx` (`STATE_ID`),
  KEY `fk_users_role_id_idx` (`ROLE_ID`),
  CONSTRAINT `fk_users_role_id` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ID`),
  CONSTRAINT `fk_users_status_id` FOREIGN KEY (`STATE_ID`) REFERENCES `dict_users_state` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'petr001','161ebd7d45089b3446ee4e0d86dbcf92','Петр','Юрлов','+375259000358','test3335@mail.com','г.Минск','2007-01-30',1,2),(8,'ivan1','fcea920f7412b5da7be0cf42b8c93759','Иван','Иванов','+375259000377','tes8885@mail.com','г.Минск','2001-01-30',1,2),(9,'mikola999','6ebe76c9fb411be97b3b0d48b791a7c9','Mikola','Bobrov','+375294562013','bobr@test.ru','Brest','1999-05-31',1,2),(10,'admin','21232f297a57a5a743894a0e4a801fc3','Admin','-','+375252555566','admin@mail.ru','-','2010-12-19',1,1),(11,'masha2','202cb962ac59075b964b07152d234b70','Maria','Gorbach','+375447895563','masha77@mail.ru','Vitebsk','1995-05-14',1,2),(12,'olga4','dbc4d84bfcfe2284ba11beffb853a8c4','Olga','Chechulina','+375295596545','Olya-4ech@yandex.ru','Minsk','1987-11-25',1,2),(13,'petr01','25f9e794323b453885f5181f1b624d0b','Petr','Petrov','+375291044298','test@life.tu','Minsk','1989-12-31',1,2),(14,'amelina1','202cb962ac59075b964b07152d234b70','Amelina','Ali','+375256257415','ali@test.ru','Gomel','2010-06-14',1,2),(15,'marina123','68053af2923e00204c3ca7c6a3150cf7','Marina','Oreo','+3754410222236','marina@mail.ru','Minsk','2000-12-10',1,2),(46,'stas','2b3ffa9163c5444fc0c5c9f0b116305d','Станислав','Малышев','+375295556933','stat@super.com','Республика Беларусь','2010-11-14',1,2),(47,'mam','202cb962ac59075b964b07152d234b70','Мама','Петрова','+375291044298','mam@list.ru','Брест','1969-05-14',1,2),(48,'papa','202cb962ac59075b964b07152d234b70','Папа','Иванов','+375256257415','pap123@list.ru','Витебск','1999-12-31',1,2),(49,'papa123','202cb962ac59075b964b07152d234b70','Папа','Иванов','+375256257415','papa123@list.ru','Витебск','1999-12-31',1,2),(50,'sister','1327a71aeeef17500851d23af9c6b87b','Сестра','Иванова','+375252555566','sister123@list.ru','Брест','2002-12-31',1,2),(51,'tst0','202cb962ac59075b964b07152d234b70','Тест','Тестович','-','tst0@tut.by','-','2000-12-31',1,2),(52,'tst01','202cb962ac59075b964b07152d234b70','Тест','Тестович','-','tst01@tut.by','-','2000-12-31',1,2),(53,'tst02','202cb962ac59075b964b07152d234b70','Тест','Тестович','-','tst02@tut.by','-','2002-02-01',1,2),(54,'tst03','202cb962ac59075b964b07152d234b70','Тестович','Тестттт','-','ttt@tt.tu','-','2002-12-31',1,2),(55,'musia','202cb962ac59075b964b07152d234b70','Муся','-','-','musioa@li.nm','-','2015-12-24',1,2),(56,'tst05','202cb962ac59075b964b07152d234b70','Тест','Тестович','-','tst05@live.ru','-','2020-02-01',1,2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-11 12:07:13
