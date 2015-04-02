//
//  FEMallProductListRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEMallProductListRequest.h"

@implementation FEMallProductListRequest
-(id)initWithUid:(NSNumber *)uid page:(FEPage *)page fillter:(NSArray *)filter{
    self = [super initWithMothed:__METHOD_MALL_PRODUCT];
    if (self) {
        _userID = uid;
        _page = page;
        _filterList = filter;
    }
    return self;
}
@end
