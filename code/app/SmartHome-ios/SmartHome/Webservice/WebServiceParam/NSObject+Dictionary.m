//
//  NSObject+Dictionary.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "NSObject+Dictionary.h"
#import <objc/runtime.h>

@implementation NSObject (Dictionary)


- (NSDictionary *)dictionary{
    NSArray *property = [self getAllProperty];
    NSMutableDictionary *dic = [[NSMutableDictionary alloc]init];
    
    for (NSString *name in property) {
        
        if ([name isEqualToString:@"method"]) {
            break;
        }
        
        id item = [self valueForKey:name];
        if ([item isKindOfClass:[NSString class]] || [item isKindOfClass:[NSNumber class]] || [item isKindOfClass:[NSDictionary class]] || [item isKindOfClass:[NSArray class]]) {
            dic[name] = item;
        }else{
            dic[name] = [item dictionary];
        }
        
    }
    return dic;
}

//获取类的所有的 属性的名字
-(NSArray *)getAllProperty
{
    unsigned int count;
    objc_property_t *properties = class_copyPropertyList([self class], &count);
    NSMutableArray *rv = [NSMutableArray array];
    
    unsigned int i;
    for (i = 0; i < count; i++)
    {
        objc_property_t property = properties[i];
        NSString *name = @(property_getName(property));
        [rv addObject:name];
    }
    free(properties);
    return rv;
}

- (id)copyWithZone:(NSZone *)zone
{
    id copy = [[[self class] alloc] init];
    
    if (copy)
    {
        // Copy NSObject subclasses
        NSArray *property = [copy getAllProperty];
        for (NSString *name in property) {
            [copy setValue:[[self valueForKey:name] copyWithZone:zone] forKey:name];
        }
    }
    
    return copy;
}

@end
