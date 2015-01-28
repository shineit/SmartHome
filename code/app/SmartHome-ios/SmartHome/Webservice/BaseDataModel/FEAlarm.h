//
//  FEAlarm.h
//  SmartHome
//
//  Created by Seven on 14-11-5.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEAlarm : SSObject

//private int id;  			//告警ID，自增长
//private int objType;      //0 集中器,1家庭终端，2消防终端 AlarmObjTypeEnmu
//private int objID;        //对象ID
//
//private int alarmType;		//告警事件类型 AlarmTypeEnum
//private float dataValue;	//告警值,模拟量类型 才有
//private long alarmTime;	//告警产生的时间
//private String clearUser;  //清除人 手动清除需要填写
//private int clearStatus;   //0未清除 1 手动清除 2自动清除 AlarmClearEnum
//private long clearTime;	//告警清除的时间

@property (nonatomic, strong, readonly) NSNumber *id;           //告警ID，自增长
@property (nonatomic, strong, readonly) NSNumber *objType;      //0 集中器,1家庭终端，2消防终端 AlarmObjTypeEnmu
@property (nonatomic, strong, readonly) NSNumber *objID;        //对象ID
@property (nonatomic, strong, readonly) NSNumber *alarmType;    //告警事件类型 AlarmTypeEnum
@property (nonatomic, strong, readonly) NSNumber *dataValue;    //告警值,模拟量类型 才有
@property (nonatomic, strong, readonly) NSNumber *alarmTime;    //告警产生的时间
@property (nonatomic, strong, readonly) NSString *cleatUser;    //清除人 手动清除需要填写
@property (nonatomic, strong, readonly) NSNumber *clearStatus;  //0未清除 1 手动清除 2自动清除 AlarmClearEnum
@property (nonatomic, strong, readonly) NSNumber *clearTime;    //告警清除的时间

@end
