//
//  NSObject+Dictionary.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSObject (Dictionary)

@property (nonatomic, strong, readonly) NSDictionary *dictionary;
-(NSArray *)getAllProperty;

@end
