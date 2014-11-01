//
//  FEUserMarkRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEUserMarkRequest.h"

@implementation FEUserMarkRequest

-(id)initWithuserID:(NSNumber *)userID usermark:(NSString *)mark{
    self = [super initWithMothed:@""];
    if (self) {
        _userID = userID;
        _mark = mark;
    }
    return self;
}

@end
