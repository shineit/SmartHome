# Host: localhost  (Version: 5.6.21)
# Date: 2014-11-03 21:03:46
# Generator: MySQL-Front 5.3  (Build 4.123)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "alarm"
#

CREATE TABLE `alarm` (
  `ID` int(11) NOT NULL,
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

CREATE TABLE `concentrator` (
  `CONCENTRATOR_ID` int(11) NOT NULL,
  `IP_ADDR` varchar(255) NOT NULL,
  `STATUS` int(11) NOT NULL,
  `LOCATION_NS` float DEFAULT NULL,
  `LOCATION_WE` float DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESPCRIPTION` varchar(255) DEFAULT NULL,
  `ADDR` varchar(255) DEFAULT NULL,
  `MARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CONCENTRATOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "concentrator"
#


#
# Structure for table "home_sensor"
#

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


#
# Structure for table "misp_oper_log"
#

CREATE TABLE `misp_oper_log` (
  `id` int(11) NOT NULL,
  `USER` varchar(255) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `OBJECT` varchar(255) DEFAULT NULL,
  `RESULT` varchar(255) DEFAULT NULL,
  `OPER_DESP` varchar(255) DEFAULT NULL,
  `OPER_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "misp_oper_log"
#

INSERT INTO `misp_oper_log` VALUES (1,'admin','登录',NULL,'成功',NULL,'2014-11-03 15:02:29'),(2,'admin','登录',NULL,'成功',NULL,'2014-11-03 15:03:22'),(3,'admin','登录',NULL,'成功',NULL,'2014-11-03 15:20:51'),(4,'admin','登录',NULL,'成功',NULL,'2014-11-03 15:56:12'),(5,'admin','登录',NULL,'成功',NULL,'2014-11-03 15:58:04'),(6,'admin','登录',NULL,'成功',NULL,'2014-11-03 15:59:39'),(7,'admin','登录',NULL,'成功',NULL,'2014-11-03 16:03:07'),(8,'admin','登录',NULL,'成功',NULL,'2014-11-03 16:29:59'),(9,'admin','登录',NULL,'成功',NULL,'2014-11-03 18:26:49');

#
# Structure for table "misp_privilege"
#

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

INSERT INTO `misp_privilege` VALUES (1,'ROLE','99','MENU','1001',NULL),(2,'ROLE','99','MENU','1002',NULL),(3,'ROLE','99','MENU','1003',NULL),(4,'ROLE','99','MENU','1004',NULL),(5,'ROLE','99','MENU','1005',NULL),(6,'ROLE','99','MENU','1006',''),(7,'ROLE','99','MENU','1007',''),(8,'ROLE','99','MENU','1008',''),(9,'ROLE','99','MENU','1009',NULL),(10,'ROLE','99','MENU','1010',NULL),(11,'ROLE','99','MENU','1011',NULL);

#
# Structure for table "misp_system_id_type"
#

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

INSERT INTO `misp_system_id_type` VALUES ('ORDER_ID',1,10,1,2,'S',NULL);

#
# Structure for table "misp_system_menu"
#

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

INSERT INTO `misp_system_menu` VALUES (1001,'首页','首页',NULL,NULL,NULL,NULL,'#','0',NULL),(1002,'我的主页','我的主页',NULL,NULL,NULL,NULL,NULL,'1001',NULL),(1003,'设备管理','设备管理',NULL,NULL,NULL,NULL,NULL,'0',NULL),(1004,'集中器管理','集中器管理',NULL,NULL,NULL,NULL,NULL,'1003',NULL),(1005,'平面图管理','平面图管理',NULL,NULL,NULL,NULL,NULL,'1003',NULL),(1006,'客户分布','客户分布',NULL,NULL,NULL,NULL,NULL,'1003',NULL),(1007,'公共服务','公共服务 ',NULL,NULL,NULL,NULL,NULL,'0',NULL),(1008,'公告管理','公告管理',NULL,NULL,NULL,NULL,NULL,'1007',NULL),(1009,'服务申请','服务申请',NULL,NULL,NULL,NULL,'info/OrderManage','1007',NULL),(1010,'系统管理','系统管理',NULL,NULL,NULL,NULL,NULL,'0',NULL),(1011,'用户管理','用户管理',NULL,NULL,NULL,NULL,NULL,'1010',NULL);

#
# Structure for table "misp_user"
#

CREATE TABLE `misp_user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(11) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "misp_user"
#

INSERT INTO `misp_user` VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e',99,NULL);

#
# Structure for table "news"
#

CREATE TABLE `news` (
  `NEWS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `AUTHOR` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `RELEASE_DATE` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`NEWS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "news"
#

INSERT INTO `news` VALUES (1,'通知','admin','放假三天','2014-11-03 00:00:00',NULL);

#
# Structure for table "service_order"
#

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

