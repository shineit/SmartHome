//
//  FECloudSafeTableCell.h
//  SmartHome
//
//  Created by Seven on 14-10-25.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FESensor;
@class FECloudSafeTableCell;

@protocol FECloudSafeTableCellDelegate <NSObject>

@optional
-(void)switchStatusDidChange:(FECloudSafeTableCell *)cell switcher:(UISwitch *)sw1;

@end

@interface FECloudSafeTableCell : UITableViewCell
@property (nonatomic, assign, getter = isdeviceOpen) BOOL deviceOpen;
@property (nonatomic, strong, readonly) FESensor *sensor;
@property (nonatomic, weak) id<FECloudSafeTableCellDelegate> delegate;

-(void)configWithSensor:(FESensor *)sensor;

@end
