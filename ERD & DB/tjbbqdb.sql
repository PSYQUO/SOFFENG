CREATE DATABASE  IF NOT EXISTS `tjbbqdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tjbbqdb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tjbbqdb
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `Category_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Category_Name` varchar(45) NOT NULL,
  PRIMARY KEY (`Category_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Sandwiches'),(2,'Appetizer'),(3,'Budget Meal'),(4,'Combo Meal'),(5,'Pasta'),(6,'Rice'),(7,'Dessert'),(8,'Drinks'),(9,'Favorites'),(10,'Extras');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumable`
--

DROP TABLE IF EXISTS `consumable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumable` (
  `Consumable_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Consumable_Name` varchar(100) NOT NULL,
  `Consumable_CodeName` varchar(45) DEFAULT NULL,
  `Consumable_Price` float NOT NULL,
  `Meal_ID` int(11) DEFAULT NULL,
  `Category_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`Consumable_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumable`
--

LOCK TABLES `consumable` WRITE;
/*!40000 ALTER TABLE `consumable` DISABLE KEYS */;
INSERT INTO `consumable` VALUES (1,'Crispy Pork Chop','',99,NULL,'3'),(2,'Juicy Fried Chicken','',99,NULL,'3'),(3,'Crispy Liempo','',99,NULL,'3'),(4,'Beef Nachos','',145,NULL,'2'),(5,'Chicken Croquettes','',145,NULL,'2'),(6,'Buffalo Wings','',145,NULL,'2'),(7,'Hungarian Sausage','',145,NULL,'1'),(8,'Hamburger','',125,NULL,'1'),(9,'Hamburger w/ Cheese','',140,NULL,'1'),(10,'Hungarian','',140,NULL,'1'),(11,'Sriracha BBQ Chicken Skewers','',50,NULL,'2'),(12,'Sriracha Pork BBQ Skewers','',50,NULL,'2'),(13,'Fries Ala Carte','',80,NULL,'2'),(14,'Twisty Fries','',95,NULL,'2'),(15,'Cross Fries','',95,NULL,'2'),(16,'Crispy Wedges','',95,NULL,'2'),(17,'Jalapeno Balls','',155,NULL,'2'),(18,'Mozarella Cheese Sticks','',175,NULL,'2'),(19,'Sizzling Sisig','',220,NULL,'2'),(20,'Beef Nachos','',145,NULL,'2'),(49,'Chicken Quarter',NULL,125,NULL,'4'),(50,'Pork BBQ 2 pcs',NULL,105,NULL,'4'),(51,'Liempo BBQ',NULL,125,NULL,'4'),(52,'Porkchop BBQ 2 pcs',NULL,125,NULL,'4'),(53,'BBQ Spareribs',NULL,145,NULL,'4'),(54,'Baby Back Ribs',NULL,185,NULL,'4'),(55,'Grilled Beefy Burger',NULL,105,NULL,'4'),(56,'Grilled Hungarian 2pcs',NULL,145,NULL,'4'),(57,'Grilled Beefy Belly',NULL,195,NULL,'4'),(58,'Grilled Bulalo Steak',NULL,195,NULL,'4'),(59,'Beef Baby Back Ribs',NULL,195,NULL,'4'),(60,'Grilled Bangus',NULL,115,NULL,'4'),(61,'Grilled Tuna Belly',NULL,195,NULL,'4'),(62,'Grilled Tuna Panga',NULL,310,NULL,'4'),(78,'Spaghetti w/ Meatballs',NULL,125,NULL,'5'),(79,'Spaghetti Carbonara',NULL,125,NULL,'5'),(80,'Plain',NULL,20,NULL,'6'),(81,'Garlic',NULL,25,NULL,'6'),(82,'Java',NULL,25,NULL,'6'),(83,'Halo Halo',NULL,70,NULL,'7'),(84,'Saba Con Hielo',NULL,60,NULL,'7'),(85,'Mais Con Hielo',NULL,60,NULL,'7'),(86,'Lemon Iced Tea',NULL,40,NULL,'8'),(87,'Raspberry Juice',NULL,40,NULL,'8'),(88,'Iced Tea Litro',NULL,125,NULL,'8'),(89,'Bottled Water',NULL,20,NULL,'8'),(90,'Coke',NULL,40,NULL,'8'),(91,'Pineapple Juice in Can',NULL,45,NULL,'8'),(92,'San Mig Pale',NULL,55,NULL,'8'),(93,'San Mig Light',NULL,55,NULL,'8'),(94,'Sprite',NULL,40,NULL,'8');
/*!40000 ALTER TABLE `consumable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incoming`
--

DROP TABLE IF EXISTS `incoming`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incoming` (
  `In_DateTime` datetime NOT NULL,
  `In_Quantity` int(11) NOT NULL,
  `In_Remarks` varchar(255) DEFAULT NULL,
  `RawItem_ID` int(11) NOT NULL,
  `In_ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`In_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incoming`
--

LOCK TABLES `incoming` WRITE;
/*!40000 ALTER TABLE `incoming` DISABLE KEYS */;
INSERT INTO `incoming` VALUES ('2017-10-14 01:00:00',3,'Hello World',2,1);
/*!40000 ALTER TABLE `incoming` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info`
--

DROP TABLE IF EXISTS `info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info` (
  `POS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(225) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  PRIMARY KEY (`POS_ID`),
  UNIQUE KEY `telephone_UNIQUE` (`telephone`),
  UNIQUE KEY `address_UNIQUE` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info`
--

LOCK TABLES `info` WRITE;
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
/*!40000 ALTER TABLE `info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredient` (
  `Consumable_ID` int(11) NOT NULL,
  `RawItem_ID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (2,3,2),(2,2,1),(2,3,2),(2,2,1),(2,3,2),(2,2,1);
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineitem`
--

DROP TABLE IF EXISTS `lineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lineitem` (
  `Transaction_ID` int(11) NOT NULL,
  `Consumable_ID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineitem`
--

LOCK TABLES `lineitem` WRITE;
/*!40000 ALTER TABLE `lineitem` DISABLE KEYS */;
INSERT INTO `lineitem` VALUES (0,2,4),(1,1,3),(2,1,3),(3,2,4),(0,3,1),(0,3,1),(0,3,1),(4,83,10),(5,84,20),(6,85,15);
/*!40000 ALTER TABLE `lineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal` (
  `Meal_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AddOns` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Meal_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outgoing`
--

DROP TABLE IF EXISTS `outgoing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `outgoing` (
  `Out_DateTime` datetime NOT NULL,
  `Out_Quantity` int(11) NOT NULL,
  `Out_Remarks` varchar(255) DEFAULT NULL,
  `RawItem_ID` int(11) NOT NULL,
  `Out_ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Out_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outgoing`
--

LOCK TABLES `outgoing` WRITE;
/*!40000 ALTER TABLE `outgoing` DISABLE KEYS */;
/*!40000 ALTER TABLE `outgoing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rawitem`
--

DROP TABLE IF EXISTS `rawitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rawitem` (
  `RawItem_ID` int(11) NOT NULL AUTO_INCREMENT,
  `RawItem_Name` varchar(45) NOT NULL,
  `RawItem_Price` float NOT NULL,
  `RawItem_Quantity` varchar(45) NOT NULL,
  PRIMARY KEY (`RawItem_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rawitem`
--

LOCK TABLES `rawitem` WRITE;
/*!40000 ALTER TABLE `rawitem` DISABLE KEYS */;
INSERT INTO `rawitem` VALUES (1,'Onion',333,'222'),(2,'Patty',444,'333'),(3,'Bun',12,'31234'),(4,'Cabbage',5,'195');
/*!40000 ALTER TABLE `rawitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `Role_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Role_Name` varchar(45) NOT NULL,
  PRIMARY KEY (`Role_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Owner'),(2,'Supervisor'),(3,'Cashier');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `Transaction_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Transaction_DateTime` datetime NOT NULL,
  `User_ID` int(11) NOT NULL,
  `Customer_Number` int(11) DEFAULT NULL,
  `Trans_Type` varchar(45) NOT NULL,
  `Cash` float NOT NULL,
  `Change` float DEFAULT NULL,
  `Subtotal` float NOT NULL,
  `Senior_Discount` float DEFAULT NULL,
  `Total` float NOT NULL,
  PRIMARY KEY (`Transaction_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'2017-10-14 01:00:00',1,22,'dine-in',144,NULL,144,NULL,144),(2,'2017-10-14 00:00:00',1,23,'dine-in',3123,NULL,3123,NULL,3105),(3,'2017-12-14 00:00:00',1,24,'dine-in',1331,NULL,1331,NULL,1331),(4,'2017-12-14 00:00:00',6,1,'dine-in',2342,NULL,2342,NULL,2342),(5,'2018-01-02 00:00:00',6,2,'dine-in',2231,NULL,2231,NULL,2231),(6,'2018-01-02 00:00:00',6,3,'dine-in',3331,NULL,3331,NULL,3331),(7,'2018-01-02 00:00:00',6,4,'dine-in',604,NULL,604,NULL,604);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `User_ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_Username` varchar(45) NOT NULL,
  `User_Name` varchar(45) NOT NULL,
  `User_Password` varchar(45) NOT NULL,
  `Role_ID` int(11) NOT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'jordan','jose','1234',1),(2,'jolene','jolene','1234',2),(3,'gavin','mark','1234',3),(4,'jason','clark','1234',1),(5,'carlo','gian','1234',2),(6,'nixon','nixon','1234',3),(7,'allen','patrick','1234',1),(8,'stephen','stephen','1234',2),(9,'sing','nyles','1234',3),(10,'glen','jasper','1234',1);
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

-- Dump completed on 2017-11-04 21:34:13
