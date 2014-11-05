//
//  FELogoutRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-5.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FELogoutRequest.h"

@implementation FELogoutRequest

-(id)initWithUserName:(NSString *)uname{
    self = [super initWithMothed:__METHOD_SIGOUT];
    if (self) {
        _userName = uname;
    }
    return self;
}

@end
