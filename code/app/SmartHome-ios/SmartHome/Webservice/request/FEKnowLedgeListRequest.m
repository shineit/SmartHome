//
//  FEKnowLedgeListRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEKnowLedgeListRequest.h"

@implementation FEKnowLedgeListRequest

-(id)initWithUid:(NSNumber *)uid page:(FEPage *)page fillter:(NSArray *)filter{
    self = [super initWithMothed:__METHOD_KNOWLEDGE_LIST];
    if (self) {
        _userID = uid;
        _page = page;
        _filterList = filter;
    }
    return self;
}

@end
