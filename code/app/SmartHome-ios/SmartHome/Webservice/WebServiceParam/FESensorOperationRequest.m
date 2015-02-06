//
//  FESensorOperationRequest.m
//  SmartHome
//
//  Created by Seven on 15-1-31.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FESensorOperationRequest.h"

@implementation FESensorOperationRequest
-(id)initWithUserId:(NSNumber *)uid enableSensors:(NSArray *)slist{
    self = [super initWithMothed:__METHOD_SENSOR_ENABLE];
    if (self) {
        _userID = uid;
        _sensorList = slist;
    }
    return self;
}

-(id)initWithUserId:(NSNumber *)uid disableSensors:(NSArray *)slist{
    self = [super initWithMothed:__METHOD_SENSOR_DISABLE];
    if (self) {
        _userID = uid;
        _sensorList = slist;
    }
    return self;
}
@end
