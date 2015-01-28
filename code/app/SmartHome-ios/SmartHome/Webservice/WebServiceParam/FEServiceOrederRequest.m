//
//  FEServiceOrederRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEServiceOrederRequest.h"
#import "FEPage.h"
#import "FEAttribute.h"

@implementation FEServiceOrederRequest

-(id)initWithPage:(FEPage *)page attribute:(NSArray *)attr userID:(NSNumber *)uid{
    self = [super initWithMothed:__METHOD_SEVICE_ORDER];
    if (self) {
        _page = page;
        _filterList = attr;
        _userID = uid;
    }
    return self;
}

@end
