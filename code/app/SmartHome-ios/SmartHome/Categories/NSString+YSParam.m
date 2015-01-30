//
//  NSString+YSParam.m
//  SmartHome
//
//  Created by Seven on 15-1-29.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "NSString+YSParam.h"

@implementation NSString (YSParam)

+(NSString *)ysParam:(NSDictionary *)dic method:(NSString *)method {
    
    long time = [[NSDate date] timeIntervalSince1970];
    
    NSMutableArray *param = [NSMutableArray new];
    for (NSString *key in dic.allKeys) {
        [param addObject:[NSString stringWithFormat:@"%@:%@",key,dic[key]]];
    }
    NSArray *sortArray = [param sortedArrayUsingSelector:@selector(compare:)];
    NSString *pstring = [sortArray componentsJoinedByString:@","];
    
    NSMutableString *pmstring = [[NSMutableString alloc] initWithString:pstring];
    [pmstring appendFormat:@",method:%@,",method];
    [pmstring appendFormat:@"time:%ld,",time];
    [pmstring appendFormat:@"secret:%@",YSAppSecret];
    NSString *md5string = [pmstring MD5];
    
    NSDictionary *systemdic = @{@"ver":@"1.0",@"sign":md5string,@"key":YSAppKey,@"time":@(time)};
    NSDictionary *paramDic = @{@"system":systemdic,@"method":method,@"params":dic,@"id":@"123456"};
    NSData *data = [NSJSONSerialization dataWithJSONObject:paramDic options:NSJSONWritingPrettyPrinted error:nil];
    return [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
}

@end
