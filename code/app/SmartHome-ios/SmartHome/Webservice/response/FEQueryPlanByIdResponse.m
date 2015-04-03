//
//  FEQueryPlanByIdResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEQueryPlanByIdResponse.h"

@implementation FEQueryPlanByIdResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _plan = [[FESensorPlan alloc] initWithDictionary:obj[@"plan"]];
    }
    return self;
}

@end
