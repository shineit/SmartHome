//
//  FECameraPlayerVC.h
//  SmartHome
//
//  Created by Seven on 15-2-9.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"
#import "YSCamera.h"
@interface FECameraPlayerVC : FECommonViewController
@property (nonatomic, strong) YSCamera *camera;
@property (nonatomic, strong) NSString *accessToken;
@end
