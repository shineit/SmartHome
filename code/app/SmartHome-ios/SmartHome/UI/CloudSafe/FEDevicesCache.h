//
//  FEDevicesCache.h
//  SmartHome
//
//  Created by Seven on 14-12-26.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>


typedef enum : NSUInteger {
    DISCRETE_SENSOR = 0,
    CONTIUOUS_SENSOR,
    CTRL_SENSOR,
} SENSOR_TYPE;

#define __SENSOR_MARK   @"mark"
#define __SENSOR_LIST   @"sensors"

@interface FEDevicesCache : NSObject

+(FEDevicesCache *)sharedInstance;
-(void)putDevices:(NSArray *)devices;
-(NSArray *)getAlldevices;

-(NSArray *)getFilterSensors;
-(NSArray *)getFilterControlDevice;

@end
