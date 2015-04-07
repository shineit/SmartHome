//
//  FEADResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-7.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEADResponse.h"


@implementation FEADResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _adList = [self getListFromObject:obj[@"adList"] class:[FEAdvertisement class]];
    }
    return self;
}

@end
