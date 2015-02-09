//
//  FECameraItemCell.h
//  SmartHome
//
//  Created by Seven on 15-2-6.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>
@class YSCamera;

@interface FECameraItemCell : UITableViewCell
@property (strong, nonatomic) IBOutlet UIImageView *cameraImageView;
@property (strong, nonatomic) IBOutlet UILabel *titleLabel;
@property (nonatomic, strong, readonly) YSCamera *camera;

-(void)configWithCamera:(YSCamera *)camera;

@end
