//
//  FECheckHistoryResponse.m
//  SmartHome
//
//  Created by Seven on 15-5-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckHistoryResponse.h"

@implementation FECheckHistoryResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _checkLogList = [self getListFromObject:obj[@"checkLogList"] class:[FECheckLog class]];
    }
    return self;
}

@end
