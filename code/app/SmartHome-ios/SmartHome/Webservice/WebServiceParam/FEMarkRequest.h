//
//  FEMarkRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

//#import <Foundation/Foundation.h>
#import "FERequestBaseData.h"
@class FEPage;

@interface FEMarkRequest : FERequestBaseData
@property (nonatomic, strong, readonly) NSNumber *userID;
@property (nonatomic, strong, readonly) FEPage *page;

-(id)initWithUserid:(NSNumber *)uid page:(FEPage *)page;

@end
