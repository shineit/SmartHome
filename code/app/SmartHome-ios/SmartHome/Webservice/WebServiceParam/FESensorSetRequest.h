//
//  FESensorSetRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
@class FESensor;
@interface FESensorSetRequest : FERequestBaseData

@property (nonatomic, strong, readonly) NSNumber *command;
@property (nonatomic, strong, readonly) FESensor *sensor;

-(id)initWithCommond:(NSNumber *)cmd sensor:(FESensor *)sensor;

@end
