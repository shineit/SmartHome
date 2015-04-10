//
//  FECheckLogByIdResponse.h
//  SmartHome
//
//  Created by Seven on 15-4-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#include "FECheckLog.h"

@interface FECheckLogByIdResponse : FEBaseResponse

@property (nonatomic, strong, readonly) NSArray *checkLogList;

@end
