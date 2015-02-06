//
//  FEEnumString.m
//  SmartHome
//
//  Created by Seven on 14-11-10.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEEnumString.h"

@implementation FEEnumString

+(NSString *)alarmType:(NSNumber *)type{
    static NSArray *array = NULL;
    if (!array) {

        array = [NSArray arrayWithObjects:FEString(@"UNKNOW"),FEString(@"掉线"),FEString(@"掉线恢复"),FEString(@"欠压"),FEString(@"欠压恢复"),FEString(@"设防"),FEString(@"撤防"),FEString(@"故障"),FEString(@"故障恢复"),FEString(@"预警"),FEString(@"火警"),@"反馈",@"复位",@"动作",@"动作复位", nil];
    }
    return array[type.integerValue];
}

+(NSString *)deviceType:(NSNumber *)type{
    static NSArray *array = NULL;
    if (!array) {
        array = [NSArray arrayWithObjects:FEString(@"CONCENTRATOR_ALARM"),FEString(@"HOME_SENSOR"),FEString(@"FIRE_SENSOR"), nil];
    }
    return array[type.integerValue];
}

+(NSString *)alarmHandledType:(NSNumber *)type{
    static NSArray *array = NULL;
    if (!array) {
        array = [NSArray arrayWithObjects:FEString(@"NONE_CLEAR"),FEString(@"MANUAL_CLEAR"),FEString(@"AUTO_CLEAR"), nil];
    }
    return array[type.integerValue];
}



@end
