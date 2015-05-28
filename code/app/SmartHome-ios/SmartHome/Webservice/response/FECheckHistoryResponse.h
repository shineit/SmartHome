//
//  FECheckHistoryResponse.h
//  SmartHome
//
//  Created by Seven on 15-5-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FECheckLog.h"

@interface FECheckHistoryResponse : FEBaseResponse
@property (nonatomic, strong) NSArray *checkLogList;
@end
