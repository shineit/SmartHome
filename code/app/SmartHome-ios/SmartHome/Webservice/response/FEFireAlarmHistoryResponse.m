//
//  FEFireAlarmHistoryResponse.m
//  SmartHome
//
//  Created by Seven on 15-5-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEFireAlarmHistoryResponse.h"

@implementation FEFireAlarmHistoryResponse
-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _fireAlarmList = [self getListFromObject:obj[@"fireAlarmList"] class:[FEFireAlarm class]];
    }
    return self;
}
@end
