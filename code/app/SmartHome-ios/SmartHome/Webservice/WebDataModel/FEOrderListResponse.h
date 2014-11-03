//
//  FEOrderListResponse.h
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"

@interface FEOrderListResponse : FEBaseResponse

@property (nonatomic, strong, readonly) NSArray *orderList;

@end
