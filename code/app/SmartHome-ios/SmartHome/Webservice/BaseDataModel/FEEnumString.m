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
        array = [NSArray arrayWithObjects:FEString(@"UNKNOW"),FEString(@"OFFLINE_ALARM"),FEString(@"FAULT_ALARM"),FEString(@"SUBPRESSURE_ALARM"),FEString(@"WARN_ALARM"),FEString(@"ERROR_ALARM"),FEString(@"FEEDBACK_ALARM"),FEString(@"ACTION_ALARM"),FEString(@"RESET_ALARM"),FEString(@"SETUP_ALARM"),FEString(@"REMOVE_ALARM"), nil];
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
