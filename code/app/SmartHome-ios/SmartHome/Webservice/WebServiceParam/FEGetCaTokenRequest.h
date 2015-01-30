//
//  FEGetCaTokenRequest.h
//  SmartHome
//
//  Created by Seven on 15-1-29.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FEGetCaTokenRequest : FERequestBaseData

@property (nonatomic, strong) NSNumber *userID;
@property (nonatomic, strong) NSString *phone;

-(id)initWithUserID:(NSNumber *)uid phone:(NSString *)phone;

@end
