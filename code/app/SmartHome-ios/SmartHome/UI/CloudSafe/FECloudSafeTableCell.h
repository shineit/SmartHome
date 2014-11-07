//
//  FECloudSafeTableCell.h
//  SmartHome
//
//  Created by Seven on 14-10-25.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FESensor;

@interface FECloudSafeTableCell : UITableViewCell
@property (nonatomic, assign, getter = isdeviceOpen) BOOL deviceOpen;

-(void)configWithSensor:(FESensor *)sensor;

@end
