//
//  FECheckLogNumRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckLogNumRequest.h"

@implementation FECheckLogNumRequest
-(id)initWithUid:(NSNumber *)uid{
    self = [super initWithMothed:__METHOD_CHECH_LOG_NUMBER];
    if (self) {
        _userID = uid;
    }
    return self;
}
@end
