//
//  FESensorListResponse.h
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"

@interface FESensorListResponse : FEBaseResponse

@property (nonatomic, strong, readonly) NSArray *sensorList;

@end
