//
//  FECheckLogCreateRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckLogCreateRequest.h"

@implementation FECheckLogCreateRequest


-(id)initWithCheckLogList:(NSArray *)clog{
    self = [super initWithMothed:__METHOD_CHECK_LOG_CREATE];
    if (self) {
        _checkLogList = clog;
    }
    return self;
}

@end
