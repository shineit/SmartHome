//
//  FEHistoryAlarmResponse.h
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"

@interface FEHistoryAlarmResponse : FEBaseResponse

@property (nonatomic, strong, readonly) NSArray *alarmList;

@end
