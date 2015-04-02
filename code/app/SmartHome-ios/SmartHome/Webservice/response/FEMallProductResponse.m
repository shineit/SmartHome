//
//  FEMallProductResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEMallProductResponse.h"

@implementation FEMallProductResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _productList = [self getListFromObject:[obj valueForKey:@"productList"] class:[FEProduct class]];
    }
    return self;
}

@end
