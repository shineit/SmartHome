//
//  FEFireAlarmNumRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEFireAlarmNumRequest.h"

@implementation FEFireAlarmNumRequest

-(id)initWithUid:(NSNumber *)uid{
    self = [super initWithMothed:__METHOD_ALARM_NUMBER];
    if (self) {
        _userID = uid;
    }
    return self;
}

@end
