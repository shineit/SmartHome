//
//  FENewsResponse.m
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FENewsResponse.h"
#import "FEResult.h"

@implementation FENewsResponse

-(id)initWithResponse:(id)responseObj{
    
//    FEResult *result = [[FEResult alloc] initWithDictionary:responseObj[@"result"]];
    self = [super initWithResponse:responseObj];
    if (self) {
        
    }
    return self;
}

@end
