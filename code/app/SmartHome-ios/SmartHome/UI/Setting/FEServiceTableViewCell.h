//
//  FEServiceTableViewCell.h
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>
@class FEOrder;

@interface FEServiceTableViewCell : UITableViewCell

@property (nonatomic, strong, readonly) FELabel *numberLabel;
@property (nonatomic, strong, readonly) FELabel *typeLabel;
@property (nonatomic, strong, readonly) FELabel *statusLabel;

-(void)configWithOrder:(FEOrder *)order;

@end
