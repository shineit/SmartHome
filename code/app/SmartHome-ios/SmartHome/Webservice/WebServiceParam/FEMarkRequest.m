//
//  FEMarkRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEMarkRequest.h"
#import "FEPage.h"


@implementation FEMarkRequest

-(id)initWithUserid:(NSNumber *)uid page:(FEPage *)page{
    self = [super initWithMothed:__METHOD_MARK_LIST];
    if (self) {
        _userID = uid;
        _page = page;
    }
    return self;
}

@end
