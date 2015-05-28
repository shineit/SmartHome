//
//  FEFireAlarmHistoryResponse.h
//  SmartHome
//
//  Created by Seven on 15-5-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEFireAlarm.h"

@interface FEFireAlarmHistoryResponse : FEBaseResponse
@property (nonatomic, strong, readonly) NSArray *fireAlarmList;
@end
