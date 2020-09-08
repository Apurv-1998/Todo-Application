-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: todo_application
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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activity_id` varchar(255) NOT NULL,
  `creation_time` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `text` varchar(255) NOT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `todo_id` bigint DEFAULT NULL,
  `activity_name` varchar(255) NOT NULL,
  `is_completed` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7xecinwc1lpgj2ldjcdg6uyjy` (`todo_id`),
  CONSTRAINT `FK7xecinwc1lpgj2ldjcdg6uyjy` FOREIGN KEY (`todo_id`) REFERENCES `todo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'fe4hi','2020-09-07 09:35:19.358000','2020-09-02 05:30:00.000000','2020-09-01 05:30:00.000000','Search for the best avaialble course that can help to clear the basics of spring boot and start it','2020-09-07 09:35:19.358000',2,'Start Spring Boot Beginner',_binary '\0'),(2,'1fqq1','2020-09-08 19:12:53.862000','2020-09-09 05:30:00.000000','2020-09-08 05:30:00.000000','<p><em>Selected Courses:-</em></p>\n<ol>\n<li><span style=\"background-color: #bfedd2;\">Spring Basics to Masters</span></li>\n<li><span style=\"background-color: #bfedd2;\">Spring Fullstack</span></li>\n</ol>','2020-09-08 19:12:53.862000',2,'Select Best Course From The List',_binary '\0'),(3,'79uHy','2020-09-08 19:43:53.467000','2020-09-09 05:30:00.000000','2020-09-09 05:30:00.000000','<p><em><strong>Concentrate on the production value of the product.</strong></em></p>','2020-09-08 19:43:53.467000',5,'Attend Meeting',_binary '\0');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-08 20:24:40
