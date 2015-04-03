//
//  FECheckListRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckListRequest.h"

@implementation FECheckListRequest

-(id)initWithCid:(NSNumber *)cid{
    self = [super initWithMothed:__METHOD_CHECK_LIST];
    if (self) {
        _companyID = cid;
    }
    return self;
}

@end
