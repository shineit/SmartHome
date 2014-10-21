//
//  FEWarringTableViewCell.h
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FEWarringTableViewCell : UITableViewCell

@property (nonatomic, strong, readonly) UIImageView *typeImageView;
@property (nonatomic, strong, readonly) UILabel *typeLabel;
@property (nonatomic, strong, readonly) UILabel *deviceLabel;
@property (nonatomic, strong, readonly) UILabel *statLabel;
@property (nonatomic, strong, readonly) UILabel *timeLabel;

@end
