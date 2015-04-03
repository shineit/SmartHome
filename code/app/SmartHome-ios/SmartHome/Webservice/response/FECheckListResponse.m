//
//  FECheckListResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckListResponse.h"

@implementation FECheckListResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _checkItemList = [self getListFromObject:obj[@"checkItemList"] class:[FECheckItem class]];
    }
    return self;
}

@end
