//
//  FEDeviceInfoView.h
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FEDeviceInfoView : UIView

@property (nonatomic, strong, readonly) FELabel *controlPoint;
@property (nonatomic, strong, readonly) FELabel *deviceNumber;
@property (nonatomic, strong, readonly) FELabel *deviceType;

@end
