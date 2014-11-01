//
//  FEAttribute.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEAttribute.h"

@implementation FEAttribute


-(id)initWithAttrName:(NSString *)name value:(NSString *)value{
    self = [super init];
    if (self) {
        _attrName = name;
        _attrValue = value;
    }
    return self;
}


@end
