//
//  FEOrder.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEOrder : SSObject

@property (nonatomic, strong, readonly) NSString *orderID;
@property (nonatomic, strong, readonly) NSString *orderName;
@property (nonatomic, strong, readonly) NSNumber *orderType;
@property (nonatomic, strong, readonly) NSString *content;
@property (nonatomic, strong, readonly) NSString *creator;
@property (nonatomic, strong, readonly) NSNumber *createTime;
@property (nonatomic, strong, readonly) NSString *contactName;
@property (nonatomic, strong, readonly) NSString *phoneNum;
@property (nonatomic, strong, readonly) NSString *contactAddr;
@property (nonatomic, strong, readonly) NSNumber *orderStatus;
@property (nonatomic, strong, readonly) NSString *handler;
@property (nonatomic, strong, readonly) NSString *handleResult;
@property (nonatomic, strong, readonly) NSNumber *handleTime;

-(id)initWithOrderID:(NSString *)oid name:(NSString *)oname type:(NSNumber *)otype content:(NSString *)content creater:(NSString *)creater time:(NSNumber *)time contactname:(NSString *)cname phone:(NSString *)phone address:(NSString *)addr status:(NSNumber *)stat handler:(NSString *)hand handResult:(NSString *)hresult handTime:(NSNumber *)htime;

@end
