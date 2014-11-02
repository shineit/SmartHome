//
//  FEWebServiceManager.h
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <AFNetworking/AFHTTPRequestOperationManager.h>
#import "FEDataUser.h"
#import "FESiginData.h"
#import "FENewsRequest.h"
#import "FEDataNew.h"
#import "FEServiceOrederRequest.h"
#import "FEServiceOrderSetRequest.h"
#import "FEBaseResponse.h"

@interface FEWebServiceManager : AFHTTPRequestOperationManager

//DEFINE_SINGLETON_FOR_HEADER(FEWebServiceManager);

+(FEWebServiceManager *)sharedInstance;

-(AFHTTPRequestOperation *)siginWithParam:(FESiginData *)sdata response:(void (^)(NSError *error,FEDataUser *user))block;
-(AFHTTPRequestOperation *)news:(FENewsRequest *)ndata response:(void (^)(NSError *error,FEDataNew *user))block;

-(AFHTTPRequestOperation *)orederList:(FEServiceOrederRequest *)odata response:(void (^)(NSError *error, FEBaseResponse*response))block;

-(AFHTTPRequestOperation *)orederSet:(FEServiceOrderSetRequest *)odata response:(void (^)(NSError *error, FEBaseResponse*response))block;

@end
