//
//  FERelyUserRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@class FEPage;

@interface FERelyUserRequest : FERequestBaseData

@property (nonatomic, strong, readonly) NSDictionary *page;
@property (nonatomic, strong, readonly) NSNumber *userID;
@property (nonatomic, strong, readonly) NSArray *filterList;

-(id)initWithUserID:(NSNumber *)uid page:(FEPage *)page attributes:(NSArray *)attrs method:(NSString *)method;
-(id)initWithUserID:(NSNumber *)uid page:(FEPage *)page attributes:(NSArray *)attrs;

@end
