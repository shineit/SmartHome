//
//  FECheckLogByIdRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckLogByIdRequest.h"

@implementation FECheckLogByIdRequest

-(id)initWithUid:(NSNumber *)uid comanyId:(NSNumber *)cid page:(FEPage *)page filterList:(NSArray *)flist{
    self = [super initWithMothed:__METHOD_CHECK_LOG_GET];
    if (self) {
        _userID = uid;
        _companyID = cid;
        _page = page;
        _filterList = flist;
    }
    return self;
}

@end
