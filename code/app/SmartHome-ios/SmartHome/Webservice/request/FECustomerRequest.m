//
//  FECustomerRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECustomerRequest.h"

@implementation FECustomerRequest

-(id)initWithUid:(NSNumber *)uid{
    self = [super initWithMothed:__METHOD_CUSTOMER];
    if (self) {
        _userID = uid;
    }
    return self;
}

@end
