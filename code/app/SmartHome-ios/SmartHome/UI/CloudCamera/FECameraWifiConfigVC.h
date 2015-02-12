//
//  FECameraWifiConfigVC.h
//  SmartHome
//
//  Created by Seven on 15-2-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FETableViewController.h"
@class FECameraCode;

@interface FECameraWifiConfigVC : FETableViewController
@property (nonatomic, strong) FECameraCode *ccode;
@property (nonatomic, strong) NSString *accessToken;
@end
