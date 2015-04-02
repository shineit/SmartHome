//
//  FERequestBaseData.m
//  SmartHome
//
//  Created by Seven on 14-10-30.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@implementation FERequestBaseData

-(instancetype)initWithMothed:(NSString *)mothed{
    self = [super init];
    if (self) {
        _method = mothed;
        _type = POST;
    }
    return self;
}


@end
