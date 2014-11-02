//
//  FEServiceOrederRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
@class FEPage;

@interface FEServiceOrederRequest : FERequestBaseData

@property (nonatomic, strong, readonly) NSDictionary *page;
@property (nonatomic, strong, readonly) NSNumber *userID;
@property (nonatomic, strong, readonly) NSArray *filterList;

-(id)initWithPage:(FEPage *)page attribute:(NSArray *)attr userID:(NSNumber *)uid;

@end
