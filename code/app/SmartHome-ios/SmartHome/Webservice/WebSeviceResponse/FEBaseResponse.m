//
//  FEBaseResponse.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEResult.h"

@implementation FEBaseResponse


-(id)initWithResponse:(id)obj{
    self = [super init];
    if (self) {
//        if (![obj isKindOfClass:[NSNull class]]) {
        _result = [[FEResult alloc] initWithDictionary:obj];
//        }
    }
    return self;
}

-(NSArray *)getListFromObject:(id)obj class:(Class)cls{
    NSArray *objects = obj;
    NSMutableArray *toObjectList = NULL;
    if (objects && ![objects isKindOfClass:[NSNull class]]) {
        toObjectList = [NSMutableArray new];
        for (id item in objects) {
            [toObjectList addObject:[[cls alloc] initWithDictionary:item]];
        }
    }
    return toObjectList;
}


@end
