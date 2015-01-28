//
//  FESensorBatchEnableRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-6.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESensorBatchEnableRequest.h"
#import "FESensor.h"

@implementation FESensorBatchEnableRequest

-(id)initWithSensorList:(NSArray *)sensorList{
    self = [super initWithMothed:__METHOD_SENSOR_ENABLE];
    if (self) {
        _sensorList = sensorList;
    }
    return self;
}

@end
