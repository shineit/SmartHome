//
//  FESiginData.h
//  SmartHome
//
//  Created by Seven on 14-10-29.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FESiginRequest : FERequestBaseData
@property (nonatomic, strong, readonly) NSString *userName;
@property (nonatomic, strong, readonly) NSString *password;
@property (nonatomic, strong, readonly) NSString *clientType;
@property (nonatomic, strong, readonly) NSString *clientVersion;
@property (nonatomic, strong, readonly) NSString *devToken;

-(instancetype)initWtihUserName:(NSString *)name password:(NSString *)password clientType:(NSString *)type clientVersion:(NSString *)version devToken:(NSString *)dToken;

@end
