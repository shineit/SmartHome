//
//  FEFireAlarmResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEFireAlarmResponse.h"

@implementation FEFireAlarmResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _fireAlarmList = [self getListFromObject:obj[@"fireAlarmList"] class:[FEFireAlarm class]];
    }
    return self;
}

@end
