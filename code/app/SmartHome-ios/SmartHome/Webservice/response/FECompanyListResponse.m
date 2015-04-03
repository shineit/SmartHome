//
//  FECompanyListResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECompanyListResponse.h"

@implementation FECompanyListResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _companyList = [self getListFromObject:[obj valueForKey:@"companyList"] class:[FECompany class]];
    }
    return self;
}

@end
