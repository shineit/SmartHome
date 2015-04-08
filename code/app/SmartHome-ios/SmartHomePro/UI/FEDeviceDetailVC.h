//
//  FEDeviceDetailVC.h
//  SmartHome
//
//  Created by Seven on 15-4-7.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"
@class FEFireAlarm;
@class FECompany;

@interface FEDeviceDetailVC : FECommonViewController

@property (nonatomic, strong) FEFireAlarm *device;
@property (nonatomic, strong) FECompany *company;

@end
