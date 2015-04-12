//
//  FERequestBaseData.h
//  SmartHome
//
//  Created by Seven on 14-10-30.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <SSCommon-Utilities/SSObject.h>

#define __METHOD_SIGIN              @"user/login" //登陆
#define __METHOD_MARK_LIST          @"user/mark/list"
#define __METHOD_MARK_ADD           @"user/mark/add"
#define __METHOD_MARK_DELET         @"user/mark/delete"
#define __METHOD_SIGOUT             @"user/logout"
#define __METHOD_MODIFY_PASSWORD    @"user/password/modify"
#define __METHOD_CATOKEN            @"user/caToken/get"

#define __METHOD_GET_NEWS           @"news/list"  //news
#define __METHOD_SEVICE_ORDER       @"order/list" //orders
#define __METHOD_SEVICE_ORDER_SET   @"order/set" //set order

#define __METHOD_HISTORY_ALARM      @"sensor/alarm"
#define __METHOD_SENSOR_LIST        @"sensor/list"
#define __METHOD_SENSOR_SET         @"sensor/set"
#define __METHOD_SENSOR_ENABLE      @"sensor/batch/enable"
#define __METHOD_SENSOR_DISABLE     @"sensor/batch/disable"

//for smarthome enterprise

//knowledge
#define __METHOD_KNOWLEDGE_LIST     @"knowledge/list"
#define __METHOD_KNOWLEDGE_COMMONSENSE @"knowledge/commonsense/list"
#define __METHOD_KNOWLEDGE_HELP     @"knowledge/help/list"
//mall
#define __METHOD_MALL_PRODUCT       @"product/list"
#define __METHOD_MALL_AD            @"ad/list"

//company
#define __METHOD_COMPANY_LIST       @"company/list"
#define __METHOD_QUERY_COMPANY      @"company/id"
#define __METHOD_GET_PLAN           @"plan/get"

//fire alarm
#define __METHOD_FIRE_ALARM         @"sensor/fireAlarm/get"


//check
#define __METHOD_CHECK_LIST         @"check/item/id"
#define __METHOD_CHECK_LOG_CREATE   @"check/checkLog/create"
#define __METHOD_CHECK_LOG_GET      @"check/checkLog/get"
#define __METHOD_DELET_IMG          @"check/img/delete"

//upload
#define __METHOD_UPLOAD             @"UploadFile!uploadFile"

//number
#define __METHOD_ALARM_NUMBER       @"user/fireAlarmNum/get"
#define __METHOD_DEVICE_STATUS_NUMBER @"user/fireStatusNum/get"
#define __METHOD_CHECH_LOG_NUMBER   @"user/checkLogNum/get"


typedef enum : NSUInteger {
    GET = 0,
    POST
} METHOD_TYPE;


@interface FERequestBaseData : SSObject

@property (nonatomic, strong, readonly) NSString *method;
@property (nonatomic, assign, readonly) METHOD_TYPE type;

-(instancetype)initWithMothed:(NSString *)mothed;

@end
