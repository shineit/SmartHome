//
//  FEPlanVC.h
//  SmartHome
//
//  Created by Seven on 15-4-8.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"
#import "FEFireAlarm.h"

@interface FEPlanVC : FECommonViewController
//@property (nonatomic, strong) NSNumber *planID;
@property (nonatomic, strong) FEFireAlarm *alarm;
@end
