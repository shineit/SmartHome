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
#import "FELogoutRequest.h"
#import "FESigoutResponse.h"
#import "FEModifyPasswordRequest.h"
#import "FESensorListRequest.h"
#import "FESensorListResponse.h"
#import "FESensorBatchDisableRequest.h"
#import "FESensorBatchEnableRequest.h"
#import "FESensorSetRequest.h"
#import "FESensorSetResponse.h"
#import "FEMarkDeletRequest.h"


#define _BASE_URL @"http://192.168.100.189:8080/SmartHome/rest" //@"http://163.125.217.158:9000/SmartHome/rest/"

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
-(AFHTTPRequestOperation *)siginWithParam:(FESiginRequest *)sdata response:(void (^)(NSError *error, FESiginResponse *))block{
    return [self POST:sdata.method parameters:sdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
//        NSInteger code = [((NSDictionary *)responseObject)[@"result"][@"errorCode"] integerValue];
        FESiginResponse *sresponse = [[FESiginResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:sresponse];
        block(NULL,sresponse);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
        block(error,nil);
    }];
    
}

//sigout
-(AFHTTPRequestOperation *)sigoutWithParam:(FELogoutRequest *)udata response:(void (^)(NSError *error, FESigoutResponse *))block{
    return [self POST:udata.method parameters:udata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FESigoutResponse *sresponse = [[FESigoutResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:sresponse];
        block(NULL,sresponse);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
        block(error,nil);
    }];
    
}

//modify password
-(AFHTTPRequestOperation *)modifyPassword:(FEModifyPasswordRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block{
    return [self POST:mdata.method parameters:mdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FEBaseResponse *base = [[FEBaseResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:base];
        block(NULL,base);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        block(error,NULL);
    }];
}

//news
-(AFHTTPRequestOperation *)news:(FENewsRequest *)ndata response:(void (^)(NSError *error,FENewsResponse *news))block{
    return [self POST:ndata.method parameters:ndata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSLog(@"news success!");
        FENewsResponse *news = [[FENewsResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:news];
        block(NULL,news);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"news fail!");
        [self showerror:error];
        block(error,NULL);
    }];
}

//order list
-(AFHTTPRequestOperation *)orederList:(FEServiceOrederRequest *)odata response:(void (^)(NSError *error, FEOrderListResponse *response))block{
    return [self POST:odata.method parameters:odata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FEOrderListResponse *olist = [[FEOrderListResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:olist];
        block(NULL,olist);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
        block(error,NULL);
    }];
}

//set order
-(AFHTTPRequestOperation *)orederSet:(FEServiceOrderSetRequest *)odata response:(void (^)(NSError *error,FEOrderSetResponse *response))block{
    return [self POST:odata.method parameters:odata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        
        FEOrderSetResponse *oset = [[FEOrderSetResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:oset];
        block(NULL,oset);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
        block(error,NULL);
    }];
}

//set mark
-(AFHTTPRequestOperation *)markSet:(FEMarkSetRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block{
    return [self POST:mdata.method parameters:mdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        
        block(NULL,[[FEBaseResponse alloc] initWithResponse:responseObject]);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
         block(error,NULL);
    }];
}

//get mark list
-(AFHTTPRequestOperation *)markList:(FEMarkRequest *)mdata response:(void (^)(NSError *errrot, FEBaseResponse *response))block{
    return [self POST:mdata.method parameters:mdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
//        [self showerrorResponse:operation];
        block(NULL,[FEBaseResponse new]);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
        block(error,NULL);
    }];
}

//delete mark
-(AFHTTPRequestOperation *)markDelete:(FEMarkDeletRequest *)mdata response:(void (^)(NSError *error, FEBaseResponse *response))block{
    return [self POST:mdata.method parameters:mdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FEBaseResponse *response = [[FEBaseResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:response];
        block(NULL,response);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
        block(error,NULL);
    }];
}

//get History Alarm
-(AFHTTPRequestOperation *)historyAlarmList:(FEHistoryAlarmRequest *)hdata reponse:(void (^)(NSError *error, FEHistoryAlarmResponse *response))block{
    return [self POST:hdata.method parameters:hdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FEHistoryAlarmResponse *hresponse = [[FEHistoryAlarmResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:hresponse];
        block(NULL,hresponse);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
        block(error,NULL);
    }];
}

//get sensor list
-(AFHTTPRequestOperation *)sensorList:(FESensorListRequest *)sdata response:(void (^)(NSError *error, FESensorListResponse *response))block{
    return [self POST:sdata.method parameters:sdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FESensorListResponse *response = [[FESensorListResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:response];
        block(NULL,response);
        
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        block(error,NULL);
        [self showerror:error];
    }];
}

//sensor set
-(AFHTTPRequestOperation *)sensorSet:(FESensorSetRequest *)sdata response:(void (^)(NSError *error, FESensorSetResponse *respone))block{
    return [self POST:sdata.method parameters:sdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FESensorSetResponse *response = [[FESensorSetResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:response];
        block(NULL,response);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
        block(error,NULL);
    }];
}

//batch enable
-(AFHTTPRequestOperation *)SensorBatchEnable:(FESensorBatchEnableRequest *)sdata response:(void (^)(NSError *error, FEBaseResponse *respone))block{
    return [self POST:sdata.method parameters:sdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FEBaseResponse *response = [[FEBaseResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:response];
        block(NULL,response);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
        block(error,NULL);
    }];
}

//batch disable
-(AFHTTPRequestOperation *)SensorBatchDisable:(FESensorBatchDisableRequest *)sdata response:(void (^)(NSError *, FEBaseResponse *))block{
    return [self POST:sdata.method parameters:sdata.dictionary success:^(AFHTTPRequestOperation *operation, id responseObject) {
        FEBaseResponse *response = [[FEBaseResponse alloc] initWithResponse:responseObject];
        [self showerrorResponse:response];
        block(NULL,response);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self showerror:error];
        block(error,NULL);
    }];
}

-(void)showerror:(NSError *)error{
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"SmartHome" message:[NSString stringWithFormat:@"%@",error.localizedDescription] delegate:nil cancelButtonTitle:FEString(@"OK") otherButtonTitles:nil];
    [alert show];
}

-(void)showerrorResponse:(FEBaseResponse *)response{
    if (response.result.errorCode.integerValue != 0) {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"错误" message:[NSString stringWithFormat:@"error code %@",response.result.errorCode] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
        [alert show];
    }
}





@end
