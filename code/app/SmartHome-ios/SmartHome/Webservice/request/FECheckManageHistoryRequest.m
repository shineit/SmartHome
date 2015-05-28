//
//  FECheckManageHistoryRequest.m
//  SmartHome
//
//  Created by Seven on 15-5-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckManageHistoryRequest.h"

@implementation FECheckManageHistoryRequest
-(id)initWithUid:(NSNumber *)uid company:(NSNumber *)cid page:(FEPage *)page filer:(NSArray *)filter{
    self = [super initWithMothed:__METHOD_CHECK_HISTORY];
    if (self) {
        _userID = uid;
        _companyID = cid;
        _page = page;
        _filterList = filter;
    }
    return self;
}

@end
