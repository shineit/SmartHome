//
//  FEUser.h
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <SSCommon-Utilities/SSObject.h>

@interface FEUser : SSObject
@property (nonatomic, strong, readonly) NSArray *listAttr;
@property (nonatomic, strong, readonly) NSNumber *role;
@property (nonatomic, strong, readonly) NSNumber *userID;
@property (nonatomic, strong, readonly) NSString *userName;
@property (nonatomic, strong, readonly) NSString *devToken;
@property (nonatomic, strong, readonly) NSString *push_appID;
@property (nonatomic, strong, readonly) NSString *push_userID;
@property (nonatomic, strong, readonly) NSString *push_channelID;

@end
