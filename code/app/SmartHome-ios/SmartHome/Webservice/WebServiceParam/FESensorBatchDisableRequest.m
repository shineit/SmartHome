//
//  FESensorBatchDisableRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-6.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESensorBatchDisableRequest.h"
#import "FESensor.h"

@implementation FESensorBatchDisableRequest

-(id)initWithSensorList:(NSArray *)sensorList{
    self = [super initWithMothed:__METHOD_SENSOR_DISABLE];
    if (self) {
        NSMutableArray *sensors = [NSMutableArray new];
        for (FESensor *sensor in sensorList) {
            [sensors addObject:sensor.dictionary];
        }
        _sensorList = sensors;
    }
    return self;
}

@end
