//
//  FEDeviceControllVC.h
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"

@class FESensor;

@protocol FEDeviceControllVCDelegate <NSObject>

@optional
-(void)sensorDidConfig;

@end

@interface FEDeviceControllVC : FECommonViewController

@property (nonatomic, strong, readonly) FESensor *sensor;
@property (nonatomic, strong) NSArray *marks;
@property (nonatomic, strong) id<FEDeviceControllVCDelegate> delegate;

-(id)initWithSensor:(FESensor *)sensor;

@end
