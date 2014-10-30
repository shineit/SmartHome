//
//  FERequestBaseData.h
//  SmartHome
//
//  Created by Seven on 14-10-30.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

#define __MODTHED_SIGIN     @"user/login" //登陆



@interface FERequestBaseData : NSObject

@property (nonatomic, strong, readonly) NSString *method;
@property (nonatomic, strong, readonly) NSDictionary *requestParam;

-(instancetype)initWithMothed:(NSString *)mothed;

@end
