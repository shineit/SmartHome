//
//  FEBaseResponse.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <SSCommon-Utilities/SSObject.h>
#import "FEResult.h"
@class FEResult;

@interface FEBaseResponse : SSObject

@property (nonatomic, strong, readonly) FEResult *result;

-(id)initWithResponse:(id)obj;


@end
