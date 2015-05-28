//
//  FEHistoryVC.h
//  SmartHome
//
//  Created by Seven on 15-5-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FETableViewController.h"
@class FECompany;
typedef enum : NSUInteger {
    FIRE_ALARM,
    DEVICE_STATUS,
    MANAGE
} HISTORY_TYPE;

@interface FEHistoryVC : FETableViewController

@property (nonatomic, assign) HISTORY_TYPE type;
@property (nonatomic, strong) FECompany *company;

@end
