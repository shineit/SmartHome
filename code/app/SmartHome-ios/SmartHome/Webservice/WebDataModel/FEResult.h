//
//  FEResult.h
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDictionaryObject.h"
//#import "NSObject+Dictionary.h"

@interface FEResult : FEDictionaryObject
@property (nonatomic, strong, readonly) NSNumber *errorCode;
@property (nonatomic, strong, readonly) NSString *errorMsg;
@property (nonatomic, strong, readonly) NSString *obj;

@end
