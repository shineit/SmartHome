//
//  FETableViewController.h
//  SmartHome
//
//  Created by Seven on 15-1-27.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FETableViewController : UITableViewController
- (void)loadRightCustomButtonItemWithTitle:(NSString *)title image:(UIImage *)image;
-(void)rightbarpressed:(UIButton *)button;

@end
