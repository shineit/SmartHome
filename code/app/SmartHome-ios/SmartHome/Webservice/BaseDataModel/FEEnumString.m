//
//  FEEnumString.m
//  SmartHome
//
//  Created by Seven on 14-11-10.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEEnumString.h"
#import "Define.h"

@implementation FEEnumString

+(NSString *)alarmType:(NSNumber *)type{
    static NSArray *array = NULL;
    if (!array) {

        array = [NSArray arrayWithObjects:kString(@"UNKNOW"),kString(@"掉线"),kString(@"掉线恢复"),kString(@"欠压"),kString(@"欠压恢复"),kString(@"设防"),kString(@"撤防"),kString(@"故障"),kString(@"故障恢复"),kString(@"预警"),kString(@"火警"),@"反馈",@"复位",@"动作",@"动作复位", nil];
    }
    return array[type.integerValue];
}

+(NSString *)deviceType:(NSNumber *)type{
    static NSArray *array = NULL;
    if (!array) {
        array = [NSArray arrayWithObjects:kString(@"CONCENTRATOR_ALARM"),kString(@"HOME_SENSOR"),kString(@"FIRE_SENSOR"), nil];
    }
    return array[type.integerValue];
}

+(NSString *)alarmHandledType:(NSNumber *)type{
    static NSArray *array = NULL;
    if (!array) {
        array = [NSArray arrayWithObjects:kString(@"NONE_CLEAR"),kString(@"MANUAL_CLEAR"),kString(@"AUTO_CLEAR"), nil];
    }
    return array[type.integerValue];
}



@end
