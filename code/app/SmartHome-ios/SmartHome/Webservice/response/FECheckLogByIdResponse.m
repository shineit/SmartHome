//
//  FECheckLogByIdResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckLogByIdResponse.h"

@implementation FECheckLogByIdResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _checkLogList = [self getListFromObject:obj[@"checkLogList"] class:[FECheckLog class]];
    }
    return self;
}

@end
