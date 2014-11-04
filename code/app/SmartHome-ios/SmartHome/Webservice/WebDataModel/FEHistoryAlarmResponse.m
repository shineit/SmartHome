//
//  FEHistoryAlarmResponse.m
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEHistoryAlarmResponse.h"

@implementation FEHistoryAlarmResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        if (![obj[@"alarmList"] isKindOfClass:[NSNull class]] && [obj[@"alarmList"] count]) {
            NSLog(@"alarmList");
        }
    }
    return self;
}

@end
