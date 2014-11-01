//
//  FEUserMarkRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

//#import <Foundation/Foundation.h>
#import "FERequestBaseData.h"

@interface FEUserMarkRequest : FERequestBaseData
@property (nonatomic, strong, readonly) NSNumber *userID;
@property (nonatomic, strong, readonly) NSString *mark;

-(id)initWithuserID:(NSNumber *)userID usermark:(NSString *)mark;

@end
