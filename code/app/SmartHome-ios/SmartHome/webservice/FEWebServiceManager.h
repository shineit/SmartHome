//
//  FEWebServiceManager.h
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <AFNetworking/AFHTTPRequestOperationManager.h>
#import "FESiginData.h"
#import "FENewsRequest.h"
#import "FEServiceOrederRequest.h"
#import "FEServiceOrderSetRequest.h"
#import "FEMarkSetRequest.h"
#import "FEMarkRequest.h"
//#import "FEBaseResponse.h"
//#import "FESiginResponse.h"
@class FESiginResponse;
@class FENewsResponse;
@class FEOrderListResponse;
@class FEBaseResponse;

@interface FEWebServiceManager : AFHTTPRequestOperationManager

//DEFINE_SINGLETON_FOR_HEADER(FEWebServiceManager);

+(FEWebServiceManager *)sharedInstance;

-(AFHTTPRequestOperation *)siginWithParam:(FESiginData *)sdata response:(void (^)(NSError *error,FESiginResponse *user))block;

-(AFHTTPRequestOperation *)news:(FENewsRequest *)ndata response:(void (^)(NSError *error,FENewsResponse *news))block;

-(AFHTTPRequestOperation *)orederList:(FEServiceOrederRequest *)odata response:(void (^)(NSError *error, FEOrderListResponse *response))block;

-(AFHTTPRequestOperation *)orederSet:(FEServiceOrderSetRequest *)odata response:(void (^)(NSError *error, FEBaseResponse*response))block;

-(AFHTTPRequestOperation *)markSet:(FEMarkSetRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block;

-(AFHTTPRequestOperation *)markList:(FEMarkRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block;

@end
