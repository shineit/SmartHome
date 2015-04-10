//
//  FEADRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-7.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEADRequest.h"

@implementation FEADRequest

-(id)initWithUid:(NSNumber *)uid page:(FEPage *)page filter:(NSArray *)filerList{
    self = [super initWithMothed:__METHOD_MALL_AD];
    if (self) {
        _userID = uid;
        _page = page;
        _filerList = filerList;
    }
    return self;
}

@end
