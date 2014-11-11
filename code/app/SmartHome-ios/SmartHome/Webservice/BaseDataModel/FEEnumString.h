//
//  FEEnumString.h
//  SmartHome
//
//  Created by Seven on 14-11-10.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FEEnumString : NSObject

+(NSString *)alarmType:(NSNumber *)type;

+(NSString *)deviceType:(NSNumber *)type;

+(NSString *)alarmHandledType:(NSNumber *)type;



@end
