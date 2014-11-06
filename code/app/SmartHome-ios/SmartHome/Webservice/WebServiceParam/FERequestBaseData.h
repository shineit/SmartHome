//
//  FERequestBaseData.h
//  SmartHome
//
//  Created by Seven on 14-10-30.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "NSObject+Dictionary.h"

#define __METHOD_SIGIN              @"user/login" //登陆
#define __METHOD_MARK_LIST          @"user/mark/list"
#define __METHOD_MARK_ADD           @"user/mark/add"
#define __METHOD_MARK_DELET         @"user/mark/delet"
#define __METHOD_SIGOUT             @"user/logout"
#define __METHOD_MODIFY_PASSWORD    @"user/password/modify"

#define __METHOD_GET_NEWS           @"news/list"  //news
#define __METHOD_SEVICE_ORDER       @"order/list" //orders
#define __METHOD_SEVICE_ORDER_SET   @"order/set" //set order

#define __METHOD_HISTORY_ALARM      @"sensor/alarm"
#define __METHOD_SENSOR_LIST        @"sensor/list"
#define __METHOD_SENSOR_SET         @"sensor/set"
#define __METHOD_SENSOR_ENABLE      @"sensor/batch/enable"
#define __METHOD_SENSOR_DISABLE     @"sensor/batch/disable"


@interface FERequestBaseData : NSObject

@property (nonatomic, strong, readonly) NSString *method;

-(instancetype)initWithMothed:(NSString *)mothed;

@end
