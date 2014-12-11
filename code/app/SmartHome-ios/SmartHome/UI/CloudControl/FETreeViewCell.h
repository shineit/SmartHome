//
//  FETreeViewCell.h
//  SmartHome
//
//  Created by Seven on 14-10-24.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>
@class FEControlObject;

@interface FETreeViewCell : UITableViewCell

-(void)configurelevel:(NSInteger)level withControlObj:(FEControlObject *)obj;

@end
