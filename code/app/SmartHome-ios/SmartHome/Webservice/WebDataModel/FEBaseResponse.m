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
        id object = obj[@"result"];
        if (![object isKindOfClass:[NSNull class]]) {
            _result = [[FEResult alloc] initWithDictionary:obj[@"result"]];
        }
    }
    return self;
}

@end
