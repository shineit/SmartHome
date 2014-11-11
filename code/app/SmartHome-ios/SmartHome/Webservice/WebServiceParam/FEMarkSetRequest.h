//
//  FEMarkSetRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-2.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@class FEUserMark;

@interface FEMarkSetRequest : FERequestBaseData

@property (nonatomic, strong, readonly) NSDictionary *userMark;

-(id)initWithMark:(FEUserMark *)umark;

@end
