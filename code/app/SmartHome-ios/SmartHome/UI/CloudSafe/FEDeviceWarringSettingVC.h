//
//  FEDeviceWarringSettingVC.h
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"
@class FESensor;

@interface FEDeviceWarringSettingVC : FECommonViewController

-(id)initWithSensor:(FESensor *)sensor markList:(NSArray *)marks;

@end
