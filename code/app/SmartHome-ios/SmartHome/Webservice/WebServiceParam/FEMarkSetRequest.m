//
//  FEMarkSetRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-2.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEMarkSetRequest.h"
#import "FEUserMark.h"

@implementation FEMarkSetRequest

-(id)initWithCommand:(NSString *)cmd mark:(FEUserMark *)umark{
    self = [super initWithMothed:__MARK_SET];
    if (self) {
        _command = cmd;
        _userMark = umark.dictionary;
    }
    return self;
}

@end
