//
//  FESiginResponse.m
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESiginResponse.h"
#import "FEResult.h"
#import "FEUser.h"

@implementation FESiginResponse


-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        id user = response[@"user"];
        if (![user isKindOfClass:[NSNull class]]) {
            _user = [[FEUser alloc] initWithDictionary:user];
        }
    }
    return self;
}

@end
