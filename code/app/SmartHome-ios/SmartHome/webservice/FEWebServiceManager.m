//
//  FEWebServiceManager.m
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEWebServiceManager.h"
#import "FESiginRequest.h"
#import "FESiginResponse.h"
#import "FENewsResponse.h"
#import "FEOrderListResponse.h"
#import "FEHistoryAlarmRequest.h"
#import "FEOrderSetResponse.h"
#import "FEHistoryAlarmResponse.h"


#define _BASE_URL @"http://192.168.1.101:8080/SmartHome/rest" //@"http://163.125.217.158:9000/SmartHome/rest/"

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

//sigin
-(AFHTTPRequestOperation *)siginWithParam:(FESiginRequest *)sdata response:(void (^)(NSError *, FESiginResponse *))block{
    return [self POST:sdata.method parameters:sdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
//        NSInteger code = [((NSDictionary *)responseObject)[@"result"][@"errorCode"] integerValue];
        FESiginResponse *sresponse = [[FESiginResponse alloc] initWithResponse:responseObject];
        if (sresponse.result.errorCode.integerValue == 0) {
            block(NULL,sresponse);
        }else{
            NSError *error = [[NSError alloc] initWithDomain:nil code:sresponse.result.errorCode.integerValue userInfo:sresponse.result.dictionary];
            block(error,sresponse);
        }
        
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        block(error,nil);
    }];
    
}

//news
-(AFHTTPRequestOperation *)news:(FENewsRequest *)ndata response:(void (^)(NSError *error,FENewsResponse *news))block{
    return [self POST:ndata.method parameters:ndata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSLog(@"news success!");
        FENewsResponse *news = [[FENewsResponse alloc] initWithResponse:responseObject];
        block(NULL,news);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"news fail!");
        block(error,NULL);
    }];
}

//order list
-(AFHTTPRequestOperation *)orederList:(FEServiceOrederRequest *)odata response:(void (^)(NSError *error, FEOrderListResponse *response))block{
    return [self POST:odata.method parameters:odata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FEOrderListResponse *olist = [[FEOrderListResponse alloc] initWithResponse:responseObject];
        block(NULL,olist);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        block(error,NULL);
    }];
}

//set order
-(AFHTTPRequestOperation *)orederSet:(FEServiceOrderSetRequest *)odata response:(void (^)(NSError *error,FEOrderSetResponse *response))block{
    return [self POST:odata.method parameters:odata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        block(NULL,[[FEOrderSetResponse alloc] initWithResponse:responseObject]);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        block(error,NULL);
    }];
}

//set mark
-(AFHTTPRequestOperation *)markSet:(FEMarkSetRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block{
    return [self POST:mdata.method parameters:mdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        block(NULL,[[FEBaseResponse alloc] initWithResponse:responseObject]);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
         block(error,NULL);
    }];
}

//get mark list
-(AFHTTPRequestOperation *)markList:(FEMarkRequest *)mdata response:(void (^)(NSError *errrot, FEBaseResponse *response))block{
    return [self POST:mdata.method parameters:mdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        block(NULL,[FEBaseResponse new]);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        block(error,NULL);
    }];
}

//get History Alarm
-(AFHTTPRequestOperation *)historyAlarmList:(FEHistoryAlarmRequest *)hdata reponse:(void (^)(NSError *error, FEHistoryAlarmResponse *response))block{
    return [self POST:hdata.method parameters:hdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FEHistoryAlarmResponse *hresponse = [[FEHistoryAlarmResponse alloc] initWithResponse:responseObject];
        block(NULL,hresponse);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        block(error,NULL);
    }];
}





@end
