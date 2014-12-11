//
//  FEControlObject.h
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FEControlObject : NSObject

@property (nonatomic, strong) NSString *name;
@property (nonatomic, strong) NSString *imageName;
@property (nonatomic, strong) NSArray *children;

- (id)initWithName:(NSString *)name children:(NSArray *)array;

+ (id)dataObjectWithName:(NSString *)name children:(NSArray *)children;

- (void)addChild:(id)child;
- (void)removeChild:(id)child;

@end
