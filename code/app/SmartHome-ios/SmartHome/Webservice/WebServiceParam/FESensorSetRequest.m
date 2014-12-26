//
//  FESensorSetRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESensorSetRequest.h"
#import "FESensor.h"

@implementation FESensorSetRequest

-(id)initWithCommond:(NSNumber *)cmd sensor:(FESensor *)sensor{
    self = [super initWithMothed:__METHOD_SENSOR_SET];
    if (self) {
        _command = cmd;
        _sensor = sensor;
    }
    return self;
}

@end
