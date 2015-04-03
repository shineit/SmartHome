//
//  FEGetCompanyByIdResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetCompanyByIdResponse.h"

@implementation FEGetCompanyByIdResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _company = [[FECompany alloc] initWithDictionary:obj[@"company"]];
    }
    return self;
}

@end
