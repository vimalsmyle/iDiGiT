-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: idigitest
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `alertsettings`
--

DROP TABLE IF EXISTS `alertsettings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alertsettings` (
  `AlertID` int NOT NULL AUTO_INCREMENT,
  `NoAMRInterval` bigint NOT NULL,
  `TimeOut` int NOT NULL,
  `ReconnectionCharges` int NOT NULL,
  `LateFee` int NOT NULL,
  `ReconnectionChargeDays` int NOT NULL,
  `DueDayCount` int NOT NULL,
  `BillGenerationDate` varchar(1000) NOT NULL,
  `GST` int NOT NULL,
  `VendorGSTNumber` varchar(100) DEFAULT NULL,
  `CustomerGSTNumber` varchar(100) DEFAULT NULL,
  `Remarks` varchar(1000) DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`AlertID`),
  KEY `AlertID` (`AlertID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 MAX_ROWS=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alertsettings`
--

LOCK TABLES `alertsettings` WRITE;
/*!40000 ALTER TABLE `alertsettings` DISABLE KEYS */;
INSERT INTO `alertsettings` VALUES (1,4320,330,51,50,20,10,'2nd of everymonth',0,NULL,NULL,'All the water meter readings are shown in liters',SYSDATE(),SYSDATE());
/*!40000 ALTER TABLE `alertsettings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `UserID` varchar(100) DEFAULT NULL,
  `UserName` varchar(100) NOT NULL,
  `UserPassword` varchar(100) NOT NULL,
  `RoleID` int NOT NULL,
  `ActiveStatus` int NOT NULL,
  `Token` varchar(45) DEFAULT NULL,
  `CommunityID` bigint NOT NULL,
  `BlockID` bigint DEFAULT NULL,
  `CustomerID` bigint DEFAULT NULL,
  `CustomerUniqueID` varchar(100) DEFAULT NULL,
  `MobileNumber` varchar(100) DEFAULT NULL,
  `Email` varchar(1000) DEFAULT NULL,
  `CreatedByID` bigint NOT NULL,
  `CreatedByRoleID` tinyint NOT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CommunityID` (`CommunityID`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Superadmin','Idigitronics','cvp/LzpadrQT+2k0WDjyOQ==',1,1,NULL,0,0,0,NULL,NULL,NULL,0,0,SYSDATE(),SYSDATE());
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userrole` (
  `RoleID` int NOT NULL,
  `RoleDescription` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,'Super Admin'),(2,'Admin'),(3,'User'),(4,'SuperAdminSupervisor'),(5,'AdminSupervisor'),(6,'Support');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-31 12:07:19
