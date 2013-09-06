-- MySQL dump 10.10
--
-- Host: localhost    Database: casinoGod
-- ------------------------------------------------------
-- Server version	5.0.27-community-nt

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
-- Table structure for table `casinogod_account`
--

DROP TABLE IF EXISTS `casinogod_account`;
CREATE TABLE `casinogod_account` (
  `account` int(50) NOT NULL auto_increment,
  `password` varchar(225) NOT NULL,
  `freeze` int(10) default NULL,
  PRIMARY KEY  (`account`),
  KEY `account_id` (`account`),
  CONSTRAINT `casinoGod_account_ibfk_1` FOREIGN KEY (`account`) REFERENCES `casinogod_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_account`
--

LOCK TABLES `casinogod_account` WRITE;
/*!40000 ALTER TABLE `casinogod_account` DISABLE KEYS */;
INSERT INTO `casinogod_account` VALUES (100,'e10adc3949ba59abbe56e057f20f883e',0),(10000,'e10adc3949ba59abbe56e057f20f883e',0),(10001,'e10adc3949ba59abbe56e057f20f883e',0),(10006,'e10adc3949ba59abbe56e057f20f883e',0),(10007,'e10adc3949ba59abbe56e057f20f883e',1),(10008,'e120ea280aa50693d5568d0071456460',0),(10009,'d678e79715e811ce58f4057ad551a864',0),(10010,'e10adc3949ba59abbe56e057f20f883e',0),(10011,'e10adc3949ba59abbe56e057f20f883e',0),(10012,'e10adc3949ba59abbe56e057f20f883e',0),(10013,'e10adc3949ba59abbe56e057f20f883e',0),(10014,'25f9e794323b453885f5181f1b624d0b',0),(10015,'25f9e794323b453885f5181f1b624d0b',0),(10016,'200820e3227815ed1756a6b531e7e0d2',0),(10017,'db459cd583c215cbcc3fdd4990ce0047',0);
/*!40000 ALTER TABLE `casinogod_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_battlehistory`
--

DROP TABLE IF EXISTS `casinogod_battlehistory`;
CREATE TABLE `casinogod_battlehistory` (
  `id` int(10) NOT NULL auto_increment,
  `battleId` varchar(20) default NULL,
  `owenId` int(10) NOT NULL,
  `userList` varchar(50) default NULL,
  `battleStatue` int(5) default NULL,
  `battleType` int(5) default NULL,
  `createTime` datetime default NULL,
  `result` text,
  `otherNotes` text,
  PRIMARY KEY  (`id`),
  KEY `battleHistory_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_battlehistory`
--

LOCK TABLES `casinogod_battlehistory` WRITE;
/*!40000 ALTER TABLE `casinogod_battlehistory` DISABLE KEYS */;
INSERT INTO `casinogod_battlehistory` VALUES (1,'7310786929331233580',10000,NULL,1,1,'2013-04-07 10:24:55',NULL,NULL),(2,'-2734322864936961211',10000,NULL,1,1,'2013-04-11 16:42:55',NULL,NULL),(3,'-6287150757505570021',10000,NULL,1,1,'2013-04-25 16:19:53',NULL,NULL),(4,'-7745313890939382902',10000,NULL,1,1,'2013-04-25 16:27:22',NULL,NULL),(5,'7778176930679210701',10000,NULL,1,1,'2013-04-25 16:28:36',NULL,NULL),(6,'-7828363492431597256',10000,NULL,1,1,'2013-04-25 16:35:21',NULL,NULL);
/*!40000 ALTER TABLE `casinogod_battlehistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_boardcastinfo`
--

DROP TABLE IF EXISTS `casinogod_boardcastinfo`;
CREATE TABLE `casinogod_boardcastinfo` (
  `id` int(10) NOT NULL auto_increment,
  `title` text NOT NULL,
  `content` text,
  `startDate` datetime default NULL,
  `endDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `boardCast_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_boardcastinfo`
--

LOCK TABLES `casinogod_boardcastinfo` WRITE;
/*!40000 ALTER TABLE `casinogod_boardcastinfo` DISABLE KEYS */;
INSERT INTO `casinogod_boardcastinfo` VALUES (1,'Lucky Draw','Congratulations, \"xxx\" get the total bonus of 21 - Macau! You will be the next one!','2013-04-18 16:21:43','2013-04-19 19:21:44'),(2,'Callback','Your friends need your support, hurry up back to table','2013-04-19 16:27:38','2013-04-20 16:27:43'),(3,'test','谎言来到test东渡扶桑飞','2013-04-24 16:26:46','2013-04-25 16:26:49');
/*!40000 ALTER TABLE `casinogod_boardcastinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_friendrequest`
--

DROP TABLE IF EXISTS `casinogod_friendrequest`;
CREATE TABLE `casinogod_friendrequest` (
  `id` int(10) NOT NULL auto_increment,
  `owenId` int(50) NOT NULL,
  `userId` int(50) NOT NULL,
  `requestDate` datetime default NULL,
  `statue` int(10) default NULL,
  PRIMARY KEY  (`id`),
  KEY `friendRequest_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_friendrequest`
--

LOCK TABLES `casinogod_friendrequest` WRITE;
/*!40000 ALTER TABLE `casinogod_friendrequest` DISABLE KEYS */;
INSERT INTO `casinogod_friendrequest` VALUES (1,10000,10001,'2013-04-17 19:39:33',1),(2,10000,10006,'2013-04-18 10:34:00',1),(3,10001,10006,'2013-04-18 14:01:04',0),(4,10006,10000,'2013-04-18 14:09:31',1),(5,10006,10001,'2013-04-18 14:09:37',1),(6,10000,10007,'2013-04-18 14:52:45',0),(7,10000,10015,'2013-04-18 14:52:49',0),(8,10000,10008,'2013-04-25 10:39:44',0),(9,10000,10009,'2013-04-25 20:21:01',0);
/*!40000 ALTER TABLE `casinogod_friendrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_item`
--

DROP TABLE IF EXISTS `casinogod_item`;
CREATE TABLE `casinogod_item` (
  `id` int(10) NOT NULL auto_increment,
  `gameType` int(10) NOT NULL,
  `itemName` varchar(225) NOT NULL,
  `itemPrice` int(10) NOT NULL,
  `comment` text,
  PRIMARY KEY  (`id`),
  KEY `item_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_item`
--

LOCK TABLES `casinogod_item` WRITE;
/*!40000 ALTER TABLE `casinogod_item` DISABLE KEYS */;
INSERT INTO `casinogod_item` VALUES (1,0,'itemtest',2,'itemtest'),(2,0,'Golden Eyes',2,'See the dealer\'s card'),(3,0,'Super Spring',1,'Change the last \"Hit\" card'),(4,2,'Video Poker',1,'One more chance to change the cards'),(5,1,'test',2,'test');
/*!40000 ALTER TABLE `casinogod_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_itemhistory`
--

DROP TABLE IF EXISTS `casinogod_itemhistory`;
CREATE TABLE `casinogod_itemhistory` (
  `id` int(10) NOT NULL auto_increment,
  `gameType` int(10) NOT NULL,
  `owenId` int(50) NOT NULL,
  `purchaseType` int(10) NOT NULL,
  `itemName` varchar(225) default NULL,
  `itemNum` int(10) NOT NULL,
  `giftUserId` int(50) default NULL,
  `purchaseTime` datetime default NULL,
  `otherNotes` text,
  PRIMARY KEY  (`id`),
  KEY `itemHistory_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_itemhistory`
--

LOCK TABLES `casinogod_itemhistory` WRITE;
/*!40000 ALTER TABLE `casinogod_itemhistory` DISABLE KEYS */;
INSERT INTO `casinogod_itemhistory` VALUES (1,0,10000,0,'itemtest',1,0,'2013-04-10 11:35:24','test'),(2,0,10000,0,'itemtest',1,0,'2013-04-10 11:37:57','test'),(3,0,10000,0,'itemtest',2,0,'2013-04-10 11:40:12','test'),(4,0,10000,0,'Super Spring',1,0,'2013-04-10 17:20:45','test'),(5,0,10000,0,'Super Spring',1,0,'2013-04-10 17:22:52','test'),(6,0,10000,0,'Super Spring',2,0,'2013-04-10 18:29:14','See the dealer\'s 3 cards (30%), 4 cards (65%), 5 cards (5%'),(7,0,10000,1,'itemtest',1,0,'2013-04-10 19:48:24','test '),(8,0,10000,1,'itemtest',2,0,'2013-04-10 20:02:12','test'),(9,0,10000,1,'itemtest',1,0,'2013-04-10 20:17:20','test'),(10,0,10000,0,'Golden Eyes',2,0,'2013-04-10 20:36:55','test'),(11,0,10000,0,'itemtest',6,0,'2013-04-10 20:37:23','test'),(12,0,10000,1,'testItem',2,0,'2013-04-10 20:37:53','消费'),(13,0,10000,0,'Super Spring',10,0,'2013-04-10 20:38:11','test'),(14,2,10000,0,'Video Poker',10,0,'2013-04-10 20:38:39','test'),(15,0,10000,1,'itemtest',1,0,'2013-04-10 20:55:51','test'),(16,0,10000,1,'Super Spring',5,10001,'2013-04-10 20:58:00','test'),(17,0,10001,2,'Super Spring',2,10000,'2013-04-10 21:00:13','test'),(18,0,10000,2,'itemtest',2,10001,'2013-04-10 21:00:56','test'),(19,0,10000,0,'itemtest',1,0,'2013-04-11 16:47:07','test'),(20,0,10000,1,'Golden Eyes',1,0,'2013-04-15 10:11:36','test'),(21,0,10000,0,'itemtest',1,0,'2013-04-25 11:32:08','test'),(22,0,10000,2,'itemtest',2,10002,'2013-04-25 11:57:38','test'),(23,0,10000,2,'itemtest',2,10001,'2013-04-25 11:57:51','test'),(24,0,10000,0,'Golden',2,0,'2013-04-25 13:25:16','test'),(25,2,10000,0,'Video+Poker',1,0,'2013-04-25 13:42:19','test'),(26,0,10000,0,'Golden Eyes',1,0,'2013-04-25 15:05:35','test'),(27,0,10000,0,'Golden Eyes',1,0,'2013-04-25 15:06:39','test'),(28,0,10000,2,'Golden Eyes',1,10001,'2013-04-25 15:07:08','test'),(29,0,10000,2,'Super Spring',1,10001,'2013-04-25 15:19:48','tesfgsgs'),(30,0,10000,2,'Super Spring',1,10001,'2013-04-25 15:27:30','test'),(31,2,10000,0,'Video Poker',4,0,'2013-04-25 15:28:05','test'),(32,2,10000,0,'Video Poker',4,0,'2013-04-25 15:34:04','test'),(33,0,10000,1,'Golden Eyes',1,0,'2013-04-25 15:34:28','消费'),(34,0,10000,1,'Golden Eyes',1,0,'2013-04-25 15:35:50','消费'),(35,2,10000,2,'Video Poker',2,10002,'2013-04-25 15:50:14','送给你了'),(36,2,10000,2,'Video Poker',1,10002,'2013-04-25 18:01:15','test'),(37,2,10000,2,'Video Poker',1,10001,'2013-04-25 18:03:51','test'),(38,2,10000,2,'Video Poker',1,10007,'2013-04-25 18:29:25','test'),(39,0,10000,0,'itemtest',1,0,'2013-04-25 20:20:02','test');
/*!40000 ALTER TABLE `casinogod_itemhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_loginreward`
--

DROP TABLE IF EXISTS `casinogod_loginreward`;
CREATE TABLE `casinogod_loginreward` (
  `id` int(10) NOT NULL auto_increment,
  `userId` int(50) NOT NULL,
  `logInTime` datetime default NULL,
  `lastTimes` int(10) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `casinoGod_logInReward_ibfk_1` (`userId`),
  KEY `logInReward_id` (`id`),
  CONSTRAINT `casinoGod_logInReward_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `casinogod_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_loginreward`
--

LOCK TABLES `casinogod_loginreward` WRITE;
/*!40000 ALTER TABLE `casinogod_loginreward` DISABLE KEYS */;
INSERT INTO `casinogod_loginreward` VALUES (2,100,'2013-05-03 18:00:42',4),(3,10000,'2013-05-02 18:39:51',0),(4,10001,'2013-05-02 16:52:57',0),(5,10006,'2013-04-22 16:07:26',0);
/*!40000 ALTER TABLE `casinogod_loginreward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_loginrewardconfig`
--

DROP TABLE IF EXISTS `casinogod_loginrewardconfig`;
CREATE TABLE `casinogod_loginrewardconfig` (
  `id` int(10) NOT NULL auto_increment,
  `day` int(10) NOT NULL,
  `reward` int(10) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `logInRewardConfig_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_loginrewardconfig`
--

LOCK TABLES `casinogod_loginrewardconfig` WRITE;
/*!40000 ALTER TABLE `casinogod_loginrewardconfig` DISABLE KEYS */;
INSERT INTO `casinogod_loginrewardconfig` VALUES (1,1,1),(2,2,4),(3,3,6),(4,4,12);
/*!40000 ALTER TABLE `casinogod_loginrewardconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_lottery`
--

DROP TABLE IF EXISTS `casinogod_lottery`;
CREATE TABLE `casinogod_lottery` (
  `id` int(10) NOT NULL auto_increment,
  `userId` int(50) NOT NULL,
  `lotteryType` int(20) default NULL,
  `lotteryValue` varchar(20) default NULL,
  `betAmount` int(20) default NULL,
  `result` bit(1) default NULL,
  `betDateTime` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `lottery_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_lottery`
--

LOCK TABLES `casinogod_lottery` WRITE;
/*!40000 ALTER TABLE `casinogod_lottery` DISABLE KEYS */;
INSERT INTO `casinogod_lottery` VALUES (1,10000,0,'521033',100,'\0','2013-04-16 20:05:30'),(2,10000,1,'5122',1000,'\0','2013-04-24 17:24:03'),(3,10001,1,'2500',200,'\0','2013-04-29 10:22:18'),(4,10006,1,'2500',5000,'\0','2013-04-27 10:26:44'),(5,10000,2,'40',200,'\0','2013-04-19 10:31:29'),(6,10000,4,'1',500,'','2013-04-27 10:35:57');
/*!40000 ALTER TABLE `casinogod_lottery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_roominfo`
--

DROP TABLE IF EXISTS `casinogod_roominfo`;
CREATE TABLE `casinogod_roominfo` (
  `id` int(10) NOT NULL auto_increment,
  `gameType` int(10) NOT NULL,
  `roomLevel` varchar(225) NOT NULL,
  `levelScore` int(50) default NULL,
  `minHand` int(50) default NULL,
  `maxHand` int(50) default NULL,
  `otherNotes` text,
  `beginTime` datetime default NULL,
  `endTime` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `roomInfo_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_roominfo`
--

LOCK TABLES `casinogod_roominfo` WRITE;
/*!40000 ALTER TABLE `casinogod_roominfo` DISABLE KEYS */;
INSERT INTO `casinogod_roominfo` VALUES (1,1,'level1',2000,1000,2000,'','2013-04-07 09:45:50','2013-04-08 00:00:00'),(2,2,'level2',1000,1000,2000,'','1000-01-01 01:01:01','1000-01-01 01:01:01'),(3,0,'level2',5000,5000,100000,'test for qa','2013-04-17 00:00:00','2013-04-18 23:55:50');
/*!40000 ALTER TABLE `casinogod_roominfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_user`
--

DROP TABLE IF EXISTS `casinogod_user`;
CREATE TABLE `casinogod_user` (
  `userId` int(50) NOT NULL auto_increment,
  `nickName` varchar(225) NOT NULL,
  `gender` varchar(225) NOT NULL,
  `emailAddress` varchar(225) NOT NULL,
  `telephone` varchar(50) default NULL,
  `userType` int(50) default NULL,
  `registerDate` datetime default NULL,
  `image` varchar(225) default NULL,
  `level` int(50) default NULL,
  `exp` int(50) default NULL,
  `vipLevel` int(50) default NULL,
  `gold` int(50) default NULL,
  `diamond` int(50) default NULL,
  `otherNotes` text,
  `friendList` varchar(50) default NULL,
  PRIMARY KEY  (`userId`),
  UNIQUE KEY `nickName` (`nickName`,`emailAddress`,`telephone`),
  KEY `user_id` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_user`
--

LOCK TABLES `casinogod_user` WRITE;
/*!40000 ALTER TABLE `casinogod_user` DISABLE KEYS */;
INSERT INTO `casinogod_user` VALUES (100,'admin','m','njdxjjj@gmail.com','12584621357',100,'2013-04-20 12:23:45',NULL,100000,10033,100000,10000,10000,NULL,NULL),(10000,'testqa','man','njnd@gmail.com','15421877',0,'2013-03-30 20:21:24',NULL,0,22,0,0,9915,NULL,'10001#10006#'),(10001,'张三','m','njndxjjj@gmail.com','456123',0,'2013-03-30 00:00:00',NULL,0,5,0,0,0,NULL,'10000#10006#'),(10006,'领世军','m','njidjkejk@gmail.com','180045271',0,'2013-03-30 17:19:26',NULL,0,11,0,0,0,NULL,'10000#'),(10007,'刘英','m','kldjksjfk@gmail.com','187452003',0,'2013-04-01 10:32:45',NULL,0,0,0,0,0,NULL,NULL),(10008,'网络1','m','njnd@gmail.com','456123789',0,'2013-04-02 14:59:47',NULL,0,0,0,0,0,NULL,NULL),(10009,'懒死','m','njdskdjfk@gmail.com','123456',0,'2013-04-07 18:06:18',NULL,0,0,0,0,0,NULL,NULL),(10010,'张华','m','654njdn@gmail.com','123456',0,'2013-04-10 10:49:50',NULL,0,0,0,0,0,NULL,NULL),(10011,'青花器','m','njdsnjkdfsk@gmail.com','152369741',0,'2013-04-10 11:20:31',NULL,0,0,0,0,0,NULL,NULL),(10012,'利好','f','njndxjjjjj@gmail.com','7541258964',0,'2013-04-12 15:09:33',NULL,0,0,0,0,0,NULL,NULL),(10013,'王柳','m','jknknskdn@gmail.com','123456',0,'2013-04-15 15:20:07',NULL,0,0,0,0,0,NULL,NULL),(10014,'王琦jinang','m','njnddsdfs@gmail.com','15203369780',0,'2013-04-16 00:00:00',NULL,0,0,0,0,0,NULL,NULL),(10015,'王柳','f','njndskdfsdjsk@gmail.com','789456120321',0,'2013-04-16 00:00:00',NULL,0,0,0,0,0,NULL,NULL),(10016,'指望刘','m','nkmksfsl@gmail.com','125436987',0,'2013-04-19 11:54:39',NULL,0,0,0,0,0,NULL,NULL),(10017,'忘写了','f','njn988@qq.com','789102345',0,'2013-04-25 17:26:39',NULL,0,0,0,0,0,NULL,NULL);
/*!40000 ALTER TABLE `casinogod_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_userdevice`
--

DROP TABLE IF EXISTS `casinogod_userdevice`;
CREATE TABLE `casinogod_userdevice` (
  `userId` int(50) NOT NULL,
  `deviceToken` varchar(224) NOT NULL,
  `registerTime` datetime default NULL,
  `notes` text,
  PRIMARY KEY  (`userId`,`deviceToken`),
  CONSTRAINT `casinoGod_userDevice_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `casinogod_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_userdevice`
--

LOCK TABLES `casinogod_userdevice` WRITE;
/*!40000 ALTER TABLE `casinogod_userdevice` DISABLE KEYS */;
INSERT INTO `casinogod_userdevice` VALUES (10000,'1bd8ace569e7aad2c36b4ebbc5f891cb43b11b5df8fc82d7fbc28dca70a4ff98','2013-05-02 16:56:20','特提斯'),
(10001,'f851adc6693fa02238f1acf59ec35e36cec5041d099bf8efa3aeec1f317a5423','2013-05-03 14:20:54','xiaoming'),(10006,'1bd8ace569e7aad2c36b4ebbc5f891cb43b11b5cf8fc82d7fbc28dca70a4ff98','2013-05-03 15:46:19','1006'),(10007,'2cd8ace569e7aad2c36b4ebbc5f891cb43b11b5df8fc82d7fbc28dca70a4ff98','2013-05-03 15:47:12','10007');
/*!40000 ALTER TABLE `casinogod_userdevice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_useritem`
--

DROP TABLE IF EXISTS `casinogod_useritem`;
CREATE TABLE `casinogod_useritem` (
  `id` int(10) NOT NULL auto_increment,
  `itemId` int(10) NOT NULL,
  `userId` int(50) NOT NULL,
  `itemName` varchar(225) NOT NULL,
  `itemNum` int(10) NOT NULL,
  `gameType` int(10) NOT NULL,
  `comment` text,
  PRIMARY KEY  (`id`),
  KEY `casinoGod_userItem_ibfk_1` (`itemId`),
  KEY `casinoGod_userItem_ibfk_2` (`userId`),
  KEY `userItem_id` (`id`),
  CONSTRAINT `casinoGod_userItem_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `casinogod_item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `casinoGod_userItem_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `casinogod_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_useritem`
--

LOCK TABLES `casinogod_useritem` WRITE;
/*!40000 ALTER TABLE `casinogod_useritem` DISABLE KEYS */;
INSERT INTO `casinogod_useritem` VALUES (3,1,10000,'itemtest',2,0,'test'),(4,3,10000,'Super Spring',5,0,'test'),(5,4,10000,'Video Poker',13,2,'test'),(6,3,10001,'Super Spring',5,0,'test'),(7,1,10001,'itemtest',4,0,'test'),(8,2,10000,'Golden',2,0,'test'),(9,4,10000,'Video+Poker',1,2,'test'),(10,2,10001,'Golden Eyes',1,0,'test'),(11,4,10001,'Video Poker',1,2,'test'),(12,4,10007,'Video Poker',1,2,'test');
/*!40000 ALTER TABLE `casinogod_useritem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-03 11:21:04
