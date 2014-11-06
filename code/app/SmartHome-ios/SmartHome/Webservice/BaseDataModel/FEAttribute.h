//
//  FEAttribute.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDictionaryObject.h"

@interface FEAttribute : FEDictionaryObject

@property (nonatomic, strong, readonly) NSString *attrName;
@property (nonatomic, strong, readonly) NSString *attrValue;

-(id)initWithAttrName:(NSString *)name value:(NSString *)value;

@end
