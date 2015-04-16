//
//  FECompanyVC.h
//  SmartHome
//
//  Created by Seven on 15-4-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FETableViewController.h"

typedef enum : NSUInteger {
    FIRE_ALARM,
    DEVICE_STATUS,
    CHECK,
    INFO,
    MANAGE
} MENU_TYPE;

@interface FECompanyVC : FETableViewController

@property (nonatomic, assign) MENU_TYPE type;
@property (nonatomic, strong) NSArray *numbers;

@end
