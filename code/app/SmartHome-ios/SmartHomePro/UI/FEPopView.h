//
//  FEPopView.h
//  SmartHome
//
//  Created by Seven on 15-4-13.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FEPopView : UIView

@property (nonatomic, strong) UIView *tview;
@property (nonatomic, strong) UILabel *tlabel;
@property (nonatomic, strong) UIImageView *imageView;
@property (nonatomic, strong) UILabel *personLabel;
@property (nonatomic, strong) UILabel *timeLabel;
@property (strong, nonatomic) void (^action)();

- (id)initFromView:(UIView *)view action:(void(^)())action;
- (void)show;
- (void)dismiss;
//-(void)dismissNoAnimate;

@end
