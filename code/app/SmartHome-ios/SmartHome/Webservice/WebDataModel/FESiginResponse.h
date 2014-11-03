//
//  FESiginResponse.h
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEUser.h"
#import "FEResult.h"

@interface FESiginResponse : FEBaseResponse

@property (nonatomic, strong, readonly) FEUser *user;

-(id)initWithResponse:(id)response;

@end
