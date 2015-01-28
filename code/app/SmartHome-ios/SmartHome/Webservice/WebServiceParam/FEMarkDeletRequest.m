//
//  FEMarkDeletRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-6.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEMarkDeletRequest.h"
#import "FEUserMark.h"

@implementation FEMarkDeletRequest

-(id)initWithMark:(FEUserMark *)mark{
    self = [super initWithMothed:__METHOD_MARK_DELET];
    if (self) {
        _userMark = mark;
    }
    return self;
}


@end
