//
//  FEOrder.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEOrder.h"

@implementation FEOrder

-(id)initWithOrderID:(NSString *)oid name:(NSString *)oname type:(NSNumber *)otype content:(NSString *)content creater:(NSString *)creater time:(NSNumber *)time contactname:(NSString *)cname phone:(NSString *)phone address:(NSString *)addr status:(NSNumber *)stat handler:(NSString *)hand handResult:(NSString *)hresult handTime:(NSNumber *)htime{
    self = [super init];
    if (self) {
        _orderID = oid;
        _orderName = oname;
        _orderType = otype;
        _content = content;
        _creator = creater;
        _createTime = time;
        _contactName = cname;
        _phoneNum = phone;
        _contactAddr = addr;
        _orderStatus = stat;
        _handler = hand;
        _handleResult = hresult;
        _handleTime = htime;
        
    }
    return self;
}

@end
