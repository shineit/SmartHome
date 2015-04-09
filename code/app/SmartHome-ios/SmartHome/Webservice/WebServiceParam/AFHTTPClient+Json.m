//
//  AFHTTPClient+Json.m
//  SmartHome
//
//  Created by Seven on 15-1-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "AFHTTPClient+Json.h"
#import <AFNetworking/AFJSONRequestOperation.h>

@implementation AFHTTPClient (Json)

-(AFHTTPRequestOperation *)POST:(NSString *)path
                     parameters:(NSDictionary *)parameters
                        success:(void (^)(AFHTTPRequestOperation *operation, id responseObject))success
                        failure:(void (^)(AFHTTPRequestOperation *operation, NSError *error))failure{
    NSURLRequest *request = [self requestWithMethod:@"POST" path:path parameters:parameters];
    AFJSONRequestOperation *operation = [AFJSONRequestOperation JSONRequestOperationWithRequest:request success:nil failure:nil];
    [operation setCompletionBlockWithSuccess:success failure:failure];
    AFHTTPRequestOperation *op = [self HTTPRequestOperationWithRequest:request success:nil failure:nil];
    operation.credential = op.credential;
    operation.SSLPinningMode = op.SSLPinningMode;
    operation.allowsInvalidSSLCertificate = op.allowsInvalidSSLCertificate;
    [self enqueueHTTPRequestOperation:operation];
    return operation;
}

-(AFHTTPRequestOperation *)GET:(NSString *)path
                     parameters:(NSDictionary *)parameters
                        success:(void (^)(AFHTTPRequestOperation *operation, id responseObject))success
                        failure:(void (^)(AFHTTPRequestOperation *operation, NSError *error))failure{
    NSURLRequest *request = [self requestWithMethod:@"GET" path:path parameters:parameters];
    AFJSONRequestOperation *operation = [AFJSONRequestOperation JSONRequestOperationWithRequest:request success:nil failure:nil];
    [operation setCompletionBlockWithSuccess:success failure:failure];
    AFHTTPRequestOperation *op = [self HTTPRequestOperationWithRequest:request success:nil failure:nil];
    operation.credential = op.credential;
    operation.SSLPinningMode = op.SSLPinningMode;
    operation.allowsInvalidSSLCertificate = op.allowsInvalidSSLCertificate;
    [self enqueueHTTPRequestOperation:operation];
    return operation;
}

-(AFHTTPRequestOperation *)POST:(NSString *)path
      constructingBodyWithBlock:(void (^)(id <AFMultipartFormData> formData))block
                     parameters:(NSDictionary *)parameters
                        success:(void (^)(AFHTTPRequestOperation *operation, id responseObject))success
                        failure:(void (^)(AFHTTPRequestOperation *operation, NSError *error))failure{
    NSMutableURLRequest *request = [self multipartFormRequestWithMethod:@"POST" path:path parameters:parameters constructingBodyWithBlock:block];
    AFJSONRequestOperation *operation = [AFJSONRequestOperation JSONRequestOperationWithRequest:request success:nil failure:nil];
    [operation setCompletionBlockWithSuccess:success failure:failure];
    AFHTTPRequestOperation *op = [self HTTPRequestOperationWithRequest:request success:nil failure:nil];
    operation.credential = op.credential;
    operation.SSLPinningMode = op.SSLPinningMode;
    operation.allowsInvalidSSLCertificate = op.allowsInvalidSSLCertificate;
    [self enqueueHTTPRequestOperation:operation];
    return operation;
}


@end
