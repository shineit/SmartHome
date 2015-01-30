//
//  FEGetCaTokenResponse.m
//  SmartHome
//
//  Created by Seven on 15-1-29.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetCaTokenResponse.h"

@implementation FEGetCaTokenResponse
-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _caToken = [[FECatoken alloc] initWithDictionary:obj[@"caToken"]];
    }
    return self;
}
@end
