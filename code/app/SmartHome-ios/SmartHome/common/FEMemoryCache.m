//
//  FEMemoryCache.m
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEMemoryCache.h"
#import "FECommonDefine.h"

#import "FECheckLog.h"

NSMutableArray *checkLog;

@implementation FEMemoryCache

+(FEMemoryCache *)sharedInstance{
    static FEMemoryCache *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [FEMemoryCache new];
    });
    return instance;
}

-(id)init{
    self = [super init];
    if (self) {
        NSDictionary *duser = kUserDefaultsObjectForKey(kLoginUser);
        if (duser) {
            _user = [[FEUser alloc] initWithDictionary:duser];
            checkLog = [NSMutableArray new];
        }
    }
    return self;
}

-(NSArray *)getCheckLog{
    return checkLog;
}

-(void)addCheckLog:(FECheckLog *)clog{
    [checkLog addObject:clog];
    
}

-(void)removeAllCheckLog{
    [checkLog removeAllObjects];
}

@end
