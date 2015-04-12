//
//  FENumberResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FENumberResponse.h"

@implementation FENumberResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _numList = [self getListFromObject:obj[@"numList"] class:[FEBageNumber class]];
    }
    return self;
}

@end
