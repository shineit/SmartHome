//
//  FERequestBaseData.h
//  SmartHome
//
//  Created by Seven on 14-10-30.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "NSObject+Dictionary.h"

#define __MODTHED_SIGIN    @"user/login" //登陆
#define __GET_NEWS         @"news/list"  //news 


@interface FERequestBaseData : NSObject

@property (nonatomic, strong, readonly) NSString *method;

-(instancetype)initWithMothed:(NSString *)mothed;

@end
