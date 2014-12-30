//
//  FEMarkDeletRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-6.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
@class FEUserMark;
@interface FEMarkDeletRequest : FERequestBaseData

@property (nonatomic, strong, readonly) NSDictionary *userMark;

-(id)initWithMark:(FEUserMark *)mark;

@end
