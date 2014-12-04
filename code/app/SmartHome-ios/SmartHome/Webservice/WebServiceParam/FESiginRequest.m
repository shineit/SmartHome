//
//  FESiginData.m
//  SmartHome
//
//  Created by Seven on 14-10-29.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESiginRequest.h"

@implementation FESiginRequest


-(instancetype)initWtihUserName:(NSString *)name password:(NSString *)password clientType:(NSString *)type clientVersion:(NSString *)version devToken:(NSString *)dToken push_id:(NSString *)pid push_userid:(NSString *)p_uid push_channelID:(NSString *)p_cid; {
    self = [super initWithMothed:__METHOD_SIGIN];
    if (self) {
        _userName = name;
        _password = password;
        _clientType = type;
        _clientVersion = version;
        _devToken = dToken;
        _push_appID = pid;
        _push_userID = p_uid;
        _push_channelID = p_cid;
    }
    return self;
}



@end
