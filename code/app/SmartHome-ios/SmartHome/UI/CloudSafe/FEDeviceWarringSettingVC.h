//
//  FEDeviceWarringSettingVC.h
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"
@class FESensor;

@protocol FEDeviceWarringSettingVCDelegate <NSObject>

@optional
//should refresh
-(void)sensorDidConfig;

@end

@interface FEDeviceWarringSettingVC : FECommonViewController
@property (nonatomic, weak) id<FEDeviceWarringSettingVCDelegate> delegate;

-(id)initWithSensor:(FESensor *)sensor markList:(NSArray *)marks;

@end
