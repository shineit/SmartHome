//
//  FEFireAlarm.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEFireAlarm : SSObject

//private int id;  			//告警ID，自增长
//private long concentratorID;
//private String alarmTypeName; //告警类型名称
//private long alarmTime;	//告警产生的时间
//private Integer clearStatus;   //0未清除 1 手动清除 2自动清除 AlarmClearEnum
//
//private long clearTime;	//告警清除的时间
////通过concenratorID关联concentrator表
//private Integer status;			//集中器状态，0 离线 1在线
//private String concentDesp;		//集中器描述
//
////通过objID关联FireSensor表
//private Integer machineID; //机号
//private Integer loopID;    //回路号
//private Integer codeID;    //编号
//private String locationDesp;  //位置描述
//private Float locationX;  //X 偏移，相对当前图片尺寸宽度
//private Float locationY;  //Y 偏移，相当当前图片尺寸高度
//
//private Integer planID;
//private String sensorTypeName;  //传感器类型名称
//private int alarmKind;//0-告警；1-设备状态；
//
//
//private String contacts; //联系人
//private String contactPhone; //联系电话
@property (nonatomic, strong) NSNumber *id;
@property (nonatomic, strong) NSNumber *concentratorID;
@property (nonatomic, strong) NSString *alarmTypeName;
@property (nonatomic, strong) NSNumber *alarmTime;
@property (nonatomic, strong) NSNumber *clearStatus;
@property (nonatomic, strong) NSNumber *clearTime;
@property (nonatomic, strong) NSNumber *status;
@property (nonatomic, strong) NSString *concentDesp;
@property (nonatomic, strong) NSNumber *machineID;
@property (nonatomic, strong) NSNumber *loopID;
@property (nonatomic, strong) NSNumber *codeID;
@property (nonatomic, strong) NSString *locationDesp;
@property (nonatomic, strong) NSNumber *locationX;
@property (nonatomic, strong) NSNumber *locationY;
@property (nonatomic, strong) NSNumber *planID;
@property (nonatomic, strong) NSString *sensorTypeName;
@property (nonatomic, strong) NSNumber *alarmKind;
@property (nonatomic, strong) NSString *contacts;
@property (nonatomic, strong) NSString *contactPhone;


@end
