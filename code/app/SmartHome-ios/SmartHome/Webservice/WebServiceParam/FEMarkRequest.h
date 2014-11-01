//
//  FEMarkRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

//#import <Foundation/Foundation.h>
#import "FERequestBaseData.h"
@class FEUserMarkRequest;

@interface FEMarkRequest : FERequestBaseData
@property (nonatomic, strong, readonly) NSString *command;
@property (nonatomic, strong, readonly) NSDictionary *userMark;

-(id)initWithCommand:(NSString *)cmd usermark:(FEUserMarkRequest *)mark;

@end
