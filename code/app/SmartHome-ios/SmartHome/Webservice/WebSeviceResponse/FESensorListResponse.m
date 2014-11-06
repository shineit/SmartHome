//
//  FESensorListResponse.m
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESensorListResponse.h"
#import "FESensor.h"

@implementation FESensorListResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        NSArray *sensors = obj[@"sensorList"];
        if (![sensors isKindOfClass:[NSNull class]] && sensors.count) {
            NSMutableArray *sensorObjs = [NSMutableArray new];
            for (NSDictionary *item in sensors) {
                [sensorObjs addObject:[[FESensor alloc] initWithDictionary:item]];
            }
            _sensorList = sensorObjs;
        }
    }
    return self;
}

@end
