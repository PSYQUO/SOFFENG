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
  PRIMARY KEY (`Category_ID`),
  UNIQUE KEY `Category_Name_UNIQUE` (`Category_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (2,'Appetizer'),(3,'Budget Meal'),(4,'Combo Meal'),(7,'Dessert'),(8,'Drinks'),(10,'Extras'),(9,'Favorites'),(5,'Pasta'),(6,'Rice'),(1,'Sandwiches');
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
  `Consumable_CodeName` varchar(15) DEFAULT NULL,
  `Consumable_Price` float NOT NULL,
  `Meal_ID` int(11) DEFAULT NULL,
  `Category_ID` int(11) NOT NULL,
  PRIMARY KEY (`Consumable_ID`),
  UNIQUE KEY `Consumable_Name_UNIQUE` (`Consumable_Name`),
  UNIQUE KEY `Consumable_CodeName_UNIQUE` (`Consumable_CodeName`),
  UNIQUE KEY `Meal_ID_UNIQUE` (`Meal_ID`),
  KEY `Consumable_CategoryID_idx` (`Category_ID`),
  CONSTRAINT `Consumable_CategoryID` FOREIGN KEY (`Category_ID`) REFERENCES `category` (`Category_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumable`
--

LOCK TABLES `consumable` WRITE;
/*!40000 ALTER TABLE `consumable` DISABLE KEYS */;
INSERT INTO `consumable` VALUES (1,'Crispy Pork Chop Meal',NULL,99,NULL,3),(2,'Juicy Fried Chicken Meal',NULL,99,NULL,3),(3,'Crispy Liempo Meal',NULL,99,NULL,3),(4,'Beef Nachos w/ Fries',NULL,145,NULL,9),(5,'Chicken Croquettes w/ Fries',NULL,145,NULL,9),(6,'Buffalo Wings w/ Fries',NULL,145,NULL,9),(7,'Hungarian Sausage w/ Fries',NULL,145,NULL,9),(8,'Hamburger',NULL,125,NULL,1),(9,'Hamburger w/ Cheese',NULL,140,NULL,1),(10,'Hungarian',NULL,140,NULL,1),(11,'Sriracha BBQ Chicken Skewers',NULL,50,NULL,2),(12,'Sriracha Pork BBQ Skewers',NULL,50,NULL,2),(13,'Fries Ala Carte',NULL,80,NULL,2),(14,'Twisty Fries',NULL,95,NULL,2),(15,'Cross Fries',NULL,95,NULL,2),(16,'Crispy Wedges',NULL,95,NULL,2),(17,'Jalapeno Balls',NULL,155,NULL,2),(18,'Mozarella Cheese Sticks',NULL,175,NULL,2),(19,'Sizzling Sisig',NULL,220,NULL,2),(49,'Chicken Quarter',NULL,125,NULL,4),(50,'Pork BBQ 2 pcs',NULL,105,NULL,4),(51,'Liempo BBQ',NULL,125,NULL,4),(52,'Porkchop BBQ 2 pcs',NULL,125,NULL,4),(53,'BBQ Spareribs',NULL,145,NULL,4),(54,'Baby Back Ribs',NULL,185,NULL,4),(55,'Grilled Beefy Burger',NULL,105,NULL,4),(56,'Grilled Hungarian 2pcs',NULL,145,NULL,4),(57,'Grilled Beefy Belly',NULL,195,NULL,4),(58,'Grilled Bulalo Steak',NULL,195,NULL,4),(59,'Beef Baby Back Ribs',NULL,195,NULL,4),(60,'Grilled Bangus',NULL,115,NULL,4),(61,'Grilled Tuna Belly',NULL,195,NULL,4),(62,'Grilled Tuna Panga',NULL,310,NULL,4),(78,'Spaghetti w/ Meatballs',NULL,125,NULL,5),(79,'Spaghetti Carbonara',NULL,125,NULL,5),(80,'Plain',NULL,20,NULL,6),(81,'Garlic',NULL,25,NULL,6),(82,'Java',NULL,25,NULL,6),(83,'Halo Halo',NULL,70,NULL,7),(84,'Saba Con Hielo',NULL,60,NULL,7),(85,'Mais Con Hielo',NULL,60,NULL,7),(86,'Lemon Iced Tea',NULL,40,NULL,8),(87,'Raspberry Juice',NULL,40,NULL,8),(88,'Iced Tea Litro',NULL,125,NULL,8),(89,'Bottled Water',NULL,20,NULL,8),(90,'Coke',NULL,40,NULL,8),(91,'Pineapple Juice in Can',NULL,45,NULL,8),(92,'San Mig Pale',NULL,55,NULL,8),(93,'San Mig Light',NULL,55,NULL,8),(94,'Sprite',NULL,40,NULL,8),(95,'Chicken Quarter Meal',NULL,165,NULL,4),(96,'Pork BBQ 2 pcs Meal',NULL,145,NULL,4),(97,'Liempo BBQ Meal',NULL,165,NULL,4),(98,'Porkchop BBQ 2 pcs Meal',NULL,165,NULL,4),(99,'BBQ Spareribs Meal',NULL,185,NULL,4),(100,'Baby Back Ribs Meal',NULL,225,NULL,4),(101,'Grilled Beefy Burger Meal',NULL,145,NULL,4),(102,'Grilled Hungarian 2pcs Meal',NULL,185,NULL,4),(103,'Grilled Beefy Belly Meal',NULL,235,NULL,4),(104,'Grilled Bulalo Steak Meal',NULL,235,NULL,4),(105,'Beef Baby Back Ribs Meal',NULL,235,NULL,4),(106,'Grilled Bangus Meal',NULL,155,NULL,4),(107,'Grilled Tuna Belly Meal',NULL,235,NULL,4),(108,'Grilled Tuna Panga Meal',NULL,350,NULL,4);
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
  PRIMARY KEY (`In_ID`),
  KEY `In_RawItem_ID_idx` (`RawItem_ID`),
  CONSTRAINT `In_RawItem_ID` FOREIGN KEY (`RawItem_ID`) REFERENCES `rawitem` (`RawItem_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredient` (
  `Consumable_ID` int(11) NOT NULL,
  `RawItem_ID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  KEY `Ingredient_Consumable_ID_idx` (`Consumable_ID`),
  KEY `Ingredient_RawItem_ID_idx` (`RawItem_ID`),
  CONSTRAINT `Ingredient_Consumable_ID` FOREIGN KEY (`Consumable_ID`) REFERENCES `consumable` (`Consumable_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Ingredient_RawItem_ID` FOREIGN KEY (`RawItem_ID`) REFERENCES `rawitem` (`RawItem_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `Meal_ID` int(11) NOT NULL,
  `AddOns` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
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
  PRIMARY KEY (`Out_ID`),
  KEY `Out_RawItem_ID_idx` (`RawItem_ID`),
  CONSTRAINT `Out_RawItem_ID` FOREIGN KEY (`RawItem_ID`) REFERENCES `rawitem` (`RawItem_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `pos_info`
--

DROP TABLE IF EXISTS `pos_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pos_info` (
  `POS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(225) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  PRIMARY KEY (`POS_ID`),
  UNIQUE KEY `telephone_UNIQUE` (`telephone`),
  UNIQUE KEY `address_UNIQUE` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pos_info`
--

LOCK TABLES `pos_info` WRITE;
/*!40000 ALTER TABLE `pos_info` DISABLE KEYS */;
INSERT INTO `pos_info` VALUES (1,'Parkwood 2 Gate, Legaspi Street, Maybunga, Pasig City','0939-527-9331');
/*!40000 ALTER TABLE `pos_info` ENABLE KEYS */;
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
  PRIMARY KEY (`RawItem_ID`),
  UNIQUE KEY `RawItem_Name_UNIQUE` (`RawItem_Name`)
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
  PRIMARY KEY (`Role_ID`),
  UNIQUE KEY `Role_Name_UNIQUE` (`Role_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (3,'Cashier'),(1,'Owner'),(2,'Supervisor');
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
  `Transaction_Type` varchar(45) NOT NULL,
  `Cash` float NOT NULL,
  `Change` float DEFAULT NULL,
  `Subtotal` float NOT NULL,
  `Senior_Discount` float DEFAULT NULL,
  `Total` float NOT NULL,
  PRIMARY KEY (`Transaction_ID`),
  KEY `Transaction_User_ID_idx` (`User_ID`),
  CONSTRAINT `Transaction_User_ID` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `User_Username_UNIQUE` (`User_Username`),
  UNIQUE KEY `User_Name_UNIQUE` (`User_Name`),
  KEY `User_Role_ID_idx` (`Role_ID`),
  CONSTRAINT `User_Role_ID` FOREIGN KEY (`Role_ID`) REFERENCES `role` (`Role_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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

-- Dump completed on 2017-11-07 21:20:04
