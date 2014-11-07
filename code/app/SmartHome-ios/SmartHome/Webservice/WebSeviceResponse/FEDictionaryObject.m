//
//  FEDictionaryObject.m
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDictionaryObject.h"
#import "NSObject+Dictionary.h"

@implementation FEDictionaryObject

-(id)initWithDictionary:(NSDictionary *)dictionary{
    self = [super init];
    if (self) {
        NSArray *property = [self getAllProperty];
        for (NSString *key in property) {
            if (![dictionary[key] isKindOfClass:[NSNull class]] && dictionary[key] != nil) {
                @try {
                    [self setValue:dictionary[key] forKey:key];
                }
                @catch (NSException *exception) {
                    NSLog(@"error create object %@",exception);
                }
                @finally {
                    
                }
                
            }
        }
    }
    return self;
}


@end
