//
//  FEWebServiceManager.m
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEWebServiceManager.h"
#import "FESiginData.h"

#define _BASE_URL @"http://localhost:8080/SmartHome/rest" //@"http://163.125.217.158:9000/SmartHome/rest/"

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
        self.requestSerializer = [AFJSONRequestSerializer serializer];
    }
    return self;
}


-(AFHTTPRequestOperation *)siginWithParam:(FESiginData *)sdata response:(void (^)(NSError *, FEDataUser *))block{
    return [self POST:sdata.method parameters:sdata.requestParam success:^(AFHTTPRequestOperation *operation, id responseObject) {
        
        FEDataUser *user = [FEDataUser new];
        user.userid = responseObject[@"id"];
        user.username = responseObject[@"firstName"];
        user.password = @"112345";
        block(NULL,user);
        
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"sigin fail %@",error.userInfo);
        block(error,nil);
    }];
    
}


@end
