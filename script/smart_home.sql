# Host: 127.0.0.1  (Version: 5.1.70-community)
# Date: 2015-03-05 06:44:18
# Generator: MySQL-Front 5.3  (Build 4.191)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "advertisement"
#

DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `AD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AD_NAME` varchar(255) DEFAULT NULL,
  `AD_INFO` varchar(255) DEFAULT NULL,
  `AD_URL` varchar(255) DEFAULT NULL,
  `AD_IMG` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "advertisement"
#


#
# Structure for table "alarm"
#

DROP TABLE IF EXISTS `alarm`;
CREATE TABLE `alarm` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONCENTRATOR_ID` bigint(20) DEFAULT NULL,
  `OBJ_TYPE` int(11) DEFAULT NULL,
  `OBJ_ID` bigint(20) DEFAULT NULL,
  `ALARM_TYPE` int(11) DEFAULT NULL,
  `DATA_VALUE` float DEFAULT NULL,
  `ALARM_TIME` datetime DEFAULT NULL,
  `CLEAR_USER` varchar(255) DEFAULT NULL,
  `CLEAR_STATUS` int(11) DEFAULT NULL,
  `CLEAR_TIME` datetime DEFAULT NULL,
  `OBJ_ID1` int(11) NOT NULL DEFAULT '0',
  `OBJ_ID2` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

#
# Data for table "alarm"
#

INSERT INTO `alarm` VALUES (1,3000000008,1,4,10,NULL,'2015-02-06 10:21:20',NULL,0,NULL,1,0),(2,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(3,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(4,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(5,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(6,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(7,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(8,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(9,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(10,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(11,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(12,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(13,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0),(14,3000000006,1,4,10,NULL,'2015-02-06 10:21:20',NULL,1,NULL,1,0);

#
# Structure for table "alarm_type"
#

DROP TABLE IF EXISTS `alarm_type`;
CREATE TABLE `alarm_type` (
  `TYPE_ID` int(11) NOT NULL,
  `TYPE_NAME` varchar(255) DEFAULT NULL,
  `KIND` int(11) DEFAULT NULL,
  `IS_PUSH` int(11) DEFAULT NULL,
  `PUSH_TYPE` int(11) DEFAULT NULL,
  `IS_FEEDBACK` int(11) DEFAULT NULL,
  `FEEDBACK_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "alarm_type"
#


#
# Structure for table "building"
#

DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
  `BUILDING_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESP` varchar(255) DEFAULT NULL,
  `ADDR` varchar(255) DEFAULT NULL,
  `PIC_PATH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BUILDING_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "building"
#

INSERT INTO `building` VALUES (3,3,'啊啊啊','1','啊啊',NULL);

#
# Structure for table "check_item"
#

DROP TABLE IF EXISTS `check_item`;
CREATE TABLE `check_item` (
  `item_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_NAME` varchar(255) DEFAULT NULL,
  `ITEM_SYS` varchar(255) DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "check_item"
#


#
# Structure for table "check_log"
#

DROP TABLE IF EXISTS `check_log`;
CREATE TABLE `check_log` (
  `LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `CHECK_ITEM` varchar(255) DEFAULT NULL,
  `CHECK_SYS` varchar(255) DEFAULT NULL,
  `CHECK_RESULT` int(11) DEFAULT NULL,
  `ABNORMAL_DESP` varchar(255) DEFAULT NULL,
  `ABNORMAL_PIC` varchar(255) DEFAULT NULL,
  `CHECKER` varchar(255) DEFAULT NULL,
  `CHECK_TIME` datetime DEFAULT NULL,
  `HANDLER` varchar(255) DEFAULT NULL,
  `HANDLE_RESULT` varchar(255) DEFAULT NULL,
  `HANDLE_TIME` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "check_log"
#


#
# Structure for table "client_version"
#

