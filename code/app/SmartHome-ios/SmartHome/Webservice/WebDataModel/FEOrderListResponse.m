//
//  FEOrderListResponse.m
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEOrderListResponse.h"
#import "FEOrder.h"

@implementation FEOrderListResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        
        NSArray *orderList = obj[@"orderList"];
        if (![orderList isKindOfClass:[NSNull class]] && orderList.count) {
            NSMutableArray *orderArray = [NSMutableArray new];
            for (NSDictionary *order in orderList) {
                [orderArray addObject:[[FEOrder alloc] initWithDictionary:order]];
            }
            _orderList = orderArray;
        }
        
    }
    return self;
}

@end
