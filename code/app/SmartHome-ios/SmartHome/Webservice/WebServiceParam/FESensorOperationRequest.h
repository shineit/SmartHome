//
//  FESensorOperationRequest.h
//  SmartHome
//
//  Created by Seven on 15-1-31.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FESensorOperationRequest : FERequestBaseData

@property (nonatomic, strong) NSNumber *userID;
@property (nonatomic, strong) NSArray *sensorList;

-(id)initWithUserId:(NSNumber *)uid enableSensors:(NSArray *)slist;
-(id)initWithUserId:(NSNumber *)uid disableSensors:(NSArray *)slist;

@end
