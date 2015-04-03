//
//  FEGetCompanyRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetCompanyRequest.h"

@implementation FEGetCompanyRequest


-(id)initWithUid:(NSNumber *)uid page:(FEPage *)page{
    self = [super initWithMothed:__METHOD_COMPANY_LIST];
    if (self) {
        _userID = uid;
        _page = page;
    }
    return self;
}

@end
