//
//  FESeviceOrederRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESeviceOrederRequest.h"
#import "FEPage.h"
#import "FEAttribute.h"

@implementation FESeviceOrederRequest

-(id)initWithPage:(FEPage *)page attribute:(NSArray *)attr userID:(NSNumber *)uid{
    self = [super initWithMothed:__SEVICE_ORDER];
    if (self) {
        _page = page.dictionary;
        NSMutableArray *attrarray = [NSMutableArray array];
        for (NSObject *item in attr) {
            [attrarray addObject:item.dictionary];
        }
        _filterList = attrarray;
        _userID = uid;
    }
    return self;
}

@end
