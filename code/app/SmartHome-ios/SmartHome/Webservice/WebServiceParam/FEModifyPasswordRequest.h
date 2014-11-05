//
//  FEModifyPasswordRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-5.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FEModifyPasswordRequest : FERequestBaseData

@property (nonatomic, strong, readonly) NSString *userName;
@property (nonatomic, strong, readonly) NSString *oldPwd;
//@property (nonatomic, strong, readonly) NSString *newPwd;

-(id)initWithUname:(NSString *)uname oldPwd:(NSString *)oldpwd newPwd:(NSString *)newpwd;

@end
