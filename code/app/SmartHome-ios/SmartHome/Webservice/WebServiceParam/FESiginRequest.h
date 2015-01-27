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
@property (nonatomic, strong, readonly) NSString *push_appID;
@property (nonatomic, strong, readonly) NSString *push_userID;
@property (nonatomic, strong, readonly) NSString *push_channelID;


-(instancetype)initWtihUserName:(NSString *)name password:(NSString *)password clientType:(NSString *)type clientVersion:(NSString *)version devToken:(NSString *)dToken push_id:(NSString *)pid push_userid:(NSString *)p_uid push_channelID:(NSString *)p_cid;

@end
