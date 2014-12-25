# Host: 127.0.0.1  (Version: 5.1.70-community)
# Date: 2014-12-25 10:31:32
# Generator: MySQL-Front 5.3  (Build 4.156)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "alarm"
#

DROP TABLE IF EXISTS `alarm`;
CREATE TABLE `alarm` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONCENTRATOR_ID` int(11) DEFAULT NULL,
  `OBJ_TYPE` int(11) DEFAULT NULL,
  `OBJ_ID` int(11) DEFAULT NULL,
  `ALARM_TYPE` int(11) DEFAULT NULL,
  `DATA_VALUE` float DEFAULT NULL,
  `ALARM_TIME` datetime DEFAULT NULL,
  `CLEAR_USER` varchar(255) DEFAULT NULL,
  `CLEAR_STATUS` int(11) DEFAULT NULL,
  `CLEAR_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "alarm"
#


#
# Structure for table "concentrator"
#

DROP TABLE IF EXISTS `concentrator`;
CREATE TABLE `concentrator` (
  `CONCENTRATOR_ID` int(11) NOT NULL,
  `IP_ADDR` varchar(255) NOT NULL,
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

INSERT INTO `concentrator` VALUES (2,'127.0.0.1',0,NULL,NULL,'家庭用户','阳光花园1栋A座705',NULL,NULL),(100001,'192.168.1.100',0,NULL,NULL,'深圳市第二人民医院','ff订单',NULL,NULL),(100003,'192.168.1.102',0,114.083,22.5891,'银湖国际会议中心','地点33',NULL,NULL),(100004,'192.168.1.103',1,113.986,22.5629,'桃源村综合楼',NULL,NULL,NULL);

#
# Structure for table "fire_sensor"
#

DROP TABLE IF EXISTS `fire_sensor`;
CREATE TABLE `fire_sensor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONCENTRATOR_ID` int(11) DEFAULT NULL,
  `MACHINE_ID` int(11) DEFAULT NULL,
  `LOOP_ID` int(11) DEFAULT NULL,
  `CODE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "fire_sensor"
#


#
# Structure for table "home_sensor"
#

DROP TABLE IF EXISTS `home_sensor`;
CREATE TABLE `home_sensor` (
  `ID` int(11) NOT NULL,
  `CONCENTRATOR_ID` int(11) DEFAULT NULL,
  `SENSOR_ID` int(11) DEFAULT NULL,
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
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "home_sensor"
#

INSERT INTO `home_sensor` VALUES (1,2,2,1,1,1,'温度传感器',1,0,0,0,'12','监控客厅温度','客厅'),(2,2,1,1,0,1,'煤气传感器',1,0,0,0,'12','监控煤气浓度gggg','卧室'),(3,2,1,1,0,1,'烟雾报警器',0,0,0,0,'12','监控插线板','厨房'),(11,2,11,11,2,2,'空调控制器',1,1,1,10,'0','控制空调关闭','客厅'),(12,2,12,12,2,2,'窗户控制器',1,1,1,10,'0','控制厨房窗户开闭','厨房'),(13,2,13,13,2,2,'喷水控制器',0,1,1,10,'0','控制卧室火警消除','卧室');

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
) ENGINE=InnoDB AUTO_INCREMENT=468 DEFAULT CHARSET=utf8;

#
# Data for table "misp_oper_log"
#

INSERT INTO `misp_oper_log` VALUES (82,'admin','登录',NULL,'成功',NULL,'2014-11-24 11:28:54'),(83,'admin','登录',NULL,'成功',NULL,'2014-11-24 11:51:37'),(84,'admin','登录',NULL,'成功',NULL,'2014-11-24 11:51:37'),(85,'admin','登录',NULL,'成功',NULL,'2014-11-24 12:03:40'),(86,'admin','登录',NULL,'成功',NULL,'2014-11-24 12:24:19'),(87,'admin','登录',NULL,'成功',NULL,'2014-11-24 12:37:46'),(88,'admin','登录',NULL,'成功',NULL,'2014-11-24 12:46:12'),(89,'admin','登录',NULL,'成功',NULL,'2014-11-24 12:46:12'),(90,'admin','登录',NULL,'成功',NULL,'2014-11-24 12:46:12'),(91,'admin','登录',NULL,'成功',NULL,'2014-11-24 12:48:15'),(92,'admin','登录',NULL,'成功',NULL,'2014-11-24 12:48:15'),(93,'admin','登录',NULL,'成功',NULL,'2014-11-24 14:13:49'),(94,'admin','登录',NULL,'成功',NULL,'2014-11-24 18:21:50'),(95,'admin','登录',NULL,'成功',NULL,'2014-11-24 18:21:50'),(96,'admin','登录',NULL,'成功',NULL,'2014-11-24 18:45:48'),(97,'admin','登录',NULL,'成功',NULL,'2014-11-24 21:51:03'),(98,'admin','登录',NULL,'成功',NULL,'2014-11-24 21:51:03'),(99,'admin','登录',NULL,'成功',NULL,'2014-11-24 22:03:39'),(100,'admin','登录',NULL,'成功',NULL,'2014-11-24 22:20:51'),(101,'admin','登录',NULL,'成功',NULL,'2014-11-24 22:20:51'),(102,'admin','登录',NULL,'成功',NULL,'2014-11-24 22:22:57'),(103,'admin','登录',NULL,'成功',NULL,'2014-11-24 22:31:38'),(104,'admin','登录',NULL,'成功',NULL,'2014-11-24 22:31:41'),(105,'admin','登录',NULL,'成功',NULL,'2014-11-24 22:32:06'),(106,'admin','登录',NULL,'成功',NULL,'2014-11-24 22:32:06'),(107,'admin','登录',NULL,'成功',NULL,'2014-11-25 00:02:17'),(108,'admin','修改密码',NULL,'成功',NULL,'2014-11-25 00:02:51'),(109,'admin','登录',NULL,'成功',NULL,'2014-11-25 00:15:32'),(110,'admin','登录',NULL,'成功',NULL,'2014-11-25 00:18:40'),(111,'admin','修改密码',NULL,'成功',NULL,'2014-11-25 00:19:07'),(112,'admin','登录',NULL,'成功',NULL,'2014-11-25 00:24:01'),(113,'admin','登录',NULL,'成功',NULL,'2014-11-25 00:25:53'),(114,'admin','登录',NULL,'成功',NULL,'2014-11-25 00:28:35'),(115,'admin','修改密码',NULL,'成功',NULL,'2014-11-25 00:29:13'),(116,'admin','登录',NULL,'成功',NULL,'2014-11-25 00:29:33'),(117,'admin','登录',NULL,'成功',NULL,'2014-11-25 09:22:16'),(118,'admin','登录',NULL,'成功',NULL,'2014-11-25 11:18:40'),(119,'admin','登录',NULL,'成功',NULL,'2014-11-25 11:19:57'),(120,'admin','登录',NULL,'成功',NULL,'2014-11-25 11:24:25'),(121,'admin','登录',NULL,'成功',NULL,'2014-11-25 11:28:59'),(122,'admin','登录',NULL,'成功',NULL,'2014-11-25 11:32:34'),(123,'admin','登录',NULL,'成功',NULL,'2014-11-25 11:46:24'),(124,'admin','登录',NULL,'成功',NULL,'2014-11-25 11:48:13'),(125,'admin','登录',NULL,'成功',NULL,'2014-11-25 11:49:16'),(126,'admin','修改密码',NULL,'成功',NULL,'2014-11-25 11:49:44'),(127,'admin','登录',NULL,'成功',NULL,'2014-11-25 13:29:27'),(128,'admin','修改密码',NULL,'成功',NULL,'2014-11-25 13:29:54'),(129,'admin','登录',NULL,'成功',NULL,'2014-11-25 14:07:27'),(130,'admin','登录',NULL,'成功',NULL,'2014-11-25 14:09:07'),(131,'admin','登录',NULL,'成功',NULL,'2014-11-25 14:32:32'),(132,'admin','登录',NULL,'成功',NULL,'2014-11-25 14:59:02'),(133,'admin','登录',NULL,'成功',NULL,'2014-11-25 15:02:55'),(134,'admin','登录',NULL,'成功',NULL,'2014-11-25 15:11:04'),(135,'admin','登录',NULL,'成功',NULL,'2014-11-25 15:11:52'),(136,'admin','登录',NULL,'成功',NULL,'2014-11-25 16:29:29'),(137,'admin','登录',NULL,'成功',NULL,'2014-11-25 16:29:29'),(138,'admin','修改密码',NULL,'成功',NULL,'2014-11-25 16:30:13'),(139,'admin','登录',NULL,'成功',NULL,'2014-11-25 16:30:25'),(140,'admin','登录',NULL,'成功',NULL,'2014-11-25 16:32:53'),(141,'admin','登录',NULL,'成功',NULL,'2014-11-25 17:00:42'),(142,'admin','登录',NULL,'成功',NULL,'2014-11-25 17:16:46'),(143,'admin','登录',NULL,'成功',NULL,'2014-11-25 20:57:24'),(144,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:04:52'),(145,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:14:45'),(146,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:17:26'),(147,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:24:45'),(148,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:30:37'),(149,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:35:15'),(150,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:44:24'),(151,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:46:37'),(152,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:49:55'),(153,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:51:45'),(154,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:54:33'),(155,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:57:09'),(156,'admin','登录',NULL,'成功',NULL,'2014-11-25 23:59:40'),(157,'admin','登录',NULL,'成功',NULL,'2014-11-26 00:17:49'),(158,'admin','登录',NULL,'成功',NULL,'2014-11-26 00:21:47'),(159,'admin','登录',NULL,'成功',NULL,'2014-11-26 00:30:07'),(160,'admin','登录',NULL,'成功',NULL,'2014-11-26 00:38:47'),(161,'admin','登录',NULL,'成功',NULL,'2014-11-26 10:58:43'),(162,'admin','登录',NULL,'成功',NULL,'2014-11-26 11:11:19'),(163,'admin','登录',NULL,'成功',NULL,'2014-11-26 11:13:14'),(164,'admin','登录',NULL,'成功',NULL,'2014-11-26 11:54:56'),(165,'admin','登录',NULL,'成功',NULL,'2014-11-26 12:13:01'),(166,'admin','登录',NULL,'成功',NULL,'2014-11-26 12:43:11'),(167,'admin','登录',NULL,'成功',NULL,'2014-11-26 12:51:52'),(168,'admin','登录',NULL,'成功',NULL,'2014-11-26 14:47:18'),(169,'admin','登录',NULL,'成功',NULL,'2014-11-26 15:06:48'),(170,'admin','登录',NULL,'成功',NULL,'2014-11-26 15:49:09'),(171,'admin','登录',NULL,'成功',NULL,'2014-11-26 16:24:11'),(172,'admin','登录',NULL,'成功',NULL,'2014-11-26 16:45:01'),(173,'admin','登录',NULL,'成功',NULL,'2014-11-26 17:08:57'),(174,'admin','登录',NULL,'成功',NULL,'2014-11-26 18:05:22'),(175,'admin','登录',NULL,'成功',NULL,'2014-11-26 22:26:10'),(176,'admin','登录',NULL,'成功',NULL,'2014-11-26 23:10:21'),(177,'admin','登录',NULL,'成功',NULL,'2014-11-26 23:20:48'),(178,'admin','登录',NULL,'成功',NULL,'2014-11-26 23:26:23'),(179,'admin','登录',NULL,'成功',NULL,'2014-11-26 23:31:22'),(180,'admin','登录',NULL,'成功',NULL,'2014-11-27 00:43:15'),(181,'admin','登录',NULL,'成功',NULL,'2014-11-27 10:48:54'),(182,'admin','登录',NULL,'成功',NULL,'2014-11-27 11:03:18'),(183,'admin','登录',NULL,'成功',NULL,'2014-11-27 11:55:48'),(184,'admin','登录',NULL,'成功',NULL,'2014-11-27 12:13:40'),(185,'admin','登录',NULL,'成功',NULL,'2014-11-27 12:13:40'),(186,'admin','登录',NULL,'成功',NULL,'2014-11-27 12:18:15'),(187,'admin','登录',NULL,'成功',NULL,'2014-11-27 12:21:41'),(188,'admin','登录',NULL,'成功',NULL,'2014-11-27 13:08:57'),(189,'admin','登录',NULL,'成功',NULL,'2014-11-27 14:06:41'),(190,'admin','登录',NULL,'成功',NULL,'2014-11-27 15:19:07'),(191,'admin','登录',NULL,'成功',NULL,'2014-11-27 15:35:40'),(192,'admin','登录',NULL,'成功',NULL,'2014-11-27 15:44:00'),(193,'admin','登录',NULL,'成功',NULL,'2014-11-27 16:11:10'),(194,'admin','登录',NULL,'成功',NULL,'2014-11-27 16:32:52'),(195,'admin','登录',NULL,'成功',NULL,'2014-11-27 16:41:47'),(196,'admin','登录',NULL,'成功',NULL,'2014-11-27 17:14:18'),(197,'admin','登录',NULL,'成功',NULL,'2014-11-27 17:17:24'),(198,'admin','登录',NULL,'成功',NULL,'2014-11-27 17:31:50'),(199,'admin','登录',NULL,'成功',NULL,'2014-11-27 17:53:52'),(200,'admin','登录',NULL,'成功',NULL,'2014-11-27 18:00:49'),(201,'admin','登录',NULL,'成功',NULL,'2014-11-27 18:37:18'),(202,'admin','登录',NULL,'成功',NULL,'2014-11-27 18:40:41'),(203,'admin','登录',NULL,'成功',NULL,'2014-11-27 21:16:02'),(204,'admin','登录',NULL,'成功',NULL,'2014-11-27 21:23:45'),(205,'admin','登录',NULL,'成功',NULL,'2014-11-27 23:03:23'),(206,'admin','登录',NULL,'成功',NULL,'2014-11-28 00:24:09'),(207,'admin','登录',NULL,'成功',NULL,'2014-11-28 09:38:27'),(208,'admin','登录',NULL,'成功',NULL,'2014-11-28 11:00:51'),(209,'admin','登录',NULL,'成功',NULL,'2014-11-28 11:13:17'),(210,'admin','登录',NULL,'成功',NULL,'2014-11-28 11:23:11'),(211,'admin','登录',NULL,'成功',NULL,'2014-11-28 11:39:23'),(212,'admin','登录',NULL,'成功',NULL,'2014-11-28 14:01:38'),(213,'admin','登录',NULL,'成功',NULL,'2014-11-28 14:41:51'),(214,'admin','登录',NULL,'成功',NULL,'2014-11-28 15:09:03'),(215,'admin','登录',NULL,'成功',NULL,'2014-11-28 15:14:51'),(216,'admin','登录',NULL,'成功',NULL,'2014-11-28 15:19:42'),(217,'admin','登录',NULL,'成功',NULL,'2014-11-28 15:40:00'),(218,'admin','登录',NULL,'成功',NULL,'2014-11-28 15:51:19'),(219,'admin','登录',NULL,'成功',NULL,'2014-11-28 16:24:47'),(220,'admin','登录',NULL,'成功',NULL,'2014-11-28 17:03:28'),(221,'admin','登录',NULL,'成功',NULL,'2014-11-28 17:30:02'),(222,'admin','登录',NULL,'成功',NULL,'2014-11-28 17:53:55'),(223,'admin','登录',NULL,'成功',NULL,'2014-11-28 17:56:08'),(224,'admin','登录',NULL,'成功',NULL,'2014-11-28 18:05:26'),(225,'admin','登录',NULL,'成功',NULL,'2014-11-28 18:39:29'),(226,'admin','登录',NULL,'成功',NULL,'2014-11-29 13:35:10'),(227,'admin','登录',NULL,'成功',NULL,'2014-11-29 13:58:48'),(228,'admin','登录',NULL,'成功',NULL,'2014-11-29 15:26:36'),(229,'admin','登录',NULL,'成功',NULL,'2014-11-29 15:33:16'),(230,'admin','登录',NULL,'成功',NULL,'2014-11-29 15:38:43'),(231,'admin','登录',NULL,'成功',NULL,'2014-11-29 17:13:23'),(232,'普通管理员1','登录',NULL,'成功',NULL,'2014-11-29 17:20:39'),(233,'admin','登录',NULL,'成功',NULL,'2014-11-29 17:34:46'),(234,'普通管理员1','登录',NULL,'成功',NULL,'2014-11-29 17:36:47'),(235,'admin','登录',NULL,'成功',NULL,'2014-11-29 17:49:23'),(236,'普通管理员1','登录',NULL,'成功',NULL,'2014-11-29 17:56:22'),(237,'区域管理员1','登录',NULL,'成功',NULL,'2014-11-29 18:16:48'),(238,'admin','登录',NULL,'成功',NULL,'2014-11-29 18:18:39'),(239,'admin','登录',NULL,'成功',NULL,'2014-12-02 18:01:38'),(240,'admin','登录',NULL,'成功',NULL,'2014-12-04 14:24:48'),(241,'2','登录',NULL,'成功',NULL,'2014-12-08 23:42:40'),(242,'2','登录',NULL,'成功',NULL,'2014-12-09 00:26:34'),(243,'admin','登录',NULL,'成功',NULL,'2014-12-09 00:27:15'),(244,'admin','登录',NULL,'成功',NULL,'2014-12-09 00:28:26'),(245,'admin','登录',NULL,'成功',NULL,'2014-12-09 00:32:00'),(246,'admin','登录',NULL,'成功',NULL,'2014-12-09 00:33:34'),(247,'admin','登录',NULL,'成功',NULL,'2014-12-09 09:28:33'),(248,'admin','登录',NULL,'成功',NULL,'2014-12-09 09:31:58'),(249,'admin','登录',NULL,'成功',NULL,'2014-12-09 09:35:34'),(250,'admin','登录',NULL,'成功',NULL,'2014-12-09 09:39:50'),(251,'admin','登录',NULL,'成功',NULL,'2014-12-09 10:00:43'),(252,'admin','登录',NULL,'成功',NULL,'2014-12-09 10:03:34'),(253,'admin','登录',NULL,'成功',NULL,'2014-12-09 10:16:59'),(254,'admin','登录',NULL,'成功',NULL,'2014-12-09 10:24:27'),(255,'admin','登录',NULL,'成功',NULL,'2014-12-09 10:26:42'),(256,'admin','登录',NULL,'成功',NULL,'2014-12-09 10:43:57'),(257,'admin','登录',NULL,'成功',NULL,'2014-12-09 10:47:47'),(258,'admin','登录',NULL,'成功',NULL,'2014-12-09 11:04:53'),(259,'admin','登录',NULL,'成功',NULL,'2014-12-09 11:09:33'),(260,'admin','登录',NULL,'成功',NULL,'2014-12-09 11:14:59'),(261,'admin','登录',NULL,'成功',NULL,'2014-12-09 12:26:44'),(262,'admin','登录',NULL,'成功',NULL,'2014-12-09 14:16:51'),(263,'admin','登录',NULL,'成功',NULL,'2014-12-09 14:53:25'),(264,'admin','登录',NULL,'成功',NULL,'2014-12-09 14:59:42'),(265,'admin','登录',NULL,'成功',NULL,'2014-12-09 15:28:53'),(266,'admin','登录',NULL,'成功',NULL,'2014-12-09 15:33:45'),(267,'admin','登录',NULL,'成功',NULL,'2014-12-09 16:48:05'),(268,'admin','登录',NULL,'成功',NULL,'2014-12-09 17:44:33'),(269,'admin','登录',NULL,'成功',NULL,'2014-12-09 17:49:44'),(270,'admin','登录',NULL,'成功',NULL,'2014-12-09 18:20:16'),(271,'admin','登录',NULL,'成功',NULL,'2014-12-09 18:22:23'),(272,'admin','登录',NULL,'成功',NULL,'2014-12-09 18:24:04'),(273,'admin','登录',NULL,'成功',NULL,'2014-12-09 18:36:14'),(274,'admin','登录',NULL,'成功',NULL,'2014-12-09 18:38:30'),(275,'admin','登录',NULL,'成功',NULL,'2014-12-09 18:47:16'),(276,'admin','登录',NULL,'成功',NULL,'2014-12-09 18:55:04'),(277,'admin','登录',NULL,'成功',NULL,'2014-12-09 18:56:28'),(278,'admin','登录',NULL,'成功',NULL,'2014-12-09 18:58:50'),(279,'admin','登录',NULL,'成功',NULL,'2014-12-09 21:33:03'),(280,'admin','登录',NULL,'成功',NULL,'2014-12-09 22:52:00'),(281,'admin','登录',NULL,'成功',NULL,'2014-12-09 22:56:53'),(282,'admin','登录',NULL,'成功',NULL,'2014-12-10 00:34:24'),(283,'admin','登录',NULL,'成功',NULL,'2014-12-10 00:34:30'),(284,'admin','登录',NULL,'成功',NULL,'2014-12-10 09:10:03'),(285,'admin','登录',NULL,'成功',NULL,'2014-12-10 09:13:27'),(286,'admin','登录',NULL,'成功',NULL,'2014-12-10 09:28:36'),(287,'admin','登录',NULL,'成功',NULL,'2014-12-10 09:38:24'),(288,'admin','登录',NULL,'成功',NULL,'2014-12-10 09:44:14'),(289,'admin','登录',NULL,'成功',NULL,'2014-12-10 09:46:42'),(290,'admin','登录',NULL,'成功',NULL,'2014-12-10 09:56:16'),(291,'admin','登录',NULL,'成功',NULL,'2014-12-10 10:05:11'),(292,'admin','登录',NULL,'成功',NULL,'2014-12-10 10:07:26'),(293,'admin','登录',NULL,'成功',NULL,'2014-12-10 10:14:42'),(294,'admin','登录',NULL,'成功',NULL,'2014-12-10 11:36:02'),(295,'admin','登录',NULL,'成功',NULL,'2014-12-10 11:39:29'),(296,'admin','登录',NULL,'成功',NULL,'2014-12-10 11:42:11'),(297,'admin','登录',NULL,'成功',NULL,'2014-12-10 11:44:53'),(298,'admin','登录',NULL,'成功',NULL,'2014-12-10 11:47:23'),(299,'admin','登录',NULL,'成功',NULL,'2014-12-10 11:48:55'),(300,'admin','登录',NULL,'成功',NULL,'2014-12-11 14:47:01'),(301,'admin','登录',NULL,'成功',NULL,'2014-12-11 16:30:10'),(302,'admin','登录',NULL,'成功',NULL,'2014-12-11 16:37:43'),(303,'admin','登录',NULL,'成功',NULL,'2014-12-11 17:23:32'),(304,'admin','登录',NULL,'成功',NULL,'2014-12-11 17:44:55'),(305,'admin','登录',NULL,'成功',NULL,'2014-12-11 17:46:29'),(306,'admin','登录',NULL,'成功',NULL,'2014-12-11 17:50:44'),(307,'admin','登录',NULL,'成功',NULL,'2014-12-11 17:52:10'),(308,'admin','登录',NULL,'成功',NULL,'2014-12-11 17:53:05'),(309,'admin','登录',NULL,'成功',NULL,'2014-12-11 17:58:34'),(310,'admin','登录',NULL,'成功',NULL,'2014-12-11 17:59:38'),(311,'admin','登录',NULL,'成功',NULL,'2014-12-11 18:02:49'),(312,'admin','登录',NULL,'成功',NULL,'2014-12-11 18:05:36'),(313,'admin','登录',NULL,'成功',NULL,'2014-12-11 18:06:13'),(314,'admin','登录',NULL,'成功',NULL,'2014-12-11 18:30:16'),(315,'admin','登录',NULL,'成功',NULL,'2014-12-11 18:30:59'),(316,'admin','登录',NULL,'成功',NULL,'2014-12-11 18:37:41'),(317,'admin','登录',NULL,'成功',NULL,'2014-12-11 18:41:00'),(318,'admin','登录',NULL,'成功',NULL,'2014-12-11 18:43:20'),(319,'admin','登录',NULL,'成功',NULL,'2014-12-11 18:51:07'),(320,'admin','登录',NULL,'成功',NULL,'2014-12-11 19:09:21'),(321,'admin','登录',NULL,'成功',NULL,'2014-12-11 19:11:15'),(322,'admin','登录',NULL,'成功',NULL,'2014-12-12 10:00:41'),(323,'admin','登录',NULL,'成功',NULL,'2014-12-12 11:28:15'),(324,'admin','登录',NULL,'成功',NULL,'2014-12-12 11:41:58'),(325,'admin','登录',NULL,'成功',NULL,'2014-12-12 13:30:13'),(326,'admin','登录',NULL,'成功',NULL,'2014-12-12 14:16:34'),(327,'admin','登录',NULL,'成功',NULL,'2014-12-12 17:10:40'),(328,'admin','登录',NULL,'成功',NULL,'2014-12-15 16:23:43'),(329,'admin','登录',NULL,'成功',NULL,'2014-12-15 17:27:42'),(330,'admin','登录',NULL,'成功',NULL,'2014-12-15 17:31:50'),(331,'admin','登录',NULL,'成功',NULL,'2014-12-15 17:33:23'),(332,'admin','登录',NULL,'成功',NULL,'2014-12-15 17:35:03'),(333,'admin','登录',NULL,'成功',NULL,'2014-12-15 17:41:01'),(334,'admin','登录',NULL,'成功',NULL,'2014-12-15 17:45:57'),(335,'admin','登录',NULL,'成功',NULL,'2014-12-15 17:48:03'),(336,'admin','登录',NULL,'成功',NULL,'2014-12-15 17:49:35'),(337,'admin','登录',NULL,'成功',NULL,'2014-12-15 17:52:01'),(338,'admin','登录',NULL,'成功',NULL,'2014-12-15 18:03:48'),(339,'admin','登录',NULL,'成功',NULL,'2014-12-15 21:39:01'),(340,'admin','登录',NULL,'成功',NULL,'2014-12-15 22:26:56'),(341,'admin','登录',NULL,'成功',NULL,'2014-12-16 00:25:15'),(342,'admin','登录',NULL,'成功',NULL,'2014-12-16 09:21:08'),(343,'admin','登录',NULL,'成功',NULL,'2014-12-16 15:46:21'),(344,'admin','登录',NULL,'成功',NULL,'2014-12-16 15:46:49'),(345,'admin','登录',NULL,'成功',NULL,'2014-12-16 15:48:37'),(346,'admin','登录',NULL,'成功',NULL,'2014-12-16 15:55:05'),(347,'admin','登录',NULL,'成功',NULL,'2014-12-16 16:03:14'),(348,'admin','登录',NULL,'成功',NULL,'2014-12-16 16:25:22'),(349,'admin','登录',NULL,'成功',NULL,'2014-12-16 16:27:13'),(350,'admin','登录',NULL,'成功',NULL,'2014-12-16 17:18:11'),(351,'admin','登录',NULL,'成功',NULL,'2014-12-16 17:57:33'),(352,'admin','登录',NULL,'成功',NULL,'2014-12-16 17:57:51'),(353,'admin','登录',NULL,'成功',NULL,'2014-12-16 18:01:33'),(354,'admin','登录',NULL,'成功',NULL,'2014-12-16 18:25:47'),(355,'admin','登录',NULL,'成功',NULL,'2014-12-16 18:27:20'),(356,'admin','登录',NULL,'成功',NULL,'2014-12-16 18:28:21'),(357,'admin','登录',NULL,'成功',NULL,'2014-12-16 18:51:26'),(358,'admin','登录',NULL,'成功',NULL,'2014-12-16 18:59:11'),(359,'admin','登录',NULL,'成功',NULL,'2014-12-16 19:01:32'),(360,'admin','登录',NULL,'成功',NULL,'2014-12-16 19:05:17'),(361,'admin','登录',NULL,'成功',NULL,'2014-12-16 21:33:35'),(362,'admin','登录',NULL,'成功',NULL,'2014-12-16 23:32:01'),(363,'admin','登录',NULL,'成功',NULL,'2014-12-16 23:32:30'),(364,'admin','登录',NULL,'成功',NULL,'2014-12-16 23:37:02'),(365,'admin','登录',NULL,'成功',NULL,'2014-12-16 23:43:53'),(366,'admin','登录',NULL,'成功',NULL,'2014-12-16 23:46:55'),(367,'admin','登录',NULL,'成功',NULL,'2014-12-16 23:49:02'),(368,'admin','登录',NULL,'成功',NULL,'2014-12-16 23:50:20'),(369,'admin','登录',NULL,'成功',NULL,'2014-12-16 23:53:38'),(370,'admin','登录',NULL,'成功',NULL,'2014-12-16 23:56:03'),(371,'admin','登录',NULL,'成功',NULL,'2014-12-16 23:58:37'),(372,'admin','登录',NULL,'成功',NULL,'2014-12-17 00:01:33'),(373,'admin','登录',NULL,'成功',NULL,'2014-12-17 00:09:41'),(374,'admin','登录',NULL,'成功',NULL,'2014-12-17 00:18:17'),(375,'admin','登录',NULL,'成功',NULL,'2014-12-17 00:19:36'),(376,'admin','登录',NULL,'成功',NULL,'2014-12-17 09:42:47'),(377,'admin','登录',NULL,'成功',NULL,'2014-12-17 09:46:05'),(378,'admin','登录',NULL,'成功',NULL,'2014-12-17 10:27:44'),(379,'admin','登录',NULL,'成功',NULL,'2014-12-17 10:28:29'),(380,'admin','登录',NULL,'成功',NULL,'2014-12-17 10:45:22'),(381,'admin','登录',NULL,'成功',NULL,'2014-12-17 11:56:48'),(382,'admin','登录',NULL,'成功',NULL,'2014-12-17 12:00:10'),(383,'admin','登录',NULL,'成功',NULL,'2014-12-17 14:02:35'),(384,'admin','登录',NULL,'成功',NULL,'2014-12-17 14:20:29'),(385,'admin','登录',NULL,'成功',NULL,'2014-12-17 14:27:28'),(386,'admin','登录',NULL,'成功',NULL,'2014-12-17 15:12:17'),(387,'admin','登录',NULL,'成功',NULL,'2014-12-17 16:00:16'),(388,'admin','登录',NULL,'成功',NULL,'2014-12-17 16:09:10'),(389,'admin','登录',NULL,'成功',NULL,'2014-12-17 16:15:38'),(390,'admin','登录',NULL,'成功',NULL,'2014-12-17 16:35:25'),(391,'admin','登录',NULL,'成功',NULL,'2014-12-17 16:41:17'),(392,'admin','登录',NULL,'成功',NULL,'2014-12-17 16:51:42'),(393,'admin','登录',NULL,'成功',NULL,'2014-12-17 16:52:59'),(394,'admin','登录',NULL,'成功',NULL,'2014-12-17 16:56:49'),(395,'admin','登录',NULL,'成功',NULL,'2014-12-17 16:58:18'),(396,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:02:45'),(397,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:05:08'),(398,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:06:53'),(399,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:11:05'),(400,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:12:45'),(401,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:17:55'),(402,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:23:08'),(403,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:25:15'),(404,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:26:05'),(405,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:31:26'),(406,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:39:45'),(407,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:45:43'),(408,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:48:05'),(409,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:52:20'),(410,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:53:55'),(411,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:57:26'),(412,'admin','登录',NULL,'成功',NULL,'2014-12-17 17:59:05'),(413,'admin','登录',NULL,'成功',NULL,'2014-12-17 18:03:31'),(414,'admin','登录',NULL,'成功',NULL,'2014-12-17 18:05:58'),(415,'admin','登录',NULL,'成功',NULL,'2014-12-17 18:27:31'),(416,'admin','登录',NULL,'成功',NULL,'2014-12-18 11:22:41'),(417,'admin','登录',NULL,'成功',NULL,'2014-12-18 11:31:35'),(418,'admin','登录',NULL,'成功',NULL,'2014-12-18 11:40:23'),(419,'admin','登录',NULL,'成功',NULL,'2014-12-18 11:49:49'),(420,'admin','登录',NULL,'成功',NULL,'2014-12-18 11:51:44'),(421,'admin','登录',NULL,'成功',NULL,'2014-12-18 11:55:26'),(422,'admin','登录',NULL,'成功',NULL,'2014-12-18 14:56:52'),(423,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:00:37'),(424,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:17:22'),(425,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:20:35'),(426,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:23:25'),(427,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:24:06'),(428,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:28:11'),(429,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:31:33'),(430,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:33:15'),(431,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:39:43'),(432,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:41:50'),(433,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:43:19'),(434,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:54:08'),(435,'admin','登录',NULL,'成功',NULL,'2014-12-18 15:58:05'),(436,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:06:35'),(437,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:10:32'),(438,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:15:55'),(439,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:17:53'),(440,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:19:36'),(441,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:21:31'),(442,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:24:13'),(443,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:29:00'),(444,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:31:29'),(445,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:37:38'),(446,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:39:27'),(447,'admin','登录',NULL,'成功',NULL,'2014-12-18 16:49:32'),(448,'admin','登录',NULL,'成功',NULL,'2014-12-24 17:12:04'),(449,'admin','登录',NULL,'成功',NULL,'2014-12-24 18:02:57'),(450,'admin','登录',NULL,'成功',NULL,'2014-12-24 18:20:26'),(451,'admin','登录',NULL,'成功',NULL,'2014-12-24 18:22:54'),(452,'admin','登录',NULL,'成功',NULL,'2014-12-24 18:23:18'),(453,'admin','登录',NULL,'成功',NULL,'2014-12-24 21:25:16'),(454,'admin','登录',NULL,'成功',NULL,'2014-12-24 21:27:21'),(455,'admin','登录',NULL,'成功',NULL,'2014-12-24 21:32:56'),(456,'admin','登录',NULL,'成功',NULL,'2014-12-24 21:33:23'),(457,'admin','登录',NULL,'成功',NULL,'2014-12-24 21:40:34'),(458,'admin','登录',NULL,'成功',NULL,'2014-12-24 21:42:21'),(459,'admin','登录',NULL,'成功',NULL,'2014-12-24 21:56:51'),(460,'admin','登录',NULL,'成功',NULL,'2014-12-24 21:57:21'),(461,'admin','登录',NULL,'成功',NULL,'2014-12-24 22:43:55'),(462,'admin','登录',NULL,'成功',NULL,'2014-12-24 22:53:48'),(463,'admin','登录',NULL,'成功',NULL,'2014-12-25 09:27:01'),(464,'admin','登录',NULL,'成功',NULL,'2014-12-25 09:29:27'),(465,'admin','登录',NULL,'成功',NULL,'2014-12-25 09:35:44'),(466,'admin','登录',NULL,'成功',NULL,'2014-12-25 09:41:00'),(467,'admin','登录',NULL,'成功',NULL,'2014-12-25 09:43:58');

#
# Structure for table "misp_privilege"
#

DROP TABLE IF EXISTS `misp_privilege`;
CREATE TABLE `misp_privilege` (
  `ID` int(11) NOT NULL,
  `master_type` varchar(20) NOT NULL,
  `master_value` varchar(255) NOT NULL,
  `access_obj_type` varchar(20) NOT NULL,
  `access_obj_value` varchar(50) NOT NULL,
  `operation` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "misp_privilege"
#

INSERT INTO `misp_privilege` VALUES (1,'ROLE','99','MENU','1001',NULL),(2,'ROLE','99','MENU','1002',NULL),(3,'ROLE','99','MENU','1003',NULL),(4,'ROLE','99','MENU','1004',NULL),(5,'ROLE','99','MENU','1005',NULL),(6,'ROLE','99','MENU','1006',''),(7,'ROLE','99','MENU','1007',''),(8,'ROLE','99','MENU','1008',''),(9,'ROLE','99','MENU','1009',NULL),(10,'ROLE','99','MENU','1010',NULL),(11,'ROLE','99','MENU','1011',NULL),(12,'ROLE','99','MENU','1012',NULL),(13,'ROLE','88','MENU','1001',NULL),(14,'ROLE','88','MENU','1002',NULL),(15,'ROLE','88','MENU','1003',NULL),(16,'ROLE','88','MENU','1004',NULL),(17,'ROLE','88','MENU','1005',NULL),(18,'ROLE','3','MENU','1001',NULL),(19,'ROLE','3','MENU','1002',NULL),(20,'ROLE','3','MENU','1003',NULL),(21,'ROLE','3','MENU','1004',NULL),(22,'ROLE','3','MENU','1005',NULL);

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

INSERT INTO `misp_system_id_type` VALUES ('ORDER_ID',1,8,1,29,'S',NULL);

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

INSERT INTO `misp_system_menu` VALUES (1001,'首页','首页',NULL,NULL,NULL,NULL,'#','0',NULL),(1002,'我的主页','我的主页',NULL,NULL,NULL,NULL,'login/Index','1001',NULL),(1003,'设备管理','设备管理',NULL,NULL,NULL,NULL,'#','0',NULL),(1004,'集中器管理','集中器管理',NULL,NULL,NULL,NULL,'device/ConcentratorManage','1003',NULL),(1005,'平面图管理','平面图管理',NULL,NULL,NULL,NULL,'device/PlanManage','1003',NULL),(1006,'客户分布','客户分布',NULL,NULL,NULL,NULL,'device/CustomerDistribution','1003',NULL),(1007,'公共服务','公共服务 ',NULL,NULL,NULL,NULL,'#','0',NULL),(1008,'公告管理','公告管理',NULL,NULL,NULL,NULL,'info/NewsManage','1007',NULL),(1009,'服务申请','服务申请',NULL,NULL,NULL,NULL,'info/OrderManage','1007',NULL),(1010,'系统管理','系统管理',NULL,NULL,NULL,NULL,'#','0',NULL),(1011,'用户管理','用户管理',NULL,NULL,NULL,NULL,'sys/UserManage','1010',NULL),(1012,'日志管理','日志管理',NULL,NULL,NULL,NULL,'log/LogManage','1010',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "misp_user"
#

INSERT INTO `misp_user` VALUES (1,'admin','81dc9bdb52d04dc20036dbd8313ed055',99,'2014-11-27 16:07:48',2),(2,'2','81dc9bdb52d04dc20036dbd8313ed055',1,'2014-11-27 16:07:48',2),(3,'普通管理员1','81dc9bdb52d04dc20036dbd8313ed055',88,'2014-11-29 17:14:09',2),(4,'区域管理员1','81dc9bdb52d04dc20036dbd8313ed055',3,'2014-11-29 17:14:24',2),(5,'终端用户1','81dc9bdb52d04dc20036dbd8313ed055',1,'2014-11-29 17:14:36',2),(6,'联防用户1','81dc9bdb52d04dc20036dbd8313ed055',2,'2014-11-29 17:14:50',2);

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

#
# Data for table "news"
#

INSERT INTO `news` VALUES (3,'大家好','admin','哈哈哈哈','2014-11-25 14:33:57',0),(5,'关于消防安全的通知','admin',' 一.安全用电\r\n    各施工单位电源接驳应由具备操作资格的人员进行,其它人员严禁私自接驳电源线或违规用电,对未按规定用电的须及时作出整改配合,杜绝隐患;施工人员离开单位时,要切断电源,以防电路异常引致意外;\r\n    二.消除火患\r\n    施工现场,特别是在木器施工时严禁吸烟,吸烟时须远离易燃物,并将烟头熄灭及妥善处置;禁止在施工单元内生火或用电煮食、烤物和取暖等;小区内严禁燃放烟花、爆竹;烧纸、烧香时,必须有合适的容器盛放,同时选择合适的位置并作好周全的预防措施;\r\n    三.施工配备灭火器材\r\n    各施工单位必须配备器材,并时刻检查及保持器材的正常使用状态。\r\n    以上事宜及各项安全管理制度,希各施工单位及相关人员能配合执行,管理处将于2008年元月15日前进行统一的消防安全巡检工作,消防局将于2008年元18日开展消防安全大检查,未符合相关要求的请及时作出整改,如未能配合整改的单位,管理处将作出严厉处理。','2014-11-28 15:21:42',0),(9,'kokop','admin','ui9huihuio','2014-12-10 09:28:59',NULL),(10,'哦哦哦哦哦','admin','。。。，。。','2014-12-10 09:38:41',NULL),(11,'噢噢噢噢','admin','国有股一股一股','2014-12-10 09:44:32',NULL),(12,'请问轻轻','admin','我去问问v','2014-12-10 09:46:55',NULL),(13,'okooko','admin','hhhhh用户与远古语言','2014-12-10 11:49:17',NULL),(14,'12','admin','','2014-12-12 11:20:18',NULL),(15,'aaaa','admin','','2014-12-12 11:23:49',NULL),(16,'222','admin','','2014-12-12 11:24:25',NULL),(17,'uuuuju','admin','kkkkkkk','2014-12-12 11:28:48',NULL),(18,'huuhuhh','admin','ddddd','2014-12-12 11:37:06',NULL);

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

INSERT INTO `service_order` VALUES ('S00000028','监控摄像头配置',2,'需要给不同房间的摄像头配置特点属性','admin','2014-11-28 16:03:22','张三丰','13866774432','杭州市萧山区五一路34号',1,'admin','请参考APP设置中的帮助文档摄像头配置一项说明','2014-11-28 16:43:16'),('S00000029','hhhh',0,'','admin','2014-11-28 16:22:42','ssss','ssss','ss',1,'admin','事宜按','2014-11-28 16:41:56');

#
# Structure for table "user_concentrator"
#

DROP TABLE IF EXISTS `user_concentrator`;
CREATE TABLE `user_concentrator` (
  `USER_ID` int(11) NOT NULL,
  `CONCENTRATOR_ID` int(11) NOT NULL,
  `OPERATE` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`CONCENTRATOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_concentrator"
#

INSERT INTO `user_concentrator` VALUES (1,2,0),(1,100003,2),(2,100004,0),(3,100001,2),(3,100003,3),(3,100004,0),(4,100001,1),(6,100004,0);

#
# Structure for table "user_mark"
#

DROP TABLE IF EXISTS `user_mark`;
CREATE TABLE `user_mark` (
  `MARK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `MARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MARK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "user_mark"
#

INSERT INTO `user_mark` VALUES (1,1,'厨房'),(2,1,'卧室'),(3,1,'客厅');

DROP VIEW IF EXISTS `fire_alarm_view`;
CREATE VIEW `fire_alarm_view` AS 
  select `smart_home`.`alarm`.`ID` AS `ID`,`smart_home`.`alarm`.`CONCENTRATOR_ID` AS `CONCENTRATOR_ID`,`smart_home`.`alarm`.`OBJ_TYPE` AS `OBJ_TYPE`,`smart_home`.`alarm`.`OBJ_ID` AS `OBJ_ID`,`smart_home`.`alarm`.`ALARM_TYPE` AS `ALARM_TYPE`,`smart_home`.`alarm`.`ALARM_TIME` AS `ALARM_TIME`,`smart_home`.`alarm`.`CLEAR_STATUS` AS `CLEAR_STATUS`,`smart_home`.`alarm`.`DATA_VALUE` AS `DATA_VALUE`,`smart_home`.`alarm`.`CLEAR_USER` AS `CLEAR_USER`,`smart_home`.`alarm`.`CLEAR_TIME` AS `CLEAR_TIME`,`smart_home`.`concentrator`.`STATUS` AS `STATUS`,`smart_home`.`concentrator`.`DESCRIPTION` AS `CONCENT_DESP`,`smart_home`.`fire_sensor`.`MACHINE_ID` AS `MACHINE_ID`,`smart_home`.`fire_sensor`.`LOOP_ID` AS `LOOP_ID`,`smart_home`.`fire_sensor`.`CODE_ID` AS `CODE_ID` from ((`smart_home`.`alarm` left join `smart_home`.`concentrator` on((`smart_home`.`alarm`.`CONCENTRATOR_ID` = `smart_home`.`concentrator`.`CONCENTRATOR_ID`))) left join `smart_home`.`fire_sensor` on((`smart_home`.`alarm`.`OBJ_ID` = `smart_home`.`fire_sensor`.`ID`))) where ((`smart_home`.`alarm`.`OBJ_TYPE` = 0) or (`smart_home`.`alarm`.`OBJ_TYPE` = 2));

DROP VIEW IF EXISTS `home_alarm_view`;
CREATE VIEW `home_alarm_view` AS 
  select `smart_home`.`alarm`.`ID` AS `ID`,`smart_home`.`alarm`.`CONCENTRATOR_ID` AS `CONCENTRATOR_ID`,`smart_home`.`alarm`.`OBJ_TYPE` AS `OBJ_TYPE`,`smart_home`.`alarm`.`OBJ_ID` AS `OBJ_ID`,`smart_home`.`alarm`.`ALARM_TYPE` AS `ALARM_TYPE`,`smart_home`.`alarm`.`ALARM_TIME` AS `ALARM_TIME`,`smart_home`.`alarm`.`CLEAR_STATUS` AS `CLEAR_STATUS`,`smart_home`.`alarm`.`DATA_VALUE` AS `DATA_VALUE`,`smart_home`.`alarm`.`CLEAR_USER` AS `CLEAR_USER`,`smart_home`.`alarm`.`CLEAR_TIME` AS `CLEAR_TIME`,`smart_home`.`concentrator`.`STATUS` AS `STATUS`,`smart_home`.`concentrator`.`DESCRIPTION` AS `CONCENT_DESP`,`smart_home`.`home_sensor`.`SENSOR_TYPE` AS `SENSOR_TYPE`,`smart_home`.`home_sensor`.`SENSOR_TYPE_NAME` AS `SENSOR_TYPE_NAME`,`smart_home`.`home_sensor`.`DESCRIPTION` AS `SENSOR_DESP` from ((`smart_home`.`alarm` left join `smart_home`.`concentrator` on((`smart_home`.`alarm`.`CONCENTRATOR_ID` = `smart_home`.`concentrator`.`CONCENTRATOR_ID`))) left join `smart_home`.`home_sensor` on((`smart_home`.`alarm`.`OBJ_ID` = `smart_home`.`home_sensor`.`ID`))) where ((`smart_home`.`alarm`.`OBJ_TYPE` = 0) or (`smart_home`.`alarm`.`OBJ_TYPE` = 1));
