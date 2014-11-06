//
//  FEModifyPasswordRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-5.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEModifyPasswordRequest.h"

@implementation FEModifyPasswordRequest

//@synthesize newPwd = _newPwd;

-(id)initWithUname:(NSString *)uname oldPwd:(NSString *)oldpwd newPwd:(NSString *)newpwd{
    self = [super initWithMothed:__METHOD_MODIFY_PASSWORD];
    if (self) {
        _userName = uname;
        _oldPwd = oldpwd;
        _pwdNew = newpwd;
    }
    return self;
}

@end
