-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pitos
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `description` varchar(45) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'Αμπελόκηποι'),(2,'Παπάγου'),(3,'Κέντρο Αθήνας'),(4,'Ζωγράφου');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `award`
--

DROP TABLE IF EXISTS `award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `award` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(45) DEFAULT NULL,
                         `pie_id` int DEFAULT NULL,
                         `order` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `fk_award_pie` (`pie_id`),
                         CONSTRAINT `fk_award_pie` FOREIGN KEY (`pie_id`) REFERENCES `pie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `award`
--

LOCK TABLES `award` WRITE;
/*!40000 ALTER TABLE `award` DISABLE KEYS */;
INSERT INTO `award` VALUES (1,'Βραβείο Καλύτερης Πίτας 2013',1,1),(2,'Βραβείο Καλύτερης Πίτας 1991',4,1),(3,'Βραβείο Καλύτερης Πίτας 1992',4,3);
/*!40000 ALTER TABLE `award` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `name` varchar(45) NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,'Σπανάκι'),(2,'Φέτα'),(3,'Μανιτάρια'),(4,'Βούτυρο'),(5,'Πράσα'),(6,'Κολοκύθια'),(7,'Πατάτες');
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `fullname` varchar(200) DEFAULT NULL,
                         `address` varchar(200) DEFAULT NULL,
                         `area_id` int DEFAULT NULL,
                         `email` varchar(50) DEFAULT NULL,
                         `tel` varchar(20) DEFAULT NULL,
                         `comments` varchar(200) DEFAULT NULL,
                         `offer` tinyint DEFAULT NULL,
                         `payment` varchar(20) DEFAULT NULL,
                         `stamp` datetime DEFAULT NULL,
                         `user_id` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `fk_order_area` (`area_id`),
                         CONSTRAINT `fk_order_area` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'NIKOLAOS POULOPOULOS','SOYNIOY 21',4,'nikolaspoulopoulos2005@gmail.com','6907217324','make the crispy xd',0,'cash','2026-08-21 14:18:09',NULL),(5,'NIKOLAOS POULOPOULOS','SOYNIOY 21',2,'nickstation007@gmail.com','6907217324','ada',0,'visa','2026-08-23 15:43:52',NULL),(6,'NIKOLAOS POULOPOULOS','SOYNIOY 21',1,'nikolaspoulopoulos2005@gmail.com','6907217324','1213',0,'visa','2026-08-23 15:49:25',2),(7,'NIKOLAOS POULOPOULOS','SOYNIOY 21',1,'nikolaspoulopoulos2005@gmail.com','6969696969','wa',0,'visa','2026-08-23 19:51:39',2);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
                              `order_id` int NOT NULL,
                              `pie_id` int NOT NULL,
                              `quantity` int DEFAULT NULL,
                              PRIMARY KEY (`order_id`,`pie_id`),
                              KEY `fk_order_item_pie` (`pie_id`),
                              CONSTRAINT `fk_order_item_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE,
                              CONSTRAINT `fk_order_item_pie` FOREIGN KEY (`pie_id`) REFERENCES `pie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,2),(1,2,0),(1,3,2),(1,4,0),(5,1,0),(5,2,2),(5,3,0),(5,4,0),(6,1,3),(6,2,3),(6,3,0),(6,4,0),(7,1,1),(7,2,0),(7,3,0),(7,4,0);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pie`
--

DROP TABLE IF EXISTS `pie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pie` (
                       `id` int NOT NULL AUTO_INCREMENT,
                       `name` varchar(200) DEFAULT NULL,
                       `price` double DEFAULT NULL,
                       `filename` varchar(200) DEFAULT NULL,
                       PRIMARY KEY (`id`),
                       UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pie`
--

LOCK TABLES `pie` WRITE;
/*!40000 ALTER TABLE `pie` DISABLE KEYS */;
INSERT INTO `pie` VALUES (1,'Σπανακόπιτα',4,'spanakopita.jpg'),(2,'Μανιταρόπιτα',5.5,'manitaropita.jpg'),(3,'Πρασόπιτα',3.5,'prasopita.jpg'),(4,'Μπουρέκι',4.5,'boureki.jpg');
/*!40000 ALTER TABLE `pie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pie_ingredient`
--

DROP TABLE IF EXISTS `pie_ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pie_ingredient` (
                                  `pie_id` int NOT NULL,
                                  `ingredient_id` int NOT NULL,
                                  PRIMARY KEY (`pie_id`,`ingredient_id`),
                                  KEY `fk_pieingredient_ingredient` (`ingredient_id`),
                                  CONSTRAINT `fk_pieingredient_ingredient` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                  CONSTRAINT `fk_pieingredient_pie` FOREIGN KEY (`pie_id`) REFERENCES `pie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pie_ingredient`
--

LOCK TABLES `pie_ingredient` WRITE;
/*!40000 ALTER TABLE `pie_ingredient` DISABLE KEYS */;
INSERT INTO `pie_ingredient` VALUES (1,1),(1,2),(3,2),(2,3),(2,4),(3,5),(4,6),(4,7);
/*!40000 ALTER TABLE `pie_ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(45) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(45) DEFAULT NULL,
                        `password` varchar(100) DEFAULT NULL,
                        `fullname` varchar(20) DEFAULT NULL,
                        `email` varchar(50) DEFAULT NULL,
                        `tel` varchar(50) DEFAULT NULL,
                        `status` varchar(50) DEFAULT NULL,
                        `code` varchar(45) DEFAULT NULL,
                        `session` varchar(45) DEFAULT NULL,
                        `salt` varchar(100) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'nikolasp','$2a$10$kI96jWN9LpZjmDtVY5ZX5e1FFu1LMyGdPoG0iTCAGwOKlBOljABx2','NIKOLAOS POULOPOULOS','nickstation007@gmail.com','6907217324','verified','1697',NULL,NULL),(2,'nikolas','$2a$10$1Lj6wIXj/qqlLamBQrn5X.xJn9USxFCsNBIXsAm9nLfpZYcqaCxga','NIKOLAOS POULOPOULOS','nikolaspoulopoulos2005@gmail.com','6907217324','verified',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
                             `user_id` int NOT NULL,
                             `role_id` int NOT NULL,
                             PRIMARY KEY (`user_id`,`role_id`),
                             KEY `role_id` (`role_id`),
                             CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                             CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,2),(2,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-23 19:55:57
