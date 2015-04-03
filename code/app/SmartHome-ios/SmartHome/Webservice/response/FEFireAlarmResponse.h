//
//  FEFireAlarmResponse.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEFireAlarm.h"

@interface FEFireAlarmResponse : FEBaseResponse

@property (nonatomic, strong, readonly) NSArray *fireAlarmList;

@end
