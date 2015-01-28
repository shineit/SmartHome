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

-(id)initWithMark:(FEUserMark *)umark{
    self = [super initWithMothed:__METHOD_MARK_ADD];
    if (self) {
        _userMark = umark;
    }
    return self;
}

@end
