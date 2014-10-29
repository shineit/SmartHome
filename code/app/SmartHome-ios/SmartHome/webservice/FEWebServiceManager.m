//
//  FEWebServiceManager.m
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEWebServiceManager.h"

#define _BASE_URL @"http://163.125.217.158:9000/SmartHome/rest/"

@implementation FEWebServiceManager

+(FEWebServiceManager *)sharedInstance{
    static FEWebServiceManager *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = (FEWebServiceManager *)[[self alloc] initWithBaseURL:[NSURL URLWithString:_BASE_URL]];
    });
    return instance;
}


-(instancetype)initWithBaseURL:(NSURL *)url{
    self = [super initWithBaseURL:url];
    if (self) {
        
    }
    return self;
}


-(AFHTTPRequestOperation *)siginWithParam:(NSString *)param response:(void (^)(NSError *, FEDataUser *))block{
    return [self GET:@"sensor/hello" parameters:nil success:^(AFHTTPRequestOperation *operation, id responseObject) {
        if ([responseObject isKindOfClass:[NSDictionary class]]) {
            FEDataUser *user = [FEDataUser new];
            user.userid = responseObject[@"id"];
            user.username = responseObject[@"firstName"];
            user.password = @"112345";
            block(NULL,user);
        }
        
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"sigin fail %@",error.userInfo);
        block(error,nil);
    }];
    
}


@end
