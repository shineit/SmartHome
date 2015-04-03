//
//  FEQueryPlanByIdRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEQueryPlanByIdRequest.h"

@implementation FEQueryPlanByIdRequest

-(id)initWithPid:(NSNumber *)pid{
    self = [super init];
    if (self) {
        _planID = pid;
    }
    return self;
}

@end
