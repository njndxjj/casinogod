-- Host: localhost    Database: casinogod
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
-- Table structure for table `casinoGod_account`
--

DROP TABLE IF EXISTS `casinoGod_Account`;
CREATE TABLE `casinoGod_Account` (
  `account` int(50) auto_increment,
  `password` varchar(225) NOT NULL,
  `freeze` int(10) default NULL,
  `userType` varchar(225) default NULL,
  `snsId` varchar(225) default NULL,
  `snsToken` varchar(225) default NULL,
  PRIMARY KEY  (`account`),
  CONSTRAINT `casinoGod_account_ibfk_1` FOREIGN KEY (`account`) REFERENCES `casinogod_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_account`
--

LOCK TABLES `casinoGod_account` WRITE;
/*!40000 ALTER TABLE `casinogod_account` DISABLE KEYS */;
INSERT INTO `casinoGod_account` VALUES (10000,'123456',0),(10001,'123456',0),(10006,'12345879',0),(10007,'123456',1),(10008,'123456',NULL);
/*!40000 ALTER TABLE `casinogod_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_battlehistory`
--

DROP TABLE IF EXISTS `casinoGod_BattleHistory`;
CREATE TABLE `casinoGod_BattleHistory` (
  `id` int(50) NOT NULL auto_increment,
  `battleId` int(50)  NOT NULL,
  `owenId` int(10) NOT NULL,
  `userList` varchar(50) default NULL,
  `battleStatue` int(5) default NULL,
  `battleType` int(5) default NULL,
  `createTime` datetime default NULL,
  `result` text,
  `otherNotes` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casinogod_battlehistory`
--

LOCK TABLES `casinoGod_battlehistory` WRITE;
/*!40000 ALTER TABLE `casinogod_battlehistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `casinogod_battlehistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casinogod_user`
--

DROP TABLE IF EXISTS `casinoGod_User`;
CREATE TABLE `casinoGod_User` (
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
  PRIMARY KEY  (`userId`),
  UNIQUE KEY `nickName` (`nickName`,`emailAddress`,`telephone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index user_id on casinoGod_user;
--
-- Dumping data for table `casinogod_user`
--

LOCK TABLES `casinoGod_user` WRITE;
INSERT INTO `casinoGod_user` VALUES (10000,'testqa','man','njnd@gmail.com','15421877',0,'2013-03-30 20:21:24',NULL,0,0,0,0,0,NULL),(10001,'鐎殿喚濮崇粭锟�'m','njndxjjj@gmail.com','456123',0,'2013-03-30 00:00:00',NULL,0,0,0,0,0,NULL),(10006,'濡澘妫旂粭姗�礃閿燂拷'm','njidjkejk@gmail.com','180045271',0,'2013-03-30 17:19:26',NULL,0,0,0,0,0,NULL),(10007,'闁告帗顭堢�锟�'m','kldjksjfk@gmail.com','187452003',0,'2013-04-01 10:32:45',NULL,0,0,0,0,0,NULL),(10008,'缂傚啯鍨圭划锟�,'m','njnd@gmail.com','456123789',0,'2013-04-02 14:59:47',NULL,0,0,0,0,0,NULL);


DROP TABLE IF EXISTS `casinoGod_RoomInfo`;
CREATE TABLE `casinoGod_RoomInfo` (
  `id` int(10) NOT NULL auto_increment,
  `gameType` int(10) NOT NULL,
  `roomLevel` varchar(225) NOT NULL,
  `levelScore` int(50),
  `minHand` int(50),
  `maxHand` int (50),
  `otherNotes` text,
   beginTime datetime,
   endTime datetime,
   PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_Item`;
CREATE TABLE `casinoGod_Item` (
  `id` int(10) NOT NULL auto_increment,
  `gameType` int(10) NOT NULL,
  `itemName` varchar(225) NOT NULL,
  `itemPrice` int(10) NOT NULL,
  `comment` text,
   PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_UserItem`;
CREATE TABLE `casinoGod_UserItem` (
  `id` int(10) NOT NULL auto_increment,
  `itemId` int(10) NOT NULL,
  `userId` int(50) NOT NULL,
  `itemName` varchar(225) NOT NULL,
  `itemNum` int(10) NOT NULL,
  `gameType` int(10) NOT NULL,
  `comment` text,
   PRIMARY KEY  (`id`),
   CONSTRAINT `casinoGod_userItem_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `casinoGod_item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `casinoGod_userItem_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `casinogod_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
   
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_ItemHistory`;
CREATE TABLE `casinoGod_ItemHistory` (
  `id` int(10) NOT NULL auto_increment,
  `gameType` int(10) NOT NULL,
  `owenId` int(50) NOT NULL,
  `purchaseType` int(10) NOT NULL,
  `itemName` varchar(225) NOT NULL,
  `itemNum` int(10) NOT NULL,
  `giftUserId`  int(50),
  `purchaseTime` datetime,
   `otherNotes` text,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_LogInReward`;
CREATE TABLE `casinoGod_LogInReward` (
  `id` int(10) NOT NULL auto_increment,
  `userId` int(50) NOT NULL,
  `logInTime` datetime,
  `lastTimes` int(10) NOT NULL,
   primary key(id),
   CONSTRAINT `casinoGod_logInReward_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `casinoGod_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_LogInRewardConfig`;
CREATE TABLE `casinoGod_LogInRewardConfig` (
  `id` int(10) NOT NULL auto_increment,
  `day` int(10) NOT NULL,
  `reward` int(10) NOT NULL,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_UserLottery`;
CREATE TABLE `casinoGod_UserLottery` (
  `userId` int(50) NOT NULL,
  `lotteryId` int(20) NOT NULL,
  `lotteryType` int(20),
  `lotteryValue` varchar(20),
  `betAmount` int (20),
  `num` int (10),
  `result` bit(1),
  `betDateTime` datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_FriendRequest`;
CREATE TABLE `casinoGod_FriendRequest` (
  `id` int(10) NOT NULL auto_increment,
  `owenId` int(50) NOT NULL,
  `userId` int(50) NOT NULL,
  `requestDate` datetime,
  `statue` int(10),
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_BoardCastInfo`;
CREATE TABLE `casinoGod_BoardCastInfo` (
  `id` int(10) NOT NULL auto_increment,
  `title` text NOT NULL,
  `content` text ,
  `startDate` datetime,
   `endDate` datetime,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_UserDevice`;
CREATE TABLE `casinoGod_UserDevice` (
  `userId` int(50) NOT NULL,
  `deviceToken` varchar(224) NOT NULL,
  `registerTime` datetime ,
  `notes` text,
   primary key(`userId`,`deviceToken`),
    CONSTRAINT `casinoGod_userDevice_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `casinoGod_User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_LotteryHistory`;
CREATE TABLE `casinoGod_LotteryHistory` (
  `lotteryId` int(10) NOT NULL auto_increment,
  `lotteryType` text NOT NULL,
  `openDateTime` datetime ,
  `result` text,
   primary key(lotteryId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=134562;

DROP TABLE IF EXISTS `casinoGod_UserBattleHistory`;
CREATE TABLE `casinoGod_UserBattleHistory` (
  `id` int(10) NOT NULL auto_increment,
  `userId` int(20) NOT NULL ,
  `gameType` varchar(20) NOT NULL,
  `winTimes` int (10) NOT NULL,
  `lostTime` int (10) NOT NULL,
  `winMoney` int (10) NOT NULL,
  `lostMoney` int (10) NOT NULL,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_Production`;
CREATE TABLE `casinoGod_Production` (
  `id` int(10) NOT NULL auto_increment,
  `productId` varchar(20) NOT NULL ,
  `product` varchar(20) NOT NULL,
  `diamond` int (10) NOT NULL,
  `unit` int (10) NOT NULL,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_authToken`;
CREATE TABLE `casinoGod_authToken` (
  `id` int(10) NOT NULL auto_increment,
  `userId` int(30) NOT NULL ,
  `authTOken` varchar(200) NOT NULL,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_bossConfig`;
CREATE TABLE `casinoGod_bossConfig` (
  `bossId` int(10) NOT NULL auto_increment,
  `maxHP` int(50) NOT NULL ,
   primary key(bossId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_bossBattleInfo`;
CREATE TABLE `casinoGod_bossBattleInfo` (
  `id` int(10) NOT NULL auto_increment,
  `bossType` int(10) NOT NULL,
  `bossInstance` int(10) NOT NULL,
  `maxHP` int(50) NOT NULL ,
  `battleId` varchar(50) ,
  `startTime` varchar(50) NOT NULL ,
  `statue` varchar(50) NOT NULL ,
  `battleTime` int(50) NOT NULL ,
  `currentHP` int(50) NOT NULL ,
   primary key(id,bossInstance,battleId,bossType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_bossBattleInfo`;
CREATE TABLE `casinoGod_bossBattleInfo` (
  `bossType` int(10) NOT NULL,
  `bossInstance` int(10) NOT NULL auto_increment,
  `maxHP` int(50) NOT NULL ,
  `battleId` varchar(50) ,
  `startTime` varchar(50) NOT NULL ,
  `statue` varchar(50) NOT NULL ,
  `battleTime` int(50) NOT NULL ,
  `currentHP` int(50) NOT NULL ,
   primary key(bossInstance,battleId,bossType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_bossUserInfo`;
CREATE TABLE `casinoGod_bossUserInfo` (
  `id` int(10) NOT NULL auto_increment,
  `bossType` int(10) NOT NULL,
  `bossInstance` int(10) NOT NULL,
  `userId` int(50) NOT NULL ,
  `result` int(20) ,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_iapLog`;
CREATE TABLE `casinoGod_iapLog` (
  `id` int(10) NOT NULL auto_increment,
  `userId` int(30) NOT NULL,
  `productId` varchar(50) NOT NULL,
  `quantity` int(20) NOT NULL ,
  `money` int(20) NOT NULL,
  `statue` int(10) NOT NULL,
  `purchaseTime` varchar(50) NOT NULL,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_eventConfig`;
CREATE TABLE `casinoGod_eventConfig` (
  `eventId` int(10) NOT NULL auto_increment,
  `eventType` int(10) NOT NULL,
  `startTime` varchar(50) NOT NULL,
  `endTime` varchar(50) NOT NULL,
  `description` text ,
  `title` varchar(200) NOT NULL,
  `imageUrl` varchar(100) NOT NULL,
  `frequency` int(10) NOT NULL,
   `detailData` text,
   primary key(eventId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_rankType`;
CREATE TABLE `casinoGod_rankType` (
  `typeId` int(10) NOT NULL auto_increment,
  `rankName` int(10) NOT NULL,
  `rankDescription` text NOT NULL,
  `startTime` varchar(50) NOT NULL,
  `endTime` varchar(50) NOT NULL ,
   primary key(typeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `casinoGod_userRankInfo`;
CREATE TABLE `casinoGod_userRankInfo` (
  `userId` int(10) NOT NULL,
  `typeId` int(10) NOT NULL,
  `rankValue` int(40) NOT NULL,
  `updateTime` varchar(50) NOT NULL ,
  `winTime` int(10) NOT NULL,
  `loseTime` int(10) NOT NULL,
  `drawTime` int(10) NOT NULL,
   primary key(typeId,userId,updateTime)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_userBattleResult`;
CREATE TABLE `casinoGod_userBattleResult` (
  `userId` int(10) NOT NULL,
  `gameType` int(10) NOT NULL,
  `battleType` int(40) NOT NULL,
  `winTotal` int(40) NOT NULL ,
  `loseTotal` int(40) NOT NULL ,
  `totalResult` int(40) NOT NULL ,
   primary key(userId,gameType,battleType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_chatRoom`;
CREATE TABLE `casinoGod_chatRoom` (
  `id` int(10) NOT NULL auto_increment,
  `userId` int(60) NOT NULL,
  `content` text NOT NULL,
  `updateTime` varchar(50) NOT NULL ,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_chatLimit`;
CREATE TABLE `casinoGod_chatLimit` (
  `userId` int(60) NOT NULL,
  `startTime` varchar(50) NOT NULL,
  `endTime` varchar(50) NOT NULL ,
   primary key(userId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_friendInvite`;
CREATE TABLE `casinoGod_friendInvite` (
  `userId` int(60) NOT NULL,
  `inviteCode` varchar(50) NOT NULL,
  `createTime` varchar(50) NOT NULL ,
  `friendCount` int ,
   primary key(userId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_inviteTable`;
CREATE TABLE `casinoGod_inviteTable` (
  `userId` int(60) NOT NULL,
  `invitedId` int(50) NOT NULL,
  `inviteTime` varchar(50) NOT NULL ,
   primary key(invitedId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `casinoGod_task`;
CREATE TABLE `casinoGod_task` (
  `taskId` int(10) NOT NULL auto_increment,
  `taskType` int(50) NOT NULL,
  `taskRate` int(50) NOT NULL,
  `itemId` int(50) NOT NULL ,
  `itemNum` int(50) NOT NULL ,
  `status` int(50) NOT NULL ,
  `isDaily` int(50) NOT NULL ,
  `description` varchar(50) NOT NULL, 
   primary key(taskId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_userTask`;
CREATE TABLE `casinoGod_userTask` (
  `id` int(10) NOT NULL auto_increment,
  `userId` int(50) NOT NULL,
  `taskId` int(50) NOT NULL,
  `status` int(50) NOT NULL ,
  `updateTime` varchar(50) NOT NULL ,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `casinoGod_configuration`;
CREATE TABLE `casinoGod_configuration` (
`id` int(10) NOT NULL auto_increment,
  `winTimes` int(5) NOT NULL,
  `rankSize` int(5) NOT NULL,
   primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


bossconfig
boss:
id , maxgold, battleId, userId,startTime,timesamp,currentGold

bossbattlHistory
battleId bossId,userId,rust

shareBoss
getBossInfo

firsttime, firstEnd