//
//  FESensor.h
//  SmartHome
//
//  Created by Seven on 14-11-6.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FESensor : SSObject

//private int id;              //唯一ID，终端设备的出场ID，具有唯一性
//private int concentratorID;  //集中器ID
//private int sensorID;         //终端ID，在集中中，每个终端设备，每个通道有一个唯一ID
//private int channelID;       //终端设备的通道ID
//private int sensorKind;      //传感器种类 0 告警类，1模拟类，2控制类型， SensorKindEunm
//private int sensorType;      //传感器类型
//private String sensorTypeName;  //传感器类型名称
//private int status;          //告警使能状态，0-禁止，1-使能，SensorStatusEnum
//private float warnValue;    //预警值
//private float errorValue;   //火警值
//private int groupID;         //区域ID（用于标识联动控制器）
//private String ctrGroupID;      //联动控制器ID ;隔开
//private String descriptions;  //描述
//private String mark;         //自定义标签

@property (nonatomic, strong, readonly) NSNumber *id;
@property (nonatomic, strong, readonly) NSNumber *concentratorID;
@property (nonatomic, strong, readonly) NSNumber *sensorID;
@property (nonatomic, strong, readonly) NSNumber *channelID;
@property (nonatomic, strong, readonly) NSNumber *sensorKind;
@property (nonatomic, strong, readonly) NSNumber *sensorType;
@property (nonatomic, strong, readonly) NSString *sensorTypeName;
@property (nonatomic, strong, readonly) NSNumber *status;
@property (nonatomic, strong, readonly) NSNumber *warnValue;
@property (nonatomic, strong, readonly) NSNumber *errorValue;
@property (nonatomic, strong, readonly) NSNumber *groupID;
@property (nonatomic, strong, readonly) NSString *ctrGroupID;
@property (nonatomic, strong, readonly) NSString *descriptions;
@property (nonatomic, strong, readonly) NSString *mark;


@end
