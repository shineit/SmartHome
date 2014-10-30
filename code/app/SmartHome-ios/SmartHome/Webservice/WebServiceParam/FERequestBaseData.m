//
//  FERequestBaseData.m
//  SmartHome
//
//  Created by Seven on 14-10-30.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
#import <objc/runtime.h>

@implementation FERequestBaseData

-(instancetype)initWithMothed:(NSString *)mothed{
    self = [super init];
    if (self) {
        _method = mothed;
    }
    return self;
}

-(NSDictionary *)requestParam{
    NSArray *property = [self getAllProperty];
    NSMutableDictionary *dic = [[NSMutableDictionary alloc]init];
    
    for (NSString *name in property) {
        
        if ([name isEqualToString:@"method"]) {
            break;
        }
        
        id item = [self valueForKey:name];
        if ([item isKindOfClass:[NSString class]] || [item isKindOfClass:[NSNumber class]]) {
            dic[name] = item;
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

@end
