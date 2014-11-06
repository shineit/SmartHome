//
//  FEUserMark.m
//  SmartHome
//
//  Created by Seven on 14-11-2.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEUserMark.h"

@implementation FEUserMark

-(id)initWithUserID:(NSNumber *)uid mark:(NSString *)mark{
    self = [super init];
    if (self) {
        _userID = uid;
        _mark = mark;
    }
    return self;
}

@end
