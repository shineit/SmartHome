//
//  FEENavgationController.h
//  SmartHome
//
//  Created by Seven on 15-4-17.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECommonNavgationController.h"
@class FECompany;
@interface FEENavgationController : FECommonNavgationController
-(void)toAlarmSegue:(FECompany *)company;
@end
