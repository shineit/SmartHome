//
//  FEWarringTableViewCell.h
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef enum : NSUInteger {
    OFFLINE_ALARM = 1,
    FAULT_ALARM,
    SUBPRESSURE_ALARM,
    WARN_ALARM,
    ERROR_ALARM,
    FEEDBACK_ALARM,
    ACTION_ALARM,
    RESET_ALARM,
    SETUP_ALARM,
    REMOVE_ALARM
} FEAlartType;

@class FEAlarm;

@interface FEWarringTableViewCell : UITableViewCell

@property (nonatomic, strong, readonly) UIImageView *typeImageView;
@property (nonatomic, strong, readonly) UILabel *typeLabel;
@property (nonatomic, strong, readonly) UILabel *deviceLabel;
@property (nonatomic, strong, readonly) UILabel *statLabel;
@property (nonatomic, strong, readonly) UILabel *timeLabel;

-(void)configWithAlarm:(FEAlarm *)alarm;

@end