DROP TABLE IF EXISTS `client_version`;
CREATE TABLE `client_version` (
  `VERSION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `APP_NAME` varchar(255) DEFAULT NULL,
  `APK_NAME` varchar(255) DEFAULT NULL,
  `VERSION_NAME` varchar(255) DEFAULT NULL,
  `VERSION_CODE` int(11) DEFAULT NULL,
  `CLIENT_TYPE` int(11) DEFAULT NULL,
  `APK_URL` varchar(255) DEFAULT NULL,
  `QR_CODE` varchar(255) DEFAULT NULL,
  `VERSION_STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`VERSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "client_version"
#

INSERT INTO `client_version` VALUES (1,1,'云安防','SmartHome.apk','1.0.1',2,3,'/client/resource/appDownload/SmartHome.apk',NULL,1);

#
# Structure for table "company"
#

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `COMPANY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(255) DEFAULT NULL,
  `APPLY_NAME` varchar(255) DEFAULT NULL,
  `COMPANY_ADDR` varchar(255) DEFAULT NULL,
  `COMPANY_TYPE` varchar(255) DEFAULT NULL,
  `COMPANY_PHONE` varchar(255) DEFAULT NULL,
  `BUILDING_AREA` float DEFAULT NULL,
  `LEGAL_OFFICER` varchar(255) DEFAULT NULL,
  `FIRE_MANAGER` varchar(255) DEFAULT NULL,
  `MANAGER_PHONE` varchar(255) DEFAULT NULL,
  `FIRE_DUTY` varchar(255) DEFAULT NULL,
  `DUTY_PHONE` varchar(255) DEFAULT NULL,
  `EXTEND_INFO` varchar(255) DEFAULT NULL,
  `FIRE_RISK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "company"
#

INSERT INTO `company` VALUES (3,'撒发生的','阿斯顿发生','撒发生的',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'阿凡达','地方','的的',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'圣达菲','是否','',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'达到','的','',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'地方','圣达菲','',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "concentrator"
#

DROP TABLE IF EXISTS `concentrator`;
CREATE TABLE `concentrator` (
  `CONCENTRATOR_ID` bigint(20) NOT NULL,
  `IP_ADDR` varchar(255) NOT NULL,
  `PORT` int(11) NOT NULL,
  `STATUS` int(11) NOT NULL,
  `LOCATION_NS` float DEFAULT NULL,
  `LOCATION_WE` float DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ADDR` varchar(255) DEFAULT NULL,
  `MARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CONCENTRATOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "concentrator"
#

INSERT INTO `concentrator` VALUES (3000000001,'115.228.172.192',1238,0,22.6446,114.021,NULL,NULL,NULL,NULL),(3000000002,'183.140.40.76',2264,0,22.6446,114.021,NULL,NULL,NULL,NULL),(3000000003,'115.228.174.110',8600,0,22.6446,114.021,NULL,NULL,NULL,NULL),(3000000004,'115.228.161.203',8600,0,22.6446,114.021,NULL,NULL,NULL,NULL),(3000000005,'115.228.161.203',8600,0,22.6446,114.021,NULL,NULL,NULL,NULL),(3000000006,'163.125.209.106',1320,1,22.6446,114.021,NULL,NULL,NULL,NULL),(3000000007,'115.228.161.203',8600,0,22.6446,114.021,NULL,NULL,NULL,NULL),(3000000008,'115.228.161.203',8600,0,22.6446,114.021,NULL,NULL,NULL,NULL),(3000000009,'183.141.144.134',8600,0,22.6446,114.021,NULL,NULL,NULL,NULL);

#
# Structure for table "customer"
#

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `USER_ID` int(11) NOT NULL,
  `CUSTOMER_NAME` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `ADDR` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "customer"
#

INSERT INTO `customer` VALUES (2,'test1','','','133@163.com',0),(3,'test2','18668399921','','',0),(4,'fuego',NULL,NULL,NULL,0);

#
# Structure for table "fire_sensor"
#

DROP TABLE IF EXISTS `fire_sensor`;
CREATE TABLE `fire_sensor` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONCENTRATOR_ID` bigint(20) DEFAULT NULL,
  `MACHINE_ID` int(11) DEFAULT NULL,
  `LOOP_ID` int(11) DEFAULT NULL,
  `CODE_ID` int(11) DEFAULT NULL,
  `PLAN_NODE_ID` int(11) DEFAULT NULL,
  `LOCATION_DESP` varchar(255) DEFAULT NULL,
  `LOCATION_X` float DEFAULT NULL,
  `LOCATION_Y` float DEFAULT NULL,
  `SENSOR_TYPE` int(11) DEFAULT NULL,
  `SENSOR_TYPE_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

#
# Data for table "fire_sensor"
#

INSERT INTO `fire_sensor` VALUES (1,1,1,1,1,1,'1',153,97,NULL,NULL),(2,1,2,1,2,1,'23',78,28,NULL,NULL),(3,1111,11,11,11,1,'订单',80,80,7,'电气火灾监控设备'),(4,77,7,77,77,1,'77',0,0,4,'剩余电流式电气火灾监控探测器'),(5,111,333,33,33,0,'33',0,0,10,'防火阀'),(6,55,0,0,0,0,'',0,0,0,'未命名'),(7,33,4,0,0,0,'',0,0,0,'未命名'),(8,3333,0,0,0,0,'',0,0,0,'未命名'),(9,333,0,0,0,0,'',0,0,0,'未命名'),(10,33333,0,0,0,0,'',0,0,0,'未命名'),(11,44444,0,0,0,0,'',0,0,0,'未命名');

#
# Structure for table "home_sensor"
#

DROP TABLE IF EXISTS `home_sensor`;
CREATE TABLE `home_sensor` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONCENTRATOR_ID` bigint(20) DEFAULT NULL,
  `SENSOR_ID` bigint(20) DEFAULT NULL,
  `CHANNEL_ID` int(11) DEFAULT NULL,
  `SENSOR_KIND` int(11) DEFAULT NULL,
  `SENSOR_TYPE` int(11) DEFAULT NULL,
  `SENSOR_TYPE_NAME` varchar(255) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `WARN_VALUE` float DEFAULT NULL,
  `ERROR_VALUE` float DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  `CTL_GROUP_ID` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `MARK` varchar(255) DEFAULT NULL,
  `CTR_SENSOR_ID` bigint(20) DEFAULT NULL,
  `CTR_CHANNEL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1137 DEFAULT CHARSET=utf8;

#
# Data for table "home_sensor"
#

INSERT INTO `home_sensor` VALUES (857,3000000004,2,1,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(858,3000000004,2,3,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(859,3000000004,2,4,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(860,3000000004,2,2,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(873,3000000005,2,1,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(874,3000000005,2,2,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(875,3000000005,2,3,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(876,3000000005,2,4,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(933,3000000008,4,3,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(934,3000000008,5,3,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(935,3000000008,4,4,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(936,3000000008,5,4,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(937,3000000008,4,1,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(938,3000000008,5,1,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(939,3000000008,4,2,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(940,3000000008,5,2,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(941,3000000007,2,1,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(942,3000000007,2,4,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(943,3000000007,2,2,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(944,3000000007,2,3,0,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(1045,3000000001,2,1,0,0,'未命名',0,0,0,0,'','桌上A通道                 ','未分组',268435455,255),(1046,3000000001,1,1,0,65535,'未命名',1,0,0,255,'','烟感A通道                 ','未分组',4294967295,255),(1047,3000000001,1,4,0,65535,'未命名',1,0,0,255,'','烟感测试按钮              ','未分组',4294967295,255),(1048,3000000001,2,4,0,65535,'未命名',1,0,0,255,'','桌上测试按钮              ','未分组',4294967295,255),(1049,3000000001,2000000001,3,2,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(1050,3000000001,2000000001,2,2,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(1051,3000000001,2000000001,4,2,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(1052,3000000001,2000000001,1,2,65535,'未命名',1,0,0,255,'','�������������','未分组',4294967295,255),(1053,3000000001,1,2,0,0,'未命名',0,0,0,0,'','烟感B通道                 ','未分组',268435455,255),(1054,3000000001,2,3,0,65535,'未命名',1,0,0,255,'','桌上C通道                 ','未分组',4294967295,255),(1055,3000000001,2,2,0,65535,'未命名',1,0,0,255,'','桌上B通道                 ','未分组',4294967295,255),(1056,3000000001,1,3,0,65535,'未命名',1,0,0,255,'','烟感C通道                 ','未分组',4294967295,255),(1105,3000000009,4,3,0,65535,'未命名',1,0,0,255,'','4号终端C口                ','未分组',4294967295,255),(1106,3000000009,5,3,0,65535,'未命名',1,0,0,255,'','5号终端C口                ','未分组',4294967295,255),(1107,3000000009,5,4,0,0,'未命名',1,0,0,0,'','null                      ','未分组',268435455,255),(1108,3000000009,4,4,0,65535,'未命名',1,0,0,255,'','4号终端测试按钮           ','未分组',4294967295,255),(1109,3000000009,5,1,0,65535,'未命名',1,0,0,255,'','5号终端A口                ','未分组',4294967295,255),(1110,3000000009,5,2,0,65535,'未命名',1,0,0,255,'','5号终端B口                ','未分组',4294967295,255),(1111,3000000009,4,2,0,0,'未命名',0,0,0,0,NULL,NULL,'未分组',268435455,65535),(1112,3000000009,4,1,0,65535,'未命名',1,0,0,255,'','4号终端A口                ','未分组',4294967295,255),(1129,3000000006,2000000003,2,2,7,'电气火灾监控设备',0,0,0,255,'','公司火灾控制              ','未分组',4294967295,255),(1130,3000000006,2000000003,4,2,8,'通用',1,0,0,255,'','出面膜                    ','未分组',4294967295,255),(1131,3000000006,2000000003,3,2,11,'防火卷帘控制器',1,0,0,255,'','大门','未分组',4294967295,255),(1132,3000000006,3,1,0,65535,'未命名',0,0,0,255,'','11','未分组',4294967295,255),(1133,3000000006,3,2,0,0,'未命名',0,0,0,0,'','11                        ','未分组',268435455,255),(1134,3000000006,3,4,0,65535,'未命名',1,0,0,255,'','呜呜','未分组',4294967295,255),(1135,3000000006,3,3,0,65535,'未命名',1,0,0,255,'','111','未分组',4294967295,255),(1136,3000000006,2000000003,1,2,65535,'未命名',1,0,0,255,'','呵呵','未分组',4294967295,255);

#
# Structure for table "knowledge"
#

DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `KNOWLEDGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` text,
  `KNOWLEDGE_TYPE` int(11) DEFAULT NULL,
  `KNOWLEDGE_KIND` int(11) DEFAULT NULL,
  PRIMARY KEY (`KNOWLEDGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "knowledge"
#


#
# Structure for table "misp_oper_log"
#

DROP TABLE IF EXISTS `misp_oper_log`;
CREATE TABLE `misp_oper_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USER` varchar(255) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `OBJECT` varchar(255) DEFAULT NULL,
  `RESULT` varchar(255) DEFAULT NULL,
  `OPER_DESP` varchar(255) DEFAULT NULL,
  `OPER_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=540 DEFAULT CHARSET=utf8;

#
# Data for table "misp_oper_log"
#

INSERT INTO `misp_oper_log` VALUES (1,'admin','登录',NULL,'成功',NULL,'2015-01-24 17:38:05'),(2,'test1','登录',NULL,'成功',NULL,'2015-01-24 17:38:56'),(3,'test1','登录',NULL,'成功',NULL,'2015-01-25 14:46:27'),(4,'admin','登录',NULL,'成功',NULL,'2015-01-25 14:47:50'),(5,'test1','登录',NULL,'成功',NULL,'2015-01-25 14:48:52'),(6,'test1','登录',NULL,'成功',NULL,'2015-01-25 16:51:54'),(7,'test1','登录',NULL,'成功',NULL,'2015-01-25 18:16:22'),(8,'test1','登录',NULL,'成功',NULL,'2015-01-25 18:19:12'),(9,'test1','登录',NULL,'成功',NULL,'2015-01-25 18:28:26'),(10,'test1','登录',NULL,'成功',NULL,'2015-01-25 18:36:09'),(11,'test1','登录',NULL,'成功',NULL,'2015-01-25 18:38:36'),(12,'test1','登录',NULL,'成功',NULL,'2015-01-25 18:45:25'),(13,'test1','登录',NULL,'成功',NULL,'2015-01-25 19:01:52'),(14,'test1','登录',NULL,'成功',NULL,'2015-01-25 19:03:13'),(15,'test1','登录',NULL,'成功',NULL,'2015-01-25 19:05:08'),(16,'admin','登录',NULL,'成功',NULL,'2015-01-26 09:11:53'),(17,'test1','登录',NULL,'成功',NULL,'2015-01-26 10:30:25'),(18,'test1','登录',NULL,'成功',NULL,'2015-01-26 10:33:31'),(19,'test1','登录',NULL,'成功',NULL,'2015-01-26 10:33:52'),(20,'test1','登录',NULL,'成功',NULL,'2015-01-26 10:37:51'),(21,'test1','登录',NULL,'成功',NULL,'2015-01-26 10:38:10'),(22,'test1','登录',NULL,'成功',NULL,'2015-01-26 10:42:14'),(23,'test1','登录',NULL,'成功',NULL,'2015-01-26 10:42:43'),(24,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 10:43:27'),(25,'test1','登录',NULL,'成功',NULL,'2015-01-26 11:18:41'),(26,'test1','登录',NULL,'成功',NULL,'2015-01-26 11:22:34'),(27,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 11:23:47'),(28,'test1','登录',NULL,'成功',NULL,'2015-01-26 11:40:11'),(29,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 11:40:27'),(30,'test1','登录',NULL,'成功',NULL,'2015-01-26 11:45:19'),(31,'test1','登录',NULL,'成功',NULL,'2015-01-26 11:46:48'),(32,'test1','登录',NULL,'成功',NULL,'2015-01-26 11:47:00'),(33,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 11:47:33'),(34,'test1','登录',NULL,'成功',NULL,'2015-01-26 11:49:42'),(35,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 11:50:01'),(36,'test1','登录',NULL,'成功',NULL,'2015-01-26 11:51:45'),(37,'test1','登录',NULL,'成功',NULL,'2015-01-26 11:52:51'),(38,'test1','登录',NULL,'成功',NULL,'2015-01-26 11:57:12'),(39,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 11:57:39'),(40,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:08:20'),(41,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 12:08:44'),(42,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:10:50'),(43,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:10:55'),(44,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:11:14'),(45,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:13:06'),(46,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 12:13:41'),(47,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:15:28'),(48,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 12:15:41'),(49,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:19:07'),(50,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 12:19:21'),(51,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:20:43'),(52,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 12:21:08'),(53,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:29:45'),(54,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 12:30:05'),(55,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:32:57'),(56,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:33:10'),(57,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:34:50'),(58,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 12:35:25'),(59,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:35:47'),(60,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 12:36:10'),(61,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:53:23'),(62,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:54:13'),(63,'test1','登录',NULL,'成功',NULL,'2015-01-26 12:54:28'),(64,'test1','登录',NULL,'成功',NULL,'2015-01-26 13:22:19'),(65,'admin','登录',NULL,'成功',NULL,'2015-01-26 13:24:13'),(66,'test1','登录',NULL,'成功',NULL,'2015-01-26 14:11:55'),(67,'admin','登录',NULL,'成功',NULL,'2015-01-26 14:12:28'),(68,'test1','登录',NULL,'成功',NULL,'2015-01-26 14:29:20'),(69,'admin','登录',NULL,'成功',NULL,'2015-01-26 14:41:13'),(70,'admin','登录',NULL,'成功',NULL,'2015-01-26 15:03:58'),(71,'admin','登录',NULL,'成功',NULL,'2015-01-26 15:04:37'),(72,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:23:12'),(73,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:29:09'),(74,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:29:26'),(75,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:39:37'),(76,'admin','登录',NULL,'成功',NULL,'2015-01-26 15:41:31'),(77,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:44:37'),(78,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:44:51'),(79,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:49:04'),(80,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:52:41'),(81,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:54:18'),(82,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:56:51'),(83,'test1','登录',NULL,'成功',NULL,'2015-01-26 15:59:46'),(84,'test1','登录',NULL,'成功',NULL,'2015-01-26 16:05:14'),(85,'test1','登录',NULL,'成功',NULL,'2015-01-26 16:07:56'),(86,'admin','登录',NULL,'成功',NULL,'2015-01-26 16:23:54'),(87,'test1','登录',NULL,'成功',NULL,'2015-01-26 16:24:20'),(88,'admin','登录',NULL,'成功',NULL,'2015-01-26 16:35:35'),(89,'admin','登录',NULL,'成功',NULL,'2015-01-26 16:45:43'),(90,'test1','登录',NULL,'成功',NULL,'2015-01-26 16:54:19'),(91,'admin','登录',NULL,'成功',NULL,'2015-01-26 16:55:15'),(92,'admin','登录',NULL,'成功',NULL,'2015-01-26 17:03:36'),(93,'test1','登录',NULL,'成功',NULL,'2015-01-26 17:04:11'),(94,'test1','修改密码',NULL,'成功',NULL,'2015-01-26 17:04:28'),(95,'test1','登录',NULL,'成功',NULL,'2015-01-26 17:04:37'),(96,'admin','登录',NULL,'成功',NULL,'2015-01-26 17:25:41'),(97,'test1','登录',NULL,'成功',NULL,'2015-01-26 17:37:53'),(98,'test1','登录',NULL,'成功',NULL,'2015-01-26 22:21:36'),(99,'test1','登录',NULL,'成功',NULL,'2015-01-26 22:25:52'),(100,'test1','登录',NULL,'成功',NULL,'2015-01-26 22:30:59'),(101,'test1','登录',NULL,'成功',NULL,'2015-01-26 22:32:22'),(102,'test1','登录',NULL,'成功',NULL,'2015-01-26 22:39:08'),(103,'test1','登录',NULL,'成功',NULL,'2015-01-26 22:51:16'),(104,'test1','登录',NULL,'成功',NULL,'2015-01-26 22:54:06'),(105,'test1','登录',NULL,'成功',NULL,'2015-01-26 22:55:54'),(106,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:00:26'),(107,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:06:58'),(108,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:12:17'),(109,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:21:46'),(110,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:23:46'),(111,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:25:14'),(112,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:31:06'),(113,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:32:13'),(114,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:34:44'),(115,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:37:23'),(116,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:43:23'),(117,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:45:22'),(118,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:46:04'),(119,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:46:21'),(120,'test1','登录',NULL,'成功',NULL,'2015-01-26 23:48:17'),(121,'test1','登录',NULL,'成功',NULL,'2015-01-27 09:19:26'),(122,'test1','登录',NULL,'成功',NULL,'2015-01-27 10:03:28'),(123,'test1','登录',NULL,'成功',NULL,'2015-01-27 10:05:25'),(124,'admin','登录',NULL,'成功',NULL,'2015-01-27 10:07:28'),(125,'admin','登录',NULL,'成功',NULL,'2015-01-27 10:13:16'),(126,'test1','登录',NULL,'成功',NULL,'2015-01-27 10:14:44'),(127,'test1','登录',NULL,'成功',NULL,'2015-01-27 10:17:43'),(128,'test1','登录',NULL,'成功',NULL,'2015-01-27 10:19:32'),(129,'admin','登录',NULL,'成功',NULL,'2015-01-27 10:34:50'),(130,'test1','登录',NULL,'成功',NULL,'2015-01-27 10:50:45'),(131,'test1','登录',NULL,'成功',NULL,'2015-01-27 10:54:13'),(132,'test1','登录',NULL,'成功',NULL,'2015-01-27 10:54:15'),(133,'test1','登录',NULL,'成功',NULL,'2015-01-27 11:05:49'),(134,'test1','登录',NULL,'成功',NULL,'2015-01-27 11:07:10'),(135,'admin','登录',NULL,'成功',NULL,'2015-01-27 11:10:11'),(136,'test1','登录',NULL,'成功',NULL,'2015-01-27 11:15:22'),(137,'test1','登录',NULL,'成功',NULL,'2015-01-27 11:16:17'),(138,'test1','登录',NULL,'成功',NULL,'2015-01-27 11:16:22'),(139,'test1','登录',NULL,'成功',NULL,'2015-01-27 11:20:03'),(140,'test1','登录',NULL,'成功',NULL,'2015-01-27 11:48:38'),(141,'test1','登录',NULL,'成功',NULL,'2015-01-27 11:49:35'),(142,'test1','登录',NULL,'成功',NULL,'2015-01-27 11:51:41'),(143,'admin','登录',NULL,'成功',NULL,'2015-01-27 12:01:36'),(144,'admin','登录',NULL,'成功',NULL,'2015-01-27 12:20:09'),(145,'test1','登录',NULL,'成功',NULL,'2015-01-27 12:24:06'),(146,'test1','登录',NULL,'成功',NULL,'2015-01-27 13:24:49'),(147,'test1','登录',NULL,'成功',NULL,'2015-01-27 13:25:17'),(148,'test1','登录',NULL,'成功',NULL,'2015-01-27 13:26:59'),(149,'test1','登录',NULL,'成功',NULL,'2015-01-27 13:27:07'),(150,'admin','登录',NULL,'成功',NULL,'2015-01-27 13:35:24'),(151,'test1','登录',NULL,'成功',NULL,'2015-01-27 14:08:25'),(152,'test1','登录',NULL,'成功',NULL,'2015-01-27 14:13:50'),(153,'test1','登录',NULL,'成功',NULL,'2015-01-27 14:15:40'),(154,'admin','登录',NULL,'成功',NULL,'2015-01-27 14:20:16'),(155,'test1','登录',NULL,'成功',NULL,'2015-01-27 14:23:04'),(156,'admin','登录',NULL,'成功',NULL,'2015-01-27 15:20:38'),(157,'admin','登录',NULL,'成功',NULL,'2015-01-27 15:37:02'),(158,'admin','登录',NULL,'成功',NULL,'2015-01-27 16:16:41'),(159,'admin','登录',NULL,'成功',NULL,'2015-01-27 16:31:23'),(160,'admin','登录',NULL,'成功',NULL,'2015-01-27 16:44:04'),(161,'admin','登录',NULL,'成功',NULL,'2015-01-27 17:56:19'),(162,'test1','登录',NULL,'成功',NULL,'2015-01-27 18:03:37'),(163,'test1','登录',NULL,'成功',NULL,'2015-01-27 18:38:01'),(164,'test1','登录',NULL,'成功',NULL,'2015-01-27 18:41:18'),(165,'admin','登录',NULL,'成功',NULL,'2015-01-27 18:43:43'),(166,'test1','登录',NULL,'成功',NULL,'2015-01-27 18:59:21'),(167,'test1','登录',NULL,'成功',NULL,'2015-01-27 19:28:59'),(168,'test1','登录',NULL,'成功',NULL,'2015-01-27 19:31:04'),(169,'test1','登录',NULL,'成功',NULL,'2015-01-27 19:32:55'),(170,'test1','登录',NULL,'成功',NULL,'2015-01-27 19:43:11'),(171,'Test1','登录',NULL,'成功',NULL,'2015-01-27 20:16:19'),(172,'test1','登录',NULL,'成功',NULL,'2015-01-27 22:44:56'),(173,'test1','登录',NULL,'成功',NULL,'2015-01-27 23:15:31'),(174,'test1','登录',NULL,'成功',NULL,'2015-01-27 23:16:30'),(175,'test1','登录',NULL,'成功',NULL,'2015-01-28 00:20:22'),(176,'test1','登录',NULL,'成功',NULL,'2015-01-28 00:36:28'),(177,'test1','登录',NULL,'成功',NULL,'2015-01-28 00:39:06'),(178,'test1','登录',NULL,'成功',NULL,'2015-01-28 00:42:10'),(179,'admin','登录',NULL,'成功',NULL,'2015-01-28 09:07:50'),(180,'test1','登录',NULL,'成功',NULL,'2015-01-28 09:19:00'),(181,'test1','登录',NULL,'成功',NULL,'2015-01-28 09:42:44'),(182,'admin','登录',NULL,'成功',NULL,'2015-01-28 09:42:44'),(183,'test1','登录',NULL,'成功',NULL,'2015-01-28 10:12:53'),(184,'test1','登录',NULL,'成功',NULL,'2015-01-28 10:33:00'),(185,'test1','登录',NULL,'成功',NULL,'2015-01-28 10:58:09'),(186,'admin','登录',NULL,'成功',NULL,'2015-01-28 11:16:11'),(187,'Test1','登录',NULL,'成功',NULL,'2015-01-28 11:16:42'),(188,'test1','登录',NULL,'成功',NULL,'2015-01-28 11:19:17'),(189,'test1','登录',NULL,'成功',NULL,'2015-01-28 11:27:36'),(190,'test1','登录',NULL,'成功',NULL,'2015-01-28 11:32:38'),(191,'test1','登录',NULL,'成功',NULL,'2015-01-28 11:42:35'),(192,'test1','登录',NULL,'成功',NULL,'2015-01-28 11:46:35'),(193,'test1','登录',NULL,'成功',NULL,'2015-01-28 11:47:37'),(194,'test1','登录',NULL,'成功',NULL,'2015-01-28 11:51:01'),(195,'test1','登录',NULL,'成功',NULL,'2015-01-28 12:04:10'),(196,'test1','登录',NULL,'成功',NULL,'2015-01-28 12:14:23'),(197,'admin','登录',NULL,'成功',NULL,'2015-01-28 12:18:10'),(198,'test1','登录',NULL,'成功',NULL,'2015-01-28 12:23:38'),(199,'admin','登录',NULL,'成功',NULL,'2015-01-28 12:54:53'),(200,'test1','登录',NULL,'成功',NULL,'2015-01-28 13:46:41'),(201,'test1','登录',NULL,'成功',NULL,'2015-01-28 14:05:02'),(202,'test1','登录',NULL,'成功',NULL,'2015-01-28 14:06:26'),(203,'test1','登录',NULL,'成功',NULL,'2015-01-28 14:14:46'),(204,'test1','登录',NULL,'成功',NULL,'2015-01-28 14:18:47'),(205,'admin','登录',NULL,'成功',NULL,'2015-01-28 14:56:00'),(206,'test1','登录',NULL,'成功',NULL,'2015-01-28 15:10:42'),(207,'test1','登录',NULL,'成功',NULL,'2015-01-28 15:11:08'),(208,'test1','登录',NULL,'成功',NULL,'2015-01-28 15:18:14'),(209,'test1','登录',NULL,'成功',NULL,'2015-01-28 15:27:07'),(210,'test1','登录',NULL,'成功',NULL,'2015-01-28 15:37:07'),(211,'test1','登录',NULL,'成功',NULL,'2015-01-28 15:40:50'),(212,'test1','登录',NULL,'成功',NULL,'2015-01-28 15:41:48'),(213,'test1','登录',NULL,'成功',NULL,'2015-01-28 16:01:44'),(214,'test1','登录',NULL,'成功',NULL,'2015-01-28 16:24:19'),(215,'test1','登录',NULL,'成功',NULL,'2015-01-28 16:27:37'),(216,'admin','登录',NULL,'成功',NULL,'2015-01-28 16:30:38'),(217,'test1','登录',NULL,'成功',NULL,'2015-01-28 16:30:50'),(218,'test1','登录',NULL,'成功',NULL,'2015-01-28 16:32:52'),(219,'admin','登录',NULL,'成功',NULL,'2015-01-28 17:00:01'),(220,'test1','登录',NULL,'成功',NULL,'2015-01-28 17:25:09'),(221,'admin','登录',NULL,'成功',NULL,'2015-01-28 17:48:20'),(222,'test1','登录',NULL,'成功',NULL,'2015-01-28 18:26:59'),(223,'test1','登录',NULL,'成功',NULL,'2015-01-28 18:56:34'),(224,'test1','登录',NULL,'成功',NULL,'2015-01-28 18:58:32'),(225,'Test1','登录',NULL,'成功',NULL,'2015-01-28 19:35:20'),(226,'test1','登录',NULL,'成功',NULL,'2015-01-28 22:22:16'),(227,'test1','登录',NULL,'成功',NULL,'2015-01-28 22:31:07'),(228,'test1','登录',NULL,'成功',NULL,'2015-01-28 22:42:14'),(229,'admin','登录',NULL,'成功',NULL,'2015-01-29 09:45:37'),(230,'test1','登录',NULL,'成功',NULL,'2015-01-29 10:11:13'),(231,'test1','登录',NULL,'成功',NULL,'2015-01-29 10:18:06'),(232,'admin','登录',NULL,'成功',NULL,'2015-01-29 10:20:48'),(233,'test1','登录',NULL,'成功',NULL,'2015-01-29 11:18:40'),(234,'admin','登录',NULL,'成功',NULL,'2015-01-29 11:28:08'),(235,'test1','登录',NULL,'成功',NULL,'2015-01-29 11:32:46'),(236,'test1','登录',NULL,'成功',NULL,'2015-01-29 11:38:03'),(237,'admin','登录',NULL,'成功',NULL,'2015-01-29 11:44:41'),(238,'test1','登录',NULL,'成功',NULL,'2015-01-29 13:34:07'),(239,'test1','登录',NULL,'成功',NULL,'2015-01-29 13:35:52'),(240,'test1','登录',NULL,'成功',NULL,'2015-01-29 13:49:27'),(241,'test1','登录',NULL,'成功',NULL,'2015-01-29 13:49:45'),(242,'test1','登录',NULL,'成功',NULL,'2015-01-29 13:57:33'),(243,'test1','登录',NULL,'成功',NULL,'2015-01-29 14:07:49'),(244,'admin','登录',NULL,'成功',NULL,'2015-01-29 14:30:14'),(245,'test1','登录',NULL,'成功',NULL,'2015-01-29 16:05:00'),(246,'admin','登录',NULL,'成功',NULL,'2015-01-29 16:29:17'),(247,'admin','登录',NULL,'成功',NULL,'2015-01-29 16:30:58'),(248,'test1','登录',NULL,'成功',NULL,'2015-01-29 16:50:03'),(249,'Test1','登录',NULL,'成功',NULL,'2015-01-29 16:50:27'),(250,'Test1','登录',NULL,'成功',NULL,'2015-01-29 16:51:19'),(251,'test1','登录',NULL,'成功',NULL,'2015-01-29 16:54:16'),(252,'test1','登录',NULL,'成功',NULL,'2015-01-29 20:56:11'),(253,'test1','登录',NULL,'成功',NULL,'2015-01-30 08:56:53'),(254,'test1','登录',NULL,'成功',NULL,'2015-01-30 09:24:45'),(255,'test1','登录',NULL,'成功',NULL,'2015-01-30 09:54:40'),(256,'test1','登录',NULL,'成功',NULL,'2015-01-30 09:57:01'),(257,'test1','登录',NULL,'成功',NULL,'2015-01-30 10:04:52'),(258,'admin','登录',NULL,'成功',NULL,'2015-01-30 10:16:27'),(259,'admin','登录',NULL,'成功',NULL,'2015-01-30 11:30:08'),(260,'admin','登录',NULL,'成功',NULL,'2015-01-30 12:00:35'),(261,'test1','登录',NULL,'成功',NULL,'2015-01-30 12:19:52'),(262,'admin','登录',NULL,'成功',NULL,'2015-01-30 12:38:20'),(263,'admin','登录',NULL,'成功',NULL,'2015-01-30 12:47:59'),(264,'test1','登录',NULL,'成功',NULL,'2015-01-30 13:25:49'),(265,'admin','登录',NULL,'成功',NULL,'2015-01-30 13:26:20'),(266,'admin','登录',NULL,'成功',NULL,'2015-01-30 13:32:45'),(267,'test1','登录',NULL,'成功',NULL,'2015-01-30 13:39:58'),(268,'admin','登录',NULL,'成功',NULL,'2015-01-30 14:16:38'),(269,'Test1','登录',NULL,'成功',NULL,'2015-01-30 14:30:25'),(270,'test1','登录',NULL,'成功',NULL,'2015-01-30 15:18:30'),(271,'admin','登录',NULL,'成功',NULL,'2015-01-30 15:19:18'),(272,'admin','登录',NULL,'成功',NULL,'2015-01-30 15:19:19'),(273,'Test1','登录',NULL,'成功',NULL,'2015-01-30 15:46:38'),(274,'test1','登录',NULL,'成功',NULL,'2015-01-30 15:47:13'),(275,'test1','登录',NULL,'成功',NULL,'2015-01-30 15:48:05'),(276,'Test1','登录',NULL,'成功',NULL,'2015-01-30 15:50:46'),(277,'test1','登录',NULL,'成功',NULL,'2015-01-30 15:51:54'),(278,'Test1','登录',NULL,'成功',NULL,'2015-01-30 15:52:45'),(279,'test1','登录',NULL,'成功',NULL,'2015-01-30 15:57:52'),(280,'admin','登录',NULL,'成功',NULL,'2015-01-30 15:58:41'),(281,'test1','登录',NULL,'成功',NULL,'2015-01-30 16:00:32'),(282,'test1','登录',NULL,'成功',NULL,'2015-01-30 16:02:48'),(283,'test1','登录',NULL,'成功',NULL,'2015-01-30 16:07:55'),(284,'test1','登录',NULL,'成功',NULL,'2015-01-30 16:13:43'),(285,'admin','登录',NULL,'成功',NULL,'2015-01-30 16:46:46'),(286,'test2','登录',NULL,'成功',NULL,'2015-01-30 16:48:10'),(287,'test2','登录',NULL,'成功',NULL,'2015-01-30 16:54:22'),(288,'test1','登录',NULL,'成功',NULL,'2015-01-30 16:57:35'),(289,'test1','登录',NULL,'成功',NULL,'2015-01-30 17:20:05'),(290,'test1','登录',NULL,'成功',NULL,'2015-01-30 17:22:23'),(291,'test1','登录',NULL,'成功',NULL,'2015-01-30 17:23:16'),(292,'admin','登录',NULL,'成功',NULL,'2015-01-30 17:33:27'),(293,'test2','登录',NULL,'成功',NULL,'2015-01-30 17:36:46'),(294,'admin','登录',NULL,'成功',NULL,'2015-01-30 18:13:41'),(295,'test2','登录',NULL,'成功',NULL,'2015-01-30 18:36:11'),(296,'admin','登录',NULL,'成功',NULL,'2015-01-31 09:40:35'),(297,'test1','登录',NULL,'成功',NULL,'2015-01-31 11:13:20'),(298,'test1','登录',NULL,'成功',NULL,'2015-01-31 11:18:04'),(299,'test1','登录',NULL,'成功',NULL,'2015-01-31 11:20:28'),(300,'test1','登录',NULL,'成功',NULL,'2015-01-31 11:23:01'),(301,'test1','登录',NULL,'成功',NULL,'2015-01-31 11:33:21'),(302,'test2','登录',NULL,'成功',NULL,'2015-01-31 11:46:21'),(303,'test2','登录',NULL,'成功',NULL,'2015-01-31 11:49:36'),(304,'test2','登录',NULL,'成功',NULL,'2015-01-31 12:22:50'),(305,'test1','登录',NULL,'成功',NULL,'2015-01-31 14:52:05'),(306,'admin','登录',NULL,'成功',NULL,'2015-01-31 14:52:23'),(307,'admin','登录',NULL,'成功',NULL,'2015-01-31 15:29:59'),(308,'test1','登录',NULL,'成功',NULL,'2015-01-31 15:30:57'),(309,'test1','登录',NULL,'成功',NULL,'2015-01-31 15:31:54'),(310,'test1','登录',NULL,'成功',NULL,'2015-01-31 16:05:36'),(311,'admin','登录',NULL,'成功',NULL,'2015-01-31 16:53:04'),(312,'test1','登录',NULL,'成功',NULL,'2015-01-31 17:03:56'),(313,'test1','登录',NULL,'成功',NULL,'2015-01-31 17:04:25'),(314,'test1','登录',NULL,'成功',NULL,'2015-01-31 17:04:57'),(315,'test2','登录',NULL,'成功',NULL,'2015-01-31 17:05:07'),(316,'test1','登录',NULL,'成功',NULL,'2015-01-31 17:06:12'),(317,'test1','登录',NULL,'成功',NULL,'2015-01-31 17:07:28'),(318,'test1','登录',NULL,'成功',NULL,'2015-01-31 17:07:53'),(319,'test1','登录',NULL,'成功',NULL,'2015-01-31 17:08:27'),(320,'test1','登录',NULL,'成功',NULL,'2015-01-31 17:08:28'),(321,'test2','登录',NULL,'成功',NULL,'2015-01-31 17:10:24'),(322,'test2','登录',NULL,'成功',NULL,'2015-01-31 17:10:47'),(323,'admin','登录',NULL,'成功',NULL,'2015-01-31 17:38:16'),(324,'test1','登录',NULL,'成功',NULL,'2015-01-31 17:45:29'),(325,'test2','登录',NULL,'成功',NULL,'2015-01-31 18:28:10'),(326,'test2','登录',NULL,'成功',NULL,'2015-01-31 18:30:36'),(327,'test2','登录',NULL,'成功',NULL,'2015-01-31 18:38:49'),(328,'test1','登录',NULL,'成功',NULL,'2015-01-31 20:09:54'),(329,'test1','登录',NULL,'成功',NULL,'2015-01-31 20:13:21'),(330,'test1','登录',NULL,'成功',NULL,'2015-01-31 20:34:42'),(331,'admin','登录',NULL,'成功',NULL,'2015-01-31 20:34:56'),(332,'test2','登录',NULL,'成功',NULL,'2015-01-31 20:38:16'),(333,'test1','登录',NULL,'成功',NULL,'2015-01-31 20:49:13'),(334,'test2','登录',NULL,'成功',NULL,'2015-01-31 20:50:21'),(335,'test1','登录',NULL,'成功',NULL,'2015-01-31 21:03:44'),(336,'test1','登录',NULL,'成功',NULL,'2015-01-31 21:33:45'),(337,'test1','登录',NULL,'成功',NULL,'2015-02-01 07:31:29'),(338,'test2','登录',NULL,'成功',NULL,'2015-02-01 09:41:13'),(339,'test2','登录',NULL,'成功',NULL,'2015-02-01 10:08:45'),(340,'test2','登录',NULL,'成功',NULL,'2015-02-01 14:19:19'),(341,'test2','登录',NULL,'成功',NULL,'2015-02-01 17:26:00'),(342,'test2','登录',NULL,'成功',NULL,'2015-02-01 17:28:30'),(343,'test1','登录',NULL,'成功',NULL,'2015-02-01 23:26:57'),(344,'test1','登录',NULL,'成功',NULL,'2015-02-02 14:17:34'),(345,'test1','登录',NULL,'成功',NULL,'2015-02-02 16:02:11'),(346,'test1','登录',NULL,'成功',NULL,'2015-02-02 16:14:04'),(347,'test1','登录',NULL,'成功',NULL,'2015-02-02 16:14:42'),(348,'test1','登录',NULL,'成功',NULL,'2015-02-02 16:15:11'),(349,'test1','登录',NULL,'成功',NULL,'2015-02-02 21:36:14'),(350,'test1','登录',NULL,'成功',NULL,'2015-02-03 09:23:39'),(351,'test1','登录',NULL,'成功',NULL,'2015-02-03 10:35:12'),(352,'test1','登录',NULL,'成功',NULL,'2015-02-03 10:40:00'),(353,'test1','登录',NULL,'成功',NULL,'2015-02-03 10:59:50'),(354,'test1','登录',NULL,'成功',NULL,'2015-02-03 11:02:01'),(355,'admin','登录',NULL,'成功',NULL,'2015-02-03 11:22:13'),(356,'test1','登录',NULL,'成功',NULL,'2015-02-03 11:24:16'),(357,'test1','登录',NULL,'成功',NULL,'2015-02-03 11:25:37'),(358,'test1','登录',NULL,'成功',NULL,'2015-02-03 11:37:00'),(359,'test1','登录',NULL,'成功',NULL,'2015-02-03 11:39:45'),(360,'test1','登录',NULL,'成功',NULL,'2015-02-03 11:42:16'),(361,'test1','登录',NULL,'成功',NULL,'2015-02-03 11:43:48'),(362,'test1','登录',NULL,'成功',NULL,'2015-02-03 11:50:33'),(363,'test1','登录',NULL,'成功',NULL,'2015-02-03 11:59:13'),(364,'test2','登录',NULL,'成功',NULL,'2015-02-03 11:59:55'),(365,'test2','登录',NULL,'成功',NULL,'2015-02-03 12:02:32'),(366,'test1','登录',NULL,'成功',NULL,'2015-02-03 12:03:22'),(367,'test1','登录',NULL,'成功',NULL,'2015-02-03 12:21:30'),(368,'test1','登录',NULL,'成功',NULL,'2015-02-03 13:42:23'),(369,'test1','登录',NULL,'成功',NULL,'2015-02-03 14:05:29'),(370,'test1','登录',NULL,'成功',NULL,'2015-02-03 15:09:20'),(371,'test1','登录',NULL,'成功',NULL,'2015-02-03 16:27:41'),(372,'test1','登录',NULL,'成功',NULL,'2015-02-03 16:27:44'),(373,'test2','登录',NULL,'成功',NULL,'2015-02-03 16:32:37'),(374,'test2','登录',NULL,'成功',NULL,'2015-02-03 16:33:54'),(375,'test2','登录',NULL,'成功',NULL,'2015-02-03 16:35:08'),(376,'test1','登录',NULL,'成功',NULL,'2015-02-03 17:24:39'),(377,'test2','登录',NULL,'成功',NULL,'2015-02-03 20:08:49'),(378,'test2','登录',NULL,'成功',NULL,'2015-02-03 20:09:57'),(379,'test1','登录',NULL,'成功',NULL,'2015-02-03 22:14:54'),(380,'test1','登录',NULL,'成功',NULL,'2015-02-04 09:10:07'),(381,'test2','登录',NULL,'成功',NULL,'2015-02-04 10:40:46'),(382,'test2','登录',NULL,'成功',NULL,'2015-02-04 12:00:57'),(383,'test1','登录',NULL,'成功',NULL,'2015-02-04 12:07:29'),(384,'test2','登录',NULL,'成功',NULL,'2015-02-04 13:56:53'),(385,'test1','登录',NULL,'成功',NULL,'2015-02-04 15:43:42'),(386,'test1','登录',NULL,'成功',NULL,'2015-02-05 11:45:01'),(387,'test1','登录',NULL,'成功',NULL,'2015-02-05 12:03:04'),(388,'test1','登录',NULL,'成功',NULL,'2015-02-05 12:05:44'),(389,'test2','登录',NULL,'成功',NULL,'2015-02-05 12:43:33'),(390,'test2','登录',NULL,'成功',NULL,'2015-02-05 12:48:45'),(391,'test1','登录',NULL,'成功',NULL,'2015-02-05 13:02:02'),(392,'admin','登录',NULL,'成功',NULL,'2015-02-05 13:04:10'),(393,'test2','登录',NULL,'成功',NULL,'2015-02-05 13:08:51'),(394,'test2','登录',NULL,'成功',NULL,'2015-02-05 13:11:52'),(395,'admin','登录',NULL,'成功',NULL,'2015-02-05 13:33:43'),(396,'admin','登录',NULL,'成功',NULL,'2015-02-05 16:43:00'),(397,'test2','登录',NULL,'成功',NULL,'2015-02-05 20:55:33'),(398,'admin','登录',NULL,'成功',NULL,'2015-02-06 09:55:38'),(399,'test2','登录',NULL,'成功',NULL,'2015-02-06 11:09:22'),(400,'test2','登录',NULL,'成功',NULL,'2015-02-06 11:14:50'),(401,'test1','登录',NULL,'成功',NULL,'2015-02-06 11:59:26'),(402,'test1','登录',NULL,'成功',NULL,'2015-02-06 12:00:56'),(403,'test1','登录',NULL,'成功',NULL,'2015-02-06 12:02:28'),(404,'test1','登录',NULL,'成功',NULL,'2015-02-06 12:14:33'),(405,'admin','登录',NULL,'成功',NULL,'2015-02-06 12:16:06'),(406,'test1','登录',NULL,'成功',NULL,'2015-02-06 12:16:10'),(407,'test1','登录',NULL,'成功',NULL,'2015-02-06 12:18:22'),(408,'test2','登录',NULL,'成功',NULL,'2015-02-06 12:21:20'),(409,'test1','登录',NULL,'成功',NULL,'2015-02-06 12:26:44'),(410,'test1','登录',NULL,'成功',NULL,'2015-02-06 12:47:58'),(411,'admin','登录',NULL,'成功',NULL,'2015-02-06 13:02:14'),(412,'test1','登录',NULL,'成功',NULL,'2015-02-06 14:21:15'),(413,'test1','登录',NULL,'成功',NULL,'2015-02-06 14:33:19'),(414,'test1','登录',NULL,'成功',NULL,'2015-02-06 14:48:32'),(415,'admin','登录',NULL,'成功',NULL,'2015-02-06 14:49:15'),(416,'test1','登录',NULL,'成功',NULL,'2015-02-06 14:51:30'),(417,'test1','登录',NULL,'成功',NULL,'2015-02-06 14:54:46'),(418,'test1','登录',NULL,'成功',NULL,'2015-02-06 14:56:06'),(419,'test1','登录',NULL,'成功',NULL,'2015-02-06 15:11:49'),(420,'test1','登录',NULL,'成功',NULL,'2015-02-06 15:19:32'),(421,'test1','登录',NULL,'成功',NULL,'2015-02-06 15:55:41'),(422,'test1','登录',NULL,'成功',NULL,'2015-02-06 16:03:07'),(423,'admin','登录',NULL,'成功',NULL,'2015-02-06 16:10:12'),(424,'test1','登录',NULL,'成功',NULL,'2015-02-06 16:24:17'),(425,'test2','登录',NULL,'成功',NULL,'2015-02-06 18:52:38'),(426,'test2','登录',NULL,'成功',NULL,'2015-02-07 10:29:44'),(427,'admin','登录',NULL,'成功',NULL,'2015-02-07 10:54:05'),(428,'test1','登录',NULL,'成功',NULL,'2015-02-07 10:56:04'),(429,'test2','登录',NULL,'成功',NULL,'2015-02-07 11:03:10'),(430,'test2','登录',NULL,'成功',NULL,'2015-02-07 11:04:25'),(431,'test2','登录',NULL,'成功',NULL,'2015-02-07 11:08:26'),(432,'test1','登录',NULL,'成功',NULL,'2015-02-07 11:10:23'),(433,'test2','登录',NULL,'成功',NULL,'2015-02-07 11:12:21'),(434,'admin','登录',NULL,'成功',NULL,'2015-02-07 13:04:12'),(435,'admin','登录',NULL,'成功',NULL,'2015-02-07 14:53:01'),(436,'test1','登录',NULL,'成功',NULL,'2015-02-07 15:03:02'),(437,'fuego','登录',NULL,'成功',NULL,'2015-02-07 15:03:56'),(438,'test2','登录',NULL,'成功',NULL,'2015-02-07 16:05:44'),(439,'fuego','登录',NULL,'成功',NULL,'2015-02-07 16:08:10'),(440,'admin','登录',NULL,'成功',NULL,'2015-02-07 16:20:07'),(441,'fuego','登录',NULL,'成功',NULL,'2015-02-07 16:26:25'),(442,'test1','登录',NULL,'成功',NULL,'2015-02-07 16:27:22'),(443,'admin','登录',NULL,'成功',NULL,'2015-02-07 16:27:50'),(444,'test1','登录',NULL,'成功',NULL,'2015-02-07 19:09:45'),(445,'test2','登录',NULL,'成功',NULL,'2015-02-08 09:38:00'),(446,'test2','登录',NULL,'成功',NULL,'2015-02-08 09:42:42'),(447,'admin','登录',NULL,'成功',NULL,'2015-02-08 15:48:45'),(448,'fuego','登录',NULL,'成功',NULL,'2015-02-08 16:08:11'),(449,'test1','登录',NULL,'成功',NULL,'2015-02-08 16:11:49'),(450,'fuego','登录',NULL,'成功',NULL,'2015-02-08 16:27:00'),(451,'fuego','登录',NULL,'成功',NULL,'2015-02-08 17:06:56'),(452,'test2','登录',NULL,'成功',NULL,'2015-02-08 17:08:00'),(453,'admin','登录',NULL,'成功',NULL,'2015-02-08 17:16:33'),(454,'test1','登录',NULL,'成功',NULL,'2015-02-08 18:14:18'),(455,'admin','登录',NULL,'成功',NULL,'2015-02-09 12:00:36'),(456,'fuego','登录',NULL,'成功',NULL,'2015-02-09 14:41:46'),(457,'fuego','登录',NULL,'成功',NULL,'2015-02-09 15:34:47'),(458,'fuego','登录',NULL,'成功',NULL,'2015-02-09 21:39:57'),(459,'fuego','登录',NULL,'成功',NULL,'2015-02-09 21:58:43'),(460,'fuego','登录',NULL,'成功',NULL,'2015-02-09 23:07:53'),(461,'fuego','登录',NULL,'成功',NULL,'2015-02-09 23:28:54'),(462,'admin','登录',NULL,'成功',NULL,'2015-02-09 23:34:27'),(463,'fuego','登录',NULL,'成功',NULL,'2015-02-09 23:35:06'),(464,'fuego','登录',NULL,'成功',NULL,'2015-02-09 23:53:32'),(465,'fuego','登录',NULL,'成功',NULL,'2015-02-09 23:54:57'),(466,'fuego','登录',NULL,'成功',NULL,'2015-02-09 23:55:30'),(467,'fuego','登录',NULL,'成功',NULL,'2015-02-09 23:57:44'),(468,'fuego','登录',NULL,'成功',NULL,'2015-02-09 23:59:11'),(469,'fuego','登录',NULL,'成功',NULL,'2015-02-10 00:05:50'),(470,'fuego','登录',NULL,'成功',NULL,'2015-02-10 00:06:40'),(471,'fuego','登录',NULL,'成功',NULL,'2015-02-10 00:08:21'),(472,'fuego','登录',NULL,'成功',NULL,'2015-02-10 00:08:44'),(473,'fuego','登录',NULL,'成功',NULL,'2015-02-10 00:08:59'),(474,'fuego','登录',NULL,'成功',NULL,'2015-02-10 00:16:52'),(475,'fuego','登录',NULL,'成功',NULL,'2015-02-10 00:18:12'),(476,'fuego','登录',NULL,'成功',NULL,'2015-02-10 00:23:19'),(477,'fuego','登录',NULL,'成功',NULL,'2015-02-10 00:27:32'),(478,'fuego','登录',NULL,'成功',NULL,'2015-02-12 21:43:33'),(479,'admin','登录',NULL,'成功',NULL,'2015-02-13 14:14:44'),(480,'admin','登录',NULL,'成功',NULL,'2015-02-13 14:30:25'),(481,'admin','登录',NULL,'成功',NULL,'2015-02-13 14:31:59'),(482,'admin','登录',NULL,'成功',NULL,'2015-02-13 14:42:46'),(483,'admin','登录',NULL,'成功',NULL,'2015-02-13 14:53:23'),(484,'admin','登录',NULL,'成功',NULL,'2015-02-20 15:24:42'),(485,'admin','登录',NULL,'成功',NULL,'2015-02-20 15:29:56'),(486,'admin','登录',NULL,'成功',NULL,'2015-02-20 15:56:37'),(487,'admin','登录',NULL,'成功',NULL,'2015-02-20 16:28:06'),(488,'admin','登录',NULL,'成功',NULL,'2015-02-21 13:30:51'),(489,'admin','登录',NULL,'成功',NULL,'2015-03-03 17:44:14'),(490,'admin','登录',NULL,'成功',NULL,'2015-03-03 19:45:48'),(491,'admin','登录',NULL,'成功',NULL,'2015-03-03 21:02:17'),(492,'admin','登录',NULL,'成功',NULL,'2015-03-03 21:14:32'),(493,'admin','登录',NULL,'成功',NULL,'2015-03-03 21:16:35'),(494,'admin','登录',NULL,'成功',NULL,'2015-03-03 21:20:37'),(495,'admin','登录',NULL,'成功',NULL,'2015-03-03 21:18:59'),(496,'admin','登录',NULL,'成功',NULL,'2015-03-03 21:28:31'),(497,'admin','登录',NULL,'成功',NULL,'2015-03-03 21:31:09'),(498,'admin','登录',NULL,'成功',NULL,'2015-03-03 21:45:44'),(499,'admin','登录',NULL,'成功',NULL,'2015-03-03 21:56:17'),(500,'admin','登录',NULL,'成功',NULL,'2015-03-03 22:03:40'),(501,'admin','登录',NULL,'成功',NULL,'2015-03-03 22:34:39'),(502,'admin','登录',NULL,'成功',NULL,'2015-03-03 23:04:28'),(503,'admin','登录',NULL,'成功',NULL,'2015-03-04 09:28:10'),(504,'admin','登录',NULL,'成功',NULL,'2015-03-04 09:52:31'),(505,'admin','登录',NULL,'成功',NULL,'2015-03-04 10:06:05'),(506,'admin','登录',NULL,'成功',NULL,'2015-03-04 10:38:48'),(507,'admin','登录',NULL,'成功',NULL,'2015-03-04 10:42:09'),(508,'admin','登录',NULL,'成功',NULL,'2015-03-04 10:42:09'),(509,'admin','登录',NULL,'成功',NULL,'2015-03-04 10:58:18'),(510,'admin','登录',NULL,'成功',NULL,'2015-03-04 11:21:30'),(511,'admin','登录',NULL,'成功',NULL,'2015-03-04 11:41:15'),(512,'admin','登录',NULL,'成功',NULL,'2015-03-04 11:49:24'),(513,'admin','登录',NULL,'成功',NULL,'2015-03-04 12:11:32'),(514,'admin','登录',NULL,'成功',NULL,'2015-03-04 12:13:25'),(515,'admin','登录',NULL,'成功',NULL,'2015-03-04 12:18:36'),(516,'admin','登录',NULL,'成功',NULL,'2015-03-04 13:17:07'),(517,'admin','登录',NULL,'成功',NULL,'2015-03-04 14:20:02'),(518,'admin','登录',NULL,'成功',NULL,'2015-03-04 14:41:14'),(519,'admin','登录',NULL,'成功',NULL,'2015-03-04 14:48:26'),(520,'admin','登录',NULL,'成功',NULL,'2015-03-04 14:58:00'),(521,'admin','登录',NULL,'成功',NULL,'2015-03-04 14:57:13'),(522,'admin','登录',NULL,'成功',NULL,'2015-03-04 15:01:30'),(523,'admin','登录',NULL,'成功',NULL,'2015-03-04 15:57:41'),(524,'admin','登录',NULL,'成功',NULL,'2015-03-04 16:38:31'),(525,'admin','登录',NULL,'成功',NULL,'2015-03-04 16:39:57'),(526,'admin','登录',NULL,'成功',NULL,'2015-03-04 16:43:28'),(527,'admin','登录',NULL,'成功',NULL,'2015-03-04 16:40:54'),(528,'admin','登录',NULL,'成功',NULL,'2015-03-04 16:41:49'),(529,'admin','登录',NULL,'成功',NULL,'2015-03-04 16:56:25'),(530,'admin','登录',NULL,'成功',NULL,'2015-03-04 16:56:43'),(531,'admin','登录',NULL,'成功',NULL,'2015-03-04 16:58:13'),(532,'admin','登录',NULL,'成功',NULL,'2015-03-04 17:01:29'),(533,'admin','登录',NULL,'成功',NULL,'2015-03-04 16:59:01'),(534,'admin','登录',NULL,'成功',NULL,'2015-03-04 17:25:55'),(535,'admin','登录',NULL,'成功',NULL,'2015-03-04 18:15:48'),(536,'admin','登录',NULL,'成功',NULL,'2015-03-04 18:29:17'),(537,'admin','登录',NULL,'成功',NULL,'2015-03-04 18:35:55'),(538,'admin','登录',NULL,'成功',NULL,'2015-03-04 19:27:00'),(539,'admin','登录',NULL,'成功',NULL,'2015-03-04 22:39:55');

#
# Structure for table "misp_privilege"
#

DROP TABLE IF EXISTS `misp_privilege`;
CREATE TABLE `misp_privilege` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `master_type` varchar(20) NOT NULL,
  `master_value` varchar(255) NOT NULL,
  `access_obj_type` varchar(20) NOT NULL,
  `access_obj_value` varchar(50) NOT NULL,
  `operation` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

#
# Data for table "misp_privilege"
#

INSERT INTO `misp_privilege` VALUES (1,'ROLE','99','MENU','1001',NULL),(2,'ROLE','99','MENU','1010',NULL),(3,'ROLE','99','MENU','2001',NULL),(4,'ROLE','99','MENU','2010',NULL),(5,'ROLE','99','MENU','2020',NULL),(6,'ROLE','99','MENU','2030',''),(7,'ROLE','99','MENU','3001',''),(8,'ROLE','99','MENU','3010',''),(9,'ROLE','99','MENU','3020',NULL),(10,'ROLE','99','MENU','4001',NULL),(11,'ROLE','99','MENU','4010',NULL),(12,'ROLE','99','MENU','4020',NULL),(13,'ROLE','99','MENU','4030',NULL),(14,'ROLE','99','MENU','4040',NULL),(15,'ROLE','99','MENU','5001',NULL),(16,'ROLE','99','MENU','5010',NULL),(17,'ROLE','99','MENU','5020',NULL),(18,'ROLE','99','MENU','6001',NULL),(19,'ROLE','99','MENU','6010',NULL),(20,'ROLE','99','MENU','6020',NULL);

#
# Structure for table "misp_system_id_type"
#

DROP TABLE IF EXISTS `misp_system_id_type`;
CREATE TABLE `misp_system_id_type` (
  `NAME` varchar(20) NOT NULL,
  `STEP` int(11) NOT NULL,
  `LENGTH` int(11) NOT NULL,
  `INC_MODE` int(11) NOT NULL,
  `CURRENT_ID` int(11) NOT NULL,
  `PREFIX` varchar(255) DEFAULT NULL,
  `SUFFIX` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "misp_system_id_type"
#

INSERT INTO `misp_system_id_type` VALUES ('ORDER_ID',1,8,1,31,'S',NULL);

#
# Structure for table "misp_system_menu"
#

DROP TABLE IF EXISTS `misp_system_menu`;
CREATE TABLE `misp_system_menu` (
  `id` int(11) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `VALUE` varchar(50) NOT NULL,
  `TYPE` varchar(10) DEFAULT NULL,
  `ICON` varchar(50) DEFAULT NULL,
  `EXTERNAL` varchar(255) DEFAULT NULL,
  `FRESH` varchar(255) DEFAULT NULL,
  `URL` varchar(100) DEFAULT NULL,
  `PARENT_ID` varchar(20) DEFAULT NULL,
  `FUNCTION_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "misp_system_menu"
#

INSERT INTO `misp_system_menu` VALUES (1001,'首页','首页',NULL,NULL,NULL,NULL,'#','0',NULL),(1010,'今日通告','今日通告',NULL,NULL,NULL,NULL,'login/Index','1001',NULL),(2001,'设备管理','设备管理',NULL,NULL,NULL,NULL,'#','0',NULL),(2010,'集中器管理','集中器管理',NULL,NULL,NULL,NULL,'device/ConcentratorManage','2001',NULL),(2020,'家庭告警','家庭告警',NULL,NULL,NULL,NULL,'#','2001',NULL),(2030,'企业告警','企业告警',NULL,NULL,NULL,NULL,'#','2001',NULL),(3001,'企业服务','企业服务',NULL,NULL,NULL,NULL,'#','0',NULL),(3010,'公司管理','公司管理',NULL,NULL,NULL,NULL,'device/CompanyManage','3001',NULL),(3020,'巡检日志','巡检日志',NULL,NULL,NULL,NULL,'#','3001',NULL),(4001,'公共服务','公共服务',NULL,NULL,NULL,NULL,'#','0',NULL),(4010,'新闻公告','新闻公告',NULL,NULL,NULL,NULL,'info/NewsManage','4001',NULL),(4020,'服务申请','服务申请',NULL,NULL,NULL,NULL,'info/OrderManage','4001',NULL),(4030,'常识帮助','常识帮助',NULL,NULL,NULL,NULL,'#','4001',NULL),(4040,'客户分布','客户分布',NULL,NULL,NULL,NULL,'device/CustomerDistribution','4001',NULL),(5001,'商场管理','商场管理',NULL,NULL,NULL,NULL,'#','0',NULL),(5010,'产品管理','产品管理',NULL,NULL,NULL,NULL,'#','5001',NULL),(5020,'广告发布','广告发布',NULL,NULL,NULL,NULL,'#','5001',NULL),(6001,'系统管理','系统管理',NULL,NULL,NULL,NULL,'#','0',NULL),(6010,'用户管理','用户管理',NULL,NULL,NULL,NULL,'sys/UserManage','6001',NULL),(6020,'操作日志','操作日志',NULL,NULL,NULL,NULL,'log/LogManage','6001',NULL);

#
# Structure for table "misp_user"
#

DROP TABLE IF EXISTS `misp_user`;
CREATE TABLE `misp_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(11) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "misp_user"
#

INSERT INTO `misp_user` VALUES (1,'admin','81dc9bdb52d04dc20036dbd8313ed055',99,'2014-11-27 16:07:48',2),(2,'test1','81dc9bdb52d04dc20036dbd8313ed055',1,'2015-01-24 17:38:31',2),(3,'test2','81dc9bdb52d04dc20036dbd8313ed055',1,'2015-01-30 16:47:01',2),(4,'fuego','81dc9bdb52d04dc20036dbd8313ed055',1,'2015-02-07 14:53:29',2);

#
# Structure for table "news"
#

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `NEWS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(128) DEFAULT NULL,
  `AUTHOR` varchar(255) DEFAULT NULL,
  `CONTENT` text,
  `RELEASE_DATE` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`NEWS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

#
# Data for table "news"
#

INSERT INTO `news` VALUES (3,'大家好','admin','哈哈哈哈','2014-11-25 14:33:57',0),(5,'关于消防安全的通知','admin',' 一.安全用电\r\n    各施工单位电源接驳应由具备操作资格的人员进行,其它人员严禁私自接驳电源线或违规用电,对未按规定用电的须及时作出整改配合,杜绝隐患;施工人员离开单位时,要切断电源,以防电路异常引致意外;\r\n    二.消除火患\r\n    施工现场,特别是在木器施工时严禁吸烟,吸烟时须远离易燃物,并将烟头熄灭及妥善处置;禁止在施工单元内生火或用电煮食、烤物和取暖等;小区内严禁燃放烟花、爆竹;烧纸、烧香时,必须有合适的容器盛放,同时选择合适的位置并作好周全的预防措施;\r\n    三.施工配备灭火器材\r\n    各施工单位必须配备器材,并时刻检查及保持器材的正常使用状态。\r\n    以上事宜及各项安全管理制度,希各施工单位及相关人员能配合执行,管理处将于2008年元月15日前进行统一的消防安全巡检工作,消防局将于2008年元18日开展消防安全大检查,未符合相关要求的请及时作出整改,如未能配合整改的单位,管理处将作出严厉处理。','2014-11-28 15:21:42',0),(8,'通天塔','admin','呵呵呵','2015-01-26 13:24:36',NULL),(12,'侧睡','admin','得得得','2015-01-26 14:29:31',NULL),(13,'测试通知','admin','111111','2015-01-31 14:53:12',NULL),(14,'测试通知2','admin','鹅鹅鹅饿饿','2015-01-31 15:30:37',NULL),(15,'测试通知3','admin','天天天天天天天天天天天天天天','2015-01-31 15:32:07',NULL),(16,'测试通知4','admin','iohioii好ioiOhio呃呃菲儿','2015-01-31 20:37:29',NULL),(17,'测试通知5','admin','测试特殊报警声音 ','2015-01-31 20:49:35',NULL),(18,'测试通知6','admin','告警声音测试','2015-01-31 21:04:05',NULL),(19,'测试声音','admin','呃呃呃','2015-02-03 11:38:51',NULL),(20,'测试声音','admin','呃呃呃','2015-02-06 12:17:33',NULL),(21,'测试声音','admin','吾问无为谓','2015-02-06 12:18:30',NULL),(22,'测试声音','admin','乒乒乓乓','2015-02-06 14:49:33',NULL),(23,'测试声音','admin','呵呵呵','2015-02-06 14:51:32',NULL),(24,'测试声音','admin','凤飞飞','2015-02-06 14:54:57',NULL),(25,'测试声音','admin','啪啪啪','2015-02-06 14:56:00',NULL),(26,'测试声音','admin','pppp','2015-02-06 15:12:26',NULL),(27,'ces','admin','dddd','2015-02-09 23:35:24',NULL),(28,'dd','admin','ddd','2015-02-09 23:36:39',NULL),(29,'测试声音不能点击','admin','凤飞飞','2015-02-09 23:53:44',NULL),(30,'测试声音不能点击','admin','灌灌灌灌灌','2015-02-09 23:54:16',NULL),(31,'测试声音不能点击','admin','发反反复复','2015-02-09 23:55:04',NULL),(32,'测试声音不能点击','admin','哈哈哈哈哈','2015-02-09 23:55:43',NULL),(33,'测试声音不能点击','admin','呵呵呵','2015-02-09 23:58:05',NULL),(34,'测试声音不能点击','admin','哈哈哈哈','2015-02-09 23:59:18',NULL),(35,'测试声音不能点击','admin','凤飞飞','2015-02-09 23:59:43',NULL),(36,'测试声音不能点击','admin','管灌灌灌灌灌','2015-02-10 00:05:55',NULL),(37,'测试声音不能点击','admin','哈哈哈哈哈','2015-02-10 00:16:39',NULL),(38,'测试声音不能点击','admin','人人','2015-02-10 00:16:57',NULL),(39,'测试声音不能点击','admin','公告高规格公告','2015-02-10 00:23:21',NULL),(40,'测试声音不能点击','admin','订单','2015-02-10 00:23:44',NULL),(41,'测试声音不能点击','admin','恩恩','2015-02-10 00:23:57',NULL),(42,'人人','admin','','2015-02-10 00:26:56',NULL),(43,'测试声音不能点击','admin','','2015-02-10 00:27:45',NULL),(44,'vv','admin','','2015-03-04 19:01:03',NULL);

#
# Structure for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `PRODUCT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `DESP` varchar(255) DEFAULT NULL,
  `PRICE` float DEFAULT NULL,
  `PIC_LABEL` varchar(255) DEFAULT NULL,
  `PIC_DETAIL1` varchar(255) DEFAULT NULL,
  `PIC_DETAIL2` varchar(255) DEFAULT NULL,
  `PIC_DETAIL3` varchar(255) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "product"
#


#
# Structure for table "sensor_plan"
#

DROP TABLE IF EXISTS `sensor_plan`;
CREATE TABLE `sensor_plan` (
  `PLAN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUILDING_ID` int(11) DEFAULT NULL,
  `FLOOR` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESP` varchar(255) DEFAULT NULL,
  `PIC_PATH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PLAN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "sensor_plan"
#

INSERT INTO `sensor_plan` VALUES (1,3,'2','啊啊啊','啊啊','单独');

#
# Structure for table "sensor_type"
#

DROP TABLE IF EXISTS `sensor_type`;
CREATE TABLE `sensor_type` (
  `TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE_NAME` varchar(255) DEFAULT NULL,
  `DEF_WARN_VALUE` float DEFAULT NULL,
  `DEF_ERROR_VALUE` float DEFAULT NULL,
  `TYPE_CODE` int(11) DEFAULT NULL,
  `TYPE_SYS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=491 DEFAULT CHARSET=utf8;

#
# Data for table "sensor_type"
#

INSERT INTO `sensor_type` VALUES (1,'通用',0,0,0,'RFID'),(2,'通用',0,0,0,'电气火灾监控系统'),(3,'电气火灾监控报警器',0,0,16,'电气火灾监控系统'),(4,'剩余电流式电气火灾监控探测器',0,0,17,'电气火灾监控系统'),(5,'测温式电气火灾监控探测器',0,0,18,'电气火灾监控系统'),(6,'消防电气控制装置',0,0,82,'电气火灾监控系统'),(7,'电气火灾监控设备',0,0,231,'电气火灾监控系统'),(8,'通用',0,0,0,'防火分隔设施'),(9,'防火门',0,0,102,'防火分隔设施'),(10,'防火阀',0,0,103,'防火分隔设施'),(11,'防火卷帘控制器',0,0,117,'防火分隔设施'),(12,'防火门监控器',0,0,118,'防火分隔设施'),(13,'卷帘门中',0,0,120,'防火分隔设施'),(14,'警报装置',0,0,121,'防火分隔设施'),(15,'卷帘门下',0,0,143,'防火分隔设施'),(16,'电动防火阀控制装置',0,0,223,'防火分隔设施'),(17,'防火墙',0,0,452,'防火分隔设施'),(18,'钢质隔热防火门',0,0,453,'防火分隔设施'),(19,'钢质部分隔热防火门',0,0,454,'防火分隔设施'),(20,'钢质非隔热防火门',0,0,455,'防火分隔设施'),(21,'木质隔热防火门',0,0,456,'防火分隔设施'),(22,'木质部分隔热防火门',0,0,457,'防火分隔设施'),(23,'木质非隔热防火门',0,0,458,'防火分隔设施'),(24,'钢木质隔热防火门',0,0,459,'防火分隔设施'),(25,'钢木质部分隔热防火门',0,0,460,'防火分隔设施'),(26,'钢木质非隔热防火门',0,0,461,'防火分隔设施'),(27,'其他材质隔热防火门',0,0,462,'防火分隔设施'),(28,'其他材质部分隔热防火门',0,0,463,'防火分隔设施'),(29,'其他材质非隔热防火门',0,0,464,'防火分隔设施'),(30,'防火窗',0,0,465,'防火分隔设施'),(31,'防火卷帘',0,0,466,'防火分隔设施'),(32,'控制箱',0,0,467,'防火分隔设施'),(33,'通用',0,0,0,'防火门及卷帘系统'),(34,'防火门',0,0,102,'防火门及卷帘系统'),(35,'防火阀',0,0,103,'防火门及卷帘系统'),(36,'防火卷帘控制器',0,0,117,'防火门及卷帘系统'),(37,'防火门监控器',0,0,118,'防火门及卷帘系统'),(38,'卷帘门中',0,0,120,'防火门及卷帘系统'),(39,'警报装置',0,0,121,'防火门及卷帘系统'),(40,'卷帘门下',0,0,143,'防火门及卷帘系统'),(41,'电动防火阀控制装置',0,0,223,'防火门及卷帘系统'),(42,'防火墙',0,0,452,'防火门及卷帘系统'),(43,'钢质隔热防火门',0,0,453,'防火门及卷帘系统'),(44,'钢质部分隔热防火门',0,0,454,'防火门及卷帘系统'),(45,'钢质非隔热防火门',0,0,455,'防火门及卷帘系统'),(46,'木质隔热防火门',0,0,456,'防火门及卷帘系统'),(47,'木质部分隔热防火门',0,0,457,'防火门及卷帘系统'),(48,'木质非隔热防火门',0,0,458,'防火门及卷帘系统'),(49,'钢木质隔热防火门',0,0,459,'防火门及卷帘系统'),(50,'钢木质部分隔热防火门',0,0,460,'防火门及卷帘系统'),(51,'钢木质非隔热防火门',0,0,461,'防火门及卷帘系统'),(52,'其他材质隔热防火门',0,0,462,'防火门及卷帘系统'),(53,'其他材质部分隔热防火门',0,0,463,'防火门及卷帘系统'),(54,'其他材质非隔热防火门',0,0,464,'防火门及卷帘系统'),(55,'防火窗',0,0,465,'防火门及卷帘系统'),(56,'防火卷帘',0,0,466,'防火门及卷帘系统'),(57,'控制箱',0,0,467,'防火门及卷帘系统'),(58,'通用',0,0,0,'防排烟通风系统'),(59,'通风空调',0,0,104,'防排烟通风系统'),(60,'防烟排烟风机',0,0,111,'防排烟通风系统'),(61,'防火阀',0,0,112,'防排烟通风系统'),(62,'排烟防火阀',0,0,113,'防排烟通风系统'),(63,'常闭送风口',0,0,114,'防排烟通风系统'),(64,'排烟口',0,0,115,'防排烟通风系统'),(65,'电控挡烟垂壁',0,0,116,'防排烟通风系统'),(66,'防火卷帘控制器',0,0,117,'防排烟通风系统'),(67,'防烟排烟控制装置',0,0,222,'防排烟通风系统'),(68,'送风口',0,0,417,'防排烟通风系统'),(69,'送风阀',0,0,418,'防排烟通风系统'),(70,'送风机',0,0,419,'防排烟通风系统'),(71,'送风机控制柜',0,0,420,'防排烟通风系统'),(72,'挡烟垂壁',0,0,421,'防排烟通风系统'),(73,'排烟阀',0,0,423,'防排烟通风系统'),(74,'排烟风机',0,0,424,'防排烟通风系统'),(75,'电动排烟窗',0,0,425,'防排烟通风系统'),(76,'排烟风机控制柜',0,0,427,'防排烟通风系统'),(77,'自然排烟窗',0,0,428,'防排烟通风系统'),(78,'送风、排烟机房',0,0,429,'防排烟通风系统'),(79,'机械排烟风机',0,0,580,'防排烟通风系统'),(80,'通用',0,0,0,'防烟排烟系统'),(81,'通风空调',0,0,104,'防烟排烟系统'),(82,'防烟排烟风机',0,0,111,'防烟排烟系统'),(83,'防火阀',0,0,112,'防烟排烟系统'),(84,'排烟防火阀',0,0,113,'防烟排烟系统'),(85,'常闭送风口',0,0,114,'防烟排烟系统'),(86,'排烟口',0,0,115,'防烟排烟系统'),(87,'电控挡烟垂壁',0,0,116,'防烟排烟系统'),(88,'防火卷帘控制器',0,0,117,'防烟排烟系统'),(89,'防烟排烟控制装置',0,0,222,'防烟排烟系统'),(90,'送风口',0,0,417,'防烟排烟系统'),(91,'送风阀',0,0,418,'防烟排烟系统'),(92,'送风机',0,0,419,'防烟排烟系统'),(93,'送风机控制柜',0,0,420,'防烟排烟系统'),(94,'挡烟垂壁',0,0,421,'防烟排烟系统'),(95,'排烟阀',0,0,423,'防烟排烟系统'),(96,'排烟风机',0,0,424,'防烟排烟系统'),(97,'电动排烟窗',0,0,425,'防烟排烟系统'),(98,'排烟风机控制柜',0,0,427,'防烟排烟系统'),(99,'自然排烟窗',0,0,428,'防烟排烟系统'),(100,'送风、排烟机房',0,0,429,'防烟排烟系统'),(101,'机械排烟风机',0,0,580,'防烟排烟系统'),(102,'通用',0,0,0,'干粉灭火系统'),(103,'干粉贮存容器',0,0,483,'干粉灭火系统'),(104,'干粉贮存容器出口释放装置',0,0,484,'干粉灭火系统'),(105,'驱动控制装置',0,0,486,'干粉灭火系统'),(106,'安全防护装置',0,0,487,'干粉灭火系统'),(107,'燃气发生器',0,0,488,'干粉灭火系统'),(108,'干粉软管卷盘',0,0,489,'干粉灭火系统'),(109,'干粉炮',0,0,490,'干粉灭火系统'),(110,'通用',0,0,0,'火灾探测报警系统'),(111,'火灾报警控制器',0,0,1,'火灾探测报警系统'),(112,'可燃气体探测器',0,0,10,'火灾探测报警系统'),(113,'点型可燃气体探测器',0,0,11,'火灾探测报警系统'),(114,'独立式可燃气体探测器',0,0,12,'火灾探测报警系统'),(115,'线型可燃气体探测器',0,0,13,'火灾探测报警系统'),(116,'电气火灾监控报警器',0,0,16,'火灾探测报警系统'),(117,'剩余电流式电气火灾监控探测器',0,0,17,'火灾探测报警系统'),(118,'测温式电气火灾监控探测器',0,0,18,'火灾探测报警系统'),(119,'消火栓按钮',0,0,24,'火灾探测报警系统'),(120,'火灾探测器',0,0,25,'火灾探测报警系统'),(121,'感温火灾探测器',0,0,30,'火灾探测报警系统'),(122,'点型感温火灾探测器',0,0,31,'火灾探测报警系统'),(123,'点型火灾探测器（S型）',0,0,32,'火灾探测报警系统'),(124,'点型火灾探测器（R型）',0,0,33,'火灾探测报警系统'),(125,'线型感温火灾探测器',0,0,34,'火灾探测报警系统'),(126,'线型火灾探测器（S型）',0,0,35,'火灾探测报警系统'),(127,'线型火灾探测器（R型）',0,0,36,'火灾探测报警系统'),(128,'光纤感温火灾探测器',0,0,37,'火灾探测报警系统'),(129,'感烟火灾探测器',0,0,40,'火灾探测报警系统'),(130,'点型离子感烟火灾探测器',0,0,41,'火灾探测报警系统'),(131,'点型光电感烟火灾探测器',0,0,42,'火灾探测报警系统'),(132,'线型光束感烟火灾探测器',0,0,43,'火灾探测报警系统'),(133,'吸气式感烟火灾探测器',0,0,44,'火灾探测报警系统'),(134,'复合式火灾探测器',0,0,50,'火灾探测报警系统'),(135,'复合式感烟感温火灾探测器',0,0,51,'火灾探测报警系统'),(136,'复合式感光感温火灾探测器',0,0,52,'火灾探测报警系统'),(137,'复合式感光感烟火灾探测器',0,0,53,'火灾探测报警系统'),(138,'紫外火焰探测器',0,0,61,'火灾探测报警系统'),(139,'红外火焰探测器',0,0,62,'火灾探测报警系统'),(140,'感光火灾探测器',0,0,69,'火灾探测报警系统'),(141,'气体探测器',0,0,74,'火灾探测报警系统'),(142,'图象摄像方式火灾探测器',0,0,78,'火灾探测报警系统'),(143,'感声火灾探测器',0,0,79,'火灾探测报警系统'),(144,'气体灭火控制器',0,0,81,'火灾探测报警系统'),(145,'消防电气控制装置',0,0,82,'火灾探测报警系统'),(146,'消防控制室图形显示装置',0,0,83,'火灾探测报警系统'),(147,'模块',0,0,84,'火灾探测报警系统'),(148,'输入模块',0,0,85,'火灾探测报警系统'),(149,'输入/输出模块',0,0,87,'火灾探测报警系统'),(150,'阀驱动装置',0,0,101,'火灾探测报警系统'),(151,'讯响器',0,0,107,'火灾探测报警系统'),(152,'消防电话',0,0,108,'火灾探测报警系统'),(153,'防火卷帘控制器',0,0,117,'火灾探测报警系统'),(154,'开关量报警',0,0,122,'火灾探测报警系统'),(155,'柴油发电',0,0,124,'火灾探测报警系统'),(156,'照明配电',0,0,125,'火灾探测报警系统'),(157,'动力配电',0,0,126,'火灾探测报警系统'),(158,'水幕电嗞',0,0,127,'火灾探测报警系统'),(159,'防盗模块',0,0,131,'火灾探测报警系统'),(160,'气压罐',0,0,132,'火灾探测报警系统'),(161,'层号灯',0,0,133,'火灾探测报警系统'),(162,'急停按钮',0,0,136,'火灾探测报警系统'),(163,'上位机',0,0,137,'火灾探测报警系统'),(164,'空压机',0,0,138,'火灾探测报警系统'),(165,'联动电源',0,0,139,'火灾探测报警系统'),(166,'多线制盘锁',0,0,140,'火灾探测报警系统'),(167,'雨淋阀',0,0,141,'火灾探测报警系统'),(168,'点型感烟火灾探测器',0,0,200,'火灾探测报警系统'),(169,'红外光束感烟火灾探测器',0,0,201,'火灾探测报警系统'),(170,'吸气式火灾探测器',0,0,202,'火灾探测报警系统'),(171,'点型火焰探测器',0,0,203,'火灾探测报警系统'),(172,'点型可燃气体探测器',0,0,204,'火灾探测报警系统'),(173,'独立式感烟火灾探测报警器',0,0,205,'火灾探测报警系统'),(174,'点型紫外火焰探测器',0,0,206,'火灾探测报警系统'),(175,'特种火灾探测器',0,0,207,'火灾探测报警系统'),(176,'消防联动控制器',0,0,208,'火灾探测报警系统'),(177,'总线联动控制盘',0,0,209,'火灾探测报警系统'),(178,'直接联动控制盘',0,0,210,'火灾探测报警系统'),(179,'可燃气体报警控制器',0,0,211,'火灾探测报警系统'),(180,'消防设备应急电源',0,0,213,'火灾探测报警系统'),(181,'系统备用电源',0,0,214,'火灾探测报警系统'),(182,'消防应急广播设备',0,0,215,'火灾探测报警系统'),(183,'消防电动装置',0,0,216,'火灾探测报警系统'),(184,'自动喷水灭火系统控制装置',0,0,217,'火灾探测报警系统'),(185,'干粉灭火系统控制装置',0,0,218,'火灾探测报警系统'),(186,'消火栓系统的控制装置',0,0,220,'火灾探测报警系统'),(187,'通风空调控制装置',0,0,221,'火灾探测报警系统'),(188,'电动防火门控制装置',0,0,224,'火灾探测报警系统'),(189,'消防电梯和非消防电梯的回降控制装置',0,0,225,'火灾探测报警系统'),(190,'火灾声光报警器',0,0,226,'火灾探测报警系统'),(191,'声光报警器、警铃',0,0,227,'火灾探测报警系统'),(192,'火灾应急照明和疏散指示控制装置',0,0,228,'火灾探测报警系统'),(193,'切断非消防电源的控制装置',0,0,229,'火灾探测报警系统'),(194,'电动阀控制装置',0,0,230,'火灾探测报警系统'),(195,'测量范围为0～100％LEL的点型可燃气体探测器',0,0,232,'火灾探测报警系统'),(196,'测量范围为0～100％LEL的独立式可燃气体探测器',0,0,233,'火灾探测报警系统'),(197,'测量范围为0～100％LEL的便携式可燃气体探测器',0,0,234,'火灾探测报警系统'),(198,'测量人工煤气的点型可燃气体探测器',0,0,235,'火灾探测报警系统'),(199,'测量人工煤气的独立式可燃气体探测器',0,0,236,'火灾探测报警系统'),(200,'测量人工煤气的便携式可燃气体探测器',0,0,237,'火灾探测报警系统'),(201,'传输设备',0,0,261,'火灾探测报警系统'),(202,'控制系统',0,0,470,'火灾探测报警系统'),(203,'供电电源',0,0,471,'火灾探测报警系统'),(204,'端子箱',0,0,499,'火灾探测报警系统'),(205,'逃生自救设施',0,0,500,'火灾探测报警系统'),(206,'疏散楼梯',0,0,502,'火灾探测报警系统'),(207,'疏散走道',0,0,503,'火灾探测报警系统'),(208,'建筑防火安全标志',0,0,504,'火灾探测报警系统'),(209,'家用报警器',0,0,506,'火灾探测报警系统'),(210,'消防火灾控制器',0,0,515,'火灾探测报警系统'),(211,'消防报警控制器',0,0,517,'火灾探测报警系统'),(212,'探测回路',0,0,548,'火灾探测报警系统'),(213,'手动火灾报警按钮',0,0,549,'火灾探测报警系统'),(214,'图象型火灾探测器',0,0,550,'火灾探测报警系统'),(215,'区域显示器（火灾显示盘）',0,0,551,'火灾探测报警系统'),(216,'火灾报警探测器',0,0,566,'火灾探测报警系统'),(217,'手动报警按钮',0,0,567,'火灾探测报警系统'),(218,'消防联动控制设备',0,0,568,'火灾探测报警系统'),(219,'电话模块',0,0,585,'火灾探测报警系统'),(220,'通用',0,0,0,'可燃气体探测报警系统'),(221,'可燃气体探测器',0,0,10,'可燃气体探测报警系统'),(222,'点型可燃气体探测器',0,0,11,'可燃气体探测报警系统'),(223,'独立式可燃气体探测器',0,0,12,'可燃气体探测报警系统'),(224,'线型可燃气体探测器',0,0,13,'可燃气体探测报警系统'),(225,'点型可燃气体探测器',0,0,204,'可燃气体探测报警系统'),(226,'测量范围为0～100％LEL的点型可燃气体探测器',0,0,232,'可燃气体探测报警系统'),(227,'测量范围为0～100％LEL的独立式可燃气体探测器',0,0,233,'可燃气体探测报警系统'),(228,'测量范围为0～100％LEL的便携式可燃气体探测器',0,0,234,'可燃气体探测报警系统'),(229,'测量人工煤气的点型可燃气体探测器',0,0,235,'可燃气体探测报警系统'),(230,'测量人工煤气的独立式可燃气体探测器',0,0,236,'可燃气体探测报警系统'),(231,'测量人工煤气的便携式可燃气体探测器',0,0,237,'可燃气体探测报警系统'),(232,'通用',0,0,0,'灭火器'),(233,'手提式干粉灭火器',0,0,491,'灭火器'),(234,'手提式二氧化碳灭火器',0,0,492,'灭火器'),(235,'手提式水基型灭火器',0,0,493,'灭火器'),(236,'手提式洁净气体灭火器',0,0,494,'灭火器'),(237,'推车式干粉灭火器',0,0,495,'灭火器'),(238,'推车式二氧化碳灭火器',0,0,496,'灭火器'),(239,'推车式水基型灭火器',0,0,497,'灭火器'),(240,'推车式洁净气休灭火器',0,0,498,'灭火器'),(241,'手提贮压式干粉灭火器',0,0,507,'灭火器'),(242,'推车式1211灭火器',0,0,510,'灭火器'),(243,'手提式清水灭火器',0,0,511,'灭火器'),(244,'手提式酸碱灭火器',0,0,512,'灭火器'),(245,'手提式化学泡沫灭火器',0,0,513,'灭火器'),(246,'手提式驻压式干粉灭火器',0,0,584,'灭火器'),(247,'通用',0,0,0,'泡沫灭火系统'),(248,'报警阀',0,0,98,'泡沫灭火系统'),(249,'泡沫液泵',0,0,105,'泡沫灭火系统'),(250,'泡沫灭火系统的控制装置',0,0,219,'泡沫灭火系统'),(251,'压力表',0,0,284,'泡沫灭火系统'),(252,'压力式比例混合装置',0,0,345,'泡沫灭火系统'),(253,'泡沫液压力储罐',0,0,346,'泡沫灭火系统'),(254,'安全阀',0,0,347,'泡沫灭火系统'),(255,'环泵式比例混合器',0,0,349,'泡沫灭火系统'),(256,'管线式比例混合器',0,0,350,'泡沫灭火系统'),(257,'平衡式比例混合装置',0,0,351,'泡沫灭火系统'),(258,'平衡阀',0,0,352,'泡沫灭火系统'),(259,'低倍数空气泡沫产生器',0,0,353,'泡沫灭火系统'),(260,'高背压泡沫产生器',0,0,354,'泡沫灭火系统'),(261,'中倍数泡沫产生器',0,0,355,'泡沫灭火系统'),(262,'高倍数泡沫产生器',0,0,356,'泡沫灭火系统'),(263,'泡沫钩管',0,0,358,'泡沫灭火系统'),(264,'泡沫炮',0,0,359,'泡沫灭火系统'),(265,'空气泡沫枪',0,0,360,'泡沫灭火系统'),(266,'泡沫喷头',0,0,361,'泡沫灭火系统'),(267,'泡沫消火栓',0,0,362,'泡沫灭火系统'),(268,'单向阀',0,0,363,'泡沫灭火系统'),(269,'控制阀门',0,0,364,'泡沫灭火系统'),(270,'过滤器',0,0,365,'泡沫灭火系统'),(271,'连接软管',0,0,366,'泡沫灭火系统'),(272,'控制盘、控制柜',0,0,367,'泡沫灭火系统'),(273,'常压泡沫液储罐',0,0,368,'泡沫灭火系统'),(274,'半固定式（轻便式）泡沫灭火装置',0,0,369,'泡沫灭火系统'),(275,'泡沫消火栓箱',0,0,370,'泡沫灭火系统'),(276,'泡沫消防水泵',0,0,371,'泡沫灭火系统'),(277,'泡沫混合液泵',0,0,372,'泡沫灭火系统'),(278,'泡沫管道',0,0,373,'泡沫灭火系统'),(279,'压力泄放阀',0,0,374,'泡沫灭火系统'),(280,'电磁阀',0,0,375,'泡沫灭火系统'),(281,'泡沫喷雾灭火装置',0,0,376,'泡沫灭火系统'),(282,'其他泡沫灭火设备',0,0,377,'泡沫灭火系统'),(283,'闭式喷淋头【闭式泡沫-水喷淋系统】',0,0,378,'泡沫灭火系统'),(284,'泡沫泵',0,0,555,'泡沫灭火系统'),(285,'泡沫消防泵',0,0,575,'泡沫灭火系统'),(286,'泡沫消防泵控制柜',0,0,576,'泡沫灭火系统'),(287,'泡沫发生器',0,0,577,'泡沫灭火系统'),(288,'通用',0,0,0,'气体灭火系统'),(289,'压力表',0,0,284,'气体灭火系统'),(290,'单向阀',0,0,363,'气体灭火系统'),(291,'灭火剂瓶组',0,0,379,'气体灭火系统'),(292,'驱动气体瓶组',0,0,380,'气体灭火系统'),(293,'容器（贮存灭火剂容器）',0,0,381,'气体灭火系统'),(294,'容器阀',0,0,384,'气体灭火系统'),(295,'选择阀',0,0,385,'气体灭火系统'),(296,'集流管',0,0,386,'气体灭火系统'),(297,'连接管',0,0,387,'气体灭火系统'),(298,'喷嘴',0,0,388,'气体灭火系统'),(299,'信号反馈装置',0,0,389,'气体灭火系统'),(300,'安全泄放装置',0,0,390,'气体灭火系统'),(301,'控制盘',0,0,391,'气体灭火系统'),(302,'检漏装置',0,0,392,'气体灭火系统'),(303,'减压装置',0,0,393,'气体灭火系统'),(304,'低泄高封阀',0,0,394,'气体灭火系统'),(305,'压力讯号器【高、低压二氧化碳灭火系统】',0,0,398,'气体灭火系统'),(306,'制冷机组',0,0,400,'气体灭火系统'),(307,'储瓶柜【柜式七氟丙烷灭火装置】',0,0,401,'气体灭火系统'),(308,'火灾探测装置【柜式七氟丙烷灭火装置】',0,0,402,'气体灭火系统'),(309,'控制器【柜式七氟丙烷灭火装置】',0,0,403,'气体灭火系统'),(310,'消防柜【油浸变压器排油注氮灭火装置】',0,0,404,'气体灭火系统'),(311,'消防控制柜【油浸变压器排油注氮灭火装置】',0,0,405,'气体灭火系统'),(312,'火灾探测装置【油浸变压器排油注氮灭火装置】',0,0,406,'气体灭火系统'),(313,'断流阀【油浸变压器排油注氮灭火装置】',0,0,407,'气体灭火系统'),(314,'氮气瓶组【油浸变压器排油注氮灭火装置】',0,0,408,'气体灭火系统'),(315,'氮气释放阀【油浸变压器排油注氮灭火装置】',0,0,409,'气体灭火系统'),(316,'排油阀及配套的管路管件',0,0,412,'气体灭火系统'),(317,'流量调节阀【油浸变压器排油注氮灭火装置】',0,0,413,'气体灭火系统'),(318,'排气组件【油浸变压器排油注氮灭火装置】',0,0,414,'气体灭火系统'),(319,'油气隔离装置【油浸变压器排油注氮灭火装置】',0,0,415,'气体灭火系统'),(320,'检修阀【油浸变压器排油注氮灭火装置】',0,0,416,'气体灭火系统'),(321,'驱动装置',0,0,556,'气体灭火系统'),(322,'气体灭火控制盘',0,0,578,'气体灭火系统'),(323,'通用',0,0,0,'水喷雾灭火系统（泵启动方式）'),(324,'通用',0,0,0,'水喷雾灭火系统（压力容器启动方式）'),(325,'通用',0,0,0,'通用'),(326,'点型可燃气体探测器',0,0,11,'通用'),(327,'独立式可燃气体探测器',0,0,12,'通用'),(328,'剩余电流式电气火灾监控探测器',0,0,17,'通用'),(329,'线型感温火灾探测器',0,0,34,'通用'),(330,'逃生自救设施',0,0,500,'通用'),(331,'安全车道',0,0,501,'通用'),(332,'疏散楼梯',0,0,502,'通用'),(333,'疏散走道',0,0,503,'通用'),(334,'建筑防火安全标志',0,0,504,'通用'),(335,'通用',0,0,0,'细水雾灭火系统'),(336,'通用',0,0,0,'细水雾灭火系统'),(337,'细水雾喷头',0,0,474,'细水雾灭火系统'),(338,'贮气瓶组【瓶组式系统】',0,0,475,'细水雾灭火系统'),(339,'贮气容器【瓶组式系统】',0,0,476,'细水雾灭火系统'),(340,'贮水瓶组【瓶组式系统】',0,0,477,'细水雾灭火系统'),(341,'贮水箱【泵组式系统】',0,0,478,'细水雾灭火系统'),(342,'分区控制阀',0,0,479,'细水雾灭火系统'),(343,'气体单向阀【瓶组式系统】',0,0,480,'细水雾灭火系统'),(344,'泵组单元【泵组式系统】',0,0,481,'细水雾灭火系统'),(345,'泄压调压阀【泵组式系统】',0,0,482,'细水雾灭火系统'),(346,'泵租单元【泵组式系统】',0,0,562,'细水雾灭火系统'),(347,'通用',0,0,0,'消防电话'),(348,'输入模块',0,0,85,'消防电话'),(349,'输入/输出模块',0,0,87,'消防电话'),(350,'中继模块',0,0,88,'消防电话'),(351,'消防电话总机',0,0,448,'消防电话'),(352,'消防电话分机',0,0,449,'消防电话'),(353,'消防电话插孔',0,0,450,'消防电话'),(354,'电源',0,0,451,'消防电话'),(355,'通用',0,0,0,'消防电梯'),(356,'消防电梯和非消防电梯的回降控制装置',0,0,225,'消防电梯'),(357,'轿门和层门',0,0,468,'消防电梯'),(358,'驱动主机和相关设备',0,0,469,'消防电梯'),(359,'控制系统',0,0,470,'消防电梯'),(360,'轿厢和层站的控制装置',0,0,472,'消防电梯'),(361,'消防服务通讯系统',0,0,473,'消防电梯'),(362,'通用',0,0,0,'消防供配电设施'),(363,'消防配电柜（箱）',0,0,256,'消防供配电设施'),(364,'自备发电机组',0,0,257,'消防供配电设施'),(365,'应急电源',0,0,258,'消防供配电设施'),(366,'储油设施',0,0,259,'消防供配电设施'),(367,'通用',0,0,0,'消防供水设施'),(368,'消防水泵',0,0,91,'消防供水设施'),(369,'稳压泵',0,0,93,'消防供水设施'),(370,'喷淋泵',0,0,95,'消防供水设施'),(371,'管网电磁阀',0,0,106,'消防供水设施'),(372,'消防水池',0,0,263,'消防供水设施'),(373,'低压车用消防泵',0,0,264,'消防供水设施'),(374,'车用消防泵',0,0,265,'消防供水设施'),(375,'手台机动消防泵',0,0,266,'消防供水设施'),(376,'船用消防泵',0,0,267,'消防供水设施'),(377,'消防泵组',0,0,268,'消防供水设施'),(378,'工程柴油机消防泵（不含控制柜',0,0,269,'消防供水设施'),(379,'卧式单极切线消防泵',0,0,270,'消防供水设施'),(380,'立式单极切线消防泵',0,0,271,'消防供水设施'),(381,'卧式单极消防泵',0,0,272,'消防供水设施'),(382,'水平中开双吸消防泵',0,0,273,'消防供水设施'),(383,'立式单极消防泵',0,0,274,'消防供水设施'),(384,'地上式消防泵接合器',0,0,276,'消防供水设施'),(385,'地下式消防泵接合器',0,0,277,'消防供水设施'),(386,'墙壁式消防泵接合器',0,0,278,'消防供水设施'),(387,'多用式消防泵接合器',0,0,279,'消防供水设施'),(388,'消防气压给水设备',0,0,280,'消防供水设施'),(389,'消防水泵控制柜',0,0,281,'消防供水设施'),(390,'增压泵',0,0,282,'消防供水设施'),(391,'气压水罐',0,0,283,'消防供水设施'),(392,'压力表',0,0,284,'消防供水设施'),(393,'管网控制阀门',0,0,285,'消防供水设施'),(394,'系统减压、泄压装置',0,0,286,'消防供水设施'),(395,'喷淋泵控制器',0,0,288,'消防供水设施'),(396,'消防接口',0,0,289,'消防供水设施'),(397,'消防水箱',0,0,552,'消防供水设施'),(398,'通用',0,0,0,'消防设备'),(399,'通用',0,0,0,'消防应急广播'),(400,'消防广播',0,0,90,'消防应急广播'),(401,'功放',0,0,443,'消防应急广播'),(402,'音箱（扩音机、喇叭）',0,0,445,'消防应急广播'),(403,'广播模块',0,0,446,'消防应急广播'),(404,'消防广播主机',0,0,447,'消防应急广播'),(405,'通用',0,0,0,'消防应急照明和疏散指示系统'),(406,'紧急照明',0,0,129,'消防应急照明和疏散指示系统'),(407,'疏导指示',0,0,130,'消防应急照明和疏散指示系统'),(408,'设备停动',0,0,134,'消防应急照明和疏散指示系统'),(409,'应急照明集中电源【集中电源】',0,0,430,'消防应急照明和疏散指示系统'),(410,'应急照明分配电装置【集中电源型】',0,0,431,'消防应急照明和疏散指示系统'),(411,'应急照明控制器',0,0,432,'消防应急照明和疏散指示系统'),(412,'消防应急标志灯具',0,0,433,'消防应急照明和疏散指示系统'),(413,'消防应急照明灯具',0,0,434,'消防应急照明和疏散指示系统'),(414,'消防应急照明标志复合灯具',0,0,435,'消防应急照明和疏散指示系统'),(415,'持续型消防应急灯具',0,0,436,'消防应急照明和疏散指示系统'),(416,'非持续性消防应急灯具',0,0,437,'消防应急照明和疏散指示系统'),(417,'自带电源型消防应急灯具【自带电源型】',0,0,438,'消防应急照明和疏散指示系统'),(418,'集中电源型消防应急灯具【集中电源型】',0,0,439,'消防应急照明和疏散指示系统'),(419,'非集中控制型消防应急灯具【非集中控制型】',0,0,441,'消防应急照明和疏散指示系统'),(420,'集中控制型消防应急灯具【集中控制型】',0,0,442,'消防应急照明和疏散指示系统'),(421,'消防应急照明防爆灯具',0,0,509,'消防应急照明和疏散指示系统'),(422,'应急照明系统检查',0,0,546,'消防应急照明和疏散指示系统'),(423,'子母型消防应急灯具',0,0,547,'消防应急照明和疏散指示系统'),(424,'消防应急照明防爆灯',0,0,581,'消防应急照明和疏散指示系统'),(425,'led防水应急灯',0,0,583,'消防应急照明和疏散指示系统'),(426,'通用',0,0,0,'消火栓（消防炮）灭火系统'),(427,'室内消火栓',0,0,290,'消火栓（消防炮）灭火系统'),(428,'消防水带',0,0,291,'消火栓（消防炮）灭火系统'),(429,'消防软管盘卷',0,0,292,'消火栓（消防炮）灭火系统'),(430,'压力显示装置',0,0,293,'消火栓（消防炮）灭火系统'),(431,'地上式室外消火栓',0,0,294,'消火栓（消防炮）灭火系统'),(432,'地下式室外消火栓',0,0,295,'消火栓（消防炮）灭火系统'),(433,'地下消火栓标志',0,0,296,'消火栓（消防炮）灭火系统'),(434,'栓井',0,0,297,'消火栓（消防炮）灭火系统'),(435,'直接水枪',0,0,298,'消火栓（消防炮）灭火系统'),(436,'喷雾水枪',0,0,299,'消火栓（消防炮）灭火系统'),(437,'直流喷雾水枪',0,0,300,'消火栓（消防炮）灭火系统'),(438,'多用水枪',0,0,301,'消火栓（消防炮）灭火系统'),(439,'消防炮',0,0,302,'消火栓（消防炮）灭火系统'),(440,'炮塔',0,0,303,'消火栓（消防炮）灭火系统'),(441,'现场火灾探测控制装置',0,0,304,'消火栓（消防炮）灭火系统'),(442,'回旋装置',0,0,305,'消火栓（消防炮）灭火系统'),(443,'消防炮控制装置',0,0,308,'消火栓（消防炮）灭火系统'),(444,'消防泵组【水炮系统】',0,0,309,'消火栓（消防炮）灭火系统'),(445,'消防泵站【水炮系统】',0,0,310,'消火栓（消防炮）灭火系统'),(446,'泡沫比例混合器【泡沫炮系统】',0,0,311,'消火栓（消防炮）灭火系统'),(447,'泡沫液罐【泡沫炮系统】',0,0,312,'消火栓（消防炮）灭火系统'),(448,'干粉罐【干粉炮系统】',0,0,313,'消火栓（消防炮）灭火系统'),(449,'氦气瓶【干粉炮系统】',0,0,314,'消火栓（消防炮）灭火系统'),(450,'内扣式消防接口',0,0,315,'消火栓（消防炮）灭火系统'),(451,'卡式消防接口',0,0,316,'消火栓（消防炮）灭火系统'),(452,'螺纹式消防接口',0,0,317,'消火栓（消防炮）灭火系统'),(453,'异接口',0,0,318,'消火栓（消防炮）灭火系统'),(454,'吸水管接口',0,0,319,'消火栓（消防炮）灭火系统'),(455,'输入输出模块',0,0,565,'消火栓（消防炮）灭火系统'),(456,'消防卷盘',0,0,572,'消火栓（消防炮）灭火系统'),(457,'消火栓启动按钮',0,0,573,'消火栓（消防炮）灭火系统'),(458,'消防水炮',0,0,574,'消火栓（消防炮）灭火系统'),(459,'通用',0,0,0,'消火栓系统'),(460,'室外消火栓',0,0,564,'消火栓系统'),(461,'通用',0,0,0,'自动喷水灭火系统'),(462,'水流指示器',0,0,96,'自动喷水灭火系统'),(463,'信号阀',0,0,97,'自动喷水灭火系统'),(464,'压力开关',0,0,99,'自动喷水灭火系统'),(465,'故障输出',0,0,142,'自动喷水灭火系统'),(466,'消防接口',0,0,289,'自动喷水灭火系统'),(467,'报警阀组',0,0,320,'自动喷水灭火系统'),(468,'干式报警阀组',0,0,321,'自动喷水灭火系统'),(469,'预作用报警阀组',0,0,322,'自动喷水灭火系统'),(470,'雨淋报警阀组',0,0,323,'自动喷水灭火系统'),(471,'洒水喷头',0,0,324,'自动喷水灭火系统'),(472,'水雾喷头',0,0,325,'自动喷水灭火系统'),(473,'扩大覆盖面积洒水喷头',0,0,326,'自动喷水灭火系统'),(474,'水幕喷头',0,0,327,'自动喷水灭火系统'),(475,'家用喷头',0,0,328,'自动喷水灭火系统'),(476,'早期抑制快速响应ESFR喷头',0,0,329,'自动喷水灭火系统'),(477,'末端试水装置',0,0,330,'自动喷水灭火系统'),(478,'消防水泵接合器',0,0,332,'自动喷水灭火系统'),(479,'消防接口',0,0,333,'自动喷水灭火系统'),(480,'泄水阀（或泄水池）',0,0,334,'自动喷水灭火系统'),(481,'排气阀（或排气口）',0,0,335,'自动喷水灭火系统'),(482,'控制阀',0,0,337,'自动喷水灭火系统'),(483,'水力警钟',0,0,338,'自动喷水灭火系统'),(484,'节流管和减压孔扳',0,0,339,'自动喷水灭火系统'),(485,'减压阀',0,0,340,'自动喷水灭火系统'),(486,'多功能水泵控制阀',0,0,341,'自动喷水灭火系统'),(487,'倒流防止器',0,0,342,'自动喷水灭火系统'),(488,'通用阀门',0,0,343,'自动喷水灭火系统'),(489,'自动寻地喷水灭火装置',0,0,344,'自动喷水灭火系统'),(490,'排污口',0,0,553,'自动喷水灭火系统');

#
# Structure for table "service_order"
#

DROP TABLE IF EXISTS `service_order`;
CREATE TABLE `service_order` (
  `ORDER_ID` varchar(255) NOT NULL,
  `ORDER_NAME` varchar(255) DEFAULT NULL,
  `ORDER_TYPE` int(11) NOT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `CREATOR` varchar(255) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CONTACT_NAME` varchar(255) DEFAULT NULL,
  `PHONE_NUM` varchar(255) DEFAULT NULL,
  `CONTACT_ADDR` varchar(255) DEFAULT NULL,
  `ORDER_STATUS` int(11) DEFAULT NULL,
  `HANDLER` varchar(255) DEFAULT NULL,
  `HANDLE_RESULT` varchar(255) DEFAULT NULL,
  `HANDLE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "service_order"
#

INSERT INTO `service_order` VALUES ('S00000028','监控摄像头配置',2,'需要给不同房间的摄像头配置特点属性','admin','2014-11-28 16:03:22','张三丰','13866774432','杭州市萧山区五一路34号',1,'admin','请参考APP设置中的帮助文档摄像头配置一项说明','2014-11-28 16:43:16'),('S00000029','hhhh',0,'','admin','2014-11-28 16:22:42','ssss','ssss','ss',1,'admin','事宜按','2014-11-28 16:41:56'),('S00000030','5555',2,'','admin','2015-01-05 17:26:27','','','',0,NULL,NULL,NULL),('S00000031','test',1,'test content!','test1','2015-01-27 13:34:14','tester','123456','test address',0,NULL,NULL,NULL);

#
# Structure for table "user_concentrator"
#

DROP TABLE IF EXISTS `user_concentrator`;
CREATE TABLE `user_concentrator` (
  `USER_ID` int(11) NOT NULL,
  `CONCENTRATOR_ID` bigint(20) NOT NULL,
  `OPERATE` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`CONCENTRATOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_concentrator"
#

INSERT INTO `user_concentrator` VALUES (1,3000000001,3),(1,3000000003,0),(1,3000000004,0),(1,3000000005,0),(1,3000000006,0),(1,3000000007,0),(1,3000000008,0),(1,3000000009,0),(2,3000000001,3),(2,3000000009,0),(3,3000000001,3),(3,3000000009,0),(4,3000000006,3);

#
# Structure for table "user_mark"
#

DROP TABLE IF EXISTS `user_mark`;
CREATE TABLE `user_mark` (
  `MARK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `MARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MARK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Data for table "user_mark"
#

INSERT INTO `user_mark` VALUES (2,2,'控制设备'),(3,2,'厨房'),(5,2,'大门口'),(6,2,'卧室'),(7,2,'客厅'),(8,2,'书房'),(9,3,'耀江商务楼');

#
# Structure for table "fire_alarm_view"
#

DROP VIEW IF EXISTS `fire_alarm_view`;
CREATE VIEW `fire_alarm_view` AS 
  select `alarm`.`ID` AS `ID`,`alarm`.`CONCENTRATOR_ID` AS `CONCENTRATOR_ID`,`alarm`.`OBJ_TYPE` AS `OBJ_TYPE`,`alarm`.`OBJ_ID` AS `OBJ_ID`,`alarm`.`ALARM_TYPE` AS `ALARM_TYPE`,`alarm`.`ALARM_TIME` AS `ALARM_TIME`,`alarm`.`CLEAR_STATUS` AS `CLEAR_STATUS`,`alarm`.`DATA_VALUE` AS `DATA_VALUE`,`alarm`.`CLEAR_USER` AS `CLEAR_USER`,`alarm`.`CLEAR_TIME` AS `CLEAR_TIME`,`concentrator`.`STATUS` AS `STATUS`,`concentrator`.`DESCRIPTION` AS `CONCENT_DESP`,`fire_sensor`.`MACHINE_ID` AS `MACHINE_ID`,`fire_sensor`.`LOOP_ID` AS `LOOP_ID`,`fire_sensor`.`CODE_ID` AS `CODE_ID`,`fire_sensor`.`LOCATION_DESP` AS `LOCATION_DESP`,`fire_sensor`.`LOCATION_X` AS `LOCATION_X`,`fire_sensor`.`LOCATION_Y` AS `LOCATION_Y`,`fire_sensor`.`SENSOR_TYPE` AS `SENSOR_TYPE`,`fire_sensor`.`SENSOR_TYPE_NAME` AS `SENSOR_TYPE_NAME` from ((`alarm` left join `concentrator` on((`alarm`.`CONCENTRATOR_ID` = `concentrator`.`CONCENTRATOR_ID`))) left join `fire_sensor` on(((`alarm`.`CONCENTRATOR_ID` = `fire_sensor`.`CONCENTRATOR_ID`) and (`alarm`.`OBJ_ID` = `fire_sensor`.`MACHINE_ID`) and (`alarm`.`OBJ_ID` = `fire_sensor`.`LOOP_ID`) and (`alarm`.`OBJ_ID` = `fire_sensor`.`CODE_ID`)))) where ((`alarm`.`OBJ_TYPE` = 0) or (`alarm`.`OBJ_TYPE` = 2));

#
# Structure for table "home_alarm_view"
#

DROP VIEW IF EXISTS `home_alarm_view`;
CREATE VIEW `home_alarm_view` AS 
  select `alarm`.`ID` AS `ID`,`alarm`.`CONCENTRATOR_ID` AS `CONCENTRATOR_ID`,`alarm`.`OBJ_TYPE` AS `OBJ_TYPE`,`alarm`.`OBJ_ID` AS `OBJ_ID`,`alarm`.`ALARM_TYPE` AS `ALARM_TYPE`,`alarm`.`ALARM_TIME` AS `ALARM_TIME`,`alarm`.`CLEAR_STATUS` AS `CLEAR_STATUS`,`alarm`.`DATA_VALUE` AS `DATA_VALUE`,`alarm`.`CLEAR_USER` AS `CLEAR_USER`,`alarm`.`CLEAR_TIME` AS `CLEAR_TIME`,`concentrator`.`STATUS` AS `STATUS`,`concentrator`.`DESCRIPTION` AS `CONCENT_DESP`,`home_sensor`.`SENSOR_TYPE` AS `SENSOR_TYPE`,`home_sensor`.`SENSOR_TYPE_NAME` AS `SENSOR_TYPE_NAME`,`home_sensor`.`DESCRIPTION` AS `SENSOR_DESP` from ((`alarm` left join `concentrator` on((`alarm`.`CONCENTRATOR_ID` = `concentrator`.`CONCENTRATOR_ID`))) left join `home_sensor` on(((`alarm`.`CONCENTRATOR_ID` = `home_sensor`.`CONCENTRATOR_ID`) and (`alarm`.`OBJ_ID` = `home_sensor`.`SENSOR_ID`) and (`alarm`.`OBJ_ID1` = `home_sensor`.`CHANNEL_ID`)))) where ((`alarm`.`OBJ_TYPE` = 0) or (`alarm`.`OBJ_TYPE` = 1));
