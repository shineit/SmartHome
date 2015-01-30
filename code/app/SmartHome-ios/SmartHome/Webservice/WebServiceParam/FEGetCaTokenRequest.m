//
//  FEGetCaTokenRequest.m
//  SmartHome
//
//  Created by Seven on 15-1-29.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetCaTokenRequest.h"

@implementation FEGetCaTokenRequest

-(id)initWithUserID:(NSNumber *)uid phone:(NSString *)phone{
    self = [super initWithMothed:__METHOD_CATOKEN];
    if (self) {
        _userID = uid;
        _phone = phone;
    }
    return self;
}

@end
