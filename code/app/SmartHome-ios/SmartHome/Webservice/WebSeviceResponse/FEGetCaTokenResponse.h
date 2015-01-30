//
//  FEGetCaTokenResponse.h
//  SmartHome
//
//  Created by Seven on 15-1-29.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FECatoken.h"

@interface FEGetCaTokenResponse : FEBaseResponse
@property (nonatomic, strong, readonly) FECatoken *caToken;
@end
