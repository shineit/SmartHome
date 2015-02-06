//
//  FEWebServiceManager.h
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <AFNetworking/AFHTTPClient.h>
#import "FESiginRequest.h"
#import "FENewsRequest.h"
#import "FEServiceOrederRequest.h"
#import "FEServiceOrderSetRequest.h"
#import "FEMarkSetRequest.h"
#import "FEMarkRequest.h"
#import "FESensorListRequest.h"
#import "FEGetCaTokenRequest.h"
#import "FEGetCaTokenResponse.h"
#import "FESensorOperationRequest.h"

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
@class FESensorListResponse;
@class FESensorBatchDisableRequest;
@class FESensorBatchEnableRequest;
@class FESensorSetRequest;
@class FESensorSetResponse;
@class FEMarkDeletRequest;
@class FEUserMarkResponse;


@interface FEWebServiceManager : AFHTTPClient

//DEFINE_SINGLETON_FOR_HEADER(FEWebServiceManager);

+(FEWebServiceManager *)sharedInstance;

-(AFHTTPRequestOperation *)siginWithParam:(FESiginRequest *)sdata response:(void (^)(NSError *error,FESiginResponse *user))block;

//sigout
-(AFHTTPRequestOperation *)sigoutWithParam:(FELogoutRequest *)udata response:(void (^)(NSError *error, FESigoutResponse *response))block;

//get ca token
-(AFHTTPRequestOperation *)getCatokenWithParam:(FEGetCaTokenRequest *)udata response:(void (^)(NSError *error, FEGetCaTokenResponse *response))block;

//modify password
-(AFHTTPRequestOperation *)modifyPassword:(FEModifyPasswordRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block;

-(AFHTTPRequestOperation *)news:(FENewsRequest *)ndata response:(void (^)(NSError *error,FENewsResponse *news))block;

-(AFHTTPRequestOperation *)orederList:(FEServiceOrederRequest *)odata response:(void (^)(NSError *error, FEOrderListResponse *response))block;

-(AFHTTPRequestOperation *)orederSet:(FEServiceOrderSetRequest *)odata response:(void (^)(NSError *error, FEOrderSetResponse *response))block;

-(AFHTTPRequestOperation *)markSet:(FEMarkSetRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block;

-(AFHTTPRequestOperation *)markList:(FEMarkRequest *)mdata response:(void (^)(NSError *error, FEUserMarkResponse *response))block;
//mark delet
-(AFHTTPRequestOperation *)markDelete:(FEMarkDeletRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block;

//history alarm
-(AFHTTPRequestOperation *)historyAlarmList:(FEHistoryAlarmRequest *)hdata reponse:(void (^)(NSError *error, FEHistoryAlarmResponse *response))block;
//sensor list
-(AFHTTPRequestOperation *)sensorList:(FESensorListRequest *)sdata response:(void (^)(NSError *error, FESensorListResponse *response))block;
//sensor set
-(AFHTTPRequestOperation *)sensorSet:(FESensorSetRequest *)sdata response:(void (^)(NSError *error, FESensorSetResponse *response))block;

//sensor batch enable
-(AFHTTPRequestOperation *)SensorBatchEnable:(FESensorOperationRequest *)sdata response:(void (^)(NSError *error, FEBaseResponse *response))block;

//sensor batch disable
-(AFHTTPRequestOperation *)SensorBatchDisable:(FESensorOperationRequest *)sdata response:(void (^)(NSError *error, FEBaseResponse *response))block;




@end
