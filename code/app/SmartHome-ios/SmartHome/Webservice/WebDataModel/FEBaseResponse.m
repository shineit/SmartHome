//
//  FEBaseResponse.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"

@implementation FEBaseResponse

-(id)initWithResult:(FEResult *)result{
    self = [super init];
    if (self) {
        _result = result;
    }
    return self;
}

@end
