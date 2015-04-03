//
//  FEGetCompanyByIdRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetCompanyByIdRequest.h"

@implementation FEGetCompanyByIdRequest


-(id)initWithCid:(NSNumber *)Cid{
    self = [super initWithMothed:__METHOD_QUERY_COMPANY];
    if (self) {
        _companyID = Cid;
    }
    return self;
}

@end
