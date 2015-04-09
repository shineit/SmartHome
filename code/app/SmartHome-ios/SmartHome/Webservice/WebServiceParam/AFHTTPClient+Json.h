//
//  AFHTTPClient+Json.h
//  SmartHome
//
//  Created by Seven on 15-1-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "AFHTTPClient.h"

@interface AFHTTPClient (Json)
-(AFHTTPRequestOperation *)POST:(NSString *)path
                     parameters:(NSDictionary *)parameters
                        success:(void (^)(AFHTTPRequestOperation *operation, id responseObject))success
                        failure:(void (^)(AFHTTPRequestOperation *operation, NSError *error))failure;
-(AFHTTPRequestOperation *)GET:(NSString *)path
                    parameters:(NSDictionary *)parameters
                       success:(void (^)(AFHTTPRequestOperation *operation, id responseObject))success
                       failure:(void (^)(AFHTTPRequestOperation *operation, NSError *error))failure;

-(AFHTTPRequestOperation *)POST:(NSString *)path
      constructingBodyWithBlock:(void (^)(id <AFMultipartFormData> formData))block
                     parameters:(NSDictionary *)parameters
                        success:(void (^)(AFHTTPRequestOperation *operation, id responseObject))success
                        failure:(void (^)(AFHTTPRequestOperation *operation, NSError *error))failure;
@end
