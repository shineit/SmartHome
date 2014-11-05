//
//  FELogoutRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-5.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FELogoutRequest : FERequestBaseData

@property (nonatomic, strong, readonly) NSString *userName;

-(id)initWithUserName:(NSString *)uname;

@end
