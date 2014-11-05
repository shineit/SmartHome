//
//  FEWebServiceManager.h
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <AFNetworking/AFHTTPRequestOperationManager.h>
#import "FESiginRequest.h"
#import "FENewsRequest.h"
#import "FEServiceOrederRequest.h"
#import "FEServiceOrderSetRequest.h"
#import "FEMarkSetRequest.h"
#import "FEMarkRequest.h"

@class FESiginResponse;
@class FENewsResponse;
@class FEOrderListResponse;
@class FEBaseResponse;
@class FEOrderSetResponse;
@class FEHistoryAlarmRequest;
@class FEHistoryAlarmResponse;
@class FELogoutRequest;
@class FESigoutResponse;
@class FEModifyPasswordRequest;

@interface FEWebServiceManager : AFHTTPRequestOperationManager

//DEFINE_SINGLETON_FOR_HEADER(FEWebServiceManager);

+(FEWebServiceManager *)sharedInstance;

-(AFHTTPRequestOperation *)siginWithParam:(FESiginRequest *)sdata response:(void (^)(NSError *error,FESiginResponse *user))block;

//sigout
-(AFHTTPRequestOperation *)sigoutWithParam:(FELogoutRequest *)udata response:(void (^)(NSError *error, FESigoutResponse *response))block;

//modify password
-(AFHTTPRequestOperation *)modifyPassword:(FEModifyPasswordRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block;

-(AFHTTPRequestOperation *)news:(FENewsRequest *)ndata response:(void (^)(NSError *error,FENewsResponse *news))block;

-(AFHTTPRequestOperation *)orederList:(FEServiceOrederRequest *)odata response:(void (^)(NSError *error, FEOrderListResponse *response))block;

-(AFHTTPRequestOperation *)orederSet:(FEServiceOrderSetRequest *)odata response:(void (^)(NSError *error, FEOrderSetResponse *response))block;

-(AFHTTPRequestOperation *)markSet:(FEMarkSetRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block;

-(AFHTTPRequestOperation *)markList:(FEMarkRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block;
//history alarm
-(AFHTTPRequestOperation *)historyAlarmList:(FEHistoryAlarmRequest *)hdata reponse:(void (^)(NSError *error, FEHistoryAlarmResponse *response))block;

@end
