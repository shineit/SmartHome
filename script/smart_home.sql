# Host: 127.0.0.1  (Version: 5.1.70-community)
# Date: 2015-01-14 16:16:35
# Generator: MySQL-Front 5.3  (Build 4.191)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "alarm"
#

DROP TABLE IF EXISTS `alarm`;
CREATE TABLE `alarm` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONCENTRATOR_ID` bigint(20) DEFAULT NULL,
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

INSERT INTO `concentrator` VALUES (2,'127.0.0.1',0,0,114.123,22.6116,'家庭用户','阳光花园1栋A座705',NULL,NULL),(100001,'192.168.1.100',0,0,114.092,22.563,'深圳市第二人民医院','ff订单',NULL,NULL),(100003,'192.168.1.102',0,0,114.083,22.5891,'银湖国际会议中心','地点33',NULL,NULL),(100004,'192.168.1.103',0,0,113.986,22.5629,'桃源村综合楼',NULL,NULL,NULL);

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
  `ID` int(11) NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

#
# Data for table "home_sensor"
#

INSERT INTO `home_sensor` VALUES (1,2,2,1,1,1,'温度传感器',1,0,0,0,'12','监控客厅温度555','客厅'),(2,2,1,1,0,1,'煤气传感器',1,0,0,0,'13','监控煤气浓度gggg','卧室'),(3,2,1,1,0,1,'烟雾报警器',1,0,0,0,'12','监控插线板','厨房'),(11,2,11,11,2,2,'空调控制器',1,0,0,10,NULL,'控制空调关闭','厨房'),(12,2,12,12,2,2,'窗户控制器',1,0,0,10,NULL,'控制厨房窗户开闭','卧室'),(13,2,13,13,2,2,'喷水控制器',0,1,1,10,'0','控制卧室火警消除','卧室');

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
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

#
# Data for table "misp_oper_log"
#


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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

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

INSERT INTO `misp_system_id_type` VALUES ('ORDER_ID',1,8,1,30,'S',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "misp_user"
#

INSERT INTO `misp_user` VALUES (1,'admin','81dc9bdb52d04dc20036dbd8313ed055',99,'2014-11-27 16:07:48',2);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "news"
#

INSERT INTO `news` VALUES (3,'大家好','admin','哈哈哈哈','2014-11-25 14:33:57',0),(5,'关于消防安全的通知','admin',' 一.安全用电\r\n    各施工单位电源接驳应由具备操作资格的人员进行,其它人员严禁私自接驳电源线或违规用电,对未按规定用电的须及时作出整改配合,杜绝隐患;施工人员离开单位时,要切断电源,以防电路异常引致意外;\r\n    二.消除火患\r\n    施工现场,特别是在木器施工时严禁吸烟,吸烟时须远离易燃物,并将烟头熄灭及妥善处置;禁止在施工单元内生火或用电煮食、烤物和取暖等;小区内严禁燃放烟花、爆竹;烧纸、烧香时,必须有合适的容器盛放,同时选择合适的位置并作好周全的预防措施;\r\n    三.施工配备灭火器材\r\n    各施工单位必须配备器材,并时刻检查及保持器材的正常使用状态。\r\n    以上事宜及各项安全管理制度,希各施工单位及相关人员能配合执行,管理处将于2008年元月15日前进行统一的消防安全巡检工作,消防局将于2008年元18日开展消防安全大检查,未符合相关要求的请及时作出整改,如未能配合整改的单位,管理处将作出严厉处理。','2014-11-28 15:21:42',0);

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

INSERT INTO `service_order` VALUES ('S00000028','监控摄像头配置',2,'需要给不同房间的摄像头配置特点属性','admin','2014-11-28 16:03:22','张三丰','13866774432','杭州市萧山区五一路34号',1,'admin','请参考APP设置中的帮助文档摄像头配置一项说明','2014-11-28 16:43:16'),('S00000029','hhhh',0,'','admin','2014-11-28 16:22:42','ssss','ssss','ss',1,'admin','事宜按','2014-11-28 16:41:56'),('S00000030','5555',2,'','admin','2015-01-05 17:26:27','','','',0,NULL,NULL,NULL);

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

#
# Structure for table "fire_alarm_view"
#

DROP VIEW IF EXISTS `fire_alarm_view`;
CREATE VIEW `fire_alarm_view` AS 
  select `alarm`.`ID` AS `ID`,`alarm`.`CONCENTRATOR_ID` AS `CONCENTRATOR_ID`,`alarm`.`OBJ_TYPE` AS `OBJ_TYPE`,`alarm`.`OBJ_ID` AS `OBJ_ID`,`alarm`.`ALARM_TYPE` AS `ALARM_TYPE`,`alarm`.`ALARM_TIME` AS `ALARM_TIME`,`alarm`.`CLEAR_STATUS` AS `CLEAR_STATUS`,`alarm`.`DATA_VALUE` AS `DATA_VALUE`,`alarm`.`CLEAR_USER` AS `CLEAR_USER`,`alarm`.`CLEAR_TIME` AS `CLEAR_TIME`,`concentrator`.`STATUS` AS `STATUS`,`concentrator`.`DESCRIPTION` AS `CONCENT_DESP`,`fire_sensor`.`MACHINE_ID` AS `MACHINE_ID`,`fire_sensor`.`LOOP_ID` AS `LOOP_ID`,`fire_sensor`.`CODE_ID` AS `CODE_ID` from ((`alarm` left join `concentrator` on((`alarm`.`CONCENTRATOR_ID` = `concentrator`.`CONCENTRATOR_ID`))) left join `fire_sensor` on((`alarm`.`OBJ_ID` = `fire_sensor`.`ID`))) where ((`alarm`.`OBJ_TYPE` = 0) or (`alarm`.`OBJ_TYPE` = 2));

#
# Structure for table "home_alarm_view"
#

DROP VIEW IF EXISTS `home_alarm_view`;
CREATE VIEW `home_alarm_view` AS 
  select `alarm`.`ID` AS `ID`,`alarm`.`CONCENTRATOR_ID` AS `CONCENTRATOR_ID`,`alarm`.`OBJ_TYPE` AS `OBJ_TYPE`,`alarm`.`OBJ_ID` AS `OBJ_ID`,`alarm`.`ALARM_TYPE` AS `ALARM_TYPE`,`alarm`.`ALARM_TIME` AS `ALARM_TIME`,`alarm`.`CLEAR_STATUS` AS `CLEAR_STATUS`,`alarm`.`DATA_VALUE` AS `DATA_VALUE`,`alarm`.`CLEAR_USER` AS `CLEAR_USER`,`alarm`.`CLEAR_TIME` AS `CLEAR_TIME`,`concentrator`.`STATUS` AS `STATUS`,`concentrator`.`DESCRIPTION` AS `CONCENT_DESP`,`home_sensor`.`SENSOR_TYPE` AS `SENSOR_TYPE`,`home_sensor`.`SENSOR_TYPE_NAME` AS `SENSOR_TYPE_NAME`,`home_sensor`.`DESCRIPTION` AS `SENSOR_DESP` from ((`alarm` left join `concentrator` on((`alarm`.`CONCENTRATOR_ID` = `concentrator`.`CONCENTRATOR_ID`))) left join `home_sensor` on((`alarm`.`OBJ_ID` = `home_sensor`.`ID`))) where ((`alarm`.`OBJ_TYPE` = 0) or (`alarm`.`OBJ_TYPE` = 1));
