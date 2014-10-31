//
//  FEUserRequestData.m
//  SmartHome
//
//  Created by Seven on 14-10-31.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEUserRequestData.h"
#import "AppDelegate.h"
#import "FECoreDataHandler.h"
#import "CDUser.h"

@implementation FEUserRequestData

-(instancetype)init{
    self = [super init];
    if (self) {
        _userID = FELoginUser.userid;//@(((CDUser *)FELoginUser).userid);
    }
    return self;
}

@end
