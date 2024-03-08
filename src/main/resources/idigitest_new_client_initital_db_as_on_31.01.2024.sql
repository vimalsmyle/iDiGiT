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
-- Table structure for table `balancelog`
--

DROP TABLE IF EXISTS `balancelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `balancelog` (
  `ReadingID` bigint NOT NULL AUTO_INCREMENT,
  `MIUID` varchar(100) NOT NULL,
  `CommunityID` int DEFAULT NULL,
  `BlockID` int DEFAULT NULL,
  `CustomerID` bigint DEFAULT NULL,
  `CustomerMeterID` bigint DEFAULT NULL,
  `MeterSizeID` int DEFAULT NULL,
  `MeterSerialNumber` varchar(100) DEFAULT NULL,
  `CustomerUniqueID` varchar(100) DEFAULT NULL,
  `MeterType` varchar(100) NOT NULL,
  `SyncTime` varchar(100) NOT NULL,
  `SyncInterval` int NOT NULL,
  `PayType` varchar(100) NOT NULL,
  `BatteryVoltage` decimal(10,2) NOT NULL,
  `ValveConfiguration` tinyint NOT NULL,
  `ValveStatus` tinyint NOT NULL,
  `Balance` decimal(10,2) NOT NULL,
  `TariffID` int DEFAULT NULL,
  `Tariff` decimal(10,2) NOT NULL,
  `EmergencyCredit` decimal(10,2) NOT NULL,
  `Minutes` bigint DEFAULT NULL,
  `Reading` decimal(10,2) NOT NULL,
  `DoorOpenTamper` tinyint DEFAULT NULL,
  `MagneticTamper` tinyint DEFAULT NULL,
  `Vacation` tinyint DEFAULT NULL,
  `RTCFault` tinyint DEFAULT NULL,
  `NFCTamper` tinyint DEFAULT NULL,
  `LowBattery` tinyint DEFAULT NULL,
  `LowBalance` tinyint DEFAULT NULL,
  `LogDate` datetime NOT NULL,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=1576 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `billingdetails`
--

DROP TABLE IF EXISTS `billingdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billingdetails` (
  `BillingID` bigint NOT NULL AUTO_INCREMENT,
  `CommunityID` int NOT NULL,
  `BlockID` int NOT NULL,
  `CustomerID` bigint NOT NULL,
  `CustomerUniqueID` varchar(500) NOT NULL,
  `CustomerMeterID` bigint NOT NULL,
  `MeterType` varchar(100) NOT NULL,
  `MIUID` varchar(100) NOT NULL,
  `PreviousReading` decimal(10,2) NOT NULL,
  `PresentReading` decimal(10,2) NOT NULL,
  `Consumption` float NOT NULL,
  `TariffID` int NOT NULL,
  `Tariff` float NOT NULL,
  `BillAmount` float NOT NULL,
  `BillMonth` int DEFAULT NULL,
  `BillYear` int DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`BillingID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `billingpaymentdetails`
--

