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

INSERT INTO `alarm` VALUES (1,0,702,0,0,'2014-11-01 00:00:00','张政',1,'2014-11-02 00:00:00');

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

INSERT INTO `concentrator` VALUES (3,'192.168.1.1:8080',1,114.007,22.5968,'集中器','描述',NULL,NULL),(5,'192.168.1.1:8080',1,116.418,39.922,'集中器5','地址：北京市东城区王府井大街88号乐天银泰百货八层',NULL,NULL),(6,'192.168.1.1:8080',1,116.407,39.9216,'集中器6','地址：北京市东城区东华门大街',NULL,NULL),(7,'192.168.1.1:8080',1,116.412,39.9123,'集中器7','地址：北京市东城区正义路甲5号',NULL,NULL),(8,'192.168.1.1:8080',1,114.016,22.6327,'集中器7','描述',NULL,NULL);

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

INSERT INTO `misp_privilege` VALUES (1,'ROLE','99','MENU','1001',NULL),(2,'ROLE','99','MENU','1002',NULL),(3,'ROLE','99','MENU','1003',NULL),(4,'ROLE','99','MENU','1004',NULL),(5,'ROLE','99','MENU','1005',NULL),(6,'ROLE','99','MENU','1006',''),(7,'ROLE','99','MENU','1007',''),(8,'ROLE','99','MENU','1008',''),(9,'ROLE','99','MENU','1009',NULL),(10,'ROLE','99','MENU','1010',NULL),(11,'ROLE','99','MENU','1011',NULL),(12,'ROLE','99','MENU','1012',NULL);

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

INSERT INTO `misp_system_menu` VALUES (1001,'首页','首页',NULL,NULL,NULL,NULL,'#','0',NULL),(1002,'我的主页','我的主页',NULL,NULL,NULL,NULL,'login/Index','1001',NULL),(1003,'设备管理','设备管理',NULL,NULL,NULL,NULL,'#','0',NULL),(1004,'集中器管理','集中器管理',NULL,NULL,NULL,NULL,'device/ConcentratorManage','1003',NULL),(1005,'平面图管理','平面图管理',NULL,NULL,NULL,NULL,'device/PlanManage','1003',NULL),(1006,'客户分布','客户分布',NULL,NULL,NULL,NULL,'device/CustomerDistribution','1003',NULL),(1007,'公共服务','公共服务 ',NULL,NULL,NULL,NULL,'#','0',NULL),(1008,'公告管理','公告管理',NULL,NULL,NULL,NULL,'info/NewsManage','1007',NULL),(1009,'服务申请','服务申请',NULL,NULL,NULL,NULL,'info/OrderManage','1007',NULL),(1010,'系统管理','系统管理',NULL,NULL,NULL,NULL,'#','0',NULL),(1011,'用户管理','用户管理',NULL,NULL,NULL,NULL,'sys/UserManage','1010',NULL),(1012,'日志管理','日志管理',NULL,NULL,NULL,NULL,'log/LogManage','1010',NULL);

#
# Structure for table "misp_user"
#

CREATE TABLE `misp_user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(11) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "misp_user"
#

/*!40000 ALTER TABLE `misp_user` DISABLE KEYS */;
INSERT INTO `misp_user` VALUES (1,'admin','81dc9bdb52d04dc20036dbd8313ed055',99,'2014-11-04 00:00:00',2);
/*!40000 ALTER TABLE `misp_user` ENABLE KEYS */;

#
# Structure for table "news"
#

CREATE TABLE `news` (
  `NEWS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `AUTHOR` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `RELEASE_DATE` datetime DEFAULT NULL,
  `STATUS` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`NEWS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "news"
#

INSERT INTO `news` VALUES (7,'今日头条','admin','智慧城市一直在想方设法优化其基础设施。借助智能电网、楼宇自动化和智能交通系统等智能基础设施解决方案，西门子可助力中国城市充分挖掘城市基础设施的潜力。\r\n','2014-11-08 15:49:38',0),(8,'五一放假通知','admin','五一劳动节即将至，根据国务院办公厅部分节假日安排的通知精神，结合本公司实际情况，现将五一放假事宜通知如下：  一、放假时间  2014年5月1日（星期四）、5月2日（星期五）、5月3日（星期六）放假，共三天；5月4日（星期日）开始上班。','2014-11-08 16:56:58',0),(9,'消防注意事项','admin','切莫乱扔烟头和火种。\r\n3．室内装修装饰不宜采用易燃可燃材料。\r\n4．消火栓关系公共安全，切勿损坏、圈占或埋压。\r\n5．爱护消防器材，掌握常用消防器材的使用方法。','2014-11-08 17:00:35',0);

#
# Structure for table "service_order"
#

CREATE TABLE `service_order` (
  `ORDER_ID` varchar(255) NOT NULL,
  `ORDER_NAME` varchar(255) DEFAULT NULL,
  `ORDER_TYPE` int(11) NOT NULL DEFAULT '0',
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

INSERT INTO `service_order` VALUES ('1','修理集中器',0,'集中器被雷击中烧毁','张三','2014-11-01 00:00:00','李四','13855243366','簪花路122号',1,'admin','周四会有维修人员上门服务敬请期待！','2014-11-04 10:19:35');
