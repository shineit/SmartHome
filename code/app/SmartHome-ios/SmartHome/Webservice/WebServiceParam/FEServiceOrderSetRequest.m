//
//  FEServiceOrderSetRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEServiceOrderSetRequest.h"
#import "FEOrder.h"

@implementation FEServiceOrderSetRequest

-(id)initWithCmd:(NSString *)cmd serviceOrder:(FEOrder *)order{
    self = [super initWithMothed:__METHOD_SEVICE_ORDER_SET];
    if (self) {
        _command = cmd;
        _serviceOrder = order;
    }
    return self;
}

@end