DROP TABLE IF EXISTS `billingpaymentdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billingpaymentdetails` (
  `TransactionID` bigint NOT NULL AUTO_INCREMENT,
  `CustomerBillingID` bigint NOT NULL,
  `CustomerID` bigint NOT NULL,
  `CustomerUniqueID` varchar(100) NOT NULL,
  `TotalAmount` float NOT NULL,
  `LateFee` float DEFAULT NULL,
  `Source` varchar(10) NOT NULL,
  `ModeOfPayment` varchar(50) NOT NULL,
  `PaymentStatus` tinyint unsigned NOT NULL DEFAULT '0',
  `RazorPayOrderID` varchar(50) DEFAULT NULL,
  `RazorPayPaymentID` varchar(50) DEFAULT NULL,
  `RazorPaySignature` varchar(5000) DEFAULT NULL,
  `ErrorResponse` varchar(10000) DEFAULT NULL,
  `RazorPayRefundID` varchar(50) DEFAULT NULL,
  `RazorPayRefundStatus` varchar(50) DEFAULT NULL,
  `RazorPayRefundEntity` varchar(10000) DEFAULT NULL,
  `CreatedByID` int NOT NULL,
  `CreatedByRoleID` int NOT NULL,
  `TransactionDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `AcknowledgeDate` datetime DEFAULT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `block` (
  `BlockID` bigint NOT NULL AUTO_INCREMENT,
  `BlockName` varchar(80) NOT NULL,
  `Location` varchar(300) DEFAULT NULL,
  `MobileNumber` varchar(10) DEFAULT NULL,
  `Email` varchar(300) DEFAULT NULL,
  `CommunityID` int NOT NULL,
  `CreatedByID` int NOT NULL,
  `CreatedByRoleID` tinyint NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`BlockID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `command`
--

DROP TABLE IF EXISTS `command`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `command` (
  `TransactionID` bigint NOT NULL AUTO_INCREMENT,
  `CustomerID` bigint NOT NULL,
  `CustomerMeterID` bigint NOT NULL,
  `MIUID` varchar(80) NOT NULL,
  `CustomerUniqueID` varchar(100) NOT NULL,
  `CreatedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `commanddetails`
--

DROP TABLE IF EXISTS `commanddetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commanddetails` (
  `CommandDetailsID` bigint NOT NULL AUTO_INCREMENT,
  `TransactionID` bigint NOT NULL,
  `CommandType` int NOT NULL,
  `Value` varchar(500) NOT NULL,
  `Status` tinyint NOT NULL DEFAULT '10',
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CommandDetailsID`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `community`
--

DROP TABLE IF EXISTS `community`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `community` (
  `CommunityID` bigint NOT NULL AUTO_INCREMENT,
  `CommunityName` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `Address` varchar(300) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CommunityID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customerbillingdetails`
--

DROP TABLE IF EXISTS `customerbillingdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerbillingdetails` (
  `CustomerBillingID` bigint NOT NULL AUTO_INCREMENT,
  `CommunityID` int NOT NULL,
  `BlockID` int NOT NULL,
  `CustomerID` bigint NOT NULL,
  `CustomerUniqueID` varchar(500) NOT NULL,
  `TotalAmount` float NOT NULL,
  `TaxAmount` float NOT NULL,
  `TotalConsumption` int NOT NULL,
  `PreviousDues` float DEFAULT NULL,
  `Status` tinyint NOT NULL DEFAULT '0',
  `DueDate` date NOT NULL,
  `BillMonth` int DEFAULT NULL,
  `BillYear` int DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CustomerBillingID`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customerdeletedetails`
--

DROP TABLE IF EXISTS `customerdeletedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerdeletedetails` (
  `CustomerDeleteID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int NOT NULL,
  `CommunityID` int NOT NULL,
  `BlockID` int NOT NULL,
  `HouseNumber` varchar(30) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `ActiveStatus` tinyint DEFAULT NULL,
  `CustomerUniqueID` varchar(50) NOT NULL,
  `CreatedByID` int NOT NULL,
  `CreatedByRoleID` tinyint NOT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CustomerDeleteID`,`CustomerUniqueID`),
  UNIQUE KEY `CRNNumber` (`CustomerUniqueID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customerdeletemeter`
--

DROP TABLE IF EXISTS `customerdeletemeter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerdeletemeter` (
  `CustomerMeterDeleteID` bigint NOT NULL AUTO_INCREMENT,
  `CustomerMeterID` bigint NOT NULL,
  `CustomerID` bigint NOT NULL,
  `CustomerUniqueID` varchar(50) NOT NULL,
  `MIUID` varchar(100) NOT NULL,
  `MeterSerialNumber` varchar(100) NOT NULL,
  `MeterType` varchar(12) NOT NULL,
  `MeterSizeID` int NOT NULL,
  `PayType` varchar(10) NOT NULL,
  `TariffID` int NOT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `ThresholdMaximum` float NOT NULL,
  `ThresholdMinimum` float NOT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`CustomerMeterDeleteID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customerdetails`
--

DROP TABLE IF EXISTS `customerdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerdetails` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `CommunityID` int NOT NULL,
  `BlockID` int NOT NULL,
  `HouseNumber` varchar(50) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `ActiveStatus` tinyint DEFAULT NULL,
  `CustomerUniqueID` varchar(50) NOT NULL,
  `CreatedByID` int NOT NULL,
  `CreatedByRoleID` tinyint NOT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CustomerID`,`CustomerUniqueID`),
  UNIQUE KEY `CRNNumber` (`CustomerUniqueID`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customermeterdetails`
--

DROP TABLE IF EXISTS `customermeterdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customermeterdetails` (
  `CustomerMeterID` bigint NOT NULL AUTO_INCREMENT,
  `CustomerID` bigint NOT NULL,
  `CustomerUniqueID` varchar(50) NOT NULL,
  `MIUID` varchar(100) NOT NULL,
  `MeterSerialNumber` varchar(100) NOT NULL,
  `MeterType` varchar(12) NOT NULL,
  `MeterSizeID` int NOT NULL,
  `PayType` varchar(10) NOT NULL,
  `TariffID` int NOT NULL,
  `GatewayID` int NOT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `ThresholdMaximum` float NOT NULL,
  `ThresholdMinimum` float NOT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`CustomerMeterID`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `displaybalancelog`
--

DROP TABLE IF EXISTS `displaybalancelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `displaybalancelog` (
  `ReadingID` bigint NOT NULL AUTO_INCREMENT,
  `MainBalanceLogID` bigint NOT NULL,
  `MIUID` varchar(100) NOT NULL,
  `CommunityID` int DEFAULT NULL,
  `BlockID` int DEFAULT NULL,
  `CustomerID` bigint DEFAULT NULL,
  `CustomerMeterID` bigint DEFAULT NULL,
  `MeterSizeID` int DEFAULT NULL,
  `MeterSerialNumber` varchar(100) DEFAULT NULL,
  `CustomerUniqueID` varchar(100) DEFAULT NULL,
  `MeterType` varchar(100) NOT NULL,
  `SyncTime` varchar(100) NOT NULL,
  `SyncInterval` int NOT NULL,
  `PayType` varchar(100) NOT NULL,
  `BatteryVoltage` decimal(10,2) NOT NULL,
  `ValveConfiguration` tinyint NOT NULL,
  `ValveStatus` tinyint NOT NULL,
  `Balance` decimal(10,2) NOT NULL,
  `TariffID` int DEFAULT NULL,
  `Tariff` decimal(10,2) NOT NULL,
  `EmergencyCredit` decimal(10,2) NOT NULL,
  `Minutes` bigint DEFAULT NULL,
  `Reading` decimal(10,2) NOT NULL,
  `DoorOpenTamper` tinyint DEFAULT NULL,
  `MagneticTamper` tinyint DEFAULT NULL,
  `Vacation` tinyint DEFAULT NULL,
  `RTCFault` tinyint DEFAULT NULL,
  `NFCTamper` tinyint DEFAULT NULL,
  `LowBattery` tinyint DEFAULT NULL,
  `LowBalance` tinyint DEFAULT NULL,
  `LogDate` datetime NOT NULL,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `FeedbackID` bigint NOT NULL AUTO_INCREMENT,
  `Feedback` varchar(200) DEFAULT NULL,
  `Description` varchar(2000) DEFAULT NULL,
  `Status` int NOT NULL DEFAULT '0',
  `CommunityID` int NOT NULL,
  `BlockID` int NOT NULL,
  `CustomerID` int NOT NULL,
  `CustomerUniqueID` varchar(50) NOT NULL,
  `Remarks` varchar(2000) DEFAULT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`FeedbackID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gateway`
--

DROP TABLE IF EXISTS `gateway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gateway` (
  `GatewayID` int NOT NULL AUTO_INCREMENT,
  `GatewayName` varchar(100) DEFAULT NULL,
  `GatewaySerialNumber` varchar(100) NOT NULL,
  `GatewayIP` varchar(100) NOT NULL,
  `GatewayPort` int DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`GatewayID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mailsettings`
--

DROP TABLE IF EXISTS `mailsettings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mailsettings` (
  `Host` varchar(50) NOT NULL,
  `User` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `port` varchar(50) NOT NULL,
  `FromMail` varchar(50) NOT NULL,
  `ToMail` varchar(50) NOT NULL,
  `Subject` varchar(60) NOT NULL,
  `Text` varchar(60) NOT NULL,
  `CommunityID` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `metersize`
--

DROP TABLE IF EXISTS `metersize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metersize` (
  `MeterSizeID` int NOT NULL AUTO_INCREMENT,
  `MeterType` varchar(100) NOT NULL,
  `MeterSize` int NOT NULL,
  `PerUnitValue` decimal(10,2) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ModifiedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MeterSizeID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `Sno` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `bank_trans_id` varchar(50) DEFAULT NULL,
  `card_brand` varchar(50) DEFAULT NULL,
  `card_issuer` varchar(50) DEFAULT NULL,
  `card_issuer_country` varchar(50) DEFAULT NULL,
  `card_issuer_country_code` varchar(50) DEFAULT NULL,
  `card_no` varchar(50) DEFAULT NULL,
  `card_type` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `store_amount` double DEFAULT NULL,
  `store_id` varchar(50) DEFAULT NULL,
  `trans_date` datetime DEFAULT NULL,
  `trans_id` varchar(50) DEFAULT NULL,
  `val_id` varchar(50) DEFAULT NULL,
  `verify_sign` varchar(50) DEFAULT NULL,
  `verify_key` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sensorlog`
--

DROP TABLE IF EXISTS `sensorlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sensorlog` (
  `ReadingID` int NOT NULL AUTO_INCREMENT,
  `equipment_serial_id` varchar(45) NOT NULL,
  `CommunityID` int NOT NULL,
  `BlockID` int NOT NULL,
  `CustomerID` int NOT NULL,
  `CustomerUniqueID` varchar(45) NOT NULL,
  `reading1` int NOT NULL DEFAULT '0',
  `reading2` int NOT NULL DEFAULT '0',
  `reading3` int NOT NULL DEFAULT '0',
  `reading4` int NOT NULL DEFAULT '0',
  `reader_sensor_status1` tinyint NOT NULL DEFAULT '0',
  `reader_sensor_status2` tinyint NOT NULL DEFAULT '0',
  `reader_sensor_status3` tinyint NOT NULL DEFAULT '0',
  `reader_sensor_status4` tinyint NOT NULL DEFAULT '0',
  `per_day_flow_rate1` float NOT NULL DEFAULT '0',
  `per_day_flow_rate2` float NOT NULL DEFAULT '0',
  `per_day_flow_rate3` float NOT NULL DEFAULT '0',
  `per_day_flow_rate4` float NOT NULL DEFAULT '0',
  `live_flow_rate1` float NOT NULL DEFAULT '0',
  `live_flow_rate2` float NOT NULL DEFAULT '0',
  `live_flow_rate3` float NOT NULL DEFAULT '0',
  `live_flow_rate4` float NOT NULL DEFAULT '0',
  `record_interval` int NOT NULL,
  `sync_interval` int NOT NULL,
  `rssi` int NOT NULL,
  `digital_outputs1` tinyint NOT NULL DEFAULT '0',
  `digital_outputs2` tinyint NOT NULL DEFAULT '0',
  `digital_outputs3` tinyint NOT NULL DEFAULT '0',
  `digital_outputs4` tinyint NOT NULL DEFAULT '0',
  `analog_inputs1` float NOT NULL DEFAULT '0',
  `analog_inputs2` float NOT NULL DEFAULT '0',
  `analog_inputs3` float NOT NULL DEFAULT '0',
  `analog_inputs4` float NOT NULL DEFAULT '0',
  `analog_outputs1` float NOT NULL DEFAULT '0',
  `analog_outputs2` float NOT NULL DEFAULT '0',
  `analog_outputs3` float NOT NULL DEFAULT '0',
  `analog_outputs4` float NOT NULL DEFAULT '0',
  `voltage_outputs1` float NOT NULL DEFAULT '0',
  `voltage_outputs2` float NOT NULL DEFAULT '0',
  `voltage_outputs3` float NOT NULL DEFAULT '0',
  `voltage_outputs4` float NOT NULL DEFAULT '0',
  `battery_percentage` int NOT NULL,
  `online_powersupply` tinyint NOT NULL,
  `gsm_status` tinyint NOT NULL,
  `ethernet_status` tinyint NOT NULL,
  `nfc_status` tinyint NOT NULL,
  `flash_status` tinyint NOT NULL,
  `nfc_memory_status` tinyint NOT NULL,
  `flash_memory_status` tinyint NOT NULL,
  `low_gsm` tinyint NOT NULL,
  `low_battery` tinyint NOT NULL,
  `sensor_detachment` tinyint NOT NULL,
  `door_open_switch` tinyint NOT NULL,
  `magnetic_tamper` tinyint NOT NULL,
  `timestamp` datetime NOT NULL,
  `LogDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tariff`
--

DROP TABLE IF EXISTS `tariff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tariff` (
  `TariffID` int NOT NULL AUTO_INCREMENT,
  `Tariff` float NOT NULL,
  `TariffName` varchar(100) NOT NULL,
  `EmergencyCredit` float DEFAULT NULL,
  `AlarmCredit` float DEFAULT NULL,
  `FixedCharges` float DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TariffID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topup`
--

DROP TABLE IF EXISTS `topup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topup` (
  `TransactionID` bigint NOT NULL AUTO_INCREMENT,
  `CommunityID` int NOT NULL,
  `BlockID` int NOT NULL,
  `CustomerID` bigint NOT NULL,
  `MIUID` varchar(50) NOT NULL,
  `CustomerMeterID` bigint NOT NULL,
  `TariffID` int NOT NULL,
  `Amount` decimal(10,0) NOT NULL,
  `Reading` decimal(10,2) DEFAULT '0.00',
  `Status` int unsigned NOT NULL DEFAULT '10',
  `FixedCharges` int unsigned DEFAULT '0',
  `ReconnectionCharges` int unsigned DEFAULT '0',
  `Source` varchar(10) NOT NULL,
  `ModeOfPayment` varchar(50) NOT NULL,
  `PaymentStatus` tinyint unsigned NOT NULL DEFAULT '0',
  `RazorPayOrderID` varchar(50) DEFAULT NULL,
  `RazorPayPaymentID` varchar(50) DEFAULT NULL,
  `RazorPaySignature` varchar(5000) DEFAULT NULL,
  `ErrorResponse` varchar(10000) DEFAULT NULL,
  `RazorPayRefundID` varchar(50) DEFAULT NULL,
  `RazorPayRefundStatus` varchar(50) DEFAULT NULL,
  `RazorPayRefundEntity` varchar(10000) DEFAULT NULL,
  `CreatedByID` int NOT NULL,
  `CreatedByRoleID` int NOT NULL,
  `CustomerUniqueID` varchar(100) NOT NULL,
  `TransactionDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `AcknowledgeDate` datetime NOT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `updaterequestcustomermeterdetails`
--

DROP TABLE IF EXISTS `updaterequestcustomermeterdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `updaterequestcustomermeterdetails` (
  `RequestID` bigint NOT NULL AUTO_INCREMENT,
  `BlockID` int NOT NULL,
  `CustomerID` int NOT NULL,
  `CustomerUniqueID` varchar(100) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `ToBeApprovedByID` int NOT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`RequestID`),
  UNIQUE KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `updaterequestcustomermeterdetails_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customerdetails` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-31 12:06:29
