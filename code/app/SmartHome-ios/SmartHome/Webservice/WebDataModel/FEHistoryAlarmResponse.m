//
//  FEHistoryAlarmResponse.m
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEHistoryAlarmResponse.h"
#import "FEAlarm.h"

@implementation FEHistoryAlarmResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        
        NSArray *alarmList = obj[@"alarmList"];
        
        if (![obj[@"alarmList"] isKindOfClass:[NSNull class]] && [obj[@"alarmList"] count]) {
            
            NSMutableArray *alarms = [NSMutableArray new];
            for (NSDictionary *item in alarmList) {
                [alarms addObject:[[FEAlarm alloc] initWithDictionary:item]];
            }
            _alarmList = alarms;
        }
    }
    return self;
}

@end
