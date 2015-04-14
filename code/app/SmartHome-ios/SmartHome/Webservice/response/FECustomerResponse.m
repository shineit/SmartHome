//
//  FECustomerResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECustomerResponse.h"

@implementation FECustomerResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _customer = [[FECustomer alloc] initWithDictionary:obj[@"customer"]];
    }
    return self;
}

@end
